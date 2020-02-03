/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mosip.model;

import mosip.model.interfaces.IRandomDistribution;
import mosip.model.interfaces.IShip;
import mosip.model.interfaces.IShipController;
import mosip.model.randoms.UniformRandomDistribution;
import mosip.model.simulation.Simulation;
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
public class SimulationControllerIT
{
    
    public SimulationControllerIT()
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
     * Test of getControllerFor method, of class SimulationController.
     */
    @Test
    public void testGetControllerFor()
    {
        System.out.println("getControllerFor");
        BaseShip.ShipType type = BaseShip.ShipType.CARGO;
        Simulation sim = new Simulation();
        sim.getRandomFactory().allocateNewRandom(500);
        sim.getRandomFactory().allocateNewRandom(500);
        SimulationConfiguration config = new SimulationConfiguration(sim);
        config.distributions=new IRandomDistribution[3];
        config.distributions[0]=new UniformRandomDistribution();
        config.distributions[1]=new UniformRandomDistribution();
        config.distributions[2]=new UniformRandomDistribution();
        config.simControllerDist=new UniformRandomDistribution();
        config.bays=new SimulationConfiguration.DummyDockingBay[0];
        SimulationController instance = new SimulationController(new RandomSeedFactory(),config);
        /*IShipController expResult = new CargoController(sim.getRandomFactory(), 1,new UniformRandomDistribution());
        IShipController result = instance.getControllerFor(type);
        assertEquals(expResult.getClass(), result.getClass());*/
        fail("Not updated.");
    }
    
    @Test(expected = RuntimeException.class)
    public void testGetControllerForNonAvailableType()
    {
        System.out.println("getControllerForNonAvailableType");
        BaseShip.ShipType type = BaseShip.ShipType.UNKNOWN;
        SimulationController instance = new SimulationController(new RandomSeedFactory(),new SimulationConfiguration(null));
        IShipController result = instance.getControllerFor(type);
    }

    /**
     * Test of generateShips method, of class SimulationController.
     */
    @Test
    public void testGenerateShips()
    {
        System.out.println("generateShips");
        SimulationConfiguration config = null;
        SimulationController instance = null;
        instance.generateShips(config,0,10);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fetchMaxBridgeHeightRequirement method, of class SimulationController.
     */
    @Test
    public void testFetchMaxBridgeHeightRequirement()
    {
        System.out.println("fetchMaxBridgeHeightRequirement");
        SimulationController instance = null;
        double expResult = 0.0;
        double result = instance.fetchMaxBridgeHeightRequirement();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
