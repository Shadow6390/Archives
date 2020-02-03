/*
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controllers.AdicionarServidorSalaController;
import bragasQuest.PositionPickerListener;
import java.util.Arrays;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import model.Simulacao;
import universe.EnhancedUniverse;
import universe.PrimitiveFactory;

/**
 *
 * @author jbraga
 */
public class JPositionPicker extends javax.swing.JDialog {

    /**
     * UI pai.
     */
    private AdicionarServidorUI parentUI;
    /**
     * Class que coordena o caso de uso.
     */
    private AdicionarServidorSalaController controller;
    
    /**
     * Universo 3D.
     */
    private EnhancedUniverse uv;
    
    /**
     * Creates new form PositionPicker
     */
    public JPositionPicker(final java.awt.Frame parent, boolean modal, 
            AdicionarServidorSalaController con,final AdicionarServidorUI parentUI,EnhancedUniverse uv) {
        super(parent, modal);
        initComponents();
        controller=con;
        this.uv=uv;
        this.parentUI=parentUI;
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                if (controller.getDimensoesSala()==null)
                {
                    JOptionPane.showMessageDialog(parent, Simulacao.getFrasePelaChave("uc5.sala.inexistente"),"",JOptionPane.ERROR_MESSAGE);
                    dispose();
                    parentUI.dispose();
                }
                else
                {
                    double[] dim = controller.getDimensoesSala();
                    positionPickerXY.setDimensions(dim[0], dim[1]);
                    positionPickerXZ.setDimensions(dim[0], dim[2]);
                    positionPickerXY.addPositionPickerListener(new PositionPickerListener() {

                        @Override
                        public void onPositionChanged() {
                            xCoordField.setText(String.valueOf(positionPickerXY.getXCoord(2)));
                            yCoordField.setText(String.valueOf(positionPickerXY.getYCoord(2)));
                        }
                    });
                    positionPickerXZ.addPositionPickerListener(new PositionPickerListener() {
                    
                        @Override
                        public void onPositionChanged() {
                            xCoordField.setText(String.valueOf(positionPickerXZ.getXCoord(2)));
                            zCoordField.setText(String.valueOf(positionPickerXZ.getYCoord(2)));
                        }
                    });

                    initTextListeners();
                }
            }
        });
    }

    private void initTextListeners() 
    {
        xCoordField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                warn();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                //warn();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                warn();
            }

            private void warn() {
                try {
                    double val = Double.parseDouble(xCoordField.getText());
                    positionPickerXY.setXCoord(val);
                    positionPickerXZ.setXCoord(val);
                } catch (NumberFormatException e) {
                    xCoordField.setText("0");
                    JOptionPane.showMessageDialog(null, e.getLocalizedMessage(), null, JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    xCoordField.setText("0");
                    JOptionPane.showMessageDialog(null, ex.getMessage(), null, JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        yCoordField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                warn();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                //warn();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                warn();
            }

            private void warn() {
                try {
                    double val = Double.parseDouble(yCoordField.getText());
                    positionPickerXY.setYCoord(val);
                } catch (NumberFormatException e) {
                    yCoordField.setText("0");
                    JOptionPane.showMessageDialog(null, e.getLocalizedMessage(), null, JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    yCoordField.setText("0");
                    JOptionPane.showMessageDialog(null, ex.getMessage(), null, JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        zCoordField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                warn();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                //warn();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                warn();
            }

            private void warn() {
                try {
                    double val = Double.parseDouble(zCoordField.getText());
                    positionPickerXZ.setYCoord(val);
                } catch (NumberFormatException e) {
                    zCoordField.setText("0");
                    JOptionPane.showMessageDialog(null, e.getLocalizedMessage(), null, JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    zCoordField.setText("0");
                    JOptionPane.showMessageDialog(null, ex.getMessage(), null, JOptionPane.ERROR_MESSAGE);
                }
            }
        });
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

        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        xCoordField = new javax.swing.JTextField();
        yCoordField = new javax.swing.JTextField();
        zCoordField = new javax.swing.JTextField();
        addServerButton = new javax.swing.JButton();
        temperatureLabel = new javax.swing.JLabel();
        tempField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        positionPickerXY = new bragasQuest.JPositionPickerPanel();
        positionPickerXZ = new bragasQuest.JPositionPickerPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter()
        {
            public void windowClosing(java.awt.event.WindowEvent evt)
            {
                onWindowClosing(evt);
            }
        });

        jLabel4.setText("X:");

        jLabel5.setText("Y:");

        jLabel6.setText("Z:");

        xCoordField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        xCoordField.setText("0");

        yCoordField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        yCoordField.setText("0");

        zCoordField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        zCoordField.setText("0");

        addServerButton.setText(Simulacao.getFrasePelaChave("uc5.button.adicionar")
        );
        addServerButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                addServerButtonActionPerformed(evt);
            }
        });

        temperatureLabel.setText(Simulacao.getFrasePelaChave("uc5.jposition.temperature")
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(yCoordField, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(addServerButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(zCoordField)
                                .addGap(18, 18, 18))))
                    .addComponent(temperatureLabel)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tempField)
                            .addComponent(xCoordField, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(temperatureLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tempField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(xCoordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(yCoordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(zCoordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(addServerButton)
                .addContainerGap())
        );

        jLabel2.setText("XY");

        jLabel3.setText("XZ");

        javax.swing.GroupLayout positionPickerXYLayout = new javax.swing.GroupLayout(positionPickerXY);
        positionPickerXY.setLayout(positionPickerXYLayout);
        positionPickerXYLayout.setHorizontalGroup(
            positionPickerXYLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 311, Short.MAX_VALUE)
        );
        positionPickerXYLayout.setVerticalGroup(
            positionPickerXYLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 144, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout positionPickerXZLayout = new javax.swing.GroupLayout(positionPickerXZ);
        positionPickerXZ.setLayout(positionPickerXZLayout);
        positionPickerXZLayout.setHorizontalGroup(
            positionPickerXZLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        positionPickerXZLayout.setVerticalGroup(
            positionPickerXZLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3)
                            .addComponent(positionPickerXY, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(positionPickerXZ, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(positionPickerXY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(positionPickerXZ, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void addServerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addServerButtonActionPerformed
        // TODO add your handling code here:
        try
        {
            String nome = parentUI.getSelectedServidor();
            if (nome!=null)
            {
                double[] pos = new double[3];
                pos[0]=Double.parseDouble(xCoordField.getText());
                pos[1]=Double.parseDouble(yCoordField.getText());
                pos[2]=Double.parseDouble(zCoordField.getText());
                double temp = Double.parseDouble(tempField.getText());
                
                double[] dim = controller.getServidorDimensoes(nome);
                float[] fPos = new float[3];
                fPos[0] = (float)pos[0];
                fPos[1] = (float)pos[1];
                fPos[2] = (float)pos[2];

                float[] fDim = new float[3];
                fDim[0] = (float) dim[0];
                fDim[1] = (float) dim[1];
                fDim[2] = (float) dim[2];

                if (fDim[0]+fPos[0]>controller.getDimensoesSala()[0])
                {
                    fPos[0]-=fDim[0];
                    pos[0]-=dim[0];
                }
                if (fDim[1]+fPos[1]>controller.getDimensoesSala()[1])
                {
                    fPos[1]-=fDim[1];
                    pos[1]-=dim[1];
                }
                if (fDim[2]+fPos[2]>controller.getDimensoesSala()[2])
                {
                    fPos[2]-=fDim[2];
                    pos[2]-=dim[2];
                }
                
                if (controller.adicionarServidor(nome, pos,temp))
                {

                    uv.addPrimitive(PrimitiveFactory.createTexturizedBox(fDim[0], fDim[2], fDim[1], controller.getServidorImage(nome)), 
                        fPos[0]+fDim[0]*0.5f, fPos[2]+fDim[2]*0.5f, fPos[1]+fDim[1]*0.5f);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, Simulacao.getFrasePelaChave("uc5.button.adicionar.erro"), null, JOptionPane.ERROR_MESSAGE);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, Simulacao.getFrasePelaChave("uc5.button.adicionar.servNulo"), null, JOptionPane.ERROR_MESSAGE);
            }
        }
        catch (NumberFormatException e)
        {
            JOptionPane.showMessageDialog(null, Simulacao.getFrasePelaChave("uc5.jposition.temperature.invalid"), null, JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_addServerButtonActionPerformed

    private void onWindowClosing(java.awt.event.WindowEvent evt)//GEN-FIRST:event_onWindowClosing
    {//GEN-HEADEREND:event_onWindowClosing
        // TODO add your handling code here:
        parentUI.dispose();
    }//GEN-LAST:event_onWindowClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addServerButton;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private bragasQuest.JPositionPickerPanel positionPickerXY;
    private bragasQuest.JPositionPickerPanel positionPickerXZ;
    private javax.swing.JTextField tempField;
    private javax.swing.JLabel temperatureLabel;
    private javax.swing.JTextField xCoordField;
    private javax.swing.JTextField yCoordField;
    private javax.swing.JTextField zCoordField;
    // End of variables declaration//GEN-END:variables
}
