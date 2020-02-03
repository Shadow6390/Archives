/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author jbraga
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({controllers.ImportarDadosControllerTest.class,controllers.ExportarDadosHTMLControllerTest.class,controllers.ExportarDadosControllerTest.class,controllers.DefinirServidorControllerTest.class,controllers.AlterarTemperaturasMeioControllerTest.class,controllers.CorrerSimulacaoControllerTest.class,controllers.AdicionarServidorSalaControllerTest.class,controllers.AdicionarTipoMaterialControllerTest.class,controllers.AlterarIdiomaControllerTest.class,controllers.AlterarInformacaoServidorControllerTest.class,controllers.AlterarTemperaturasMeioControllerTest.class})
public class ControllersSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
