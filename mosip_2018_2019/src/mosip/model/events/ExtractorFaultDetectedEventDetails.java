/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mosip.model.events;

import mosip.model.DockingBay;
import mosip.model.interfaces.IExtractor;
import mosip.model.interfaces.IShip;
import mosip.model.simulation.Simulation;

/**
 *
 * @author Diogo
 */
public class ExtractorFaultDetectedEventDetails extends EventDetailsBase
{

    private IExtractor ext;
    private DockingBay bay;
    private Simulation simulation;
    /**
     * Whether to fire the unloading event again or not.
     */
    private boolean refireUnloading;
    
    public ExtractorFaultDetectedEventDetails(double timeStamp,IExtractor extractor,DockingBay bay,Simulation sim,boolean refireUnloading)
    {
        super(timeStamp,sim.getClock().getStart());
        this.simulation=sim;
        this.ext=extractor;
        this.bay=bay;
        this.refireUnloading=refireUnloading;
    }
    
    public IExtractor getExtractor()
    {
        return ext;
    }

    /**
     * @return the bay
     */
    public DockingBay getBay()
    {
        return bay;
    }

    /**
     * @return the simulation
     */
    public Simulation getSimulation()
    {
        return simulation;
    }

    /**
     * @return the refireUnloading
     */
    public boolean shouldRefireUnloading()
    {
        return refireUnloading;
    }
}
