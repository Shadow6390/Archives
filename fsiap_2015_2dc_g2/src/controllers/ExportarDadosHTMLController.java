package controllers;

import model.CodificadorHTML;
import model.Simulacao;

/**
 * Classe controller que controla a exportação dos dados em HTML.
 *
 * @author DC_G2
 */
public class ExportarDadosHTMLController {
    
    /**
     * Simulação cujo dados vão ser exportados.
     */
    private Simulacao simulacao;

    /**
     * ??
     */
    private CorrerSimulacaoController csController;

    /**
     * Classe codificadora dos dados.
     */
    private CodificadorHTML codificador;

    /**
     * Contrutor completo que cria uma instância com os parâmetros
     * especificados.
     * 
     * @param simulacao Simulação pretendida.
     * @param csController Controlador da simulação pretendida.
     */
    public ExportarDadosHTMLController(Simulacao simulacao, CorrerSimulacaoController csController) {
        this.simulacao = simulacao;
        this.csController = csController;
        this.codificador = new CodificadorHTML(this.simulacao,csController);
    }

    /**
     * Define os dados opcionais a exportar.
     * 
     * @param materiais se pretende exportar os materiais;
     * @param servidores se pretende exportar os servidores;
     * @param sala se pretende exportar os servidores;
     */
    public void setDados(boolean materiais, boolean servidores, boolean sala) {
        this.codificador.exportarDados(materiais, servidores, sala);
    }

    /**
     * Define o caminho do ficheiro e o nome a atribuir-lhe.
     * 
     * @param caminho caminho do ficheiro.
     * @param nomeFicheiro nome do ficheiro.
     */
    public void setCaminho(String caminho, String nomeFicheiro) {
        this.codificador.setCaminhoFicheiro(caminho);
        this.codificador.setNomeFicheiro(nomeFicheiro);
    }
}
