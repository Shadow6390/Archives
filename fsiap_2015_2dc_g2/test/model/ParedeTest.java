/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import model.interfaces.CondutorCalor;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alexandra Silva
 */
public class ParedeTest {

    Simulacao simulacao;

    public ParedeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        simulacao = new Simulacao();
        Idioma idioma = new Idioma("uc12.idioma.portugues", new Locale("pt", "PT"));
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getListaMateriais method, of class Parede.
     */
    @Test
    public void testSetGetListaMateriais() {
        try {
            System.out.println("setListaMateriais e getListaMateriais");

            Parede instance = new Parede();

            BufferedImage imagem = ImageIO.read(new File("imagemTeste.jpg"));
            Material m1 = new Material("material 1", "isto é uma descrição do material ", 3.5, 3.5, 3.5, imagem);

            ListaMateriaisParede lmp = new ListaMateriaisParede();
            lmp.adicionarMaterial(m1, 2.5);

            instance.setListaMateriais(lmp);

            assertTrue(lmp.equals(instance.getListaMateriais()));

            try {
                instance.setListaMateriais(null);
                assertTrue("Devia ter sido lançada uma excepção devido à lista estar a null", false);
            } catch (IllegalArgumentException ex) {
            }

        } catch (IOException ex) {
            Logger.getLogger(MaterialTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getComprimento method, of class Parede.
     */
    @Test
    public void testSetGetComprimento() {
        System.out.println("setComprimento e getComprimento");

        Parede instance = new Parede();

        instance.setComprimento(5);

        double expResult = 5;
        double result = instance.getComprimento();
        assertEquals(expResult, result, 0.0);

        try {
            instance.setComprimento(-3);
            assertTrue("Devia ter sido lançada uma excepção devido ao comprimento ser nulo/negativo", false);
        } catch (IllegalArgumentException ex) {
        }
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getAltura method, of class Parede.
     */
    @Test
    public void testSetGetAltura() {
        System.out.println("setAltura e getAltura");

        Parede instance = new Parede();

        instance.setAltura(3);

        double expResult = 3.0;
        double result = instance.getAltura();
        assertEquals(expResult, result, 0.0);

        try {
            instance.setAltura(-3);
            assertTrue("Devia ter sido lançada uma excepção devido à altura ser nula/negativa", false);
        } catch (IllegalArgumentException ex) {
        }
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getLargura method, of class Parede.
     */
    @Test
    public void testGetLargura() {
        try {
            System.out.println("setLargura e getLargura");

            Parede instance = new Parede();

            BufferedImage imagem = ImageIO.read(new File("imagemTeste.jpg"));
            Material m1 = new Material("material 1", "isto é uma descrição do material ", 3.5, 3.5, 3.5, imagem);
            Material m2 = new Material("material 2", "isto é a descrição do material 2", 1.7, 1.7, 1.7, imagem);
            Material m3 = new Material("material 3", "isto é a descrição do material 3", 2.9, 2.9, 2.9, imagem);

            instance.getListaMateriais().adicionarMaterial(m1, 2.5);
            instance.getListaMateriais().adicionarMaterial(m2, 9.7);
            instance.getListaMateriais().adicionarMaterial(m3, 6.2);

            double expResult = 18.4;
            double result = instance.getProfundidade();
            assertEquals(expResult, result, 0.0);

        } catch (IOException ex) {
            Logger.getLogger(MaterialTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Parede.
     */
    @Test
    public void testEquals() {
        try {
            System.out.println("equals");

            Parede instance = new Parede();
            Parede instance2 = new Parede();
            Parede instance3 = new Parede();
            Parede instance4 = null;
            ListaMateriaisParede instance5 = new ListaMateriaisParede();

            BufferedImage imagem = ImageIO.read(new File("imagemTeste.jpg"));
            Material m1 = new Material("material 1", "isto é uma descrição do material ", 3.5, 3.5, 3.5, imagem);
            Material m2 = new Material("material 2", "isto é a descrição do material 2", 1.7, 1.7, 1.7, imagem);
            Material m3 = new Material("material 3", "isto é a descrição do material 3", 2.9, 2.9, 2.9, imagem);

            instance.getListaMateriais().adicionarMaterial(m1, 2.5);
            instance.getListaMateriais().adicionarMaterial(m2, 9.7);
            instance.getListaMateriais().adicionarMaterial(m3, 6.2);

            instance2.getListaMateriais().adicionarMaterial(m1, 2.5);
            instance2.getListaMateriais().adicionarMaterial(m2, 9.7);
            instance2.getListaMateriais().adicionarMaterial(m3, 6.2);

            instance3.getListaMateriais().adicionarMaterial(m2, 9.7);
            instance3.getListaMateriais().adicionarMaterial(m3, 6.2);

            assertTrue(instance.equals(instance2));
            assertTrue(!instance.equals(instance3));
            assertTrue(!instance.equals(instance4));
            assertTrue(!instance.equals(instance5));

        } catch (IOException ex) {
            Logger.getLogger(MaterialTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getMaterialExterior method, of class Parede.
     */
    @Test
    public void testGetMaterialExterior() {
        System.out.println("getMaterialExterior");
        Parede instance = new Parede();
        Material m1 = simulacao.getListaMateriaisExistentes().get(0);
        Material m2 = simulacao.getListaMateriaisExistentes().get(1);
        instance.getListaMateriais().adicionarMaterial(m1, 4);
        instance.getListaMateriais().adicionarMaterial(m2, 7);
        assertEquals(m2, instance.getMaterialExterior());
    }

    /**
     * Test of getTemperatura method, of class Parede.
     */
    @Test
    public void testSetGetTemperatura() {
        System.out.println("getTemperatura");
        Parede instance = new Parede();
        double expResult = 25.0;
        instance.setTemperatura(expResult);
        double result = instance.getTemperatura();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of convectHeatExternal method, of class Parede.
     */
    @Test
    public void testConvectHeatExternal() {
        System.out.println("convectHeatExternal");
        Simulacao sim = new Simulacao();
        Sala sala = sim.getSala();
        Parede instance = new Parede();
        instance.convectHeatExternal(sala);
    }

    /**
     * Test of validar method, of class Parede.
     */
    @Test
    public void testValidar() {
        try {
            System.out.println("validar");

            Parede instance = new Parede();
            instance.setPosicao(new double[]{0.0, 0.0, 0.0});
            instance.setAltura(3);
            instance.setComprimento(7);
            instance.setLargura(1);
            instance.setOrientacaoParede(Parede.ORIENTACAO_HORIZONTAL);
            instance.setTemperatura(5);

            BufferedImage imagem = ImageIO.read(new File("imagemTeste.jpg"));
            Material m1 = new Material("material 1", "isto é uma descrição do material ", 3.5, 3.5, 3.5, imagem);
            Material m2 = new Material("material 2", "isto é a descrição do material 2", 1.7, 1.7, 1.7, imagem);
            Material m3 = new Material("material 3", "isto é a descrição do material 3", 2.9, 2.9, 2.9, imagem);

            instance.getListaMateriais().adicionarMaterial(m1, 2.5);
            instance.getListaMateriais().adicionarMaterial(m2, 9.7);
            instance.getListaMateriais().adicionarMaterial(m3, 6.2);

            boolean expResult = true;
            boolean result = instance.validar();
            assertEquals(expResult, result);

        } catch (IOException ex) {
            Logger.getLogger(MaterialTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Test of getAreaContactoConveccao method, of class Parede.
     */
    @Test
    public void testGetAreaContactoConveccao() {
        System.out.println("getAreaContactoConveccao");
        Parede instance = new Parede();
        double expResult = 0.0;
        double result = instance.getAreaContactoConveccao();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getMassa method, of class Parede.
     */
    @Test
    public void testGetMassa() {
        System.out.println("getMassa");
        Parede instance = new Parede();
        double expResult = 0.0;
        double result = instance.getMassa();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getMaterial method, of class Parede.
     */
    @Test
    public void testGetMaterial() {
        System.out.println("getMaterial");
        Parede instance = new Parede();
        Material m1 = simulacao.getListaMateriaisExistentes().get(0);
        Material m2 = simulacao.getListaMateriaisExistentes().get(1);
        instance.getListaMateriais().adicionarMaterial(m1, 4);
        instance.getListaMateriais().adicionarMaterial(m2, 7);
        assertEquals(m1, instance.getMaterial());
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of isCollidable method, of class Parede.
     */
    @Test
    public void testIsCollidable() {
        System.out.println("isCollidable");
        Parede instance = new Parede();
        boolean expResult = true;
        boolean result = instance.isCollidable();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPosicao method, of class Parede.
     */
    @Test
    public void testGetPosicao() {
        System.out.println("getPosicao");
        Parede instance = new Parede();
        double[] expResult = new double[]{0.0, 0.0, 0.0};
        double[] result = instance.getPosicao();
        //assertArrayEquals(expResult, result);
    }

    /**
     * Test of getDimensoes method, of class Parede.
     */
    @Test
    public void testGetDimensoes() {
        System.out.println("getDimensoes");
        Parede instance = new Parede();
        double[] expResult = new double[3];
        expResult[0] = 1;
        expResult[1] = 2;
        expResult[2] = 3;
        instance.setAltura(3);
        instance.setComprimento(1);
        instance.setLargura(2);
        double[] result = instance.getDimensoes();
        assertEquals(Arrays.toString(expResult), Arrays.toString(result));
    }

    /**
     * Test of getResistencia method, of class Parede.
     */
    @Test
    public void testGetResistencia() throws IOException{
        System.out.println("getResistencia");
        CondutorCalor other = null;
        Parede instance = new Parede();
        
        List<Material> list1 = new ArrayList();
        BufferedImage imagem = ImageIO.read(new File("imagemTeste.jpg"));
        Material m1 = new Material("material 1", "isto é uma descrição do material ", 3.5, 3.5, 3.5, imagem);
        Material m2 = new Material("material 2", "isto é a descrição do material 2", 1.7, 1.7, 1.7, imagem);
        Material m3 = new Material("material 3", "isto é a descrição do material 3", 2.9, 2.9, 2.9, imagem);
        list1.add(m1);
        list1.add(m2);
        list1.add(m3);
        
        List<Double> ld = new ArrayList();
        ld.add(0.2);
        ld.add(0.1);
        ld.add(0.3);
        ListaMateriaisParede lm = new ListaMateriaisParede(list1, ld);
        
        instance.setLargura(5);
        instance.setAltura(3);
        instance.setListaMateriais(lm);
        instance.setOrientacaoParede(Parede.ORIENTACAO_HORIZONTAL);
        double expResult = 0.015;
        double result = instance.getResistencia(other);
        assertEquals(String.format("%.3f",expResult), String.format("%.3f",result));
    }

}