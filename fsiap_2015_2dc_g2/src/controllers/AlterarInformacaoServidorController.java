/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.ArrayList;
import model.Material;
import model.Servidor;
import model.Simulacao;
import static model.Simulacao.getIdiomaAtual;

/**
 * Class controller que coordena a alteração da informação do servidor.
 * @author DC_G2
 */
public class AlterarInformacaoServidorController {
    
    /**
     * A simulação pretendida.
     */
    private final Simulacao sim;
    
    /**
     * O servidor a ser alterado.
     */
    private Servidor serv;
    
    /**
     * O índice do servidor na lista de servidores.
     */
    private int index;
    
    /**
     * O clone do servidor.
     */
    private Servidor servClone;
    
    /**
     * Cria uma instância de {@link AlterarInformacaoServidorController} com os parâmetros
     * especificados.
     * @param s ({@link model.Simulacao}) A simulação pretendida. 
     */
    public AlterarInformacaoServidorController(Simulacao s)
    {
        sim=s;
    }
    
    /**
     * Cria um clone do servidor especificado.
     * @param serv ({@link model.Servidor}) O servidor a ser clonado.
     */
    private void createCloneServidor(Servidor serv)
    {
        this.servClone = new Servidor(serv);
    }
    
    /**
     * Devolve a lista de servidores da simulação.
     * @return (ArrayList&lt;{@link model.Servidor}&gt;) A lista de servidores da simulação.
     */
    public ArrayList<Servidor> getListaServidores()
    {
        return sim.getListaServidores();
    }
    
    /**
     * Seleciona o servidor para ser alterado.
     * @param serv ({@link model.Servidor}) O servidor a ser alterado.
     */
    public void selectServidor(Servidor serv)
    {
        this.serv=serv;
        createCloneServidor(serv);
        sim.getIndexOf(serv);
    }
    
    /**
     * Devolve a lista de materiais da simulação.
     * @return (ArrayList&lt;{@link model.Material}&gt;) A lista de materiais da simulação.
     */
    public ArrayList<Material> getListaMateriais()
    {
        return (ArrayList)sim.getListaMateriaisExistentes();
    }
    
    /**
     * Devolve o nome do servidor.
     * @return (String) O nome do servidor.
     */
    public String getNome()
    {
        return servClone.getNome();
    }
    
    /**
     * Devolve o material do servidor.
     * @return ({@link model.Material}) O material do servidor.
     */
    public Material getMaterial()
    {
        return servClone.getMaterial();
    }
    
    /**
     * Devolve a carga do servidor.
     * @return (double) A carga do servidor.
     */
    public double getCarga()
    {
        return servClone.getCarga();
    }
    
    /**
     * Devolve a massa do servidor.
     * <p>
     * A massa encontra-se nas unidades SI (kg).
     * @return (double) O valor da massa do servidor.
     */
    public double getMassa()
    {
        return servClone.getMassa();
    }
    
    /**
     * Devolve um vetor com as dimensões do servidor.
     * <p>
     * O formato das dimensões do servidor é o seguinte:
     * <p>
     * [0]=Comprimento do servidor.
     * <p>
     * [1]=Largura do servidor.
     * <p>
     * [2]=Altura do servidor.
     * @return (double[]) As dimensões do servidor.
     */
    public double[] getDimensoes()
    {
        double[] result = new double[3];
        result[0]=servClone.getComprimento();
        result[1]=servClone.getLargura();
        result[2]=servClone.getAltura();
        return result;
    }
    
    /**
     * Atribui um novo nome ao servidor.
     * @param nome (String) O novo nome do servidor.
     */
    public void setNome(String nome)
    {
        servClone.setNome(nome);
    }
    /**
     * Atribui um novo material ao servidor.
     * @param mat ({@link model.Material}) O novo material do servidor.
     */
    public void setMaterial(Material mat)
    {
        servClone.setMaterial(mat);
    }
    
    /**
     * Atribui um novo valor à carga do servidor.
     * <p>
     * A carga do servidor encontra-se entre 0% e 100%.
     * @param carga (double) O novo valor da carga do servidor.
     */
    public void setCarga(double carga)
    {
        servClone.setCarga(carga);
    }
    /**
     * Atribui um novo valor à massa do servidor.
     * <p>
     * A massa encontra-se nas unidades SI (kg).
     * @param massa (double) O novo valor da massa do servidor. 
     */
    public void setMassa(double massa)
    {
        servClone.setMassa(massa);
    }
    
    /**
     * Atribui novas dimensões ao servidor.
     * O formato do vetor das dimensões é o seguinte:
     * <p>
     * [0]=Comprimento do servidor.
     * <p>
     * [1]=Largura do servidor.
     * <p>
     * [2]=Altura do servidor.
     * @param dim (double[]) O vetor de doubles com as novas dimensões do servidor.
     */
    public void setDimensoes(double[] dim)
    {
        if (dim.length<3)
        {
            throw new IllegalArgumentException(getIdiomaAtual().getString("uc6.controller.dimensoes_invalidas"));
        }
        servClone.setAltura(dim[2]);
        servClone.setLargura(dim[1]);
        servClone.setComprimento(dim[0]);
    }
    
    /**
     * Regista a alteração da informação do servidor.
     * @return (boolean) Verdadeiro caso a alteração tenha sido bem sucedida.
     */
    public boolean registaAlteracao()
    {
        boolean result = servClone.valida();
        if (result)
        {
            //Falta alterar SD
            result = sim.registaAlteracao(servClone,index);
        }
        return result;
    }
}
