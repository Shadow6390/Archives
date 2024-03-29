/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controllers.AdicionarServidorSalaController;
import java.util.List;
import javax.swing.DefaultListModel;
import model.Simulacao;
import universe.EnhancedUniverse;

/**
 *
 * @author jbraga
 */
public class AdicionarServidorUI extends javax.swing.JDialog {

    /**
     * A simulação a usar.
     */
    private Simulacao sim;
    
    /**
     * O controller que irá coordenar este caso de uso.
     */
    private AdicionarServidorSalaController controller;
    
    private JPositionPicker picker;
    
    /**
     * Creates new form AdicionarServidorUI
     */
    public AdicionarServidorUI(java.awt.Frame parent, boolean modal,Simulacao sim,EnhancedUniverse uv) {
        super(parent, modal);
        initComponents();
        this.sim=sim;
        controller = new AdicionarServidorSalaController(sim);
        picker=new JPositionPicker(parent,false,controller,this,uv);
        picker.setVisible(true);
        DefaultListModel<String> model = new DefaultListModel();
        List<String> servidores = controller.getListaServidores();
        for (String element:servidores)
        {
            model.addElement(element);
        }
        listaServidores.setModel(model);
    }
    /**
     * Devolve o nome do servidor atualmente selecionado.
     * @return (String) O nome do servidor atualmente selecionado.
     */
    protected String getSelectedServidor()
    {
        return listaServidores.getSelectedValue();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jScrollPane1 = new javax.swing.JScrollPane();
        listaServidores = new javax.swing.JList<String>();
        listaServidoresLabel = new javax.swing.JLabel();
        serverNameLabel = new javax.swing.JLabel();
        serverMassaLabel = new javax.swing.JLabel();
        serverCargaLabel = new javax.swing.JLabel();
        serverNameField = new javax.swing.JTextField();
        serverCargaField = new javax.swing.JTextField();
        serverMassaField = new javax.swing.JTextField();
        serverImagePanel = new bragasQuest.JImagePanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter()
        {
            public void windowClosing(java.awt.event.WindowEvent evt)
            {
                onWindowClosing(evt);
            }
        });

        listaServidores.addListSelectionListener(new javax.swing.event.ListSelectionListener()
        {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt)
            {
                serverSelected(evt);
            }
        });
        jScrollPane1.setViewportView(listaServidores);

        listaServidoresLabel.setText(Simulacao.getFrasePelaChave("uc5.label.listaServidores"));

        serverNameLabel.setText(Simulacao.getFrasePelaChave("uc5.label.servidorNome"));

        serverMassaLabel.setText(Simulacao.getFrasePelaChave("uc5.label.servidorMassa"));

        serverCargaLabel.setText(Simulacao.getFrasePelaChave("uc5.label.servidorCarga"));

        serverNameField.setEditable(false);

        serverCargaField.setEditable(false);

        serverMassaField.setEditable(false);

        javax.swing.GroupLayout serverImagePanelLayout = new javax.swing.GroupLayout(serverImagePanel);
        serverImagePanel.setLayout(serverImagePanelLayout);
        serverImagePanelLayout.setHorizontalGroup(
            serverImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 148, Short.MAX_VALUE)
        );
        serverImagePanelLayout.setVerticalGroup(
            serverImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(listaServidoresLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(serverCargaLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(serverCargaField, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(serverMassaLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(serverMassaField, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(serverNameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addComponent(serverNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(serverImagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addComponent(listaServidoresLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(serverImagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(serverNameLabel)
                    .addComponent(serverNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(serverMassaLabel)
                    .addComponent(serverMassaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(serverCargaLabel)
                    .addComponent(serverCargaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void serverSelected(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_serverSelected
        // TODO add your handling code here:
        if (!evt.getValueIsAdjusting())
        {
            String nome = listaServidores.getSelectedValue();
            serverNameField.setText(nome);
            serverCargaField.setText(controller.getServidorCarga(nome));
            serverMassaField.setText(controller.getServidorMassa(nome));
            serverImagePanel.setImage(controller.getServidorImage(nome));
            repaint();
        }
    }//GEN-LAST:event_serverSelected

    private void onWindowClosing(java.awt.event.WindowEvent evt)//GEN-FIRST:event_onWindowClosing
    {//GEN-HEADEREND:event_onWindowClosing
        // TODO add your handling code here:
        picker.dispose();
    }//GEN-LAST:event_onWindowClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> listaServidores;
    private javax.swing.JLabel listaServidoresLabel;
    private javax.swing.JTextField serverCargaField;
    private javax.swing.JLabel serverCargaLabel;
    private bragasQuest.JImagePanel serverImagePanel;
    private javax.swing.JTextField serverMassaField;
    private javax.swing.JLabel serverMassaLabel;
    private javax.swing.JTextField serverNameField;
    private javax.swing.JLabel serverNameLabel;
    // End of variables declaration//GEN-END:variables
}
