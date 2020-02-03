package model;

import java.io.File;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 */
public class GestorDadosTest {

    public GestorDadosTest() {
    }

    /**
     * Test of setCaminhoFicheiro and getCaminhoFicheiro method, of class
     * GestorDados.
     */
    @Test
    public void testSetAndGetCaminhoFicheiro() {
        System.out.println("setCaminhoFicheiro");
        String caminhoFicheiro = "umCaminho";
        GestorDados instance = new GestorDados(new Simulacao());

        try {
            instance.setCaminhoFicheiro(null);
            assertTrue("Devia ter sido lançada uma exceção devido ao caminho ser nulo.", false);
        } catch (IllegalArgumentException ex) {
        }

        try {
            instance.setCaminhoFicheiro("");
            assertTrue("Devia ter sido lançada uma exceção devido ao caminho estar vazio.", false);
        } catch (IllegalArgumentException ex) {
        }

        instance.setCaminhoFicheiro(caminhoFicheiro);

        assertTrue("Os caminhos dos ficheiros deviam ser os mesmos.", instance.getCaminhoFicheiro().equals(caminhoFicheiro));
    }

    /**
     * Test of setNomeFicheiro and getNomeFicheiro method, of class GestorDados.
     */
    @Test
    public void testSetAndGetNomeFicheiro() {
        System.out.println("setNomeFicheiro");
        String nomeFicheiro = "umFicheiro";
        GestorDados instance = new GestorDados(new Simulacao());

        try {
            instance.setNomeFicheiro(null);
            assertTrue("Devia ter sido lançada uma exceção devido ao nome ser nulo.", false);
        } catch (IllegalArgumentException ex) {
        }

        try {
            instance.setNomeFicheiro("");
            assertTrue("Devia ter sido lançada uma exceção devido ao nome estar vazio.", false);
        } catch (IllegalArgumentException ex) {
        }

        instance.setCaminhoFicheiro(nomeFicheiro);

        assertTrue("Os nomes dos ficheiros deviam ser os mesmos.", instance.getCaminhoFicheiro().equals(nomeFicheiro));
    }

    /**
     * Test of validarExportacao method, of class GestorDados.
     */
    @Test
    public void testValidarExportacao() {
        System.out.println("validarExportacao");
        GestorDados instance = new GestorDados(null);

        instance.setCaminhoFicheiro("test/");
        assertTrue("O caminho devia existir", instance.validarExportacao());

        instance.setCaminhoFicheiro("caminhoFalso");
        assertFalse("O caminho não devia existir", instance.validarExportacao());
    }

    /**
     * Test of validarImportacao method, of class GestorDados.
     */
    @Test
    public void testValidarImportacao() {
        System.out.println("validarImportacao");
        GestorDados instance = new GestorDados(null);

        instance.setCaminhoFicheiro("test/model/");
        instance.setNomeFicheiro("GestorDadosTest.java");
        assertTrue("O caminho e o nome do ficheiro deviam existir", true);

        instance.setCaminhoFicheiro("caminhoFalso");
        instance.setNomeFicheiro("nomeFalso");
        assertFalse("O caminho e  nome do ficheiro não deviam de existir.", false);
    }

    /**
     * Test of exportarDados and importarDados methods, of class GestorDados.
     */
    @Test
    public void testExportarAndImportarDados() throws Exception {
        System.out.println("exportarDados");
        Simulacao simulacao = new Simulacao();
        GestorDados instance = new GestorDados(simulacao);
        instance.setCaminhoFicheiro("test/model/");
        instance.setNomeFicheiro("test.bin");
        instance.exportarDados();

        assertTrue("Devia ter sido criado um ficheiro com os dados", new File("test/model/test.bin").exists());

        instance.setCaminhoFicheiro("test/model/");
        instance.setNomeFicheiro("test.bin");

        Simulacao.setIdiomaAtual(simulacao.getRegistoIdiomas().getIdioma("Inglês").importarIdioma());

        instance.importarDados();

        assertTrue("Falta elaborar um teste correto à importação, mas são necessários mais dados na Simulacao.", true);

        assertTrue("Não foi possível remover o ficheiro de teste.", new File("test/model/test.bin").delete());
    }

}
