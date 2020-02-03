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
 * Classe responsável por transferir calor entre vários objetos e a sala.
 */
public class TransferenciaCalor implements Serializable{
    
    /**
     * Conversão de Celsius para Joule (1J = 0.00053ºC).
     */
    private static final double CELSIUS_TO_JOULE_CONSTANT=0.00053;
    
    /**
     * Conversão de Calorias para Joule (1J= 4.18Cal).
     */
    private static final double CALORIES_TO_JOULE=4.18;
    
    private ArrayList<ObjetoCalor> listaCondutores;
    private Sala sala;
    
    public TransferenciaCalor()
    {
        listaCondutores=new ArrayList();
    }
    
    /**
     * Efetua todas as transferência de calor sobre a forma de condução.
     */
    public void conductHeat()
    {
        ArrayList<ObjetoCalor> collidables = new ArrayList();
        for (ObjetoCalor focus:listaCondutores)
        {
            if (focus.isCollidable())
            {
                if (!collidables.add(focus)) 
                    throw new RuntimeException("Erro ao agrupar objetos que admitem colisões");
            }
        }
        for (int i=0;i<collidables.size();i++)
        {
            ObjetoCalor element = collidables.get(i);
            for (int j=i+1;j<collidables.size();j++)
            {
                ObjetoCalor element2=collidables.get(j);
                if (!element.equals(element2))
                {
                    element.conductHeat(element2);
                }
            }
        }        
    }
    
    /**
     * Efetua todas as transferências de calor sobre a forma de convecção.
     */
    public void convectHeat()
    {
        for (int i=0;i<listaCondutores.size();i++)
        {
            ObjetoCalor element = listaCondutores.get(i);
            if (!(element instanceof Parede))
            {
                element.convectHeat(sala);
            }
        }
        List<Parede> paredes=sala.getListaParedes();
        for (Parede focus:paredes)
        {
                focus.convectHeatExternal(sala);
        }
        sala.convectHeatToRoof();
        sala.getTeto().convectHeatExternal(sala);
    }
    
    /**
     * Efetua todas as transferências de calor sobre a forma de radiação.
     */
    public void radiateHeat()
    {
        for (int i=0;i<listaCondutores.size();i++)
        {
            ObjetoCalor element = listaCondutores.get(i);
            element.radiateHeat(sala);
        }
    }
    
    /**
     * Transfere a energia sobre a forma de calor dissipada pelo cpu do servidor
     * para o servidor.
     * <p>
     * Efetua esta operação para cada servidor.
     */
    public void stepCPUDissipation()
    {
        for (ObjetoCalor focus:listaCondutores)
        {
            if (focus instanceof ServidorInstanciado)
            {
                ((ServidorInstanciado)focus).stepDissipation();
            }
        }
    }
    
    /**
     * Atribui a sala a ser utilizada na transferencia de calor.
     * @param sala ({@link Sala}) A sala a ser atribuida.
     */
    public void setSala(Sala sala)
    {
        if (sala==null)
        {
            throw new IllegalArgumentException(Simulacao.getFrasePelaChave("uc10.sala.inexistente"));
        }
        this.sala = sala;
    }
    
    /**
     * Insere todos os objetos que conduzem calor de uma dada lista para a lista
     * a ser utilizada na transferência de calor.
     * @param list (ArrayList&lt;{@link ObjetoCalor}&gt;) A lista de objetos a serem
     * adicionados.
     * @return (boolean) Verdadeiro se todos os elementos foram adicionados.
     */
    public boolean setListaCondutores(ArrayList<ObjetoCalor> list)
    {
        listaCondutores.clear();
        return listaCondutores.addAll(list);
    }
    
    /**
     * Devolve a energia necessária para refrigerar a sala desta transferência de
     * calor.
     * @return (double) A energia, expressa em Joule.
     */
    public double getRefrigerationCost()
    {
        double result=0;
        double tempAlvo = sala.getTemperaturaInteriorAlvo();
        double tempAtual = sala.getTemperaturaInterior();
        double diff = tempAtual-tempAlvo;
        if (diff>0)
        {
            result=celsiusToJoule(diff);
        }
        return result;
    }
    
    /**
     * Atualiza a temperatura externa da sala, caso esta tenha uma variação de
     * temperatura externa.
     * @param timeQuantum (double) O tempo atual da sala.
     * @return (boolean) Verdadeiro caso tenha alterado a temperatura externa da sala.
     */
    public boolean updateTemperaturaExterna(double timeQuantum)
    {
        boolean result=false;
        if (sala.hasTemperaturaVariation()) 
        {
            double tMin = sala.getTemperaturaExternaMinima();
            double tMax = sala.getTemperaturaExternaMaxima();
            //1 dia = 1440 minutos.
            double temp =(tMin+tMax)*0.5+(0.5*(tMax-tMin)*Math.sin(2d*Math.PI*timeQuantum/1440d));
            sala.setTemperaturaExterior(temp);
            result = true;
        }
        return result;
    }
    
    /**
     * Converte celsius em joule.
     * @param celsius (double) O valor da temperatura em celsius.
     * @return (double) A energia em joule.
     */
    public static final double celsiusToJoule(double celsius)
    {
        return celsius/CELSIUS_TO_JOULE_CONSTANT;
    }
    
    /**
     * Converte joule em celsius.
     * @param joule (double) O valor da energia em joule.
     * @return (double) A temperatura em Celsius.
     */
    public static final double jouleToCelsius(double joule)
    {
        return joule*CELSIUS_TO_JOULE_CONSTANT;
    }
    
    /**
     * Converte calorias em joule.
     * @param calories (double) O valor da energia em calorias
     * @return (double) A energia em calorias.
     */
    public static final double caloriesToJoule(double calories)
    {
        return calories*CALORIES_TO_JOULE;
    }
    
}