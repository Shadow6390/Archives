/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package navierstokes;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Hashtable;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Diogo
 */
public class NavierStokes {
    static int N;
    static final int cells=4;
    static int size,x_prev,y_prev,mouse_velocity=30;
    static double[] u,v,u_prev,v_prev,dens,dens_prev;
    static boolean mousePressed=false;
    
    /*
        dt=DeltaTime - Interval between the last update and current update. Normally, it can be 1 (as of 1 step), but if more seconds have passed, it should be higher.
    */
    
    /**
     * @param args the command line arguments
     */
    static double dt = 0.001,visc=-0.000,diff=0.000; //Values higher than 0 make the density increase slowly overtime. Values lower than 0 make the density disappear overtime.
        /*
            A value of 0 diff will make the density be stable.
        
            A value higher than 0 for visc will make the speed increase each step (as if a force was being applied).
            A value lower than 0 for visc (viscosity) will make the speed decrease each step until it reaches a halt.
            A value of 0 for visc will make no speed incrementation.
        
            dt represents the time that has passed between each step. Higher values for dt will make the simulation faster
        
            dt,visc & diff are proportional to each other, meaning if dt is 0.1, it will influencey visc and diff. Optimal configuration:
        visc=-0.001;
        diff=0.000;
        dt=0.001|0.1;
        */
    
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame mainFrame = new JFrame();
        MyCanvas canvas = new MyCanvas();
        canvas.setPreferredSize(new Dimension(512,512));
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setSize(512,512);
        mainFrame.setTitle("Fluid Simulation");
        mainFrame.setLayout(new FlowLayout());
        mainFrame.add(canvas);
        N=512/cells;
        size = (N+2)*(N+2);
        u = new double[size];
        v = new double[size]; 
        u_prev = new double[size]; //Horizontal speed
        v_prev = new double[size]; //Vertical speed
        dens = new double[size];
        dens_prev = new double[size];
     
        
        JPanel slidersPane = createSlidersPane();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.add(slidersPane);
        mainFrame.pack();
        mainFrame.setVisible(true);
        mainFrame.addMouseListener(new MouseListener(){

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton()==MouseEvent.BUTTON3)
                {
                    Point mouse = MouseInfo.getPointerInfo().getLocation();
                    System.out.println("I'm left");
                    /*boolean isEmpty=true;
                    for (int i=0;i<dens.length;i++)
                    {
                        if (dens[i]!=0)
                        {
                            isEmpty=false;
                            break;
                        }
                    }
                    if (isEmpty)
                    {
                        System.out.println("Density is empty");
                    }*/
                    //int mousex=mouse.x-mainFrame.getX();
                    //int mousey = mouse.y-mainFrame.getY();
                    mousePressed=true;
                new Thread(new Runnable(){

                    @Override
                    public void run() {
                        while (mousePressed)
                        {
                            Point mouse = MouseInfo.getPointerInfo().getLocation();
                            int mousex=mouse.x-mainFrame.getX();
                            int mousey = mouse.y-mainFrame.getY();
                            dens_prev[IX(mousex/cells,mousey/cells)]=255.0;
                            /*try
                            {
                                Thread.sleep(10);
                            }
                            catch (InterruptedException e)
                            {
                                
                            }*/
                        }
                    }
                    
                }).start();
                }
                else if (e.getButton()==MouseEvent.BUTTON1)
                {
                mousePressed=true;
                new Thread(new Runnable(){

                    @Override
                    public void run() {
                        while (mousePressed)
                        {
                            Point mouse = MouseInfo.getPointerInfo().getLocation();
                            //dens_prev[IX(mouse.x/cells,mouse.y/cells)]=0.8;
                            int mousex=mouse.x-mainFrame.getX();
                            int mousey = mouse.y-mainFrame.getY();
                            double distance = (double)(getDistance(mousex,x_prev,mousey,y_prev))*mouse_velocity;
                            double angle = getAngle(x_prev,y_prev,mousex,mousey);
                            //System.out.println("Angle: "+angle);
                            double velocityY = distance*Math.sin(angle);
                            double velocityX = distance*Math.cos(angle);
                            v_prev[IX(mousex/cells,mousey/cells)]=velocityY;
                            u_prev[IX(mousex/cells,mousey/cells)]=velocityX;
                            //System.out.println("X: "+mouse.x+"; X_PREV: "+x_prev);
                            //System.out.println("VelocityX: "+velocityX+";VelocityY: "+velocityY);
                            try
                            {
                                Thread.sleep(2);
                            }
                            catch (InterruptedException e)
                            {
                                
                            }
                            x_prev=mousex;
                            y_prev=mousey;
                        }
                    }
                    
                }).start();
                }
                else if (e.getButton()==MouseEvent.BUTTON2)
                {
                    Point mouse = MouseInfo.getPointerInfo().getLocation();
                    int mousex=mouse.x-mainFrame.getX();
                    int mousey = mouse.y-mainFrame.getY();
                    new Thread(new Runnable(){
                        int cellx =mousex/cells,celly=mousey/cells;
                        
                        @Override
                        public void run() {
                            while (true)
                            {
                                dens_prev[IX(cellx,celly)]=255.0;
                            }
                        }
                        
                    }).start();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                mousePressed=false;
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
                
            }
            
        });
        mainFrame.setResizable(false);
        boolean simulating=true;
        while (simulating)
        {
            //updateUIData(dens_prev,u_prev,v_prev);
            velStep(N,u,v,u_prev,v_prev,visc,dt);
            densStep(N,dens,dens_prev,u,v,diff,dt);
            canvas.drawDensity(N,dens,canvas.getGraphics());
            /*try
            {
                Thread.sleep(10);
            }
            catch(InterruptedException e)
            {
                System.out.println("eHRM");
            }*/
            cleanArray(dens_prev);
            //cleanArray(v_prev);
            //cleanArray(u_prev);
            capArray(dens);
        }
    }
    
    public static JPanel createSlidersPane()
    {
        GridBagLayout slidersLayout = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        c.gridy=0;
        //Delta Time
        JLabel timeLabel = new JLabel("Delta Time");
        JSlider timeSlider = new JSlider(JSlider.HORIZONTAL,1,1000,1);
        timeSlider.setPaintTicks(true);
        timeSlider.setMajorTickSpacing(100);
        timeSlider.setMinorTickSpacing(1);
        timeSlider.setPaintLabels(true);
        Hashtable labelTable = new Hashtable();
        labelTable.put( new Integer( 1 ), new JLabel("0") );
        labelTable.put( new Integer( 500 ), new JLabel("0.5") );
        labelTable.put( new Integer( 1000 ), new JLabel("1.0") );
        timeSlider.setLabelTable( labelTable );
        timeSlider.addChangeListener(new ChangeListener(){

            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                int deltaTime = source.getValue();
                dt=(double) deltaTime/1000.0;
            }
            
        });
        timeSlider.setToolTipText("This slider changes the delta time of the simulation, allowing to change the amount of time that has passed between each step of the simulation.");
        //Viscosity
        JLabel viscLabel = new JLabel("Viscosity");
        JSlider viscSlider = new JSlider(JSlider.HORIZONTAL,-10,10,0);
        viscSlider.setPaintTicks(true);
        viscSlider.setMajorTickSpacing(2);
        viscSlider.setMinorTickSpacing(1);
        viscSlider.setPaintLabels(true);
        Hashtable viscTable = new Hashtable();
        viscTable.put(-10, new JLabel("-0.100") );
        viscTable.put(0, new JLabel("0.0") );
        viscTable.put(10, new JLabel("0.100") );
        viscSlider.setLabelTable( viscTable );
        viscSlider.addChangeListener(new ChangeListener(){

            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                int viscosity = source.getValue();
                visc=(double) viscosity/100.0;
            }
            
        });
        viscSlider.setToolTipText("This slider changes the viscosity of the simulation, allowing to change the incrementation of the velocity.");
        //Diffusion
        
        JLabel diffLabel = new JLabel("Diffusion");
        JSlider diffSlider = new JSlider(JSlider.HORIZONTAL,-10,10,0);
        diffSlider.setPaintTicks(true);
        diffSlider.setMajorTickSpacing(2);
        diffSlider.setMinorTickSpacing(1);
        diffSlider.setPaintLabels(true);
        Hashtable diffTable = new Hashtable();
        diffTable.put(-10, new JLabel("-0.100") );
        diffTable.put( 0, new JLabel("0.0") );
        diffTable.put( 10, new JLabel("0.100") );
        diffSlider.setLabelTable( diffTable );
        diffSlider.addChangeListener(new ChangeListener(){

            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                int diffusion = source.getValue();
                diff=(double) diffusion/100.0;
            }
            
        });
        viscSlider.setToolTipText("This slider changes the diffusion rate of the simulation, allowing to change the incrementation of the density.");
        
        //Mouse Velocity
        JLabel velLabel = new JLabel("Mouse Velocity Multiplier");
        JSlider velSlider = new JSlider(JSlider.HORIZONTAL,0,30,30);
        velSlider.setPaintTicks(true);
        velSlider.setMajorTickSpacing(10);
        velSlider.setMinorTickSpacing(1);
        velSlider.setPaintLabels(true);
        Hashtable velTable = new Hashtable();
        velTable.put(0, new JLabel("0") );
        velTable.put(15, new JLabel("15") );
        velTable.put(30, new JLabel("30") );
        velSlider.setLabelTable( velTable );
        velSlider.addChangeListener(new ChangeListener(){

            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                mouse_velocity = source.getValue();
            }
            
        });
        
        viscSlider.setToolTipText("This slider changes the velocity multiplier of the mouse, allowing to make the mouse 'stronger' or 'weaker'.");
        
        JPanel slidersPane = new JPanel(slidersLayout);
        slidersPane.add(timeLabel,c);
        slidersPane.add(timeSlider,c);
        c.gridy=1;
        slidersPane.add(viscLabel,c);
        slidersPane.add(viscSlider,c);
        c.gridy=2;
        slidersPane.add(diffLabel,c);
        slidersPane.add(diffSlider,c);
        c.gridy=3;
        slidersPane.add(velLabel,c);
        slidersPane.add(velSlider,c);
        JButton helpButton = new JButton();
        helpButton.setText("Help");
        helpButton.addMouseListener(new MouseListener(){

            @Override
            public void mouseClicked(MouseEvent e) {
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                JFrame helpFrame = new JFrame();
                helpFrame.setTitle("Help");
                
                JPanel pane = new JPanel();
                JTextArea text = new JTextArea(5,20);
                text.setEditable(false);
                text.setText("Fluid Simulation - Navier Stokes\n"
                        + "\nThis program simulates how a fluid behaves according to Navier Stokes' equations to display a nice visual effect."
                        + "\n\nThe component 'deltaTime' represents the amount of time that has passed between each step of the simulation. The best suitable configuration is dt=0.001"
                        + "\n\nThe component 'viscosity' represents the viscosity of the liquid. This viscosity interacts with the simulation's velocity."
                        + "\nThe way it works is that it serves as a multiplier to increment or decrement the velocity of each cell, meaning that a value of 0 makes each cell have"
                        + "\n0 extra velocity, while positive values make the speed increase and negative values make the speed decrease."
                        + "\n\n The component 'diffusion' works similar to the viscosity, but instead of changing the velocity, it changes the density, meaning that it"
                        + "\neither increments (if the value is positive) or decrements (if the value is negative) the amount of density added to each cell."
                        + "\nFor example, if diffusion were to be at 0.1, it would add 0.1*dt*cellValue to a given cell, making it look like the simulation is 'creating' density."
                        + "\n\nThe 'mouse velocity multiplier' is a component that multiplies the velocity of the mouse by a given factor, enabling the control of the mouse's strength."
                        + "\n\nControls: Left Mouse Button - Move liquid;\nMiddle Mouse Button - Create source;\nRight Mouse Button - Add liquid.");
                pane.add(text);
                helpFrame.add(pane);
                helpFrame.pack();;
                helpFrame.setResizable(false);
                //helpFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                helpFrame.setVisible(true);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
                
            }
            
        });
        c.gridy=4;
        slidersPane.add(helpButton,c);
        return slidersPane;
    }
    public static double getAngle(double centerX, double centerY, double targetX,double targetY)
    {
        double angle = Math.atan2((targetY-centerY),(targetX-centerX));
        return angle;
    }
    public static void capArray(double[] arr)
    {
        for (int i=0;i<arr.length;i++)
        {
            if (arr[i]>255.0)
            {
                arr[i]=255.0;
            }
        }
    }
    public static void cleanArray(double[] arr)
    {
        for (int i=0;i<arr.length;i++)
        {
            arr[i]=0;
        }
    }
    public static double getDistance(int x,int x0,int y, int y0)
    {
        double w = Math.pow(x-x0, 2);
        double h = Math.pow(y-y0,2);
        return (Math.sqrt(w+h));
    }
    public static void updateUIData(double[] dens_prev,double[] u_prev,double[] v_prev)
    {
        
    }
    public static void addSource(int n,double[] x,double[] s,double dt)
    {
        for (int i=0;i<size;i++)
        {
            x[i]+=dt*s[i];
        }
    }
    public static void diffuse(int n,int b,double[] x,double[] x0,double diff,double dt)
    {
        int i,j,k;
        double a = dt*diff*n;
        for (k=0;k<20;k++)
        {
            for (i=1;i<=n;i++)
            {
                for (j=1;j<=n;j++)
                {
                    x[IX(i,j)] = (x0[IX(i,j)])+a*(x[IX(i-1,j)]+x[IX(i+1,j)]+x[IX(i,j+1)]+x[IX(i,j-1)])/(1+(4*a));
                }
            }
            setBoundary(n,b,x);
        }
    }
    public static void advect(int n,int b, double[] d,double[] d0,double[] u,double[] v,double dt)
    {
        int i,j,i0,j0,i1,j1;
        double x,y,s0,t0,s1,t1,dt0;
        dt0=dt*n;
        for (i=1;i<=n;i++)
        {
            for (j=1;j<=n;j++)
            {
                x=i-dt0*u[IX(i,j)];
                y=j-dt0*v[IX(i,j)];
                if (x<0.5) x=0.5; if (x>n+0.5) x = n+0.5;i0=(int)x;i1=i0+1;
                if (y<0.5) y=0.5; if (y>n+0.5) y=n+0.5;j0=(int)y; j1=j0+1;
                s1=x-i0;s0=1-s1;t1=y-j0;t0=1-t1;
                d[IX(i,j)] = s0*(t0*d0[IX(i0,j0)]+t1*d0[IX(i0,j1)])+
                             s1*(t0*d0[IX(i1,j0)]+t1*d0[IX(i1,j1)]);
            }
        }
        setBoundary(n,b,d);
    }
    public static void velStep(int n, double[] u,double[] v,double[] u0,double[] v0,double visc,double dt)
    {
        addSource(n,u,u0,dt); addSource(n,v,v0,dt);
        SWAP(u0,u); diffuse(n,1,u,u0,visc,dt);
        SWAP(v0,v); diffuse(n,2,v,v0,visc,dt);
        project(n,u,v,u0,v0);
        SWAP(u0,u); SWAP(v0,v);
        advect(n,1,u,u0,u0,v0,dt); advect(n,2,v,v0,u0,v0,dt);
        project(n,u,v,u0,v0);
    }
    public static void project(int n,double[] u,double[] v,double[] p,double[] div)
    {
        int i,j,k;
        double h;
        h=((double) (cells))/n;
        for (i=1;i<=n;i++)
        {
            for (j=1;j<=n;j++)
            {
                div[IX(i,j)] = -0.5*h*(u[IX(i,j)]-u[IX(i-1,j)]+
                                       v[IX(i,j+1)]-v[IX(i,j-1)] );
                p[IX(i,j)]=0;
            }
        }
        setBoundary(n,0,div);setBoundary(n,0,p);
        for ( k=0 ; k<20 ; k++ ) {
            for ( i=1 ; i<=N ; i++ ) {
                for ( j=1 ; j<=N ; j++ ) {
                    p[IX(i,j)] = (div[IX(i,j)]+p[IX(i-1,j)]+p[IX(i+1,j)]+
                    p[IX(i,j-1)]+p[IX(i,j+1)])/4;
                }
            }
            setBoundary( N, 0, p );
        }
        for ( i=1 ; i<=N ; i++ ) {
            for ( j=1 ; j<=N ; j++ ) {
                u[IX(i,j)] -= 0.5*(p[IX(i+1,j)]-p[IX(i-1,j)])/h;
                v[IX(i,j)] -= 0.5*(p[IX(i,j+1)]-p[IX(i,j-1)])/h;
            }
        }
        setBoundary ( N, 1, u ); setBoundary( N, 2, v );

    }
    public static void densStep(int n,double[] x,double[] x0,double[] u,double[] v,double diff, double dt)
    {
        addSource(n,x,x0,dt);
        SWAP(x0,x); diffuse(n,0,x,x0,diff,dt);
        SWAP(x0,x); advect(n,0,x,x0,u,v,dt);
    }
    /*public static void SWAP(double[] x0,double[] x)
    {
        double[] temp = x0;
        x0=x;
        x=temp;
    }*/
    public static void SWAP(double[] x0,double[] x)
    {
        double[] temp = new double[x0.length];
        for (int i=0;i<x0.length;i++)
        {
            temp[i]=x0[i];
        }
        for (int i=0;i<x.length;i++)
        {
            x0[i]=x[i];
        }
        for (int i=0;i<temp.length;i++)
        {
            x[i]=temp[i];
        }
    }
    public static void setBoundary(int n,int b, double[] x)
    {
        int i;
        for ( i=1 ; i<=n ; i++ ) {
            x[IX(0,i)] = (b==1) ? -x[IX(1,i)] : x[IX(1,i)];
            x[IX(n+1,i)] = (b==1) ? -x[IX(n,i)] : x[IX(n,i)];
            x[IX(i,0)] = (b==2) ? -x[IX(i,1)] : x[IX(i,1)];
            x[IX(i,n+1)] = (b==2) ? -x[IX(i,n)] : x[IX(i,n)];
        }
        x[IX(0 ,0 )] = 0.5*(x[IX(1,0 )]+x[IX(0 ,1)]);
        x[IX(0 ,n+1)] = 0.5*(x[IX(1,n+1)]+x[IX(0 ,n )]);
        x[IX(n+1,0 )] = 0.5*(x[IX(n,0 )]+x[IX(n+1,1)]);
        x[IX(n+1,n+1)] = 0.5*(x[IX(n,n+1)]+x[IX(n+1,n )]);
        
    }
    public static int IX(int i, int j)
    {
        return (i+((N+2)*j));
    }
}
