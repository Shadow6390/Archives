/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mosip.model;

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
public class GlobalClockIT
{
    
    public GlobalClockIT()
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
     * Test of tick method, of class GlobalClock.
     */
    @Test
    public void testTick()
    {
        System.out.println("tick");
        GlobalClock instance = new GlobalClock();
        instance.tick();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStart method, of class GlobalClock.
     */
    @Test
    public void testSetStart()
    {
        System.out.println("setStart");
        double start = 0.0;
        GlobalClock instance = new GlobalClock();
        instance.setStart(start);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of resetClock method, of class GlobalClock.
     */
    @Test
    public void testResetClock()
    {
        System.out.println("resetClock");
        GlobalClock instance = new GlobalClock();
        instance.resetClock();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEnd method, of class GlobalClock.
     */
    @Test
    public void testSetEnd()
    {
        System.out.println("setEnd");
        double end = 0.0;
        GlobalClock instance = new GlobalClock();
        instance.setEnd(end);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEnd method, of class GlobalClock.
     */
    @Test
    public void testGetEnd()
    {
        System.out.println("getEnd");
        GlobalClock instance = new GlobalClock();
        double expResult = 0.0;
        double result = instance.getEnd();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStart method, of class GlobalClock.
     */
    @Test
    public void testGetStart()
    {
        System.out.println("getStart");
        GlobalClock instance = new GlobalClock();
        double expResult = 0.0;
        double result = instance.getStart();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of finished method, of class GlobalClock.
     */
    @Test
    public void testFinished()
    {
        System.out.println("finished");
        GlobalClock instance = new GlobalClock();
        boolean expResult = false;
        boolean result = instance.finished();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTime method, of class GlobalClock.
     */
    @Test
    public void testGetTime()
    {
        System.out.println("getTime");
        GlobalClock instance = new GlobalClock();
        Double expResult = null;
        Double result = instance.getTime();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class GlobalClock.
     */
    @Test
    public void testToString()
    {
        System.out.println("toString");
        GlobalClock instance = new GlobalClock();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
