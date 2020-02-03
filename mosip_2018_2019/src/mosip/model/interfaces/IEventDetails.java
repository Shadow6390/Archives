/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mosip.model.interfaces;

import mosip.model.GlobalClock;

/**
 *
 * @author Diogo
 */
public interface IEventDetails
{
    public double getTimeStamp();
    public GlobalClock.TimeClock getClock();
}
