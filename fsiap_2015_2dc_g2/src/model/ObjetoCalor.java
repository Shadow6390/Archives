/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import model.interfaces.CondutorCalor;

/**
 * Class abstrata com implementação geral dos métodos de {@link model.interfaces.CondutorCalor}.
 * @author DC_G2
 */
public abstract class ObjetoCalor implements CondutorCalor{
    
    /**
     * A precisão da colisão utilizada no cálculo da área de colisão.
     */
    private static final double COLLISION_PRECISION=0.1;
    
    /**
     * A constante de stefan-boltzmann.
     */
    private static final double STEFAN_BOLTZMANN_CONSTANT=5.67*Math.pow(10,-8);
    
    /**
     * O tempo (em segundos) que passa por cada passo da simulação.
     */
    protected static double deltaTime = 60;
    
    /**
     * Transfere calor deste objeto para outro sobre a forma de condução.
     * @param other ({@link CondutorCalor}) O outro objeto a conduzir.
     */
    public void conductHeat(CondutorCalor other)
    {
        /*CondutorCalor highest=this;
        if (highest.getTemperatura()<other.getTemperatura())
        {
            highest=other;
        }*/
        if (isCollidingWith(other))
        {
            //for (int i=0;i<deltaTime;i++)
            {
                double deltaTemp = getTemperatura()-other.getTemperatura();
                ArrayList<CondutorCalor> list = new ArrayList();
                list.add(this);
                list.add(other);
            
                double Rt = getResistenciaTotalEmSerie(list);
                double deltaEnergy = deltaTemp/**deltaTime*/*Rt;
                deltaEnergy = TransferenciaCalor.caloriesToJoule(deltaEnergy)*0.01;
                double deltaTemperatura = TransferenciaCalor.jouleToCelsius(deltaEnergy);
            
                setTemperatura(getTemperatura()-deltaTemperatura);
                other.setTemperatura(other.getTemperatura()+deltaTemperatura);
            }
        }
    }
    /**
     * Transfere calor deste objeto para outro sobre a forma de convecção.
     * @param other ({@link CondutorCalor}) O outro objeto a conduzir.
     */
     public void convectHeat(CondutorCalor other)
    {
        double myArea =getAreaContactoConveccao();
        double deltaT,deltaEnergy,deltaTemperatura;
        //for (int i=0;i<deltaTime;i++)
        {
            deltaT = getTemperatura()-other.getTemperatura();
            if (deltaT>0)
            {
                deltaEnergy = getMaterial().getCoeficienteConveccao()*myArea*deltaT*0.01;
                deltaTemperatura = TransferenciaCalor.jouleToCelsius(deltaEnergy);
                this.setTemperatura(getTemperatura()-deltaTemperatura);
                other.setTemperatura(other.getTemperatura()+deltaTemperatura);
            }
        }
    }
    /**
     * Transfere calor deste objeto para outro sobre a forma de radiação.
     * O for existe devido ao facto de que a expressão da emissão de energia
     * sob a forma de radição não depende de deltaT (embora esteja expressa em J/S).
     * Logo, para fazer uma representação correta, terá de se efetuar a mesma operção
     * t vezes.
     * @param other ({@link CondutorCalor}) O outro objeto a conduzir.
     * //@deprecated Método de resolução da emição de radiação ainda não concluído.
     */
    public void radiateHeat(CondutorCalor other)
    {
        //for (int i=0;i<deltaTime;i++)
        {
            double kelvin = Math.pow(getTemperatura()+273.15d,4);
            double energiaRadiada = getMaterial().getCoeficienteRadiacao()*STEFAN_BOLTZMANN_CONSTANT
                *getAreaContactoConveccao()*kelvin*0.01;
        
            double otherKelvin = Math.pow(other.getTemperatura()+273.15d,4)*0.01;
            double otherEnergiaRadiada = getMaterial().getCoeficienteRadiacao()*STEFAN_BOLTZMANN_CONSTANT
                *getAreaContactoConveccao()*otherKelvin;
        
        
            setTemperatura(getTemperatura()+(TransferenciaCalor.jouleToCelsius(otherEnergiaRadiada)));
            setTemperatura(getTemperatura()-(TransferenciaCalor.jouleToCelsius(energiaRadiada)));
        
            other.setTemperatura(other.getTemperatura()-(TransferenciaCalor.jouleToCelsius(otherEnergiaRadiada)));
            other.setTemperatura(other.getTemperatura()+TransferenciaCalor.jouleToCelsius(energiaRadiada));
        }
    }
    @Override
    public boolean isCollidingWith(CondutorCalor other)
    {
        boolean result = false;
        double[] myPos = getPosicao();
        double[] otherPos = other.getPosicao();
        double[] myDim = getDimensoes();
        double[] otherDim = other.getDimensoes();
        //Using AABB method.
        if(Math.abs(myPos[0] - otherPos[0]) <= myDim[0]*0.5 + otherDim[0]*0.5)
        {
            if(Math.abs(myPos[1] - otherPos[1]) <= myDim[1]*0.5+ otherDim[1]*0.5)
            {
                if(Math.abs(myPos[2] - otherPos[2]) <= myDim[2]*0.5+ otherDim[2]*0.5)
                {
                    result=true;
                }
            }
        }
        return result;
    }
    @Override
    public double getCollidingArea(CondutorCalor other)
    {
        double result=0;
        if (isCollidingWith(other))
        {
            double[] myDim = getDimensoes();
            double[] posicao = getPosicao();
            double[] otherDim = other.getDimensoes();
            double[] otherPos=other.getPosicao();
                    
            ServidorInstanciado.Plane myTop = new ServidorInstanciado.Plane(posicao[0],posicao[1],myDim[0],myDim[1]);
            ServidorInstanciado.Plane otherBottom = new ServidorInstanciado.Plane(otherPos[0],otherPos[1],otherDim[0],otherDim[1]);
            //Se os planos coincidirem
            if (Math.abs(posicao[2]+myDim[2]-otherPos[2])<COLLISION_PRECISION)
            {
                result+=myTop.getCollisionArea(otherBottom);
            }
            
            ServidorInstanciado.Plane myBottom = new ServidorInstanciado.Plane(posicao[0],posicao[1],myDim[0],myDim[1]);
            ServidorInstanciado.Plane otherTop = new ServidorInstanciado.Plane(otherPos[0],otherPos[1],otherDim[0],otherDim[1]);
            //Se os planos coincidirem
            if (Math.abs(posicao[2]-(otherPos[2]+otherDim[2]))<COLLISION_PRECISION)
            {
                result+=myBottom.getCollisionArea(otherTop);
            }
            
            ServidorInstanciado.Plane myLeft = new ServidorInstanciado.Plane(posicao[0],posicao[2],myDim[0],myDim[2]);
            ServidorInstanciado.Plane otherRight = new ServidorInstanciado.Plane(otherPos[0],otherPos[2],otherDim[0],otherDim[2]);
            //Se os planos coincidirem
            if (Math.abs(posicao[1]-(otherPos[1]+otherDim[1]))<COLLISION_PRECISION)
            {
                result+=myLeft.getCollisionArea(otherRight);
            }
            
            ServidorInstanciado.Plane myRight = new ServidorInstanciado.Plane(posicao[0],posicao[2],myDim[0],myDim[2]);
            ServidorInstanciado.Plane otherLeft = new ServidorInstanciado.Plane(otherPos[0],otherPos[2],otherDim[0],otherDim[2]);
            //Se os planos coincidirem
            if (Math.abs(posicao[1]+myDim[1]-otherPos[1])<COLLISION_PRECISION)
            {
                result+=myRight.getCollisionArea(otherLeft);
            }
            
            ServidorInstanciado.Plane myFront = new ServidorInstanciado.Plane(posicao[2],posicao[1],myDim[2],myDim[1]);
            ServidorInstanciado.Plane otherBack = new ServidorInstanciado.Plane(otherPos[2],otherPos[1],otherDim[2],otherDim[1]);
            //Se os planos coincidirem
            if (Math.abs(posicao[0]+myDim[0]-otherPos[0])<COLLISION_PRECISION)
            {
                result+=myFront.getCollisionArea(otherBack);
            }
            
            ServidorInstanciado.Plane myBack = new ServidorInstanciado.Plane(posicao[2],posicao[1],myDim[2],myDim[1]);
            ServidorInstanciado.Plane otherFront = new ServidorInstanciado.Plane(otherPos[2],otherPos[1],otherDim[2],otherDim[1]);
            //Se os planos coincidirem
            if (Math.abs(posicao[0]-(otherPos[0]+otherDim[0]))<COLLISION_PRECISION)
            {
                result+=myBack.getCollisionArea(otherFront);
            }
        }
        return result;
    }
    
