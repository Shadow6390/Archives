/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mosip.model;

import mosip.model.interfaces.IExtractor;
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
public class DockingBayIT
{
    
    public DockingBayIT()
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
     * Test of dockShip method, of class DockingBay.
     */
    @Test
    public void testDockShip()
    {
        System.out.println("dockShip");
        IShip ship = null;
        DockingBay instance = null;
        instance.dockShip(ship);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of undockShip method, of class DockingBay.
     */
    @Test
    public void testUndockShip()
    {
        System.out.println("undockShip");
        DockingBay instance = null;
        instance.undockShip();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addUndockListener method, of class DockingBay.
     */
    @Test
    public void testAddUndockListener()
    {
        System.out.println("addUndockListener");
        UndockListener l = null;
        DockingBay instance = null;
        boolean expResult = false;
        boolean result = instance.addUndockListener(l);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of canDockShip method, of class DockingBay.
     */
    @Test
    public void testCanDockShip()
    {
        System.out.println("canDockShip");
        IShip ship = null;
        DockingBay instance = null;
        boolean expResult = false;
        boolean result = instance.canDockShip(ship);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of changeBridgeHeight method, of class DockingBay.
     */
    @Test
    public void testChangeBridgeHeight()
    {
        System.out.println("changeBridgeHeight");
        double height = 0.0;
        DockingBay instance = null;
        instance.changeBridgeHeight(height);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isBridgeHeightChanging method, of class DockingBay.
     */
    @Test
    public void testIsBridgeHeightChanging()
    {
        System.out.println("isBridgeHeightChanging");
        DockingBay instance = null;
        boolean expResult = false;
        boolean result = instance.isBridgeHeightChanging();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDockingReason method, of class DockingBay.
     */
    @Test
    public void testGetDockingReason()
    {
        System.out.println("getDockingReason");
        IShip ship = null;
        DockingBay instance = null;
        DockingBay.NoDockReason expResult = null;
        DockingBay.NoDockReason result = instance.getDockingReason(ship);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getExtractorForShip method, of class DockingBay.
     */
    @Test
    public void testGetExtractorForShip()
    {
        System.out.println("getExtractorForShip");
        IShip ship = null;
        DockingBay instance = null;
        IExtractor expResult = null;
        IExtractor result = instance.getExtractorForShip(ship);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
