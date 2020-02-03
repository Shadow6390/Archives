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
import mosip.model.simulation.Simulation;

/**
 *
 * @author Diogo
 */
public class UndockShipEvent extends SimulationEventBase
{

    private Simulation sim;
    
    public UndockShipEvent(IEventDetails details,Simulation sim)
    {
        super(details);
        this.sim=sim;
        //System.out.println(getClass()+" started");
    }

    @Override
    public void onEventFinished()
    {
        //System.out.println(getClass()+" ended");
        UndockShipEventDetails d = (UndockShipEventDetails) getEventDetails();
        d.getBay().undockShip();
        d.getSimulation().addEvent(new RequestShipDockEvent(new RequestShipDockEventDetails(getEventDetails().getTimeStamp(), d.getController(),sim)));
        changeEventState(new CompletedEventState());
    }
    
    @Override
    public void reportResults(IReport report)
    {
        LeixoesCargoReportData data = report.getReportData();
        UndockShipEventDetails d = (UndockShipEventDetails) getEventDetails();
        String bayName = d.getBay().getName();
        String shipName = d.getShip().getShipClassName();
        LeixoesCargoReportData.ReportBundle bundle = data.bayData.get(bayName);
        double sum = bundle.averageUndocking.getOrDefault(shipName,0.0);
        sum+=d.getClock().getEnd()-d.getClock().getStart();
        bundle.averageUndocking.put(shipName,sum);
        bundle.numberOfUndocks.put(
                shipName,bundle.numberOfUndocks.getOrDefault(shipName, 0)+1);
    }
}
