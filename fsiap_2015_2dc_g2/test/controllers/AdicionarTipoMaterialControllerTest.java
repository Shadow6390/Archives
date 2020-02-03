/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import model.Simulacao;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author
 */
public class AdicionarTipoMaterialControllerTest {

    public AdicionarTipoMaterialControllerTest() {
    }

    /**
     * Test of getListaMateriaisString method, of class
     * AdicionarTipoMaterialController.
     */
    @Test
    public void testGetListaMateriaisString() {
        System.out.println("getListaMateriaisString");
        Simulacao simulacao = new Simulacao();
        AdicionarTipoMaterialController instance = new AdicionarTipoMaterialController(simulacao);
        ArrayList<String> expResult = new ArrayList<>();
        expResult.add("Glass");
        expResult.add("Wood");
        expResult.add("Lead");
        expResult.add("Polished Gold");
        ArrayList<String> result = instance.getListaMateriaisString();
        assertEquals(expResult, result);
    }

    /**
     * Test of novoMaterial method, of class AdicionarTipoMaterialController.
     */
    @Test
    public void testNovoMaterial() {

        try {
            System.out.println("novoMaterial");
            String nome = "nome";
            String descricao = "descricao";
            double coeficiente = 20.0;
            BufferedImage imagem = ImageIO.read(new File("imagemTeste.jpg"));
            Simulacao simulacao = new Simulacao();
            AdicionarTipoMaterialController instance = new AdicionarTipoMaterialController(simulacao);
            boolean expResult = true;
            boolean result = instance.novoMaterial(nome, descricao, coeficiente, coeficiente, coeficiente, imagem);
            assertEquals(expResult, result);
        } catch (IOException ex) {
            Logger.getLogger(AdicionarTipoMaterialControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getInfo method, of class AdicionarTipoMaterialController.
     */
    @Test
    public void testGetInfo() {
        try {
            System.out.println("getInfo");
            Simulacao simulacao = new Simulacao();
            AdicionarTipoMaterialController instance = new AdicionarTipoMaterialController(simulacao);
            String nome = "nome";
            String descricao = "descricao";
            double coeficiente = 20.0;
            BufferedImage imagem = ImageIO.read(new File("imagemTeste.jpg"));
            boolean material = instance.novoMaterial(nome, descricao, coeficiente, coeficiente, coeficiente, imagem);
            String expResult = "Material: Nome: nome, Descrição: descricao, Coeficiente de Condução: 20,00, Coeficiente de Convecção: 20,00, Coeficiente de Radiação: 20,00.";
            String result = instance.getInfo();
            assertEquals(expResult, result);
        } catch (IOException ex) {
            Logger.getLogger(AdicionarTipoMaterialControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Test of RegistaMaterial method, of class AdicionarTipoMaterialController.
     */
    @Test
    public void testRegistaMaterial() {
        try {
            System.out.println("RegistaMaterial");
            Simulacao simulacao = new Simulacao();
            AdicionarTipoMaterialController instance = new AdicionarTipoMaterialController(simulacao);
            String nome = "nome";
            String descricao = "descricao";
            double coeficiente = 20.0;
            BufferedImage imagem = ImageIO.read(new File("imagemTeste.jpg"));
            boolean material = instance.novoMaterial(nome, descricao, coeficiente, coeficiente, coeficiente, imagem);
            boolean expResult = true;
            boolean result = instance.RegistaMaterial();
            assertEquals(expResult, result);
        } catch (IOException ex) {
            Logger.getLogger(AdicionarTipoMaterialControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
