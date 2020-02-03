/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mosip.model.events;

import mosip.model.GlobalClock;
import mosip.model.events.states.CreatedEventState;
import mosip.model.interfaces.IEventDetails;
import mosip.model.interfaces.IEventState;
import mosip.model.interfaces.IReportComponent;
import mosip.model.interfaces.ISimulationEvent;

/**
 *
 * @author Diogo
 */
public abstract class SimulationEventBase implements ISimulationEvent, IReportComponent
{
    private IEventDetails details;
    
    private IEventState state;
    
    private GlobalClock.TimeClock clock;
    
    public SimulationEventBase(IEventDetails details)
    {
        this.details=details;
        state = new CreatedEventState();
        clock = new GlobalClock.TimeClock();
    }
    
    @Override
    public IEventDetails getEventDetails()
    {
        return details;
    }
    
    @Override
    public IEventState getState()
    {
        return state;
    }
    
    @Override
    public void changeEventState(IEventState newState)
    {
        this.state=state.changeState(newState);
    }
    
    @Override
    public String toString()
    {
        return getClass()+"[state="+state.getClass()+"]";
    }
    
    /**
     * Signals the event to register when it finished and when it started.
     */
    public void registerEventTimeFrame()
    {
        
    }
}
