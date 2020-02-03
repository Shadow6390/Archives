/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbuntu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

/**
 *
 * @author jbraga
 */
public class FileViewer extends JTextArea{
    
    private File file;
    private String fileName;
    private String savePath;
    private FileDocumentListener fdl;
    private int tabIndex;
    
    public FileViewer()
    {
        super(20,80);
        fdl=new FileDocumentListener();
        getDocument().addDocumentListener(fdl);
        tabIndex=-1;
    }
    
    public FileViewer(String file) throws IOException
    {
        this(new File(file));
    }
    public FileViewer(File file) throws IOException
    {
        super(20,80);
        this.file = file;
        savePath = this.file.getAbsolutePath();
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String cache;
        while ((cache=buffer.readLine())!=null)
        {
            appendLined(cache);
        }
        buffer.close();
        fileName=file.getName();
        fdl=new FileDocumentListener();
        getDocument().addDocumentListener(fdl);
        tabIndex=-1;
    }
    
    public void setTabIndex(int tab)
    {
        tabIndex=tab;
    }
    
    public int getTabIndex()
    {
        return tabIndex;
    }
    
    public void appendLined(String text)
    {
        append(text+"\n");
    }
    
    public void saveContent() throws IOException
    {
        if (savePath!=null)
        {
            BufferedWriter buffer = new BufferedWriter(new FileWriter(file));
            String[] data = this.getText().split("\n");
            for (int i=0;i<data.length;i++)
            {
                buffer.write(data[i]);
                buffer.newLine();
            }
            buffer.close();
        }
        else
        {
            throw new IllegalArgumentException("Save path not specified.");
        }
    }
    
    public void setFileName(String name)
    {
        fileName=name;
    }
    
    public String getFileName()
    {
        return fileName;
    }
    /**
     * Adds a change listener to this viewer.
     * <p>
     * An event is fired everytime content is changed on the editor.
     * @param cl (ChangeListener) The change listener to add.
     */
    public void addChangeListener(ChangeListener cl)
    {
        if (!fdl.listeners.add(cl)) throw new RuntimeException("Error whilst adding change listener.");
    }
    public void setSavePath(String path)
    {
        savePath=path;
    }
    
    @Override
    public String toString()
    {
        return fileName;
    }
    
    protected class FileDocumentListener implements DocumentListener
    {
        private ArrayList<ChangeListener> listeners;
        
        public FileDocumentListener()
        {
            listeners=new ArrayList();
        }
        
        @Override
        public void insertUpdate(DocumentEvent e) {
            for (ChangeListener focus:listeners)
            {
                try {
                    focus.onDocumentChanged(e.getDocument().getText(0, e.getDocument().getLength()));
                } catch (BadLocationException ex) {
                    Logger.getLogger(FileViewer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            for (ChangeListener focus:listeners)
            {
                try {
                    focus.onDocumentChanged(e.getDocument().getText(0, e.getDocument().getLength()));
                } catch (BadLocationException ex) {
                    Logger.getLogger(FileViewer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            for (ChangeListener focus:listeners)
            {
                try {
                    focus.onDocumentChanged(e.getDocument().getText(0, e.getDocument().getLength()));
                } catch (BadLocationException ex) {
                    Logger.getLogger(FileViewer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }
}
