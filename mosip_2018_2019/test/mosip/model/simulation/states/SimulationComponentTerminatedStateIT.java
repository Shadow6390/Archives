/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mosip.model.simulation.states;

import mosip.model.interfaces.ISimulationComponentState;
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
public class SimulationComponentTerminatedStateIT
{
    
    public SimulationComponentTerminatedStateIT()
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
     * Test of changeState method, of class SimulationComponentTerminatedState.
     */
    @Test
    public void testChangeState()
    {
        System.out.println("changeState");
        ISimulationComponentState newState = null;
        SimulationComponentTerminatedState instance = new SimulationComponentTerminatedState();
        ISimulationComponentState expResult = null;
        ISimulationComponentState result = instance.changeState(newState);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
