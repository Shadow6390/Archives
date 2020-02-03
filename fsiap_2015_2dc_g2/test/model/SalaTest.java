/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.sun.javafx.scene.control.skin.VirtualFlow;
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
 * @author jbraga
 */
public class SalaTest {

    public SalaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        Simulacao simulacao = new Simulacao();
        Idioma idioma = new Idioma("uc12.idioma.portugues", new Locale("pt", "PT"));
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getComprimento method, of class Sala.
     */
    @Test
    public void testSetGetComprimento() {
        System.out.println("setComprimento e getComprimento");

        Simulacao sim = new Simulacao();
        Material m = sim.getListaMateriaisExistentes().get(0);
        Sala instance = new Sala(1, 1, 1, 20, 21, 22, 20, 24, m);

        instance.setComprimento(5);

        double expResult = 5.0;
        double result = instance.getComprimento();
        assertEquals(expResult, result, 0.0);

        try {
            instance.setComprimento(-3);
            assertTrue("Devia ter sido lançada uma excepção devido ao comprimento ser negativo", false);
        } catch (IllegalArgumentException ex) {
        }
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getLargura method, of class Sala.
     */
    @Test
    public void testSetGetLargura() {
        System.out.println("setLargura e getLargura");

        Simulacao sim = new Simulacao();
        Material m = sim.getListaMateriaisExistentes().get(0);
        Sala instance = new Sala(1, 1, 1, 20, 21, 22, 20, 24, m);

        instance.setLargura(2);

        double expResult = 2.0;
        double result = instance.getLargura();
        assertEquals(expResult, result, 0.0);

        try {
            instance.setLargura(-3);
            assertTrue("Devia ter sido lançada uma excepção devido à largura ser negativa", false);
        } catch (IllegalArgumentException ex) {
        }
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getAltura method, of class Sala.
     */
    @Test
    public void testSetGetAltura() {
        System.out.println("setAltura e getAltura");

        Sala instance = new Sala();

        instance.setAltura(3);

        double expResult = 3.0;
        double result = instance.getAltura();
        assertEquals(expResult, result, 0.0);
        try {
            instance.setAltura(-3);
            assertTrue("Devia ter sido lançada uma excepção devido à altura ser negativa", false);
        } catch (IllegalArgumentException ex) {
        }
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getTeto method, of class Sala.
     */
    @Test
    public void testSetGetTeto() {
        try {
            System.out.println("setTeto e getTeto");
            Sala instance = new Sala();

            BufferedImage imagem = ImageIO.read(new File("imagemTeste.jpg"));
            Material m1 = new Material("material 1", "isto é uma descrição do material ", 3.5, 3.5, 3.5, imagem);

            Parede t = new Parede();
            t.getListaMateriais().adicionarMaterial(m1, 3);

            instance.setTeto(t);

            Parede expResult = t;
            Parede result = instance.getTeto();
            assertEquals(expResult, result);

            try {
                instance.setTeto(null);
                assertTrue("Devia ter sido lançada uma excepção devido ao teto ser nulo", false);
            } catch (IllegalArgumentException ex) {
            }
        } catch (IOException ex) {
            Logger.getLogger(MaterialTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getListaParedes method, of class Sala.
     */
    @Test
    public void testSetGetListaParedes() {
        try {
            System.out.println("setListaParedes e getListaParedes");
            Sala instance = new Sala();

            BufferedImage imagem = ImageIO.read(new File("imagemTeste.jpg"));
            Material m1 = new Material("material 1", "isto é uma descrição do material ", 3.5, 3.5, 3.5, imagem);

            Parede p1 = new Parede();
            p1.getListaMateriais().adicionarMaterial(m1, 3);
            Parede p2 = new Parede();
            p1.getListaMateriais().adicionarMaterial(m1, 3);
            Parede p3 = new Parede();
            p1.getListaMateriais().adicionarMaterial(m1, 3);
            Parede p4 = new Parede();
            p1.getListaMateriais().adicionarMaterial(m1, 3);

            List<Parede> paredes = new ArrayList<>();
            paredes.add(p1);
            paredes.add(p2);
            paredes.add(p3);
            paredes.add(p4);

            instance.setListaParedes(paredes);

            List<Parede> expResult = paredes;
            List<Parede> result = instance.getListaParedes();
            assertEquals(expResult, result);

            paredes.remove(p4);
            try {
                instance.setListaParedes(paredes);
                assertTrue("Devia ter sido lançada uma excepção devido ao numero de paredes ser diferente de 4", false);
            } catch (IllegalArgumentException ex) {
            }

            try {
                instance.setListaParedes(null);
                assertTrue("Devia ter sido lançada uma excepção devido à lista de paredes ser nula", false);
            } catch (IllegalArgumentException ex) {
            }

        } catch (IOException ex) {
            Logger.getLogger(MaterialTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of setTemperaturaInterior method, of class Sala.
     */
    @Test
    public void testSetGetTemperaturaInterior() {
        System.out.println("setTemperaturaInterior");
        double temperaturaInterior = 0.0;
        Sala instance = new Sala();
        instance.setTemperaturaInterior(temperaturaInterior);
    }

    /**
     * Test of setTemperaturaInteriorAlvo method, of class Sala.
     */
    @Test
    public void testSetGetTemperaturaInteriorAlvo() {
        System.out.println("setTemperaturaInteriorAlvo");
        double temperaturaInteriorAlvo = 25.0;
        Sala instance = new Sala();
        instance.setTemperaturaInteriorAlvo(temperaturaInteriorAlvo);
        assertEquals(25, instance.getTemperaturaInteriorAlvo(), 0.0);
        try {
            instance.setTemperaturaInteriorAlvo(-300);
            assertTrue("There should have been an exception", false);
        } catch (IllegalArgumentException e) {

        }
    }

    /**
     * Test of setTemperaturaExternaMinima method, of class Sala.
     */
    @Test
    public void testSetGetTemperaturaExternaMinima() {
        System.out.println("setTemperaturaExternaMinima");
        double temperaturaExteriorMinima = -2.0;
        Sala instance = new Sala();
        instance.setTemperaturaExternaMinima(temperaturaExteriorMinima);
        assertEquals(-2.0, instance.getTemperaturaExternaMinima(), 0.0);
        try {
            instance.setTemperaturaExternaMinima(-300);
            assertTrue("There should have been an exception", false);
        } catch (IllegalArgumentException e) {

        }
    }

    /**
     * Test of setTemperaturaExternaMaxima method, of class Sala.
     */
    @Test
    public void testSetGetTemperaturaExternaMaxima() {
        System.out.println("setTemperaturaExternaMaxima");
        double temperaturaExteriorMaxima = 25.0;
        Sala instance = new Sala();
        instance.setTemperaturaExternaMaxima(temperaturaExteriorMaxima);
        assertEquals(25, instance.getTemperaturaExternaMaxima(), 0.0);
    }

    /**
     * Test of validar method, of class Sala.
     */
    @Test
    public void testValidar() {
        System.out.println("validar");
        Simulacao sim = new Simulacao();
        Material m = sim.getListaMateriaisExistentes().get(0);
        Sala instance = new Sala(1, 1, 1, 20, 21, 22, 20, 24, m);
        assertTrue(instance.validar());
        try {
            instance = new Sala(-4, 1, 1, 20, 21, 22, 20, 24, m);
            assertTrue("Devia ter sido lançado uma exceçao pois o comprimento é inválido.", false);
        } catch (IllegalArgumentException ex) {

        }
        try {
            instance = new Sala(1, -4, 1, 20, 21, 22, 20, 24, m);
            assertTrue("Devia ter sido lançado uma exceçao pois a largura é inválida.", false);
        } catch (IllegalArgumentException ex) {

        }
        try {
            instance = new Sala(1, 1, -4, 20, 21, 22, 20, 24, m);
            assertTrue("Devia ter sido lançado uma exceçao pois a altura é inválida.", false);
        } catch (IllegalArgumentException ex) {

        }
        try {
            instance = new Sala(1, 1, 1, -300, 21, 22, 20, 24, m);
            assertTrue("Devia ter sido lançado uma exceçao pois a temperatura interior é inválida.", false);
        } catch (IllegalArgumentException ex) {

        }
        try {
            instance = new Sala(1, 1, 1, 20, -300, 22, 20, 24, m);
            assertTrue("Devia ter sido lançado uma exceçao pois a temperatura alvo é inválida.", false);
        } catch (IllegalArgumentException ex) {

        }
        try {
            instance = new Sala(1, 1, 1, 20, 21, 22, -300, 24, m);
            instance.setHasTemperaturaVariation(true);
            assertTrue("Devia ter sido lançado uma exceçao pois a temperatura exterior minima é inválida.", false);
        } catch (IllegalArgumentException ex) {

        }
        try {
            instance = new Sala(1, 1, 1, 20, 21, 22, 21, -300, m);
            instance.setHasTemperaturaVariation(true);
            assertTrue("Devia ter sido lançado uma exceçao pois a temperatura exterior máxima é inválida.", false);
        } catch (IllegalArgumentException ex) {

        }
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of hasTemperaturaVariation method, of class Sala.
     */
    @Test
    public void testHasTemperaturaVariation() {
        System.out.println("hasTemperaturaVariation");
        Sala instance = new Sala();
        boolean expResult = false;
        boolean result = instance.hasTemperaturaVariation();
        assertEquals(expResult, result);

        expResult = true;
        instance.setHasTemperaturaVariation(true);
        result = instance.hasTemperaturaVariation();
        assertEquals(expResult, result);
    }

    /**
     * Test of setHasTemperaturaVariation method, of class Sala.
     */
    @Test
    public void testSetHasTemperaturaVariation() {
        System.out.println("setHasTemperaturaVariation");
        boolean hasTemperaturaVariation = true;
        Sala instance = new Sala();
        instance.setHasTemperaturaVariation(hasTemperaturaVariation);
        boolean expResult = true;
        assertEquals(expResult, instance.hasTemperaturaVariation());
    }

    /**
     * Test of getAreaContactoConveccao method, of class Sala. The room does not
     * perform convection. The objects in it perform convection with the room
     * (hence, their area of contact is used instead).
     */
    @Test
    public void testGetAreaContactoConveccao() {
        System.out.println("getAreaContactoConveccao");
        Sala instance = new Sala();
        double expResult = 0.0;
        double result = instance.getAreaContactoConveccao();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getMassa method, of class Sala. The mass of the air in the room
     * must be 0! This is due to the fact that conduction with room's air is not
     * performed.
     */
    @Test
    public void testGetMassa() {
        System.out.println("getMassa");
        Sala instance = new Sala();
        double expResult = 0.0;
        double result = instance.getMassa();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getMaterial method, of class Sala.
     */
    @Test
    public void testGetMaterial() {
        System.out.println("getMaterial");
        Sala instance = new Sala();
        Material expResult = null;
        Material result = instance.getMaterial();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTemperatura method, of class Sala.
     */
    @Test
    public void testSetGetTemperatura() {
        System.out.println("setTemperatura");
        double temp = 18.0;
        Sala instance = new Sala();
        instance.setTemperatura(temp);
        assertEquals(18.0, instance.getTemperatura(), 0.0);
        try {
            instance.setTemperatura(-300);
            assertTrue("There should have been an exception", false);
        } catch (IllegalArgumentException e) {

        }
    }

    /**
     * Test of isCollidable method, of class Sala.
     */
    @Test
    public void testIsCollidable() {
        System.out.println("isCollidable");
        Sala instance = new Sala();
        boolean expResult = false;
        boolean result = instance.isCollidable();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPosicao method, of class Sala.
     */
    @Test
    public void testGetPosicao() {
        System.out.println("getPosicao");
        Sala instance = new Sala();
        double[] expResult = null;
        double[] result = instance.getPosicao();
        assertTrue("Should be null", expResult == result);
    }

    /**
     * Test of getDimensoes method, of class Sala.
     */
    @Test
    public void testGetDimensoes() {
        System.out.println("getDimensoes");
        Simulacao sim = new Simulacao();
        Material m = sim.getListaMateriaisExistentes().get(0);
        Sala instance = new Sala(1, 1, 1, 20, 21, 22, 20, 24, m);
        double[] expResult = new double[3];
        expResult[0] = 3;
        expResult[1] = 2;
        expResult[2] = 4;

        instance.setAltura(4);
        instance.setComprimento(3);
        instance.setLargura(2);
        double[] result = instance.getDimensoes();
        String exp = Arrays.toString(expResult);
        String res = Arrays.toString(result);
        assertEquals(exp, res);
    }

    /**
     * Test of getResistencia method, of class Sala.
     */
    @Test
    public void testGetResistencia() {
        System.out.println("getResistencia");
        CondutorCalor other = null;
        Sala instance = new Sala();
        double expResult = 0.0;
        double result = instance.getResistencia(other);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setTemperaturaExterior method, of class Sala.
     */
    @Test
    public void testSetGetTemperaturaExterior() {
        System.out.println("setTemperaturaExterior");
        double temperaturaExterior = 11.0;
        Sala instance = new Sala();
        instance.setTemperaturaExterior(temperaturaExterior);
        assertEquals(11.0, instance.getTemperaturaExterior(), 0.0);
        try {
            instance.setTemperaturaExterior(-300);
            assertTrue("There should have been an exception", false);
        } catch (IllegalArgumentException e) {

        }
    }

}
