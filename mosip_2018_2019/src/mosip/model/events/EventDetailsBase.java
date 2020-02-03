/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mosip.model.events;

import mosip.model.GlobalClock;
import mosip.model.interfaces.IEventDetails;

/**
 *
 * @author Diogo
 */
public abstract class EventDetailsBase implements IEventDetails
{
    private double timeStamp;
    private GlobalClock.TimeClock clock;
    
    public EventDetailsBase(double timeStamp,double timeStart)
    {
        this.timeStamp=timeStamp;
        this.clock = new GlobalClock.TimeClock(timeStart,timeStamp);
    }
    
    @Override
    public double getTimeStamp()
    {
        return this.timeStamp;
    }
    
    @Override
    public GlobalClock.TimeClock getClock()
    {
        return clock;
    }
}
