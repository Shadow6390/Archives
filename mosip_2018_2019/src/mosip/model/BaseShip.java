/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mosip.model;

import mosip.model.events.DockShipEvent;
import mosip.model.events.DockShipEventDetails;
import mosip.model.events.RequestShipDockEvent;
import mosip.model.events.RequestShipDockEventDetails;
import mosip.model.interfaces.IRandomDistribution;
import mosip.model.interfaces.IShip;
import mosip.model.simulation.Simulation;

/**
 *
 * @author Diogo
 */
public abstract class BaseShip implements IShip
{
    private double requiredBridgeHeight;
    
    protected DockingBay bay;
    protected int maxExtractorsAllowed;
    protected int numberExtractors;
    protected Simulation sim;
    protected SimulationController controller;
    protected RandomSeedFactory factory;
    protected int randomStream;
    protected IRandomDistribution distribution;
    protected GlobalClock.TimeClock waitClock;
    
    /**
     * Amount of time this ship has been delayed. Occurs during loading and unloading
     */
    protected double delayTime;
    
    private static int SHIP_ID=1;
    
    private int ID;
    
    public static enum ShipType{
        CARGO, OIL, CRUISE,UNKNOWN
    }
    
    /**
     * 
     * @param arrivalTime The time at which the ship arrived
     * @param requiredHeight The required bridge height for the ship to dock.
     * @param maxExtractorsAllowed
     * @param sim
     * @param factory
     * @param randomStream
     * @param controller
     */
    public BaseShip(double arrivalTime, double requiredHeight,int maxExtractorsAllowed,Simulation sim, RandomSeedFactory factory,int randomStream,
            IRandomDistribution distribution,
            SimulationController controller)
    {
        this.requiredBridgeHeight=requiredHeight;
        this.maxExtractorsAllowed=maxExtractorsAllowed;
        this.sim=sim;
        this.controller=controller;
        this.factory=factory;
        this.distribution=distribution;
        this.randomStream=randomStream;
        this.delayTime=0;
        ID=SHIP_ID++;
        sim.addEvent(new RequestShipDockEvent(new RequestShipDockEventDetails(arrivalTime,controller,sim)));
    }

    @Override
    public void dock(DockingBay bay)
    {
        this.bay=bay;
        double dockFinishTime = getDockingTime();
        sim.addEvent(new DockShipEvent(new DockShipEventDetails(dockFinishTime,this,bay,sim)));
    }
    
    @Override
    public double getBridgeHeightLimit()
    {
        return requiredBridgeHeight;
    }

    /**
     * @return the requiredBridgeHeight
     */
    public double getRequiredBridgeHeight()
    {
        return requiredBridgeHeight;
    }
    
    protected void addDelayTime(double delay)
    {
        this.delayTime+=delay;
    }
    
    @Override
    public void reportDelayTime(DockingBay bay)
    {
        if (bay!=null)
        {
            bay.addDelayTime(delayTime);
        }
    }
    
    public abstract double getDockingTime();
    public abstract double getUndockingTime();

    @Override
    public boolean equals(Object other)
    {
        boolean result = other!=null && getClass().isInstance(other);
        if (result)
        {
            BaseShip b = (BaseShip) other;
            result = this==other || ID==b.ID;
        }
        return result;
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 19 * hash + this.ID;
        return hash;
    }
    
    @Override
    public String toString()
    {
        return getClass()+"["+ID+"]";
    }
    
    @Override
    public void registerStartWaitingTime()
    {
        this.waitClock=new GlobalClock.TimeClock();
        waitClock.setStart(sim.getClock().getTime());
    }
    
    @Override
    public void registerEndWaitingTime()
    {
        waitClock.setEnd(sim.getClock().getTime());
    }
    
    @Override
    public double getWaitingTime()
    {
        if (waitClock==null)
        {
            throw new RuntimeException("getWaitingTime was called but the ship has yet to start waiting");
        }
        
        return waitClock.getEnd()-waitClock.getStart();
    }
}
