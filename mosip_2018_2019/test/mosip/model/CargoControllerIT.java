/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mosip.model;

import mosip.model.interfaces.IShip;
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
public class CargoControllerIT
{
    
    public CargoControllerIT()
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
     * Test of canSpawn method, of class CargoController.
     */
    @Test
    public void testCanSpawn()
    {
        System.out.println("canSpawn");
        BaseShip.ShipType type = null;
        CargoController instance = null;
        boolean expResult = false;
        boolean result = instance.canSpawn(type);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of spawnShip method, of class CargoController.
     */
    @Test
    public void testSpawnShip()
    {
        System.out.println("spawnShip");
        BaseShip.ShipType type = null;
        double suggestedArrivalTime = 0.0;
        CargoController instance = null;
        IShip expResult = null;
        IShip result = instance.spawnActivityShip(type, suggestedArrivalTime);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
