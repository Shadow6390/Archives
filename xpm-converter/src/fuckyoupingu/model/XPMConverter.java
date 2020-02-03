/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuckyoupingu.model;

import fuckyoupingu.multiprocess.MultiController;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author jbraga
 */
public class XPMConverter {
    
    /**
     * Format:
     * ALPHA - 0
     * RED - 1
     * GREEN - 2
     * BLUE - 3
     */
    private static int[][] supportedColors;
    
    private String xpm;
    
    private String path;
    
    private String name;
    
    /**
     * The index of the color that is used for transparent pixels.
     */
    private static int transparencyIndex;
    
    /**
     * The threshold where it is opaque or not.
     */
    private static final int alphaThreshold=128;
    
    /**
     * Creates an instance of {@link XPMConverter} with null parameters.
     */
    public XPMConverter()
    {
        
    }
    
    /**
     * Creates an instance of {@link XPMConverter} with the specifeid parameters.
     * @param name (String) The name of the image.
     */
    public XPMConverter(String path,String name)
    {
        setPath(path);
        setName(name);
    }

    /**
     * @return the xpm
     */
    public String getXpm() {
        return xpm;
    }
    
    /**
     * Converts the image residing in this {@link XPMConverter} to XPM.
     * @param outputPath (String) The output folder.
     * @param lazy (boolean) Determines whether to print in code format or not.
     * @return (boolean) True if successfully converted.
     */
    public boolean save(BufferedImage image,String outputPath,boolean lazy) 
    {
        boolean result = true;
        try
        {
            String sExtra = (lazy) ? "\"" : "";
            String fExtra = (lazy) ? "\"," : "";
            char baseChar = '0';
            String cache=(lazy) ? "static char *"+name+"[] = {\n" : "";
            cache+=sExtra+String.valueOf(image.getWidth())+" "+String.valueOf(image.getHeight())+" "+String.valueOf(64)+fExtra+"\n";
            for (int i=0;i<supportedColors.length;i++)
            {
                cache+=sExtra+((char)(baseChar+i))+" "+String.valueOf(i)+fExtra+"\n";
            }
            for (int i=0;i<image.getHeight();i++)
            {
                cache+=sExtra;
                for (int j=0;j<image.getWidth();j++)
                {
                    int[] col = convertIntToArray(image.getRGB(j, i));
                    if (image.getType()==BufferedImage.TYPE_INT_ARGB)
                    {
                        if (col[0]>alphaThreshold)
                        {
                            cache+=(char)(baseChar+getColorIndex(image.getRGB(j, i)));
                        }
                        else
                        {
                            cache+=(char)(baseChar+transparencyIndex);
                        }
                    }
                    else
                    {
                        if ((col[0]==0 && col[1]==0 && col[2]==0 && col[3]==0))
                        {
                            cache+=(char)(baseChar+transparencyIndex);
                        }
                        else
                        {
                            cache+=(char)(baseChar+getClosestColor(image.getRGB(j, i)));
                        }
                    }
                }
                if (i!=image.getHeight()-1)
                {
                    cache+=fExtra+"\n";
                }
                else
                {
                    cache+=(fExtra.equals("")) ? "" : "\"";
                }
            }
            cache+="\n};";
            BufferedWriter buffer = new BufferedWriter(new FileWriter(new File(outputPath+File.pathSeparator+name+".xpm")));
            String[] chunk = cache.split("\n");
            for (int i=0;i<chunk.length;i++)
            {
                buffer.write(chunk[i]);
                buffer.newLine();
            }
            buffer.close();
            xpm=cache;
        }
        catch (IOException e)
        {
            result=false;
        }
        return result;
    }
    
