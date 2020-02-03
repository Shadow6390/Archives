/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbuntu;

/**
 *
 * @author jbraga
 */
public class Cbuntu {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
        IDE ide = new IDE();
        ide.setVisible(true);
    }
    
    
    /*public static void testing()
    {
        Pids pids;
        
        try
        {
            pids = new Pids(false,false);
            assertTrue("Deveria ter ocorrido uma exceção",false);
        }
        catch (RuntimeException e)
        {
            //Se for para aqui, o teste correu bem.
        }
        try
        {
            pids = new Pids(true,true);
            assertTrue("Deveria ter ocorrido uma exceção",false);
        }
        catch (RuntimeException e)
        {
            //Se for para aqui, o teste correu bem.
        }
    }
    
    private static class Pids {
        
        private boolean isIdiot;
        private boolean isUseful;
        public Pids(boolean flag,boolean returnTrue)
        {
            setIdiotState(flag);
            isUseful=returnTrue;
        }
        
        public void setIdiotState(boolean flag)
        {
            if (!flag)
            {
                throw new RuntimeException("Pids is nothing but stupid.");
            }
            isIdiot=true;
        }
        
        public boolean isUseful()
        {
            if (isUseful)
            {
                throw new RuntimeException("Don't lie to yourself.");
            }
            return false;
        }
    }*/
}
