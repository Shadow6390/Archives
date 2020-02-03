/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mosip.model;

import mosip.model.simulation.Simulation;
import mosip.model.simulation.SimulationComponent;
import java.util.ArrayList;
import java.util.List;
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
     * Test of run method, of class Simulation.
     */
    @Test
    public void testRunWithinTimeFrame()
    {
        
        for (int i = 0; i < 10; i++)
        {
            double u = new Random().nextDouble()*0.5+0.5;
            double x = Math.log(1-u)/(-0.1);
            System.out.println(x);
        }
        List<SimulationComponent> cs = new ArrayList();
        DummyComponent dc = new DummyComponent(0.1);
        cs.add(dc);
        dc.changeValue(0.7);
        Simulation sim = new Simulation(cs);
        sim.setupClock(2, 12);
        sim.runActivityBased();
        assertEquals(0.7,dc.getValue(),0.0001);
    }
    
    /**
     * Test of run method, of class Simulation.
     */
    @Test
    public void testRunOutOfTimeFrame()
    {
        List<SimulationComponent> cs = new ArrayList();
        DummyComponent dc = new DummyComponent(0.1);
        cs.add(dc);
        dc.changeValue(0.7);
        Simulation sim = new Simulation(cs);
        sim.setupClock(2, 8);
        sim.runActivityBased();
        assertEquals(0.6,dc.getValue(),0.0001);
    }
}
