/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import model.interfaces.CondutorCalor;

/**
 * Representa uma parede da sala da simulação.
 *
 * @author DC_G2
 */
public class Parede extends ObjetoCalor implements Serializable {

    /**
     * Valor que permite identificar a orientação da parede como vertical.
     * Exemplo:
     * <p>
     * |
     * <p>
     * |
     */
    public static final int ORIENTACAO_VERTICAL = 0;

    /**
     * Valor que permite identificar a orientação da parede como horizontal.
     * Exemplo:
     * <p>
     *
     * -------------
     */
    public static final int ORIENTACAO_HORIZONTAL = 1;

    /**
     * Valor que permite identificar a orientação da parede como plana.
     * Exemplo:
     * <p>
     * -------------
     * <p>
     * |            |
     * <p>
     * -------------
     */
    public static final int ORIENTACAO_PLANA = 2;

    /**
     * Lista de materiais da parede.
     */
    private ListaMateriaisParede listaMateriais;

    /**
     * O comprimento da parede.
     */
    private double comprimento;

    /**
     * A altura da parede.
     */
    private double altura;

    /**
     * A largura da parede.
     */
    private double largura;

    /**
     * A orientação da parede. Pode ser HORIZONTAL,VERTICAL ou PLANA.
     */
    private int orientacaoParede;

    /**
     * A temperatura da parte exterior da parede.
     */
    private double temperatura;

    /**
     * A massa da parede.
     */
    private double massa;

    /**
     * A posição da parede, em que os indices 0, 1 e 2 correspondem a x, y e z,
     * respetivamente.
     */
    private double[] posicao;

    /**
     * Construtor vazio da parede.
     */
    public Parede() {
        listaMateriais = new ListaMateriaisParede();
        posicao = new double[3];
    }
    
    /**
     * Construtor clone de {@link Parede}.
     * @param origin ({@link Parede}) A parede fonte de onde copiar.
     */
    public Parede(Parede origin)
    {
        listaMateriais= new ListaMateriaisParede(origin.listaMateriais);
        posicao = origin.posicao.clone();
        massa = origin.massa;
        largura = origin.largura;
        altura = origin.altura;
        comprimento = origin.comprimento;
        orientacaoParede = origin.orientacaoParede;
        temperatura = origin.temperatura;
    }

    /**
     * Retorna a lista de materiais da parede.
     *
     * @return (lista de materiais) A lista de materiais.
     */
    public ListaMateriaisParede getListaMateriais() {
        return listaMateriais;
    }

    /**
     * Retorna o valor do comprimento da parede.
     *
     * @return (double) O comprimento.
     */
    public double getComprimento() {
        return comprimento;
    }

    /**
     * Retorna o valor da altura da parede.
     *
     * @return (double) A altura.
     */
    public double getAltura() {
        return altura;
    }

    /**
     * Retorna o vetor correspondente ás coordenadas da parede.
     *
     * @return (vetor de double) Coordenadas da parede.
     */
    @Override
    public double[] getPosicao() {
        return posicao;
    }

    /**
     * Devolve o material que se encontra em contacto com o exterior.
     *
     * @return ({@link Material}) O material exterior.
     */
    public Material getMaterialExterior() {
        return listaMateriais.getMaterial(listaMateriais.size() - 1);
    }

    @Override
    public double getTemperatura() {
        return temperatura;
    }

    /**
     * Transfere calor desta parede para o exterior da sala.
     *
     * @param other ({@link Sala}) A sala a utilizar..
     */
    public void convectHeatExternal(Sala other) {
        double myArea = getAreaContactoConveccao();
        double deltaT, deltaEnergy, deltaTemperatura;
        //for (int i=0;i<deltaTime;i++)
        {
            deltaT = getTemperatura() - other.getTemperaturaExterior();
            //System.out.println("OBTAINED DELTA T OF "+deltaT);
            if (deltaT > 0) {
                deltaEnergy = getMaterial().getCoeficienteConveccao() * myArea * deltaT*0.01;
                deltaTemperatura = TransferenciaCalor.jouleToCelsius(deltaEnergy);
                this.setTemperatura(getTemperatura() - deltaTemperatura);
                //Desnecessário, dado que a massa de ar do exterior da sala é infinito
                //other.setTemperaturaExterior(other.getTemperatura()+deltaTemperatura);
            }
        }
    }

    /**
     * Retorna o valor da profundidade da parede. A profundidade depende da
     * orientação.
     *
     * @return (double) A profundidade.
     */
    public double getProfundidade() {
        double result = 0;
        for (Double d : listaMateriais.getListaEspessuras()) {
            result += d;
        }
        return result;
    }

