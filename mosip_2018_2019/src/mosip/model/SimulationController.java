/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mosip.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import mosip.model.events.SpawnShipEvent;
import mosip.model.events.SpawnShipEventDetails;
import mosip.model.interfaces.IBayPolicy;
import mosip.model.interfaces.IExtractor;
import mosip.model.interfaces.IRandomDistribution;
import mosip.model.interfaces.IReport;
import mosip.model.interfaces.IReportComponent;
import mosip.model.interfaces.IShip;
import mosip.model.interfaces.IShipController;
import mosip.model.reporting.LeixoesCargoReportData;
import mosip.model.simulation.Simulation;
import mosip.model.simulation.SimulationComponent;
import mosip.model.simulation.states.SimulationComponentAliveState;
import mosip.model.utils.Tuple;

/**
 *
 * @author Diogo
 */
public class SimulationController extends SimulationComponent implements UndockListener, IReportComponent
{

    private List<DockingBay> bays;
    private List<IShip> shipPool;
    private List<IShipController> controllers;
    private List<IShip> processedShips;
    private RandomSeedFactory factory;
    private IRandomDistribution distribution;
    /**
     * This class' random stream.
     */
    private int randomStream;
    private Simulation simulation;
    private SimulationConfiguration config;
    private int shipCount=0;
    
    /**
     * Must contain an ordered set of ships that should be spawned.
     * The order is defined by the moment at which the ship arrives at the
     * "ship pool" to enter the docking bay.
     */
    private Queue<Tuple<Double,IShip>> spawningShips;
    
    private final int SEED=500;
    
    public SimulationController(RandomSeedFactory factory,SimulationConfiguration config)
    {
        this.factory=factory;
        this.randomStream = factory.allocateNewRandom(config.simRandomSeed);
        this.changeState(new SimulationComponentAliveState());
        controllers = new ArrayList();
        bays = new ArrayList();
        shipPool = new LinkedList();
        spawningShips=new LinkedList<>();
        processedShips = new ArrayList<>();
        this.config=config;
        setup(config);
    }
    
    private void setup(SimulationConfiguration config)
    {
        simulation=config.simulation;
        distribution=config.simControllerDist;
        distribution.setRandomStream(factory.getRandomStream(this.randomStream));
        initControllers(config);
        for (int i=0;i<config.bays.length;i++)
        {
            Bridge b = new Bridge(config.bays[i].bridgeHeight,config.bays[i].bridgeSpeed);
            Map<IExtractor,Tuple<Integer,Integer>> exts = buildExtractorMap(config.bays[i].extractors,i);
            IBayPolicy policy = null;
            switch (config.bays[i].policy)
            {
                case ONLY_SHORT_DURATION:
                    policy = new BayPolicyFrequentOnly();
                    break;
                default:
                    policy = new BayPolicyAcceptAll();
                    break;
            }
            DockingBay bay = new DockingBay(exts,b,simulation,this,policy);
            bay.addUndockListener(this);
            bays.add(bay);
        }
    }
    
    /**
     * Creates a map with a docking bay's extractors from the given dummy/dto map.
     * @param dummyMap 
     */
    private Map<IExtractor,Tuple<Integer,Integer>> buildExtractorMap(Map<SimulationConfiguration.DummyDockingBay.Extractors,Integer> dummyMap, int bayIndex)
    {
        Map<IExtractor,Tuple<Integer,Integer>> result = new HashMap<>();
        for (Entry<SimulationConfiguration.DummyDockingBay.Extractors,Integer> elem:dummyMap.entrySet())
        {
            int randomSeed;
            IRandomDistribution dist;
            double chanceOfFailure;
            try
            {
                switch (elem.getKey())
                {
                    case CRANE:
                        randomSeed = config.bays[bayIndex].extRS.get(elem.getKey());
                        dist = config.bays[bayIndex].extDists.get(elem.getKey()).convert();
                        chanceOfFailure = config.bays[bayIndex].failureChance.get(elem.getKey());
                        IExtractor crane = new Crane(dist,
                                factory,
                                factory.allocateNewRandom(randomSeed),
                                chanceOfFailure);
                        result.put(crane,new Tuple(elem.getValue(),elem.getValue()));
                        break;
                    case STAIRS:
                        randomSeed = config.bays[bayIndex].extRS.get(elem.getKey());
                        dist = config.bays[bayIndex].extDists.get(elem.getKey()).convert();
                        chanceOfFailure = config.bays[bayIndex].failureChance.get(elem.getKey());
                        IExtractor e = new DockingStairs(dist,factory,factory.allocateNewRandom(randomSeed),chanceOfFailure);
                        result.put(e, new Tuple(elem.getValue(),elem.getValue()));
                        break;
                    case PUMP:
                        randomSeed = config.bays[bayIndex].extRS.get(elem.getKey());
                        dist = config.bays[bayIndex].extDists.get(elem.getKey()).convert();
                        chanceOfFailure = config.bays[bayIndex].failureChance.get(elem.getKey());
                        IExtractor p = new Pump(dist,factory,factory.allocateNewRandom(randomSeed),chanceOfFailure);
                        result.put(p, new Tuple(elem.getValue(),elem.getValue()));
                        break;
                }
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
                System.exit(5);
            }
        }
        return result;
    }
    
