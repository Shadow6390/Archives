/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mosip.model.events;

import mosip.model.interfaces.IEventDetails;
import mosip.model.interfaces.IReport;
import mosip.model.simulation.Simulation;

/**
 *
 * @author Diogo
 */
public class BridgeHeightChangeEvent extends SimulationEventBase
{

    private Simulation sim;
    
    public BridgeHeightChangeEvent(IEventDetails details,Simulation sim)
    {
        super(details);
        //System.out.println(getClass()+" started");
        this.sim=sim;
    }

    @Override
    public void onEventFinished()
    {
        //Once the bridge has been lifted, it's necessary to request a dock event again.
        //System.out.println(getClass()+" end");
        BridgeHeightChangeEventDetails d = (BridgeHeightChangeEventDetails)getEventDetails();
        d.getBridge().finalizeHeightChange();
        d.getSimulation().addEvent(new RequestShipDockEvent(new RequestShipDockEventDetails(d.getTimeStamp(), d.getSimulationController(),sim)));
    }
    
    @Override
    public void reportResults(IReport report)
    {
        //Do nothing...
    }
}
