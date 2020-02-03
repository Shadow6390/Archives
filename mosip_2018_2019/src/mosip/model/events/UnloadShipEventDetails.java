/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mosip.model.events;

import mosip.model.DockingBay;
import mosip.model.interfaces.IShip;
import mosip.model.simulation.Simulation;

/**
 *
 * @author Diogo
 */
public class UnloadShipEventDetails extends EventDetailsBase
{
    private IShip ship;

    private DockingBay bay;
    
    public UnloadShipEventDetails(double timeStamp,IShip ship,DockingBay bay,Simulation sim)
    {
        super(timeStamp,sim.getClock().getStart());
        this.ship=ship;
        this.bay=bay;
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
    
}
