/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bragasQuest;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;
import model.Simulacao;

/**
 *
 * @author jbraga
 */
public class JPositionPickerPanel extends JPanel{
    
    /**
     * Coordenada x do ponto escolhido.
     */
    private double x;
    /**
     * Coordenada y do ponto escolhido.
     */
    private double y;
    
    /**
     * Comprimento do espaço de amostragem.
     */
    private double width;
    
    /**
     * Largura do espaço de amostragem.
     */
    private double height;
    
    /**
     * Posição x do rato.
     */
    private double mouseX;
    
    /**
     * Posição y do rato.
     */
    private double mouseY;
    
    /**
     * Liberdade de escolha na posição ou baseada em grelha.
     */
    private boolean freeForm;
    
    /**
     * Array de listeners de {@link PositionPickerListener}.
     */
    private ArrayList<PositionPickerListener> listeners;
    
    /**
     * Construtor nulo de JPositionPickerPanel.
     */
    public JPositionPickerPanel()
    {
        this(false);
    }
    
    /**
     * Cria uma instância de {@link JPositionPickerPanel} com os parâmetros
     * especificados.
     * @param freeForm (boolean) Especifica se é de escolha livre ou não. 
     */
    public JPositionPickerPanel(boolean freeForm)
    {
        super();
        this.freeForm=freeForm;
        x=-1;
        y=-1;
        listeners = new ArrayList();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                double mx=e.getX(),my=e.getY();
                if (mx>=0 && my>=0 && mx<getWidth() && my<getHeight())
                {
                    x=mx*width/((double)getWidth());
                    y=my*height/((double)getHeight());
                    for (PositionPickerListener focus:listeners)
                    {
                        focus.onPositionChanged();
                    }
                }
            }
            
            @Override
            public void mouseMoved(MouseEvent e)
            {
                mouseX=e.getX();
                mouseY=e.getY();
            }
        });
        Timer timer;
        timer = new Timer(20,new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });
        timer.setRepeats(true);
        timer.start();
    }
    
    /**
     * Atribui uma nova dimensão à escolha de posição desta panel.
     * @param w (double) Comprimento do espaço.
     * @param h (double) Largura do espaço.
     */
    public void setDimensions(double w,double h)
    {
        width=w;
        height=h;
    }
    
    /**
     * Atribui um novo par de coordenadas para a posição escolhida.
     * @param x (double) A coordenada x da posição.
     * @param y (double) A coordenada y da posição.
     */
    public void setPosition(double x,double y)
    {
        setXCoord(x);
        setYCoord(y);
    }
    
    /**
     * Atribui um novo valor à coordenada x da posição escolhida.
     * @param coord (double) O novo valor da coordenada x.
     */
    public void setXCoord(double coord)
    {
        if (coord<0 || coord>width)
        {
            throw new IllegalArgumentException(Simulacao.getFrasePelaChave("extra.positionpicker.xOutOfRange"));
        }
        this.x=coord;
    }
    
    /**
     * Atribui um novo valor à coordenada y da posição escolhida.
     * @param coord (double) O novo valor da coordenada y.
     */
    public void setYCoord(double coord)
    {
        if (coord<0 || coord>height)
        {
            throw new IllegalArgumentException(Simulacao.getFrasePelaChave("extra.positionpicker.yOutOfRange"));
        }
        this.y=coord;
    }
    
    /**
     * Devolve o valor da coordenada x da posição escolhida.
     * @param precision (int) A precisão decimal do valor retornado.
     * @return (double) O valor da posição x. -1 caso ainda não tenha sido
     * escolhida nenhuma posição.
     */
    public double getXCoord(int precision)
    {
        return (x>=0) ? Math.round(Math.pow(10, precision)*x)*Math.pow(10,-precision) : -1;
    }
    
    /**
     * Devolve o valor da coordenada y da posição escolhida.
     * @param precision (int) A precisão decimal do valor retornado.
     * @return (double) O valor da posição y. -1 caso ainda não tenha sido
     * escolhida nenhuma posição.
     */
    public double getYCoord(int precision)
    {
        return (y>=0) ? Math.round(Math.pow(10, precision)*y)*Math.pow(10,-precision) : -1;
    }
    
    /**
     * Adiciona um {@link PositionPickerListener} a este {@link JPositionPickerPanel}.
     * @param l ({@link PositionPickerListener}) O listener para escutar mudanças de posição.
     * @return (boolean) Verdadeiro caso tenha sido adicionado.
     */
    public boolean addPositionPickerListener(PositionPickerListener l)
    {
        return listeners.add(l);
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.drawRect(1, 1, getWidth()-2, getHeight()-2);
        /*g.setColor(Color.YELLOW);
        g.fillOval((int) mouseX, (int)mouseY, 8, 8);*/
        g.setColor(Color.BLUE);
        if (x!=-1 && y!=-1)
        {
            g.fillOval((int) (x*((double)getWidth())/width), (int) (y*((double)getHeight())/height), 8, 8);
        }
    }
}
