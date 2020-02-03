/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mosip.model;

import mosip.model.interfaces.IRandomDistribution;
import mosip.model.interfaces.IShip;
import mosip.model.interfaces.IShipController;
import mosip.model.simulation.Simulation;
import mosip.model.simulation.states.SimulationComponentAliveState;

/**
 *
 * @author Diogo
 */
public class CargoController extends BaseController implements IShipController
{
    
    public CargoController(RandomSeedFactory factory, int randomStream,IRandomDistribution distribution,Simulation sim,
            SimulationController parent)
    {
        super(factory, randomStream, distribution,sim,parent);
    }

    @Override
    public boolean canSpawn(BaseShip.ShipType type)
    {
        return type==BaseShip.ShipType.CARGO;
    }

    @Override
    public IShip spawnActivityShip(BaseShip.ShipType type,double suggestedArrivalTime)
    {
        /*CargoShip cargo = new CargoShip(suggestedArrivalTime, 
                distribution.randomRange(10,12), 
                distribution.randomRange(10,12), 
                distribution.randomRange(10,12), 
                distribution.randomRange(4,6), 
                distribution.randomRange(10,12),
                distribution.randomRange(1, 2));
        cargo.changeState(new SimulationComponentAliveState());
        return cargo;*/
        throw new UnsupportedOperationException("Deprecated."); 
    }
    
    @Override
    public IShip spawnEventShip(BaseShip.ShipType type,double arrivalTime)
    {
        EventCargoShip cargo = new EventCargoShip(arrivalTime, 
                distribution.randomRange(4.0,8.0),
                distribution.randomRange((int)1,(int)4),
                simulation,
                factory,
                randomStream,
                distribution,
                parent);
        return cargo;
    }
}
