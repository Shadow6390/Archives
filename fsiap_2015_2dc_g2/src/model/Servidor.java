/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 * Representa um servidor usado na simulação.
 * 
 * @author DC_G2
 */
public class Servidor implements Serializable{

    /**
     * Nome do servidor.
     */
    private String nome;

    /**
     * Material composto.
     */
    private Material material;

    /**
     * Massa, comprimento, largura, carga e altura do servidor.
     */
    private double massa, comprimento, largura, carga, altura;

    /**
     * Potência da dissipação térmica do processador deste servidor.
     * <p>
     * Assume-se que este servidor está equipado com um processador  Core i7-5820K,
     * que dissipa 140W sob a forma de calor (https://en.wikipedia.org/wiki/List_of_CPU_power_dissipation_figures).
     */
    private static final double heatDissipation = 140;
    
    /**
     * Contrutor vazio do Servidor.
     */
    public Servidor() {
    }

    /**
     * Construtor completo do servidor que constroi uma instância com os 
     * parâmetros fornecidos.
     *
     * @param nome nome do servidor.
     * @param material material composto.
     * @param massa massa do servidor.
     * @param comprimento comprimento do servidor.
     * @param largura largura do servidor.
     * @param carga carga do servidor.
     * @param altura altura do servidor.
     */
    public Servidor(String nome, Material material, double massa,
            double comprimento, double largura, double carga, double altura) {
        this.nome = nome;
        this.material = material;
        this.massa = massa;
        this.comprimento = comprimento;
        this.largura = largura;
        this.carga = carga;
        this.altura = altura;
    }

    /**
     * Copia uma instância de {@link Servidor} a partir de outro servidor
     * especificado.
     *
     * @param serv ({@link Servidor}) O servidor a ser copiado.
     */
    public Servidor(Servidor serv) {
        this.nome = serv.nome;
        this.material = serv.material;
        this.massa = serv.massa;
        this.comprimento = serv.comprimento;
        this.largura = serv.largura;
        this.carga = serv.carga;
        this.altura = serv.altura;
    }

    /**
     * Devolve o nome do servidor.
     * 
     * @return o nome do servidor.
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Devolve o material composto.
     * 
     * @return material composto.
     */
    public Material getMaterial() {
        return this.material;
    }

    /**
     * Devolve a massa do servidor.
     * 
     * @return a massa do servidor.
     */
    public double getMassa() {
        return this.massa;
    }

    /**
     * Devolve o comprimento do servidor.
     * 
     * @return o comprimento do servidor.
     */
    public double getComprimento() {
        return this.comprimento;
    }

    /**
     * Devolve a largura do servidor.
     * 
     * @return a largura do servidor.
     */
    public double getLargura() {
        return this.largura;
    }

    /**
     * Devolve a carga do servidor.
     * 
     * @return a carga do servidor.
     */
    public double getCarga() {
        return this.carga;
    }

    /**
     * Devolve a altura do servidor.
     * 
     * @return a altura do servidor.
     */
    public double getAltura() {
        return this.altura;
    }

