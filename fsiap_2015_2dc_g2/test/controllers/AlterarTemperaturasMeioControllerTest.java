package controllers;

import java.awt.image.BufferedImage;
import model.Material;
import model.Sala;
import model.Servidor;
import model.Simulacao;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 */
public class AlterarTemperaturasMeioControllerTest {

    private Sala sala;

    public AlterarTemperaturasMeioControllerTest() {

        this.sala = new Sala();
    }

    /**
     * Test of getTemperaturaInteriorAlvo method and setTemperaturaInteriorAlvo
     * method, of class AlterarTemperaturasMeioController.
     */
    @Test
    public void testGetTemperaturaInteriorAlvo() {
        System.out.println("getSetTemperaturaInteriorAlvo");
        double expResult = 20.0;
        Simulacao simulacao = new Simulacao();
        simulacao.setSala(sala);
        AlterarTemperaturasMeioController instance = new AlterarTemperaturasMeioController(simulacao);
        instance.setTemperaturaInteriorAlvo(expResult);
        double result = instance.getTemperaturaInteriorAlvo();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getTemperaturaExterior method and setTemperaturaExterior method,
     * of class AlterarTemperaturasMeioController.
     */
    @Test
    public void testGetSetTemperaturaExterior() {
        System.out.println("getSetTemperaturaExterior");
        double expResult = 20.0;
        Simulacao simulacao = new Simulacao();
        simulacao.setSala(sala);
        AlterarTemperaturasMeioController instance = new AlterarTemperaturasMeioController(simulacao);
        instance.setTemperaturaExterior(expResult);
        double result = instance.getTemperaturaExterior();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getTemperaturaExteriorMinima method and
     * setTemperaturaExteriorMinima method, of class
     * AlterarTemperaturasMeioController.
     */
    @Test
    public void testGetSetTemperaturaExteriorMinima() {
        System.out.println("getSetTemperaturaExteriorMinima");
        double expResult = 10.0;
        Simulacao simulacao = new Simulacao();
        simulacao.setSala(sala);
        AlterarTemperaturasMeioController instance = new AlterarTemperaturasMeioController(simulacao);
        instance.setTemperaturaExteriorMinima(expResult);
        double result = instance.getTemperaturaExteriorMinima();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getTemperaturaExteriorMaxima method and
     * setTemperaturaExteriorMaxima method, of class
     * AlterarTemperaturasMeioController.
     */
    @Test
    public void testGetSetTemperaturaExteriorMaxima() {
        System.out.println("getSetTemperaturaExteriorMaxima");
        double expResult = 20.0;
        Simulacao simulacao = new Simulacao();
        simulacao.setSala(sala);
        AlterarTemperaturasMeioController instance = new AlterarTemperaturasMeioController(simulacao);
        instance.setTemperaturaExteriorMaxima(expResult);
        double result = instance.getTemperaturaExteriorMaxima();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setTemperaturaInterior method and getTemperaturaInterior method,
     * of class AlterarTemperaturasMeioController.
     */
    @Test
    public void testGetSetTemperaturaInterior() {
        System.out.println("getSetTemperaturaInterior");
        double expResult = 20.0;
        Simulacao simulacao = new Simulacao();
        simulacao.setSala(sala);
        AlterarTemperaturasMeioController instance = new AlterarTemperaturasMeioController(simulacao);
        instance.setTemperaturaInterior(expResult);
        double result = instance.getTemperaturaInterior();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setHasTemperaturaVariation method, of class
     * AlterarTemperaturasMeioController.
     */
    @Test
    public void testSetHasTemperaturaVariation() {
        System.out.println("setHasTemperaturaVariation");
        boolean value = true;
        Simulacao simulacao = new Simulacao();
        simulacao.setSala(sala);
        AlterarTemperaturasMeioController instance = new AlterarTemperaturasMeioController(simulacao);
        instance.setHasTemperaturaVariation(value);
    }

    /**
     * Test of hasTemperaturaVariation method, of class
     * AlterarTemperaturasMeioController.
     */
    @Test
    public void testHasTemperaturaVariation() {
        System.out.println("hasTemperaturaVariation");
        Simulacao simulacao = new Simulacao();
        simulacao.setSala(sala);
        AlterarTemperaturasMeioController instance = new AlterarTemperaturasMeioController(simulacao);
        boolean expResult = true;
        instance.setHasTemperaturaVariation(expResult);
        boolean result = instance.hasTemperaturaVariation();
        assertEquals(expResult, result);
    }

    /**
     * Test of valida method, of class AlterarTemperaturasMeioController.
     */
    @Test
    public void testValida() {
        System.out.println("valida");
        Simulacao simulacao = new Simulacao();
        Material mat = new Material();
        mat.setCoeficienteConducao(0.5);
        mat.setCoeficienteConveccao(0.002);
        mat.setCoeficienteRadiacao(0.07);
        mat.setNome("Material XPTO");
        mat.setDescricao("Descricao XPTO");
        mat.setImagem(new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB));
        Sala sala = new Sala(20, 20, 20, 20, 20, 20, 20, 20, mat);
        simulacao.setSala(sala);

        AlterarTemperaturasMeioController instance = new AlterarTemperaturasMeioController(simulacao);
        boolean expResult = true;
        boolean result = instance.valida();
        assertEquals(expResult, result);
    }

    /**
     * Test of alterarSala method, of class AlterarTemperaturasMeioController.
     */
    @Test
    public void testAlterarSala() {
        System.out.println("alterarSala");
        Simulacao simulacao = new Simulacao();
        simulacao.setSala(sala);
        AlterarTemperaturasMeioController instance = new AlterarTemperaturasMeioController(simulacao);
        instance.alterarSala();
    }

}
