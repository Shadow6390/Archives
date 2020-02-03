/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mosip.model.events;

import mosip.model.Bridge;
import mosip.model.SimulationController;
import mosip.model.simulation.Simulation;

/**
 *
 * @author Diogo
 */
public class BridgeHeightChangeEventDetails extends EventDetailsBase
{

    private Bridge bridge;
    private Simulation simulation;
    private SimulationController simulationController;
    
    public BridgeHeightChangeEventDetails(double timeStamp,Bridge bridge,Simulation sim,SimulationController c)
    {
        super(timeStamp,sim.getClock().getStart());
        this.bridge=bridge;
        this.simulation=sim;
        this.simulationController=c;
    }

    /**
     * @return the bridge
     */
    public Bridge getBridge()
    {
        return bridge;
    }
    
    public Simulation getSimulation()
    {
        return simulation;
    }

    /**
     * @return the simulationController
     */
    public SimulationController getSimulationController()
    {
        return simulationController;
    }
}
