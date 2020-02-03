package view;

import bragasQuest.PointDouble;
import controllers.CorrerSimulacaoController;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import model.Sala;
import model.Simulacao;

/**
 *
 */
public class JanelaPrincipal extends javax.swing.JFrame {

    private SimulacaoWindow simWindow;
    private Simulacao simulacao;
    private CorrerSimulacaoController controller;
    private Timer sTimer;
    private int playButtonState;

    /**
     * Creates new form JanelaPrincipal
     */
    public JanelaPrincipal() {
        this.simulacao = new Simulacao();
        initComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.simWindow = new SimulacaoWindow(this, false);
        this.simWindow.setVisible(true);
        this.alterarIdiomaJFileChooser();
        controller = new CorrerSimulacaoController(simulacao);
        playButtonState=0;
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                simWindow.updateUniverse(simulacao.getSala());
                plotGraph1.setXScale(3);
                plotGraph1.setYScale(0.001);
                plotGraph1.setDivisionFactor(2);
                deltaTimeLabel.setText(Simulacao.getFrasePelaChave("uc10.slider.elapsedTime") + "(" + (controller.getDeltaTime() / 60) + ")");
                deltaTimeSlider.addChangeListener(new ChangeListener() {

                    @Override
                    public void stateChanged(ChangeEvent e) {
                        if (!deltaTimeSlider.getValueIsAdjusting()) {
                            controller.setDeltaTime(deltaTimeSlider.getValue() * 60);
                            deltaTimeLabel.setText(Simulacao.getFrasePelaChave("uc10.slider.elapsedTime") + "(" + (controller.getDeltaTime() / 60) + ")");
                        }
                    }
                });
                uc7exportarHTML.setEnabled(false);
                
                sTimer=new Timer(100,new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        
                        double val;
                        controller.updateTemperatures();
                        val = controller.getRefrigerationCost();
                        energyField.setText(String.format("%.1fJ",val));
                        tempIntField.setText(String.format("%.2fºC",controller.getTemperaturaInterior()));
                        tempAlvoField.setText(controller.getTemperaturaInteriorAlvo()+"ºC");
                        double time = controller.stepTime();
                        timeField.setText(time+" min");
                        
                        if (!controller.isRestarting())
                        {
                            resetButton.setEnabled(true);
                        }
                        plotGraph1.addPoint(new PointDouble(time,val));
                        plotGraph1.repaint();
                    }
                });
                sTimer.setRepeats(true);
            }
        });

    }

    public void alterarIdioma() {
        this.ficheiro.setText(Simulacao.getFrasePelaChave("menu.file"));
        this.dados.setText(Simulacao.getFrasePelaChave("menu.dados"));
        this.uc8importar.setText(Simulacao.getFrasePelaChave("menu.dados.importar"));
        this.uc8exportar.setText(Simulacao.getFrasePelaChave("menu.dados.exportar"));
        this.uc7exportarHTML.setText(Simulacao.getFrasePelaChave("menu.dados.exportarHTML"));
        this.sair.setText(Simulacao.getFrasePelaChave("menu.sair"));
        this.sala.setText(Simulacao.getFrasePelaChave("menu.sala"));
        this.alterar.setText(Simulacao.getFrasePelaChave("menu.sala.alterar"));
        this.uc2alterarTemperatura.setText(Simulacao.getFrasePelaChave("menu.sala.alterar.temperatura"));
        this.uc1alterarPropriedades.setText(Simulacao.getFrasePelaChave("menu.sala.alterar.propriedades"));
        this.servidor.setText(Simulacao.getFrasePelaChave("menu.sala.servidor"));
        this.uc4criarServidor.setText(Simulacao.getFrasePelaChave("menu.sala.servidor.criar"));
        this.uc6alterarServidor.setText(Simulacao.getFrasePelaChave("menu.sala.servidor.editar"));
        this.material.setText(Simulacao.getFrasePelaChave("menu.sala.material"));
        this.uc3adicionarMaterial.setText(Simulacao.getFrasePelaChave("menu.sala.material.criar"));
        this.idioma.setText(Simulacao.getFrasePelaChave("menu.idioma"));
        this.uc12alterarIdioma.setText(Simulacao.getFrasePelaChave("menu.idioma.alterar"));
        this.uc5adicionarServidor.setText(Simulacao.getFrasePelaChave("menu.servidor.adicionar"));
        resetButton.setText(Simulacao.getFrasePelaChave("uc10.button.reset"));
        yscaleButton.setText(Simulacao.getFrasePelaChave("menu.button.yscale"));
        deltaTimeLabel.setText(Simulacao.getFrasePelaChave("uc10.slider.elapsedTime"));
        deltaTimeSlider.setToolTipText(Simulacao.getFrasePelaChave("uc10.slider.toolTip"));
        graphLabel.setText(Simulacao.getFrasePelaChave("uc10.plotgraph"));
        switch (playButtonState)
        {
            case 0:
                    playButton.setText(Simulacao.getFrasePelaChave("uc10.button.play"));
                break;
            case 1:
                    playButton.setText(Simulacao.getFrasePelaChave("uc10.button.resume"));
                break;
            case 2:
                    playButton.setText(Simulacao.getFrasePelaChave("uc10.button.stop"));
                break;
        }
        energyLabel.setText(Simulacao.getFrasePelaChave("uc10.label.refrigeration"));
        tempIntLabel.setText(Simulacao.getFrasePelaChave("uc10.label.temperaturaInterior"));
        tempAlvoLabel.setText(Simulacao.getFrasePelaChave("uc10.label.temperaturaAlvo"));
        timeQuantumLabel.setText(Simulacao.getFrasePelaChave("uc10.label.elapsedTime"));
        clearRoomButton.setText(Simulacao.getFrasePelaChave("menu.button.clearRoom"));
        this.alterarIdiomaJFileChooser();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        uc5adicionarServidor = new javax.swing.JButton();
        plotGraph1 = new bragasQuest.PlotGraph();
        graphLabel = new javax.swing.JLabel();
        energyLabel = new javax.swing.JLabel();
        tempIntLabel = new javax.swing.JLabel();
        tempAlvoLabel = new javax.swing.JLabel();
        energyField = new javax.swing.JTextField();
        tempIntField = new javax.swing.JTextField();
        tempAlvoField = new javax.swing.JTextField();
        playButton = new javax.swing.JButton();
        resetButton = new javax.swing.JButton();
        timeField = new javax.swing.JTextField();
        timeQuantumLabel = new javax.swing.JLabel();
        deltaTimeSlider = new javax.swing.JSlider();
        deltaTimeLabel = new javax.swing.JLabel();
        yscaleButton = new javax.swing.JButton();
        clearRoomButton = new javax.swing.JButton();
        barraMenu = new javax.swing.JMenuBar();
        ficheiro = new javax.swing.JMenu();
        dados = new javax.swing.JMenu();
        uc8importar = new javax.swing.JMenuItem();
        uc8exportar = new javax.swing.JMenuItem();
        uc7exportarHTML = new javax.swing.JMenuItem();
        sair = new javax.swing.JMenuItem();
        sala = new javax.swing.JMenu();
        alterar = new javax.swing.JMenu();
        uc2alterarTemperatura = new javax.swing.JMenuItem();
        uc1alterarPropriedades = new javax.swing.JMenuItem();
        servidor = new javax.swing.JMenu();
        uc4criarServidor = new javax.swing.JMenuItem();
        uc6alterarServidor = new javax.swing.JMenuItem();
        material = new javax.swing.JMenu();
        uc3adicionarMaterial = new javax.swing.JMenuItem();
        idioma = new javax.swing.JMenu();
        uc12alterarIdioma = new javax.swing.JMenuItem();

        jMenuItem4.setText("jMenuItem4");

        jMenu3.setText("jMenu3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        uc5adicionarServidor.setText(Simulacao.getFrasePelaChave("menu.servidor.adicionar")
        );
        uc5adicionarServidor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uc5adicionarServidorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout plotGraph1Layout = new javax.swing.GroupLayout(plotGraph1);
        plotGraph1.setLayout(plotGraph1Layout);
        plotGraph1Layout.setHorizontalGroup(
            plotGraph1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        plotGraph1Layout.setVerticalGroup(
            plotGraph1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 115, Short.MAX_VALUE)
        );

        graphLabel.setText(Simulacao.getFrasePelaChave("uc10.plotgraph")
        );

        energyLabel.setText(Simulacao.getFrasePelaChave("uc10.label.refrigeration")
        );

        tempIntLabel.setText(Simulacao.getFrasePelaChave("uc10.label.temperaturaInterior")
        );

        tempAlvoLabel.setText(Simulacao.getFrasePelaChave("uc10.label.temperaturaAlvo")
        );

        energyField.setEditable(false);
        energyField.setText("0J");

        tempIntField.setEditable(false);

        tempAlvoField.setEditable(false);

        playButton.setText(Simulacao.getFrasePelaChave("uc10.button.play"));
        playButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playButtonActionPerformed(evt);
            }
        });

        resetButton.setText(Simulacao.getFrasePelaChave("uc10.button.reset"));
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        timeField.setEditable(false);
        timeField.setText("0 min");

        timeQuantumLabel.setText(Simulacao.getFrasePelaChave("uc10.label.elapsedTime"));

        deltaTimeSlider.setForeground(new java.awt.Color(153, 153, 255));
        deltaTimeSlider.setMajorTickSpacing(1);
        deltaTimeSlider.setMaximum(30);
        deltaTimeSlider.setMinimum(1);
        deltaTimeSlider.setMinorTickSpacing(1);
        deltaTimeSlider.setPaintTicks(true);
        deltaTimeSlider.setSnapToTicks(true);
        deltaTimeSlider.setToolTipText(Simulacao.getFrasePelaChave("uc10.slider.toolTip"));
        deltaTimeSlider.setValue(1);

        deltaTimeLabel.setText(Simulacao.getFrasePelaChave("uc10.slider.elapsedTime"));

        yscaleButton.setText(Simulacao.getFrasePelaChave("menu.button.yscale"));
        yscaleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yscaleButtonActionPerformed(evt);
            }
        });

        clearRoomButton.setText(Simulacao.getFrasePelaChave("menu.button.clearRoom"));
        clearRoomButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearRoomButtonActionPerformed(evt);
            }
        });

        ficheiro.setText(Simulacao.getFrasePelaChave("menu.file"));

        dados.setText(Simulacao.getFrasePelaChave("menu.dados"));

        uc8importar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        uc8importar.setText(Simulacao.getFrasePelaChave("menu.dados.importar"));
        uc8importar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uc8importarActionPerformed(evt);
            }
        });
        dados.add(uc8importar);

        uc8exportar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        uc8exportar.setText(Simulacao.getFrasePelaChave("menu.dados.exportar"));
        uc8exportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uc8exportarActionPerformed(evt);
            }
        });
        dados.add(uc8exportar);

        uc7exportarHTML.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        uc7exportarHTML.setText(Simulacao.getFrasePelaChave("menu.dados.exportarHTML"));
        uc7exportarHTML.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uc7exportarHTMLActionPerformed(evt);
            }
        });
        dados.add(uc7exportarHTML);

        ficheiro.add(dados);

        sair.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        sair.setText(Simulacao.getFrasePelaChave("menu.sair"));
        sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sairActionPerformed(evt);
            }
        });
        ficheiro.add(sair);

        barraMenu.add(ficheiro);

        sala.setText(Simulacao.getFrasePelaChave("menu.sala"));

        alterar.setText(Simulacao.getFrasePelaChave("menu.sala.alterar"));

        uc2alterarTemperatura.setText(Simulacao.getFrasePelaChave("menu.sala.alterar.temperatura"));
        uc2alterarTemperatura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uc2alterarTemperaturaActionPerformed(evt);
            }
        });
        alterar.add(uc2alterarTemperatura);

        uc1alterarPropriedades.setText(Simulacao.getFrasePelaChave("menu.sala.alterar.propriedades"));
        uc1alterarPropriedades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uc1alterarPropriedadesActionPerformed(evt);
            }
        });
        alterar.add(uc1alterarPropriedades);

        sala.add(alterar);

        servidor.setText(Simulacao.getFrasePelaChave("menu.sala.servidor"));

        uc4criarServidor.setText(Simulacao.getFrasePelaChave("menu.sala.servidor.criar"));
        uc4criarServidor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uc4criarServidorActionPerformed(evt);
            }
        });
        servidor.add(uc4criarServidor);

        uc6alterarServidor.setText(Simulacao.getFrasePelaChave("menu.sala.servidor.editar"));
        uc6alterarServidor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uc6alterarServidorActionPerformed(evt);
            }
        });
        servidor.add(uc6alterarServidor);

        sala.add(servidor);

        material.setText(Simulacao.getFrasePelaChave("menu.sala.material"));

        uc3adicionarMaterial.setText(Simulacao.getFrasePelaChave("menu.sala.material.criar"));
        uc3adicionarMaterial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uc3adicionarMaterialActionPerformed(evt);
            }
        });
        material.add(uc3adicionarMaterial);

        sala.add(material);

        barraMenu.add(sala);

        idioma.setText(Simulacao.getFrasePelaChave("menu.idioma"));

        uc12alterarIdioma.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        uc12alterarIdioma.setText(Simulacao.getFrasePelaChave("menu.idioma.alterar"));
        uc12alterarIdioma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uc12alterarIdiomaActionPerformed(evt);
            }
        });
        idioma.add(uc12alterarIdioma);

        barraMenu.add(idioma);

        setJMenuBar(barraMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(deltaTimeSlider, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(tempIntLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tempIntField))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(tempAlvoLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tempAlvoField))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(timeQuantumLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(timeField, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(energyLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(energyField)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(uc5adicionarServidor, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                            .addComponent(plotGraph1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(yscaleButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(graphLabel)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(playButton, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(clearRoomButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(resetButton, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE))))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(108, 108, 108)
                .addComponent(deltaTimeLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(playButton)
                    .addComponent(resetButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(clearRoomButton)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(graphLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(plotGraph1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(energyLabel)
                            .addComponent(energyField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tempIntLabel)
                            .addComponent(tempIntField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tempAlvoLabel)
                            .addComponent(tempAlvoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(timeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(timeQuantumLabel))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(yscaleButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(deltaTimeLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(uc5adicionarServidor)
                    .addComponent(deltaTimeSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void uc12alterarIdiomaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uc12alterarIdiomaActionPerformed
        new AlterarIdiomaUI(this, true, simulacao);
    }//GEN-LAST:event_uc12alterarIdiomaActionPerformed

    private void sairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sairActionPerformed
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_sairActionPerformed

    private void uc8importarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uc8importarActionPerformed
        new ImportarDadosUI(this, true, this.simulacao);
    }//GEN-LAST:event_uc8importarActionPerformed

    private void uc8exportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uc8exportarActionPerformed
        new ExportarDadosUI(this, true, this.simulacao);
    }//GEN-LAST:event_uc8exportarActionPerformed

    private void uc5adicionarServidorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uc5adicionarServidorActionPerformed
        new AdicionarServidorUI(this, false, simulacao, simWindow.getUniverse()).setVisible(true);
    }//GEN-LAST:event_uc5adicionarServidorActionPerformed

    private void uc7exportarHTMLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uc7exportarHTMLActionPerformed
        new ExportarDadosHTMLUI(this, true, simulacao, controller).setVisible(true);
    }//GEN-LAST:event_uc7exportarHTMLActionPerformed

    private void uc2alterarTemperaturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uc2alterarTemperaturaActionPerformed
        // TODO add your handling code here:
        new AlterarTemperaturasUI(this, true, simulacao).setVisible(true);
    }//GEN-LAST:event_uc2alterarTemperaturaActionPerformed

    private void uc1alterarPropriedadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uc1alterarPropriedadesActionPerformed
        // TODO add your handling code here:
        new AlterarPropriedadesSalaUI(this, true, simulacao,simWindow).setVisible(true);
    }//GEN-LAST:event_uc1alterarPropriedadesActionPerformed

    private void uc4criarServidorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uc4criarServidorActionPerformed
        // TODO add your handling code here:
        new DefinirServidorUI(this, true, simulacao).setVisible(true);
    }//GEN-LAST:event_uc4criarServidorActionPerformed

    private void uc6alterarServidorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uc6alterarServidorActionPerformed
        new AlterarInformacaoServidorUI(this, true, simulacao).setVisible(true);
    }//GEN-LAST:event_uc6alterarServidorActionPerformed

    private void uc3adicionarMaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uc3adicionarMaterialActionPerformed
        // TODO add your handling code here:
        new AdicionarTipoMaterialUI(this, true, simulacao).setVisible(true);
    }//GEN-LAST:event_uc3adicionarMaterialActionPerformed

    private void playButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playButtonActionPerformed
        // TODO add your handling code here:
        if (!playButton.getText().equalsIgnoreCase(Simulacao.getFrasePelaChave("uc10.button.play")))
        {
            if (controller.isStopped())
            {
                playButton.setText(Simulacao.getFrasePelaChave("uc10.button.stop"));
                controller.continuarSimulacao();
                sTimer.start();
                playButtonState=2;
            }
            else
            {
                playButton.setText(Simulacao.getFrasePelaChave("uc10.button.resume"));
                controller.pararSimulacao();
                sTimer.stop();
                playButtonState=1;
            }
        }
        else
        {
            uc7exportarHTML.setEnabled(true);
            playButton.setText(Simulacao.getFrasePelaChave("uc10.button.stop"));
            sTimer.start();
            playButtonState=2;
        }
    }//GEN-LAST:event_playButtonActionPerformed

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        // TODO add your handling code here:
        resetButton.setEnabled(controller.recomecarSimulacao());
        plotGraph1.clearPoints();
    }//GEN-LAST:event_resetButtonActionPerformed

    private void yscaleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yscaleButtonActionPerformed
        // TODO add your handling code here:
        JDialog dialog = new JDialog();
        dialog.setLayout(new FlowLayout());
        dialog.add(new JLabel(Simulacao.getFrasePelaChave("menu.label.yscale")));
        dialog.setLocationRelativeTo(null);
        final JTextField numberField = new JTextField(8);
        dialog.add(numberField);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        JButton button = new JButton();
        button.setText(Simulacao.getFrasePelaChave("menu.button.yscale"));
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                    plotGraph1.setYScale(Double.parseDouble(numberField.getText()));
                }
                catch (NumberFormatException ex)
                {
                    JOptionPane.showMessageDialog(null, String.format(Simulacao.getFrasePelaChave("menu.yscale.numberFormat"),numberField.getText()),"",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        dialog.add(button);
        dialog.pack();
        dialog.setVisible(true);
        
    }//GEN-LAST:event_yscaleButtonActionPerformed

    private void clearRoomButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearRoomButtonActionPerformed
        // TODO add your handling code here:
        simulacao.getSala().removeServidoresInstanciados();
        simWindow.updateUniverse(simulacao.getSala());
    }//GEN-LAST:event_clearRoomButtonActionPerformed

    /**
     * Atribui uma nova simulação a esta UI.
     *
     * @param simulacao ({@link model.Simulacao}) A nova simulação.
     */
    public void setSimulacao(Simulacao simulacao) {
        this.simulacao = simulacao;
        controller = new CorrerSimulacaoController(simulacao);
    }

    public final void updateUniverse(Sala sala)
    {
        simWindow.updateUniverse(sala);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JanelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JanelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JanelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JanelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new JanelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu alterar;
    private javax.swing.JMenuBar barraMenu;
    private javax.swing.JButton clearRoomButton;
    private javax.swing.JMenu dados;
    private javax.swing.JLabel deltaTimeLabel;
    private javax.swing.JSlider deltaTimeSlider;
    private javax.swing.JTextField energyField;
    private javax.swing.JLabel energyLabel;
    private javax.swing.JMenu ficheiro;
    private javax.swing.JLabel graphLabel;
    private javax.swing.JMenu idioma;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenu material;
    private javax.swing.JButton playButton;
    private bragasQuest.PlotGraph plotGraph1;
    private javax.swing.JButton resetButton;
    private javax.swing.JMenuItem sair;
    private javax.swing.JMenu sala;
    private javax.swing.JMenu servidor;
    private javax.swing.JTextField tempAlvoField;
    private javax.swing.JLabel tempAlvoLabel;
    private javax.swing.JTextField tempIntField;
    private javax.swing.JLabel tempIntLabel;
    private javax.swing.JTextField timeField;
    private javax.swing.JLabel timeQuantumLabel;
    private javax.swing.JMenuItem uc12alterarIdioma;
    private javax.swing.JMenuItem uc1alterarPropriedades;
    private javax.swing.JMenuItem uc2alterarTemperatura;
    private javax.swing.JMenuItem uc3adicionarMaterial;
    private javax.swing.JMenuItem uc4criarServidor;
    private javax.swing.JButton uc5adicionarServidor;
    private javax.swing.JMenuItem uc6alterarServidor;
    private javax.swing.JMenuItem uc7exportarHTML;
    private javax.swing.JMenuItem uc8exportar;
    private javax.swing.JMenuItem uc8importar;
    private javax.swing.JButton yscaleButton;
    // End of variables declaration//GEN-END:variables

    private void alterarIdiomaJFileChooser() {
        // Títulos das Caixas de Diálogo
        UIManager.put("FileChooser.openDialogTitleText", Simulacao.getFrasePelaChave("openDialogTitleText"));
        UIManager.put("FileChooser.saveDialogTitleText", Simulacao.getFrasePelaChave("saveDialogTitleText"));
        // Botão "Importar"
        UIManager.put("FileChooser.openButtonText", Simulacao.getFrasePelaChave("openButtonText"));
        UIManager.put("FileChooser.openButtonMnemonic", Simulacao.getFrasePelaChave("openButtonMnemonic"));
        UIManager.put("FileChooser.openButtonToolTipText", Simulacao.getFrasePelaChave("openButtonToolTipText"));

        // Botão "Exportar"
        UIManager.put("FileChooser.saveButtonText", Simulacao.getFrasePelaChave("saveButtonText"));
        UIManager.put("FileChooser.saveButtonMnemonic", Simulacao.getFrasePelaChave("saveButtonMnemonic"));
        UIManager.put("FileChooser.saveButtonToolTipText", Simulacao.getFrasePelaChave("saveButtonToolTipText"));

        // Botão "Cancelar"
        UIManager.put("FileChooser.cancelButtonText", Simulacao.getFrasePelaChave("cancelButtonText"));
        UIManager.put("FileChooser.cancelButtonMnemonic", Simulacao.getFrasePelaChave("cancelButtonMnemonic"));
        UIManager.put("FileChooser.cancelButtonToolTipText", Simulacao.getFrasePelaChave("cancelButtonToolTipText"));

        // Botão "Ajuda"
        UIManager.put("FileChooser.helpButtonText", Simulacao.getFrasePelaChave("helpButtonText"));
        UIManager.put("FileChooser.helpButtonMnemonic", Simulacao.getFrasePelaChave("helpButtonMnemonic"));
        UIManager.put("FileChooser.helpButtonToolTipText", Simulacao.getFrasePelaChave("helpButtonToolTipText"));

        // Legenda "Procurar em:"
        UIManager.put("FileChooser.lookInLabelMnemonic", Simulacao.getFrasePelaChave("lookInLabelMnemonic"));
        UIManager.put("FileChooser.lookInLabelText", Simulacao.getFrasePelaChave("lookInLabelText"));

        // Legenda "Guardar em:"
        UIManager.put("FileChooser.saveInLabelText", Simulacao.getFrasePelaChave("saveInLabelText"));
        UIManager.put("FileChooser.saveInLabelMnemonic", Simulacao.getFrasePelaChave("saveInLabelMnemonic"));

        // Legenda "Tipo de ficheiros:"
        UIManager.put("FileChooser.filesOfTypeLabelText", Simulacao.getFrasePelaChave("filesOfTypeLabelText"));
        UIManager.put("FileChooser.filesOfTypeLabelMnemonic", Simulacao.getFrasePelaChave("filesOfTypeLabelMnemonic"));

        // Legenda "Nome do ficheiro:"
        UIManager.put("FileChooser.fileNameLabelMnemonic", Simulacao.getFrasePelaChave("fileNameLabelMnemonic"));
        UIManager.put("FileChooser.fileNameLabelText", Simulacao.getFrasePelaChave("fileNameLabelText"));

        // Filtro "Todos os Ficheiros"
        UIManager.put("FileChooser.acceptAllFileFilterText", Simulacao.getFrasePelaChave("acceptAllFileFilterText"));

        // Botão "Um nível acima"
        UIManager.put("FileChooser.upFolderToolTipText", Simulacao.getFrasePelaChave("upFolderToolTipText"));
        UIManager.put("FileChooser.upFolderAccessibleName", Simulacao.getFrasePelaChave("upFolderAccessibleName"));

        // Botão "Ambiente de Trabalho"
        UIManager.put("FileChooser.homeFolderToolTipText", Simulacao.getFrasePelaChave("homeFolderToolTipText"));
        UIManager.put("FileChooser.homeFolderToolTipText", Simulacao.getFrasePelaChave("homeFolderToolTipText"));
        UIManager.put("FileChooser.homeFolderAccessibleName", Simulacao.getFrasePelaChave("homeFolderAccessibleName"));

        // Botão "Nova Pasta"
        UIManager.put("FileChooser.newFolderToolTipText", Simulacao.getFrasePelaChave("newFolderToolTipText"));
        UIManager.put("FileChooser.newFolderAccessibleName", Simulacao.getFrasePelaChave("newFolderAccessibleName"));

        // Botão "Vista Lista"
        UIManager.put("FileChooser.listViewButtonToolTipText", Simulacao.getFrasePelaChave("listViewButtonToolTipText"));
        UIManager.put("FileChooser.listViewButtonAccessibleName", Simulacao.getFrasePelaChave("listViewButtonAccessibleName"));

        // Botão "Vista Detalhada"
        UIManager.put("FileChooser.detailsViewButtonToolTipText", Simulacao.getFrasePelaChave("detailsViewButtonToolTipText"));
        UIManager.put("FileChooser.detailsViewButtonAccessibleName", Simulacao.getFrasePelaChave("detailsViewButtonAccessibleName"));

        // Cabeçalhos da "Vista Lista Detalhada"
        UIManager.put("FileChooser.fileNameHeaderText", Simulacao.getFrasePelaChave("fileNameHeaderText"));
        UIManager.put("FileChooser.fileSizeHeaderText", Simulacao.getFrasePelaChave("fileSizeHeaderText"));
        UIManager.put("FileChooser.fileTypeHeaderText", Simulacao.getFrasePelaChave("fileTypeHeaderText"));
        UIManager.put("FileChooser.fileDateHeaderText", Simulacao.getFrasePelaChave("fileDateHeaderText"));
        UIManager.put("FileChooser.fileAttrHeaderText", Simulacao.getFrasePelaChave("fileAttrHeaderText"));
    }

}
