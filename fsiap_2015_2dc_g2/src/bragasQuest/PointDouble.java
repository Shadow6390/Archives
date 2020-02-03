/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bragasQuest;

/**
 * Uma classe que representa um ponto 2D com valores double.
 * @author Diogo
 */
public class PointDouble {
    
    /**
     * O valor do ponto na posição x.
     */
    public double x;
    
    /**
     * O valor do ponto na posição y.
     */
    public double y;
    
    /**
     * Construtor nulo de {@link PointDouble}.
     */
    public PointDouble()
    {
        
    }
    
    /**
     * Cria uma instância de {@link PointDouble} com os parâmetros especificados.
     * @param x (double) O valor do ponto na posição x.
     * @param y (double) O valor do ponto na posição y.
     */
    public PointDouble(double x,double y)
    {
        this.x=x;
        this.y=y;
    }
}
