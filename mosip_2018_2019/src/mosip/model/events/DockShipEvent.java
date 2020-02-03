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
public class DockShipEvent extends SimulationEventBase
{

    public DockShipEvent(IEventDetails details)
    {
        super(details);
        DockShipEventDetails d = (DockShipEventDetails) getEventDetails();
        d.getShip().registerEndWaitingTime();
        //System.out.println(getClass()+" started");
    }

    @Override
    public void onEventFinished()
    {
        //System.out.println(getClass()+" ended");
        //When executed, it means the ship is already docked and the next step should be performed.
        DockShipEventDetails d = (DockShipEventDetails) getEventDetails();
        d.getBay().extractFromDockedShip();
        d.getBay().addWaitingTime(d.getShip().getWaitingTime());
        changeEventState(new CompletedEventState());
    }

    @Override
    public void reportResults(IReport report)
    {
        LeixoesCargoReportData data = report.getReportData();
        DockShipEventDetails d = (DockShipEventDetails) getEventDetails();
        String bayName = d.getBay().getName();
        String shipName = d.getShip().getShipClassName();
        LeixoesCargoReportData.ReportBundle bundle = data.bayData.get(bayName);
        double sum = bundle.averageDocking.getOrDefault(shipName,0.0);
        sum+=d.getClock().getEnd()-d.getClock().getStart();
        bundle.averageDocking.put(shipName,sum);
        bundle.numberOfDocks.put(
                shipName,bundle.numberOfDocks.getOrDefault(shipName, 0)+1);
    }
    
}
