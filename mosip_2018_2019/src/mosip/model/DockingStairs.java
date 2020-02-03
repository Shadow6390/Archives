/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mosip.model;

import mosip.model.interfaces.IExtractor;
import mosip.model.interfaces.IRandomDistribution;
import mosip.model.interfaces.IShip;

/**
 *
 * @author jbraga
 */
public class DockingStairs extends BaseExtractor{

    public DockingStairs(IRandomDistribution dist, RandomSeedFactory factory, int randomStream, double chanceOfFailure)
    {
        super(dist, factory, randomStream, chanceOfFailure);
    }

    @Override
    public boolean canExtract(IShip ship)
    {
        return ship instanceof EventCruiseShip;
    }

    @Override
    public void doExtraction(IShip ship, int numberExtractors)
    {
        /*ship.performUnloading(numberExtractors,ex);
        ship.performLoading(numberExtractors);*/
    }

    @Override
    public void doEventExtraction(IShip ship, int numberOfExtractors,int expectedNumberExtractors)
    {
        ship.performUnloading(numberOfExtractors,expectedNumberExtractors);
    }
    
    @Override
    public void doEventLoad(IShip ship, int numberOfExtractors,int expectedNumberExtractors)
    {
        ship.performLoading(numberOfExtractors,expectedNumberExtractors);
    }

    @Override
    public boolean doFaultEvent(boolean canFault)
    {
        if (canFault)
        {
            return attemptFailure();
        }
        else
        {
            return false;
        }
    }
    
    @Override
    public double getRepairTime()
    {
        return dist.randomRange(30, 120);
    }
}
