/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mosip.model;

import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Diogo
 */
public class RandomSeedFactoryIT
{
    
    public RandomSeedFactoryIT()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }
    
    @Before
    public void setUp()
    {
    }
    
    @After
    public void tearDown()
    {
    }

    /**
     * Test of allocateNewRandom method, of class RandomSeedFactory.
     */
    @Test
    public void testAllocateNewRandom()
    {
        System.out.println("allocateNewRandom");
        int seed = 5;
        RandomSeedFactory instance = new RandomSeedFactory();
        int expResult = 0;
        int result = instance.allocateNewRandom(seed);
        assertEquals(expResult, result);
        
        seed=6;
        expResult = 1;
        result = instance.allocateNewRandom(seed);
        assertEquals(expResult, result);
    }

    /**
     * Test of getRandomStream method, of class RandomSeedFactory.
     */
    @Test
    public void testGetRandomStream()
    {
        System.out.println("getRandomStream");
        int seed = 500;
        int stream = 0;
        RandomSeedFactory instance = new RandomSeedFactory();
        instance.allocateNewRandom(seed);
        instance.allocateNewRandom(seed+1);
        int[] expectedResult = new int[]{-1040059906,
            523022788,
            1620136985,
            1193875645,
            -1940504925,
            659733131,
            -573698283,
            -1894865639,
            -1999915881,
            86049264};
        for (int i = 0; i < 10; i++)
        {
            assertEquals(expectedResult[i], instance.getRandomStream(0).nextInt());
        }
        assertFalse(expectedResult[0]==instance.getRandomStream(1).nextInt());
        instance.allocateNewRandom(seed);
        assertEquals(expectedResult[0],instance.getRandomStream(2).nextInt());
        assertFalse(expectedResult[0]==instance.getRandomStream(0).nextInt());
    }
    
}
