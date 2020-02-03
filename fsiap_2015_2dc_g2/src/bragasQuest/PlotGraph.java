/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bragasQuest;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * Classe que desenha um gráfico utilizando pontos. Wow. So much documentation. Very englihtening.
 */
public class PlotGraph extends JPanel{
    
    private Graph graph;
    private double middle=0;
    private double xscale;
    private double yscale;
    private int divisionFactor;
    
    public PlotGraph()
    {
        graph = new Graph();
        xscale=1;
        yscale=1;
        divisionFactor=2;
    }
    
    /**
     * Cria uma instância de {@link PlotGraph} com os parâmetros especificados.
     * @param dim (Dimension) A dimensão do grafo.
     * @param xscale (double) A escala em x.
     * @param yscale (double) A escala em y.
     */
    public PlotGraph(Dimension dim,double xscale,double yscale)
    {
        setPreferredSize(dim);
        graph = new Graph();
        this.xscale=xscale;
        this.yscale=yscale;
    }
    
    /**
     * Atribui uma nova dimensão a este {@link PlotGraph}.
     * @param dim (Dimension) A nova dimensão.
     */
    public void setDimension(Dimension dim)
    {
        setPreferredSize(dim);
        setMinimumSize(dim);
    }
    
    /**
     * Atribui um novo valor ao fator de divisão.
     * <p>
     * O fator de divisão é um fator que permite ditar onde começa a linha do grafo.
     * Um fator de divisão de 2 indica que a linha começa no meio do grafo.
     * @param divisionFactor 
     */
    public void setDivisionFactor(int divisionFactor)
    {
        this.divisionFactor=divisionFactor;
    }
    
    /**
     * Atribui um novo valor à escala no eixo do x.
     * @param xscale (double) O novo valor da escala do eixo do x.
     */
    public void setXScale(double xscale)
    {
        this.xscale=xscale;
    }
    
    /**
     * Atribui um novo valor à escala no eixo do y.
     * @param yscale (double) O novo valor da escala do eixo do y.
     */
    public void setYScale(double yscale)
    {
        this.yscale=yscale;
    }
    
    /**
     * Limpa todos os pontos no gráfico.
     */
    public void clearPoints()
    {
        graph.clearPoints();
        middle=0;
    }
    
    /**
     * Adiciona um ponto ao gráfico.
     * @param p ({@link PointDouble}) O novo ponto a adicionar.
     */
    public void addPoint(PointDouble p)
    {
        graph.add(p);
        middle--;
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawRect(0, 0, getWidth()-1, getHeight()-1);
        graph.draw(g, getWidth()/divisionFactor, middle, xscale,yscale, getHeight());
    }
    
    private class Graph{
        private ArrayList<PointDouble> points;
        
        public Graph()
        {
            points=new ArrayList();
        }
        
        /**
         * Adiciona um ponto a este gráfico.
         * @param p ({@link PointDouble}) O ponto a adicionar.
         * @return (boolean) Verdadeiro em caso de sucesso.
         */
        public boolean add(PointDouble p)
        {
            return points.add(p);
        }
        
        /**
         * Limpa todos os pontos no gráfico.
         */
        public void clearPoints()
        {
            points.clear();
        }
        
        /**
         * Desenha o gráfico.
         * @param g (Graphics) 
         * @param offset (int) Onde começa o eixo dos X.
         * @param shifting (double) Permite o grafo andar para a direita ou esquerda.
         * @param xscale (double) A escala em x.
         * @param yscale (double) A escala em y.
         */
        public void draw(Graphics g,int offset,double shifting,double xscale,double yscale,int height)
        {
            if (points.size()>1)
            {
                for (int i=0;i<points.size()-1;i++)
                {
                    PointDouble prevP=points.get(i),currP=points.get(i+1);
                    double x1=((offset+middle+prevP.x)*xscale),x2=((offset+middle+currP.x)*xscale),y1=height-prevP.y*yscale,y2=height-currP.y*yscale;
                    g.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
                }
            }
        }
    }
}
