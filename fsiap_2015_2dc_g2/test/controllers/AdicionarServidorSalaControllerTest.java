/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.imageio.ImageIO;
import model.Material;
import model.Servidor;
import model.Simulacao;
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
public class AdicionarServidorSalaControllerTest {

    public AdicionarServidorSalaControllerTest() {
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
     * Test of getDimensoesSala method, of class
     * AdicionarServidorSalaController.
     */
    @Test
    public void testGetDimensoesSala() {
        System.out.println("getDimensoesSala");
        Simulacao simulacao = new Simulacao();
        AdicionarServidorSalaController instance = new AdicionarServidorSalaController(simulacao);
        double[] expResult = {5.0,5.0,3.0};
        double[] result = instance.getDimensoesSala();
        String expResultS = Arrays.toString(expResult);
        String resultS = Arrays.toString(result);
        assertEquals(expResultS, resultS);
    }

    /**
     * Test of getListaServidores method, of class
     * AdicionarServidorSalaController.
     */
    @Test
    public void testGetListaServidores() {
        System.out.println("getListaServidores");
        Simulacao simulacao = new Simulacao();

        AdicionarServidorSalaController instance = new AdicionarServidorSalaController(simulacao);
        List<String> expResult = new ArrayList<>();
        List<String> result = instance.getListaServidores();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of adicionarServidor method, of class
     * AdicionarServidorSalaController.
     */
    @Test
    public void testAdicionarServidor() {
        System.out.println("adicionarServidor");
        String idServidor = "ServTeste";

        Simulacao simulacao = new Simulacao();
        Servidor serv = new Servidor();
        serv.setNome("ServTeste");
        Material mat = new Material("nome", "descricao", 20.0, 20.0, 20.0, new BufferedImage(1,1,BufferedImage.TYPE_INT_ARGB));
        serv.setCarga(50);
        serv.setMassa(5);
        serv.setMaterial(mat);
        serv.setComprimento(1);
        serv.setLargura(1);
        serv.setAltura(1);
        simulacao.getRegistoServidores().adicionarServidor(serv);
        AdicionarServidorSalaController instance = new AdicionarServidorSalaController(simulacao);
        double[] pos = {1.0,1.0,1.0};
        boolean expResult = true;
        boolean result = instance.adicionarServidor(idServidor, pos, 20);
        assertEquals(expResult, result);
        
        expResult=false;
        result=instance.adicionarServidor(idServidor,pos,20);
        assertTrue("Servers cannot overlap!",expResult==result);
    }

    /**
     * Test of getServidorCarga method, of class
     * AdicionarServidorSalaController.
     */
    @Test
    public void testGetServidorCarga() {
        System.out.println("getServidorCarga");
        String idServidor = "ServTeste";

        Simulacao simulacao = new Simulacao();
        Servidor serv = new Servidor();
        serv.setNome("ServTeste");
        Material mat = new Material("nome", "descricao", 20.0, 20.0, 20.0, new BufferedImage(1,1,BufferedImage.TYPE_INT_ARGB));
        serv.setCarga(50);
        serv.setMassa(5);
        serv.setMaterial(mat);
        serv.setComprimento(1);
        serv.setLargura(1);
        serv.setAltura(1);
        simulacao.getRegistoServidores().adicionarServidor(serv);
        AdicionarServidorSalaController instance = new AdicionarServidorSalaController(simulacao);
        String expResult = "50.0";
        String result = instance.getServidorCarga(idServidor);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getServidorMassa method, of class
     * AdicionarServidorSalaController.
     */
    @Test
    public void testGetServidorMassa() {
        System.out.println("getServidorMassa");
        String idServidor = "ServTeste";

        Simulacao simulacao = new Simulacao();
        Servidor serv = new Servidor();
        serv.setNome("ServTeste");
        Material mat = new Material("nome", "descricao", 20.0, 20.0, 20.0, new BufferedImage(1,1,BufferedImage.TYPE_INT_ARGB));
        serv.setCarga(50);
        serv.setMassa(5);
        serv.setMaterial(mat);
        serv.setComprimento(1);
        serv.setLargura(1);
        serv.setAltura(1);
        simulacao.getRegistoServidores().adicionarServidor(serv);
        AdicionarServidorSalaController instance = new AdicionarServidorSalaController(simulacao);
        String expResult = "5.0";
        String result = instance.getServidorMassa(idServidor);
        assertEquals(expResult, result);
    }

    /**
     * Test of getServidorDimensoes method, of class
     * AdicionarServidorSalaController.
     */
    @Test
    public void testGetServidorDimensoes() {
        System.out.println("getServidorDimensoes");
        String idServidor = "ServTeste";

        Simulacao simulacao = new Simulacao();
        Servidor serv = new Servidor();
        serv.setNome("ServTeste");
        Material mat = new Material("nome", "descricao", 20.0, 20.0, 20.0, new BufferedImage(1,1,BufferedImage.TYPE_INT_ARGB));
        serv.setCarga(50);
        serv.setMassa(5);
        serv.setMaterial(mat);
        serv.setComprimento(3);
        serv.setLargura(2);
        serv.setAltura(1);
        simulacao.getRegistoServidores().adicionarServidor(serv);
        AdicionarServidorSalaController instance = new AdicionarServidorSalaController(simulacao);
        double[] expResult = {3.0,2.0,1.0};
        double[] result = instance.getServidorDimensoes(idServidor);
        String expResultS = Arrays.toString(expResult);
        String resultS = Arrays.toString(result);
        assertEquals(expResultS, resultS);
    }

    /**
     * Test of getServidorImage method, of class
     * AdicionarServidorSalaController.
     */
    @Test
    public void testGetServidorImage() throws IOException{
        System.out.println("getServidorImage");
        String idServidor = "ServTeste";

        Simulacao simulacao = new Simulacao();
        Servidor serv = new Servidor();
        serv.setNome("ServTeste");
        BufferedImage buff = ImageIO.read(new File("imagemTeste.jpg"));
        Material mat = new Material("nome", "descricao", 20.0, 20.0, 20.0, buff);
        serv.setCarga(50);
        serv.setMassa(5);
        serv.setMaterial(mat);
        serv.setComprimento(3);
        serv.setLargura(2);
        serv.setAltura(1);
        simulacao.getRegistoServidores().adicionarServidor(serv);
        AdicionarServidorSalaController instance = new AdicionarServidorSalaController(simulacao);
        BufferedImage expResult = buff;
        BufferedImage result = instance.getServidorImage(idServidor);
        assertEquals(expResult, result);
    }

}