    /**
     * Define o nome do servidor.
     * 
     * @param nome o nome do servidor pretendido.
     */
    public void setNome(String nome) {
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException(Simulacao.getFrasePelaChave("uc4.servidor.setNome"));
        }
        this.nome = nome;
    }

    /**
     * Define o material composto.
     * 
     * @param material o material pretendido.
     */
    public void setMaterial(Material material) {
        if (material == null || !material.valida()) {
            //"O Material informado é inválido"
            throw new IllegalArgumentException(Simulacao.getFrasePelaChave("uc4.servidor.setMaterial"));
        } else {
            this.material = material;
        }
    }

    /**
     * Atribui o valor da massa do servidor.
     * <p>
     * O valor da massa encontra-se nas unidades SI (kg).
     *
     * @param massa a massa do servidor pretendida.
     */
    public void setMassa(double massa) {
        if (massa <= 0) {
            //"A massa informada é inválida"
            throw new IllegalArgumentException(Simulacao.getFrasePelaChave("uc4.servidor.setMassa"));
        } else {
            this.massa = massa;
        }
    }

    /**
     * Define o comprimento do servidor.
     * 
     * @param comprimento o comprimento do servidor pretendido.
     */
    public void setComprimento(double comprimento) {
        if (comprimento <= 0) {
            //"O comprimento informado é inválido"
            throw new IllegalArgumentException(Simulacao.getFrasePelaChave("uc4.servidor.setComprimento"));
        } else {
            this.comprimento = comprimento;
        }
    }

    /**
     * Define a largura do servidor.
     * 
     * @param largura a largura do servidor pretendida.
     */
    public void setLargura(double largura) {
        if (largura <= 0) {
            //"A largura informada é inválida"
            throw new IllegalArgumentException(Simulacao.getFrasePelaChave("uc4.servidor.setLargura"));
        } else {
            this.largura = largura;
        }
    }

    /**
     * Define a carga do servidor.
     * 
     * @param carga a carga do servidor pretendida.
     */
    public void setCarga(double carga) {
        if (carga <= 0 || carga > 100) {
            //"A carga informada é inválida"
            throw new IllegalArgumentException(Simulacao.getFrasePelaChave("uc4.servidor.setCarga"));
        } else {
            this.carga = carga;
        }
        this.carga = carga;
    }

    /**
     * Define a altura do servidor.
     * 
     * @param altura a altura do servidor pretendida.
     */
    public void setAltura(double altura) {
        if (altura <= 0) {
            //"A altura informada é inválida"
            throw new IllegalArgumentException(Simulacao.getFrasePelaChave("uc4.servidor.setAltura"));
        } else {
            this.altura = altura;
        }
    }

    // ;
    /**
     * Valida o nome , o material , a massa, o comprimento, a largura, 
     * a carga e a altura de um Servidor.
     * Retorna verdadeiro se for válido (ou seja, todos os atributos são válidos)
     * ou falso se não for válido.
     * 
     * @return verdadeiro se for válido, falso se não for.
     */
    public boolean valida() {
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException(Simulacao.getFrasePelaChave("uc4.servidor.setNome"));
        }
        if (material == null || !material.valida()) {
            //"O Material informado é inválido"
            throw new IllegalArgumentException(Simulacao.getFrasePelaChave("uc4.servidor.setMaterial"));
        }

        if (massa <= 0) {
            //"A massa informada é inválida"
            throw new IllegalArgumentException(Simulacao.getFrasePelaChave("uc4.servidor.setMassa"));
        }
        if (comprimento <= 0) {
            //"O comprimento informado é inválido"
            throw new IllegalArgumentException(Simulacao.getFrasePelaChave("uc4.servidor.setComprimento"));
        }

        if (largura <= 0) {
            //"A largura informada é inválida"
            throw new IllegalArgumentException(Simulacao.getFrasePelaChave("uc4.servidor.setLargura"));
        }

        if (carga <= 0 || carga > 100) {
            //"A carga informada é inválida"
            throw new IllegalArgumentException(Simulacao.getFrasePelaChave("uc4.servidor.setCarga"));
        }

        if (altura <= 0) {
            //"A altura informada é inválida"
            throw new IllegalArgumentException(Simulacao.getFrasePelaChave("uc4.servidor.setAltura"));
        }
        return true;
    }
    
    /**
     * Devolve o valor da potência da dissipação térmica.
     * @return (double) A potência da dissipação térmica.
     */
    public static final double getHeatDissipation()
    {
        return heatDissipation;
    }

    /**
     * Compara dois objetos.
     * 
     * @param other outro servidor a comparar.
     * @return verdadeiro se os servidores forem iguais, falso se não forem.
     */
    @Override
    public boolean equals(Object other) {
        boolean result = other != null && other.getClass().equals(getClass());
        if (result) {
            Servidor o = (Servidor) other;
            result = (this == o) || (nome.equals(o.nome));
        }
        return result;
    }

    /**
     * Devolve a Informação do Servidor.
     * 
     * @return informação do servidor.
     */
    @Override
    public String toString() {
        return String.format(Simulacao.getFrasePelaChave("uc4.servidor.info"),
                this.nome, this.massa, this.comprimento, this.largura,
                this.carga, this.altura, this.material.toString());

    }
}
