package view;

import controllers.ExportarDadosController;
import java.awt.Frame;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import model.Simulacao;

/**
 *
 */
public class ExportarDadosUI extends javax.swing.JDialog {

    private Frame framePai;
    private ExportarDadosController controller;

    /**
     * Creates new form ExportarDadosUI
     * @param parent
     * @param modal
     * @param simulacao
     */
    public ExportarDadosUI(java.awt.Frame parent, boolean modal, Simulacao simulacao) {
        super(parent, Simulacao.getFrasePelaChave("uc8.janela.exportar.titulo"), modal);
        this.controller = new ExportarDadosController(simulacao);
        this.controller.iniciarExportacao();
        this.framePai = parent;
        setResizable(false);
        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);
        pack();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonSpecify = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();
        jButtonExport = new javax.swing.JButton();
        jLabel = new javax.swing.JLabel();
        jTextField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jButtonSpecify.setText(Simulacao.getFrasePelaChave("uc8.janela.indicar"));
        jButtonSpecify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSpecifyActionPerformed(evt);
            }
        });

        jButtonCancel.setText(Simulacao.getFrasePelaChave("uc8.janela.cancelar"));
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });

        jButtonExport.setText(Simulacao.getFrasePelaChave("uc8.janela.exportar"));
        jButtonExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExportActionPerformed(evt);
            }
        });

        jLabel.setText(Simulacao.getFrasePelaChave("uc8.janela.exportar.label"));

        jTextField.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 152, Short.MAX_VALUE)
                        .addComponent(jButtonExport)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonCancel))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jTextField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonSpecify)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSpecify)
                    .addComponent(jTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonCancel)
                    .addComponent(jButtonExport))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSpecifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSpecifyActionPerformed
        JFileChooser jfc = new JFileChooser();
        jfc.showSaveDialog(this);
        
        if (jfc.getSelectedFile() != null) {
            this.jTextField.setText(jfc.getSelectedFile().getAbsolutePath());
        }
    }//GEN-LAST:event_jButtonSpecifyActionPerformed

    private void jButtonExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExportActionPerformed
        try {
            String caminhoTotal = this.jTextField.getText();
            int split = caminhoTotal.lastIndexOf("\\") + 1;
            
            String caminho = caminhoTotal.substring(0, split);
            String nome = caminhoTotal.substring(split);

            this.controller.setDados(caminho, nome);

            String opcoes[] = {Simulacao.getFrasePelaChave("menu.sim"), Simulacao.getFrasePelaChave("menu.nao")};
            int resposta = JOptionPane.showOptionDialog(
                    null, Simulacao.getFrasePelaChave("uc8.janela.exportar.mensagem"),
                    Simulacao.getFrasePelaChave("uc8.janela.exportar.titulo"), 0,
                    JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);
            if (resposta == 0) {
                this.controller.exportarDados();

                dispose();
            }
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(
                    framePai,
                    ex.getMessage(),
                    Simulacao.getFrasePelaChave("uc8.janela.exportar.titulo"),
                    JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(
                    framePai,
                    Simulacao.getFrasePelaChave("uc8.janela.exportar.erro"),
                    Simulacao.getFrasePelaChave("uc8.janela.exportar.titulo"),
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonExportActionPerformed

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        dispose();
    }//GEN-LAST:event_jButtonCancelActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonExport;
    private javax.swing.JButton jButtonSpecify;
    private javax.swing.JLabel jLabel;
    private javax.swing.JTextField jTextField;
    // End of variables declaration//GEN-END:variables
}