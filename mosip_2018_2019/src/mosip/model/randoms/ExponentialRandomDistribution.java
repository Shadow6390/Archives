/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mosip.model.randoms;

/**
 *
 * @author Diogo
 */
public class ExponentialRandomDistribution extends RandomDistributionBase
{
    /**
     * The exponential rate.
     */
    private double lambda;
    
    public ExponentialRandomDistribution(double lambda)
    {
        super();
        this.lambda=lambda;
    }

    @Override
    public double randomRange(double lowerBound, double upperBound)
    {
        double x;
        double fakeLower=0,fakeUpper=1; //Goal: generate exponential between 0 and 1, then scale
        double u = getRandom().nextDouble();
        double ea = Math.pow(Math.E,-fakeLower*lambda);
        double eb = Math.pow(Math.E,-fakeUpper*lambda);
        double eu = Math.pow(Math.E,-lambda*(1-u));
        x = (ea-eu)/(eb-ea);
        x = 1+x;
        x = lowerBound+(upperBound-lowerBound)*x;
        return x;
    }

    @Override
    public int randomRange(int lowerBound, int upperBound)
    {
        return (int)randomRange((double)lowerBound,(double)upperBound);
    }

    @Override
    public double random()
    {
        return randomRange(0,1);
    }
}
