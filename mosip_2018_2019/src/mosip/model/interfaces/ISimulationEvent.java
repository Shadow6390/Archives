/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mosip.model.interfaces;

/**
 *
 * @author Diogo
 */
public interface ISimulationEvent
{
    public void onEventFinished();
    public IEventDetails getEventDetails();
    public IEventState getState();
    public void changeEventState(IEventState newState);
}
