/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mosip.model;

import mosip.model.events.LoadingShipEvent;
import mosip.model.events.LoadingShipEventDetails;
import mosip.model.events.UndockShipEvent;
import mosip.model.events.UndockShipEventDetails;
import mosip.model.events.UnloadShipEvent;
import mosip.model.events.UnloadShipEventDetails;
import mosip.model.interfaces.IRandomDistribution;
import mosip.model.simulation.Simulation;

/**
 *
 * @author jbraga
 */
public class EventPetrolShip extends BaseShip{

    public EventPetrolShip(double arrivalTime, double requiredHeight, int maxExtractorsAllowed,Simulation sim,RandomSeedFactory factory,int randomStream,
            IRandomDistribution distribution,
            SimulationController controller)
    {
        super(arrivalTime, requiredHeight, maxExtractorsAllowed,sim,factory,randomStream,distribution,controller);
    }

    @Override
    public void performUnloading(int numberExtractors,int expectedNumberExtractors)
    {
        sim.addEvent(new UnloadShipEvent(
                new UnloadShipEventDetails(getUnloadTime(numberExtractors,expectedNumberExtractors),this,bay,sim)));
    }

    @Override
    public void performLoading(int numberExtractors,int expectedNumberExtractors)
    {
        sim.addEvent(new LoadingShipEvent(
                new LoadingShipEventDetails(getLoadTime(numberExtractors,expectedNumberExtractors),this,bay,sim)));
    }
    
    private double getUnloadTime(double numberExtractors, double expectedNumberExtractors)
    {
        double base = sim.getClock().getTime();
        double ddist = distribution.randomRange(270.0/numberExtractors, 970.0/numberExtractors);
        if (expectedNumberExtractors!=numberExtractors)
        {
            this.addDelayTime(ddist*numberExtractors/expectedNumberExtractors);
        }
        double result = base+ddist;
        return result;
    }
    
    private double getLoadTime(double numberExtractors, double expectedNumberExtractors)
    {
        double base = sim.getClock().getTime();
        double ddist = distribution.randomRange(340.0/numberExtractors, 560.0/numberExtractors);
        if (expectedNumberExtractors!=numberExtractors)
        {
            this.addDelayTime(ddist*numberExtractors/expectedNumberExtractors);
        }
        double result = base+ddist;
        return result;
    }

    @Override
    public double getDockingTime()
    {
        double base = sim.getClock().getTime();
        return distribution.randomRange(base+10.0,base+40.0);
    }

    @Override
    public double getUndockingTime()
    {
        double base = sim.getClock().getTime();
        return distribution.randomRange(base+10.0,base+40.0);
    }

    @Override
    public void undock(DockingBay bay)
    {
        sim.addEvent(new UndockShipEvent(new UndockShipEventDetails(getUndockingTime(),this,bay,controller,sim),sim));
    }
    
    @Override
    public double[] getTimes()
    {
        double[] times = new double[]{25,620,400,25};
        return times;
    }

    @Override
    public String getShipClassName()
    {
        return "Petrol Ship";
    }
}
