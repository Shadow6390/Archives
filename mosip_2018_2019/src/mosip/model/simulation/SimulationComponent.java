/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mosip.model.simulation;

import java.util.HashMap;
import java.util.Map;
import mosip.model.GlobalClock;
import mosip.model.GlobalClock.TimeClock;
import mosip.model.interfaces.ISimulationComponentState;
import mosip.model.simulation.states.SimulationComponentAliveState;
import mosip.model.simulation.states.SimulationComponentLoadingState;

/**
 *
 * @author jbraga
 */
public abstract class SimulationComponent {
    
    private Map<Integer,Boolean> locks;
    protected TimeClock clock;
    private ISimulationComponentState state;
    private Simulation simulation;
    
    public SimulationComponent()
    {
        locks = new HashMap();
        clock = new TimeClock();
        state = new SimulationComponentLoadingState();
    }
    
    /**
     * Sets the parent simulation to which this component belongs to.
     */
    public void setParentSimulation(Simulation sim)
    {
        this.simulation=sim;
    }
    
    public void changeState(ISimulationComponentState newState)
    {
        state = state.changeState(newState);
    }
    
    public void onTick(){
        if (state instanceof SimulationComponentAliveState)
        {
            clock.tick(GlobalClock.TICK_TIME);
        }
    }
    
    /**
     * Gets the current simulation's global clock.
     * @return 
     */
    protected GlobalClock getGlobalClock()
    {
        return simulation.getClock();
    }
    
    /**
     * Checks whether a lock has been acquired for the given index.
     * @param index
     * @return 
     */
    protected boolean hasLock(Integer index)
    {
        return locks.getOrDefault(index, Boolean.FALSE);
    }
    
    /**
     * Acquires a lock for this simulation. 
     * This essentially means that, if the lock is not cleared, no other actions
     * can be performed by this component (assuming the logic is implemented).
     * In this sense, sequential actions can be implemented, parallel to the
     * simulation's ticking process.
     * @param index
     * @return 
     */
    protected boolean acquireLock(Integer index){
        boolean result = true;
        if (locks.containsKey(index))
        {
            result = !hasLock(index);
        }
        if (result)
        {
            locks.put(index,true);
        }
        return result;
    }
    
    /**
     * Releases a lock acquired by a subprocess of this component.
     * @param index
     * @return 
     */
    protected boolean releaseLock(Integer index)
    {
        boolean result = false;
        if (hasLock(index))
        {
            locks.put(index, false);
            result = true;
        }
        return result;
    }
    
    protected boolean checkComponentState(ISimulationComponentState cstate)
    {
        boolean result = this.state.getClass().equals(cstate.getClass());
        return result;
    }
    
    public abstract void doStep();
}
