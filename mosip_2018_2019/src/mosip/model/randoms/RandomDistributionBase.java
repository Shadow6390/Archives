/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mosip.model.randoms;

import java.util.Random;
import mosip.model.interfaces.IRandomDistribution;

/**
 *
 * @author Diogo
 */
public abstract class RandomDistributionBase implements IRandomDistribution
{
    /**
     * The random number generator.
     */
    private Random random;
    
    public RandomDistributionBase()
    {
        random = new Random();
    }
    
    @Override
    public void setRandomStream(Random random)
    {
        this.random=random;
    }
    
    /**
     * Returns the random of this distribution base.
     * @return 
     */
    protected Random getRandom()
    {
        return random;
    }
}
