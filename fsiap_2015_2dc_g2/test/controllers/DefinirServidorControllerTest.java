/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import model.Material;
import model.Simulacao;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 */
public class DefinirServidorControllerTest {

    public DefinirServidorControllerTest() {
    }

    /**
     * Test of novoServidor method, of class DefinirServidorController.
     */
    @Test
    public void testNovoServidor() {
        System.out.println("novoServidor");
        Simulacao simulacao = new Simulacao();
        DefinirServidorController instance = new DefinirServidorController(simulacao);
        instance.novoServidor();
    }

    /**
     * Test of setNome method, of class DefinirServidorController.
     */
    @Test
    public void testSetNome() {
        System.out.println("setNome");
        String nome = "nome";
        Simulacao simulacao = new Simulacao();
        DefinirServidorController instance = new DefinirServidorController(simulacao);
        instance.novoServidor();
        instance.setNome(nome);
    }

    /**
     * Test of setMaterial method, of class DefinirServidorController.
     */
    @Test
    public void testSetMaterial() {
        try {
            System.out.println("setMaterial");
            BufferedImage imagem = ImageIO.read(new File("imagemTeste.jpg"));
            Material mat = new Material("nome", "descricao", 2, 2, 2, imagem);
            Simulacao simulacao = new Simulacao();
            DefinirServidorController instance = new DefinirServidorController(simulacao);
            instance.novoServidor();
            instance.setMaterial(mat);
        } catch (IOException ex) {
            Logger.getLogger(DefinirServidorControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Test of setDados method, of class DefinirServidorController.
     */
    @Test
    public void testSetDados() {
        System.out.println("setDados");
        double massa = 20.0;
        double comprimento = 20.0;
        double largura = 20.0;
        double altura = 20.0;
        double carga = 20.0;
        Simulacao simulacao = new Simulacao();
        DefinirServidorController instance = new DefinirServidorController(simulacao);
        instance.novoServidor();
        instance.setDados(massa, comprimento, largura, altura, carga);
    }

    /**
     * Test of getDados method, of class DefinirServidorController.
     */
    @Test
    public void testGetDados() {
        try {
            System.out.println("getDados");
            Simulacao simulacao = new Simulacao();
            DefinirServidorController instance = new DefinirServidorController(simulacao);
            BufferedImage imagem = ImageIO.read(new File("imagemTeste.jpg"));
            Material mat = new Material("nome", "descricao", 2, 2, 2, imagem);
            instance.novoServidor();
            instance.setNome("nome");
            instance.setDados(20, 20, 20, 20, 20);
            instance.setMaterial(mat);
            String expResult = "Servidor: Nome: nome, "
                    + "Massa: 20.0kg, Comprimento: 20,00m, Largura: 20,00m, Carga: 20,00%, Altura: 20,00m, "
                    + "Material: Material: Nome: nome, Descrição: descricao, "
                    + "Coeficiente de Condução: 2,00, Coeficiente de Convecção: 2,00, Coeficiente de Radiação: 2,00.";
            String result = instance.getDados();
            assertEquals(expResult, result);
        } catch (IOException ex) {
            Logger.getLogger(DefinirServidorControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of adicionarServidor method, of class DefinirServidorController.
     */
    @Test
    public void testAdicionarServidor() {
        try {
            System.out.println("adicionarServidor");
            Simulacao simulacao = new Simulacao();
            DefinirServidorController instance = new DefinirServidorController(simulacao);
            double massa = 20.0;
            double comprimento = 20.0;
            double largura = 20.0;
            double altura = 20.0;
            double carga = 20.0;
            BufferedImage imagem = ImageIO.read(new File("imagemTeste.jpg"));
            Material mat = new Material("nome", "descricao", 2, 2, 2, imagem);
            instance.novoServidor();
            instance.setNome("nome");
            instance.setDados(massa, comprimento, largura, altura, carga);
            instance.setMaterial(mat);
            boolean expResult = true;
            boolean result = instance.adicionarServidor();
            assertEquals(expResult, result);
        } catch (IOException ex) {
            Logger.getLogger(DefinirServidorControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getListaMateriais method, of class DefinirServidorController.
     */
    @Test
    public void testGetListaMateriais() {
        try {
            System.out.println("getListaMateriais");
            Simulacao simulacao = new Simulacao();
            DefinirServidorController instance = new DefinirServidorController(simulacao);
            double massa = 20.0;
            double comprimento = 20.0;
            double largura = 20.0;
            double altura = 20.0;
            double carga = 20.0;
            BufferedImage imagem = ImageIO.read(new File("imagemTeste.jpg"));
            Material mat = new Material("nome", "descricao", 2, 2, 2, imagem);
            instance.novoServidor();
            instance.setNome("nome");
            instance.setDados(massa, comprimento, largura, altura, carga);
            instance.setMaterial(mat);
            List<Material> expResult=simulacao.getListaMateriaisExistentes();
            expResult.add(mat);
            List<Material> result = instance.getListaMateriais();
            assertEquals(expResult, result);
        } catch (IOException ex) {
            Logger.getLogger(DefinirServidorControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
