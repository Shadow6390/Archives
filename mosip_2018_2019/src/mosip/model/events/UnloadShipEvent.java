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
public class UnloadShipEvent extends SimulationEventBase
{

    public UnloadShipEvent(IEventDetails details)
    {
        super(details);
        //System.out.println(getClass()+" started");
    }

    @Override
    public void onEventFinished()
    {
        //System.out.println(getClass()+" ended");
        UnloadShipEventDetails d = (UnloadShipEventDetails) getEventDetails();
        d.getBay().loadDockedShip();
        changeEventState(new CompletedEventState());
    }

    @Override
    public void reportResults(IReport report)
    {
        LeixoesCargoReportData data = report.getReportData();
        UnloadShipEventDetails d = (UnloadShipEventDetails) getEventDetails();
        String bayName = d.getBay().getName();
        String shipName = d.getShip().getShipClassName();
        LeixoesCargoReportData.ReportBundle bundle = data.bayData.get(bayName);
        double sum = bundle.averageUnloading.getOrDefault(shipName,0.0);
        sum+=d.getClock().getEnd()-d.getClock().getStart();
        bundle.averageUnloading.put(shipName,sum);
        bundle.numberOfUnloadings.put(
                shipName,bundle.numberOfUnloadings.getOrDefault(shipName, 0)+1);
    }
}
