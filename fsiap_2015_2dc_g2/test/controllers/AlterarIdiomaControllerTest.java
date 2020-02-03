package controllers;

import model.Simulacao;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 */
public class AlterarIdiomaControllerTest {

    public AlterarIdiomaControllerTest() {
    }

    /**
     * Test of class AlterarIdiomaController.
     */
    @Test
    public void testFullController() {
        System.out.println("fullController");
        Simulacao simulacao = new Simulacao();
        AlterarIdiomaController instance = new AlterarIdiomaController(simulacao);

        String[] result = instance.iniciarAlteracaoIdioma();
        String[] expResult = {"Português", "Inglês"};

        assertArrayEquals("Os idiomas disponíveis deveriam ser os mesmos.", result, expResult);

        try {
            instance.selecionarIdioma("Português");
            assertTrue("Não deve ser possível selecionar o idioma igual ao atual.", false);
        } catch (IllegalArgumentException ex) {
        }

        assertTrue("Deve ser possível selecionar um idioma diferente do atual", instance.selecionarIdioma("Inglês"));

        instance.confirmarIdioma();

        assertTrue("A lingua atual deveria de ser inglesa", Simulacao.getIdiomaAtual().getLocale().toLanguageTag().equals("en-GB"));
    }

}
