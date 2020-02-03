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
public class CreatedEventState implements IEventState
{

    @Override
    public IEventState changeState(IEventState newState)
    {
        if (newState instanceof CompletedEventState || newState instanceof TerminatedEventState)
        {
            return newState;
        }
        return this;
    }
    
}
