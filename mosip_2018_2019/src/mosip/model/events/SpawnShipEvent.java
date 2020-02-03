/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mosip.model.events;

import mosip.model.BaseShip;
import mosip.model.events.states.CompletedEventState;
import mosip.model.events.states.TerminatedEventState;
import mosip.model.interfaces.IEventDetails;
import mosip.model.interfaces.IReport;
import mosip.model.interfaces.IShip;

/**
 *
 * @author Diogo
 */
public class SpawnShipEvent extends SimulationEventBase
{

    public SpawnShipEvent(IEventDetails details)
    {
        super(details);
        //System.out.println(getClass()+" started");
    }

    
    @Override
    public void onEventFinished()
    {
        //System.out.println(getClass()+" ended");
        SpawnShipEventDetails d = (SpawnShipEventDetails) getEventDetails();
        if (d.getController().canSpawn(d.getShipType()))
        {
            if (d.getTimeStamp()<d.getSimulation().getClock().getTime())
            {
                System.out.println("UH OH!");
            }
            IShip s = d.getController().spawnEventShip(d.getShipType(), d.getTimeStamp());
            d.getSimulationController().addShipToPool(s);
            this.changeEventState(new CompletedEventState());
            s.registerStartWaitingTime();
        }
        else
        {
            changeEventState(new TerminatedEventState());
        }
        d.getSimulationController().scheduleShipSpawn(); //Spawn another ship after this one has appeared.
        
    }

    @Override
    public void reportResults(IReport report)
    {
        //Do nothing to the report...
    }
    
}
