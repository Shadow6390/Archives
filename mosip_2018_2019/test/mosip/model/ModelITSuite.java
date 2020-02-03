/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mosip.model;

import mosip.model.randoms.RandomsITSuite;
import mosip.model.simulation.SimulationITSuite;
import mosip.model.utils.UtilsITSuite;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Diogo
 */
@RunWith(Suite.class)
@Suite.SuiteClasses(
    {ExtractorControllerIT.class, BaseControllerIT.class, DockingBayIT.class, CruiseControllerIT.class, ShipControllerIT.class, RandomsITSuite.class,SimulationControllerIT.class, SimulationConfigurationIT.class, UtilsITSuite.class, RandomSeedFactoryIT.class, DummyComponentIT.class, SimulationITSuite.class, CargoControllerIT.class, GlobalClockIT.class})
public class ModelITSuite
{

    @BeforeClass
    public static void setUpClass() throws Exception
    {
    }

    @AfterClass
    public static void tearDownClass() throws Exception
    {
    }

    @Before
    public void setUp() throws Exception
    {
    }

    @After
    public void tearDown() throws Exception
    {
    }
    
}
