/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuckyoupingu;

import fuckyoupingu.model.XPMConverter;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListCellRenderer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author 2DJ
 */
public class EditPicture extends javax.swing.JDialog {

    private DefaultListModel<DisplayColor> model;
    
    private XPMConverter xpm;
    
    private String path;
    
    protected static class DisplayColor
    {
        private Color color;
        private String text;
        
        public DisplayColor()
        {
            
        }

        /**
         * @return the color
         */
        public Color getColor() {
            return color;
        }

        /**
         * @param color the color to set
         */
        public void setColor(Color color) {
            this.color = color;
        }

        /**
         * @return the text
         */
        public String getText() {
            return text;
        }

        /**
         * @param text the text to set
         */
        public void setText(String text) {
            this.text = text;
        }
        @Override
        public String toString()
        {
            return text;
        }
    }
    
    protected class ComboBoxRenderer extends JLabel
                       implements ListCellRenderer {
        public ComboBoxRenderer() {
            setOpaque(true);
            setHorizontalAlignment(CENTER);
            setVerticalAlignment(CENTER);
        }

        /*
         * This method finds the image and text corresponding
         * to the selected value and returns the label, set up
         * to display the text and image.
         */
        public Component getListCellRendererComponent(
                                           JList list,
                                           Object value,
                                           int index,
                                           boolean isSelected,
                                           boolean cellHasFocus) {
            //Get the selected index. (The index param isn't
            //always valid, so just use the value.)

            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBackground(model.getElementAt(index).getColor());
                setForeground(Color.BLACK);
            }
            setText(model.getElementAt(index).getText());

            return this;
        }
    }
    
    /**
     * Creates new form EditPicture
     */
    public EditPicture(java.awt.Frame parent,boolean modal) {
        super(parent, modal);
        initComponents();
        xpm = new XPMConverter();
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                try
                {
                    JFileChooser chooser = new JFileChooser();
                    chooser.setAcceptAllFileFilterUsed(false);
                    chooser.addChoosableFileFilter(new FileNameExtensionFilter("X PixMap","xpm"));
                    int result = chooser.showOpenDialog(null);
                    switch (result)
                    {
                        case JFileChooser.APPROVE_OPTION:
                            File file = chooser.getSelectedFile();
                            path = file.getParent();
                            xpm.setName(file.getName().replace(".xpm",""));
                            BufferedImage im = XPMConverter.previewXPM(file);
                            imagePanel.setImage(im);
                            model = new DefaultListModel();
                            for (int i=0;i<XPMConverter.colorsAvailable();i++)
                            {
                                DisplayColor dc = new DisplayColor();
                                dc.setText("Color "+i);
                                dc.setColor(XPMConverter.getColor(i));
                                model.addElement(dc);
                            }
                            colorsList.setModel(model);
                            colorsList.setCellRenderer(new ComboBoxRenderer());
                            colorsList.addListSelectionListener(new ListSelectionListener() {

                                @Override
                                public void valueChanged(ListSelectionEvent e) {
                                    imagePanel.setSelectedColor(model.getElementAt(colorsList.getSelectedIndex()).getColor());
                                }
                            });
                            imagePanel.addMouseMotionListener(new MouseMotionListener() {

                        @Override
                        public void mouseDragged(MouseEvent e) {
                            int width=imagePanel.getImage().getWidth(),
                                            height=imagePanel.getImage().getHeight();
                                    int x=(int)(width*((float)e.getX()/(float)imagePanel.getWidth()))
                                            ,y=(int)(height*((float)e.getY()/(float)imagePanel.getHeight()));
                                    imagePanel.paintPixelAt(x,y);
                                    repaint();
                        }

                        @Override
                        public void mouseMoved(MouseEvent e) {
                            
                        }
                    });
                            imagePanel.addMouseListener(new MouseAdapter() {
                                
                                @Override
                                public void mousePressed(MouseEvent e)
                                {
                                    int width=imagePanel.getImage().getWidth(),
                                            height=imagePanel.getImage().getHeight();
                                    int x=(int)(width*((float)e.getX()/(float)imagePanel.getWidth()))
                                            ,y=(int)(height*((float)e.getY()/(float)imagePanel.getHeight()));
                                    imagePanel.paintPixelAt(x,y);
                                    repaint();
                                }
                            });
                            break;
                    }
                    Dimension dim =new Dimension(imagePanel.getImage().getWidth()*2,imagePanel.getImage().getHeight()*2);
                    imagePanel.setPreferredSize(dim);
                    imagePanel.setMinimumSize(dim);
                    imagePanel.setMaximumSize(dim);
                    imagePanel.updateUI();
                }
                catch (IOException e)
                {
                    JOptionPane.showMessageDialog(null,e.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
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
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        colorsList = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        imagePanel = new fuckyoupingu.JEditImagePanel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel1.setText("Preview");

        jScrollPane1.setViewportView(colorsList);

        javax.swing.GroupLayout imagePanelLayout = new javax.swing.GroupLayout(imagePanel);
        imagePanel.setLayout(imagePanelLayout);
        imagePanelLayout.setHorizontalGroup(
            imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 423, Short.MAX_VALUE)
        );
        imagePanelLayout.setVerticalGroup(
            imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 297, Short.MAX_VALUE)
        );

        jScrollPane2.setViewportView(imagePanel);

        jLabel2.setText("Colors");

        jButton1.setText("Save Changes");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1))
                    .addComponent(jScrollPane2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int result = JOptionPane.showConfirmDialog(rootPane, "Save with code-generated format?");
        boolean flag = result==JOptionPane.OK_OPTION;
        if (xpm.save(imagePanel.getImage(), path, flag))
        {
            JOptionPane.showMessageDialog(rootPane,"Saved successfully.");
        }
        else
        {
            JOptionPane.showMessageDialog(this,"Error whilst saving file.","ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList colorsList;
    private fuckyoupingu.JEditImagePanel imagePanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
