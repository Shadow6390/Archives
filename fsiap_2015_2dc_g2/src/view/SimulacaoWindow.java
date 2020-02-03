/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.ArrayList;
import java.util.List;
import model.Parede;
import model.Sala;
import model.ServidorInstanciado;
import universe.CustomVector;
import universe.EnhancedUniverse;
import universe.PrimitiveFactory;

/**
 *
 * @author jbraga
 */
public class SimulacaoWindow extends javax.swing.JDialog {

    private EnhancedUniverse universe;
    
    /**
     * Creates new form SimulacaoWindow
     */
    public SimulacaoWindow(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        universe=new EnhancedUniverse();
        universe.setSize(512,512);
        universe.setCameraZoomFactor(2);
        setSize(512,512);
        add(universe);
    }

    /**
     * Devolve o universo tri-dimensional desta simulação.
     * @return ({@link EnhancedUniverse}) O universo da simulação.
     */
    public EnhancedUniverse getUniverse()
    {
        return universe;
    }
    
    /**
     * Atualiza o universo com os conteúdos da sala.
     * @param sala ({@link Sala}) A sala a adicionar.
     */
    public void updateUniverse(Sala sala)
    {
        universe.removeAllEntities();
        List<Parede> list = new ArrayList(sala.getListaParedes());
        list.add(sala.getTeto());
        for (int i=0;i<list.size();i++)
        {
            Parede p = list.get(i);
            double[] pos= p.getPosicao();
            double[] dim= p.getDimensoes();
            int type=p.getOrientacaoParede();
            float variantPos;
            float depth;
            switch (type)
            {
                case Parede.ORIENTACAO_VERTICAL:
                    variantPos=(float) pos[0];
                    for (int j=0;j<p.getListaMateriais().size();j++)
                    {
                        depth = (float)p.getListaMateriais().getEspessura(j);
                        universe.addPrimitive(PrimitiveFactory.createTexturizedBoxAlpha((float)p.getListaMateriais().getEspessura(j),
                                (float)dim[2], (float)dim[1], p.getListaMateriais().getMaterial(j).getImagem(),0.5f),
                                variantPos+depth*0.5f, (float)pos[2]+(float)dim[2]*0.5f, (float)pos[1]+(float)dim[1]*0.5f);
                        variantPos+=p.getListaMateriais().getEspessura(j);
                    }
                    break;
                case Parede.ORIENTACAO_HORIZONTAL:
                    variantPos=(float) pos[1];
                    for (int j=0;j<p.getListaMateriais().size();j++)
                    {
                        depth = (float)p.getListaMateriais().getEspessura(j);
                        universe.addPrimitive(PrimitiveFactory.createTexturizedBoxAlpha((float)dim[0],
                                (float)dim[2], (float)p.getListaMateriais().getEspessura(j), p.getListaMateriais().getMaterial(j).getImagem(),0.5f),
                                (float)pos[0]+(float)dim[0]*0.5f, (float)pos[2]+(float)dim[2]*0.5f, variantPos+depth*0.5f);
                        variantPos+=p.getListaMateriais().getEspessura(j);
                    }
                    break;
                case Parede.ORIENTACAO_PLANA:
                    variantPos=(float) pos[2];
                    for (int j=0;j<p.getListaMateriais().size();j++)
                    {
                        depth = (float)p.getListaMateriais().getEspessura(j);
                        universe.addPrimitive(PrimitiveFactory.createTexturizedBoxAlpha((float)dim[0],
                                depth, (float)dim[1], p.getListaMateriais().getMaterial(j).getImagem(),0.5f),
                                (float)pos[0]+(float)dim[0]*0.5f, variantPos+depth*0.5f, (float)pos[1]+(float)dim[1]*0.5f);
                        variantPos+=p.getListaMateriais().getEspessura(j);
                    }
                    break;
            }
        }
        List<ServidorInstanciado> iList=sala.getListaServidoresInstanciados();
        for (ServidorInstanciado focus:iList)
        {
            double[] pos = focus.getPosicao();
            double[] dim = focus.getDimensoes();
            universe.addPrimitive(PrimitiveFactory.createTexturizedBox((float)dim[0], 
                    (float)dim[2], (float)dim[2], focus.getMaterial().getImagem()), 
                    (float)pos[0], (float)pos[2], (float)pos[1]);
        }
        double[] salaDim = sala.getDimensoes();
        float[] fSalaDim = new float[3];
        fSalaDim[0] = (float)salaDim[0];
        fSalaDim[1] = (float)salaDim[1];
        fSalaDim[2] = (float)salaDim[2];
        universe.addPrimitive(PrimitiveFactory.createColoredBoxAlpha(fSalaDim[0], 
                0.2f, fSalaDim[1], new CustomVector(0f,0f,1f).toColor3f(), 0.5f),fSalaDim[0]*0.5f,-1.6f+fSalaDim[2]*0.5f,fSalaDim[1]*0.5f);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                UniverseResize(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void UniverseResize(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_UniverseResize
        // TODO add your handling code here:
        universe.setSize(getSize());
    }//GEN-LAST:event_UniverseResize


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
