package controllers;

import model.Idioma;
import model.RegistoIdiomas;
import model.Simulacao;

/**
 * Representa um controlador que gere o caso de uso 12, alterar idioma, através
 * de uma simulação, um registo de idiomas e um idioma.
 */
public class AlterarIdiomaController {

    /**
     * Simulação à qual se vai alterar o idioma.
     */
    private Simulacao simulacao;

    /**
     * Lista dos vários idiomas disponíveis.
     */
    private RegistoIdiomas registoIdiomas;

    /**
     * Idioma selecionado.
     */
    private Idioma idioma;

    /**
     * Constrói uma instância do controlador que gere o caso de uso 12, alterar
     * idioma, através da simulação.
     *
     * @param simulacao Simulação cujo idioma vai ser alterado.
     */
    public AlterarIdiomaController(Simulacao simulacao) {
        this.simulacao = simulacao;
    }

    /**
     * Devolve a lista de idiomas disponíveis.
     *
     * @return Lista de idiomas disponíveis.
     */
    public String[] iniciarAlteracaoIdioma() {
        this.registoIdiomas = simulacao.getRegistoIdiomas();

        return this.registoIdiomas.listaParaVetor();
    }

    /**
     * Seleciona um idioma de entre os disponíveis e valida se o mesmo não é
     * igual ao atual.
     *
     * @param idioma Idioma selecionado.
     * @return Verdadeiro se for possível alterar o idioma e falso caso o idioma
     * selecionado seja o atual.
     */
    public boolean selecionarIdioma(String idioma) {
        this.idioma = this.registoIdiomas.getIdioma(idioma);

        if (!this.simulacao.validarIdioma(this.idioma.importarIdioma())) {
            throw new IllegalArgumentException(
                    Simulacao.getIdiomaAtual().getString("uc12.erro.selecionaridioma"));
        }

        return true;
    }

    /**
     * Procede à alteração do idioma para o escolhido previamente.
     */
    public void confirmarIdioma() {
        Simulacao.setIdiomaAtual(this.idioma.importarIdioma());
    }

}
