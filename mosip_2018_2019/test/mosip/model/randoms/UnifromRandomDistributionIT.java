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
public class UnifromRandomDistributionIT
{
    
    public UnifromRandomDistributionIT()
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
     * Test of randomRange method, of class UnifromRandomDistribution.
     */
    @Test
    public void testRandomRange_double_double()
    {
        System.out.println("randomRange");
        double lowerBound = 6748.556869217802;
        double upperBound = 30000.0;
        upperBound-=(upperBound-lowerBound)*0.7;
        UniformRandomDistribution instance = new UniformRandomDistribution();
        instance.setRandomStream(new Random(300));
        for (int i=0;i<99999999;i++)
        {
            double result = instance.randomRange(lowerBound, upperBound);
            assertTrue("Cannot generate values below lower bound!",result>=lowerBound);
        }
    }

    /**
     * Test of randomRange method, of class UnifromRandomDistribution.
     */
    @Test
    public void testRandomRange_int_int()
    {
        System.out.println("randomRange");
        int lowerBound = 0;
        int upperBound = 0;
        UniformRandomDistribution instance = new UniformRandomDistribution();
        int expResult = 0;
        int result = instance.randomRange(lowerBound, upperBound);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of random method, of class UnifromRandomDistribution.
     */
    @Test
    public void testRandom()
    {
        System.out.println("random");
        UniformRandomDistribution instance = new UniformRandomDistribution();
        double expResult = 0.0;
        double result = instance.random();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
