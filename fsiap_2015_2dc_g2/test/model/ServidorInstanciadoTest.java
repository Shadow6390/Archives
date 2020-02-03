/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import model.interfaces.CondutorCalor;
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
public class ServidorInstanciadoTest {
    
    Servidor servidor;
    ServidorInstanciado si,si2;
    Simulacao sim;
    
    public ServidorInstanciadoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        servidor = new Servidor();
        servidor.setAltura(20d);
        servidor.setLargura(40d);
        servidor.setComprimento(10d);
        si = new ServidorInstanciado(servidor);
        si2 = new ServidorInstanciado(servidor);
        si.setPosicao(new double[]{0d,0d,0d});
        double[] pos = new double[3];
        pos[0]=5d;
        pos[1]=35d;
        pos[2]=20d;
        si2.setPosicao(pos);
        sim = new Simulacao();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getTemperatura method, of class ServidorInstanciado.
     */
    @Test
    public void testGetTemperatura() {
        System.out.println("getTemperatura");
        ServidorInstanciado instance = new ServidorInstanciado();
        instance.setTemperatura(20);
        double expResult = 20.0;
        double result = instance.getTemperatura();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getPosicao method, of class ServidorInstanciado.
     */
    @Test
    public void testGetPosicao() {
        System.out.println("getPosicao");
        ServidorInstanciado instance = new ServidorInstanciado();
        instance.setPosicao(new double[]{2,3,4});
        double[] expResult = {2,3,4};
        double[] result = instance.getPosicao();
        String a = Arrays.toString(result);
        String b = Arrays.toString(expResult);
        assertEquals(b,a);
    }

    /**
     * Test of setTemperatura method, of class ServidorInstanciado.
     */
    @Test
    public void testSetTemperatura() {
        System.out.println("setTemperatura");
        double temp = -3.0;
        ServidorInstanciado instance = new ServidorInstanciado();
        instance.setTemperatura(temp);
        assertEquals(temp,instance.getTemperatura(),0.0);
    }

    /**
     * Test of setPosicao method, of class ServidorInstanciado.
     */
    @Test
    public void testSetPosicao() {
        System.out.println("setPosicao");
        double[] pos = {-3.2,4.0,5.5};
        ServidorInstanciado instance = new ServidorInstanciado();
        instance.setPosicao(pos);
        String a = Arrays.toString(pos);
        String b = Arrays.toString(instance.getPosicao());
        assertEquals(a,b);
    }

    /**
     * Test of getCollidingArea method, of class ServidorInstanciado.
     */
    @Test
    public void testGetCollidingArea() {
        System.out.println("getCollidingArea");
        CondutorCalor other = si2;
        double expResult = 25.0;
        double result = si.getCollidingArea(si2);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getAreaContactoConveccao method, of class ServidorInstanciado.
     */
    @Test
    public void testGetAreaContactoConveccao() {
        System.out.println("getAreaContactoConveccao");
        double expResult = 2400.0;
        double result = si.getAreaContactoConveccao();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getMassa method, of class ServidorInstanciado.
     */
    @Test
    public void testGetMassa() {
        System.out.println("getMassa");
        Servidor serv = new Servidor();
        serv.setMassa(40d);
        ServidorInstanciado instance = new ServidorInstanciado(serv);
        double expResult = 40d;
        double result = instance.getMassa();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getMaterial method, of class ServidorInstanciado.
     */
    @Test
    public void testGetMaterial() {
        System.out.println("getMaterial");
        Servidor server = new Servidor();
        Material mat=new Material();
        mat.setNome("XPTO");
        mat.setDescricao("Best description ever.");
        mat.setImagem(new BufferedImage(1,1,BufferedImage.TYPE_INT_RGB));
        server.setMaterial(mat);
        ServidorInstanciado instance = new ServidorInstanciado(server);
        Material expResult = mat;
        Material result = instance.getMaterial();
        assertEquals(expResult, result);
    }

    /**
     * Test of isCollidable method, of class ServidorInstanciado.
     */
    @Test
    public void testIsCollidable() {
        System.out.println("isCollidable");
        ServidorInstanciado instance = new ServidorInstanciado();
        boolean expResult = true;
        boolean result = instance.isCollidable();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDimensoes method, of class ServidorInstanciado.
     */
    @Test
    public void testGetDimensoes() {
        System.out.println("getDimensoes");
        double[] expResult = new double[3];
        expResult[0]=10d;
        expResult[1]=40d;
        expResult[2]=20d;
        double[] result = si.getDimensoes();
        String resA = Arrays.toString(expResult);
        String resB = Arrays.toString(result);
        assertEquals(resA, resB);
    }

    /**
     * Test of getResistencia method, of class ServidorInstanciado.
     */
    @Test
    public void testGetResistencia() {
        System.out.println("getResistencia");
        Material mat = new Material();
        mat.setCoeficienteConducao(0.05);
        mat.setNome("XPTO");
        mat.setDescricao("Best description ever.");
        mat.setImagem(new BufferedImage(1,1,BufferedImage.TYPE_INT_RGB));
        servidor.setMaterial(mat);
        CondutorCalor other = si2;
        double expResult = 8.0;
        double result = si.getResistencia(other);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setServidor method, of class ServidorInstanciado.
     */
    @Test
    public void testSetServidor() {
        System.out.println("setServidor");
        Servidor serv = new Servidor();
        ServidorInstanciado instance = new ServidorInstanciado();
        instance.setServidor(serv);
    }

    /**
     * Test of setCarga method, of class ServidorInstanciado.
     */
    @Test
    public void testSetCarga() {
        System.out.println("setCarga");
        double carga = 50.0;
        ServidorInstanciado instance = new ServidorInstanciado();
        instance.setCarga(carga);
        assertEquals(carga,instance.getCarga(),0.0);
    }
    
    /**
     * Test of setCarga method, of class ServidorInstanciado.
     * Neste teste, a carga a ser atribuida é inválida
     */
    @Test(expected=IllegalArgumentException.class)
    public void testSetCargaInvalido() {
        System.out.println("setCargaInvalido");
        double carga = -50.0;
        ServidorInstanciado instance = new ServidorInstanciado();
        instance.setCarga(carga);
    }

    /**
     * Test of getCarga method, of class ServidorInstanciado.
     */
    @Test
    public void testGetCarga() {
        System.out.println("getCarga");
        ServidorInstanciado instance = new ServidorInstanciado();
        double expResult = 2.0;
        instance.setCarga(2d);
        double result = instance.getCarga();
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void testIsCollidingWith()
    {
        System.out.println("isCollidingWith");
        
        ServidorInstanciado instance = new ServidorInstanciado(servidor);
        instance.setPosicao(new double[]{10,40,21});
        boolean expResult = false;
        boolean result = si.isCollidingWith(instance);
        assertEquals(expResult, result);
    }
    
}
