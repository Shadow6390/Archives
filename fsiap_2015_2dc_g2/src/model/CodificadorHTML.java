package model;

import controllers.CorrerSimulacaoController;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.List;

public class CodificadorHTML implements Serializable {

    /**
     * Dados da simulação.
     */
    private Simulacao simulacao;

    /**
     * Controller que corre a simulação.
     */
    private CorrerSimulacaoController csController;

    /**
     * Diretório para o qual se pretende exportar os dados da simulação.
     */
    private String caminhoFicheiro;

    /**
     * Nome do ficheiro que irá conter os dados da simulação.
     */
    private String nomeFicheiro;

    /**
     * Constroi uma instância com apenas a simulação pretendida.
     *
     * @param simulacao simulação pretendida.
     */
    public CodificadorHTML(Simulacao simulacao) {
        this.simulacao = simulacao;
        this.csController = new CorrerSimulacaoController(simulacao);
    }

    /**
     * Constroi uma instância com apenas a simulação pretendida e uma instância
     * de CorrerSimulacaoController.
     *
     * @param simulacao Simulação pretendida.
     * @param csController Instância de CorrerSimulacaoController;
     */
    public CodificadorHTML(Simulacao simulacao, CorrerSimulacaoController csController) {
        this.simulacao = simulacao;
        this.csController = csController;
    }

    /**
     * Contrutor completo que constroi uma instância com todos os parâmetros
     * desejados.
     *
     * @param simulacao Simulação pretendida.
     * @param csController Instância de CorrerSimulacaoController.
     * @param caminhoFicheiro Caminho do ficheiro pretendido.
     * @param nomeFicheiro Nome do ficheiro pretendido.
     */
    public CodificadorHTML(Simulacao simulacao, CorrerSimulacaoController csController, String caminhoFicheiro, String nomeFicheiro) {
        this.simulacao = simulacao;
        this.csController = csController;
        this.caminhoFicheiro = caminhoFicheiro;
        this.nomeFicheiro = nomeFicheiro;
    }

    /**
     * Construtor que constroi uma instância com todos os parâmetros desejados.
     *
     * @param simulacao simulação pretendida.
     * @param caminhoFicheiro caminho do ficheiro pretendido.
     * @param nomeFicheiro nome do ficheiro pretendido.
     */
    public CodificadorHTML(Simulacao simulacao, String caminhoFicheiro, String nomeFicheiro) {
        this.simulacao = simulacao;
        this.csController = new CorrerSimulacaoController(simulacao);
        this.caminhoFicheiro = caminhoFicheiro;
        this.nomeFicheiro = nomeFicheiro;
    }

    /**
     * Devolve o caminho do ficheiro.
     *
     * @return caminho do ficheiro.
     */
    public String getCaminhoFicheiro() {
        return this.caminhoFicheiro;
    }

    /**
     * Devolve o nome do ficheiro.
     *
     * @return nome do ficheiro.
     */
    public String getNomeFicheiro() {
        return this.nomeFicheiro;
    }

    /**
     * Define o caminho do ficheiro.
     *
     * @param caminhoFicheiro caminho do ficheiro.
     */
    public void setCaminhoFicheiro(String caminhoFicheiro) {
        if (caminhoFicheiro == null || caminhoFicheiro.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }

        this.caminhoFicheiro = caminhoFicheiro;
    }

    /**
     * Define o nome do ficheiro.
     *
     * @param nomeFicheiro nome do ficheiro.
     */
    public void setNomeFicheiro(String nomeFicheiro) {
        if (nomeFicheiro == null || nomeFicheiro.trim().isEmpty()) {
            throw new IllegalArgumentException(Simulacao.getIdiomaAtual().getString("uc8.erro.nome"));
        }

        this.nomeFicheiro = nomeFicheiro;
    }

    /**
     * Valida se a exportação dos dados é valida verificando se o caminho
     * indicado existe.
     *
     * @return Verdadeiro se o caminho existir e falso caso não exista.
     */
    public boolean validarExportacao() {
        return new File(this.caminhoFicheiro).exists();
    }

    /**
     * Exporta os dados da simulação para o ficheiro no diretório indicado.
     *
     * @param materiais Booleano que determina se é suposto ou não exportar os
     * materiais utilizados na simulação.
     * @param servidores Booleano que determina se é suposto ou não exportar os
     * servidores utilizados na simulação.
     * @param sala Booleano que determina se é suposto ou não exportar mais
     * informações sobre a sala utilizada na simulação.
     */
    public void exportarDados(boolean materiais, boolean servidores, boolean sala) {
        try {
            BufferedWriter output = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(
                            this.caminhoFicheiro + this.nomeFicheiro), "UTF-8"));

