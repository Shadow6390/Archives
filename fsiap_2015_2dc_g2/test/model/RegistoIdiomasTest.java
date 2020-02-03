package model;

import java.util.Locale;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 */
public class RegistoIdiomasTest {

    public RegistoIdiomasTest() {
    }

    /**
     * Test of toString method, of class RegistoIdiomas.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Simulacao simulacao = new Simulacao();
        RegistoIdiomas instance = new RegistoIdiomas();
        String expResult = "Lista de idiomas:\nPortuguês\nInglês\n";
        String result = instance.toString();
        
        assertTrue("As informações da lista deviam de ser semelhantes.", result.equals(expResult));
    }
    
    /**
     * Test of listaParaVetor method, of class RegistoIdiomas.
     */
    @Test
    public void testlistaParaVetor() {
        System.out.println("listaParaVetor");
        Simulacao simulacao = new Simulacao();
        RegistoIdiomas instance = new RegistoIdiomas();
        String[] expResult = {"Português", "Inglês"};
        String[] result = instance.listaParaVetor();
        
        assertArrayEquals("Os vetores deviam de ser semelhantes.", result, expResult);
    }

    /**
     * Test of getIdiomaPorDefeito method, of class RegistoIdiomas.
     */
    @Test
    public void testGetIdiomaPorDefeito() {
        System.out.println("getIdiomaPorDefeito");
        RegistoIdiomas instance = new RegistoIdiomas();
        Idioma expResult = new Idioma("uc12.idioma.portugues", new Locale("pt", "PT"));
        Idioma result = instance.getIdiomaPorDefeito();
        assertTrue("Os idiomas deveriam de ser iguais.", result.equals(expResult));
    }

    /**
     * Test of getIdioma method, of class RegistoIdiomas.
     */
    @Test
    public void testGetIdioma() {
        System.out.println("getIdioma");
        Simulacao simulacao = new Simulacao();
        Idioma portugues = new Idioma("uc12.idioma.portugues", new Locale("pt", "PT"));
        Idioma inglesBritanico = new Idioma("uc12.idioma.ingles", new Locale("en", "GB"));

        RegistoIdiomas instance = new RegistoIdiomas();

        assertTrue("O idioma retornado deveria de ser o português", instance.getIdioma(portugues.toString()).equals(portugues));

        assertTrue("O idioma retornado deveria de ser o inglês", instance.getIdioma(inglesBritanico.toString()).equals(inglesBritanico));
    }

}
