/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbuntu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JViewport;
import javax.swing.KeyStroke;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author jbraga
 */
public class IDE extends JFrame{
    
    private JTabbedPane fileTabs;
    private JList<String> fileList;
    private DefaultListModel<String> model;
    private JMenuBar bar;
    private ArrayList<FileViewer> fileViewers;
    
    public IDE()
    {
        super();
        setTitle("CBuntu - C Editor for Ubuntu");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        fileViewers=new ArrayList();
        initiateComponents();
        
        pack();
    }
    private void initiateComponents()
    {
            bar = new JMenuBar();
            fileList = new JList();
            model = new DefaultListModel();
            fileList.setModel(model);
            fileList.setPreferredSize(new Dimension(110,400));
            fileList.setMinimumSize(new Dimension(110,400));
            fileList.addListSelectionListener(new ListSelectionListener() {

                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if (!e.getValueIsAdjusting())
                    {
                        fileTabs.setSelectedIndex(fileList.getSelectedIndex());
                    }
                }
            });
            
            initiateMenuBar();
            
            fileTabs = new JTabbedPane();
            fileTabs.addMouseListener(new MouseAdapter() {
            
                @Override
                public void mousePressed(MouseEvent e)
                {
                    System.out.println(fileTabs.getComponentAt(e.getX(), e.getY()));
                }
            });
            
