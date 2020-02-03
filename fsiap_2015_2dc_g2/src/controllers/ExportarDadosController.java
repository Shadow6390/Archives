package controllers;

import java.io.IOException;
import model.GestorDados;
import model.Simulacao;

/**
 * Representa um controlador que gere o caso de uso 8, exportar dados, através
 * de uma simulação, um gestor de dados.
 */
public class ExportarDadosController {
    
    /**
     * Simulação que contém os dados exportados.
     */
    private Simulacao simulacao;

    /**
     * Objeto que gere a importação dos dados.
     */
    private GestorDados gestorDados;

    /**
     * Constrói uma instância do controlador que gere o caso de uso 8, exportar
     * dados, através da simulação.
     *
     * @param simulacao Simulação que contém os dados exportados.
     */
    public ExportarDadosController(Simulacao simulacao) {
        this.simulacao = simulacao;
    }

    /**
     * Cria e associa ao controller uma instância do gestor de dados,
     * responsável por gerir a exportação dos mesmos.
     */
    public void iniciarExportacao() {
        this.gestorDados = this.simulacao.iniciarExportacao();
    }

    /**
     * Associa o caminho e o nome do ficheiro no qual serão guardados os dados a
     * serem exportados.
     *
     * @param caminho Caminho do ficheiro.
     * @param nome Nome do ficheiro.
     */
    public void setDados(String caminho, String nome) {
        this.gestorDados.setCaminhoFicheiro(caminho);
        this.gestorDados.setNomeFicheiro(nome);

        if (!this.gestorDados.validarExportacao()) {
            throw new IllegalArgumentException(
                    Simulacao.getFrasePelaChave("uc8.janela.exportar.validar"));
        }
    }

    /**
     * Inicia a importação dos dados do ficheiro para dentro da simulação.
     *
     * @throws java.io.IOException Lançada caso não seja possível criar o
     * ficheiro com os dados.
     */
    public void exportarDados() throws IOException {
        this.gestorDados.exportarDados();
    }

}
