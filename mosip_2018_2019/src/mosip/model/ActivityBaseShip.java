/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mosip.model;

import mosip.model.interfaces.IShip;
import mosip.model.interfaces.ISimulationComponentState;
import mosip.model.simulation.SimulationComponent;

/**
 *
 * @author Diogo
 */
public abstract class ActivityBaseShip extends SimulationComponent implements IShip
{
    private double arrivalTime;
    private double loadingTime;
    private double unloadingTime;
    private double enterDockTime;
    private double leaveDockTime;
    private double requiredBridgeHeight;
    
    protected GlobalClock.TimeClock enterDockClock;
    protected GlobalClock.TimeClock leaveDockClock;
    protected GlobalClock.TimeClock loadingClock;
    protected GlobalClock.TimeClock unloadingClock;
    protected DockingBay bay;
    protected int maxExtractorsAllowed;
    protected int numberExtractors;
    
    public static enum ShipType{
        CARGO, OIL, CRUISE,UNKNOWN
    }
    
    /**
     * 
     * @param arrivalTime The time at which the ship arrived
     * @param enterTime Time it takes for the ship to dock
     * @param leaveTime Time it takes for the ship to undock
     * @param requiredHeight The required bridge height for the ship to dock.
     * @param loadingTime
     * @param unloadingTime
     * @param maxExtractorsAllowed
     */
    public ActivityBaseShip(double arrivalTime, double enterTime, double leaveTime, double requiredHeight,double loadingTime,double unloadingTime,int maxExtractorsAllowed)
    {
        this.arrivalTime=arrivalTime;
        this.enterDockTime=enterTime;
        this.leaveDockTime=leaveTime;
        this.requiredBridgeHeight=requiredHeight;
        this.loadingTime=loadingTime;
        this.unloadingTime=unloadingTime;
        this.maxExtractorsAllowed=maxExtractorsAllowed;
        unloadingClock = new GlobalClock.TimeClock();
        unloadingClock.setEnd(unloadingTime);
        loadingClock = new GlobalClock.TimeClock();
        loadingClock.setEnd(loadingTime);
    }

    @Override
    public void dock(DockingBay bay)
    {
        this.bay=bay;
        //Since we've entered the bay, we know when we've started: right at the current
        //clock tick of the global clock.
        enterDockClock = new GlobalClock.TimeClock();
        enterDockClock.setStart(getGlobalClock().getTime());
        enterDockClock.reset();
        enterDockClock.setEnd(enterDockClock.getStart()+enterDockTime);
        //Acquire lock for arriving at bay
        this.acquireLock(0);
        
        //Acquire lock for leaving the bay
        this.acquireLock(1);
        leaveDockClock = new GlobalClock.TimeClock();
        leaveDockClock.setEnd(leaveDockClock.getStart()+leaveDockTime);
    }
    
    /**
     * Executes the standard code for docking a ship.
     * @return False if the ship has been docked. True if the process is still being
     * executed.
     */
    protected boolean enterDock()
    {
        boolean result = enterDockClock.getTime()<enterDockClock.getEnd();
        if (result)
        {
            enterDockClock.tick(GlobalClock.TICK_TIME);
        }
        else if (hasLock(0))
        {
            releaseLock(0);
            unloadingClock.setStart(getGlobalClock().getTime());
            unloadingClock.setEnd(unloadingClock.getStart()+unloadingClock.getEnd());
            unloadingClock.reset();
        }
        return result;
    }
    
    /**
     * Executes the standard code for undocking a ship.
     * @return False if the ship has been undocked. True if the process is still being
     * executed.
     */
    protected boolean leaveDock()
    {
        boolean result = leaveDockClock.getTime()<leaveDockClock.getEnd();
        if (result)
        {
            leaveDockClock.tick(GlobalClock.TICK_TIME);
        }
        else if (hasLock(1))
        {
            releaseLock(1);
        }
        return result;
    }
    
    protected boolean checkState(ISimulationComponentState state)
    {
        return checkComponentState(state);
    }
    
    @Override
    public double getBridgeHeightLimit()
    {
        return requiredBridgeHeight;
    }
    
    /**
     * @return the arrivalTime
     */
    public double getArrivalTime()
    {
        return arrivalTime;
    }

    /**
     * @return the enterDockTime
     */
    public double getEnterDockTime()
    {
        return enterDockTime;
    }

    /**
     * @return the leaveDockTime
     */
    public double getLeaveDockTime()
    {
        return leaveDockTime;
    }

    /**
     * @return the requiredBridgeHeight
     */
    public double getRequiredBridgeHeight()
    {
        return requiredBridgeHeight;
    }

}
