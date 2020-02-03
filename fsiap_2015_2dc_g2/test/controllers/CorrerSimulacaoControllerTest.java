/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import model.ListaMateriaisParede;
import model.Material;
import model.Parede;
import model.Sala;
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
public class CorrerSimulacaoControllerTest {
    
    private CorrerSimulacaoController instance;
    
    private Simulacao sim;
    private Sala sala;
    private Servidor serv;
    private Material mat;
    
    public CorrerSimulacaoControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        sim = new Simulacao();
        sala = new Sala();
        sala.setHasTemperaturaVariation(false);
        sala.setTemperaturaExternaMinima(3);
        sala.setTemperaturaExternaMinima(9);
        sala.setTemperaturaExterior(3);
        sala.setTemperaturaInterior(15);
        sala.setTemperaturaInteriorAlvo(10);
        serv = new Servidor();
        serv.setNome("XPTO");
        serv.setMassa(30);
        serv.setComprimento(1);
        serv.setLargura(1);
        serv.setAltura(2);
        serv.setCarga(30);
        mat = new Material();
        mat.setCoeficienteConducao(0.5);
        mat.setCoeficienteConveccao(0.002);
        mat.setCoeficienteRadiacao(0.07);
        mat.setNome("Material XPTO");
        mat.setDescricao("Descricao XPTO");
        mat.setImagem(new BufferedImage(1,1,BufferedImage.TYPE_INT_RGB));
        serv.setMaterial(mat);
        
        
        sala.addServidor(serv, new double[]{0.0,0.0,0.0},20);
        sala.addServidor(serv, new double[]{1.0,0.0,0.0},10);
        
        List<Parede> paredes = new ArrayList();
        Parede p  = new Parede();
        p.setPosicao(new double[]{0.0,0.0,0.0});
        p.setAltura(3);
        p.setComprimento(7);
        p.setLargura(1);
        p.setOrientacaoParede(Parede.ORIENTACAO_HORIZONTAL);
        p.setTemperatura(5);
        
        ListaMateriaisParede lm = new ListaMateriaisParede();
        lm.adicionarMaterial(mat, 1d);
        p.setListaMateriais(lm);
        
        paredes.add(p);
        
        p  = new Parede();
        p.setPosicao(new double[]{0.0,1.0,0.0});
        p.setAltura(3);
        p.setComprimento(1d);
        p.setLargura(5d);
        p.setOrientacaoParede(Parede.ORIENTACAO_VERTICAL);
        p.setTemperatura(5);
        
        lm = new ListaMateriaisParede();
        lm.adicionarMaterial(mat, 1d);
        p.setListaMateriais(lm);
        
        paredes.add(p);
        
        p  = new Parede();
        p.setPosicao(new double[]{6.0,0.0,0.0});
        p.setAltura(3);
        p.setComprimento(7);
        p.setLargura(1);
        p.setOrientacaoParede(Parede.ORIENTACAO_HORIZONTAL);
        p.setTemperatura(5);
        
        lm = new ListaMateriaisParede();
        lm.adicionarMaterial(mat, 1d);
        p.setListaMateriais(lm);
        
        paredes.add(p);
        
        p  = new Parede();
        p.setPosicao(new double[]{6.0,1.0,0.0});
        p.setAltura(3);
        p.setComprimento(1);
        p.setLargura(5);
        p.setOrientacaoParede(Parede.ORIENTACAO_VERTICAL);
        p.setTemperatura(5);
        
        lm = new ListaMateriaisParede();
        lm.adicionarMaterial(mat, 1d);
        p.setListaMateriais(lm);
        
        paredes.add(p);
        
        sala.setListaParedes(paredes);
        
        p  = new Parede();
        p.setPosicao(new double[]{0.0,0.0,2.0});
        p.setAltura(1);
        p.setComprimento(7);
        p.setLargura(7);
        p.setOrientacaoParede(Parede.ORIENTACAO_PLANA);
        p.setTemperatura(5);
        
        lm = new ListaMateriaisParede();
        lm.adicionarMaterial(mat, 1d);
        p.setListaMateriais(lm);
        p.setTemperatura(5);
        
        sala.setTeto(p);
        
        sim.setSala(sala);
        instance = new CorrerSimulacaoController(sim);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setupSimulation method, of class CorrerSimulacaoController.
     */
    @Test
    public void testSetupSimulation() {
        System.out.println("setupSimulation");
        instance.setupSimulation();
    }

    /**
     * Test of updateTemperatures method, of class CorrerSimulacaoController.
     */
    @Test
    public void testUpdateTemperatures() {
        System.out.println("updateTemperatures");
        instance.setupSimulation();
        instance.updateTemperatures();
    }

