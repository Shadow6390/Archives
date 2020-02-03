/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package navierstokes;

import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;

/**
 *
 * @author Diogo
 */
public class MyCanvas extends JPanel{
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);  
    }
    
    public void drawDensity(int n, double[] dens,Graphics g)
    {
        //Graphics2D g2d = (Graphics2D)g;
        for (int i=1;i<=n;i++)
        {
            for (int j=1;j<=n;j++)
            {
                //g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) dens[NavierStokes.IX(i, j)]));
                //g2d.fillRect(i*NavierStokes.cells,j*NavierStokes.cells,(i+1)*NavierStokes.cells,(j+1)*NavierStokes.cells);
                if (dens[NavierStokes.IX(i, j)]<0)
                {
                    dens[NavierStokes.IX(i, j)]=0;
                }
                //g.setColor(new Color(0,(int)min(dens[NavierStokes.IX(i,j)]*255.0,255.0),0));
                g.setColor(new Color(0,(int)min(dens[NavierStokes.IX(i,j)],255.0),0));
                //g.fillRect((i-1)*NavierStokes.cells,(j-1)*NavierStokes.cells,i*NavierStokes.cells,j*NavierStokes.cells);
                g.fillRect((i-1)*NavierStokes.cells,(j-1)*NavierStokes.cells,NavierStokes.cells,NavierStokes.cells);
                //System.out.println("min: "+min(dens[NavierStokes.IX(i,j)]*255.0,1.0));
            }
        }
    }
    public static double min(double ... values)
    {
        double result = values[0];
        for (int i=1;i<values.length;i++)
        {
            if (result>values[i])
            {
                result=values[i];
            }
        }
        return result;
    }
}
