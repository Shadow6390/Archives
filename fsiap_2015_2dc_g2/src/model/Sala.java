package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import model.interfaces.CondutorCalor;

/**
 * Representa a sala da simulação.
 *
 */
public class Sala extends ObjetoCalor implements Serializable {

    /**
     * O comprimento da sala.
     */
    private double comprimento;

    /**
     * A largura da sala.
     */
    private double largura;

    /**
     * A altura da sala.
     */
    private double altura;

    /**
     * Indica se a temperatura externa da sala pode variar sinosoidalmente com o
     * tempo.
     */
    private boolean hasTemperaturaVariation;

    /**
     * A temperatura no interior da sala.
     */
    private double temperaturaInterior;

    /**
     * A temperatura no interior da sala.
     */
    private double temperaturaInteriorAlvo;

    /**
     * A temperatura no exterior mínima da sala.
     */
    private double temperaturaExteriorMinima;

    /**
     * A temperatura no exterior máxima da sala.
     */
    private double temperaturaExteriorMaxima;

    /**
     * A temperatura no exterior da sala.
     */
    private double temperaturaExterior;

    /**
     * O teto da sala.
     */
    private Parede teto;

    /**
     * As paredes da sala.
     */
    private List<Parede> listaParedes;

    /**
     * A lista de servidores instanciados.
     */
    private ListaServidoresInstanciados listaServidoresInstanciados;

    /**
     * Construtor nulo da sala.
     */
    public Sala() {
        listaParedes = new ArrayList<>();
        listaServidoresInstanciados = new ListaServidoresInstanciados();
    }

    /**
     * Constrói uma instância de sala através de um comprimento, uma largura,
     * uma altura, uma temperatura interior, uma temperatura interior alvo, uma
     * temperatura exterior minima e máxima, um teto e 4 paredes.
     *
     * @param comprimento Comprimento da sala.
     * @param largura Largura da sala.
     * @param altura Altura da sala.
     * @param temperaturaInterior Temperatura interior da sala.
     * @param temperaturaInteriorAlvo Temperatura interior alvo da sala.
     * @param temperaturaExterior Tempertura exterior.
     * @param temperaturaExteriorMinima Temperatura exterior minima à sala.
     * @param temperaturaExteriorMaxima Temperatura exterior máxima à sala.
     * @param material Material por defeito que compõe a sala.
     */
    public Sala(double comprimento, double largura, double altura,
            double temperaturaInterior, double temperaturaInteriorAlvo,
            double temperaturaExterior, double temperaturaExteriorMinima,
            double temperaturaExteriorMaxima, Material material) {
        // A sala tem 4 paredes e 1 teto que são criados com valores por defeito.
        Parede parede1 = new Parede();
        Parede parede2 = new Parede();
        Parede parede3 = new Parede();
        Parede parede4 = new Parede();
        this.listaParedes = new ArrayList<>();
        this.listaParedes.add(parede1);
        this.listaParedes.add(parede2);
        this.listaParedes.add(parede3);
        this.listaParedes.add(parede4);
        this.teto = new Parede();

        // Definir o material que constitui cada parede e a sua temperatura inicial.
        for (Parede parede : this.listaParedes) {
            parede.getListaMateriais().adicionarMaterial(material, 0.5);
            parede.setTemperatura(temperaturaInterior / 2.0);
        }
        this.teto.getListaMateriais().adicionarMaterial(material, 0.5);
        this.teto.setTemperatura(temperaturaInterior / 2.0);

        // Definir a posição e orientação de cada parede.
        parede1.setPosicao(new double[]{0, -parede1.getProfundidade(), 0});
        parede2.setPosicao(new double[]{comprimento, 0, 0});
        parede3.setPosicao(new double[]{0, largura, 0});
        parede4.setPosicao(new double[]{-parede4.getProfundidade(), 0, 0});
        parede1.setOrientacaoParede(Parede.ORIENTACAO_HORIZONTAL);
        parede2.setOrientacaoParede(Parede.ORIENTACAO_VERTICAL);
        parede3.setOrientacaoParede(Parede.ORIENTACAO_HORIZONTAL);
        parede4.setOrientacaoParede(Parede.ORIENTACAO_VERTICAL);
        this.teto.setPosicao(new double[]{0, 0, altura});
        this.teto.setAltura(this.teto.getProfundidade());
        this.teto.setOrientacaoParede(Parede.ORIENTACAO_PLANA);

        // Definir os restantes atributos da sala.
        setComprimento(comprimento);
        setLargura(largura);
        setAltura(altura);
        setTemperaturaInterior(temperaturaInterior);
        setTemperaturaInteriorAlvo(temperaturaInteriorAlvo);
        setTemperaturaExterior(temperaturaExterior);
        setTemperaturaExternaMinima(temperaturaExteriorMinima);
        setTemperaturaExternaMaxima(temperaturaExteriorMaxima);

        this.listaServidoresInstanciados = new ListaServidoresInstanciados();
    }

