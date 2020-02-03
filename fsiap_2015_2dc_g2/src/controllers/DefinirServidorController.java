/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.ArrayList;
import java.util.List;
import model.Material;
import model.RegistoMateriais;
import model.RegistoServidores;
import model.Servidor;
import model.Simulacao;

/**
 *
 */
public class DefinirServidorController {

    /**
     * Simulação à qual se vai adicionar um tipo de material.
     */
    private Simulacao simulacao;

    /**
     * Lista dos vários materiais existentes.
     */
    private RegistoMateriais registoMateriais;

    /**
     * Lista dos vários servidores existentes.
     */
    private RegistoServidores registoServidores;

    /**
     * Lista de materiais.
     */
    private ArrayList<Material> listaMateriais;

    /**
     * Servidor criado.
     */
    private Servidor servidor;

    /**
     * Constrói uma instância do controlador que gere o caso de uso 4, Definir 
     * Servidor Controller, através da simulação.
     * 
     * @param simulacao 
     */
    public DefinirServidorController(Simulacao simulacao) {
        this.simulacao = simulacao;
        this.registoMateriais = simulacao.getRegistoMateriais();
        this.registoServidores = simulacao.getRegistoServidores();
    }

    /**
     * Cria uma nova instância de Servidor.
     */
    public void novoServidor() {
        this.listaMateriais = registoMateriais.getMateriais();
        this.servidor = this.registoServidores.novoServidor();
    }

    /**
     * Devolve a lista de materiais da simulação.
     * @return (List&lt;{@link model.Material}&gt;) A lista de materiais.
     */
    public List<Material> getListaMateriais()
    {
        return simulacao.getListaMateriaisExistentes();
    }
    
    /**
    * Define o nome da instância de Servidor criada.
    */
    public void setNome(String nome) {
        this.servidor.setNome(nome);
    }

    /**
     * Define o material da instância de Servidor criada.
     * @param mat Material a ser adicionado.
     */
    public void setMaterial(Material mat) {
        this.servidor.setMaterial(mat);
    }

    /**
     * 
     * Define os dados da instância de Servidor criada.
     * 
     * @param massa Massa do Servidor.
     * @param comprimento Comprimento do Servidor.
     * @param largura Largura do Servidor.
     * @param altura Altura do Servidor.
     * @param carga Carga do Servidor.
     */
    public void setDados(double massa, double comprimento, double largura,
            double altura, double carga) {
        this.servidor.setMassa(massa);
        this.servidor.setComprimento(comprimento);
        this.servidor.setLargura(largura);
        this.servidor.setAltura(altura);
        this.servidor.setCarga(carga);
    }

    /**
     * Retorna os dados necessários da instância de Servidor criada.
     * 
     * @return dados do servidor. 
     */
    public String getDados() {
        if (this.servidor.valida()) {
            return this.servidor.toString();
        }
        return "Simulador Inválido";
    }

    /**
     * Adiciona o Servidor à lista de Servidores já existentes.
     * 
     * @return verdade se foi adicionado.
     */
    public boolean adicionarServidor() {
        return this.registoServidores.adicionarServidor(this.servidor);
    }

}
