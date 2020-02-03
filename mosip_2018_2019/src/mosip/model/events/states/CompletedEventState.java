/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mosip.model.events.states;

import mosip.model.interfaces.IEventState;

/**
 *
 * @author Diogo
 */
public class CompletedEventState implements IEventState
{

    @Override
    public IEventState changeState(IEventState newState)
    {
        return this;
    }
    
}