    /**
     * Constrói uma cópia de uma instância de sala através de um comprimento,
     * uma largura, uma altura, uma temperatura interior, uma temperatura
     * interior alvo, uma temperatura exterior minima e máxima, um teto e 4
     * paredes.
     *
     * @param outraSala Sala para a qual vai ser realizada a cópia.
     */
    public Sala(Sala outraSala) {
        setListaParedes(outraSala.getListaParedes());
        setTeto(outraSala.getTeto());
        setComprimento(outraSala.getComprimento());
        setLargura(outraSala.getLargura());
        setAltura(outraSala.getAltura());
        setTemperaturaInterior(outraSala.getTemperaturaInterior());
        setTemperaturaInteriorAlvo(outraSala.getTemperaturaInteriorAlvo());
        setTemperaturaExternaMinima(outraSala.getTemperaturaExternaMinima());
        setTemperaturaExternaMaxima(outraSala.getTemperaturaExternaMaxima());
        this.listaServidoresInstanciados = outraSala.listaServidoresInstanciados;
    }

    /**
     * Retorna o valor do comprimento da sala.
     *
     * @return (double) O comprimento.
     */
    public double getComprimento() {
        return comprimento;
    }

    /**
     * Retorna o valor do largura da sala.
     *
     * @return (double) A largura.
     */
    public double getLargura() {
        return largura;
    }

    /**
     * Retorna o valor do altura da sala.
     *
     * @return (double) A altura.
     */
    public double getAltura() {
        return altura;
    }

    /**
     * Retorna o valor da temperatura interior da sala.
     *
     * @return (double) A temperatura interior.
     */
    public double getTemperaturaInterior() {
        return temperaturaInterior;
    }

    /**
     * Retorna o valor da temperatura interior alvo da sala.
     *
     * @return (double) A temperatura interior alvo.
     */
    public double getTemperaturaInteriorAlvo() {
        return temperaturaInteriorAlvo;
    }

    /**
     * Retorna o valor da temperatura exterior minima da sala.
     *
     * @return (double) A temperatura exterior minima.
     */
    public double getTemperaturaExternaMinima() {
        return temperaturaExteriorMinima;
    }

    /**
     * Retorna o valor da temperatura exterior maxima da sala.
     *
     * @return (double) A temperatura exterior maxima.
     */
    public double getTemperaturaExternaMaxima() {
        return temperaturaExteriorMaxima;
    }

    /**
     * Retorna o teto da sala.
     *
     * @return (parede) O teto.
     */
    public Parede getTeto() {
        return teto;
    }

    /**
     * Retorna as paredes da sala.
     *
     * @return (list de paredes) As paredes.
     */
    public List<Parede> getListaParedes() {
        return listaParedes;
    }

    /**
     * Devolve uma lista com os servidores instanciados na sala.
     *
     * @return (List&lt;{@link ServidorInstanciado}&gt;) A lista de servidores
     * instanciados.
     */
    public List<ServidorInstanciado> getListaServidoresInstanciados() {
        return listaServidoresInstanciados.getListaServidoresInstanciados();
    }

    /**
     * Altera o valor do comprimento da sala.
     *
     * @param comprimento (double) O comprimento.
     */
    public final void setComprimento(double comprimento) {
        if (comprimento <= 0) {
            throw new IllegalArgumentException(Simulacao.getFrasePelaChave(
                    "uc1.setComprimento"));
        }

        this.teto.setComprimento(comprimento);
        for (Parede parede : this.listaParedes) {
            if (parede.getOrientacaoParede() == Parede.ORIENTACAO_HORIZONTAL) {
                parede.setComprimento(comprimento);
            } else {
                parede.setComprimento(parede.getProfundidade());
            }
        }

        this.comprimento = comprimento;
    }

    /**
     * Altera o valor do comprimento da sala.
     *
     * @param largura (double) A largura.
     */
    public final void setLargura(double largura) {
        if (largura <= 0) {
            throw new IllegalArgumentException(Simulacao.getFrasePelaChave(
                    "uc1.sala.setLargura"));
        }

        this.teto.setLargura(largura);
        for (Parede parede : this.listaParedes) {
            if (parede.getOrientacaoParede() == Parede.ORIENTACAO_VERTICAL) {
                parede.setLargura(largura);
            } else {
                parede.setLargura(parede.getProfundidade());
            }
        }

        this.largura = largura;
    }

    /**
     * Altera o valor do altura da sala.
     *
     * @param altura (double) A altura.
     */
    public final void setAltura(double altura) {
        if (altura <= 0) {
            throw new IllegalArgumentException(Simulacao.getFrasePelaChave(
                    "uc1.setAltura"));
        }

        for (Parede parede : this.listaParedes) {
            parede.setAltura(altura);
        }

        this.altura = altura;
    }