    /**
     * Verifica se este objeto encontra-se dentro de outro objeto.
     * Este método deve ser utilizado para verificar se os objetos intersetam-se.
     * @param other ({@link ObjetoCalor}) O outro objeto a verificar.
     * @return (boolean) Verdadeiros se estiverem dentro um do outro.
     */
    public boolean isInsideOf(ObjetoCalor other)
    {
        boolean result = false;
        double[] myPos = getPosicao();
        double[] otherPos = other.getPosicao();
        double[] myDim = getDimensoes();
        double[] otherDim = other.getDimensoes();
        //Using AABB method.
        if(Math.abs(myPos[0] - otherPos[0]) < myDim[0]*0.5+ otherDim[0]*0.5)
        {
            if(Math.abs(myPos[1] - otherPos[1]) < myDim[1]*0.5+ otherDim[1]*0.5)
            {
                if(Math.abs(myPos[2] - otherPos[2]) < myDim[2]*0.5+ otherDim[2]*0.5)
                {
                    result=true;
                }
            }
        }
        return result;
    }

    /**
     * Devolve a resistencia total de todos os objetos da lista, assumindo que todos 
     * estes estão em série uns com os outros.
     * @param objects (ArrayList&lt;{@link CondutorCalor}&gt;) A lista de objetos condutores
     * de calores.
     * @return (double) A resistência total.
     */
    public static double getResistenciaTotalEmSerie(ArrayList<CondutorCalor> objects)
    {
        double result=0;
        for (int i=0;i<objects.size()-1;i++)
        {
            result+=objects.get(i).getResistencia(objects.get(i+1));
        }
        result+=objects.get(objects.size()-1).getResistencia(objects.get(objects.size()-2));
        return result;
    }
}
