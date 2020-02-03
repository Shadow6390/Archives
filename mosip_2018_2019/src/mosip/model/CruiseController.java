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
public class CruiseController extends BaseController implements IShipController
{

    public CruiseController(RandomSeedFactory factory, int randomStream,IRandomDistribution distribution,Simulation sim,
            SimulationController parent)
    {
        super(factory, randomStream,distribution,sim,parent);
    }

    @Override
    public boolean canSpawn(BaseShip.ShipType type)
    {
        return type==BaseShip.ShipType.CRUISE;
    }

    @Override
    public IShip spawnActivityShip(BaseShip.ShipType type,double suggestedArrivalTime)
    {
        /*CruiseShip cruise = new CruiseShip(suggestedArrivalTime, 
                distribution.randomRange(10,12), 
                distribution.randomRange(10,12), 
                distribution.randomRange(10,12), 
                distribution.randomRange(4,6), 
                distribution.randomRange(15,30),
                distribution.randomRange(15, 30));
        cruise.changeState(new SimulationComponentAliveState());
        return cruise;*/
        throw new UnsupportedOperationException("Deprecated"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IShip spawnEventShip(BaseShip.ShipType type,double arrivalTime)
    {
        EventCruiseShip cargo = new EventCruiseShip(arrivalTime, 
                distribution.randomRange(3.5,7.5),
                distribution.randomRange((int)1,(int)2),
                simulation,
                factory,
                randomStream,
                distribution,
                parent);
        return cargo;
    }
    
}
