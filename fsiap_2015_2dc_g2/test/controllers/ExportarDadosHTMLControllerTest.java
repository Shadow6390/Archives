/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import model.ListaMateriaisParede;
import model.Material;
import model.Parede;
import model.Sala;
import model.Servidor;
import model.Simulacao;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author
 */
public class ExportarDadosHTMLControllerTest {

    private CorrerSimulacaoController csController;

    private Simulacao sim;
    private Sala sala;
    private Servidor serv;
    private Material mat;

    public ExportarDadosHTMLControllerTest() {
        sim = new Simulacao();
        sala = new Sala();
        sala.setHasTemperaturaVariation(false);
        sala.setTemperaturaExternaMinima(3);
        sala.setTemperaturaExternaMinima(9);
        sala.setTemperaturaExterior(3);
        sala.setTemperaturaInterior(15);
        sala.setTemperaturaInteriorAlvo(10);
        serv = new Servidor();
        serv.setNome("XPTO");
        serv.setMassa(30);
        serv.setComprimento(1);
        serv.setLargura(1);
        serv.setAltura(2);
        serv.setCarga(30);
        mat = new Material();
        mat.setCoeficienteConducao(0.5);
        mat.setCoeficienteConveccao(0.002);
        mat.setCoeficienteRadiacao(0.07);
        mat.setNome("Material XPTO");
        mat.setDescricao("Descricao XPTO");
        mat.setImagem(new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB));
        serv.setMaterial(mat);

        sala.addServidor(serv, new double[]{0.0, 0.0, 0.0}, 20);
        sala.addServidor(serv, new double[]{1.0, 0.0, 0.0}, 10);

        List<Parede> paredes = new ArrayList();
        Parede p = new Parede();
        p.setPosicao(new double[]{0.0, 0.0, 0.0});
        p.setAltura(3);
        p.setComprimento(7);
        p.setLargura(1);
        p.setOrientacaoParede(Parede.ORIENTACAO_HORIZONTAL);
        p.setTemperatura(5);

        ListaMateriaisParede lm = new ListaMateriaisParede();
        lm.adicionarMaterial(mat, 1d);
        p.setListaMateriais(lm);

        paredes.add(p);

        p = new Parede();
        p.setPosicao(new double[]{0.0, 1.0, 0.0});
        p.setAltura(3);
        p.setComprimento(1d);
        p.setLargura(5d);
        p.setOrientacaoParede(Parede.ORIENTACAO_VERTICAL);
        p.setTemperatura(5);

        lm = new ListaMateriaisParede();
        lm.adicionarMaterial(mat, 1d);
        p.setListaMateriais(lm);

        paredes.add(p);

        p = new Parede();
        p.setPosicao(new double[]{6.0, 0.0, 0.0});
        p.setAltura(3);
        p.setComprimento(7);
        p.setLargura(1);
        p.setOrientacaoParede(Parede.ORIENTACAO_HORIZONTAL);
        p.setTemperatura(5);

        lm = new ListaMateriaisParede();
        lm.adicionarMaterial(mat, 1d);
        p.setListaMateriais(lm);

        paredes.add(p);

        p = new Parede();
        p.setPosicao(new double[]{6.0, 1.0, 0.0});
        p.setAltura(3);
        p.setComprimento(1);
        p.setLargura(5);
        p.setOrientacaoParede(Parede.ORIENTACAO_VERTICAL);
        p.setTemperatura(5);

        lm = new ListaMateriaisParede();
        lm.adicionarMaterial(mat, 1d);
        p.setListaMateriais(lm);

        paredes.add(p);

        sala.setListaParedes(paredes);

        p = new Parede();
        p.setPosicao(new double[]{0.0, 0.0, 2.0});
        p.setAltura(1);
        p.setComprimento(7);
        p.setLargura(7);
        p.setOrientacaoParede(Parede.ORIENTACAO_PLANA);
        p.setTemperatura(5);

        lm = new ListaMateriaisParede();
        lm.adicionarMaterial(mat, 1d);
        p.setListaMateriais(lm);
        p.setTemperatura(5);

        sala.setTeto(p);

        sim.setSala(sala);

        csController = new CorrerSimulacaoController(sim);
    }

    /**
     * Test of setDados method, of class ExportarDadosHTMLController.
     */
    @Test
    public void testSetDados() {
        System.out.println("setDados");
        boolean materiais = true;
        boolean servidores = true;
        boolean sala = true;
        csController.setupSimulation();

        for (int i = 0; i < 50; i++) {
            //Corre durante um minuto.
            csController.setDeltaTime(60);
            csController.updateTemperatures();
            csController.updateTemperaturaExterna();
            csController.stepCPUDissipation();
            csController.stepTime();
        }
        ExportarDadosHTMLController instance
                = new ExportarDadosHTMLController(this.sim, this.csController);
        instance.setCaminho("test/controllers/", "test_export.html");
        instance.setDados(materiais, servidores, sala);
    }

    /**
     * Test of setCaminho method, of class ExportarDadosHTMLController.
     */
    @Test
    public void testSetCaminho() {
        System.out.println("setCaminho");
        ExportarDadosHTMLController instance
                = new ExportarDadosHTMLController(this.sim, this.csController);

        // Testar os diferentes sets e respetivas exceções.
        try {
            instance.setCaminho(null, null);
            assertTrue("O caminho não pode ser null.", false);
        } catch (IllegalArgumentException ex) {
        }

        try {
            instance.setCaminho(" ", null);
            assertTrue("O caminho não pode estar vazio.", false);
        } catch (IllegalArgumentException ex) {
        }

        try {
            instance.setCaminho("caminho", null);
            assertTrue("O nome não pode ser null.", false);
        } catch (IllegalArgumentException ex) {
        }
        try {
            instance.setCaminho("caminho", " ");
            assertTrue("O nome não pode estar vazio.", false);
        } catch (IllegalArgumentException ex) {
        }

        instance.setCaminho("test/controllers/", "test_export.html");

    }

}
