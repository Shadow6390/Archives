/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web;

/**
 *
 * @author jbraga
 */
public class JSONFormatter {
    
    private String result;
    
    public JSONFormatter()
    {
        startBuilding();
    }
    
    public void startBuilding()
    {
        result="{\n";
    }
    
    public void addKeyString(String key,String str)
    {
        result+='"'+key+'"'+':'+'"'+str.replaceAll("\n", "\\\\n")+"\",\n";
    }
    
    public void addKeyValue(String key, String value)
    {
        result+='"'+key+'"'+':'+value+",\n";
    }
    
    public void addKeyArray(String key,String[] array)
    {
        result+='"'+key+'"'+":[";
        if (array.length==0)
        {
            result+="]\n";
        }
        else
        {
            for (int i=0;i<array.length-1;i++)
            {
                result+='"'+array[i]+"\",";
            }
            result+='"'+array[array.length-1]+"\"],\n";
        }
    }
    
    public void finalFormat()
    {
        result=result.substring(0,result.length()-2)+"\n}";
    }
    
    public String getBuiltJSON()
    {
        return result;
    }
}
