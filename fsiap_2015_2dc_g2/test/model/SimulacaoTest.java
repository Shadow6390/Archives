package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
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
 * @author jbraga
 */
public class SimulacaoTest {

    public SimulacaoTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test of getRegistoIdiomas method, of class Simulacao.
     */
    @Test
    public void testGetRegistoIdiomas() {
        System.out.println("getRegistoIdiomas");
        Simulacao instance = new Simulacao();
        RegistoIdiomas expResult = new RegistoIdiomas();
        RegistoIdiomas result = instance.getRegistoIdiomas();

        assertTrue("Os objetos deviam de ser iguais", result.toString().equals(expResult.toString()));
    }

    /**
     * Test of getRegistoMateriais method, of class Simulacao.
     */
    @Test
    public void testGetRegistoMateriais() {
        System.out.println("getRegistoMateriais");
        try {

            Simulacao instance = new Simulacao();
            RegistoMateriais rm = new RegistoMateriais();
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
            
            rm.setMateriais(lm);

            RegistoMateriais result = instance.getRegistoMateriais();
            assertTrue("Os objetos deviam de ser iguais", result.equals(rm));

        } catch (IOException ex) {
            Logger.getLogger(MaterialTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Test of setIdiomaAtual and getIdiomaAtual method, of class Simulacao.
     */
    @Test
    public void testSetAndGetIdiomaAtual() {
        System.out.println("getIdiomaAtual");
        Simulacao simulacao = new Simulacao();
        ResourceBundle expResult = ResourceBundle.getBundle("languages/language", new Locale("en", "GB"));

        try {
            Simulacao.setIdiomaAtual(null);
            assertTrue("Devia de ter sido lançada uma exceção devido ao idioma estar null", false);
        } catch (IllegalArgumentException ex) {
        }

        Simulacao.setIdiomaAtual(expResult);

        ResourceBundle result = Simulacao.getIdiomaAtual();

        assertTrue("Os objectos deverial de ser iguais.", result.equals(expResult));
    }

    /**
     * Test of validarIdioma method, of class Simulacao.
     */
    @Test
    public void testValidarIdioma() {
        System.out.println("validarIdioma");
        ResourceBundle portugues = ResourceBundle.getBundle("languages/language", new Locale("pt", "PT"));
        ResourceBundle inglesBritanico = ResourceBundle.getBundle("languages/language", new Locale("en", "GB"));
        Simulacao instance = new Simulacao();

        assertFalse("O idioma devia ser inválido pois é igual ao atual.", instance.validarIdioma(portugues));

        assertTrue("O idioma devia ser válido pois é diferente do atual.", instance.validarIdioma(inglesBritanico));
    }

    /**
     * Test of iniciarExportacao method, of class Simulacao.
     */
    @Test
    public void testIniciarExportacao() {
        System.out.println("iniciarExportacao");
        Simulacao instance = new Simulacao();

        assertTrue("Devia ter sido criada uma instância de GestorDados", instance.iniciarExportacao().getClass().toString().equals("class model.GestorDados"));
    }

    /**
     * Test of setSala method, of class Simulacao.
     */
    @Test
    public void testSetAndGetSala() {
        System.out.println("setSala");
        Simulacao sim = new Simulacao();
        Material m = sim.getListaMateriaisExistentes().get(0);
        Sala sala = new Sala(1, 1, 1, 20, 21, 22, 20, 24, m);
        sim.setSala(sala);
        assertTrue("As salas deveriam ser iguais", sim.getSala().equals(sala));
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getListaServidores method, of class Simulacao.
     */
    @Test
    public void testGetListaServidores() {
        System.out.println("getListaServidores");
        Servidor serv1 = new Servidor();
        serv1.setNome("S1");
        serv1.setMassa(40d);
        Servidor serv2 = new Servidor();
        serv2.setNome("S2");
        ArrayList<Servidor> list = new ArrayList();
        list.add(serv1);
        list.add(serv2);
        RegistoServidores registoServidores = new RegistoServidores(list);
        Simulacao instance = new Simulacao(registoServidores);
        assertTrue("Registos de Servidores deveriam ser iguais", list.equals(instance.getListaServidores()));
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getIndexOf method, of class Simulacao.
     */
    @Test
    public void testGetIndexOf() {
        System.out.println("getIndexOf");
        Servidor serv = null;
        Simulacao instance = new Simulacao();
        int expResult = -1;
        int result = instance.getIndexOf(serv);
        assertEquals(expResult, result);
    }

    /**
     * Test of registaAlteracao method, of class Simulacao.
     */
    @Test
    public void testRegistaAlteracao() {
        System.out.println("registaAlteracao");
        Servidor serv = null;
        int index = 0;
        Simulacao instance = new Simulacao();
        boolean expResult = false;
        boolean result = instance.registaAlteracao(serv, index);
        assertEquals(expResult, result);
    }

    /**
     * Test of iniciarImportacao method, of class Simulacao.
     */
    @Test
    public void testIniciarImportacao() {
        System.out.println("iniciarImportacao");
        Simulacao instance = new Simulacao();
        GestorDados result = instance.iniciarImportacao();
        assertTrue("An instance should have been created.", result!=null);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getListaMateriaisExistentes method, of class Simulacao.
     */
    @Test
    public void testGetListaMateriaisExistentes() {
        System.out.println("getListaMateriaisExistentes");
        Simulacao instance = new Simulacao();
        List<Material> expResult = new ArrayList();
        for (Material focus : instance.getRegistoMateriais().getMateriais()) {
            expResult.add(focus);
        }

        List<Material> result = instance.getListaMateriaisExistentes();
        assertEquals(expResult, result);
    }

    /**
     * Test of setupSimulation method, of class Simulacao.
     */
    @Test
    public void testSetupSimulation() {
        System.out.println("setupSimulation");
        Simulacao instance = new Simulacao();
        instance.setupSimulation();
    }

    /**
     * Test of getRegistoServidores method, of class Simulacao.
     */
    @Test
    public void testGetRegistoServidores() {
        System.out.println("getRegistoServidores");
        Servidor serv1 = new Servidor();
        serv1.setNome("S1");
        serv1.setMassa(40d);
        Servidor serv2 = new Servidor();
        serv2.setNome("S2");
        ArrayList<Servidor> list = new ArrayList();
        list.add(serv1);
        list.add(serv2);
        RegistoServidores registoServidores = new RegistoServidores(list);
        Simulacao instance = new Simulacao(registoServidores);
        assertTrue("Registos de Servidores deveriam ser iguais", registoServidores.equals(instance.getRegistoServidores()));
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

}
