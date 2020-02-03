/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 * Contém a informação que pode ser utilizada para gerar um mapa térmico.
 * @author DC_G2
 */
public class MapaTermicoData {
    
    private double temperaturaInterior;
    private double temperaturaExterior;
    private ArrayList<Double> temperaturas;
    private ArrayList<Double[]> posicoes;
    
    /**
     * Construtor nulo de {@link MapaTermicoData}.
     */
    public MapaTermicoData()
    {
        temperaturas=new ArrayList();
        posicoes = new ArrayList();
    }
    
    /**
     * Adiciona dados relativos ao mapa térmico.
     * @param temp (double) A temperatura do i-ésimo objeto.
     * @param pos (double[]) As posições do i-ésimo objeto.
     */
    public boolean addData(double temp,double[] pos)
    {
        boolean result = temperaturas.add(temp);
        if (result)
        {
            result =posicoes.add(primitiveDoubleToDouble(pos));
        }
        return result;
    }
    
    /**
     * Converte um array de doubles primitivos para um array de Double do java.
     * @param arr (double[]) O array a converter.
     * @return (Double[]) Array convertido.
     */
    private Double[] primitiveDoubleToDouble(double[] arr)
    {
        Double[] result = new Double[arr.length];
        for (int i=0;i<arr.length;i++)
        {
            result[i]=arr[i];
        }
        return result;
    }

    /**
     * Retorna o valor da temperatura interior.
     * @return (double) A temperatura interior.
     */
    public double getTemperaturaInterna() {
        return temperaturaInterior;
    }

    /**
     * Atribui um novo valor à temperatura interna.
     * @param temperaturaInterior (double) O novo valor da temperatura interna.
     */
    public void setTemperaturaInterna(double temperaturaInterior) {
        this.temperaturaInterior = temperaturaInterior;
    }

    /**
     * Retorna o valor da temperatura externa.
     * @return (double) O valor da temperatura externa.
     */
    public double getTemperaturaExterna() {
        return temperaturaExterior;
    }

    /**
     * Atribui um novo valor à temperatura externa.
     * @param temperaturaExterior (double) O novo valor da temperatura externa.
     */
    public void setTemperaturaExterna(double temperaturaExterior) {
        this.temperaturaExterior = temperaturaExterior;
    }
}
