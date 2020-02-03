package model;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * Representa uma simulação da transferência de calor que ocorre numa sala
 * através de uma lista de idiomas e um idioma atual.
 */
public class Simulacao implements Serializable {

    /**
     * Lista de idiomas disponíveis na simulação.
     */
    private RegistoIdiomas registoIdiomas;

    /**
     * O idioma atual da simulação.
     */
    private static ResourceBundle idiomaAtual;

    /**
     * Lista de Materiais disponíveis na simulação.
     */
    private RegistoMateriais registoMateriais;

    /**
     * O registo de servidores desta simulação.
     * <p>
     * O registo contém uma lista de tipos de servidor que podem ser
     * instanciados na simulação.
     */
    private RegistoServidores registoServidores;

    /**
     * A sala desta simulação.
     */
    private Sala sala;
    
    private static List<Material> defaultMaterialBundle = loadMaterialStartingPack();

    /**
     * Constrói uma instância de simulação inicializando a lista de idiomas
     * disponíveis e selecionando o idioma por defeito.
     */
    public Simulacao() {
        this(new RegistoServidores());
    }

    /**
     * Cria uma instância de {@link Simulacao} com os parâmetros especificados.
     *
     * @param registoServidores ({@link RegistoServidores}) O registo de
     * servidores desta simulação.
     */
    public Simulacao(RegistoServidores registoServidores) {
        this.registoServidores = registoServidores;
        this.registoIdiomas = new RegistoIdiomas();
        this.registoMateriais = new RegistoMateriais();
        setIdiomaAtual(
                this.registoIdiomas.getIdiomaPorDefeito().importarIdioma());
        List<Material> mats = defaultMaterialBundle;
        for (Material focus : mats) {
            registoMateriais.addMaterial(focus);
        }
        this.sala = new Sala(5, 5, 3, 10, 7.50, 15, 3, 12,
                this.registoMateriais.procuraMaterial("Wood"));
    }

