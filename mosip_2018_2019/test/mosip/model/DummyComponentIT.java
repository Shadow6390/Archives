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
public class DummyComponentIT
{
    
    public DummyComponentIT()
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
     * Test of changeValue method, of class DummyComponent.
     */
    @Test
    public void testChangeValue()
    {
        System.out.println("changeValue");
        double value = 0.0;
        DummyComponent instance = null;
        instance.changeValue(value);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValue method, of class DummyComponent.
     */
    @Test
    public void testGetValue()
    {
        System.out.println("getValue");
        DummyComponent instance = null;
        double expResult = 0.0;
        double result = instance.getValue();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of doStep method, of class DummyComponent.
     */
    @Test
    public void testDoStep()
    {
        System.out.println("doStep");
        DummyComponent instance = null;
        instance.doStep();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class DummyComponent.
     */
    @Test
    public void testToString()
    {
        System.out.println("toString");
        DummyComponent instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
