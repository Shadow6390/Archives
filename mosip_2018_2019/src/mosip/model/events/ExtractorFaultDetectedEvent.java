/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mosip.model.events;

import mosip.model.events.states.CompletedEventState;
import mosip.model.interfaces.IEventDetails;
import mosip.model.interfaces.IReport;
import mosip.model.reporting.LeixoesCargoReportData;

/**
 *
 * @author Diogo
 */
public class ExtractorFaultDetectedEvent extends SimulationEventBase
{

    public ExtractorFaultDetectedEvent(IEventDetails details)
    {
        super(details);
    }

    @Override
    public void onEventFinished()
    {
        System.out.println(getClass()+" ended");
        ExtractorFaultDetectedEventDetails d = (ExtractorFaultDetectedEventDetails) getEventDetails();
        d.getSimulation().addEvent(
                new ExtractorRepairEvent(
                        new ExtractorRepairEventDetails(d.getTimeStamp()+d.getExtractor().getRepairTime(), d.getExtractor(), d.getBay(), d.getSimulation(),d.shouldRefireUnloading())));
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