            addFileTab("newFile");
            JPanel panel = new JPanel();
            panel.add(fileTabs);
            add(fileTabs,BorderLayout.EAST);
            add(new JScrollPane(fileList),BorderLayout.WEST);
            setJMenuBar(bar);
    }
    private void initiateMenuBar()
    {
        JMenu menu = new JMenu();
        
        menu.setText("File");
        JMenuItem newItem = new JMenuItem();
        newItem.setText("New File");
        newItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
        
        JMenuItem saveItem = new JMenuItem();
        saveItem.setText("Save");
        saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        
        JMenuItem saveAsItem = new JMenuItem();
        saveAsItem.setText("Save As...");
        
        JMenuItem openItem = new JMenuItem();
        openItem.setText("Open...");
        openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        
        newItem.addActionListener(new ActionListener() 
        {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                addFileTab("newFile");
            }
        });
        
        saveItem.addActionListener(new ActionListener() 
        {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                JScrollPane vp = (JScrollPane) fileTabs.getComponentAt(fileTabs.getSelectedIndex());
                JViewport sp = (JViewport)vp.getComponent(0);
                FileViewer fv = (FileViewer)sp.getComponent(0);
                try
                {
                    fv.saveContent();
                    fileTabs.setTitleAt(fv.getTabIndex(), fv.getFileName());
                    JOptionPane.showMessageDialog(null,"File successfully saved!");
                }
                catch (IOException |IllegalArgumentException ex)
                {
                    if (ex instanceof IllegalArgumentException)
                    {
                        displaySaveAsSystem();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"Error whilst saving file: "+ex.getMessage());
                    }
                }
            }
        });
        
        saveAsItem.addActionListener(new ActionListener() 
        {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                displaySaveAsSystem();
            }
        });
        
        openItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                int result = chooser.showOpenDialog(bar);
                switch (result)
                {
                    case JFileChooser.APPROVE_OPTION:
                            addFileTab(chooser.getSelectedFile());
                        break;
                }
            }
        });
        
        menu.add(newItem);
        menu.add(saveItem);
        menu.add(saveAsItem);
        menu.add(openItem);
        
        bar.add(menu);
    }
    
    private void displaySaveAsSystem()
    {
        JScrollPane vp = (JScrollPane) fileTabs.getComponentAt(fileTabs.getSelectedIndex());
        JViewport sp = (JViewport)vp.getComponent(0);
        FileViewer fv = (FileViewer)sp.getComponent(0);
        JFileChooser chooser = new JFileChooser();
        chooser.addChoosableFileFilter(new FileTypeFilter(".c","C source file"));
        chooser.addChoosableFileFilter(new FileTypeFilter(".cpp","C++ source file"));
        chooser.addChoosableFileFilter(new FileTypeFilter(".java","Java source file"));
        chooser.addChoosableFileFilter(new FileTypeFilter(".txt","Text file"));
        chooser.addChoosableFileFilter(new FileTypeFilter(".s","Assembly file"));
        int result = chooser.showSaveDialog(bar);
        switch (result)
        {
            case JFileChooser.APPROVE_OPTION:
                fv.setSavePath(chooser.getCurrentDirectory().getAbsolutePath());
                String fileName = chooser.getSelectedFile().getName();
                FileFilter ff = chooser.getFileFilter();
                if (ff instanceof FileTypeFilter)
                {
                    FileTypeFilter ftf = (FileTypeFilter)ff;
                    if (!fileName.contains("."))
                        fileName+=ftf.getExtension();
                }   
                fv.setFileName(fileName);
                System.out.println("File name:"+fileName);
                try
                {
                    fv.saveContent();
                    fileTabs.setTitleAt(fv.getTabIndex(), fv.getFileName());
                    JOptionPane.showMessageDialog(null,"File successfully saved!");
                }
                catch (IOException |IllegalArgumentException ex)
                {
                    JOptionPane.showMessageDialog(null,"Error whilst saving file: "+ex.getMessage());
                }
            break;
        }
    }
    
    private void addFileTab(File file)
    {
        try {
            FileViewer fv = new FileViewer(file);
            fv.setTabIndex(fileTabs.getTabCount());
            fileTabs.addTab(fv.toString(),new JScrollPane(fv));
            fileTabs.setTabComponentAt(fv.getTabIndex(), new CustomTab(fv.toString(),fv));
            fileTabs.setSelectedIndex(fileTabs.getTabCount()-1);
            fileViewers.add(fv);
            fv.addChangeListener(new ChangeListener() 
            {
                @Override
                public void onDocumentChanged(String currentData)
                {
                    CustomTab tab = (CustomTab)fileTabs.getTabComponentAt(fv.getTabIndex());
                    tab.setTitle("*"+fv.getFileName());
                }
            });
            model.addElement(fv.getFileName());
            pack();
        } catch (IOException ex) {
            Logger.getLogger(IDE.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void addFileTab(String file)
    {
        FileViewer fv = new FileViewer();
        fv.setFileName(file);
        fv.setTabIndex(fileTabs.getTabCount());
        fileTabs.addTab(fv.toString(),new JScrollPane(fv));
        fileTabs.setTabComponentAt(fv.getTabIndex(), new CustomTab(fv.toString(),fv));
        fileTabs.setSelectedIndex(fileTabs.getTabCount()-1);
        fv.addChangeListener(new ChangeListener() 
        {
            @Override
            public void onDocumentChanged(String currentData) 
            {
                CustomTab tab = (CustomTab)fileTabs.getTabComponentAt(fv.getTabIndex());
                tab.setTitle("*"+fv.getFileName());
            }
        });
        fileViewers.add(fv);
        model.addElement(fv.getFileName());
        pack();
    }
    
    protected class CustomTab extends JPanel
    {
        private JLabel label;
        private FileViewer fv;
        
        public CustomTab(String value,FileViewer fv)
        {
            super();
            this.fv=fv;
            label = new JLabel(value);
            add(label);
            JButton button = new JButton();
            button.setText("x");
            button.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    fileTabs.removeTabAt(fv.getTabIndex());
                    model.remove(fv.getTabIndex());
                    fileViewers.remove(fv.getTabIndex());
                    for (int i=fv.getTabIndex();i<fileViewers.size();i++)
                    {
                        fileViewers.get(i).setTabIndex(i);
                    }
                }
            });
            add(button);
        }
        public void setTitle(String title)
        {
            label.setText(title);
        }
    }
    
    protected class FileTypeFilter extends FileFilter{

        private final String extension;
        private final String description;
                
        public FileTypeFilter(String ext,String desc)
        {
            extension=ext;
            description=desc;
        }
        
        @Override
        public boolean accept(File f) 
        {
            if (f.isDirectory())
            {
                return true;
            }
            return f.getName().endsWith(extension);
        }
        public String getExtension()
        {
            return extension;
        }
        @Override
        public String getDescription() 
        {
            return description+"("+extension+")";
        }
        @Override
        public String toString()
        {
            return getDescription();
        }
        
    }
}
