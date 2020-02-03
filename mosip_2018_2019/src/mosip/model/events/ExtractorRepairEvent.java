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
public class ExtractorRepairEvent extends SimulationEventBase
{

    public ExtractorRepairEvent(IEventDetails details)
    {
        super(details);
    }

    @Override
    public void onEventFinished()
    {
        ExtractorRepairEventDetails d = (ExtractorRepairEventDetails) getEventDetails();
        d.getBay().repairExtractor(d.getExtractor());
        if (d.shouldRefireUnloading())
        {
            d.getBay().extractFromDockedShip(); //Fires unloading event again.
        }
        changeEventState(new CompletedEventState());
    }

    @Override
    public void reportResults(IReport report)
    {
        /*
        Do nothing
        */
    }
    
}
