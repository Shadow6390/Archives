/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bragasQuest;

import model.Simulacao;

/**
 * Representa um vetor em 3D.
 */
public class Vector3D {
    /**
     * Componente x do vetor.
     */
    public double x;
    
    /**
     * Componente y do vetor.
     */
    public double y;
    
    /**
     * Componente z do vetor.
     */
    public double z;
    
    public Vector3D()
    {
        
    }
    
    /**
     * Cria uma instância de {@link Vector3D} com os parâmetros especificados.
     * @param x (double) Componente x do vetor.
     * @param y (double) Componente y do vetor.
     * @param z (double) Componente z do vetor.
     */
    public Vector3D(double x,double y,double z)
    {
        this.x=x;
        this.y=y;
        this.z=z;
    }
    
    /**
     * Cria uma instância de {@link Vector3D} com os parâmetros especificados.
     * @param arr (double[]) Um array de doubles com as posições.
     */
    public Vector3D(double[] arr)
    {
        if (arr.length!=3)
        {
            throw new IllegalArgumentException(Simulacao.getFrasePelaChave("extra.vector3d.invalidLength"));
        }
        x=arr[0];
        y=arr[1];
        z=arr[2];
    }
    
    /**
     * Devolve o módulo deste vetor.
     * @return (double) O módulo do vetor.
     */
    public double getModulo()
    {
        return Math.sqrt(x*x + y*y + z*z);
    }
    
    /**
     * Realiza uma subtração entre as componentes deste vetor e de um outro vetor.
     * <p>
     * Equivalente a: A-B.
     * @param other ({@link Vector3D}) O outro vetor (vetor B).
     * @return ({@link Vector3D}) O novo vetor.
     */
    public Vector3D minus(Vector3D other)
    {
        Vector3D result = new Vector3D();
        result.x=x-other.x;
        result.y=y-other.y;
        result.z=z-other.z;
        return result;
    }
    
    /**
     * Realiza o produto externo entre dois vetores.
     * @param vec1 ({@link Vector3D}) O primeiro vetor.
     * @param vec2 ({@link Vector3D}) O segundo vetor.
     * @return ({@link Vector3D}) O resultado final.
     */
    public static final Vector3D produtoExterno(Vector3D vec1,Vector3D vec2)
    {
        Vector3D result = new Vector3D();
        result.x=(vec1.y*vec2.z)-(vec1.z*vec2.y);
        result.y=(vec1.z*vec2.x)-(vec1.x*vec2.z);
        result.z=(vec1.x*vec2.y)-(vec1.y*vec2.x);
        return result;
    }
    
    /**
     * Realiza o produto interno entre dois vetores.
     * @param vec1 ({@link Vector3D}) O primeiro vetor.
     * @param vec2 ({@link Vector3D}) O segundo vetor.
     * @return (double) O produto interno.
     */
    public static final double produtoInterno(Vector3D vec1,Vector3D vec2)
    {
        double result=(vec1.x*vec2.x)+(vec1.y*vec2.y)+(vec1.z*vec2.z);
        return result;
    }
    
    /**
     * Devolve o ângulo entre dois vetores.
     * @param vec1 ({@link Vector3D}) O primeiro vetor.
     * @param vec2 ({@link Vector3D}) O segundo vetor.
     * @return (double) O ângulo entre dois vetores.
     */
    public static final double getAngleBetween(Vector3D vec1,Vector3D vec2)
    {
        double result=produtoInterno(vec1,vec2)/(vec1.getModulo()*vec2.getModulo());
        result = Math.acos(result);
        return result;
    }
}
