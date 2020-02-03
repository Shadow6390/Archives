/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mosip.model;

import mosip.model.interfaces.IRandomDistribution;
import mosip.model.simulation.Simulation;

/**
 *
 * @author Diogo
 */
public abstract class BaseController
{
    protected int randomStream;
    protected RandomSeedFactory factory;
    protected IRandomDistribution distribution;
    protected Simulation simulation;
    protected SimulationController parent;
    
    public BaseController(RandomSeedFactory factory,int randomStream,IRandomDistribution distribution,Simulation sim,SimulationController parent)
    {
        this.factory = factory;
        this.randomStream = randomStream;
        this.distribution=distribution;
        distribution.setRandomStream(factory.getRandomStream(randomStream));
        this.simulation=sim;
        this.parent=parent;
    }
}
