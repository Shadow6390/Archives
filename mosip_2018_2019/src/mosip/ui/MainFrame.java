/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mosip.ui;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.List;
import java.util.Random;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlRootElement;
import mosip.model.BaseShip;
import mosip.model.SimulationConfiguration;
import mosip.model.SimulationConfiguration.DistributionConfig;
import mosip.model.SimulationConfiguration.DummyDockingBay.Extractors;
import mosip.model.SimulationController;
import mosip.model.interfaces.IRandomDistribution;
import mosip.model.interfaces.IReport;
import mosip.model.randoms.ExponentialRandomDistribution;
import mosip.model.randoms.UniformRandomDistribution;
import mosip.model.reporting.CSVReport;
import mosip.model.reporting.XMLReport;
import mosip.model.simulation.Simulation;
import mosip.ui.MainFrame.Distributions;

/**
 *
 * @author Diogo
 */
public class MainFrame extends javax.swing.JFrame
{

    private String[] lambdas;

    @XmlEnum
    public static enum Distributions
    {

        Uniform, Exponential;

        public String value()
        {
            return name();
        }

        public static Distributions fromValue(String v)
        {
            return valueOf(v);
        }
    }

    @XmlEnum
    public static enum Policies
    {

        AcceptAll, MostFrequentOnly;

        public String value()
        {
            return name();
        }

        public static Policies fromValue(String v)
        {
            return valueOf(v);
        }
    }

    private DefaultListModel<SimulationConfiguration.DummyDockingBay> bayModel;

    /**
     * Creates new form MainFrame
     */
    public MainFrame()
    {
        initComponents();
        initLogics();
    }

