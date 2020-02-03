/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mosip.model;

import mosip.model.interfaces.IExtractor;
import mosip.model.interfaces.IRandomDistribution;

/**
 *
 * @author Diogo
 */
public abstract class BaseExtractor implements IExtractor
{
    protected RandomSeedFactory factory;
    
    protected IRandomDistribution dist;
    
    protected int randomStream;
    
    protected double chanceOfFailure;
    
    public BaseExtractor(IRandomDistribution dist,RandomSeedFactory factory, int randomStream,double chanceOfFailure)
    {
        this.dist=dist;
        this.dist.setRandomStream(factory.getRandomStream(randomStream));
        this.factory=factory;
        this.randomStream = randomStream;
        this.chanceOfFailure=chanceOfFailure;
    }
    
    protected boolean attemptFailure()
    {
        return dist.random()<chanceOfFailure;
    }
}
