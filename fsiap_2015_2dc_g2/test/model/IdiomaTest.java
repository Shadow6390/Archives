package model;

import java.util.Locale;
import java.util.ResourceBundle;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 */
public class IdiomaTest {

    public IdiomaTest() {
    }

    /**
     * Test of GetIdioma & setIdioma method, of class Idioma.
     */
    @Test
    public void testGetAndSetIdioma() {
        System.out.println("setIdioma");
        Idioma instance = new Idioma("uc12.idioma.portugues", new Locale("pt", "PT"));

        String expResult = "uc12.idioma.portugues";
        String result = instance.getIdioma();

        assertTrue("Os idiomas devem ser idênticos.", result.equals(expResult));

        try {
            instance.setIdioma("");
            assertTrue("Devia ter sido lançada uma excepção devido ao idioma estar vazio", false);
        } catch (IllegalArgumentException ex) {
        }

        try {
            instance.setIdioma(null);
            assertTrue("Devia ter sido lançada uma excepção devido ao idioma estar a null", false);
        } catch (IllegalArgumentException ex) {
        }
    }

    /**
     * Test of getLocalizacao & setLocalizacao method, of class Idioma.
     */
    @Test
    public void testGetAndSetLocalizacao() {
        System.out.println("setLocalizacao");
        Idioma instance = new Idioma("uc12.idioma.portugues", new Locale("pt", "PT"));

        Locale expResult = new Locale("pt", "PT");
        Locale result = instance.getLocalizacao();

        assertTrue("As localizações devem ser idênticas.", result.equals(expResult));

        try {
            instance.setLocalizacao(null);
            assertTrue("Devia ter sido lançada uma excepção devido a localização estar a null", false);
        } catch (IllegalArgumentException ex) {
        }
    }

    /**
     * Test of equals method, of class Idioma.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object sameObject = new Idioma("uc12.idioma.portugues", new Locale("pt", "PT"));
        Object diffObject = new Idioma("uc12.idioma.portugues", new Locale("en", "GB"));
        Idioma instance = new Idioma("uc12.idioma.portugues", new Locale("pt", "PT"));

        assertTrue("Os objetos deviam de ser iguais.", instance.equals(sameObject));

        assertFalse("Os objetos deviam de ser diferentes.", instance.equals(diffObject));
    }

    /**
     * Test of toString method, of class Idioma.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Simulacao simulacao = new Simulacao();
        Idioma instance = new Idioma("uc12.idioma.portugues", new Locale("pt", "PT"));
        String expResult = "Português";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of importarIdioma method, of class Idioma.
     */
    @Test
    public void testImportarIdioma() {
        System.out.println("importarIdioma");
        Idioma instance = new Idioma("uc12.idioma.portugues", new Locale("pt", "PT"));
        ResourceBundle expResult = ResourceBundle.getBundle("languages/language", new Locale("pt", "PT"));
        ResourceBundle result = instance.importarIdioma();
        assertEquals(expResult, result);
    }

}
