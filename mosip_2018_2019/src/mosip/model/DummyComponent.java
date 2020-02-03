/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mosip.model;

import mosip.model.simulation.SimulationComponent;

/**
 *
 * @author jbraga
 */
public class DummyComponent extends SimulationComponent{
    
    private double value;
    private double targetValue;
    private double speed;
    
    public DummyComponent(double speed)
    {
        value=0.0;
        this.speed=speed;
    }
    
    public void changeValue(double value){
        if (acquireLock(0)) //If no action has been made for the specific index.
        {
            targetValue = value;
        }
    }
    
    public double getValue()
    {
        return value;
    }

    @Override
    public void doStep() {
        if (hasLock(0))
        {
            value+=speed;
            if (targetValue<=value)
            {
                value=targetValue;
                releaseLock(0);
            }
        }
    }
    @Override
    public String toString()
    {
        return "DummyComponent[val="+value+"]";
    }
}
