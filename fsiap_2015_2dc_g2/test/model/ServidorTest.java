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
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author
 */
public class ServidorTest {

    private Simulacao sim;
    
    public ServidorTest() {
        sim = new Simulacao();
    }

    /**
     * Test of getNome method and setNome method, of class Servidor.
     */
    @Test
    public void testGetNome() {
        System.out.println("getSetNome");
        Servidor instance = new Servidor();
        String expResult = "nome";
        instance.setNome(expResult);
        String result = instance.getNome();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMaterial method and setMaterial method, of class Servidor.
     */
    @Test
    public void testGetSetMaterial() {
        try {
            System.out.println("getSetMaterial");
            Servidor instance = new Servidor();
            BufferedImage image = ImageIO.read(new File("imagemTeste.jpg"));
            Material expResult = new Material("nome", "descricao", 20.0, 20.0, 20.0, image);
            instance.setMaterial(expResult);
            Material result = instance.getMaterial();
            assertEquals(expResult, result);
        } catch (IOException ex) {
            Logger.getLogger(ServidorTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getMassa method and setMass method, of class Servidor.
     */
    @Test
    public void testGetSetMassa() {
        System.out.println("getSetMassa");
        Servidor instance = new Servidor();
        double expResult = 20.0;
        instance.setMassa(expResult);
        double result = instance.getMassa();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getComprimento method and setComprimento method, of class
     * Servidor.
     */
    @Test
    public void testGetSetComprimento() {
        System.out.println("getSetComprimento");
        Servidor instance = new Servidor();
        double expResult = 20.0;
        instance.setComprimento(expResult);
        double result = instance.getComprimento();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getLargura method and setLargura method, of class Servidor.
     */
    @Test
    public void testGetSetLargura() {
        System.out.println("getSetLargura");
        Servidor instance = new Servidor();
        double expResult = 20.0;
        instance.setLargura(expResult);
        double result = instance.getLargura();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getCarga method and setCarga method, of class Servidor.
     */
    @Test
    public void testGetSetCarga() {
        System.out.println("getSetCarga");
        Servidor instance = new Servidor();
        double expResult = 20.0;
        instance.setCarga(expResult);
        double result = instance.getCarga();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getAltura method and setAltura method, of class Servidor.
     */
    @Test
    public void testGetSetAltura() {
        System.out.println("getSetAltura");
        Servidor instance = new Servidor();
        double expResult = 20.0;
        instance.setAltura(expResult);
        double result = instance.getAltura();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of valida method, of class Servidor.
     */
    @Test
    public void testValida() {
        try {
            System.out.println("valida");
            BufferedImage image = ImageIO.read(new File("imagemTeste.jpg"));
            Material mat = new Material("nome", "descricao", 20.0, 20.0, 20.0, image);
            Servidor instance = new Servidor("nome", mat, 20, 20, 20, 20, 20);
            boolean expResult = true;
            boolean result = instance.valida();
            assertEquals(expResult, result);
        } catch (IOException ex) {
            Logger.getLogger(ServidorTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Test of equals method, of class Servidor.
     */
    @Test
    public void testEquals() {
        try {
            System.out.println("equals");
            BufferedImage image = ImageIO.read(new File("imagemTeste.jpg"));
            Material mat = new Material("nome", "descricao", 20.0, 20.0, 20.0, image);
            Servidor instance = new Servidor("nome", mat, 20, 20, 20, 20, 20);
            Servidor other = new Servidor(instance);
            boolean expResult = true;
            boolean result = instance.equals(other);
            assertEquals(expResult, result);
        } catch (IOException ex) {
            Logger.getLogger(ServidorTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of equals method, of class Servidor.
     */
    @Test
    public void testEqualsNot() {
        try {
            System.out.println("equals");
            BufferedImage image = ImageIO.read(new File("imagemTeste.jpg"));
            Material mat = new Material("nome", "descricao", 20.0, 20.0, 20.0, image);
            Servidor instance = new Servidor("nome", mat, 20, 20, 20, 20, 20);
            Servidor other = new Servidor("nome1", mat, 20, 20, 20, 20, 20);
            boolean expResult = false;
            boolean result = instance.equals(other);
            assertEquals(expResult, result);
        } catch (IOException ex) {
            Logger.getLogger(ServidorTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of toString method, of class Servidor.
     */
    @Test
    public void testToString() {
        try {
            System.out.println("toString");
            BufferedImage image = ImageIO.read(new File("imagemTeste.jpg"));
            Material mat = new Material("nome", "descricao", 20.0, 20.0, 20.0, image);
            Servidor instance = new Servidor("nome", mat, 20, 20, 20, 20, 20);
            String expResult = "Servidor: Nome: nome, Massa: 20.0kg, Comprimento: 20,00m, "
                    + "Largura: 20,00m, Carga: 20,00%, Altura: 20,00m, Material: "
                    + "Material: Nome: nome, Descrição: descricao, Coeficiente de Condução: 20,00, "
                    + "Coeficiente de Convecção: 20,00, Coeficiente de Radiação: 20,00.";
            String result = instance.toString();
            assertEquals(expResult, result);
        } catch (IOException ex) {
            Logger.getLogger(ServidorTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
