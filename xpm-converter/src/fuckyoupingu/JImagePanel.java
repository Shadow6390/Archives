/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuckyoupingu;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 * Uma extensão de JPanel que permite a visualização de imagens.
 * @author DC_G2
 */
public class JImagePanel extends JPanel{
    
    /**
     * Imagem a ser apresentada.
     */
    private BufferedImage image;
    
    public JImagePanel()
    {
        this(null);
    }
    
    public JImagePanel(BufferedImage image)
    {
        super();
        this.image=image;
    }
    
    /**
     * Atribui uma nova imagem a este painel.
     * @param image (BufferedImage) A imagem a desenhar.
     */
    public void setImage(BufferedImage image)
    {
        this.image=image;
        repaint();
    }
    
    /**
     * Devolve a imagem que está atualmente a ser desenhada.
     * @return (BufferedImage) A imagem a ser desenhada.
     */
    public BufferedImage getImage()
    {
        return image;
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawRect(0, 0, getWidth()-1, getHeight()-1);
        if (image!=null)
        {
            g.drawImage(image, 0,0,getWidth(), getHeight(), null);
        }
    }
}
