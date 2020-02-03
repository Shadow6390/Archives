package controllers;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Simulacao;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 */
public class ExportarDadosControllerTest {

    public ExportarDadosControllerTest() {
    }

    /**
     * Test of class ExportarDadosController.
     */
    @Test
    public void testFullController() {
        System.out.println("fullController");
        Simulacao simulacao = new Simulacao();
        ExportarDadosController instance = new ExportarDadosController(simulacao);

        // Instanciar o gestor de dados.
        instance.iniciarExportacao();

        // Testar os diferentes sets e respetivas exceções.
        try {
            instance.setDados(null, null);
            assertTrue("O caminho não pode ser null.", false);
        } catch (IllegalArgumentException ex) {
        }

        try {
            instance.setDados(" ", null);
            assertTrue("O caminho não pode estar vazio.", false);
        } catch (IllegalArgumentException ex) {
        }

        try {
            instance.setDados("caminho", null);
            assertTrue("O nome não pode ser null.", false);
        } catch (IllegalArgumentException ex) {
        }

        try {
            instance.setDados("caminho", " ");
            assertTrue("O nome não pode estar vazio.", false);
        } catch (IllegalArgumentException ex) {
        }

        try {
            instance.setDados("caminho", "nome");
            assertTrue("O caminho e o nome devem ser válidos.", false);
        } catch (IllegalArgumentException ex) {
        }

        instance.setDados("test/controllers/", "test_export.bin");

        try {
            instance.exportarDados();
        } catch (IOException ex) {
        }
        
        assertTrue("O ficheiro deveria de ter sido criado", new File("test/controllers/test_export.bin").exists());
        new File("test/controllers/test_export.bin").delete();
    }

}
