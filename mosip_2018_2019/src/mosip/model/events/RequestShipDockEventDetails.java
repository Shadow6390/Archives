/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mosip.model.events;

import mosip.model.SimulationController;
import mosip.model.simulation.Simulation;

/**
 *
 * @author Diogo
 */
public class RequestShipDockEventDetails extends EventDetailsBase
{
    private SimulationController simC;

    public RequestShipDockEventDetails(double timeStamp,SimulationController c,Simulation sim)
    {
        super(timeStamp,sim.getClock().getStart());
        this.simC=c;
    }
    
    public SimulationController getSimulationController()
    {
        return simC;
    }
}
