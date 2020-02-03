package model;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Representa uma instância de registo de materiais através de uma lista com os
 * mesmos.
 */
public class RegistoMateriais implements Serializable {

    /**
     * Lista de Materiais existentes.
     */
    private ArrayList<Material> materiais;

    /**
     * Construstor da classe RegistoMateriais que cria uma nova lista de
     * materiais.
     */
    public RegistoMateriais() {
        this.materiais = new ArrayList<>();
    }

    /**
     * Construtor da classe RegistoMateriais que inicia a lista de de materias
     * com uma lista recebida por parâmetro.
     *
     * @param materiais Lista de Materiais
     */
    public RegistoMateriais(ArrayList<Material> materiais) {
        this.materiais = materiais;
    }

    /**
     * Devolve a lista de materiais.
     *
     * @return Lista de materiais
     */
    public ArrayList<Material> getMateriais() {
        return materiais;
    }

    /**
     * Modifica a lista de materiais.
     *
     * @param materiais Nova lista de materiais
     */
    public void setMateriais(ArrayList<Material> materiais) {
        this.materiais = materiais;
    }

    /**
     * Método que cria um novo tipo de material.
     *
     * @param nome Nome do material.
     * @param descricao Descrição do material.
     * @param coeficienteConveccao Coeficiente de convecção
     * @param coeficienteConducao Coeficiente de condução
     * @param coeficienteRadiacao Coeficiente de radiação
     * @param imagem Imagem do material.
     *
     * @return Retorna o material criado com os dados passados por parâmetro se
     * este for válido, senão retorna null;
     */
    public Material novoMaterial(String nome, String descricao,
            double coeficienteConveccao, double coeficienteConducao,
            double coeficienteRadiacao, BufferedImage imagem) {
        Material material = new Material(nome, descricao, coeficienteConveccao,
                coeficienteConducao, coeficienteRadiacao, imagem);

        if (this.validaMaterial(material) && material.valida()) {
            return material;
        }

        return null;
    }

    /**
     * Método que permite obter a informação do material criado.
     *
     * @param material Novo tipo de material criado.
     * @return Retorna a informação do material.
     */
    public String getInfo(Material material) {
        return material.toString();
    }

    /**
     * Método que permite validar se um material já existe na lista ou não.
     *
     * @param material Novo tipo de material criado.
     *
     * @return Retorna verdadeiro se o material não estiver presente na lista de
     * materiais existentes ou falso se já lá se encontrar
     */
    public boolean validaMaterial(Material material) {
        for (Material mat : this.materiais) {
            if (material.equals(mat)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Método que permite adicionar o material à lista de materiais já
     * existente.
     *
     * @param material Novo tipo de material.
     *
     * @return Retorna verdadeiro se foi adicionado ou falso se não foi
     * adicionado.
     */
    public boolean addMaterial(Material material) {
        return this.materiais.add(material);
    }

    /**
     * Método que permite procurar um material na lista pelo seu nome.
     *
     * @param nomeMaterial Nome do material.
     *
     * @return Retorna o material se ele existir ou null se ele não existir na
     * lista.
     */
    public Material procuraMaterial(String nomeMaterial) {
        for (Material material : this.materiais) {
            if (material.getNome().compareToIgnoreCase(nomeMaterial) == 0) {
                return material;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return materiais.toString();
    }

    /**
     * Método que compara dois objetos.
     *
     * @param obj
     * @return
     */
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        RegistoMateriais rm = (RegistoMateriais) obj;
        return materiais.equals(rm.getMateriais());
    }
}
