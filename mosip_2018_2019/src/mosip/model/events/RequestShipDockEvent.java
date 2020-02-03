/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mosip.model.events;

import mosip.model.events.states.CompletedEventState;
import mosip.model.interfaces.IEventDetails;
import mosip.model.interfaces.IReport;

/**
 *
 * @author Diogo
 */
public class RequestShipDockEvent extends SimulationEventBase
{

    public RequestShipDockEvent(IEventDetails details)
    {
        super(details);
        System.out.println(getClass()+" started");
    }

    @Override
    public void onEventFinished()
    {
        //System.out.println(getClass()+" ended");
        RequestShipDockEventDetails d = (RequestShipDockEventDetails)getEventDetails();
        //Should only try to dock (and not dock a specific ship, as the ship will
        //already be present in the list).
        d.getSimulationController().dockNextShip();
        changeEventState(new CompletedEventState());
    }
    
    @Override
    public void reportResults(IReport report)
    {
        //Do nothing...
    }
}
