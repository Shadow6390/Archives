/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuckyoupingu;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author jbraga
 */
public class JEditImagePanel extends JImagePanel{
    
    private Color selectedColor;
    
    public JEditImagePanel()
    {
        super();
        selectedColor=Color.BLACK;
    }
    
    public JEditImagePanel(BufferedImage image)
    {
        super(image);
        selectedColor=Color.BLACK;
    }

    public void paintPixelAt(int x,int y)
    {
        getImage().setRGB(x, y, selectedColor.getRGB());
    }
    
    /**
     * @return the selectedColor
     */
    public Color getSelectedColor() {
        return selectedColor;
    }

    /**
     * @param selectedColor the selectedColor to set
     */
    public void setSelectedColor(Color selectedColor) {
        this.selectedColor = selectedColor;
    }
}
