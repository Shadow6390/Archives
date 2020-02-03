/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mosip.model.randoms;

/**
 * Implementation of a uniform random distribution.
 * In this random distribution, all values have equal probability of occuring.
 * @author Diogo
 */
public class UniformRandomDistribution extends RandomDistributionBase
{

    public UniformRandomDistribution()
    {
        super();
    }
    
    @Override
    public double randomRange(double lowerBound, double upperBound)
    {
        double diff = upperBound-lowerBound;
        return lowerBound+getRandom().nextDouble()*diff;
    }

    @Override
    public int randomRange(int lowerBound, int upperBound)
    {
        int diff = upperBound-lowerBound;
        return lowerBound+getRandom().nextInt(diff);
    }

    @Override
    public double random()
    {
        return getRandom().nextDouble();
    }    
}
