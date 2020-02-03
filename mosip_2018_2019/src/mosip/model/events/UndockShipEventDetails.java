/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mosip.model.events;

import mosip.model.DockingBay;
import mosip.model.SimulationController;
import mosip.model.interfaces.IShip;
import mosip.model.simulation.Simulation;

/**
 *
 * @author Diogo
 */
public class UndockShipEventDetails extends EventDetailsBase
{
    private IShip ship;

    private DockingBay bay;
    
    private SimulationController controller;
    
    private Simulation simulation;
    
    public UndockShipEventDetails(double timeStamp,IShip ship,DockingBay bay,SimulationController c,Simulation sim)
    {
        super(timeStamp,sim.getClock().getStart());
        this.ship=ship;
        this.bay=bay;
        this.controller=c;
        this.simulation=sim;
    }

    /**
     * @return the ship
     */
    public IShip getShip()
    {
        return ship;
    }

    /**
     * @return the bay
     */
    public DockingBay getBay()
    {
        return bay;
    }

    /**
     * @return the controller
     */
    public SimulationController getController()
    {
        return controller;
    }

    /**
     * @return the simulation
     */
    public Simulation getSimulation()
    {
        return simulation;
    }
    
}
