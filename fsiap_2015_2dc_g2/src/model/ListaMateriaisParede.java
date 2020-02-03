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
 *
 * @author DC_G2
 */
public class ListaMateriaisParede implements Serializable {

    /**
     * Lista de materiais da parede.
     */
    private List<Material> listaMateriais;

    /**
     * Lista de espessuras da parede.
     */
    private List<Double> listaEspessuras;

    /**
     * Construtor vazio da lista de materiais da parede.
     */
    public ListaMateriaisParede() {
        listaMateriais = new ArrayList();
        listaEspessuras = new ArrayList();
    }

    /**
     * Construtor completo da lista de materiais da parede.
     *
     * @param listaMateriais (lista de materiais) A lista de materiais.
     * @param listaEspessuras (lista de double) A lista de espessuras dos materiais. 
     */
    public ListaMateriaisParede(List<Material> listaMateriais, List<Double> listaEspessuras) {
        setListaMateriais(listaMateriais);
        setListaEspessuras(listaEspessuras);
    }
    
    public ListaMateriaisParede(ListaMateriaisParede origin)
    {
        listaMateriais = new ArrayList();
        listaEspessuras = new ArrayList();
        listaMateriais.addAll(origin.listaMateriais);
        listaEspessuras.addAll(origin.listaEspessuras);
    }

    /**
     * Retorna a lista de materiais da parede.
     *
     * @return (lista de materiais) A lista de materiais.
     */
    public List<Material> getListaMateriais() {
        return listaMateriais;
    }

    /**
     * Retorna a lista de espessuras dos diferentes materiais da parede.
     *
     * @return (lista de double) A lista de espessuras.
     */
    public List<Double> getListaEspessuras() {
        return listaEspessuras;
    }

    /**
     * Retorna o material da parede que corresponde á posição i.
     *
     * @return (material) O material de indice i.
     */
    public Material getMaterial(int i) {
        return listaMateriais.get(i);
    }

    /**
     * Retorna a espessura do material da parede que corresponde á posição i.
     *
     * @return (double) A espessura do material de indice i.
     */
    public double getEspessura(int i) {
        return listaEspessuras.get(i);
    }

    /**
     * Altera a lista de materiais da parede.
     *
     * @param listaMateriais (lista de materiais) A lista de materiais.
     */
    public void setListaMateriais(List<Material> listaMateriais) {
        if (listaMateriais == null) {
            throw new IllegalArgumentException(Simulacao.getFrasePelaChave("uc1.setListaMateriais"));
        }
        this.listaMateriais = listaMateriais;
    }

    /**
     * Altera a lista de espessuras dos diferentes materiais da parede.
     *
     * @param listaEspessuras (lista de double) A lista de espessuras dos
     * diferentes materiais.
     */
    public void setListaEspessuras(List<Double> listaEspessuras) {
        if (listaEspessuras == null) {
            throw new IllegalArgumentException(Simulacao.getFrasePelaChave("uc1.listaMateriaisParede.setListaEspessuras"));
        }
        this.listaEspessuras = listaEspessuras;

    }

    /**
     * Altera o material da parede que corresponde á posição i.
     *
     * @param i A posição para a qual estamos a alterar o material
     * @param material (material) O material.
     */
    public void setMaterial(int i, Material material) {
        if (material == null || i >= listaMateriais.size()) {
            throw new IllegalArgumentException(Simulacao.getFrasePelaChave("uc1.listaMateriaisParede.setMaterial"));
        }
        listaMateriais.remove(i);
        listaMateriais.add(i, material);
    }

    /**
     * Altera a espessura do material da parede que corresponde á posição i.
     *
     * @param i (int) A posição para a qual estamos a alterar a espessura do
     * material
     * @param espessura (double) A espessura.
     */
    public void setEspessura(int i, double espessura) {
        if (espessura <= 0 || i >= listaEspessuras.size()) {
            throw new IllegalArgumentException(Simulacao.getFrasePelaChave("uc1.listaMateriaisParede.setEspessura"));
        }
        listaEspessuras.remove(i);
        listaEspessuras.add(i, espessura);
    }

    /**
     * Adiciona um novo material á lista de materiais, adicionando a respetiva
     * espessura á lista de espessuras.
     *
     * @param material (material) O material
     * @param espessura (double) A espessura
     */
    public void adicionarMaterial(Material material, double espessura) {
        if (espessura <= 0 || material == null) {
            throw new IllegalArgumentException(Simulacao.getFrasePelaChave("uc1.listaMateriaisParede.adicionarMaterial.espessura"));
        }
        listaMateriais.add(material);
        listaEspessuras.add(espessura);
    }

    public void removerMaterial (int index){
        if ( index < 0 || index >= listaEspessuras.size()){
            throw new IllegalArgumentException(Simulacao.getFrasePelaChave("uc11.listaMateriaisParede.removerMaterial"));
        }
        listaEspessuras.remove(index);
        listaMateriais.remove(index);
    }
    
    /**
     * Retorna o numero de materiais da parede.
     *
     * @return (int) O tamanho da lista de materiais.
     */
    public int size() {
        return listaMateriais.size();
    }

    /**
     * Valida a ListaMateriaisParede.
     * 
     * @return true caso seja válida; lançamento de exceções caso contrário
     */
    public boolean validar() {
        if (listaMateriais == null) {
            throw new IllegalArgumentException(Simulacao.getFrasePelaChave("uc1.setListaMateriais"));
        }
        if (listaEspessuras == null) {
            throw new IllegalArgumentException(Simulacao.getFrasePelaChave("uc1.listaMateriaisParede.setListaEspessuras"));
        }
        if (listaMateriais.size() != listaEspessuras.size()) {
            // "O numero de materiais e de espessuras mão são coerentes."
            throw new IllegalArgumentException(Simulacao.getFrasePelaChave(""));
        }
        for (Material m : listaMateriais) {
            m.valida();
        }
        for (Double d : listaEspessuras) {
            if (d <= 0) {
                throw new IllegalArgumentException(Simulacao.getFrasePelaChave("uc1.listaMateriaisParede.setEspessura"));
            }
        }
        return true;
    }

    /**
     * Compara dois objetos.
     * 
     * @param obj Obejto a comparar
     * @return true caso sejam iguais; false caso contrário
     */
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        ListaMateriaisParede lmp = (ListaMateriaisParede) obj;
        return listaMateriais.equals(lmp.getListaMateriais())
                && listaEspessuras.equals(lmp.getListaEspessuras());
    }
}
