/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jbraga
 */
public class ListaServidoresInstanciadosTest {

    public ListaServidoresInstanciadosTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of adicionaInstanciasA method, of class ListaServidoresInstanciados.
     */
    @Test
    public void testAdicionaInstanciasA() {
        System.out.println("adicionaInstanciasA");
        MapaTermicoData mtd = new MapaTermicoData();
        ListaServidoresInstanciados instance = new ListaServidoresInstanciados();
        instance.adicionaInstanciasA(mtd);
    }

    /**
     * Test of addServidor method, of class ListaServidoresInstanciados.
     */
    @Test
    public void testAddServidor() {
        System.out.println("addServidor");
        try {
            BufferedImage imagem = ImageIO.read(new File("imagemTeste.jpg"));
            Material m1 = new Material("material 1", "isto é uma descrição do material ", 3.5, 3.5, 3.5, imagem);

            Servidor servidor = new Servidor("nome", m1, 3, 4, 5, 6, 7);
            double[] pos = new double[3];
            pos[0] = 2.0;
            pos[1] = 3.9;
            pos[2] = 0;
            ListaServidoresInstanciados instance = new ListaServidoresInstanciados();
            assertTrue("Deveria ter sido adicionado", instance.addServidor(servidor, pos,20));
        } catch (IOException ex) {
            Logger.getLogger(MaterialTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

}