    /**
     * Converts the image residing in this {@link XPMConverter} to XPM.
     * @param outputPath (String) The output folder.
     * @param lazy (boolean) Determines whether to print in code format or not.
     * @return (boolean) True if successfully converted.
     */
    public boolean convert(String outputPath,boolean lazy) 
    {
        boolean result = true;
        try
        {
            File file = new File(path);
            BufferedImage image = ImageIO.read(file);
            String sExtra = (lazy) ? "\"" : "";
            String fExtra = (lazy) ? "\"," : "";
            char baseChar = '0';
            String cache=(lazy) ? "static char *"+file.getName().substring(0, file.getName().indexOf("."))+"[] = {\n" : "";
            cache+=sExtra+String.valueOf(image.getWidth())+" "+String.valueOf(image.getHeight())+" "+String.valueOf(64)+fExtra+"\n";
            for (int i=0;i<supportedColors.length;i++)
            {
                cache+=sExtra+((char)(baseChar+i))+" "+String.valueOf(i)+fExtra+"\n";
            }
            for (int i=0;i<image.getHeight();i++)
            {
                cache+=sExtra;
                for (int j=0;j<image.getWidth();j++)
                {
                    int[] col = convertIntToArray(image.getRGB(j, i));
                    if (image.getType()==BufferedImage.TYPE_INT_ARGB)
                    {
                        if (col[0]>alphaThreshold)
                        {
                            cache+=(char)(baseChar+getClosestColor(image.getRGB(j, i)));
                        }
                        else
                        {
                            cache+=(char)(baseChar+transparencyIndex);
                        }
                    }
                    else
                    {
                        if ((col[0]==0 && col[1]==0 && col[2]==0 && col[3]==0))
                        {
                            cache+=(char)(baseChar+transparencyIndex);
                        }
                        else
                        {
                            cache+=(char)(baseChar+getClosestColor(image.getRGB(j, i)));
                        }
                    }
                }
                if (i!=image.getHeight()-1)
                {
                    cache+=fExtra+"\n";
                }
                else
                {
                    cache+=(fExtra.equals("")) ? "" : "\"";
                }
            }
            cache+="\n};";
            BufferedWriter buffer = new BufferedWriter(new FileWriter(new File(outputPath+File.pathSeparator+name+".xpm")));
            String[] chunk = cache.split("\n");
            for (int i=0;i<chunk.length;i++)
            {
                buffer.write(chunk[i]);
                buffer.newLine();
            }
            buffer.close();
            xpm=cache;
        }
        catch (IOException e)
        {
            result=false;
        }
        return result;
    }
    
    public boolean convertMultiProcess(String outputPath,boolean lazy,int numThreads) 
    {
        boolean result = true;
        try
        {
            File file = new File(path);
            BufferedImage image = ImageIO.read(file);
            String sExtra = (lazy) ? "\"" : "";
            String fExtra = (lazy) ? "\"," : "";
            char baseChar = '0';
            String cache=(lazy) ? "static char *"+file.getName().substring(0, file.getName().indexOf("."))+"[] = {\n" : "";
            cache+=sExtra+String.valueOf(image.getWidth())+" "+String.valueOf(image.getHeight())+" "+String.valueOf(64)+fExtra+"\n";
            for (int i=0;i<supportedColors.length;i++)
            {
                cache+=sExtra+((char)(baseChar+i))+" "+String.valueOf(i)+fExtra+"\n";
            }
            MultiController mc = new MultiController(numThreads);
            final Semaphore control = new Semaphore(1);
            String[] lines = new String[image.getHeight()];
            for (int currentIteration=0;currentIteration<image.getHeight();currentIteration++)
            {
                mc.submitTask(new MultiTask(currentIteration,image,baseChar,sExtra,fExtra,control,lines));
            }
            mc.awaitAllTasks();
            for (String element:lines)
            {
                cache+=element;
            }
            cache+="\n};";
            BufferedWriter buffer = new BufferedWriter(new FileWriter(new File(outputPath+File.pathSeparator+name+".xpm")));
            String[] chunk = cache.split("\n");
            for (int i=0;i<chunk.length;i++)
            {
                buffer.write(chunk[i]);
                buffer.newLine();
            }
            buffer.close();
            xpm=cache;
        }
        catch (IOException e)
        {
            result=false;
        }
        return result;
    }
    
    private static int getColorIndex(int col)
    {
        int result = 0; //In case we can't find it, we won't place it!
        boolean same = false;
        int[] compareColor = convertIntToArray(col);
        for (int i=0;i<supportedColors.length && !same;i++)
        {
            same = compareColor[0]==supportedColors[i][0];
            for (int j=1;j<4;j++)
            {
                same=same && compareColor[j]==supportedColors[i][j];
            }
            if (same)
            {
                result=i;
            }
        }
        return result;
    }
    
    private static int getClosestColor(int col)
    {
        int result = transparencyIndex; //In case we can't find it, we won't place it!
        int closestError = 1020;
        int currentError;
        int[] compareColor = convertIntToArray(col);
        for (int i=0;i<supportedColors.length;i++)
        {
            currentError=0;
            for (int j=0;j<4;j++)
            {
                currentError+=(int)Math.abs(compareColor[j]-supportedColors[i][j]);
            }
            if (currentError<closestError)
            {
                closestError=currentError;
                result=i;
            }
        }
        return result;
    }
    
