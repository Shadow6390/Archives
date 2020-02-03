/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import bragasQuest.Vector3D;
import java.io.Serializable;
import static model.Simulacao.getIdiomaAtual;
import model.interfaces.CondutorCalor;

/**
 * Representa a instância de um servidor.
 * @author DC_G2
 */
public class ServidorInstanciado extends ObjetoCalor implements Serializable{
    
    /**
     * O servidor do qual esta instância se baseia.
     */
    private Servidor servidor;
    /**
     * A carga do servidor.
     */
    private double carga;
    /**
     * A posição em três dimensões.
     */
    private double[] posicao;
    
    /**
     * A temperatura desta instância.
     */
    private double temperatura;
    
    /**
     * A mínima temperatura máxima e a máxima temperatura máxima a que um servidor pode estar.
     */
    private static final double minMaxTemp=25,maxMaxTemp=90;
    
    /**
     * O identificar único desta instância.
     */
    private final int id;
    
    /**
     * O contador de identificadores.
     */
    private static int idCounter=0;
    
    /**
     * Construtor nulo de {@link ServidorInstanciado}.
     */
    public ServidorInstanciado()
    {
        posicao = new double[3];
        id=idCounter++;
    }
    
    /**
     * Cria uma instância de {@link ServidorInstanciado} com os parâmetros
     * especificados.
     * @param servidor ({@link Servidor}) O servidor que esta instância tem
     * de representar.
     */
    public ServidorInstanciado(Servidor servidor)
    {
        setServidor(servidor);
        setCarga(servidor.getCarga());
        id=idCounter++;
    }
    
    /**
     * Atribui o tipo de servidor que esta instância representa.
     * @param serv ({@link Servidor}) O tipo de servidor pretendido.
     */
    public void setServidor(Servidor serv)
    {
        servidor=serv;
    }
    
    /**
     * Atribui um novo valor à carga desta instância.
     * <p>
     * A carga de um servidor varia entre 0 e 100%.
     * @param carga (double) O novo valor da carga.
     */
    public void setCarga(double carga)
    {
        if (carga<0 || carga>100)
        {
            //To be replaced with Locale.
            throw new IllegalArgumentException(getIdiomaAtual().getString("uc10.servidor_instanciado.carga_invalida"));
        }
        this.carga=carga;
    }
    
    /**
     * Atribui a temperatura desta instância.
     * @param temp (double) O novo valor da temperatura.
     */
    public void setTemperatura(double temp)
    {
        temperatura=temp;
    }
    /**
     * Atribui a posição desta instância.
     * @param pos (double[]) A nova posição da instância.
     */
    public void setPosicao(double[] pos)
    {
        posicao=pos;
    }
    
    /**
     * Retorna o valor da carga desta instância de servidor.
     * @return (double) O valor da carga.
     */
    public double getCarga()
    {
        return carga;
    }
    
    /**
     * Retorna o valor da temperatura desta instância.
     * @return (double) A temperatura da instância
     */
    public double getTemperatura()
    {
        return temperatura;
    }
    
    /**
     * Retorna a posicao desta instância de servidor.
     * @return (double[]) A posicao desta instância.
     */
    public double[] getPosicao()
    {
        return posicao;
    }
    
    /**
     * Transfere a energia sobre a forma de calor dissipada pelo cpu do servidor
     * para o servidor.
     */
    public void stepDissipation()
    {
        if (temperatura<((maxMaxTemp-minMaxTemp)*carga*0.01)+25)
        {
            temperatura+=carga*0.01*TransferenciaCalor.jouleToCelsius(Servidor.getHeatDissipation())*0.001;
        }
    }

    @Override
    public double getAreaContactoConveccao() {
        //Let's assume that it will be in contact with 5 faces of this server.
        double[] myDim = getDimensoes();
        double a1 = (myDim[0])*(myDim[1]); //Bottom and up areas.
        double a2 = (myDim[1])*(myDim[2]); //Front and back areas.
        double a3 = (myDim[0])*(myDim[2]); //Left and right areas.
        return a1+2d*a2+2d*a3;
    }

