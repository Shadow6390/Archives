package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Representa uma classe que gere os vários tipos de servidores disponíveis na
 * aplicação através de uma lista de servidores.
 */
public class RegistoServidores implements Serializable {

    /**
     * A lista de servidores deste registo.
     */
    private ArrayList<Servidor> listaServidores;

    /**
     * Cria uma instância de {@link RegistoServidores} com parâmetros nulos.
     */
    public RegistoServidores() {
        listaServidores = new ArrayList();
    }

    /**
     * Cria uma instância de {@link RegistoServidores} com os parâmetros
     * especificados.
     *
     * @param listaServidores (ArrayList&lt;{@link Servidor}&gt;) Uma lista de
     * servidores para popular este registo de servidores. É realizada uma cópia
     * manual.
     */
    public RegistoServidores(ArrayList<Servidor> listaServidores) {
        this.listaServidores = new ArrayList();
        for (Servidor element : listaServidores) {
            this.listaServidores.add(element);
        }
    }

    /**
     * Retorna a lista de servidores deste registo.
     *
     * @return (ArrayList&lt;{@link Servidor}&gt;) A lista de servidores.
     */
    public ArrayList<Servidor> getListaServidores() {
        ArrayList<Servidor> result = new ArrayList();
        result.addAll(listaServidores);
        return result;
    }

    /**
     * Cria uma nova instância de servidor.
     * 
     * @return Nova instância de servidor
     */
    public Servidor novoServidor() {
        return new Servidor();
    }

    /**
     * Adiciona um servidor à lista de servidores após a validação do mesmo.
     * 
     * @param servidor Servidor que vai ser adicionado.
     * @return Verdadeiro se o servidor for adicionado com successo ou falso
     * caso não o seja.
     */
    public boolean adicionarServidor(Servidor servidor) {
        if (servidor.valida()) {
            if (getIndexOf(servidor)==-1)
            {
                return this.listaServidores.add(servidor);
            }
        }
        return false;
    }

    /**
     * Retorna a posição do servidor especificado neste registo de servidores.
     *
     * @param serv ({@link Servidor}) O servidor especificado.
     * @return (int) A posição do servidor na lista. -1 se o servidor não
     * existir na lista.
     */
    public int getIndexOf(Servidor serv) {
        return listaServidores.indexOf(serv);
    }

    /**
     * Regista a alteração de um servidor neste registo de servidores.
     *
     * @param serv ({@link Servidor}) O servidor com as novas alterações.
     * @param index (int) O índice do servidor original.
     * @return (boolean) Verdadeiro caso a alteração tenha sido registada com
     * sucesso.
     */
    public boolean registaAlteracao(Servidor serv, int index) {
        boolean result = 0 <= index && index < listaServidores.size();
        if (result) {
            result = validaServidor(serv, index);
            if (result) {
                listaServidores.set(index, serv);
            }
        }
        return result;
    }

    /**
     * Verifica se o servidor especificado já existe em qualquer outra posição
     * que não a posição original.
     *
     * @param serv ({@link Servidor}) O servidor especificado.
     * @param index (int) A posição original do servidor.
     * @return (boolean) Verdadeiro se o servidor não tiver conflito com
     * qualquer outro servidor.
     */
    private boolean validaServidor(Servidor serv, int index) {
        boolean result = true;
        for (int i = 0; i < listaServidores.size() && result; i++) {
            if (serv.equals(listaServidores.get(i)) && i != index) {
                result = false;
            }
        }
        return result;
    }

    /**
     * Obtém um servidor a partir do seu nome.
     *
     * @param nome nome do servidor
     * @return servidor
     */
    public Servidor getServidor(String nome) {
        for (Servidor servidor : listaServidores) {
            if (servidor.getNome().equalsIgnoreCase(nome)) {
                return servidor;
            }
        }
        return null;
    }
}
