/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mosip.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Diogo
 */
public class RandomSeedFactory
{

    private List<Random> randoms;
    
    public RandomSeedFactory()
    {
        randoms = new ArrayList();
    }
    
    public int allocateNewRandom(int seed)
    {
        randoms.add(new Random(seed));
        return randoms.size()-1;
    }
    
    public Random getRandomStream(int stream)
    {
        return randoms.get(stream);
    }
}
