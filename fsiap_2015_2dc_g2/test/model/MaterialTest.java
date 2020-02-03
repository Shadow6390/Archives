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
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 */
public class MaterialTest {

    private Simulacao sim;

    public MaterialTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        sim = new Simulacao();
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test of getNome method and setNome method, of class Material.
     */
    @Test
    public void testGetSetNome() {
        System.out.println("getSetNome");
        Material instance = new Material();
        String expResult = "nome";
        instance.setNome(expResult);
        String result = instance.getNome();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDescricao method and setDescricao method, of class Material.
     */
    @Test
    public void testGetSetDescricao() {
        System.out.println("getDescricao");
        Material instance = new Material();
        String expResult = "descricao";
        instance.setDescricao(expResult);
        String result = instance.getDescricao();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCoeficienteConducao method and setCoeficienteConducao method,
     * of class Material.
     */
    @Test
    public void testGetSetCoeficienteConducao() {
        System.out.println("getSetCoeficienteConducao");
        Material instance = new Material();
        double expResult = 20.0;
        instance.setCoeficienteConducao(expResult);
        double result = instance.getCoeficienteConducao();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getCoeficienteConveccao method and setCoeficienteConveccao
     * method, of class Material.
     */
    @Test
    public void testGetSetCoeficienteConveccao() {
        System.out.println("getSetCoeficienteConveccao");
        Material instance = new Material();
        double expResult = 20.0;
        instance.setCoeficienteConveccao(expResult);
        double result = instance.getCoeficienteConveccao();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getCoeficienteRadiacao method and setCoeficienteRadiacao method,
     * of class Material.
     */
    @Test
    public void testGetSetCoeficienteRadiacao() {
        System.out.println("getSetCoeficienteRadiacao");
        Material instance = new Material();
        double expResult = 20.0;
        instance.setCoeficienteRadiacao(expResult);
        double result = instance.getCoeficienteRadiacao();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getImagem method and setImagem method, of class Material.
     */
    @Test
    public void testGetSetImagem() {
        try {
            System.out.println("getSetImagem");
            Material instance = new Material();
            BufferedImage expResult = ImageIO.read(new File("imagemTeste.jpg"));
            instance.setImagem(expResult);
            BufferedImage result = instance.getImagem();
            assertEquals(expResult, result);
        } catch (IOException ex) {
            Logger.getLogger(MaterialTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of valida method, of class Material.
     */
    @Test
    public void testValida() {
        try {
            System.out.println("valida");
            BufferedImage image = ImageIO.read(new File("imagemTeste.jpg"));
            Material instance = new Material("nome", "descricao", 20.0, 20.0, 20.0, image);
            boolean expResult = true;
            boolean result = instance.valida();
            assertEquals(expResult, result);

        } catch (IOException ex) {
            Logger.getLogger(MaterialTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of toString method, of class Material.
     */
    @Test
    public void testToString() {
        try {
            System.out.println("toString");
            BufferedImage image = ImageIO.read(new File("imagemTeste.jpg"));
            Material instance = new Material("nome", "descricao", 20.0, 20.0, 20.0, image);
            String expResult = "Material: Nome: nome, Descrição: descricao, Coeficiente de Condução: 20,00, Coeficiente de Convecção: 20,00, Coeficiente de Radiação: 20,00.";
            String result = instance.toString();
            assertEquals(expResult, result);
        } catch (IOException ex) {
            Logger.getLogger(MaterialTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Test of equals method, of class Material.
     */
    @Test
    public void testEquals() {
        try {
            System.out.println("equals");
            BufferedImage image = ImageIO.read(new File("imagemTeste.jpg"));
            Material instance = new Material("nome", "descricao", 20.0, 20.0, 20.0, image);
            Material other = new Material("nome", "descricao", 20.0, 20.0, 20.0, image);
            boolean expResult = true;
            boolean result = instance.equals(other);
            assertEquals(expResult, result);

        } catch (IOException ex) {
            Logger.getLogger(MaterialTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     /**
     * Test of equals method, of class Material.
     */
    @Test
    public void testEqualsNot() {
        try {
            System.out.println("equals");
            BufferedImage image = ImageIO.read(new File("imagemTeste.jpg"));
            Material instance = new Material("nome", "descricao", 20.0, 20.0, 20.0, image);
            Material other = new Material("nome2", "descricao", 30.0, 30.0, 30.0, image);
            boolean expResult = false;
            boolean result = instance.equals(other);
            assertEquals(expResult, result);

        } catch (IOException ex) {
            Logger.getLogger(MaterialTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
