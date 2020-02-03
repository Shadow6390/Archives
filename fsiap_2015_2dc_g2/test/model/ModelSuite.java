/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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
@Suite.SuiteClasses({model.CodificadorHTMLTest.class,model.GestorDadosTest.class,model.IdiomaTest.class,model.ListaMateriaisParedeTest.class,model.MaterialTest.class,model.RegistoIdiomasTest.class,model.RegistoMateriaisTest.class,model.RegistoServidoresTest.class,model.TransferenciaCalorTest.class,model.ParedeTest.class ,model.SalaTest.class, model.ServidorTest.class, model.ServidorInstanciadoTest.class, model.ListaServidoresInstanciadosTest.class, model.SimulacaoTest.class})
public class ModelSuite {

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