            // Inicia o ficheiro HTML
            output.write(abrirHTML(nomeFicheiro));

            // Adiciona os dados elementares da sala.
            output.write(adicionarDados());

            // Adiciona os materiais utilizados na simulação.
            if (materiais) {
                output.write(adicionarDadosMateriais());
            }

            // Adiciona os servidores utilizados na simulação.
            if (servidores) {
                output.write(adicionarDadosServidores());
            }

            // Adiciona os dados utilizados na simulação relativos ao sala.
            if (sala) {
                output.write(adicionarDadosSala());
            }

            // Termina o ficheiro HTML
            output.write(fecharHTML());
            output.close();
        } catch (IOException ex) {
        }
    }

    /**
     * Abre o documento em HTML.
     *
     * @param nomeFich Título.
     * @return Abertura do documento em HTML.
     */
    public String abrirHTML(String nomeFich) {
        return ("<!DOCTYPE html>"
                + "<html>\n"
                + "\t<head>\n"
                + "\t\t<title>" + nomeFich + "</title>\n"
                + "\t\t<meta charset='utf-8'>\n"
                + "\t</head>\n"
                + "\t<body>\n");
    }

    /**
     * Adiciona todos os dados principais da simulação.
     *
     * @return A informação principais da simulação.
     */
    public String adicionarDados() {
        StringBuilder string = new StringBuilder();

        string.append("\t\t<h3>Dados da Simulação</h3>\n");
        string.append("\t\t<p>Quantidade de Energia necessária para refrigerar a sala à temperatura: ").append(String.format("%.3f", csController.getRefrigerationCost())).append(" J.</p>\n");
        string.append("\t\t<p>Temperatura alvo da Sala: ").append(this.simulacao.getSala().getTemperaturaInteriorAlvo()).append("°C.</p>\n");
        string.append("\t\t<p>Temperatura da Sala:").append(String.format("%.3f", this.simulacao.getSala().getTemperaturaInterior())).append("°C.</p>\n");
        string.append("\t\t<p>Tempo: ").append(csController.getTimeElapsed()).append(" minutos.</p>\n");
        string.append("\t\t<p>Tipos de Servidores: ").append(this.simulacao.getListaServidores().size()).append("</p>\n");
        string.append("\t\t<p>Quantidade de Servidores na sala: ").append(this.simulacao.getSala().getListaServidoresInstanciados().size()).append("</p>\n");

        return string.toString();
    }

    /**
     * Adiciona os dados de todos os materiais usados na simulação.
     *
     * @return A informação de todos os materiais usados na simulação.
     */
    public String adicionarDadosMateriais() {
        StringBuilder string = new StringBuilder();

        string.append("\t\t<hr>\n");
        string.append("\t\t<h3>Dados dos materiais usados na Simulação</h3>\n");

        for (Material material : this.simulacao.getListaMateriaisExistentes()) {
            string.append("\t\t<p>Dados do material: ").append(material.toString()).append("</p>\n");
        }

        return string.toString();
    }

    /**
     * Adiciona os dados de todos os servidores usados na simulação.
     *
     * @return A informação de todos os servidores usados na simulação.
     */
    public String adicionarDadosServidores() {
        StringBuilder string = new StringBuilder();

        string.append("\t\t<hr>\n");
        string.append("\t\t<h3>Dados dos servidores usados na Simulação</h3>\n");

        List<Servidor> servidores = this.simulacao.getListaServidores();
        for (Servidor servidor : servidores) {
            string.append("\t\t<p>Dados do servidor: ").append(servidor.toString()).append("</p>\n");
        }

        return string.toString();
    }

    /**
     * Adiciona todos os dados da sala usada na simulação.
     *
     * @return A informação da sala usada na simulação.
     */
    public String adicionarDadosSala() {
        StringBuilder string = new StringBuilder();

        string.append("\t\t<hr>\n");
        string.append("\t\t<h3>Dados da sala usada na Simulação</h3>\n");
        string.append("\t\t<ul>\n");

        // Informações da sala
        Sala sala = this.simulacao.getSala();
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

        return string.toString();
    }

    /**
     * Fecha o documento em HTML.
     *
     * @return O fecho do documento HTML.
     */
    public String fecharHTML() {
        return ("\t</body>\n"
                + "</html>\n");
    }
}
