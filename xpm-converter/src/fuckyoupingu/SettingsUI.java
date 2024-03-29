/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuckyoupingu;

import fuckyoupingu.model.AppSettings;
import javax.swing.JOptionPane;

/**
 *
 * @author jbraga
 */
public class SettingsUI extends javax.swing.JDialog {

    /**
     * Creates new form SettingsUI
     */
    public SettingsUI(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        threadSlider.setValue(AppSettings.getInstance().getNumThreads());
        threadCheckbox.setSelected(AppSettings.getInstance().isUsingThreads());
        threadSlider.setEnabled(threadCheckbox.isSelected());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        threadCheckbox = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        threadSlider = new javax.swing.JSlider();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Conversion");

        threadCheckbox.setText("Use Multiple Threads");
        threadCheckbox.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                threadCheckboxStateChanged(evt);
            }
        });

        jLabel2.setText("Number of Threads to Use:");

        threadSlider.setMajorTickSpacing(1);
        threadSlider.setMaximum((int)(Runtime.getRuntime().availableProcessors()*1.5));
        threadSlider.setMinimum(1);
        threadSlider.setMinorTickSpacing(1);
        threadSlider.setPaintLabels(true);
        threadSlider.setPaintTicks(true);
        threadSlider.setSnapToTicks(true);
        threadSlider.setValue(Math.max(Runtime.getRuntime().availableProcessors()/2,1));

        jButton1.setText("Cancel");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Save");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(threadSlider, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(threadCheckbox)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addComponent(threadCheckbox)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(threadSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void threadCheckboxStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_threadCheckboxStateChanged
        // TODO add your handling code here:
        threadSlider.setEnabled(threadCheckbox.isSelected());
    }//GEN-LAST:event_threadCheckboxStateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        AppSettings instance = AppSettings.getInstance();
        instance.setNumThreads(threadSlider.getValue());
        instance.setUsingThreads(threadCheckbox.isSelected());
        try
        {
            instance.saveSettings();
            dispose();
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null,"There was an error whilst saving: "+e.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JCheckBox threadCheckbox;
    private javax.swing.JSlider threadSlider;
    // End of variables declaration//GEN-END:variables
}