    private void initControllers(SimulationConfiguration config)
    {
        controllers.add(new CargoController(factory, 
                factory.allocateNewRandom(config.randomSeeds[0]),
                config.distributions[0],simulation,this));
        controllers.add(new CruiseController(factory, 
                factory.allocateNewRandom(config.randomSeeds[1]),
                config.distributions[1],simulation,this));
        controllers.add(new PetrolController(factory, 
                factory.allocateNewRandom(config.randomSeeds[2]),
                config.distributions[2],simulation,this));
    }
    
    public void scheduleShipSpawn()
    {
        if (shipCount<config.expectedShipCount)
        {
            double t = simulation.getClock().getTime();
            double end = simulation.getClock().getEnd();
            double interval = end-t;
            double nextT = distribution.randomRange(t, end-(interval*(config.shipSpawnFrequency/100.0)));
            BaseShip.ShipType type = null;
            double chance = factory.getRandomStream(randomStream).nextDouble();
            double sum = 0;
            for (int i=0;i<config.spawnPercentage.length;i++)
            {
                if (chance<sum+config.spawnPercentage[i])
                {
                    type=config.shipType[i];
                    break;
                }
                sum+=config.spawnPercentage[i];
            }
            simulation.addEvent(new SpawnShipEvent(new SpawnShipEventDetails(nextT,
                    getControllerFor(type),type,this,simulation)));
            shipCount++;
        }
    }
    
    /**
     * Generates the times at which ships appear.
     * The method generates the timestamp and type of ship that will appear.
     * @param config The configuration of the simulation.
     * @param timeStart The time at which the simulation should start spawning ships.
     * @param timeEnd Time time at which the simulation should stop spawning ships.
     */
    public void generateShips(SimulationConfiguration config,double timeStart, double timeEnd)
    {
        List<Tuple<Double,IShip>> ships = new ArrayList<>();
        List<Double> times = new ArrayList<>();
        for (int i=0;i<config.expectedShipCount;i++)
        {
            //double limit = config.simulation.getClock().getEnd();
            double time = distribution.randomRange(timeStart, timeEnd);
            times.add(time); 
            //Must be replaced with a random distribution class
        }
        double originalSize = times.size();
        for (int i=0;i<config.spawnPercentage.length-1;i++)
        {
            double spawn = 0;
            double ratio = spawn/originalSize;
            IShipController controller = getControllerFor(config.shipType[i]);
            while (ratio<config.spawnPercentage[i])
            {
                Double time = times.remove(distribution.randomRange(0,(int)times.size()));
                ships.add(new Tuple<>(time,controller.spawnActivityShip(config.shipType[i],time)));
                spawn++;
                ratio = spawn/originalSize;
            }
            originalSize-=spawn;
        }
        //Assuming we have at least one controller. If not, throw an error
        if (config.spawnPercentage.length>0)
        {
            //If we had at least one, in this case, we're dealing with the last controller
            //In the last controller, all remaining 'times' will be of the controller's type.
            IShipController controller = getControllerFor(config.shipType[config.shipType.length-1]);
            for (Double time:times)
            {
                ships.add(new Tuple<>(time,controller.spawnActivityShip(config.shipType[config.shipType.length-1],time)));
            }
        }
        else
        {
            throw new IllegalArgumentException("Invalid simulation configuration: there must exist ships to spawn!");
        }
        //Sort by time
        ships.sort((Tuple<Double, IShip> o1, Tuple<Double, IShip> o2) -> Double.compare(o1.x, o2.x));
        spawningShips.addAll(ships);
    }

    protected IShipController getControllerFor(BaseShip.ShipType type)
    {
        IShipController controller = null;
        for (IShipController elem:controllers)
        {
            if (elem.canSpawn(type))
            {
                controller=elem;
                break;
            }
        }
        if (controller==null)
        {
            throw new RuntimeException("No controllers found that can spawn '"+type+"' ship");
        }
        return controller;
    }
    
    @Override
    public void doStep()
    {
        System.out.println(simulation.getClock());
        while (!spawningShips.isEmpty() && simulation.getClock().getTime()>=spawningShips.peek().x)
        {
            IShip ship = spawningShips.poll().y;
            simulation.addComponent((SimulationComponent)ship);
            shipPool.add(ship);
        }
        dockNextShip();
    }
    