    @Override
    public double getMassa() {
        return servidor.getMassa();
    }

    @Override
    public Material getMaterial() {
        return servidor.getMaterial();
    }

    @Override
    public boolean isCollidable() {
        return true;
    }

    @Override
    public double[] getDimensoes() {
        double[] result = new double[3];
        result[0]=servidor.getComprimento();
        result[1]=servidor.getLargura();
        result[2]=servidor.getAltura();
        return result;
    }

    @Override
    public double getResistencia(CondutorCalor other) {
        /*
        TO-DO: GET ORIENTATION FROM getCollidingArea(other) AND CHANGE servidor.getComprimento()
        ACCORDINGLY.
        */
        double depth=0;
        Vector3D myPos = new Vector3D(posicao);
        Vector3D otherPos = new Vector3D(other.getPosicao());
        Vector3D result = myPos.minus(otherPos);
        double angleHor = Vector3D.getAngleBetween(new Vector3D(0,1,0), otherPos);
        double angleVer = Vector3D.getAngleBetween(new Vector3D(1,0,0), otherPos);
        double anglePlane = Vector3D.getAngleBetween(new Vector3D(0,0,1), otherPos);
        
        double minAngle = Math.min(Math.min(angleHor,angleVer), anglePlane);
        if (minAngle==angleHor)
        {
            depth=servidor.getComprimento();
        }
        else if (minAngle==anglePlane)
        {
            depth=servidor.getAltura();
        }
        else if (minAngle==angleVer)
        {
            depth=servidor.getLargura();
        }
        return depth/(servidor.getMaterial().getCoeficienteConducao()*getCollidingArea(other));
    }
    
    @Override
    public String toString()
    {
        return String.format(getIdiomaAtual().getString("uc10.servidor_instanciado.info"), posicao[0],posicao[1],posicao[2],carga,getTemperatura(),servidor.getNome());
    }
    
    @Override
    public boolean equals(Object other)
    {
        boolean result = other!=null && getClass().equals(other.getClass());
        if (result)
        {
            ServidorInstanciado i = (ServidorInstanciado) other;
            result = this==other || id==i.id;
        }
        return result;
    }
    
    /**
     * Representa um plano paralelo a dois eixos.
     */
    protected static class Plane
    {
        public double x;
        public double y;
        public double width;
        public double height;
    
        /**
         * Construtor nulo de {@link Plane}.
         */
        public Plane()
        {
            
        }
        /**
         * Cria uma instância de {@link Plane} com os parâmetros especificados.
         * @param x (double) A posição x deste plano.
         * @param y (double) A posição y deste plano.
         * @param w (double) O comprimento deste plano.
         * @param h (double) A largura deste plano.
         */
        public Plane(double x,double y,double w,double h)
        {
            this.x=x;
            this.y=y;
            width=w;
            height=h;
        }
        /**
         * Retorna a área de colisão com outro plano.
         * @param other ({@link Plane}) O plano com que este irá colidir.
         * @return (double) O valor da área.
         * Devolve zero se não estiverem a colidir.
         */
        public double getCollisionArea(Plane other)
        {
            double result=0;
            if (isCollidingWith(other))
            {
            
                result = Math.max(0d,Math.min(x+width,other.x+other.width)-Math.max(x,other.x))
                    * Math.max(0d,Math.min(y+height, other.y+height)-Math.max(y,other.y));
            }
            
            return result;
        }
        /**
         * Verifica se este plano está a colidir com um outro plano especificado.
         * @param other ({@link Plane}) O plano especificado.
         * @return (boolean) Verdadeiro se estiver a colidir.
         */
        public boolean isCollidingWith(Plane other)
        {
            boolean result = false;
            //Using AABB method.
            if(Math.abs(x - other.x) <= width+ other.width)
            {
                if(Math.abs(y - other.y) <= height+ other.height)
                {
                    result=true;
                }
            }
            return result;
        }
    }
}