/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
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
public class TransferenciaCalorTest {
    
    private Simulacao sim;
    private TransferenciaCalor instance;
    private ServidorInstanciado si,si2;
    private Sala sala;
    private Servidor serv;
    private Material mat;
    
    public TransferenciaCalorTest() {
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
        instance = new TransferenciaCalor();
        sala = new Sala();
        sala.setTemperaturaInterior(5);
        instance.setSala(sala);
        ArrayList<ObjetoCalor> list = new ArrayList();
        serv = new Servidor();
        serv.setNome("XPTO");
        serv.setMassa(30);
        serv.setComprimento(1);
        serv.setLargura(1);
        serv.setAltura(2);
        serv.setCarga(30);
        mat = new Material();
        mat.setCoeficienteConducao(0.5);
        mat.setCoeficienteConveccao(0.2);
        mat.setCoeficienteRadiacao(0.7);
        mat.setNome("Material XPTO");
        mat.setDescricao("Descricao XPTO");
        mat.setImagem(new BufferedImage(1,1,BufferedImage.TYPE_INT_RGB));
        serv.setMaterial(mat);
        
        si = new ServidorInstanciado(serv);
        si2 = new ServidorInstanciado(serv);
        
        si.setTemperatura(20);
        si2.setTemperatura(10);
        si.setPosicao(new double[]{0.0,0.0,0.0});
        si2.setPosicao(new double[]{1.0,0.0,0.0});
        
        list.add(si);
        list.add(si2);
        instance.setListaCondutores(list);
        instance.conductHeat();
        sim.setSala(sala);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of conductHeat method, of class TransferenciaCalor.
     */
    @Test
    public void testConductHeat() {
        System.out.println("conductHeat");
        
        double siExpTemp = 19.9998;
        double si2ExpTemp = 10.0002;
        double resultSiTemp = Math.round(si.getTemperatura()*Math.pow(10, 4))*Math.pow(10, -4);
        double resultSi2Temp = Math.round(si2.getTemperatura()*Math.pow(10, 4))*Math.pow(10, -4);
        assertTrue("SI temperatura should be "+siExpTemp+" but was "+resultSiTemp,siExpTemp==resultSiTemp);
        assertTrue("SI2 temperatura should be "+si2ExpTemp+" but was "+resultSi2Temp,String.format("%.3f",si2ExpTemp).equals(String.format("%.3f",resultSi2Temp)));
    }

    /**
     * Test of convectHeat method, of class TransferenciaCalor.
     */
    @Test
    public void testConvectHeat() {
        System.out.println("convectHeat");
        
        TransferenciaCalor instance = new TransferenciaCalor();
        ServidorInstanciado si = new ServidorInstanciado(serv);
        ServidorInstanciado si2 = new ServidorInstanciado(serv);
        si.setTemperatura(20);
        si2.setTemperatura(20);
        si.setPosicao(new double[]{0.0,0.0,0.0});
        si2.setPosicao(new double[]{1.0,0.0,0.0});
        
        ArrayList<ObjetoCalor> arr = new ArrayList();
        arr.add(si);
        arr.add(si2);
        instance.setListaCondutores(arr);
//        sala = new Sala();
//        sala.setTemperaturaInterior(15);
//        sala.setTemperaturaExterior(3);
//        sala.setComprimento(7);
//        sala.setLargura(7);
//        sala.setAltura(3);
//        
//        instance.setSala(sala);
//        List<Parede> paredes = new ArrayList();
//        Parede p  = new Parede();
//        p.setPosicao(new double[]{0.0,0.0,0.0});
//        p.setAltura(3);
//        p.setComprimento(7);
//        p.setLargura(1);
//        p.setOrientacaoParede(Parede.ORIENTACAO_HORIZONTAL);
//        
//        ListaMateriaisParede lm = new ListaMateriaisParede();
//        lm.adicionarMaterial(mat, 1d);
//        p.setListaMateriais(lm);
//        
//        paredes.add(p);
//        
//        p  = new Parede();
//        p.setPosicao(new double[]{0.0,1.0,0.0});
//        p.setAltura(3);
//        p.setComprimento(1d);
//        p.setLargura(5d);
//        p.setOrientacaoParede(Parede.ORIENTACAO_VERTICAL);
//        
//        lm = new ListaMateriaisParede();
//        lm.adicionarMaterial(mat, 1d);
//        p.setListaMateriais(lm);
//        
//        paredes.add(p);
//        
//        p  = new Parede();
//        p.setPosicao(new double[]{6.0,0.0,0.0});
//        p.setAltura(3);
//        p.setComprimento(7);
//        p.setLargura(1);
//        p.setOrientacaoParede(Parede.ORIENTACAO_HORIZONTAL);
//        
//        lm = new ListaMateriaisParede();
//        lm.adicionarMaterial(mat, 1d);
//        p.setListaMateriais(lm);
//        
//        paredes.add(p);
//        
//        p  = new Parede();
//        p.setPosicao(new double[]{6.0,1.0,0.0});
//        p.setAltura(3);
//        p.setComprimento(1);
//        p.setLargura(5);
//        p.setOrientacaoParede(Parede.ORIENTACAO_VERTICAL);
//        
//        lm = new ListaMateriaisParede();
//        lm.adicionarMaterial(mat, 1d);
//        p.setListaMateriais(lm);
//        
//        paredes.add(p);
//        
//        sala.setListaParedes(paredes);
//        
//        p  = new Parede();
//        p.setPosicao(new double[]{0.0,0.0,2.0});
//        p.setAltura(1);
//        p.setComprimento(7);
//        p.setLargura(7);
//        p.setOrientacaoParede(Parede.ORIENTACAO_PLANA);
//        
//        lm = new ListaMateriaisParede();
//        lm.adicionarMaterial(mat, 1d);
//        p.setListaMateriais(lm);
//        p.setTemperatura(5);
//        
//        sala.setTeto(p);

        sala = new Simulacao().getSala();
        instance.setSala(sala);
        instance.convectHeat();
        
        double expSiTemp = 19.9999;
        double expSi2Temp = 19.9999;
        double expSalaIntTemp = 9.99;
        double expTetoTemp = 5.0099;
        double siTemp=Math.round(si.getTemperatura()*Math.pow(10, 4))*Math.pow(10, -4);
        double si2Temp=Math.round(si2.getTemperatura()*Math.pow(10, 4))*Math.pow(10, -4);
        double salaIntTemp=Math.round(sala.getTemperatura()*Math.pow(10, 2))*Math.pow(10, -2);
        double tetoTemp=Math.round(sala.getTeto().getTemperatura()*Math.pow(10, 4))*Math.pow(10, -4);
        assertTrue("SI temperatura should be "+expSiTemp+" but got "+siTemp,expSiTemp==siTemp);
        assertTrue("SI2 temperatura should be "+expSi2Temp+" but got "+si2Temp,expSi2Temp==si2Temp);
        assertTrue("Room temperatura should be "+expSalaIntTemp+" but got "+salaIntTemp,expSalaIntTemp==salaIntTemp);
        assertTrue("Ceiling temperatura should be "+expTetoTemp+" but got "+tetoTemp,expTetoTemp==tetoTemp);
    }

    /**
     * Test of radiateHeat method, of class TransferenciaCalor.
     */
    @Test
    public void testRadiateHeat() {
        System.out.println("radiateHeat");
        
        TransferenciaCalor instance = new TransferenciaCalor();
        ServidorInstanciado si = new ServidorInstanciado(serv);
        si.setTemperatura(20);
        si.setPosicao(new double[]{0.0,0.0,0.0});
        ArrayList<ObjetoCalor> arr = new ArrayList();
        arr.add(si);
        instance.setListaCondutores(arr);
        instance.setSala(sala);
        
        instance.radiateHeat();
        double siExpResult = 19.997;
        double siResult = Math.round(si.getTemperatura()*Math.pow(10, 3))*Math.pow(10, -3);
        assertTrue("SI temperatura should be "+siExpResult+" but was "+siResult,siExpResult==siResult);
    }

    /**
     * Test of setSala method, of class TransferenciaCalor.
     */
    @Test
    public void testSetSala() {
        System.out.println("setSala");
        Sala sala = new Sala();
        TransferenciaCalor instance = new TransferenciaCalor();
        instance.setSala(sala);
    }
    
    /**
     * Test of setSala method, of class TransferenciaCalor.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetSalaNull() {
        System.out.println("setSalaNull");
        Sala sala = null;
        TransferenciaCalor instance = new TransferenciaCalor();
        instance.setSala(sala);
    }

    /**
     * Test of setListaCondutores method, of class TransferenciaCalor.
     */
    @Test
    public void testSetListaCondutores() {
        System.out.println("setListaCondutores");
        ArrayList<ObjetoCalor> list = new ArrayList();
        list.add(new ServidorInstanciado());
        list.add(new ServidorInstanciado());
        TransferenciaCalor instance = new TransferenciaCalor();
        boolean expResult = true;
        boolean result = instance.setListaCondutores(list);
        assertEquals(expResult, result);
    }

    /**
     * Test of getRefrigerationCost method, of class TransferenciaCalor.
     */
    @Test
    public void testGetRefrigerationCost() {
        System.out.println("getRefrigerationCost");
        TransferenciaCalor instance = new TransferenciaCalor();
        Sala sala = new Sala();
        int tempAlvo=20;
        int tempInterior=35;
        sala.setTemperaturaInteriorAlvo(tempAlvo);
        sala.setTemperaturaInterior(tempInterior);
        instance.setSala(sala);
        double expResult = TransferenciaCalor.celsiusToJoule(tempInterior-tempAlvo);
        double result = instance.getRefrigerationCost();
        assertEquals(expResult, result, 0.0);
        
        tempAlvo=35;
        tempInterior=20;
        sala.setTemperaturaInteriorAlvo(tempAlvo);
        sala.setTemperaturaInterior(tempInterior);
        
        expResult=0;
        result=instance.getRefrigerationCost();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of updateTemperaturaExterna method, of class TransferenciaCalor.
     * In this case, the external temperature is not allowed to change.
     */
    @Test
    public void testUpdateTemperaturaExternaNonVariant() {
        System.out.println("updateTemperaturaExternaNonVariant");
        Sala sala = new Sala();
        sala.setHasTemperaturaVariation(false);
        sala.setTemperaturaExterior(25);
        sala.setTemperaturaExternaMaxima(20);
        sala.setTemperaturaExternaMinima(10);
        double timeQuantum = 50;
        TransferenciaCalor instance = new TransferenciaCalor();
        instance.setSala(sala);
        boolean expResult = false;
        boolean result = instance.updateTemperaturaExterna(timeQuantum);
        assertEquals(expResult, result);
        double expTemperatura=25;
        double temperatura = Math.round(sala.getTemperaturaExterior());
        assertEquals(expTemperatura,temperatura,0.0);
    }
    
    /**
     * Test of updateTemperaturaExterna method, of class TransferenciaCalor.
     */
    @Test
    public void testUpdateTemperaturaExterna() {
        System.out.println("updateTemperaturaExterna");
        Sala sala = new Sala();
        sala.setHasTemperaturaVariation(true);
        sala.setTemperaturaExternaMaxima(20);
        sala.setTemperaturaExternaMinima(10);
        double timeQuantum = 50;
        TransferenciaCalor instance = new TransferenciaCalor();
        instance.setSala(sala);
        boolean expResult = true;
        boolean result = instance.updateTemperaturaExterna(timeQuantum);
        assertEquals(expResult, result);
        double expTemperatura=16;
        double temperatura = Math.round(sala.getTemperaturaExterior());
        assertEquals(expTemperatura,temperatura,0.0);
    }

    /**
     * Test of celsiusToJoule method, of class TransferenciaCalor.
     */
    @Test
    public void testCelsiusToJoule() {
        System.out.println("celsiusToJoule");
        double celsius = 0.00053;
        double expResult = 1;
        double result = TransferenciaCalor.celsiusToJoule(celsius);
        assertEquals(expResult, result, 0.0);
        celsius = 20;
        expResult=37736;
        result = Math.round(TransferenciaCalor.celsiusToJoule(celsius));
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of jouleToCelsius method, of class TransferenciaCalor.
     */
    @Test
    public void testJouleToCelsius() {
        System.out.println("jouleToCelsius");
        double joule = 1;
        double expResult = 0.00053;
        double result = TransferenciaCalor.jouleToCelsius(joule);
        assertEquals(expResult, result, 0.0);
        joule = 550000;
        expResult=291.5;
        result = TransferenciaCalor.jouleToCelsius(joule);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of caloriesToJoule method, of class TransferenciaCalor.
     */
    @Test
    public void testCaloriesToJoule() {
        System.out.println("caloriesToJoule");
        double calories = 10;
        double expResult = 41.8;
        double result = TransferenciaCalor.caloriesToJoule(calories);
        assertEquals(expResult, result, 0.0);
        
        calories = 53;
        expResult=221.54;
        result = TransferenciaCalor.caloriesToJoule(calories);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of stepCPUDissipation method, of class TransferenciaCalor.
     */
    @Test
    public void testStepCPUDissipation() {
        System.out.println("stepCPUDissipation");
        double expResult = 20;
        instance.stepCPUDissipation();
        double result = Math.round(si.getTemperatura());
        assertTrue("SI temperatura should be "+expResult+" but got "+result,expResult==result);
    }
    
}