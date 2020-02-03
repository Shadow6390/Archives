/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mosip.model.interfaces;

/**
* Completed - When a component has finished its simulation.
* Alive - When the component is currently doing work in the simulation.
* Terminated - When the component had to be terminated.
* Loading - When the component is loaded in memory, but is not actually "present"
* in the simulation.
 * @author Diogo
 */
public interface ISimulationComponentState
{
    public ISimulationComponentState changeState(ISimulationComponentState newState);
}
