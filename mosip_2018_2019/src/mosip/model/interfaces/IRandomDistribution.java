/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mosip.model.interfaces;

import java.util.Random;

/**
 *
 * @author Diogo
 */
public interface IRandomDistribution
{
    public void setRandomStream(Random random);
    public double random();
    public double randomRange(double lowerBound,double upperBound);
    public int randomRange(int lowerBound,int upperBound);
}