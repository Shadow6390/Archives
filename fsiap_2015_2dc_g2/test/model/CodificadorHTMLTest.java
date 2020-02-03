/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controllers.CorrerSimulacaoController;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author
 */
public class CodificadorHTMLTest {

    private CorrerSimulacaoController csController;

    private Simulacao sim;
    private Sala sala;
    private Servidor serv;
    private Material mat;

    public CodificadorHTMLTest() {
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
        sim.getRegistoServidores().adicionarServidor(serv);
        sim.setSala(sala);
        csController = new CorrerSimulacaoController(sim);
    }

    /**
     * Test of getCaminhoFicheiro method and setCaminhoFicheiro method, of class
     * CodificadorHTML.
     */
    @Test
    public void testGetSetCaminhoFicheiro() {
        System.out.println("getSetCaminhoFicheiro");
        CodificadorHTML instance = new CodificadorHTML(this.sim, this.csController);
        String caminhoFicheiro = "test/controllers/";

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
     * Test of getNomeFicheiro method and setNomeFicheiro method, of class
     * CodificadorHTML.
     */
    @Test
    public void testGetSetNomeFicheiro() {
        System.out.println("getSetNomeFicheiro");
        String nomeFicheiro = "test_export.html";
        CodificadorHTML instance = new CodificadorHTML(this.sim, this.csController);
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
     * Test of validarExportacao method, of class CodificadorHTML.
     */
    @Test
    public void testValidarExportacao() {
        System.out.println("validarExportacao");
        CodificadorHTML instance = new CodificadorHTML(this.sim, this.csController);
        instance.setCaminhoFicheiro("test/");
        assertTrue("O caminho devia existir", instance.validarExportacao());

        instance.setCaminhoFicheiro("caminhoFalso");
        assertFalse("O caminho não devia existir", instance.validarExportacao());
    }

    /**
     * Test of abrirHTML method, of class CodificadorHTML.
     */
    @Test
    public void testAbrirHTML() {
        System.out.println("abrirHTML");
        String nomeFich = "Dados";
        String expResult = "<!DOCTYPE html>"
                + "<html>\n"
                + "\t<head>\n"
                + "\t\t<title>" + nomeFich + "</title>\n"
                + "\t\t<meta charset='utf-8'>\n"
                + "\t</head>\n"
                + "\t<body>\n";
        CodificadorHTML instance = new CodificadorHTML(this.sim, this.csController);
        String result = instance.abrirHTML(nomeFich);
        assertEquals(expResult, result);
    }

    /**
     * Test of adicionarDados method, of class CodificadorHTML.
     */
    @Test
    public void testAdicionarDados() {
        System.out.println("adicionarDados");

        this.csController.setupSimulation();
        int i;

        for (i = 0; i < 50; i++) {
            //Corre durante um minuto.
            this.csController.setDeltaTime(60);
            this.csController.updateTemperatures();
            this.csController.stepTime();
        }

        StringBuilder string = new StringBuilder();

        string.append("\t\t<h3>Dados da Simulação</h3>\n");
        string.append("\t\t<p>Quantidade de Energia necessária para refrigerar a sala à temperatura: ").append(String.format("%.3f", csController.getRefrigerationCost())).append(" J.</p>\n");
        string.append("\t\t<p>Temperatura alvo da Sala: ").append(this.sim.getSala().getTemperaturaInteriorAlvo()).append("°C.</p>\n");
        string.append("\t\t<p>Temperatura da Sala:").append(String.format("%.3f", this.sim.getSala().getTemperaturaInterior())).append("°C.</p>\n");
        string.append("\t\t<p>Tempo: ").append(csController.stepTime()).append(" minutos.</p>\n");
        string.append("\t\t<p>Tipos de Servidores: ").append(1).append("</p>\n");
        string.append("\t\t<p>Quantidade de Servidores na sala: ").append(2).append("</p>\n");

        String expResult = string.toString();
        CodificadorHTML instance = new CodificadorHTML(this.sim, this.csController);
        String result = instance.adicionarDados();
        assertEquals(expResult, result);
    }

    /**
     * Test of adicionarDadosMateriais method, of class CodificadorHTML.
     */
    @Test
    public void testAdicionarDadosMateriais() {
        try {
            System.out.println("adicionarDadosMateriais");
            Simulacao simulacao = new Simulacao();
            CodificadorHTML instance = new CodificadorHTML(simulacao);
            BufferedImage image = ImageIO.read(new File("imagemTeste.jpg"));
            Material material = new Material("nome", "descricao", 20.0, 20.0, 20.0, image);
            Material material2 = new Material("nome2", "descricao2", 20.0, 20.0, 20.0, image);
            simulacao.getRegistoMateriais().getMateriais().add(material);
            simulacao.getRegistoMateriais().getMateriais().add(material2);
            
            StringBuilder string = new StringBuilder();

            string.append("\t\t<hr>\n");
            string.append("\t\t<h3>Dados dos materiais usados na Simulação</h3>\n");

            for (Material mat : simulacao.getListaMateriaisExistentes()) {
                string.append("\t\t<p>Dados do material: ").append(mat.toString()).append("</p>\n");
            }

            String expResult = string.toString();

            String result = instance.adicionarDadosMateriais();
            assertEquals(expResult, result);
        } catch (IOException ex) {
            Logger.getLogger(MaterialTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of adicionarDadosServidores method, of class CodificadorHTML.
     */
    @Test
    public void testAdicionarDadosServidores() {
        CodificadorHTML instance = new CodificadorHTML(this.sim, this.csController);
        String result = instance.adicionarDadosServidores();
        
        StringBuilder string = new StringBuilder();

        string.append("\t\t<hr>\n");
        string.append("\t\t<h3>Dados dos servidores usados na Simulação</h3>\n");

        List<Servidor> servidores = this.sim.getListaServidores();
        for (Servidor servidor : servidores) {
            string.append("\t\t<p>Dados do servidor: ").append(servidor.toString()).append("</p>\n");
        }

        String expResult = string.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of adicionarDadosSala method, of class CodificadorHTML.
     */
    @Test
    public void testAdicionarDadosSala() {
        System.out.println("adicionarDadosSala");
        CodificadorHTML instance = new CodificadorHTML(this.sim, this.csController);
        StringBuilder string = new StringBuilder();

        string.append("\t\t<hr>\n");
        string.append("\t\t<h3>Dados da sala usada na Simulação</h3>\n");
        string.append("\t\t<ul>\n");

        // Informações da sala
        Sala sala = this.sim.getSala();
        string.append("\t\t\t<li>Comprimento: ").append(sala.getComprimento()).append(" m</li>\n");
        string.append("\t\t\t<li>Largura: ").append(sala.getLargura()).append(" m</li>\n");
        string.append("\t\t\t<li>Altura: ").append(sala.getAltura()).append(" m</li>\n");
        string.append("\t\t\t<li>Temperatura Exterior: ").append(sala.getTemperaturaExterior()).append(" ºC</li>\n");

        if (sala.hasTemperaturaVariation()) {
            string.append("\t\t\t<li>Temperatura Externa Miníma: ").append(sala.getTemperaturaExternaMinima()).append(" ºC</li>\n");
            string.append("\t\t\t<li>Temperatura Externa Máxima: ").append(sala.getTemperaturaExternaMaxima()).append(" ºC</li>\n");
        }

        // Informações do teto
        Parede teto = sala.getTeto();
        string.append("\t\t\t<li>Teto:\n");
        string.append("\t\t\t\t<ul>\n");
        string.append("\t\t\t\t\t<li>Espesura: ").append(teto.getProfundidade()).append(" m</li>\n");
        string.append("\t\t\t\t\t<li>Resistência: ").append(String.format("%.3e", teto.getResistencia(null))).append(" Ω</li>\n");
        string.append("\t\t\t\t</ul>\n");
        string.append("\t\t\t\t<ul>\n");
        string.append("\t\t\t</li>\n");

        // Informações sobre cada uma das paredes
        string.append("\t\t\t<li>Paredes:\n");
        string.append("\t\t\t<ul>\n");
        int i = 1;
        for (Parede parede : sala.getListaParedes()) {
            string.append("\t\t\t\t<li>").append(i++).append("ª Parede:\n");
            string.append("\t\t\t\t\t<ul>\n");
            
            string.append("\t\t\t\t\t\t<li>Espessura: ").append(parede.getProfundidade()).append(" m</li>\n");
            string.append("\t\t\t\t\t\t<li>Resistência: ").append(String.format("%.3e", parede.getResistencia(null))).append(" Ω</li>\n");
                        
            string.append("\t\t\t\t\t</ul>\n");
            string.append("\t\t\t\t</li>\n");
        }

        string.append("\t\t\t</ul>\n");
        string.append("\t\t\t</li>\n");

        string.append("\t\t</ul>\n");

        String expResult= string.toString();
        String result = instance.adicionarDadosSala();
        assertEquals(expResult, result);
    }

    /**
     * Test of fecharHTML method, of class CodificadorHTML.
     */
    @Test
    public void testFecharHTML() {
        System.out.println("fecharHTML");
        CodificadorHTML instance = new CodificadorHTML(this.sim, this.csController);
        String expResult = "\t</body>\n"
                + "</html>\n";
        String result = instance.fecharHTML();
        assertEquals(expResult, result);
    }

    /**
     * Test of exportarDados method, of class CodificadorHTML.
     */
    @Test
    public void testExportarDados() {
        System.out.println("exportarDados");
        boolean materiais = true;
        boolean servidores = true;
        boolean sala = true;
        this.csController.setupSimulation();
        int i;

        for (i = 0; i < 50; i++) {
            //Corre durante um minuto.
            this.csController.setDeltaTime(60);
            this.csController.updateTemperatures();
            this.csController.stepTime();
        }
        CodificadorHTML instance = new CodificadorHTML(this.sim,
                this.csController, "test/controllers/", "test_export.html");
        instance.exportarDados(materiais, servidores, sala);
    }

}