    /**
     * Test of stepTime method, of class CorrerSimulacaoController.
     */
    @Test
    public void testStepTime() {
        System.out.println("stepTime");
        double expResult = 1.0;
        double result = instance.stepTime();
        assertEquals(expResult, result, 0.0);
        result = instance.stepTime();
        expResult=2.0;
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getRefrigerationCost method, of class CorrerSimulacaoController.
     */
    @Test
    public void testGetRefrigerationCost() {
        System.out.println("getRefrigerationCost");
        double expResult = 9599;
        instance.setupSimulation();
        int i;
        
        for (i=0;i<50;i++)
        {
            //Corre durante um minuto.
            instance.setDeltaTime(60);
            instance.updateTemperatures();
            instance.updateTemperaturaExterna();
            instance.stepCPUDissipation();
            instance.stepTime();
        }
        double result = Math.round(instance.getRefrigerationCost());
        
        assertTrue("Refrigeration cost should be "+expResult+" but is "+result, expResult==result);
        for (i=0;i<450;i++)
        {
            //Corre durante um minuto.
            instance.setDeltaTime(60);
            instance.updateTemperatures();
            instance.updateTemperaturaExterna();
            instance.stepCPUDissipation();
            instance.stepTime();
        }
        expResult=50702;
        result = Math.round(instance.getRefrigerationCost());
        assertTrue("Next, the refrigeration cost should be "+expResult+" but is "+result, expResult==result);
        
        for (i=0;i<230;i++)
        {
            //Corre durante um minuto.
            instance.setDeltaTime(60);
            instance.updateTemperatures();
            instance.updateTemperaturaExterna();
            instance.stepCPUDissipation();
            instance.stepTime();
        }
        expResult=64267;
        result = Math.round(instance.getRefrigerationCost());
        assertTrue("Next, the refrigeration cost should be "+expResult+" but is "+result, expResult==result);
        
        for (i=0;i<69;i++)
        {
            //Corre durante um minuto.
            instance.setDeltaTime(60);
            instance.updateTemperatures();
            instance.updateTemperaturaExterna();
            instance.stepCPUDissipation();
            instance.stepTime();
        }
        expResult=64267;
        result = Math.round(instance.getRefrigerationCost());
        assertTrue("Next, the refrigeration cost should be "+expResult+" but is "+result, expResult==result);
        
    }

    /**
     * Test of updateTemperaturaExterna method, of class CorrerSimulacaoController.
     */
    @Test
    public void testUpdateTemperaturaExterna() {
        System.out.println("updateTemperaturaExterna");
        boolean expResult = false;
        CorrerSimulacaoController instance = new CorrerSimulacaoController(sim);
        instance.setupSimulation();
        boolean result = instance.updateTemperaturaExterna();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of recomecarSimulacao method, of class CorrerSimulacaoController.
     */
    @Test
    public void testRecomecarSimulacao() {
        System.out.println("recomecarSimulacao");
        boolean expResult = false;
        boolean result = instance.recomecarSimulacao();
        assertEquals(expResult, result);
    }

    /**
     * Test of continuarSimulacao method, of class CorrerSimulacaoController.
     */
    @Test
    public void testContinuarSimulacao() {
        System.out.println("continuarSimulacao");
        boolean expResult = false;
        boolean result = instance.continuarSimulacao();
        assertEquals(expResult, result);
        
        expResult=true;
        instance.pararSimulacao();
        result = instance.continuarSimulacao();
        assertEquals(expResult, result);
    }

    /**
     * Test of pararSimulacao method, of class CorrerSimulacaoController.
     */
    @Test
    public void testPararSimulacao() {
        System.out.println("pararSimulacao");
        boolean expResult = true;
        boolean result = instance.pararSimulacao();
        assertEquals(expResult, result);
        
        expResult=false;
        result = instance.pararSimulacao();
        assertEquals(expResult, result);
    }


    /**
     * Test of setDeltaTime method, of class CorrerSimulacaoController.
     */
    @Test
    public void testGetSetDeltaTime() {
        System.out.println("setDeltaTime");
        int deltaTime = 30;
        instance.setDeltaTime(deltaTime);
        assertEquals(30,instance.getDeltaTime());
    }

    /**
     * Test of stepCPUDissipation method, of class CorrerSimulacaoController.
     */
    @Test
    public void testStepCPUDissipation() {
        System.out.println("stepCPUDissipation");
        instance.setupSimulation();
        instance.stepCPUDissipation();
    }
    
}
