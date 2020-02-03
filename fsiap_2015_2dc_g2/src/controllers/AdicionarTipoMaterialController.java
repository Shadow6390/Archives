/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import model.Material;
import model.RegistoMateriais;
import model.Simulacao;

/**
 * Classe controller que coordena a adição de um novo tipo de material.
 * 
 * @author DC_G2
 */
public class AdicionarTipoMaterialController {

    /**
     * Simulação à qual se vai adicionar um tipo de material.
     */
    private Simulacao simulacao;

    /**
     * Lista dos vários materiais existentes.
     */
    private RegistoMateriais registoMateriais;

    /**
     * Material criado.
     */
    private Material material;

    /**
     * Constrói uma instância do controlador que gere o caso de uso 3, Adicionar
     * Tipo de Material, através da simulação.
     *
     * @param simulacao Simulação onde material vai ser adicionado.
     */
    public AdicionarTipoMaterialController(Simulacao simulacao) {
        this.simulacao = simulacao;
        registoMateriais = simulacao.getRegistoMateriais();
    }

    /**
     * Método que permite obter a lista de materiais existentes;
     *
     * @return lista de materiais existentes.
     */
    private List<Material> getListaMateriais() {
        return simulacao.getListaMateriaisExistentes();
    }

    /**
     * Método que permite obter os dados das listas de materiais existentes;
     *
     * @return lista de materiais existentes.
     */
    public ArrayList<String> getListaMateriaisString() {
        ArrayList<String> lista = new ArrayList<>();
        for (Material mat : this.getListaMateriais()) {
            lista.add(mat.getNome());
        }
        return lista;
    }

    /**
     * Método que permite a criação de um novo tipo de material;
     *
     * @param nome nome do material.
     * @param descricao descrição do material.
     * @param coeficienteConducao Coeficiente de condução do material
     * @param coeficienteConveccao Coeficiente de convecção do material
     * @param coeficienteRadiacao Coeficiente de radiação do material
     * @param imagem imagem do material.
     * @return Verdadeiro se foi criado, falso se não foi.
     */
    public boolean novoMaterial(String nome, String descricao, double coeficienteConducao,
            double coeficienteConveccao, double coeficienteRadiacao, BufferedImage imagem) {
        this.material = new Material(nome, descricao, coeficienteConducao, 
                coeficienteConveccao, coeficienteRadiacao, imagem);
        if (this.material != null && this.material.valida()) {
            return true;
        }
        return false;
    }

    /**
     * Método que permite obter os dados do material criado.
     *
     * @return dados do material.
     */
    public String getInfo() {
        return this.material.toString();
    }

    /**
     * Método que permite registar o novo material na lista de materiais
     * existentes.
     *
     * @return Verdadeiro se foi adicionado ou falso se não foi.
     */
    public boolean RegistaMaterial() {
        return this.registoMateriais.addMaterial(this.material);
    }

}
