/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import model.Parede;
import model.Sala;
import model.Simulacao;
import model.TransferenciaCalor;

/**
 * Class controller que controla o decorrer da simulação.
 *
 * @author DC_G2
 */
public class CorrerSimulacaoController {

    /**
     * A duração da simulação.
     */
    private double timeQuantum;

    /**
     * A quantidade de tempo que passa a cada segundo.
     */
    private int deltaTime;
    
    private Simulacao sim;

    private TransferenciaCalor tc;
    /**
     * Indica se a simulação deve ser parada ou não.
     */
    private boolean stopSimulation;

    /**
     * Indica se a simulação deve começar de novo ou não.
     */
    private boolean resetSimulation;
    
    /**
     * Temperaturas iniciais da simulação. Repostas quando é reiniciada.
     */
    private double[] initTemps;

    /**
     * Cria uma instância de {@link CorrerSimulacaoController} com os parâmetros
     * especificados.
     *
     * @param sim ({@link model.Simulacao}) A simulação pretendida.
     */
    public CorrerSimulacaoController(Simulacao sim) {
        this.sim = sim;
        stopSimulation = false;
        resetSimulation = true;
        timeQuantum = 0;
        deltaTime=60;
        initTemps= new double[6];
        Sala sala=sim.getSala();
        initTemps[0]=sala.getTemperaturaInterior();
        initTemps[1]=sala.getTeto().getTemperatura();
        int i=2;
        for (Parede focus:sala.getListaParedes())
        {
            initTemps[i++]=focus.getTemperatura();
        }
    }

    /**
     * Devolve a diferença de tempo entre cada passo da simulação.
     * @return (int) A diferença de tempo.
     */
    public int getDeltaTime() {
        return deltaTime;
    }

    /**
     * Atribui um novo valor à diferença de tempo entre cada passo da simulação.
     * @param deltaTime int) A diferença de tempo.
     */
    public void setDeltaTime(int deltaTime) {
        this.deltaTime = deltaTime;
    }
    
    /**
     * Devolve o tempo decorrido desta simulação.
     * @return (double) O tempo decorrido.
     */
    public double getTimeElapsed()
    {
        return timeQuantum;
    }
    
    /**
     * Inicia a simulação.
     */
    public void setupSimulation() {
        Sala sala=sim.getSala();
        sala.setTemperaturaInterior(initTemps[0]);
        sala.getTeto().setTemperatura(initTemps[1]);
        int i=2;
        for (Parede focus:sala.getListaParedes())
        {
            focus.setTemperatura(initTemps[i++]);
        }
        tc = sim.setupSimulation();
        resetSimulation = false;
    }

    /**
     * Atualiza as temperaturas dos objetos.
     */
    public void updateTemperatures() {
        if (!stopSimulation) {
            if (resetSimulation) {
                setupSimulation();
                timeQuantum=0;
            }
            for (int i=0;i<getDeltaTime();i++)
            {
                for (int j=0;j<100;j++)
                {
                    tc.conductHeat();
                    tc.convectHeat();
                    tc.radiateHeat();
                    tc.stepCPUDissipation();
                    updateTemperaturaExterna();
                }
            }
        }
    }
    

    /**
     * Transfere a energia sobre a forma de calor dissipada pelo cpu do servidor
     * para o servidor.
     * <p>
     * Efetua esta operação para cada servidor.
     */
    protected void stepCPUDissipation()
    {
        if (!stopSimulation)
        {
            tc.stepCPUDissipation();
        }
    }
    
    /**
     * Incrementa o tempo da simulação.
     *
     * @return (double) O tempo incrementado.
     */
    public double stepTime() {
        double result=0;
        if (!stopSimulation)
        {
            timeQuantum+=deltaTime/60;
            result=timeQuantum;
        }
        return result;
    }

    /**
     * Devolve a energia gasta para refrigerar a sala no tempo atual da
     * simulação.
     *
     * @return (double) A energy, em Joule, necessária para refrigerar a sala.
     */
    public double getRefrigerationCost() {
        return tc.getRefrigerationCost();
    }

    /**
     * Atualiza a temperatura externa.
     * <p>
     * Esta atualização só ocorre caso seja definida uma variação de temperatura
     * média para a sala da simulação.
     *
     * @return (boolean) Verdadeiro se a temperatura foi variada. Senão,
     * significa que não existe variação de temperatura para o exterior da sala.
     */
    protected boolean updateTemperaturaExterna() {
        return (!stopSimulation) ? tc.updateTemperaturaExterna(timeQuantum) : false;
    }
    
    /**
     * Devolve o valor da temperatura interior.
     * @return (double) O valor da temperatura interior.
     */
    public double getTemperaturaInterior()
    {
        return sim.getSala().getTemperaturaInterior();
    }
    
    /**
     * Devolve o valor da temperatura interior alvo.
     * @return (double) O valor da temperatura interior alvo.
     */
    public double getTemperaturaInteriorAlvo()
    {
        return sim.getSala().getTemperaturaInteriorAlvo();
    }

    /**
     * Simula o cenário pretendido a partir do início.
     *
     * @return (boolean) Verdadeiro caso seja possível re-iniciar a simulação.
     */
    public boolean recomecarSimulacao() {
        boolean result = false;
        if (!resetSimulation) {
            resetSimulation = result = true;
        }
        return result;
    }
    
    /**
     * Verifica se a simulação está pronta para recomeçar.
     * @return (boolean) Verdadeiro caso esteja a reiniciar.
     */
    public boolean isRestarting()
    {
        return resetSimulation;
    }

    /**
     * Verifica se a simulação está parada.
     * @return (boolean) Verdadeiro caso a simulação esteja parada.
     */
    public boolean isStopped()
    {
        return stopSimulation;
    }
    
    /**
     * Resume a simulação.
     * @return (boolean) Verdadeiro caso tenha resumido a simulação.
     */
    public boolean continuarSimulacao() {
        if (stopSimulation) {
            stopSimulation = false;
        }
        else
        {
            return false;
        }
        return !stopSimulation;
    }

    /**
     * Pára a simulação.
     * @return (boolean) Verdadeiro caso a simulação tenha parado.
     */
    public boolean pararSimulacao() {
        if (!stopSimulation) 
        {
            stopSimulation = true;
        }
        else
        {
            return false;
        }
        return stopSimulation;
    }
}
