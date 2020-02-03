package model;

import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Representa um idioma através da sua localização, a sigla utilizada para o
 * representar (e.g. Portugal = pt_PT).
 */
public class Idioma implements Serializable {

    /**
     * Nome do idioma.
     */
    private String idioma;

    /**
     * Localização do idioma
     */
    private Locale localizacao;

    /**
     * Constrói uma instância de idioma através da sua localização.
     *
     * @param idioma Nome do idioma.
     * @param localizacao Localização do idioma.
     */
    public Idioma(String idioma, Locale localizacao) {
        setIdioma(idioma);
        setLocalizacao(localizacao);
    }

    /**
     * Devolve o nome do idioma.
     *
     * @return Nome do idioma.
     */
    public String getIdioma() {
        return this.idioma;
    }

    /**
     * Devolve a localização do idioma.
     *
     * @return Localização do idioma.
     */
    public Locale getLocalizacao() {
        return this.localizacao;
    }

    /**
     * Modifica o nome do idioma.
     *
     * @param idioma Novo nome do idioma.
     */
    public final void setIdioma(String idioma) {
        if (idioma == null || idioma.trim().isEmpty()) {
            throw new IllegalArgumentException("uc12.erro.idioma");
        }

        this.idioma = idioma;
    }

    /**
     * Modifica a localização do idioma.
     *
     * @param localizacao Nova localização do idioma.
     */
    public final void setLocalizacao(Locale localizacao) {
        if (localizacao == null) {
            throw new IllegalArgumentException("uc12.erro.localizacao");
        }

        this.localizacao = localizacao;
    }

    /**
     * Imprime o nome do Idioma dependendo do idioma atual em utilização.
     *
     * @return Nome do idioma.
     */
    @Override
    public String toString() {
        return Simulacao.getIdiomaAtual().getString(idioma);
    }

    /**
     * Verifica se dois objetos são iguais, comparando inicialmente a posição da
     * memória, de seguida se o segundo está a null ou se as classes de cada um
     * são diferentes e finalmente os seus atributos.
     *
     * @param otherObject Objecto que irá ser usado na comparação.
     * @return Verdadeiro se os objetos forem iguais e falso caso não sejam.
     */
    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }

        if (otherObject == null || this.getClass() != otherObject.getClass()) {
            return false;
        }

        Idioma otherIdioma = (Idioma) otherObject;

        return this.localizacao.equals(otherIdioma.localizacao);

    }

    /**
     * Carrega para memória o ficheiro que contém a tradução de todas as
     * palavras/frases referentes à localização do idioma.
     *
     * @return Objeto com as strings de um determinado idioma.
     */
    public ResourceBundle importarIdioma() {
        return ResourceBundle.getBundle("languages/language", localizacao);
    }

}
