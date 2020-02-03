package controllers;

import java.io.IOException;
import model.GestorDados;
import model.Simulacao;

/**
 * Representa um controlador que gere o caso de uso 8, importar dados, através
 * de uma simulação, um gestor de dados.
 */
public class ImportarDadosController {

    /**
     * Simulação que irá conter os dados importados.
     */
    private Simulacao simulacao;

    /**
     * Objeto que gere a importação dos dados.
     */
    private GestorDados gestorDados;

    /**
     * Constrói uma instância do controlador que gere o caso de uso 8, importar
     * dados, através da simulação.
     *
     * @param simulacao Simulação que irá conter os dados importados.
     */
    public ImportarDadosController(Simulacao simulacao) {
        this.simulacao = simulacao;
    }

    /**
     * Cria e associa ao controller uma instância do gestor de dados,
     * responsável por gerir a importação dos mesmos.
     */
    public void iniciarImportacao() {
        this.gestorDados = this.simulacao.iniciarImportacao();
    }

    /**
     * Associa o caminho e o nome do ficheiro no qual estão guardados os dados a
     * serem importados.
     *
     * @param caminho Caminho do ficheiro.
     * @param nome Nome do ficheiro.
     */
    public void setDados(String caminho, String nome) {
        this.gestorDados.setCaminhoFicheiro(caminho);
        this.gestorDados.setNomeFicheiro(nome);

        if (!this.gestorDados.validarImportacao()) {
            throw new IllegalArgumentException(
                    Simulacao.getFrasePelaChave("uc8.janela.importar.validar"));
        }
    }

    /**
     * Inicia a importação dos dados do ficheiro para dentro da simulação.
     *
     * @throws java.lang.ClassNotFoundException Lançada caso não seja possível
     * fazer o downcast adequado.
     * @throws IOException Lançada no caso de a importação falhar.
     */
    public Simulacao importarDados() throws ClassNotFoundException, IOException {
        return gestorDados.importarDados();
    }

}
