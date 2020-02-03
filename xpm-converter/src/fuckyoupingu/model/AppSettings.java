/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuckyoupingu.model;

import fuckyoupingu.utils.FileEncryption;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import javax.swing.JOptionPane;

/**
 *
 * @author Diogo
 */
public final class AppSettings {
    
    private static final String SETTINGS_FILE = "settings.dt";
    
    private Map<String,String> settings;
    
    private AppSettings()
    {
        settings = new LinkedHashMap();
        loadSettings();
    }
    
    public void setUsingThreads(boolean value)
    {
        settings.put("usesThreads",String.valueOf(value));
    }
    
    public void setNumThreads(int value)
    {
        settings.put("numThreads",String.valueOf(value));
    }
    
    public boolean isUsingThreads()
    {
        return Boolean.parseBoolean(settings.get("usesThreads"));
    }
    
    public int getNumThreads()
    {
        return Integer.parseInt(settings.get("numThreads"));
    }
    
    public void saveSettings() throws Exception
    {
        String data = "";
        for (Entry<String,String> element:settings.entrySet())
        {
            data+=element.getKey()+"="+element.getValue()+"\n";
        }
        FileEncryption.encryptToFile(data.getBytes("UTF-8"), SETTINGS_FILE);
    }
    
    private void loadSettings()
    {
        try
        {
            if (!new File(SETTINGS_FILE).exists())
            {
                recreateSettingsFile();
            }
            
            byte[] data = FileEncryption.decryptFromFile(SETTINGS_FILE);
            String sData = new String(data,"UTF-8");
            for (String line:sData.split("\n"))
            {
                if (!sData.startsWith("#"))
                {
                    String[] chunk = line.split("=");
                    settings.put(chunk[0], chunk[1]);
                }
            }
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }
    
    private void recreateSettingsFile() throws Exception
    {
        String data = "usesThreads=true\n"
                + "numThreads="+Math.max(Runtime.getRuntime().availableProcessors()/2,1)+"\n";
        FileEncryption.encryptToFile(data.getBytes("UTF-8"), SETTINGS_FILE);
    }
    
    // use lazy holder idiom
    // https://en.wikipedia.org/wiki/Initialization-on-demand_holder_idiom
    private static class LazyHolder {

        private static final AppSettings INSTANCE = new AppSettings();

        /**
         * Private constructor.
         */
        private LazyHolder() {
            //Just to make this instantiation impossible.
        }
    }

    public static AppSettings getInstance() {
        return LazyHolder.INSTANCE;
    }
}
