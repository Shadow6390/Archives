package controllers;

import java.util.ArrayList;
import java.util.List;
import model.ListaMateriaisParede;
import model.Material;
import model.Parede;
import model.Sala;
import model.Simulacao;

/**
 * Representa um controlador que gere o caso de uso 1, alterar propriedades da
 * sala, através de uma simulação, uma versão temporária da sala, uma lista de
 * paredes e uma lista de materiais das mesmas.
 */
public class AlterarPropriedadesSalaController {

    /**
     * Simulação à qual pertence a sala.
     */
    private Simulacao simulacao;

    /**
     * Clone da Sala, de forma a guardar as informações temporariamente.
     */
    private Sala salaTemp;

    /**
     * Lista de paredes temporária da sala.
     */
    private List<Parede> listaParedesTemp;

    /**
     * Lista de materias e respetivas espessuras de cada parede da sala.
     */
    private List<ListaMateriaisParede> listaMateriaisParedesTemp;

    /**
     * Lista de Materias de uma parede especifica.
     */
    private ListaMateriaisParede focusListaPTemp;

    /**
     * Parede temporária.
     */
    private Parede sParede;

    /**
     * Constrói uma instância do controlador que gere o caso de uso 1, alterar
     * propriedades da sala, através de uma simulação, uma versão temporária da
     * sala, uma lista de paredes e uma lista de materiais das mesmas.
     *
     * @param simulacao Sala cuja as propriedades vão ser alteradas.
     */
    public AlterarPropriedadesSalaController(Simulacao simulacao) {
        this.simulacao = simulacao;
        this.salaTemp = new Sala(simulacao.getSala());

        // Preencher a lista de paredes da sala.
        this.listaParedesTemp = new ArrayList<>();
        for (Parede focus : salaTemp.getListaParedes()) {
            listaParedesTemp.add(new Parede(focus));
        }
        this.listaParedesTemp.add(new Parede(this.salaTemp.getTeto()));

        // Preencher a lista de materiais e espessura de cada parede da sala.
        this.listaMateriaisParedesTemp = new ArrayList<>();
        for (Parede parede : this.salaTemp.getListaParedes()) {
            this.listaMateriaisParedesTemp.add(new ListaMateriaisParede(parede.getListaMateriais()));
        }
        this.listaMateriaisParedesTemp.add(new ListaMateriaisParede(
                this.salaTemp.getTeto().getListaMateriais()));
    }

    /**
     * Devolve o comprimento da sala.
     *
     * @return Comprimento da sala.
     */
    public String getComprimento() {
        return String.format("%.2f", this.salaTemp.getComprimento());
    }

    /**
     * Devolve a largura da sala.
     *
     * @return Largura da sala.
     */
    public String getLargura() {
        return String.format("%.2f", this.salaTemp.getLargura());
    }

    /**
     * Devolve a altura da sala.
     *
     * @return Altura da sala.
     */
    public String getAltura() {
        return String.format("%.2f", this.salaTemp.getAltura());
    }

    /**
     * Devolve o teto da sala.
     *
     * @return Teto da sala.
     */
    public Parede getTeto() {
        return this.salaTemp.getTeto();
    }

    /**
     * Altera o comprimento da sala.
     *
     * @param comprimento Novo comprimento da sala.
     */
    public void setComprimento(double comprimento) {
        this.salaTemp.setComprimento(comprimento);
    }

    /**
     * Altera a largura da sala.
     *
     * @param largura Nova largura da sala.
     */
    public void setLargura(double largura) {
        this.salaTemp.setLargura(largura);
    }

    /**
     * Altera a altura da sala.
     *
     * @param altura Nova altura da sala.
     */
    public void setAltura(double altura) {
        this.salaTemp.setAltura(altura);
    }

    /**
     * Altera o teto da sala.
     *
     * @param teto Novo teto da sala.
     */
    public void setTeto(Parede teto) {
        this.salaTemp.setTeto(teto);
    }

    /**
     * Cria uma lista de materiais temporária, relativa a uma parede, onde irão
     * ser feitas as alterações, antes das mesmas se tornarem definitivas.
     * 
     * @param index Indice que indica a parede.
     */
    public void novoListaParedes(int index) {
        focusListaPTemp = new ListaMateriaisParede(listaMateriaisParedesTemp.get(index));
    }

    /**
     * Devolve a temperatura da parede especificada.
     * @param paredeIndex (int) O índice da parede.
     * @return (double) A temperatura da parede.
     */
    public double getTemperatura(int paredeIndex)
    {
        return listaParedesTemp.get(paredeIndex).getTemperatura();
    }
    
    /**
     * Cria uma parede temporária, sob a qual irão ser feitas as alterações,
     * antes das mesmas se tornarem definitivas.
     *
     * @param index Indice que indica a parede.
     * @return Verdadeiro se for possível criar a parede temporária, falso caso
     * não o seja.
     */
    public boolean backupParede(int index) {
        boolean result;
        sParede = new Parede(listaParedesTemp.get(index));
        result = sParede != null;
        return result;
    }

