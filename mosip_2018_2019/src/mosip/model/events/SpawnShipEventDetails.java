/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mosip.model.events;

import mosip.model.BaseShip;
import mosip.model.SimulationController;
import mosip.model.interfaces.IShipController;
import mosip.model.simulation.Simulation;

/**
 *
 * @author Diogo
 */
public class SpawnShipEventDetails extends EventDetailsBase
{

    private IShipController controller;
    private BaseShip.ShipType type;
    private SimulationController simController;
    private Simulation simulation;
    
    public SpawnShipEventDetails(double timeStamp,IShipController c,BaseShip.ShipType t, SimulationController simC,
            Simulation sim)
    {
        super(timeStamp,sim.getClock().getStart());
        this.controller=c;
        this.type=t;
        this.simController=simC;
        this.simulation=sim;
    }
    
    public IShipController getController()
    {
        return controller;
    }

    public BaseShip.ShipType getShipType()
    {
        return type;
    }
    
    public SimulationController getSimulationController()
    {
        return simController;
    }

    /**
     * @return the simulation
     */
    public Simulation getSimulation()
    {
        return simulation;
    }
}
