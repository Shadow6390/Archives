package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Representa uma classe que gere os vários idiomas disponíveis na aplicação
 * através de uma lista de idiomas.
 */
public class ListaIdiomas implements Serializable{
    
    /**
     * Lista que contém todos os idiomas suportados pela aplicação.
     */
    private ArrayList<Idioma> listaIdiomas;
    
    public ListaIdiomas() {
        this.listaIdiomas = new ArrayList<>();
    }
    
}