    /**
     * Reverte as alterações realizadas a uma parede.
     * 
     * @param index Indice que indica a parede.
     */
    public void revertParedeChanges(int index) {
        listaParedesTemp.set(index, sParede);
    }

    /**
     * Devolve uma lista com o nome de cada material presente em cada uma das
     * paredes.
     *
     * @return Lista de materiais de cada parede.
     */
    public List<String[]> getListaMateriaisParedes() {
        List<String[]> listaMateriaisParedes = new ArrayList<>();
        String[] listaNomeMateriais;
        int i;
        List<Material> listaMateriais;
        for (int j = 0; j < 4; j++) {
            Parede parede = listaParedesTemp.get(j);
            listaMateriais
                    = parede.getListaMateriais().getListaMateriais();
            listaNomeMateriais = new String[listaMateriais.size()];
            i = 0;

            for (Material material : listaMateriais) {
                listaNomeMateriais[i] = material.getNome();
                i++;
            }

            listaMateriaisParedes.add(listaNomeMateriais);
        }

        listaMateriais
                = listaParedesTemp.get(4).getListaMateriais().getListaMateriais();
        listaNomeMateriais = new String[listaMateriais.size()];
        i = 0;
        
        for (Material material : listaMateriais) {
            listaNomeMateriais[i] = material.getNome();
            i++;
        }

        listaMateriaisParedes.add(listaNomeMateriais);

        return listaMateriaisParedes;
    }

    /**
     * Devolve uma lista com as espessuras de cada material presenta em cada uma
     * das paredes.
     *
     * @return Lista de espessuras de cada parede.
     */
    public List<String[]> getListaEspessurasParedes() {
        List<String[]> lep = new ArrayList<>();
        String[] ls;
        int i;
        List<Double> ld;

        for (int j = 0; j < 4; j++) {
            Parede p = listaParedesTemp.get(j);
            ld = p.getListaMateriais().getListaEspessuras();
            ls = new String[ld.size()];
            i = 0;
            for (Double d : ld) {
                ls[i] = d.toString();
                i++;
            }
            lep.add(ls);
        }

        ld = listaParedesTemp.get(4).getListaMateriais().getListaEspessuras();
        ls = new String[ld.size()];
        i = 0;

        for (Double d : ld) {
            ls[i] = d.toString();
            i++;
        }

        lep.add(ls);
        return lep;
    }

    /**
     * Devolve uma lista de materiais possíveis de serem adicionados a cada uma
     * das paredes.
     *
     * @return Lista de materiais.
     */
    public String[] getListaMateriaisExistentes() {
        List<Material> lm = this.simulacao.getListaMateriaisExistentes();
        String[] s = new String[lm.size()];
        int i = 0;

        for (Material m : lm) {
            s[i] = m.getNome();
            i++;
        }

        return s;
    }

    /**
     * Adiciona um material e a sua espessura a uma parede através do indice da
     * mesmo.
     * 
     * @param indexMaterial Indice do material de forma a obter o material da
     * lista.
     * @param espessura Espessura da parede.
     * @param indexParede Indice da parede na qual vai ser adicionado o material
     * e a espessura.
     */
    public void adicionarMaterial(int indexMaterial, double espessura, int indexParede) {
        Material material = this.simulacao.getRegistoMateriais().getMateriais().get(indexMaterial);
        focusListaPTemp.adicionarMaterial(material, espessura);
    }

    /**
     * Remove um material e a sua espessura da parede indicada.
     * 
     * @param indexParede Indice da parede de onde o material vai ser removido.
     * @param indexMaterial Indice do material que vai ser removido.
     */
    public void removerMaterial(int indexParede, int indexMaterial) {
        this.focusListaPTemp.removerMaterial(indexMaterial);

    }

    /**
     * Remove o ultimo material adicionado.
     */
    public void removerUltimo() {
        this.focusListaPTemp.removerMaterial(this.focusListaPTemp.size() - 1);
    }

    /**
     * Guarda as alterações realizadas numa parede.
     * 
     * @param indexParede Indice da parede à qual foram feitas as alterações.
     * @param temperatura Temperatura da parede.
     */
    public void guardarParede(int indexParede, double temperatura) {
        Parede p = listaParedesTemp.get(indexParede);
        if (indexParede == 4) {
            if (p.validar())
            {
                this.salaTemp.setTeto(p);
                this.salaTemp.getTeto().setTemperatura(temperatura);
            }
        } else {
            if (p.validar())
            {
                this.salaTemp.getListaParedes().remove(indexParede);
                this.salaTemp.getListaParedes().add(indexParede, p);
                p.setTemperatura(temperatura);   
            }
        }
    }

    /**
     * Guarda a sala depois das alterações feitas.
     */
    public void guardarSala() {
        this.simulacao.setSala(salaTemp);
    }

    /**
     * Guarda uma lista de materiais numa dada parede.
     * 
     * @param indexParede Indice da parede na qual vai ser guardada a lista de 
     * materiais.
     */
    public void guardarListaMateriais(int indexParede) {
        listaMateriaisParedesTemp.set(indexParede, focusListaPTemp);
        this.listaParedesTemp.get(indexParede).setListaMateriais(focusListaPTemp);
    }
}
