package model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javax.imageio.ImageIO;

/**
 * Representa um material usado na simulação.
 * 
 * @author DC_G2
 */
public class Material implements Serializable {
    
    /**
     * Nome do material. 
     */
    private String nome;
    
    /**
     * Descrição do material.
     */
    private String descricao;
    
    /**
     * Coeficiente de condução do material.
     */
    private double coeficienteConducao;
    
    /**
     * Coeficiente de convecção do material.
     */
    private double coeficienteConveccao;
    
    /**
     * Coeficiente de radiação do material.
     */
    private double coeficienteRadiacao;
    
    /**
     * Imagem do material
     */
    transient private BufferedImage imagem;

    /**
     * Construtor vazio do material.
     */
    public Material() {
    }

    /**
     *
     * Construtor completo do material.
     * 
     * @param nome nome do material
     * @param descricao descrição do material
     * @param coeficienteConducao Coeficiente de condução do material
     * @param coeficienteConveccao Coeficiente de convecção do material
     * @param coeficienteRadiacao Coeficiente de radiação do material
     * @param imagem imagem do material
     */
    public Material(String nome, String descricao, double coeficienteConducao,
            double coeficienteConveccao, double coeficienteRadiacao, BufferedImage imagem) {
        this.nome = nome;
        this.descricao = descricao;
        this.coeficienteConducao = coeficienteConducao;
        this.coeficienteConveccao = coeficienteConveccao;
        this.coeficienteRadiacao = coeficienteRadiacao;
        this.imagem = imagem;
    }

    /**
     * Retorna o nome do material.
     * 
     * @return o nome do material
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Retorna a descrição do material.
     * 
     * @return a descrição do material
     */
    public String getDescricao() {
        return this.descricao;
    }

    /**
     * Retorna o coeficiente de condução do material.
     * 
     * @return o coeficiente de condução do material
     */
    public double getCoeficienteConducao() {
        return this.coeficienteConducao;
    }

    /**
     * Retorna o coeficiente de convecção do material.
     * @return o coeficiente de convecção do material
     */
    public double getCoeficienteConveccao() {
        return this.coeficienteConveccao;
    }

    /**
     * Retorna o coeficiente de radiação do material.
     * 
     * @return o coeficiente de radiação do material
     */
    public double getCoeficienteRadiacao() {
        return this.coeficienteRadiacao;
    }

    /**
     * Retorna a imagem do material.
     * 
     * @return a imagem do material
     */
    public BufferedImage getImagem() {
        return this.imagem;
    }

    /**
     * Altera o nome do material.
     * 
     * @param nome novo nome do material
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Altera a descrição do material.
     * 
     * @param descricao nova descrição do material
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Altera o coeficiente de condução do material.
     * 
     * @param coeficienteConducao
     */
    public void setCoeficienteConducao(double coeficienteConducao) {
        this.coeficienteConducao = coeficienteConducao;
    }

    /**
     * Altera o coeficiente de convecção do material.
     * 
     * @param coeficienteConveccao
     */
    public void setCoeficienteConveccao(double coeficienteConveccao) {
        this.coeficienteConveccao = coeficienteConveccao;
    }

    /**
     * Altera o coeficiente de radiação do material.
     * 
     * @param coeficienteRadiacao
     */
    public void setCoeficienteRadiacao(double coeficienteRadiacao) {
        this.coeficienteRadiacao = coeficienteRadiacao;
    }

    /**
     * Altera a imagem do material.
     * 
     * @param imagem nova imagem do material
     */
    public void setImagem(BufferedImage imagem) {
        this.imagem = imagem;
    }

    /**
     * Método que permite validar se um material é válido ou não.
     *
     * @return Retorna verdadeiro se for válido ou falso se não for.
     */
    public boolean valida() {
        if (this.nome == null) {
            throw new IllegalArgumentException(Simulacao.getFrasePelaChave("uc3.material.validaNome"));
        }
        if (this.descricao == null) {
            throw new IllegalArgumentException(Simulacao.getFrasePelaChave("uc3.material.validaDescricao"));
        }
        if (this.coeficienteRadiacao < 0 && this.coeficienteRadiacao>1) {
            throw new IllegalArgumentException(Simulacao.getFrasePelaChave("uc3.material.validaCoeficiente"));
        }
        if (this.imagem == null) {
            throw new IllegalArgumentException(Simulacao.getFrasePelaChave("uc3.material.validaImagem"));
        }
        return true;
    }
    
    /**
     * Método próprio de serialização desta classe.
     * @param out
     * @throws IOException 
     */
    private void writeObject(ObjectOutputStream out) throws IOException
    {
        out.defaultWriteObject();
        ImageIO.write(imagem, "png", out);
    }
    
    /**
     * Método próprio de serialização desta classe.
     * @param in
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    private void readObject(ObjectInputStream in) throws IOException,ClassNotFoundException
    {
        in.defaultReadObject();
        imagem=ImageIO.read(in);
    }
    
    /**
     * Retorna a informação do material.
     * 
     * @return a informação do material.
     */
    @Override
    public String toString() {
        return String.format(Simulacao.getFrasePelaChave("uc3.material.info"), this.nome, this.descricao,
                this.coeficienteConducao, this.coeficienteConveccao, this.coeficienteRadiacao);
    }

    /**
     * Compara dois objetos, retornando verdadeiro se forem iguais
     * ou falso se forem diferentes.
     * 
     * @param other outro Material a comparar.
     * @return verdadeiro se forem iguais ou falso se forem diferentes.
     */
    @Override
    public boolean equals(Object other) {
        boolean result = other != null && getClass().equals(other.getClass());
        if (result) {
            Material mat = (Material) other;
            result = (this == other) || this.nome.equals(mat.nome);
        }
        return result;
    }
}
