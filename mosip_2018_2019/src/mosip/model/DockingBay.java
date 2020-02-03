/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mosip.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import mosip.model.events.ExtractorFaultDetectedEvent;
import mosip.model.events.ExtractorFaultDetectedEventDetails;
import mosip.model.interfaces.IBayPolicy;
import mosip.model.interfaces.IExtractor;
import mosip.model.interfaces.IShip;
import mosip.model.simulation.Simulation;
import mosip.model.utils.Tuple;

/**
 *
 * @author jbraga
 */
public class DockingBay {
    /**
     * The current ship in the bay.
     */
    private IShip currentShip;
    /**
     * The extractors available in the docking bay.
     */
    private Map<IExtractor,Tuple<Integer,Integer>> extractors;
    
    /**
     * The bridge of the docking bay.
     */
    private Bridge bridge;
    
    private List<UndockListener> undockListeners;
    
    private Simulation sim;
    
    private SimulationController controller;
    
    private IBayPolicy policy;
    
    private int id;
    
    private static int ID_COUNTER=1;
    
    private List<Double> waitingTimes;
    
    private List<Double> delayTimes;

    /**
     * Enumerates reasons a ship cannot be docked.
     */
    public static enum NoDockReason{
        BRIDGE_TOO_LOW,SHIP_NOT_SUPPORTED,DOCK_FULL,
        NO_REASON_CAN_DOCK
    }
    
    public DockingBay(Map<IExtractor,Tuple<Integer,Integer>> extractors,
            Bridge bridge,
            Simulation sim, 
            SimulationController controller,
            IBayPolicy policy)
    {
        this.extractors=extractors;
        undockListeners = new LinkedList();
        this.bridge=bridge;
        this.sim=sim;
        this.controller=controller;
        this.policy=policy;
        this.id = ID_COUNTER++;
        waitingTimes = new ArrayList<>();
        delayTimes = new ArrayList<>();
    }
    
    public void dockShip(IShip ship){
        /*
        Deprecated
        if (canDockShip(ship))
        {
            this.currentShip=ship;
            final IExtractor extractor = getExtractorForShip(ship);
            new Thread(() ->
            {
                ship.dock(this);
                extractor.doExtraction(currentShip,extractors.get(extractor));
            }).start();
        }*/
        if (canDockShip(ship))
        {
            this.currentShip=ship;
            ship.dock(this);
        }
    }
    
    public void extractFromDockedShip()
    {
        if (currentShip!=null)
        {
            final IExtractor extractor = getExtractorForShip(currentShip);
            Tuple<Integer,Integer> pair = extractors.get(extractor);
            int expectedCount = pair.x;
            int actualCount = pair.y;
            if (extractor.doFaultEvent(actualCount>0))
            {
                actualCount--;
                extractors.put(extractor, new Tuple<>(expectedCount,actualCount));
                //Fire repair detected event
                sim.addEvent(new ExtractorFaultDetectedEvent(
                        new ExtractorFaultDetectedEventDetails(sim.getClock().getTime(), extractor, this, sim,actualCount<=0)));
            }
            if (actualCount>0)
            {
                extractor.doEventExtraction(currentShip,actualCount,expectedCount);
            }
        }
    }
    
    public void loadDockedShip()
    {
        if (currentShip!=null)
        {
            final IExtractor extractor = getExtractorForShip(currentShip);
            Tuple<Integer,Integer> pair = extractors.get(extractor);
            int expectedCount = pair.x;
            int actualCount = pair.y;
            extractor.doEventLoad(currentShip,actualCount,expectedCount);
        }
    }
    
    /**
     * Undocks the ship from the bay, notifying any UndockListeners of the change.
     */
    public void undockShipEvent()
    {
        currentShip.undock(this);
    }
    
    public void undockShip()
    {
        if (currentShip!=null)
        {
            notifyUndocking();
            reportDelay();
            this.currentShip=null;
        }
    }
    
    public void notifyUndocking()
    {
        for (UndockListener elem:undockListeners)
        {
            elem.onUndock(this.currentShip, this);
        }
    }
    
    /**
     * Adds an Undock listener.
     * @param l
     * @return 
     */
    public boolean addUndockListener(UndockListener l)
    {
        return undockListeners.add(l);
    }
    
    /**
     * Whether a ship can be docked or not.
     * @param ship
     * @return 
     */
    public boolean canDockShip(IShip ship)
    {
        return getDockingReason(ship)==NoDockReason.NO_REASON_CAN_DOCK;
    }
    
    public void changeBridgeHeight(double height)
    {
        bridge.changeHeight(height,sim,controller);
    }
    
    public boolean isBridgeHeightChanging()
    {
        return bridge.isHeightChanging();
    }

    public NoDockReason getDockingReason(IShip ship)
    {
        NoDockReason result = NoDockReason.NO_REASON_CAN_DOCK;
        if (currentShip!=null)
        {
            result = NoDockReason.DOCK_FULL;
        }
        else if (!policy.obeysPolicy(ship))
        {
            result = NoDockReason.SHIP_NOT_SUPPORTED;
        }
        else if (!bridge.hasHeightFor(ship)) //ORDER IS IMPORTANT! BRIDGE SHOULD BE THE LAST REASON
            //TO DECLINE A SHIP!
        {
            result = NoDockReason.BRIDGE_TOO_LOW;
        }
        return result;
    }
    
    public IExtractor getExtractorForShip(IShip ship)
    {
        for (Entry<IExtractor,Tuple<Integer,Integer>> elem:extractors.entrySet())
        {
            if (elem.getKey().canExtract(ship))
            {
                return elem.getKey();
            }
        }
        throw new IllegalArgumentException("This bay does not have an extractor for "+ship);
    }
    
    public String getName()
    {
        return "Docking Bay "+id;
    }
    
    public Integer getNumberDockedShips()
    {
        return (currentShip!=null) ? 1 : 0;
    }
    
    public int numberExtractors(Class extract)
    {
        int result = -1;
        for (Entry<IExtractor,Tuple<Integer,Integer>> elem:extractors.entrySet())
        {
            if (elem.getKey().getClass().equals(extract))
            {
                result=elem.getValue().y;
                break;
            }
        }
        return result;
    }
    
    public void addWaitingTime(double waitingTime)
    {
        waitingTimes.add(waitingTime);
    }
    
    public void addDelayTime(double delayTime)
    {
        delayTimes.add(delayTime);
    }
    
    public double getAverageWaitingTime()
    {
        double result = 0;
        for (Double elem:waitingTimes)
        {
            result+=elem;
        }
        result/=Math.max(waitingTimes.size(),1.0);
        return result;
    }
    
    /**
     * Forces the current ship to report the delay it got in this dock, before leaving.
     */
    public void reportDelay()
    {
        if (currentShip!=null)
        {
            currentShip.reportDelayTime(this);
        }
    }
    
    public void repairExtractor(IExtractor extractor)
    {
        Tuple<Integer,Integer> t = extractors.get(extractor);
        extractors.put(extractor, new Tuple(t.x,t.y+1));
    }
    
    public double getAverageDelayTime()
    {
        double result = 0;
        for (Double elem:delayTimes)
        {
            result+=elem;
        }
        result/=Math.max(delayTimes.size(),1.0);
        return result;
    }
}
