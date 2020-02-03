/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa uma lista de servidores instanciados. Esta classe contém todas as
 * operações para lidar com servidores instanciados.
 *
* @author DC_G2
 */
public class ListaServidoresInstanciados implements Serializable{

    private ArrayList<ServidorInstanciado> listaServidoresInstanciados;

    public ListaServidoresInstanciados() {
        listaServidoresInstanciados = new ArrayList();
    }

    /**
     * Método que adiciona um servidor à lista de servidores instanciados, se
     * este for válido.
     *
     * @param servidor servidor a ser adicionado
     * @param pos coordenadas do servidor
     * @param temp (double) A temperatura inicial do servidor.
     * @return true caso o servidor tenha sido adicionado, false caso contrário.
     */
    public boolean addServidor(Servidor servidor, double[] pos,double temp) {
        ServidorInstanciado servidorInstanciado = new ServidorInstanciado(servidor);
        servidorInstanciado.setPosicao(pos);
        servidorInstanciado.setTemperatura(temp);
        boolean result = checkOverlaps(servidorInstanciado);
        if (result)
        {
            result = listaServidoresInstanciados.add(servidorInstanciado);
        }
        return result;
    }
    
    /**
     * Verifica se um servidor instanciado está dentro de um outro.
     * @param s ({@link ServidorInstanciado}) O servidor instanciado a verificar.
     * @return (boolean) Verdadeiro caso não esteja dentro de um outro servidor.
     */
    private boolean checkOverlaps(ServidorInstanciado s)
    {
        boolean result=true;
        for (int i=0;i<listaServidoresInstanciados.size() && result;i++)
        {
            result = !s.isInsideOf(listaServidoresInstanciados.get(i));
        }
        return result;
    }

    /**
     * Adiciona os dados das instâncias desta lista ao mapa térmico.
     *
     * @param mtd ({@link MapaTermicoData}) O mapa termico a adicionar os dados.
     */
    public void adicionaInstanciasA(MapaTermicoData mtd) {
        for (ServidorInstanciado focus : listaServidoresInstanciados) {
            double temp = focus.getTemperatura();
            double[] pos = focus.getPosicao();
            if (!mtd.addData(temp, pos)) {
                throw new RuntimeException("Erro na inicialização: não foi possível obter os dados de uma instância.");
            }
        }
    }
    
    /**
     * Remove todos os servidos instanciados desta lista de servidores instanciados.
     */
    public void removeServidoresInstanciados()
    {
        listaServidoresInstanciados.clear();
    }
    
    /**
     * Devolve uma lista com os servidores instanciados nesta lista.
     * @return (List&lt;{@link ServidorInstanciado}&gt;) A lista de servidores instanciados.
     */
    public List<ServidorInstanciado> getListaServidoresInstanciados()
    {
        return new ArrayList(listaServidoresInstanciados);
    }
}
