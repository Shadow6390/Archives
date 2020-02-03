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
import java.util.List;
import java.util.Locale;
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
 * @author Alexandra Silva
 */
public class ListaMateriaisParedeTest {
    
    public ListaMateriaisParedeTest() {
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
     * Test of getListaMateriais method, of class ListaMateriaisParede.
     */
    @Test
    public void testSetGetListaMateriais() {
        
        System.out.println("setListaMateriais e getListaMateriais");
        
        ListaMateriaisParede instance = new ListaMateriaisParede();
        
        try {
            BufferedImage imagem = ImageIO.read(new File("imagemTeste.jpg"));
            Material m1 = new Material("material 1", "isto é uma descrição do material ", 3.5,3.5,3.5, imagem);
            
            List<Material> listaMateriais = new ArrayList<>();
            listaMateriais.add(m1);
            
            instance.setListaMateriais(listaMateriais);
            
            assertTrue("Listas devem ser idênticas", listaMateriais.equals(instance.getListaMateriais()));
            
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
     * Test of getListaEspessuras method, of class ListaMateriaisParede.
     */
    @Test
    public void testSetGetListaEspessuras() {
        System.out.println("setListaEspessuras e getListaEspessuras");
        
        ListaMateriaisParede instance = new ListaMateriaisParede();
        
        List<Double> listaEspessuras = new ArrayList<>();
        listaEspessuras.add(3.0);
        listaEspessuras.add(9.0);
        
        instance.setListaEspessuras(listaEspessuras);
        
        List<Double> instance1 = listaEspessuras;
        List<Double> instance2 = instance.getListaEspessuras();
        
        assertTrue("Listas devem ser idênticas", instance1.equals(instance2));
        
        try {
            instance.setListaEspessuras(null);
            assertTrue("Devia ter sido lançada uma excepção devido à lista estar a null", false);
        } catch (IllegalArgumentException ex) {
        }
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getMaterial method, of class ListaMateriaisParede.
     */
    @Test
    public void testSetGetMaterial() {
        try {
            System.out.println("setMaterial e getMaterial");
            
            ListaMateriaisParede instance = new ListaMateriaisParede();
            
            BufferedImage imagem = ImageIO.read(new File("imagemTeste.jpg"));
            Material m1 = new Material("material 1", "isto é uma descrição do material ", 3.5,3.5,3.5, imagem);
            Material m2 = new Material("material 2", "isto é a descrição do material 2", 1.7,1.7,1.7, imagem);
            Material m3 = new Material("material 3", "isto é a descrição do material 3", 2.9,2.9,2.9, imagem);
            List<Material> listaMateriais = new ArrayList<>();
            listaMateriais.add(m1);
            listaMateriais.add(m2);
            listaMateriais.add(m3);
            
            instance.setListaMateriais(listaMateriais);
            
            int i = 1;
            
            assertTrue("Materiais devem ser idênticos", m2.equals(instance.getMaterial(i)));
            
            try {
                instance.setMaterial(i, null);
                assertTrue("Devia ter sido lançada uma excepção devido ao material estar a null", false);
            } catch (IllegalArgumentException ex) {
            }
            
            try {
                instance.setMaterial(10, m1);
                assertTrue("Devia ter sido lançada uma excepção devido ao indiceser maior que o tamanho da lista existente", false);
            } catch (IllegalArgumentException ex) {
            }
            
        } catch (IOException ex) {
            Logger.getLogger(MaterialTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getEspessura method, of class ListaMateriaisParede.
     */
    @Test
    public void testSetGetEspessura() {
        System.out.println("setEspessura e getEspessura");
        
        ListaMateriaisParede instance = new ListaMateriaisParede();
        
        List<Double> listaEspessuras = new ArrayList<>();
        listaEspessuras.add(3.0);
        listaEspessuras.add(9.7);
        listaEspessuras.add(6.5);
        
        instance.setListaEspessuras(listaEspessuras);
        
        int i = 1;
        assertTrue("Materiais devem ser idênticos", 9.7 == instance.getEspessura(i));
        
        try {
            instance.setEspessura(i, -3);
            assertTrue("Devia ter sido lançada uma excepção devido à espessura ser negativa", false);
        } catch (IllegalArgumentException ex) {
        }
        
        try {
            instance.setEspessura(10, 2);
            assertTrue("Devia ter sido lançada uma excepção devido ao indice ser maior que o tamanho da lista existente", false);
        } catch (IllegalArgumentException ex) {
        }
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of adicionarMaterial method, of class ListaMateriaisParede.
     */
    @Test
    public void testAdicionarMaterial() {
        System.out.println("adicionarMaterial");
        
        ListaMateriaisParede instance = new ListaMateriaisParede();
        
        try {
            BufferedImage imagem = ImageIO.read(new File("imagemTeste.jpg"));
            Material m1 = new Material("material 1", "isto é uma descrição do material ", 3.5,3.5,3.5, imagem);
            Double espessura = 9.7;
            
            instance.adicionarMaterial(m1, espessura);
            
            assertTrue(instance.size() != 0);
            assertTrue(m1.equals(instance.getMaterial(0)));
            assertTrue(9.7 == instance.getEspessura(0));
            
            try {
                instance.adicionarMaterial(m1, -3);
                assertTrue("Devia ter sido lançada uma excepção devido à espessura ser negativa", false);
            } catch (IllegalArgumentException ex) {
            }
            try {
                instance.adicionarMaterial(null, 2);
                assertTrue("Devia ter sido lançada uma excepção devido ao material estar a null", false);
            } catch (IllegalArgumentException ex) {
            }
        } catch (IOException ex) {
            Logger.getLogger(MaterialTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class ListaMateriaisParede.
     */
    @Test
    public void testEquals() {
        try {
            System.out.println("equals");
            
            ListaMateriaisParede instance = new ListaMateriaisParede();
            ListaMateriaisParede instance2 = new ListaMateriaisParede();
            ListaMateriaisParede instance3 = new ListaMateriaisParede();
            ListaMateriaisParede instance4 = null;
            
            BufferedImage imagem = ImageIO.read(new File("imagemTeste.jpg"));
            Material m1 = new Material("material 1", "isto é uma descrição do material ", 3.5,3.5,3.5, imagem);
            Material m2 = new Material("material 2", "isto é a descrição do material 2", 1.7,1.7,1.7, imagem);
            Material m3 = new Material("material 3", "isto é a descrição do material 3", 2.9,2.9,2.9, imagem);
            
            instance.adicionarMaterial(m1, 3.0);
            instance.adicionarMaterial(m2, 9.7);
            instance.adicionarMaterial(m3, 5.3);
            
            instance2.adicionarMaterial(m1, 3.0);
            instance2.adicionarMaterial(m2, 9.7);
            instance2.adicionarMaterial(m3, 5.3);
            
            instance3.adicionarMaterial(m1, 5.3);
            instance3.adicionarMaterial(m2, 3.0);
            instance3.adicionarMaterial(m3, 9.7);
            
            assertTrue(instance.equals(instance2));
            assertTrue(!instance.equals(instance3));
            assertTrue(!instance.equals(instance4));
            
        } catch (IOException ex) {
            Logger.getLogger(MaterialTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of size method, of class ListaMateriaisParede.
     */
    @Test
    public void testSize() {
        try {
            System.out.println("size");
            
            ListaMateriaisParede instance = new ListaMateriaisParede();
            
            BufferedImage imagem = ImageIO.read(new File("imagemTeste.jpg"));
            Material m1 = new Material("material 1", "isto é uma descrição do material ", 3.5,3.5,3.5, imagem);
            Material m2 = new Material("material 2", "isto é a descrição do material 2", 1.7,1.7,1.7, imagem);
            Material m3 = new Material("material 3", "isto é a descrição do material 3", 2.9,2.9,2.9, imagem);
            
            List<Material> listaMateriais = new ArrayList<>();
            listaMateriais.add(m1);
            listaMateriais.add(m2);
            listaMateriais.add(m3);
            
            instance.setListaMateriais(listaMateriais);
            
            int expResult = 3;
            int result = instance.size();
            
            assertEquals(expResult, result);
        } catch (IOException ex) {
            Logger.getLogger(MaterialTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }


    /**
     * Test of validar method, of class ListaMateriaisParede.
     */
    @Test
    public void testValidar() {
        try {
            System.out.println("validar");
            
            BufferedImage imagem = ImageIO.read(new File("imagemTeste.jpg"));
            Material m1 = new Material("material 1", "isto é uma descrição do material ", 3.5,3.5,3.5, imagem);
            Material m2 = new Material("material 2", "isto é a descrição do material 2", 1.7,1.7,1.7, imagem);
            Material m3 = new Material("material 3", "isto é a descrição do material 3", 2.9,2.9,2.9, imagem);
            
            List<Material> listaMateriais = new ArrayList<>();
            listaMateriais.add(m1);
            listaMateriais.add(m2);
            
            List<Double> listaEspessuras = new ArrayList<>();
            listaEspessuras.add(3.0);
            listaEspessuras.add(6.7);
            
            ListaMateriaisParede instance = new ListaMateriaisParede(listaMateriais, listaEspessuras);
            
            assertTrue(instance.validar());
            
        } catch (IOException ex) {
            Logger.getLogger(MaterialTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
    
}
