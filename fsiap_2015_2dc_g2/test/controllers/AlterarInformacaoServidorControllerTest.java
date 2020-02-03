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
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import model.Material;
import model.MaterialTest;
import model.RegistoMateriais;
import model.RegistoServidores;
import model.Servidor;
import model.Simulacao;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Diogo
 */
public class AlterarInformacaoServidorControllerTest {

    Servidor serv, serv2;
    Material mat;
    Simulacao sim;
    AlterarInformacaoServidorController instance;

    public AlterarInformacaoServidorControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        mat = new Material();
        mat.setCoeficienteConducao(0.005);
        mat.setCoeficienteConveccao(0.015);
        mat.setCoeficienteRadiacao(0.025);
        mat.setNome("Material Teste");
        mat.setDescricao("O material de teste.");
        try {
            mat.setImagem(ImageIO.read(new File("imagemTeste.jpg")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        serv = new Servidor();
        serv.setNome("Servidor XPTO");
        serv.setMassa(50.0);
        serv.setAltura(2);
        serv.setComprimento(1);
        serv.setLargura(1);
        serv.setMaterial(mat);
        serv2 = new Servidor();
        serv2.setNome("Servidor XPTO2");
        serv2.setMassa(50.0);
        serv2.setAltura(2);
        serv2.setComprimento(1);
        serv2.setLargura(1);
        serv2.setMaterial(mat);
        ArrayList<Servidor> list = new ArrayList();
        list.add(serv);
        list.add(serv2);
        RegistoServidores reg = new RegistoServidores(list);
        sim = new Simulacao(reg);
        instance = new AlterarInformacaoServidorController(sim);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getListaServidores method, of class
     * AlterarInformacaoServidorController.
     */
    @Test
    public void testGetListaServidores() {
        System.out.println("getListaServidores");
        ArrayList<Servidor> expResult = new ArrayList();
        expResult.add(serv);
        expResult.add(serv2);
        ArrayList<Servidor> result = instance.getListaServidores();
        assertEquals(expResult, result);
    }

    /**
     * Test of selectServidor method, of class
     * AlterarInformacaoServidorController.
     */
    @Test
    public void testSelectServidor() {
        System.out.println("selectServidor");
        instance.selectServidor(serv);
    }

    /**
     * Test of getListaMateriais method, of class
     * AlterarInformacaoServidorController.
     */
    @Test
    public void testGetListaMateriais() {
        System.out.println("getListaMateriais");
        try {

            ArrayList<Material> lm = new ArrayList<>();
            BufferedImage imagem1 = ImageIO.read(new File("glass_texture.png"));
            BufferedImage imagem2 = ImageIO.read(new File("wood_texture.jpg"));
            BufferedImage imagem3 = ImageIO.read(new File("lead_texture.jpg"));
            BufferedImage imagem4 = ImageIO.read(new File("gold_texture.jpg"));
            Material m1 = new Material("Glass", "A glass texture", 0.79, 0.79, 0.92, imagem1);
            Material m2 = new Material("Wood", "A wood texture", 0.13, 0.13, 0.87, imagem2);
            Material m3 = new Material("Lead", "A gray-lead texture", 35.3, 0.028, 0.28, imagem3);
            Material m4 = new Material("Polished Gold", "A polished gold texture", 317, 0.002, 0.02, imagem4);

            lm.add(m1);
            lm.add(m2);
            lm.add(m3);
            lm.add(m4);

            assertTrue("Os objetos deviam de ser iguais", lm.equals(instance.getListaMateriais()));

        } catch (IOException ex) {
            Logger.getLogger(MaterialTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getNome method, of class AlterarInformacaoServidorController.
     */
    @Test
    public void testGetNome() {
        System.out.println("getNome");
        instance.selectServidor(serv);
        String expResult = "Servidor XPTO";
        String result = instance.getNome();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMaterial method, of class AlterarInformacaoServidorController.
     */
    @Test
    public void testGetMaterial() {
        System.out.println("getMaterial");
        instance.selectServidor(serv);
        Material expResult = mat;
        Material result = instance.getMaterial();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMassa method, of class AlterarInformacaoServidorController.
     */
    @Test
    public void testGetMassa() {
        System.out.println("getMassa");
        instance.selectServidor(serv);
        double expResult = 50.0;
        double result = instance.getMassa();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getDimensoes method, of class
     * AlterarInformacaoServidorController.
     */
    @Test
    public void testGetDimensoes() {
        System.out.println("getDimensoes");
        instance.selectServidor(serv);
        double[] expResult = new double[3];
        expResult[0] = 1;
        expResult[1] = 1;
        expResult[2] = 2;
        double[] result = instance.getDimensoes();
        String a = Arrays.toString(expResult);
        String b = Arrays.toString(result);
        assertEquals(a, b);
    }

    /**
     * Test of setNome method, of class AlterarInformacaoServidorController.
     */
    @Test
    public void testSetNome() {
        System.out.println("setNome");
        String nome = "Servidor LOL";
        instance.selectServidor(serv);
        instance.setNome(nome);
        assertEquals(nome, instance.getNome());
    }

    /**
     * Test of setNome method, of class AlterarInformacaoServidorController. In
     * this case, the name inserted is invalid.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetNomeInvalido() {
        System.out.println("setNomeInválido");
        String nome = "";
        instance.selectServidor(serv);
        instance.setNome(nome);
        assertEquals(nome, instance.getNome());
    }

    /**
     * Test of setMaterial method, of class AlterarInformacaoServidorController.
     */
    @Test
    public void testSetMaterial() {
        System.out.println("setMaterial");
        Material mat = new Material();
        mat.setCoeficienteConducao(0.005);
        mat.setCoeficienteConveccao(0.015);
        mat.setCoeficienteRadiacao(0.025);
        mat.setNome("Material Teste2");
        mat.setDescricao("O material de teste.");
        try {
            mat.setImagem(ImageIO.read(new File("imagemTeste.jpg")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        instance.selectServidor(serv);
        instance.setMaterial(mat);
        assertEquals(mat, instance.getMaterial());
    }

    /**
     * Test of setMassa method, of class AlterarInformacaoServidorController.
     */
    @Test
    public void testSetMassa() {
        System.out.println("setMassa");
        double massa = 5.0;
        instance.selectServidor(serv);
        instance.setMassa(massa);
        assertEquals(instance.getMassa(), massa, 0.0);
    }

    /**
     * Test of setMassa method, of class AlterarInformacaoServidorController. In
     * this test, the mass introduced is less than 0.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetMassaInvalido() {
        System.out.println("setMassaInvalido");
        double massa = -55.0;
        instance.selectServidor(serv);
        instance.setMassa(massa);
    }

    /**
     * Test of setDimensoes method, of class
     * AlterarInformacaoServidorController.
     */
    @Test
    public void testSetDimensoes() {
        System.out.println("setDimensoes");
        double[] dim = new double[3];
        dim[0] = 4d;
        dim[1] = 2d;
        dim[2] = 3d;
        instance.selectServidor(serv);
        instance.setDimensoes(dim);
        double[] expResult = new double[3];
        expResult[0] = 4d;
        expResult[1] = 2d;
        expResult[2] = 3d;
        assertEquals(Arrays.toString(instance.getDimensoes()), Arrays.toString(expResult));
    }

    /**
     * Test of setDimensoes method, of class
     * AlterarInformacaoServidorController. In this case, only one dimension is
     * inserted.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetDimensoesInvalido() {
        System.out.println("setDimensoesInvalido");
        double[] dim = new double[1];
        dim[0] = 4d;
        instance.selectServidor(serv);
        instance.setDimensoes(dim);
    }

    /**
     * Test of registaAlteracao method, of class
     * AlterarInformacaoServidorController.
     */
    @Test
    public void testRegistaAlteracao() {
        System.out.println("registaAlteracao");
        instance.selectServidor(serv);
        instance.setMassa(40d);
        instance.setCarga(30d);
        boolean expResult = true;
        boolean result = instance.registaAlteracao();
        assertEquals(expResult, result);
    }

    /**
     * Test of registaAlteracao method, of class
     * AlterarInformacaoServidorController. In this test, the name of the server
     * is changed to ane xisting one.
     */
    @Test
    public void testRegistaAlteracaoInvalida() {
        System.out.println("registaAlteracaoInvalida");
        instance.selectServidor(serv);
        instance.setMassa(40d);
        instance.setCarga(30d);
        instance.setNome("Servidor XPTO2");
        boolean expResult = false;
        boolean result = instance.registaAlteracao();
        assertEquals(expResult, result);
    }

}