    /**
     * Altera o valor da temperatura interior da sala.
     *
     * @param temperaturaInterior (double) A temperatura interior.
     */
    public final void setTemperaturaInterior(double temperaturaInterior) {
        if (temperaturaInterior < -273.15) {
            throw new IllegalArgumentException(Simulacao.getFrasePelaChave(
                    "uc1.sala.setTemperaturaInteriror"));
        }

        this.temperaturaInterior = temperaturaInterior;
    }

    /**
     * Altera o valor da temperatura interior alvo da sala.
     *
     * @param temperaturaInteriorAlvo (double) A temperatura interior alvo.
     */
    public final void setTemperaturaInteriorAlvo(double temperaturaInteriorAlvo) {
        if (temperaturaInteriorAlvo < -273.15) {
            throw new IllegalArgumentException(Simulacao.getFrasePelaChave(
                    "uc1.sala.setTemperaturaInterirorAlvo"));
        }

        this.temperaturaInteriorAlvo = temperaturaInteriorAlvo;
    }

    /**
     * Altera o valor da temperatura exterior minima da sala.
     *
     * @param temperaturaExteriorMinima (double) A temperatura exterior minima.
     */
    public final void setTemperaturaExternaMinima(double temperaturaExteriorMinima) {
        if (temperaturaExteriorMinima < -273.15) {
            throw new IllegalArgumentException(Simulacao.getFrasePelaChave(
                    "uc1.sala.setTemperaturaExternaMinima"));
        }

        this.temperaturaExteriorMinima = temperaturaExteriorMinima;
    }

    /**
     * Altera o valor da temperatura exterior maxima da sala.
     *
     * @param temperaturaExteriorMaxima (double) A temperatura exterior maxima.
     */
    public final void setTemperaturaExternaMaxima(double temperaturaExteriorMaxima) {
        if (temperaturaExteriorMaxima < this.temperaturaExteriorMinima) {
            throw new IllegalArgumentException(Simulacao.getFrasePelaChave(
                    "uc1.sala.setTemperaturaExternaMaxima"));
        }

        this.temperaturaExteriorMaxima = temperaturaExteriorMaxima;
    }

    /**
     * Altera o teto da sala.
     *
     * @param teto(parede) O teto.
     */
    public final void setTeto(Parede teto) {
        if (teto == null) {
            throw new IllegalArgumentException(Simulacao.getFrasePelaChave(
                    "uc1.sala.setTeto"));
        }

        this.teto = teto;
    }

    /**
     * Altera o teto da sala.
     *
     * @param listaParedes (list de parede) As paredes.
     */
    public final void setListaParedes(List<Parede> listaParedes) {
        if (listaParedes == null || listaParedes.size() != 4) {
            throw new IllegalArgumentException(Simulacao.getFrasePelaChave(
                    "uc1.sala.setListaParedes"));
        }

        this.listaParedes = listaParedes;
    }

    /**
     * Adicionar servidor à sala.
     *
     * @param servidor servidor a adicionar
     * @param pos posição do servidor
     * @param temp (double) A temperatura inicial do servidor.
     * @return true caso o servidor tenha sido adicionado, false caso contrário.
     *
     */
    public boolean addServidor(Servidor servidor, double[] pos, double temp) {
        return this.listaServidoresInstanciados.addServidor(servidor, pos, temp);
    }