    /**
     * Altera a lista de materiais da parede.
     *
     * @param listaMateriais (lista de materiais) A lista de materiais.
     */
    public void setListaMateriais(ListaMateriaisParede listaMateriais) {
        if (listaMateriais == null) {
            throw new IllegalArgumentException(Simulacao.getFrasePelaChave("uc1.setListaMateriais"));
        }
        this.listaMateriais = listaMateriais;

    }

    /**
     * Altera o valor do comprimento da parede.
     *
     * @param comprimento (double) O comprimento.
     */
    public void setComprimento(double comprimento) {
        if (comprimento <= 0) {
            throw new IllegalArgumentException(Simulacao.getFrasePelaChave("uc1.setComprimento"));
        }
        this.comprimento = comprimento;
    }

    /**
     * Altera o valor da altura da parede.
     *
     * @param altura (double) A altura.
     */
    public void setAltura(double altura) {
        if (altura <= 0) {
            throw new IllegalArgumentException(Simulacao.getFrasePelaChave("uc1.setAltura"));
        }
        this.altura = altura;
    }

    /**
     * Retorna o vetor correspondente ás coordenadas da parede.
     *
     * @param posicao posição da parede.
     */
    public void setPosicao(double[] posicao) {
        if (posicao == null) {
            throw new IllegalArgumentException(); //alterar
        }
        this.posicao = posicao;
    }

    /**
     * Altera o valor da altura da parede.
     *
     * @param temp
     */
    @Override
    public void setTemperatura(double temp) {
        if (temp<-273.15)
        {
            throw new IllegalArgumentException(Simulacao.getFrasePelaChave("uc1.parede.temperatura.erro"));
        }
        this.temperatura = temp;
    }
    
    /**
     * Valida a Parede.
     *
     * @return true caso seja válida; lançamento de exceções caso contrário
     */
    public boolean validar() {
        if (listaMateriais == null) {
            throw new IllegalArgumentException(Simulacao.getFrasePelaChave("uc1.setListaMateriais"));
        }
        if (comprimento <= 0) {
            throw new IllegalArgumentException(Simulacao.getFrasePelaChave("uc1.setComprimento"));
        }
        if (altura <= 0) {
            throw new IllegalArgumentException(Simulacao.getFrasePelaChave("uc1.setAltura"));
        }
        return listaMateriais.validar();
    }

    @Override
    public String toString() {
        String aux = "";
        for (Material mat : this.listaMateriais.getListaMateriais()) {
            aux += mat.toString();
        }
        return String.format(Simulacao.getFrasePelaChave("uc1.parede.info"), aux,
                this.comprimento, this.altura, this.temperatura, this.massa);
    }

    /**
     * Compara dois objetos.
     *
     * @param obj Objeto a comparar
     * @return true caso sejam iguais; false caso contrário
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Parede p = (Parede) obj;
        return comprimento == p.getComprimento() && altura == p.getAltura() && listaMateriais.equals(p.getListaMateriais());
    }

    @Override
    public double getAreaContactoConveccao() {
        double result = 0;
        switch (orientacaoParede) {
            case ORIENTACAO_HORIZONTAL:
                result = largura * altura;
                break;
            case ORIENTACAO_VERTICAL:
                result = comprimento * altura;
                break;
            case ORIENTACAO_PLANA:
                result = comprimento * largura;
                break;
        }
        return result;
    }

    @Override
    public double getMassa() {
        return massa;
    }

    @Override
    public Material getMaterial() {
        return listaMateriais.getMaterial(0);
    }

    @Override
    public boolean isCollidable() {
        return true;
    }

    @Override
    public double[] getDimensoes() {
        double[] result = new double[3];
        result[0] = comprimento;
        result[1] = largura;
        result[2] = altura;
        return result;
    }

    @Override
    public double getResistencia(CondutorCalor other) {
        double result = 0;
        for (int i = 0; i < listaMateriais.size(); i++) {
            //Area conveccção pode ser utilizada, visto que só calcula a sua própria resistência.
            result += listaMateriais.getEspessura(i) / (listaMateriais.getMaterial(i).getCoeficienteConducao() * getAreaContactoConveccao());
        }
        return result;
    }

    /**
     * @return the largura
     */
    public double getLargura() {
        return largura;
    }

    /**
     * @param largura the largura to set
     */
    public void setLargura(double largura) {
        this.largura = largura;
    }

    /**
     * @return the orientacaoParede
     */
    public int getOrientacaoParede() {
        return orientacaoParede;
    }

    /**
     * @param orientacaoParede the orientacaoParede to set
     */
    public void setOrientacaoParede(int orientacaoParede) {
        this.orientacaoParede = orientacaoParede;
    }
}
