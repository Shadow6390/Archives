package controllers;

import java.io.IOException;
import model.Simulacao;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 */
public class ImportarDadosControllerTest {

    public ImportarDadosControllerTest() {
    }

    /**
     * Test of class ImportarDadosController.
     */
    @Test
    public void testFullController() {
        System.out.println("fullController");
        Simulacao simulacao = new Simulacao();
        ImportarDadosController instance = new ImportarDadosController(simulacao);

        // Instanciar o gestor de dados.
        instance.iniciarImportacao();

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

        instance.setDados("test/controllers/", "non_binary_file");

        try {
            instance.importarDados();
        } catch (ClassNotFoundException | IOException ex) {
        }

        instance.setDados("test/controllers/", "binary_file");

        try {
            instance.importarDados();
        } catch (ClassNotFoundException | IOException ex) {
        }

    }

}