    /**
     * Carrega os materiais por defeito da simulação.
     */
    public static List<Material> loadMaterialStartingPack() {
        RegistoMateriais rm = new RegistoMateriais();
        try {
            BufferedReader buffer = new BufferedReader(new FileReader(new File("material.bundle")));
            String cache;
            Material mat;
            while ((cache = buffer.readLine()) != null) {
                String[] matData = cache.split(";");
                mat = new Material();
                mat.setNome(matData[0]);
                mat.setDescricao(matData[1]);
                mat.setCoeficienteConducao(Double.parseDouble(matData[2]));
                mat.setCoeficienteConveccao(Double.parseDouble(matData[3]));
                mat.setCoeficienteRadiacao(Double.parseDouble(matData[4]));
                mat.setImagem(ImageIO.read(new File(matData[5])));
                rm.addMaterial(mat);
            }
            buffer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rm.getMateriais();
    }

    /**
     * Devolve o registo de servidores da simulação.
     *
     * @return registo de servidores
     */
    public RegistoServidores getRegistoServidores() {
        return registoServidores;
    }

    /**
     * Devolve a lista de idiomas da simulação.
     *
     * @return Lista de idiomas da simulação.
     */
    public RegistoIdiomas getRegistoIdiomas() {
        return this.registoIdiomas;
    }

    /**
     * Devolve o registo de materiais da simulação.
     *
     * @return registo de materiais
     */
    public RegistoMateriais getRegistoMateriais() {
        return this.registoMateriais;
    }

    /**
     * Retorna o mapa térmico desta simulação.
     *
     * @return ({@link MapaTermicoData}) O mapa térmico.
     */
    public MapaTermicoData getMapaTermico() {
        return null;
    }

    /**
     * Retorna a lista de servidores desta simulaçáo.
     *
     * @return (ArrayList&lt;{@link Servidor}&gt;) A lista de servidores desta
     * simulação.
     */
    public ArrayList<Servidor> getListaServidores() {
        return this.registoServidores.getListaServidores();
    }

    /**
     * Devolve a sala da simulação;
     *
     * @return Sala da simulação.
     */
    public Sala getSala() {
        return this.sala;
    }

    /**
     * Atribui uma nova {@link Sala} a esta simulação.
     *
     * @param sala ({@link Sala}) A nova sala desta simulação.
     */
    public void setSala(Sala sala) {
        this.sala = sala;
        List<Parede> list = sala.getListaParedes();
        if (list.size()>0)
        {
            Parede p = list.get(0);
            double[] pos = p.getPosicao();
            pos[1]=-p.getProfundidade();
            p.setPosicao(pos);
        
            p = list.get(3);
            pos = p.getPosicao();
            pos[0]=-p.getProfundidade();
            p.setPosicao(pos);
        }
    }

    /**
     * Retorna a posição do servidor especificado na lista de servidores desta
     * simulação.
     *
     * @param servidor ({@link Servidor}) O servidor especificado.
     * @return (int) A posição do servidor na lista. -1 se o servidor não
     * existir na lista.
     */
    public int getIndexOf(Servidor servidor) {
        return this.registoServidores.getIndexOf(servidor);
    }

    /**
     * Regista a alteração de um servidor no registo de servidores desta
     * simulação.
     *
     * @param serv ({@link Servidor}) O servidor com as novas alterações.
     * @param index (int) O índice do servidor original.
     * @return (boolean) Verdadeiro caso a alteração tenha sido registada com
     * sucesso.
     */
    public boolean registaAlteracao(Servidor serv, int index) {
        return this.registoServidores.registaAlteracao(serv, index);
    }

    /**
     * Valida se é possível aplicar o novo idioma, comparando com o atual.
     *
     * @param idiomaAtual Novo idioma da simulação.
     * @return Verdadeiro se for possível aplicar o idioma e falso caso o idioma
     * que se pretende aplicar for o atualmente aplicado.
     */
    public boolean validarIdioma(ResourceBundle idiomaAtual) {
        return !Simulacao.idiomaAtual.equals(idiomaAtual);
    }

    /**
     * Dá início à exportação dos dados de uma simulação para um ficheiro
     * binário.
     *
     * @return Objeto que irá gerir a exportação dos dados.
     */
    public GestorDados iniciarExportacao() {
        return new GestorDados(this);
    }

    /**
     * Dá início à importação dos dados de uma simulação a partir de um ficheiro
     * binário.
     *
     * @return Objeto que irá gerir a importação dos dados.
     */
    public GestorDados iniciarImportacao() {
        return iniciarExportacao();
    }

    /**
     * Retorna a lista de materiais existentes.
     *
     * @return lista de materiais existentes.
     */
    public List<Material> getListaMateriaisExistentes() {
        return this.registoMateriais.getMateriais();
    }

    /**
     * Adquire os conteúdos para iniciar a simulação.
     *
     * @return ({@link TransferenciaCalor}) Os conteúdos para correr a
     * simulação.
     */
    public TransferenciaCalor setupSimulation() {
        TransferenciaCalor tc = new TransferenciaCalor();
        ArrayList<ObjetoCalor> list = new ArrayList();

        tc.setSala(this.sala);
        list.addAll(this.sala.getListaServidoresInstanciados());
        tc.setListaCondutores(list);
        return tc;
    }

    /**
     * Devolve o idioma atual da simulação.
     *
     * @return Idioma atual da simulação.
     */
    public static ResourceBundle getIdiomaAtual() {
        return idiomaAtual;
    }

    /**
     * Modifica o idioma atual da simulação.
     *
     * @param idiomaAtual Novo idioma da simulação.
     */
    public static void setIdiomaAtual(ResourceBundle idiomaAtual) {
        if (idiomaAtual == null) {
            throw new IllegalArgumentException(getIdiomaAtual().getString("uc12.erro.idiomaAtual"));
        }

        Simulacao.idiomaAtual = idiomaAtual;
    }

    /**
     * Devolve uma frase no idioma atual através de uma chave que a identifica.
     *
     * @param chave Chave que identifica a frase.
     * @return Frase identificada pela chave.
     */
    public static String getFrasePelaChave(String chave) {
        return idiomaAtual.getString(chave);
    }

}
