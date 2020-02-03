package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Representa uma classe que gere os vários idiomas disponíveis na aplicação
 * através de uma lista de idiomas.
 */
public class RegistoIdiomas implements Serializable {

    /**
     * Lista que contém todos os idiomas suportados pela aplicação.
     */
    private ArrayList<Idioma> listaIdiomas;

    /**
     * Constrói uma classe que gere os vários idiomas disponíveis na aplicação
     * através de uma lista de idiomas.
     *
     * Inicialmente estão disponíveis os idiomas português (PT) e inglês (GB).
     */
    public RegistoIdiomas() {
        // Estes idiomas existem por defeito na aplicação.
        Idioma portugues = new Idioma(
                "uc12.idioma.portugues", new Locale("pt", "PT"));
        Idioma inglesBritanico = new Idioma(
                "uc12.idioma.ingles", new Locale("en", "GB"));

        this.listaIdiomas = new ArrayList<>();
        this.listaIdiomas.add(portugues);
        this.listaIdiomas.add(inglesBritanico);
    }

    /**
     * Imprime as informações da lista de idiomas.
     *
     * @return Informações da lista de idiomas.
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(Simulacao.getIdiomaAtual().getString(
                "uc12.registoidiomas.lista")).append("\n");

        for (Idioma idioma : this.listaIdiomas) {
            stringBuilder.append(idioma.toString()).append("\n");
        }

        return stringBuilder.toString();
    }

    /**
     * Converte a lista de idiomas num vetor com o nome de cada um deles.
     *
     * @return Vetor com o nome de cada um dos idiomas disponíveis.
     */
    public String[] listaParaVetor() {
        String[] vetorIdiomas = new String[this.listaIdiomas.size()];

        for (int i = 0; i < this.listaIdiomas.size(); i++) {
            vetorIdiomas[i] = this.listaIdiomas.get(i).toString();
        }

        return vetorIdiomas;
    }

    /**
     * Devolve o idioma utilizado por defeito, neste caso será o primeiro idioma
     * na lista de idiomas.
     *
     * @return Idioma por defeito.
     */
    public Idioma getIdiomaPorDefeito() {
        return this.listaIdiomas.get(0);
    }

    /**
     * Devolve um idioma da lista de idiomas, procurando o mesmo pelo respectivo
     * nome, caso o idioma não exista retorna null.
     *
     * @param nomeIdioma Nome do idioma que se pretende obter.
     * @return Devolve um idioma da lista de idiomas ou null caso o idioma não
     * exista.
     */
    public Idioma getIdioma(String nomeIdioma) {
        Idioma idioma = null;

        for (Idioma idiomaLista : this.listaIdiomas) {
            if (idiomaLista.toString().equals(nomeIdioma)) {
                idioma = idiomaLista;
            }
        }

        return idioma;
    }

}
