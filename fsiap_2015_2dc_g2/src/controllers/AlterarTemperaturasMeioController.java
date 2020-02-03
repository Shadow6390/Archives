package controllers;

import model.Sala;
import model.Simulacao;

public class AlterarTemperaturasMeioController {

    /**
     * Simulação à qual se vai alterar a temperatura do meio.
     */
    private Simulacao simulacao;

    /**
     * A sala desta simulação.
     */
    private Sala sala;

    /**
     * Constrói uma instância do controlador que gere o caso de uso 2, Alterar
     * Temperatura do Meio, através da simulação.
     *
     * @param simulacao Simulação a temperatura do meio vai ser alterada.
     */
    public AlterarTemperaturasMeioController(Simulacao simulacao) {
        this.simulacao = simulacao;
        this.sala = this.simulacao.getSala();
    }

    /**
     * Altera o valor da temperatura interior da sala.
     *
     * @param temperatura A temperatura interior.
     */
    public void setTemperaturaInterior(double temperatura) {
        this.sala.setTemperaturaInterior(temperatura);
    }
    
    /**
     * Altera o valor da temperatura exterior da sala.
     *
     * @param temperatura A temperatura exterior.
     */
    public void setTemperaturaExterior(double temperatura) {
        this.sala.setTemperaturaExterior(temperatura);
    }

    /**
     * Altera o valor da temperatura no interior da sala.
     * 
     * @param temperatura A temperatura interior.
     */
    public void setTemperaturaInteriorAlvo(double temperatura) {
        this.sala.setTemperaturaInteriorAlvo(temperatura);
    }

    /**
     * Altera o valor da temperatura no exterior mínima da sala.
     * 
     * @param temperatura A temperatura interior.
     */
    public void setTemperaturaExteriorMinima(double temperatura) {
        this.sala.setTemperaturaExternaMinima(temperatura);
    }

    /**
     * Altera o valor da temperatura no exterior máxima da sala.
     * 
     * @param temperatura A temperatura interior.
     */
    public void setTemperaturaExteriorMaxima(double temperatura) {
        this.sala.setTemperaturaExternaMaxima(temperatura);
    }
    
    /**
     * Permite ditar se a temperatura externa da sala varia ou não.
     * @param value (boolean) O valor da condição acima. Verdadeiro indica que tem 
     * variação de temperatura.
     */
    public void setHasTemperaturaVariation(boolean value)
    {
        sala.setHasTemperaturaVariation(value);
    }
    
    /**
     * Devolve a temperatura interior da sala.
     * @return (double) A temperatura interior da sala.
     */
    public double getTemperaturaInterior()
    {
        return sala.getTemperaturaInterior();
    }
    
    /**
     * Devolve a temperatura interior alvo da sala.
     * @return (double) A temperatura interior alvo da sala.
     */
    public double getTemperaturaInteriorAlvo()
    {
        return sala.getTemperaturaInteriorAlvo();
    }
    
    /**
     * Devolve a temperatura exterior da sala.
     * @return (double) A temperatura exterior da sala.
     */
    public double getTemperaturaExterior()
    {
        return sala.getTemperaturaExterior();
    }
    
    /**
     * Devolve a temperatura exterior mínima da sala.
     * @return (double) A temperatura exterior mínima da sala.
     */
    public double getTemperaturaExteriorMinima()
    {
        return sala.getTemperaturaExternaMinima();
    }
    
    /**
     * Devolve a temperatura exterior máxima da sala.
     * @return (double) A temperatura exterior máxima da sala.
     */
    public double getTemperaturaExteriorMaxima()
    {
        return sala.getTemperaturaExternaMaxima();
    }
    
    /**
     * Devolve se a sala aceita variação da temperatura ou não.
     * @return (boolean) Verdadeiro se a temperatura exterior da sala varia.
     */
    public boolean hasTemperaturaVariation()
    {
        return sala.hasTemperaturaVariation();
    }
    
    /**
     * Valida a sala a alterar.
     * 
     * @return true se a sala for válida.
     */
    public boolean valida(){
        return this.sala.validar();
    }
    
    /**
     * Altera a sala na simulação.
     */
    public void alterarSala(){
        this.simulacao.setSala(this.sala);
    }
}