    @Override
    public String toString()
    {
        return getName();
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * 
     * @param path
     * @throws IOException 
     */
    public static final void loadDefaultColors(InputStream stream) throws IOException
    {
        BufferedImage image = ImageIO.read(stream);
        final int maxColors = 64;
        supportedColors= new int[maxColors][4];
        final int initX=28,initY=20,width=56,height=41;
        int colIndex=0;
        for (int i=0;i<4;i++)
        {
            for (int j=0;j<16;j++)
            {
                int col = image.getRGB(initX+(width*j), initY+(height*i)); //Also gets alpha if it has.
                supportedColors[colIndex]=convertIntToArray(col);
                colIndex++;
            }
        }
        transparencyIndex=5;
    }
    
    private static int[] convertIntToArray(int col)
    {
        int[] result = new int[4];
        for (int k=0;k<4;k++)
        {
            result[3-k]=col >> (k*8) & 0xFF;
        }
        return result;
    }
    
    private static int convertArrayToInt(int[] col)
    {
        int result = 255;//col[0];
        for (int k=1;k<4;k++)
        {
            result=(result<<8) | (col[k] & 0xFF);
        }
        return result;
    }

    
    public static final char[][] rotateXPM(char[][] xpm,double angle)
    {
        char[][] result = new char[xpm.length][xpm[0].length];
        for (int y=0;y<xpm.length;y++)
        {
            for (int x=0;x<xpm[y].length;x++)
            {
                result[y][x]='5';
            }
        }
        final int halfWidth=xpm[0].length/2,halfHeight=xpm.length/2;
        for (int y=0;y<xpm.length;y++)
        {
            for (int x=0;x<xpm[y].length;x++)
            {
                int newX=(int)Math.round(((double)(x-halfWidth))*Math.cos(angle)-((double)(y-halfHeight))*Math.sin(angle))+halfWidth;
                int newY=(int)Math.round(((double)(x-halfWidth))*Math.sin(angle)+((double)(y-halfHeight))*Math.cos(angle))+halfHeight;
                if (newY>=0 && newY<xpm.length && newX>=0 && newX<xpm[y].length)
                {
                    result[newY][newX]=xpm[y][x];
                }
            }
        }
        return result;
    }
    
    public static final char[][] resizeXPM(char[][] xpm,int newWidth,int newHeight)
    {
        char[][] result = new char[newHeight][newWidth];
        final int width=xpm[0].length,height=xpm.length;
        int xRatio = (int) ((width<<16)/newWidth)+1;
        int yRatio = (int) ((height<<16)/newHeight)+1;
        
        for (int y=0;y<newHeight;y++)
        {
            for (int x=0;x<newWidth;x++)
            {   
                int px=((x*xRatio)>>16)
                        ,py=((y*yRatio)>>16);
                result[y][x]=xpm[py][px];
            }
        }
        return result;
    }
    
    /**
     * Converts the image residing in this {@link XPMConverter} to XPM.
     * @param outputPath (String) The output folder.
     * @param lazy (boolean) Determines whether to print in code format or not.
     * @return (boolean) True if successfully converted.
     */
    public boolean save(char[][] xpm,String outputPath,boolean lazy) 
    {
        boolean result = true;
        try
        {
            String sExtra = (lazy) ? "\"" : "";
            String fExtra = (lazy) ? "\"," : "";
            char baseChar = '0';
            String cache=(lazy) ? "static char *"+name+"[] = {\n" : "";
            cache+=sExtra+String.valueOf(xpm[0].length)+" "+String.valueOf(xpm.length)+" "+String.valueOf(64)+fExtra+"\n";
            for (int i=0;i<supportedColors.length;i++)
            {
                cache+=sExtra+((char)(baseChar+i))+" "+String.valueOf(i)+fExtra+"\n";
            }
            for (int i=0;i<xpm.length;i++)
            {
                cache+=sExtra;
                for (int j=0;j<xpm[i].length;j++)
                {
                    cache+=xpm[i][j];
                }
                if (i!=xpm.length-1)
                {
                    cache+=fExtra+"\n";
                }
                else
                {
                    cache+=(fExtra.equals("")) ? "" : "\"";
                }
            }
            cache+="\n};";
            BufferedWriter buffer = new BufferedWriter(new FileWriter(new File(outputPath+File.pathSeparator+name+".xpm")));
            String[] chunk = cache.split("\n");
            for (int i=0;i<chunk.length;i++)
            {
                buffer.write(chunk[i]);
                buffer.newLine();
            }
            buffer.close();
        }
        catch (IOException e)
        {
            result=false;
        }
        return result;
    }
    
    public static final char[][] getXPM(File file) throws IOException
    {
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String cache;
        cache = buffer.readLine().replace("\"","").replace(",","");
        if (cache.startsWith("static"))
        {
            cache=buffer.readLine().replace("\"","").replace(",","");
        }
        String[] size=cache.split(" ");
        int width=Integer.parseInt(size[0]),height=Integer.parseInt(size[1]);
        int numCols = Integer.parseInt(size[2]);
        char[][] result = new char[height][width];
        LinkedHashMap<String,Integer> colors=new LinkedHashMap();
        for (int i=0;i<numCols;i++)
        {
            cache=buffer.readLine().replace(",", "").replace("\"","");
            String[] chunk = cache.split(" ");
            colors.put(chunk[0], Integer.parseInt(chunk[1]));
        }
        int y=0;
        while ((cache=buffer.readLine())!=null)
        {
            if (!cache.startsWith(String.valueOf('}')))
            {
                cache=cache.replace(",","").replace("\"","");
                for (int x=0;x<cache.length();x++)
                {
                    result[y][x]=cache.charAt(x);
                }
                y++;
            }
        }
        buffer.close();
        return result;
    }
    
    public static final BufferedImage previewXPM(File file) throws IOException
    {
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String cache;
        cache = buffer.readLine().replace("\"","").replace(",","");
        if (cache.startsWith("static"))
        {
            cache=buffer.readLine().replace("\"","").replace(",","");
        }
        String[] size=cache.split(" ");
        int width=Integer.parseInt(size[0]),height=Integer.parseInt(size[1]);
        int numCols = Integer.parseInt(size[2]);
        BufferedImage result = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
        LinkedHashMap<String,Integer> colors=new LinkedHashMap();
        for (int i=0;i<numCols;i++)
        {
            cache=buffer.readLine().replace(",", "").replace("\"","");
            String[] chunk = cache.split(" ");
            colors.put(chunk[0], Integer.parseInt(chunk[1]));
        }
        int y=0;
        while ((cache=buffer.readLine())!=null)
        {
            if (!cache.startsWith(String.valueOf('}')))
            {
                cache=cache.replace(",","").replace("\"","");
                for (int x=0;x<cache.length();x++)
                {
                    int index = colors.get(String.valueOf(cache.charAt(x)));
                    result.setRGB(x,y,convertArrayToInt(supportedColors[index]));
                }
                y++;
            }
        }
        buffer.close();
        return result;
    }
    
    public static final Color getColor(int index)
    {
        int[] col = supportedColors[index];
        col[0] = 255;
        Color result = new Color(convertArrayToInt(col));
        return result;
    }
    public static final int colorsAvailable()
    {
        return supportedColors.length;
    }
    
    /**
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path the path to set
     */
    public void setPath(String path) {
        this.path = path;
    }
    
    private class MultiTask implements Runnable
    {
        private final int myIt;
        
        private BufferedImage image;
        
        private String sExtra,fExtra;
        private char baseChar;
        private Semaphore control;
        private String[] lines;
        
        public MultiTask(int currentIteration,BufferedImage image,
                char baseChar,String sextra,String fextra,Semaphore control,
                String[] lines)
        {
            this.lines=lines;
            this.control=control;
            this.myIt=currentIteration;
            this.image=image;
            this.baseChar=baseChar;
            this.sExtra=sextra;
            this.fExtra=fextra;
        }
        
        public void run()
        {
            String temp="";
            temp+=sExtra;
            for (int j=0;j<image.getWidth();j++)
            {
                int[] col = convertIntToArray(image.getRGB(j, myIt));
                if (image.getType()==BufferedImage.TYPE_INT_ARGB)
                {
                    if (col[0]>alphaThreshold)
                    {
                        temp+=(char)(baseChar+getClosestColor(image.getRGB(j, myIt)));
                    }
                    else
                    {
                        temp+=(char)(baseChar+transparencyIndex);
                    }
                }
                else
                {
                    if ((col[0]==0 && col[1]==0 && col[2]==0 && col[3]==0))
                    {
                        temp+=(char)(baseChar+transparencyIndex);
                    }
                    else
                    {
                        temp+=(char)(baseChar+getClosestColor(image.getRGB(j, myIt)));
                    }
                }
            }
            if (myIt!=image.getHeight()-1)
            {
                temp+=fExtra+"\n";
            }
            else
            {
                temp+=(fExtra.equals("")) ? "" : "\"";
            }
            try 
            {
                control.acquire();
                lines[myIt]=temp;
                control.release();
            } 
            catch (InterruptedException ex) 
            {
                Logger.getLogger(XPMConverter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
