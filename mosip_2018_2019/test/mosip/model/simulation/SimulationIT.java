/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mosip.model.simulation;

import mosip.model.GlobalClock;
import mosip.model.RandomSeedFactory;
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
public class SimulationIT
{
    
    public SimulationIT()
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
     * Test of setupClock method, of class Simulation.
     */
    @Test
    public void testSetupClock()
    {
        System.out.println("setupClock");
        double start = 0.0;
        double end = 0.0;
        Simulation instance = new Simulation();
        instance.setupClock(start, end);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of run method, of class Simulation.
     */
    @Test
    public void testRun()
    {
        System.out.println("run");
        Simulation instance = new Simulation();
        instance.runActivityBased();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addComponent method, of class Simulation.
     */
    @Test
    public void testAddComponent()
    {
        System.out.println("addComponent");
        SimulationComponent component = null;
        Simulation instance = new Simulation();
        boolean expResult = false;
        boolean result = instance.addComponent(component);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getClock method, of class Simulation.
     */
    @Test
    public void testGetClock()
    {
        System.out.println("getClock");
        Simulation instance = new Simulation();
        GlobalClock expResult = null;
        GlobalClock result = instance.getClock();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRandomFactory method, of class Simulation.
     */
    @Test
    public void testGetRandomFactory()
    {
        System.out.println("getRandomFactory");
        Simulation instance = new Simulation();
        RandomSeedFactory expResult = null;
        RandomSeedFactory result = instance.getRandomFactory();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
