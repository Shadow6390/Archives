/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mosip.model.simulation.states;

import mosip.model.interfaces.ISimulationComponentState;

/**
 *
 * @author Diogo
 */
public class SimulationComponentCompletedState implements ISimulationComponentState
{

    @Override
    public ISimulationComponentState changeState(ISimulationComponentState newState)
    {
        return this;
    }
    
}