    /**
     * Imprime a informação da sala.
     *
     * @return Informação da sala.
     */
    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < 4; i++) {
            str += "\n" + (i + 1) + ":\n" + this.listaParedes.get(i);
        }
        return String.format(Simulacao.getFrasePelaChave("uc1.sala.info"),
                this.comprimento, this.altura, this.largura,
                this.temperaturaInterior, this.temperaturaExterior,
                this.temperaturaExteriorMinima, this.temperaturaExteriorMaxima,
                this.teto.toString(), str);
    }

    /**
     * Valida uma sala.
     *
     * @return true se válida; false se inválida
     */
    public boolean validar() {
        if (this.comprimento <= 0) {
            throw new IllegalArgumentException(Simulacao.getFrasePelaChave(
                    "uc1.setComprimento"));
        }
        if (this.largura <= 0) {
            throw new IllegalArgumentException(Simulacao.getFrasePelaChave(
                    "uc1.sala.setLargura"));
        }

        if (this.altura <= 0) {
            throw new IllegalArgumentException(Simulacao.getFrasePelaChave(
                    "uc1.setAltura"));
        }

        if (this.temperaturaInterior < -273.15) {
            throw new IllegalArgumentException(Simulacao.getFrasePelaChave(
                    "uc1.sala.setTemperaturaInteriror"));
        }

        if (this.temperaturaInteriorAlvo < -273.15) {
            throw new IllegalArgumentException(Simulacao.getFrasePelaChave(
                    "uc1.sala.setTemperaturaInterirorAlvo"));
        }
        if (hasTemperaturaVariation) {
            if (this.temperaturaExteriorMinima < -273.15) {
                throw new IllegalArgumentException(Simulacao.getFrasePelaChave(
                        "uc1.sala.setTemperaturaExternaMinima"));
            }

            if (this.temperaturaExteriorMaxima < this.temperaturaExteriorMinima) {
                throw new IllegalArgumentException(Simulacao.getFrasePelaChave(
                        "uc1.sala.setTemperaturaExternaMaxima"));
            }
        }

        if (this.teto == null) {
            throw new IllegalArgumentException(Simulacao.getFrasePelaChave(
                    "uc1.sala.setTeto"));
        }

        if (this.listaParedes == null || this.listaParedes.size() != 4) {
            throw new IllegalArgumentException(Simulacao.getFrasePelaChave(
                    "uc1.sala.setListaParedes"));
        }

        for (Parede p : this.listaParedes) {
            p.validar();
        }

        return this.teto.validar();
    }

    /**
     * Transfere a energia da sala para o teto, sobre a forma de calor.
     */
    public void convectHeatToRoof() {
        double myArea = this.teto.getAreaContactoConveccao();
        double deltaT, deltaEnergy, deltaTemperatura;
        //for (int i=0;i<deltaTime;i++)
        {
            deltaT = getTemperaturaInterior() - this.teto.getTemperatura();
            if (deltaT > 0) {
                deltaEnergy = 15d* myArea * deltaT*0.01;
                deltaTemperatura = TransferenciaCalor.jouleToCelsius(deltaEnergy);
                this.teto.setTemperatura(this.teto.getTemperatura() + deltaTemperatura);
                setTemperaturaInterior(getTemperatura() - deltaTemperatura);
                //System.out.println("Teto temperatura: "+teto.getTemperatura());
            }
        }
    }

    /**
     * Retorna os dados para gerar um mapa térmico.
     *
     * @return ({@link MapaTermicoData}) Os dados do mapa térmico.
     */
    public MapaTermicoData getMapaTermico() {
        MapaTermicoData result = new MapaTermicoData();
        result.setTemperaturaInterna(this.temperaturaInterior);
        result.setTemperaturaExterna(this.temperaturaExteriorMinima);
        this.listaServidoresInstanciados.adicionaInstanciasA(result);
        return result;
    }
    
    /**
     * Remove todos os servidos instanciados desta sala.
     */
    public void removeServidoresInstanciados()
    {
        listaServidoresInstanciados.removeServidoresInstanciados();
    }

    /**
     * Indica se esta sala permite a variação da temperatura externa.
     *
     * @return (boolean) Verdadeiro caso permita.
     */
    public boolean hasTemperaturaVariation() {
        return this.hasTemperaturaVariation;
    }

    /**
     * Permite determinar se a sala irá variar a temperatura externa ou não.
     *
     * @param hasTemperaturaVariation (boolean) O valor booleano da condição
     * especificada.
     */
    public void setHasTemperaturaVariation(boolean hasTemperaturaVariation) {
        this.hasTemperaturaVariation = hasTemperaturaVariation;
    }

    /**
     * Devolve a temperatura no exterior da sala.
     *
     * @return (double) O valor da temperatura na exterior da sala.
     */
    public double getTemperaturaExterior() {
        return this.temperaturaExterior;
    }

    /**
     * Atribui a temperatura no exterior da sala.
     *
     * @param temperaturaExterior (double) O valor da temperatura exterior À
     * sala.
     */
    public final void setTemperaturaExterior(double temperaturaExterior) {
        if (temperaturaExterior<-273.15)
        {
            throw new IllegalArgumentException(Simulacao.getFrasePelaChave("uc1.tempExterior.invalid"));
        }
        this.temperaturaExterior = temperaturaExterior;
    }

    @Override
    public double getAreaContactoConveccao() {
        return 0;
    }

    @Override
    public double getMassa() {
        return 0;
    }

    @Override
    public double getTemperatura() {
        return this.temperaturaInterior;
    }

    @Override
    public Material getMaterial() {
        return null;
    }

    @Override
    public void setTemperatura(double temp) {
        setTemperaturaInterior(temp);
    }

    @Override
    public boolean isCollidable() {
        return false;
    }

    @Override
    public double[] getPosicao() {
        return null;
    }

    @Override
    public double[] getDimensoes() {
        double[] result = new double[3];
        result[0] = this.comprimento;
        result[1] = this.largura;
        result[2] = this.altura;
        return result;
    }

    @Override
    public double getResistencia(CondutorCalor other) {
        return 0;
    }
}
