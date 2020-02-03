/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mosip.model;

import mosip.model.events.BridgeHeightChangeEvent;
import mosip.model.events.BridgeHeightChangeEventDetails;
import mosip.model.interfaces.IShip;
import mosip.model.simulation.Simulation;

/**
 *
 * @author jbraga
 */
public class Bridge{
    private double height;
    /**
     * Target height the bridge should be at when changing.
     */
    private double targetHeight; 
    /**
     * Absoulte value of the bridge's speed.
     */
    private double speed;
    
    private boolean heightChanging=false;
        
    public Bridge(double initialHeight,double speed)
    {
        this.height=initialHeight;
        this.speed=speed;
    }
    
    public boolean isHeightChanging()
    {
        return heightChanging;
    }

    public void changeHeight(double value,Simulation sim,SimulationController c){
        if (!heightChanging)
        {
            if (value!=height)
            {
                heightChanging=true;
                targetHeight=value;
                double time = sim.getClock().getTime()+(Math.abs(targetHeight-height)/speed);
                sim.addEvent(new BridgeHeightChangeEvent(new BridgeHeightChangeEventDetails(time,this,sim,c),sim));
            }
        }
    }
    
    public boolean hasHeightFor(IShip ship)
    {
        return height>=ship.getBridgeHeightLimit();
    }

    public void finalizeHeightChange()
    {
        height = targetHeight;
        heightChanging=false;
    }
}
