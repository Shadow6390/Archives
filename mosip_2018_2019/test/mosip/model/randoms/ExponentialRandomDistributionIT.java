/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mosip.model.randoms;

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
public class ExponentialRandomDistributionIT
{
    
    public ExponentialRandomDistributionIT()
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
     * Test of randomRange method, of class ExponentialRandomDistribution.
     */
    @Test
    public void testRandomRange_double_double()
    {
        System.out.println("randomRange");
        double lowerBound = 1.0;
        double upperBound = 4.0;
        ExponentialRandomDistribution instance = new ExponentialRandomDistribution(5);
        instance.setRandomStream(new Random(400));
        double expResult = 1.92;
        double result = instance.randomRange(lowerBound, upperBound);
        assertEquals(expResult, result, 0.1);
    }
    
    @Test
    public void testRandomRange_double_double_within_bounds()
    {
        System.out.println("randomRange");
        double lowerBound = 0.0;
        double upperBound = 6000.0;
        ExponentialRandomDistribution instance = new ExponentialRandomDistribution(5);
        instance.setRandomStream(new Random(500));
        double expResult = 1759.198;
        double result = instance.randomRange(lowerBound, upperBound);
        assertEquals(expResult, result, 0.001);
    }
    
    @Test
    public void testRandomRange_double_double_crescendo()
    {
        System.out.println("randomRange");
        double lowerBound = 1.0;
        double upperBound = 4.0;
        ExponentialRandomDistribution instance = new ExponentialRandomDistribution(5);
        instance.setRandomStream(new Random(500));
        double lastResult=0;
        for (int i=0;i<12;i++)
        {
            double result = instance.randomRange(lowerBound, upperBound);
            assertTrue("Result is "+result+" and last result is "+lastResult,result>lastResult);
            lastResult=result;
            lowerBound+=result+0.1;
            upperBound+=result+0.1;
        }
    }

    /**
     * Test of randomRange method, of class ExponentialRandomDistribution.
     */
    @Test
    public void testRandomRange_int_int()
    {
        System.out.println("randomRange");
        int lowerBound = 1;
        int upperBound = 4;
        ExponentialRandomDistribution instance = new ExponentialRandomDistribution(5);
        instance.setRandomStream(new Random(400));
        int expResult = 1;
        int result = instance.randomRange(lowerBound, upperBound);
        assertEquals(expResult, result);
    }

    /**
     * Test of random method, of class ExponentialRandomDistribution.
     */
    @Test
    public void testRandom()
    {
        System.out.println("random");
        ExponentialRandomDistribution instance = new ExponentialRandomDistribution(5);
        instance.setRandomStream(new Random(600));
        double expResult = 0;
        double result = instance.random();
        assertEquals(expResult, result, 0.001);
    }
    
}
