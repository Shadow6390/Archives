/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import model.RegistoServidores;
import model.Sala;
import model.Servidor;
import model.Simulacao;

/**
 * Classe controller que coordena a adição de um servidor na sala.
 *
 * @author DC_G2
 */
public class AdicionarServidorSalaController {

    /**
     * Simulação à qual pertence a sala.
     */
    private Simulacao simulacao;
    /**
     * Sala à qual se vai adicionar um servidor
     */
    private Sala sala;
    /**
     * Lista de servidores existentes na simulação.
     */
    private RegistoServidores registoServidores;

    /**
     * Construtor que recebe a simulação à qual se vai adicionar um servidor.
     *
     * @param simulacao
     */
    public AdicionarServidorSalaController(Simulacao simulacao) {
        this.simulacao = simulacao;
        this.registoServidores = simulacao.getRegistoServidores();
        this.sala = simulacao.getSala();
    }
    
    /**
     * Devolve as dimensões da sala com o nome especificado.
     * @return (double[]) As dimensões do sala.
     */
    public double[] getDimensoesSala()
    {
        double[] result=null;
        if (sala != null) {
            result=sala.getDimensoes();
        }
        return result;
    }

    /**
     * Método que obtém a lista de servidores existentes sob a forma de texto.
     *
     * @return lista de servidores
     */
    public List<String> getListaServidores() {
        List<String> listaServidoresStr = new ArrayList<>();
        for (Servidor servidor : registoServidores.getListaServidores()) {
            listaServidoresStr.add(servidor.getNome()); //ou toString ??
        }
        return listaServidoresStr;
    }

    /**
     * Adicionar o servidor à sala, a partir do seu id (nome).
     *
     * @param idServidor nome do servidor
     * @param pos posição na qual etamos a colocar o servidor
     * @param temp (double) A temperatura inicial do servidor instanciado.
     * @return true caso tenha sido adicionado, false caso contrario
     */
    public boolean adicionarServidor(String idServidor, double[] pos,double temp) {
        Servidor servidor = registoServidores.getServidor(idServidor);
        if (servidor != null) {
            return sala.addServidor(servidor, pos,temp);
        }
        return false;
    }

    /**
     * Devolve a carga do servidor com o nome especificado.
     * @param idServidor (String) O nome do servidor.
     * @return (String) A carga do servidor.
     */
    public String getServidorCarga(String idServidor)
    {
        String result="";
        Servidor servidor = registoServidores.getServidor(idServidor);
        if (servidor != null) {
            result=String.valueOf(servidor.getCarga());
        }
        return result;
    }
    
    /**
     * Devolve a massa do servidor com o nome especificado.
     * @param idServidor (String) O nome do servidor.
     * @return (String) A massa do servidor.
     */
    public String getServidorMassa(String idServidor)
    {
        String result="";
        Servidor servidor = registoServidores.getServidor(idServidor);
        if (servidor != null) {
            result=String.valueOf(servidor.getMassa());
        }
        return result;
    }
    
    /**
     * Devolve as dimensões do servidor com o nome especificado.
     * @param idServidor (String) O nome do servidor.
     * @return (double[]) As dimensões do servidor.
     */
    public double[] getServidorDimensoes(String idServidor)
    {
        double[] result=null;
        Servidor servidor = registoServidores.getServidor(idServidor);
        if (servidor != null) {
            result=new double[3];
            result[0] = servidor.getComprimento();
            result[1] = servidor.getLargura();
            result[2] = servidor.getAltura();
        }
        return result;
    }
    
    /**
     * Devolve a imagem do material do servidor com o nome especificado.
     * @param idServidor (String) O nome do servidor.
     * @return (BufferedImage) A imagem do material do servidor.
     */
    public BufferedImage getServidorImage(String idServidor)
    {
        BufferedImage result=null;
        Servidor servidor = registoServidores.getServidor(idServidor);
        if (servidor != null) {
            result=servidor.getMaterial().getImagem();
        }
        return result;
    }
    
    /**
     * Método que atualiza a sala, conforme
     *
     * @param r
     * @return true caso a sala tenha sido atualizada, false caso contrário
     */
    public boolean atualizarSala(boolean r) { //????
        if (r) {
            simulacao.setSala(sala);
            return true;
        }
        return false;
    }
}
