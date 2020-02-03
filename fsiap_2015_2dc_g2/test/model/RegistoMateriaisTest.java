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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author
 */
public class RegistoMateriaisTest {

    private Simulacao sim;

    public RegistoMateriaisTest() {
        sim = new Simulacao();
    }

    /**
     * Test of getMateriais method and setMateriais method, of class
     * RegistoMateriais.
     */
    @Test
    public void testGetSetMateriais() {
        System.out.println("getSetMateriais");
        RegistoMateriais instance = new RegistoMateriais();
        ArrayList<Material> expResult = new ArrayList<>();
        Material mat = new Material();
        expResult.add(mat);
        instance.setMateriais(expResult);
        ArrayList<Material> result = instance.getMateriais();
        assertEquals(expResult, result);
    }

    /**
     * Test of novoMaterial method, of class RegistoMateriais.
     */
    @Test
    public void testNovoMaterial() {
        System.out.println("novoMaterial");
        String nome = "nome";
        String descricao = "descricao";
        double coeficiente = 20.0;
        try {
            BufferedImage imagem = ImageIO.read(new File("imagemTeste.jpg"));
            RegistoMateriais instance = new RegistoMateriais();
            Material result = instance.novoMaterial(nome, descricao, coeficiente, coeficiente, coeficiente, imagem);
            instance.addMaterial(result);
            Material expResult = instance.procuraMaterial(nome);
            assertEquals(expResult, result);
        } catch (IOException ex) {
            Logger.getLogger(RegistoMateriaisTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Test of getInfo method, of class RegistoMateriais.
     */
    @Test
    public void testGetInfo() {
        System.out.println("getInfo");
        RegistoMateriais instance = new RegistoMateriais();
        Material material = new Material("nome", "descricao", 20.0, 20.0, 20.0, null);
        instance.addMaterial(material);
        String expResult = material.toString();
        String result = instance.getInfo(material);
        assertEquals(expResult, result);
    }

    /**
     * Test of validaMaterial method, of class RegistoMateriais.
     */
    @Test
    public void testValidaMaterial() {
        System.out.println("validaMaterial");
        Material material = new Material();
        RegistoMateriais instance = new RegistoMateriais();
        boolean expResult = true;
        boolean result = instance.validaMaterial(material);
        assertEquals(expResult, result);
    }

    /**
     * Test of addMaterial method, of class RegistoMateriais.
     */
    @Test
    public void testAddMaterial() {
        System.out.println("addMaterial");
        Material material = new Material();
        RegistoMateriais instance = new RegistoMateriais();
        boolean expResult = true;
        boolean result = instance.addMaterial(material);
        assertEquals(expResult, result);
    }

    /**
     * Test of procuraMaterial method, of class RegistoMateriais.
     */
    @Test
    public void testProcuraMaterial() {
        try {
            System.out.println("procuraMaterial");
            String nome = "nome";
            RegistoMateriais instance = new RegistoMateriais();
            BufferedImage imagem = ImageIO.read(new File("imagemTeste.jpg"));
            Material expResult = new Material("nome", "descricao", 20.0, 20.0, 20.0, imagem);;
            instance.addMaterial(expResult);
            Material result = instance.procuraMaterial(nome);
            assertEquals(expResult, result);
        } catch (IOException ex) {
            Logger.getLogger(RegistoMateriaisTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Test of toString method, of class RegistoMateriais.
     */
    @Test
    public void testToString() {
        try {
            System.out.println("toString");
            String nome = "nome";
            RegistoMateriais instance = new RegistoMateriais();
            BufferedImage imagem = ImageIO.read(new File("imagemTeste.jpg"));
            Material material = new Material("nome", "descricao", 20.0, 20.0, 20.0, imagem);;
            instance.addMaterial(material);
            String expResult = "[Material: Nome: nome, Descrição: descricao, "
                    + "Coeficiente de Condução: 20,00,"
                    + " Coeficiente de Convecção: 20,00, "
                    + "Coeficiente de Radiação: 20,00.]";
            String result = instance.toString();
            assertEquals(expResult, result);
        } catch (IOException ex) {
            Logger.getLogger(RegistoMateriaisTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
