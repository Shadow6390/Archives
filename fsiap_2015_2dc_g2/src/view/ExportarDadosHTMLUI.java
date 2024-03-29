package view;

import controllers.CorrerSimulacaoController;
import controllers.ExportarDadosHTMLController;
import java.awt.Frame;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.Simulacao;

/**
 *
 */
public class ExportarDadosHTMLUI extends javax.swing.JDialog {

    private Frame framePai;
    private ExportarDadosHTMLController controller;

    /**
     * Creates new form ExportarDadosHTML
     *
     * @param parent
     * @param modal
     * @param simulacao
     * @param correrSimulacao
     */
    public ExportarDadosHTMLUI(java.awt.Frame parent, boolean modal,
            Simulacao simulacao, CorrerSimulacaoController correrSimulacao) {
        super(parent, Simulacao.getFrasePelaChave("uc7.janela.titulo"), modal);
        this.controller = new ExportarDadosHTMLController(simulacao, correrSimulacao);
        this.framePai = parent;
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
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

        jCheckBoxMateriais = new javax.swing.JCheckBox();
        jCheckBoxServidor = new javax.swing.JCheckBox();
        jCheckBoxSala = new javax.swing.JCheckBox();
        jLabelDados = new javax.swing.JLabel();
        jLabelCaminho = new javax.swing.JLabel();
        jTextFieldCaminho = new javax.swing.JTextField();
        jButtonCaminho = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jButtonExportar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jCheckBoxMateriais.setText(Simulacao.getFrasePelaChave("uc7.janela.checkbox.materiais"));

        jCheckBoxServidor.setText(Simulacao.getFrasePelaChave("uc7.janela.checkbox.servidores"));

        jCheckBoxSala.setText(Simulacao.getFrasePelaChave("uc7.janela.checkbox.sala"));

        jLabelDados.setText(Simulacao.getFrasePelaChave("uc7.janela.label.dados"));

        jLabelCaminho.setText(Simulacao.getFrasePelaChave("uc7.janela.label.caminho"));

        jTextFieldCaminho.setEnabled(false);

        jButtonCaminho.setText(Simulacao.getFrasePelaChave("uc7.janela.botao.indicar"));
        jButtonCaminho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCaminhoActionPerformed(evt);
            }
        });

        jButtonCancelar.setText(Simulacao.getFrasePelaChave("uc7.janela.botao.cancelar"));
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        jButtonExportar.setText(Simulacao.getFrasePelaChave("uc7.janela.botao.exportar"));
        jButtonExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExportarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelCaminho)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelDados)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jCheckBoxMateriais)
                                .addGap(18, 18, 18)
                                .addComponent(jCheckBoxServidor)
                                .addGap(18, 18, 18)
                                .addComponent(jCheckBoxSala))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextFieldCaminho, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonCaminho)))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonExportar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonCancelar)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelDados)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxMateriais)
                    .addComponent(jCheckBoxServidor)
                    .addComponent(jCheckBoxSala))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelCaminho)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldCaminho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCaminho))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCancelar)
                    .addComponent(jButtonExportar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExportarActionPerformed
        String caminhoTotal = this.jTextFieldCaminho.getText();
        int split = caminhoTotal.lastIndexOf("\\") + 1;

        String caminho = caminhoTotal.substring(0, split);
        String nomeFicheiro = caminhoTotal.substring(split);

        boolean materiais = this.jCheckBoxMateriais.isSelected();
        boolean servidores = this.jCheckBoxServidor.isSelected();
        boolean sala = this.jCheckBoxSala.isSelected();

        this.controller.setCaminho(caminho, nomeFicheiro);

        String opcoes[] = {Simulacao.getFrasePelaChave("menu.sim"), Simulacao.getFrasePelaChave("menu.nao")};
        int resposta = JOptionPane.showOptionDialog(
                null, Simulacao.getFrasePelaChave("uc7.janela.mensagem.sucesso"),
                Simulacao.getFrasePelaChave("uc7.janela.titulo"), 0,
                JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);
        if (resposta == JOptionPane.OK_OPTION) {
            this.controller.setDados(materiais, servidores, sala);
            dispose();
        }
    }//GEN-LAST:event_jButtonExportarActionPerformed

    private void jButtonCaminhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCaminhoActionPerformed
        JFileChooser jfc = new JFileChooser();
        jfc.setAcceptAllFileFilterUsed(false);
        jfc.setFileFilter(new FileNameExtensionFilter("HTML", "html"));
        jfc.showSaveDialog(this);

        if (jfc.getSelectedFile() != null) {
            this.jTextFieldCaminho.setText(jfc.getSelectedFile().getAbsolutePath());
        }
    }//GEN-LAST:event_jButtonCaminhoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCaminho;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonExportar;
    private javax.swing.JCheckBox jCheckBoxMateriais;
    private javax.swing.JCheckBox jCheckBoxSala;
    private javax.swing.JCheckBox jCheckBoxServidor;
    private javax.swing.JLabel jLabelCaminho;
    private javax.swing.JLabel jLabelDados;
    private javax.swing.JTextField jTextFieldCaminho;
    // End of variables declaration//GEN-END:variables
}
