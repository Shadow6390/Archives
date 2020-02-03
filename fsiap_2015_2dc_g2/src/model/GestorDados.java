package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Representa uma classe que gere os dados da simulação, através dos próprios
 * dados, um caminho para um ficheiro e o nome do mesmo, permitindo assim a
 * exportação e importação deles.
 */
public class GestorDados {

    /**
     * Dados da simualação.
     */
    private Simulacao dados;

    /**
     * Diretório para o qual se pretende exportar ou do qual se pretende
     * importar os dados da simulação.
     */
    private String caminhoFicheiro;

    /**
     * Nome do ficheiro que irá conter ou contém os dados da simulação.
     */
    private String nomeFicheiro;

    /**
     * Constrói uma instância de GestorDados, através dos dados da simulação.
     *
     * @param dados Dados da simulação.
     */
    public GestorDados(Simulacao dados) {
        this.dados = dados;
    }

    /**
     * Devolve o caminho do ficheiro.
     *
     * @return Caminho do ficheiro.
     */
    public String getCaminhoFicheiro() {
        return caminhoFicheiro;
    }

    /**
     * Devolve o nome do ficheiro.
     *
     * @return Nome do ficheiro.
     */
    public String getNomeFicheiro() {
        return nomeFicheiro;
    }

    /**
     * Modifica o caminho do ficheiro.
     *
     * @param caminhoFicheiro Novo caminho do ficheiro.
     */
    public void setCaminhoFicheiro(String caminhoFicheiro) {
        if (caminhoFicheiro == null || caminhoFicheiro.trim().isEmpty()) {
            throw new IllegalArgumentException(Simulacao.getIdiomaAtual().getString("uc8.erro.caminho"));
        }

        this.caminhoFicheiro = caminhoFicheiro;
    }

    /**
     * Modifica o nome do ficheiro.
     *
     * @param nomeFicheiro Novo nome do ficheiro.
     */
    public void setNomeFicheiro(String nomeFicheiro) {
        if (nomeFicheiro == null || nomeFicheiro.trim().isEmpty()) {
            throw new IllegalArgumentException(Simulacao.getIdiomaAtual().getString("uc8.erro.nome"));
        }
        
        this.nomeFicheiro = nomeFicheiro;
    }

    /**
     * Valida se a exportação dos dados é valida verificando se o caminho
     * indicado existe.
     *
     * @return Verdadeiro se o caminho existir e falso caso não exista.
     */
    public boolean validarExportacao() {
        return new File(this.caminhoFicheiro).exists();
    }

    /**
     * Valida se a importação dos dados é válida verificando se o caminho e o
     * nome indicado existem.
     *
     * @return Verdadeiro se o caminho e o ficheiro existirem e falso caso não
     * existam.
     */
    public boolean validarImportacao() {
        return new File(this.caminhoFicheiro + this.nomeFicheiro).exists();
    }

    /**
     * Exporta os dados da simulação para o ficheiro no diretório indicado.
     *
     * @throws java.io.FileNotFoundException Caso não seja possível realizar a
     * exportação dos dados.
     */
    public void exportarDados() throws FileNotFoundException, IOException {        
        FileOutputStream fos = new FileOutputStream(
                new File(this.caminhoFicheiro + this.nomeFicheiro));
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        
        oos.writeObject(dados);
        oos.close();
    }

    /**
     *
     * @throws java.io.FileNotFoundException
     * @throws java.lang.ClassNotFoundException
     */
    public Simulacao importarDados() throws FileNotFoundException, IOException,
            ClassNotFoundException {
        FileInputStream fis = new FileInputStream(
                new File(this.caminhoFicheiro + this.nomeFicheiro));
        ObjectInputStream ois = new ObjectInputStream(fis);
        
        this.dados = (Simulacao) ois.readObject();
        ois.close();
        return dados;
    }

}