    public void dockNextShip()
    {
        if (!shipPool.isEmpty())
        {
            boolean docked = false;
            List<DockingBay.NoDockReason> reasons = new ArrayList<>();
            for (DockingBay elem:bays)
            {
                IShip ship = getFittestShip(elem);
                if (ship!=null && elem.canDockShip(ship))
                {
                    elem.dockShip(ship);
                    shipPool.remove(ship);
                    docked = true;
                    break;
                }
                else 
                {
                    reasons.add(elem.getDockingReason(ship));
                }
            }
            if (!docked) //If we were unable to dock, we need to know why
            {
                for (int i=0;i<reasons.size();i++)
                {
                    DockingBay.NoDockReason elem = reasons.get(i);
                    if (elem==DockingBay.NoDockReason.BRIDGE_TOO_LOW)
                    {
                        DockingBay bay = bays.get(i);
                        if (!bay.isBridgeHeightChanging())
                        {
                            //Fetch max bridge height, so we don't have to keep changing the bridge every time
                            double maxHeight = fetchMaxBridgeHeightRequirement();
                            bay.changeBridgeHeight(maxHeight);
                        }
                        break;
                    }
                }
            }
        }
    }
    
    protected IShip getFittestShip(DockingBay bay)
    {
        IShip result = null;
        Iterator<IShip> ships = shipPool.iterator();
        while (ships.hasNext())
        {
            IShip elem = ships.next();
            if (bay.canDockShip(elem))
            {
                result = elem;
                break;
            }
            else
            {
                DockingBay.NoDockReason e = bay.getDockingReason(elem);
                if (e==DockingBay.NoDockReason.BRIDGE_TOO_LOW && result==null)
                {
                    result = elem;
                }
            }
        }
        return result;
    }

    protected double fetchMaxBridgeHeightRequirement()
    {
        double result = Double.NEGATIVE_INFINITY;
        for (IShip elem:shipPool)
        {
            if (result<elem.getBridgeHeightLimit())
            {
                result = elem.getBridgeHeightLimit();
            }
        }
        return result;
    }
    
    @Override
    public void onUndock(IShip ship, DockingBay bay)
    {
        processedShips.add(ship);
    }

    public boolean addShipToPool(IShip s)
    {
        return shipPool.add(s);
    }

    public void verboseResults()
    {
        System.out.println("==========SIMULATION_CONTROLLER_RESULTS=============");
        System.out.println("Processed Ships\n\n"+processedShips+"\n-------------");
        System.out.println("Ship Pool\n\n"+shipPool+"\n-------------");
    }

    @Override
    public void reportResults(IReport report)
    {
        for (IShip elem:processedShips)
        {
            int num = report.getReportData().numberShipsProcessed.getOrDefault(elem.getShipClassName(),0);
            num++;
            report.getReportData().numberShipsProcessed.put(elem.getShipClassName(),num);
        }
        for (IShip elem:shipPool)
        {
            int num = report.getReportData().numberShipsPool.getOrDefault(elem.getShipClassName(),0);
            num++;
            report.getReportData().numberShipsPool.put(elem.getShipClassName(),num);
        }
        double sum = 0.0;
        double sumDelay = 0.0;
        double avgW = 0;
        double avgD = 0;
        for (DockingBay elem:bays)
        {
            report.getReportData().numberShipsInDock.put(elem.getName(),elem.getNumberDockedShips());
            report.getReportData().bayData.get(elem.getName()).numberOfPumps=elem.numberExtractors(Pump.class);
            report.getReportData().bayData.get(elem.getName()).numberOfCranes=elem.numberExtractors(Crane.class);
            report.getReportData().bayData.get(elem.getName()).numberOfStairs=elem.numberExtractors(DockingStairs.class);
            avgW = elem.getAverageWaitingTime();
            avgD = elem.getAverageDelayTime();
            report.getReportData().bayData.get(elem.getName()).averageWaitingTime=avgW;
            report.getReportData().bayData.get(elem.getName()).averageDelayTime=avgD;
            sumDelay+=avgD;
            sum+=avgW;
        }
        sum/=Math.max(1.0,bays.size());
        sumDelay/=Math.max(1.0,bays.size());
        report.getReportData().globalResults.averageWaitingTime=sum;
        report.getReportData().globalResults.averageDelayTime=sumDelay;
    }

    public void createDockingBayReportAreas(IReport report)
    {
        LeixoesCargoReportData data = report.getReportData();
        for (DockingBay elem:bays)
        {
            data.bayData.put(elem.getName(),new LeixoesCargoReportData.ReportBundle());
        }
    }
    
}