    private void initLogics()
    {
        List<Distributions> dists = new ArrayList<>();
        JComboBox[] elems = new JComboBox[]
        {
            cruiseDistField,
            petrolDistField,
            cargoDistField,
            simulatorDistField,
            stairsDistField,
            pumpDistField,
            craneDistField
        };
        lambdas = new String[elems.length];
        dists.add(Distributions.Uniform);
        dists.add(Distributions.Exponential);
        for (int i = 0; i < elems.length; i++)
        {
            DefaultComboBoxModel<Distributions> model = new DefaultComboBoxModel<>();
            for (Distributions e : dists)
            {
                model.addElement(e);
            }
            elems[i].setModel(model);
            final int t = i;
            elems[i].addItemListener(new ItemListener()
            {
                int index = t;

                @Override
                public void itemStateChanged(ItemEvent e)
                {
                    if (e.getStateChange() == ItemEvent.SELECTED)
                    {
                        Distributions elem = (Distributions) e.getItem();
                        if (elem == Distributions.Exponential)
                        {
                            boolean trigger = false;
                            while (!trigger)
                            {
                                try
                                {
                                    lambdas[index] = JOptionPane.showInputDialog("Please insert the λ value for the exponential expression", 5);
                                    Double.parseDouble(lambdas[index]);
                                    trigger = true;
                                }
                                catch (NumberFormatException ex)
                                {
                                    printException(ex);
                                }
                            }
                        }
                    }
                }
            });
        }

        DefaultComboBoxModel<Policies> model = new DefaultComboBoxModel<>();
        model.addElement(Policies.AcceptAll);
        model.addElement(Policies.MostFrequentOnly);
        policyCombo.setModel(model);

        bayModel = new DefaultListModel<>();
        bayList.setModel(bayModel);
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

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        totalShipField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cruisePercentageField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        petrolPercentageField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cargoPercentageField = new javax.swing.JTextField();
        cruiseDistField = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        petrolDistField = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        cargoDistField = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        simulatorDistField = new javax.swing.JComboBox();
        cruiseRSField = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        simRSField = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        petrolRSField = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        cargoRSField = new javax.swing.JTextField();
        simulationStart = new javax.swing.JTextField();
        simulationEnd = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        simRunButton = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        frequencyField = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        pumpCheck = new javax.swing.JCheckBox();
        stairsNumberField = new javax.swing.JTextField();
        pumpNumberField = new javax.swing.JTextField();
        craneNumberField = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        bayList = new javax.swing.JList();
        jButton1 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        bridgeHeightField = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        bridgeSpeedField = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        stairsCheck = new javax.swing.JCheckBox();
        jLabel21 = new javax.swing.JLabel();
        craneCheck = new javax.swing.JCheckBox();
        jLabel28 = new javax.swing.JLabel();
        policyCombo = new javax.swing.JComboBox();
        stairsDistField = new javax.swing.JComboBox();
        jLabel32 = new javax.swing.JLabel();
        stairsRSField = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        pumpRSField = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        pumpDistField = new javax.swing.JComboBox();
        jLabel35 = new javax.swing.JLabel();
        craneRSField = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        craneDistField = new javax.swing.JComboBox();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        stairsChanceField = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        pumpChanceField = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        craneChanceField = new javax.swing.JTextField();
        repeatField = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        extensionField = new javax.swing.JComboBox();
        bundleReportsField = new javax.swing.JCheckBox();
        simulationProgress = new javax.swing.JProgressBar();
        jLabel31 = new javax.swing.JLabel();
        repeatRSField = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        saveConfiguration = new javax.swing.JMenuItem();
        loadConfiguration = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setText("Leixões Port Simulator");

        jLabel3.setText("Total Number of Ships:");

        jLabel4.setText("CruiseShip (%):");

        jLabel1.setText("Of which:");

        jLabel5.setText("PetrolShip (%):");

        jLabel6.setText("CargoShip (%):");

        cruiseDistField.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel7.setText("with Random Distribution:");

        jLabel8.setText("with Random Distribution:");

        petrolDistField.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel9.setText("with Random Distribution:");

        cargoDistField.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel10.setText("Simulator Random Distribution:");

        simulatorDistField.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel11.setText("Simulator Random Seed:");

        jLabel12.setText("and Random Seed:");

        simRSField.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                simRSFieldActionPerformed(evt);
            }
        });

        jLabel13.setText("and Random Seed:");

        petrolRSField.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                petrolRSFieldActionPerformed(evt);
            }
        });

        jLabel14.setText("and Random Seed:");

        cargoRSField.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                cargoRSFieldActionPerformed(evt);
            }
        });

        jLabel22.setText("Starting at:");

        jLabel23.setText("And ending at:");

        jLabel24.setText("minutes");

        jLabel25.setText("minutes");

        simRunButton.setText("Run Simulation");
        simRunButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                simRunButtonActionPerformed(evt);
            }
        });

        jLabel30.setText("With Frequency (0-100):");

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        pumpCheck.setText("Pump");
        pumpCheck.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                pumpCheckActionPerformed(evt);
            }
        });

        jButton2.setText("Create and Add Docking Bay");
        jButton2.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel15.setText("Docking Bays");

        jScrollPane1.setViewportView(bayList);

        jButton1.setText("Remove Selected Bay");
        jButton1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel16.setText("Docking Bay Creation");

        jLabel17.setText("Initial Bridge Height:");

        jLabel18.setText("Bridge Speed:");

        jLabel19.setText("meters");

        jLabel20.setText("meters/minute");

        stairsCheck.setText("Stairs");
        stairsCheck.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                stairsCheckActionPerformed(evt);
            }
        });

        jLabel21.setText("Extractors Present in Bay");

        craneCheck.setText("Crane");
        craneCheck.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                craneCheckActionPerformed(evt);
            }
        });

        jLabel28.setText("Policy:");

        policyCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        stairsDistField.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel32.setText("and Random Seed:");

        jLabel33.setText("with Random Distribution:");

        jLabel34.setText("and Random Seed:");

        pumpDistField.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel35.setText("with Random Distribution:");

        jLabel36.setText("and Random Seed:");

        craneDistField.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel37.setText("with Random Distribution:");

        jLabel38.setText("chance To Fail (%):");

        jLabel39.setText("chance To Fail (%):");

        jLabel40.setText("chance To Fail (%):");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(craneCheck)
                                    .addComponent(pumpCheck)
                                    .addComponent(stairsCheck))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(craneNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel37)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(craneDistField, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel36)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(craneRSField, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel40)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(craneChanceField, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(stairsNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel33)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(stairsDistField, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel32)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(stairsRSField, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel38)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(stairsChanceField, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(pumpNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel35)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(pumpDistField, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel34)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(pumpRSField, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel39)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(pumpChanceField, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel17)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(bridgeHeightField, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel18)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(bridgeSpeedField, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel20)))
                            .addComponent(jLabel21)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(73, 73, 73)
                                .addComponent(jLabel16))
                            .addComponent(jLabel28)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(policyCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jButton2))))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel16)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(bridgeHeightField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(bridgeSpeedField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20))
                        .addGap(12, 12, 12)
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel38)
                                .addComponent(stairsChanceField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(stairsDistField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel33)
                                .addComponent(jLabel32)
                                .addComponent(stairsRSField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(stairsCheck)
                                .addComponent(stairsNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel39)
                                .addComponent(pumpChanceField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(pumpDistField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel35)
                                .addComponent(jLabel34)
                                .addComponent(pumpRSField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(pumpCheck)
                                .addComponent(pumpNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel40)
                                .addComponent(craneChanceField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(craneDistField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel37)
                                .addComponent(jLabel36)
                                .addComponent(craneRSField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(craneCheck)
                                .addComponent(craneNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28)
                            .addComponent(policyCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        repeatField.setToolTipText("Setting a value on this field will disable the RandomSeeds set (except extractors)");

        jLabel26.setText("Repeat Simulation");

        jLabel27.setText("time(s)");

        jLabel29.setText("Save Reports As:");

        extensionField.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "CSV", "XML" }));

        bundleReportsField.setSelected(true);
        bundleReportsField.setText("Bundle Reports");
        bundleReportsField.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bundleReportsFieldActionPerformed(evt);
            }
        });

        simulationProgress.setStringPainted(true);

        jLabel31.setText("Random Seed:");

        repeatRSField.setToolTipText("The random seed to use for the random generator which generates random seeds for the other components");

        jMenu1.setText("File");

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("New UI Canvas");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Wipe Docking Bay");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        saveConfiguration.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        saveConfiguration.setText("Save Configuration...");
        saveConfiguration.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                saveConfigurationActionPerformed(evt);
            }
        });
        jMenu1.add(saveConfiguration);

        loadConfiguration.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        loadConfiguration.setText("Load Configuration...");
        loadConfiguration.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                loadConfigurationActionPerformed(evt);
            }
        });
        jMenu1.add(loadConfiguration);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(181, 181, 181))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(cargoPercentageField, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(cruisePercentageField, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(20, 20, 20)
                                .addComponent(petrolPercentageField, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel30)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(frequencyField, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(totalShipField, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(petrolDistField, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(petrolRSField, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cargoDistField, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cargoRSField, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cruiseDistField, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cruiseRSField, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(simulatorDistField, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel11)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(simRSField, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addGap(21, 21, 21)
                        .addComponent(extensionField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(bundleReportsField)
                    .addComponent(simulationProgress, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel23)
                                .addComponent(jLabel22))
                            .addComponent(jLabel26)
                            .addComponent(jLabel31))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(simRunButton)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(repeatRSField, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(repeatField, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(simulationEnd, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(simulationStart, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel24)
                                    .addComponent(jLabel25)
                                    .addComponent(jLabel27))))))
                .addGap(40, 40, 40))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel2)
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel22)
                                .addComponent(simulationStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel24))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(simulationEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel23)
                                    .addComponent(jLabel25)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(simulatorDistField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel10)
                                .addComponent(jLabel11)
                                .addComponent(simRSField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(cruisePercentageField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cruiseDistField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel12)
                            .addComponent(cruiseRSField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(petrolPercentageField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(petrolDistField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel13)
                            .addComponent(petrolRSField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(repeatField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26)
                            .addComponent(jLabel27))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel31)
                                .addComponent(repeatRSField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6)
                                .addComponent(cargoPercentageField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cargoDistField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel9)
                                .addComponent(jLabel14)
                                .addComponent(cargoRSField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel29)
                                    .addComponent(extensionField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(bundleReportsField)
                                .addGap(26, 26, 26)
                                .addComponent(simulationProgress, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(simRunButton)))
                        .addGap(26, 26, 26))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(totalShipField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(frequencyField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void simRSFieldActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_simRSFieldActionPerformed
    {//GEN-HEADEREND:event_simRSFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_simRSFieldActionPerformed

    private void petrolRSFieldActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_petrolRSFieldActionPerformed
    {//GEN-HEADEREND:event_petrolRSFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_petrolRSFieldActionPerformed

    private void cargoRSFieldActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_cargoRSFieldActionPerformed
    {//GEN-HEADEREND:event_cargoRSFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cargoRSFieldActionPerformed

    private void stairsCheckActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_stairsCheckActionPerformed
    {//GEN-HEADEREND:event_stairsCheckActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stairsCheckActionPerformed

    private void craneCheckActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_craneCheckActionPerformed
    {//GEN-HEADEREND:event_craneCheckActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_craneCheckActionPerformed

    private void pumpCheckActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_pumpCheckActionPerformed
    {//GEN-HEADEREND:event_pumpCheckActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pumpCheckActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton2ActionPerformed
    {//GEN-HEADEREND:event_jButton2ActionPerformed
        // TODO add your handling code here:
        SimulationConfiguration.DummyDockingBay bay = new SimulationConfiguration.DummyDockingBay();
        try
        {
            bay.bridgeHeight = Double.parseDouble(bridgeHeightField.getText());
            bay.bridgeSpeed = Double.parseDouble(bridgeSpeedField.getText());
            Random rand = new Random();
            int rs=0;
            double chance=0;
            DistributionConfig dist = null;
            if (stairsCheck.isSelected())
            {
                bay.extractors.put(Extractors.STAIRS, Integer.parseInt(stairsNumberField.getText()));
                String val = stairsRSField.getText();
                rs = rand.nextInt();
                if (val!=null && !val.isEmpty())
                {
                    rs = Integer.parseInt(val);
                }
                val = stairsChanceField.getText();
                if (val!=null && !val.isEmpty())
                {
                    chance = Double.parseDouble(val)/100.0;
                }
                dist=new DistributionConfig((Distributions)stairsDistField.getSelectedItem(),lambdas[4]);
                bay.extDists.put(Extractors.STAIRS, dist);
                bay.extRS.put(Extractors.STAIRS, rs);
                bay.failureChance.put(Extractors.STAIRS,chance);
            }
            if (pumpCheck.isSelected())
            {
                bay.extractors.put(Extractors.PUMP, Integer.parseInt(pumpNumberField.getText()));
                String val = pumpRSField.getText();
                rs = rand.nextInt();
                if (val!=null && !val.isEmpty())
                {
                    rs = Integer.parseInt(val);
                }
                val = pumpChanceField.getText();
                if (val!=null && !val.isEmpty())
                {
                    chance = Double.parseDouble(val)/100.0;
                }
                dist=new DistributionConfig((Distributions)stairsDistField.getSelectedItem(),lambdas[5]);
                bay.extDists.put(Extractors.PUMP, dist);
                bay.extRS.put(Extractors.PUMP, rs);
                bay.failureChance.put(Extractors.PUMP,chance);
            }
            if (craneCheck.isSelected())
            {
                bay.extractors.put(Extractors.CRANE, Integer.parseInt(craneNumberField.getText()));
                String val = craneRSField.getText();
                rs = rand.nextInt();
                if (val!=null && !val.isEmpty())
                {
                    rs = Integer.parseInt(val);
                }
                val = craneChanceField.getText();
                if (val!=null && !val.isEmpty())
                {
                    chance = Double.parseDouble(val)/100.0;
                }
                dist=new DistributionConfig((Distributions)stairsDistField.getSelectedItem(),lambdas[6]);
                bay.extDists.put(Extractors.CRANE, dist);
                bay.extRS.put(Extractors.CRANE, rs);
                bay.failureChance.put(Extractors.CRANE,chance);
            }
            switch ((Policies) policyCombo.getSelectedItem())
            {
                case MostFrequentOnly:
                    bay.policy = SimulationConfiguration.DummyDockingBay.Policies.ONLY_SHORT_DURATION;
                    break;
                default:
                case AcceptAll:
                    bay.policy = SimulationConfiguration.DummyDockingBay.Policies.ALL;
                    break;
            }
            bayModel.addElement(bay);
            bayList.updateUI();
        }
        catch (Exception e)
        {
            printException(e);
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton1ActionPerformed
    {//GEN-HEADEREND:event_jButton1ActionPerformed
        // TODO add your handling code here:
        //bayList.remove(bayList.getSelectedIndex());
        bayModel.remove(bayList.getSelectedIndex());
        bayList.updateUI();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void simRunButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_simRunButtonActionPerformed
    {//GEN-HEADEREND:event_simRunButtonActionPerformed
        // TODO add your handling code here:
        if (bayModel.isEmpty())
        {
            printErrorMessage("You must have at least one Docking Bay!");
        }
        else
        {
            Simulation sim = new Simulation();
            SimulationConfiguration config = new SimulationConfiguration(sim);
            config.bays = new SimulationConfiguration.DummyDockingBay[bayModel.size()];
            Enumeration<SimulationConfiguration.DummyDockingBay> it = bayModel.elements();
            int i = 0;
            while (it.hasMoreElements())
            {
                config.bays[i] = it.nextElement();
                i++;
            }
            try
            {
                config.expectedShipCount = Integer.parseInt(totalShipField.getText());
                config.spawnPercentage = new double[]
                {
                    Double.parseDouble(cruisePercentageField.getText()) * 0.01,
                    Double.parseDouble(petrolPercentageField.getText()) * 0.01,
                    Double.parseDouble(cargoPercentageField.getText()) * 0.01
                };
                config.shipType = new BaseShip.ShipType[]
                {
                    BaseShip.ShipType.CRUISE,
                    BaseShip.ShipType.OIL,
                    BaseShip.ShipType.CARGO
                };

                String repeatRS = repeatRSField.getText();
                Random tRand = new Random();
                if (repeatRS!=null && !repeatRS.isEmpty())
                {
                    tRand = new Random(Integer.parseInt(repeatRS));
                }
                
                final Random rand = tRand;

                config.randomSeeds = new int[]
                {
                    rand.nextInt(),
                    rand.nextInt(),
                    rand.nextInt()
                };
                if (cruiseRSField.getText() != null && !cruiseRSField.getText().isEmpty())
                {
                    config.randomSeeds[0] = Integer.parseInt(cruiseRSField.getText());
                }
                if (petrolRSField.getText() != null && !petrolRSField.getText().isEmpty())
                {
                    config.randomSeeds[1] = Integer.parseInt(petrolRSField.getText());
                }
                if (cargoRSField.getText() != null && !cargoRSField.getText().isEmpty())
                {
                    config.randomSeeds[2] = Integer.parseInt(cargoRSField.getText());
                }
                config.simRandomSeed = rand.nextInt();
                if (simRSField.getText() != null && !simRSField.getText().isEmpty())
                {
                    config.simRandomSeed = Integer.parseInt(simRSField.getText());
                }
                config.shipSpawnFrequency = Double.parseDouble(frequencyField.getText());
                config.simControllerDist = convertDistribution((Distributions) simulatorDistField.getSelectedItem(), 3);
                config.distributions = new IRandomDistribution[]
                {
                    convertDistribution((Distributions) cruiseDistField.getSelectedItem(), 0),
                    convertDistribution((Distributions) petrolDistField.getSelectedItem(), 1),
                    convertDistribution((Distributions) cargoDistField.getSelectedItem(), 2)
                };
                
                
                String repeatTimes = repeatField.getText();
                int repeat = 1;
                if (repeatTimes != null && !repeatTimes.isEmpty())
                {
                    try
                    {
                        repeat = Integer.parseInt(repeatField.getText());
                    }
                    catch (NumberFormatException e)
                    {
                        repeat = 1;
                    }
                }
                final int limit = repeat;
                final String baseName = new SimpleDateFormat("yyyy_MM_dd_HHmmss").format(Calendar.getInstance().getTime()) + "-Sim_";
                final String ext = (String) extensionField.getSelectedItem();
                final boolean bundleResults = bundleReportsField.isSelected();
                SwingWorker worker = new SwingWorker()
                {

                    @Override
                    protected Object doInBackground() throws Exception
                    {
                        try
                        {
                            IReport baseReport = null;
                            System.out.println("Hey");
                            for (int j = 0; j < limit; j++)
                            {
                                sim.clearPastSimulation();
                                System.out.println("Oi?");
                                SimulationController controller = new SimulationController(sim.getRandomFactory(), config);
                                System.out.println("Setup clock called");
                                sim.setupClock(Integer.parseInt(simulationStart.getText()),
                                        Integer.parseInt(simulationEnd.getText()));
                                controller.scheduleShipSpawn();
                                System.out.println("Ship spawned");
                                IReport report = new CSVReport();
                                if (ext.equalsIgnoreCase("xml"))
                                {
                                    report = new XMLReport();
                                }
                                System.out.println("Creating docking bay report areas");
                                controller.createDockingBayReportAreas(report);
                                System.out.println("Running simulation");
                                sim.runEventBased(report);
                                System.out.println("Simulation run");
                                controller.verboseResults();
                                controller.reportResults(report);
                                config.randomSeeds = new int[]
                                {
                                    rand.nextInt(),
                                    rand.nextInt(),
                                    rand.nextInt()
                                };
                                if (baseReport!=null)
                                {
                                    if (!bundleResults)
                                    {
                                        report.storeResults(new File(baseName+(j+1)+"_Results."+ext.toLowerCase()));
                                    }
                                    else
                                    {
                                        baseReport.mergeWith(report);
                                    }
                                }
                                else
                                {
                                    baseReport=report;
                                }
                                config.simRandomSeed = rand.nextInt();
                                double progress = (j + 1.0);
                                progress/= (double) limit;
                                progress*=100;
                                simulationProgress.setValue((int) Math.min(100.0, progress));
                            }
                            if (bundleResults && baseReport!=null)
                            {
                                baseReport.storeResults(new File(baseName+"Results."+ext.toLowerCase()));
                            }
                            JOptionPane.showMessageDialog(null, "Simulation run successfully! " + limit + " " + ext + " reports have been generated "+((bundleResults) ? "and bundled ": "")+"with the base name '" + baseName + "'");
                            simRunButton.setEnabled(true);
                        }
                        catch (Exception e)
                        {
                            printException(e);
                            simRunButton.setEnabled(true);
                        }
                        return null;
                    }
                };
                simRunButton.setEnabled(false);
                simulationProgress.setValue(0);
                worker.execute();
            }
            catch (Exception e)
            {
                printException(e);
                simRunButton.setEnabled(true);
            }
        }
    }//GEN-LAST:event_simRunButtonActionPerformed

    private void saveConfigurationActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_saveConfigurationActionPerformed
    {//GEN-HEADEREND:event_saveConfigurationActionPerformed
        // TODO add your handling code here:
        try
        {
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File(new File("").getAbsolutePath()));
            int result = chooser.showSaveDialog(this);
            if (result == JFileChooser.APPROVE_OPTION)
            {
                JAXBContext jaxbContext = JAXBContext.newInstance(UserInterfaceXML.class);
                Marshaller marshaller = jaxbContext.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                UserInterfaceXML uixml = new UserInterfaceXML();
                uixml.storeUI(this);
                File f = chooser.getSelectedFile();
                if (!f.getAbsolutePath().endsWith(".xml"))
                {
                    f = new File(f.getAbsolutePath() + ".xml");
                }
                marshaller.marshal(uixml, f);
            }
        }
        catch (JAXBException ex)
        {
            printException(ex);
        }
    }//GEN-LAST:event_saveConfigurationActionPerformed

    private void loadConfigurationActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_loadConfigurationActionPerformed
    {//GEN-HEADEREND:event_loadConfigurationActionPerformed
        // TODO add your handling code here:
        try
        {
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File(new File("").getAbsolutePath()));
            int result = chooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION)
            {
                JAXBContext jaxbContext = JAXBContext.newInstance(UserInterfaceXML.class);
                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                UserInterfaceXML uixml = (UserInterfaceXML) jaxbUnmarshaller.unmarshal(chooser.getSelectedFile());
                uixml.loadToUI(this);
            }
        }
        catch (JAXBException ex)
        {
            printException(ex);
        }
    }//GEN-LAST:event_loadConfigurationActionPerformed

    private void bundleReportsFieldActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_bundleReportsFieldActionPerformed
    {//GEN-HEADEREND:event_bundleReportsFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bundleReportsFieldActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItem1ActionPerformed
    {//GEN-HEADEREND:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        bayModel.clear();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItem2ActionPerformed
    {//GEN-HEADEREND:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        bayModel.clear();
        this.bridgeHeightField.setText("");
        this.bridgeSpeedField.setText("");
        this.simRSField.setText("");
        this.cargoPercentageField.setText("");
        this.cargoRSField.setText("");
        this.cruisePercentageField.setText("");
        this.cruiseRSField.setText("");
        this.craneNumberField.setText("");
        this.pumpNumberField.setText("");
        this.stairsNumberField.setText("");
        this.simulationEnd.setText("");
        this.simulationStart.setText("");
        this.frequencyField.setText("");
        this.petrolPercentageField.setText("");
        this.petrolRSField.setText("");
        this.totalShipField.setText("");
        this.repeatField.setText("");
        this.repeatRSField.setText("");
        this.stairsCheck.setSelected(false);
        this.pumpCheck.setSelected(false);
        this.craneCheck.setSelected(false);
        this.bundleReportsField.setSelected(true);
        this.cruiseDistField.setSelectedIndex(0);
        this.cargoDistField.setSelectedIndex(0);
        this.petrolDistField.setSelectedIndex(0);
        this.simulatorDistField.setSelectedIndex(0);
        this.extensionField.setSelectedIndex(0);
        this.policyCombo.setSelectedIndex(0);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    @XmlRootElement(name = "root")
    @XmlAccessorType(XmlAccessType.FIELD)
    private static class UserInterfaceXML
    {

        private String totalNumberShips;
        private String frequency;
        private String simulationRandomSeed;
        private String cargoRandomSeed;
        private String cruiseRandomSeed;
        private String petrolRandomSeed;
        private String simulationLambda;
        private String cargoLambda;
        private String cruiseLambda;
        private String petrolLambda;
        private String cargoProba;
        private String cruiseProba;
        private String petrolProba;
        private String simulationStart;
        private String simulationEnd;
        private Distributions simulationDist;
        private Distributions cargoDist;
        private Distributions cruiseDist;
        private Distributions petrolDist;
        private String repeatTimes;
        private String repeatRS;
        @XmlElementWrapper(name = "dockingbays")
        @XmlElement(name = "bay")
        private List<SimulationConfiguration.DummyDockingBay> bays;

        public UserInterfaceXML()
        {
            bays = new ArrayList();
        }

        public void storeUI(MainFrame frame)
        {
            setTotalNumberShips(frame.totalShipField.getText());
            setFrequency(frame.frequencyField.getText());
            setSimulationRandomSeed(frame.simRSField.getText());
            setCargoRandomSeed(frame.cargoRSField.getText());
            setCruiseRandomSeed(frame.cruiseRSField.getText());
            setPetrolRandomSeed(frame.petrolRSField.getText());
            setCargoProba(frame.cargoPercentageField.getText());
            setCruiseProba(frame.cruisePercentageField.getText());
            setPetrolProba(frame.petrolPercentageField.getText());
            setSimulationStart(frame.simulationStart.getText());
            setSimulationEnd(frame.simulationEnd.getText());
            setSimulationDist((Distributions) frame.simulatorDistField.getSelectedItem());
            setCargoDist((Distributions) frame.cargoDistField.getSelectedItem());
            setCruiseDist((Distributions) frame.cruiseDistField.getSelectedItem());
            setPetrolDist((Distributions) frame.petrolDistField.getSelectedItem());
            setSimulationLambda(frame.lambdas[3]);
            setCruiseLambda(frame.lambdas[0]);
            setPetrolLambda(frame.lambdas[1]);
            setCargoLambda(frame.lambdas[2]);
            setRepeatTimes(frame.repeatField.getText());
            setRepeatRS(frame.repeatRSField.getText());
            Enumeration<SimulationConfiguration.DummyDockingBay> it = frame.bayModel.elements();
            while (it.hasMoreElements())
            {
                getBays().add(it.nextElement());
            }
        }

        public void loadToUI(MainFrame frame)
        {
            frame.totalShipField.setText(getTotalNumberShips());
            frame.frequencyField.setText(getFrequency());
            frame.simRSField.setText(getSimulationRandomSeed());
            frame.cargoRSField.setText(getCargoRandomSeed());
            frame.cruiseRSField.setText(getCruiseRandomSeed());
            frame.petrolRSField.setText(getPetrolRandomSeed());
            frame.cargoPercentageField.setText(getCargoProba());
            frame.cruisePercentageField.setText(getCruiseProba());
            frame.petrolPercentageField.setText(getPetrolProba());
            frame.simulationStart.setText(getSimulationStart());
            frame.simulationEnd.setText(getSimulationEnd());
            frame.simulatorDistField.setSelectedItem(getSimulationDist());
            frame.cargoDistField.setSelectedItem(getCargoDist());
            frame.cruiseDistField.setSelectedItem(getCruiseDist());
            frame.petrolDistField.setSelectedItem(getPetrolDist());
            frame.lambdas[3] = getSimulationLambda();
            frame.lambdas[0] = getCruiseLambda();
            frame.lambdas[1] = getPetrolLambda();
            frame.lambdas[2] = getCargoLambda();
            frame.repeatField.setText(getRepeatTimes());
            frame.repeatRSField.setText(getRepeatRS());
            for (SimulationConfiguration.DummyDockingBay elem : getBays())
            {
                frame.bayModel.addElement(elem);
            }
        }

        /**
         * @return the totalNumberShips
         */
        public String getTotalNumberShips()
        {
            return totalNumberShips;
        }

        /**
         * @param totalNumberShips the totalNumberShips to set
         */
        public void setTotalNumberShips(String totalNumberShips)
        {
            this.totalNumberShips = totalNumberShips;
        }

        /**
         * @return the frequency
         */
        public String getFrequency()
        {
            return frequency;
        }

        /**
         * @param frequency the frequency to set
         */
        public void setFrequency(String frequency)
        {
            this.frequency = frequency;
        }

        /**
         * @return the simulationRandomSeed
         */
        public String getSimulationRandomSeed()
        {
            return simulationRandomSeed;
        }

        /**
         * @param simulationRandomSeed the simulationRandomSeed to set
         */
        public void setSimulationRandomSeed(String simulationRandomSeed)
        {
            this.simulationRandomSeed = simulationRandomSeed;
        }

        /**
         * @return the cargoRandomSeed
         */
        public String getCargoRandomSeed()
        {
            return cargoRandomSeed;
        }

        /**
         * @param cargoRandomSeed the cargoRandomSeed to set
         */
        public void setCargoRandomSeed(String cargoRandomSeed)
        {
            this.cargoRandomSeed = cargoRandomSeed;
        }

        /**
         * @return the cruiseRandomSeed
         */
        public String getCruiseRandomSeed()
        {
            return cruiseRandomSeed;
        }

        /**
         * @param cruiseRandomSeed the cruiseRandomSeed to set
         */
        public void setCruiseRandomSeed(String cruiseRandomSeed)
        {
            this.cruiseRandomSeed = cruiseRandomSeed;
        }

        /**
         * @return the petrolRandomSeed
         */
        public String getPetrolRandomSeed()
        {
            return petrolRandomSeed;
        }

        /**
         * @param petrolRandomSeed the petrolRandomSeed to set
         */
        public void setPetrolRandomSeed(String petrolRandomSeed)
        {
            this.petrolRandomSeed = petrolRandomSeed;
        }

        /**
         * @return the cargoProba
         */
        public String getCargoProba()
        {
            return cargoProba;
        }

        /**
         * @param cargoProba the cargoProba to set
         */
        public void setCargoProba(String cargoProba)
        {
            this.cargoProba = cargoProba;
        }

        /**
         * @return the cruiseProba
         */
        public String getCruiseProba()
        {
            return cruiseProba;
        }

        /**
         * @param cruiseProba the cruiseProba to set
         */
        public void setCruiseProba(String cruiseProba)
        {
            this.cruiseProba = cruiseProba;
        }

        /**
         * @return the petrolProba
         */
        public String getPetrolProba()
        {
            return petrolProba;
        }

        /**
         * @param petrolProba the petrolProba to set
         */
        public void setPetrolProba(String petrolProba)
        {
            this.petrolProba = petrolProba;
        }

        /**
         * @return the simulationStart
         */
        public String getSimulationStart()
        {
            return simulationStart;
        }

        /**
         * @param simulationStart the simulationStart to set
         */
        public void setSimulationStart(String simulationStart)
        {
            this.simulationStart = simulationStart;
        }

        /**
         * @return the simulationEnd
         */
        public String getSimulationEnd()
        {
            return simulationEnd;
        }

        /**
         * @param simulationEnd the simulationEnd to set
         */
        public void setSimulationEnd(String simulationEnd)
        {
            this.simulationEnd = simulationEnd;
        }

        /**
         * @return the cargoDist
         */
        public Distributions getCargoDist()
        {
            return cargoDist;
        }

        /**
         * @param cargoDist the cargoDist to set
         */
        public void setCargoDist(Distributions cargoDist)
        {
            this.cargoDist = cargoDist;
        }

        /**
         * @return the cruiseDist
         */
        public Distributions getCruiseDist()
        {
            return cruiseDist;
        }

        /**
         * @param cruiseDist the cruiseDist to set
         */
        public void setCruiseDist(Distributions cruiseDist)
        {
            this.cruiseDist = cruiseDist;
        }

        /**
         * @return the petrolDist
         */
        public Distributions getPetrolDist()
        {
            return petrolDist;
        }

        /**
         * @param petrolDist the petrolDist to set
         */
        public void setPetrolDist(Distributions petrolDist)
        {
            this.petrolDist = petrolDist;
        }

        /**
         * @return the bays
         */
        public List<SimulationConfiguration.DummyDockingBay> getBays()
        {
            return bays;
        }

        /**
         * @param bays the bays to set
         */
        public void setBays(List<SimulationConfiguration.DummyDockingBay> bays)
        {
            this.bays = bays;
        }

        /**
         * @return the simulationLambda
         */
        public String getSimulationLambda()
        {
            return simulationLambda;
        }

        /**
         * @param simulationLambda the simulationLambda to set
         */
        public void setSimulationLambda(String simulationLambda)
        {
            this.simulationLambda = simulationLambda;
        }

        /**
         * @return the cargoLambda
         */
        public String getCargoLambda()
        {
            return cargoLambda;
        }

        /**
         * @param cargoLambda the cargoLambda to set
         */
        public void setCargoLambda(String cargoLambda)
        {
            this.cargoLambda = cargoLambda;
        }

        /**
         * @return the cruiseLambda
         */
        public String getCruiseLambda()
        {
            return cruiseLambda;
        }

        /**
         * @param cruiseLambda the cruiseLambda to set
         */
        public void setCruiseLambda(String cruiseLambda)
        {
            this.cruiseLambda = cruiseLambda;
        }

        /**
         * @return the petrolLambda
         */
        public String getPetrolLambda()
        {
            return petrolLambda;
        }

        /**
         * @param petrolLambda the petrolLambda to set
         */
        public void setPetrolLambda(String petrolLambda)
        {
            this.petrolLambda = petrolLambda;
        }

        /**
         * @return the simulationDist
         */
        public Distributions getSimulationDist()
        {
            return simulationDist;
        }

        /**
         * @param simulationDist the simulationDist to set
         */
        public void setSimulationDist(Distributions simulationDist)
        {
            this.simulationDist = simulationDist;
        }

        /**
         * @return the repeatTimes
         */
        public String getRepeatTimes()
        {
            return repeatTimes;
        }

        /**
         * @param repeatTimes the repeatTimes to set
         */
        public void setRepeatTimes(String repeatTimes)
        {
            this.repeatTimes = repeatTimes;
        }

        /**
         * @return the repeatRS
         */
        public String getRepeatRS()
        {
            return repeatRS;
        }

        /**
         * @param repeatRS the repeatRS to set
         */
        public void setRepeatRS(String repeatRS)
        {
            this.repeatRS = repeatRS;
        }
    }

    public IRandomDistribution convertDistribution(Distributions d, int index)
    {
        IRandomDistribution result = null;
        switch (d)
        {
            case Uniform:
                result = new UniformRandomDistribution();
                break;
            case Exponential:
                result = new ExponentialRandomDistribution(Double.parseDouble(lambdas[index]));
                break;
        }
        return result;
    }

    private void printErrorMessage(String message)
    {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void printException(Exception e)
    {
        JOptionPane.showMessageDialog(null, e.getMessage(), "Error - " + e, JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Metal".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList bayList;
    private javax.swing.JTextField bridgeHeightField;
    private javax.swing.JTextField bridgeSpeedField;
    private javax.swing.JCheckBox bundleReportsField;
    private javax.swing.JComboBox cargoDistField;
    private javax.swing.JTextField cargoPercentageField;
    private javax.swing.JTextField cargoRSField;
    private javax.swing.JTextField craneChanceField;
    private javax.swing.JCheckBox craneCheck;
    private javax.swing.JComboBox craneDistField;
    private javax.swing.JTextField craneNumberField;
    private javax.swing.JTextField craneRSField;
    private javax.swing.JComboBox cruiseDistField;
    private javax.swing.JTextField cruisePercentageField;
    private javax.swing.JTextField cruiseRSField;
    private javax.swing.JComboBox extensionField;
    private javax.swing.JTextField frequencyField;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem loadConfiguration;
    private javax.swing.JComboBox petrolDistField;
    private javax.swing.JTextField petrolPercentageField;
    private javax.swing.JTextField petrolRSField;
    private javax.swing.JComboBox policyCombo;
    private javax.swing.JTextField pumpChanceField;
    private javax.swing.JCheckBox pumpCheck;
    private javax.swing.JComboBox pumpDistField;
    private javax.swing.JTextField pumpNumberField;
    private javax.swing.JTextField pumpRSField;
    private javax.swing.JTextField repeatField;
    private javax.swing.JTextField repeatRSField;
    private javax.swing.JMenuItem saveConfiguration;
    private javax.swing.JTextField simRSField;
    private javax.swing.JButton simRunButton;
    private javax.swing.JTextField simulationEnd;
    private javax.swing.JProgressBar simulationProgress;
    private javax.swing.JTextField simulationStart;
    private javax.swing.JComboBox simulatorDistField;
    private javax.swing.JTextField stairsChanceField;
    private javax.swing.JCheckBox stairsCheck;
    private javax.swing.JComboBox stairsDistField;
    private javax.swing.JTextField stairsNumberField;
    private javax.swing.JTextField stairsRSField;
    private javax.swing.JTextField totalShipField;
    // End of variables declaration//GEN-END:variables
}
