/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controllers.AlterarPropriedadesSalaController;
import java.awt.Frame;
import javax.swing.JOptionPane;
import model.Simulacao;

/**
 *
 * @author alexandra
 */
public class AdicionarMaterialParedeUI extends javax.swing.JDialog {

    private Frame framePai;
    private Simulacao simulacao;
    private AlterarPropriedadesSalaController controller;
    private AlterarParedeUI parentFrame;
    private int indexParede;

    /**
     * Creates new form AdicionarMaterialParedeUI
     *
     * @param controller
     */
    public AdicionarMaterialParedeUI(java.awt.Frame parent, boolean modal,
            AlterarPropriedadesSalaController controller, Simulacao simulacao, int index,AlterarParedeUI pF) {
        super(parent, modal);
        framePai = parent;
        this.simulacao = simulacao;
        this.controller = controller;
        indexParede = index;
        parentFrame=pF;
        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblMaterial = new javax.swing.JLabel();
        lblEspessura = new javax.swing.JLabel();
        cbMateriaisExistentes = new javax.swing.JComboBox<String>();
        txtEspessura = new javax.swing.JTextField();
        btnAdicionar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        lblMaterial.setText(Simulacao.getFrasePelaChave("uc1.janela.material"));

        lblEspessura.setText(Simulacao.getFrasePelaChave("uc1.janela.espessura"));

        cbMateriaisExistentes.setModel(new javax.swing.DefaultComboBoxModel<>(controller.getListaMateriaisExistentes()));

        btnAdicionar.setText(Simulacao.getFrasePelaChave("uc1.janela.adicionar"));
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        btnCancelar.setText(Simulacao.getFrasePelaChave("uc1.janela.cancelar"));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMaterial)
                    .addComponent(lblEspessura))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbMateriaisExistentes, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEspessura, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAdicionar)
                .addGap(18, 18, 18)
                .addComponent(btnCancelar)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMaterial)
                    .addComponent(cbMateriaisExistentes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEspessura)
                    .addComponent(txtEspessura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdicionar)
                    .addComponent(btnCancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed

        try {
            double espessura = Double.parseDouble(txtEspessura.getText().trim());
            int materialIndex = cbMateriaisExistentes.getSelectedIndex();

            controller.adicionarMaterial(materialIndex, espessura, indexParede);

            String opcoes[] = {Simulacao.getFrasePelaChave("menu.sim"), Simulacao.getFrasePelaChave("menu.nao")};
            int resposta = JOptionPane.showOptionDialog(
                    null,/* controller.apresentarDadosSala() + "\n"
                    + */Simulacao.getFrasePelaChave("uc1.janela.mensagem"),
                    Simulacao.getFrasePelaChave("uc1.janela.titulo"), 0,
                    JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);
            if (resposta == 0) {
                controller.guardarListaMateriais(indexParede);
                framePai.revalidate();
                parentFrame.updateList();
                dispose();
            }
            else
            {
                controller.removerUltimo();
            }

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(
                    framePai,
                    ex.getMessage(),
                    Simulacao.getFrasePelaChave("uc1.janela.titulo"),
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAdicionarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JComboBox<String> cbMateriaisExistentes;
    private javax.swing.JLabel lblEspessura;
    private javax.swing.JLabel lblMaterial;
    private javax.swing.JTextField txtEspessura;
    // End of variables declaration//GEN-END:variables
}
