/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.interfaces;

import model.Material;

/**
 * Esta interface contém metodos para transferir calor.
 * @author jbraga
 */
public interface CondutorCalor {
    
    /**
     * Retorna a área de contacto deste objeto com outro.
     * @param other ({@link CondutorCalor}) O outro objeto.
     * @return (double) A área de contacto do objeto.
     */
    public double getCollidingArea(CondutorCalor other);
    /**
     * Retorna a área de contacto do objeto para utilizar na convecção.
     * @return (double) A área de contacto do objeto.
     */
    public double getAreaContactoConveccao();
    /**
     * Retorna a massa do objeto.
     * @return (double) A massa do objeto.
     */
    public double getMassa();
    /**
     * Retorna o valor da temperatura do objeto.
     * @return (double) A temperatura do objeto.
     */
    public double getTemperatura();
    /**
     * Retorna o material do objeto.
     * <p>
     * O material do objeto contém as constantes relativas ao material, como
     * a emissividade e a constante de condutividade térmica.
     * @return ({@link model.Material}) O material do objeto.
     */
    public Material getMaterial();
    /**
     * Atribui o valor da temperatura deste objeto.
     * @param temp (double) O novo valor da temperatura.
     */
    public void setTemperatura(double temp);
    /**
     * Verifica se o objeto é um objeto que admite colisões.
     * @return (boolean) Verdadeiro se admite colisões.
     */
    public boolean isCollidable();
    
    /**
     * Verifica se este objeto está a colidir com outro objeto.
     * @param other ({@link CondutorCalor}) O outro objeto.
     * @return (boolean) Verdadeiro se está a colidir.
     */
    public boolean isCollidingWith(CondutorCalor other);
    
    /**
     * Retorna a posição do objeto.
     * @return (double[]) Um array contendo as posições nos eixos x, y e z do objeto.
     * <p>
     * A estrutura encontra-se da seguinte forma:<p>
     * [0]=Posição x;<p>
     * [1]=Posição y;<p>
     * [2]=Posição z.<p>
     */
    public double[] getPosicao();
    
    /**
     * Retorna as dimensoes do objeto.
     * @return (double[]) Um array contendo as dimensões nos eixos x, y e z do objeto.
     * <p>
     * A estrutura encontra-se da seguinte forma:<p>
     * [0]=Dimensão do objeto no eixo dos x;<p>
     * [1]=Dimensão do objeto no eixo dos y;<p>
     * [2]=Dimensão do objeto no eixo dos z.<p>
     */
    public double[] getDimensoes();
    
    /**
     * Devolve a resistência do condutor de calor.
     * @return (double) A resistência do condutor.
     */
    public double getResistencia(CondutorCalor other);
    
}
