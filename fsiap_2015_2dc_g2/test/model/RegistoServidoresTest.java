/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Diogo
 */
public class RegistoServidoresTest {

    RegistoServidores instance;
    Servidor serv1, serv2, serv3;

    public RegistoServidoresTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        serv1 = new Servidor();
        serv1.setNome("S1");
        serv1.setMassa(40d);
        serv2 = new Servidor();
        serv2.setNome("S2");
        serv2.setMassa(20d);
        serv3 = new Servidor();
        serv3.setNome("S3");
        serv3.setMassa(50d);
        ArrayList<Servidor> list = new ArrayList();
        list.add(serv1);
        list.add(serv2);
        list.add(serv3);
        instance = new RegistoServidores(list);

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getListaServidores method, of class RegistoServidores.
     */
    @Test
    public void testGetListaServidores() {
        System.out.println("getListaServidores");
        ArrayList<Servidor> expResult = new ArrayList();
        expResult.add(serv1);
        expResult.add(serv2);
        expResult.add(serv3);
        ArrayList<Servidor> result = instance.getListaServidores();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIndexOf method, of class RegistoServidores.
     */
    @Test
    public void testGetIndexOf() {
        System.out.println("getIndexOf");
        Servidor serv = new Servidor();
        serv.setNome("S2");
        int expResult = 1;
        int result = instance.getIndexOf(serv);
        assertEquals(expResult, result);
    }

    /**
     * Test of getIndexOf method, of class RegistoServidores.
     */
    @Test
    public void testGetIndexOfInvalido() {
        System.out.println("getIndexOf");
        Servidor serv = new Servidor();
        serv.setNome("S4");
        int expResult = -1;
        int result = instance.getIndexOf(serv);
        assertEquals(expResult, result);
    }

    /**
     * Test of registaAlteracao method, of class RegistoServidores.
     */
    @Test
    public void testRegistaAlteracao() {
        System.out.println("registaAlteracao");
        Servidor serv = new Servidor();
        serv.setNome("S1");
        serv.setMassa(4);
        int index = 0;
        boolean expResult = true;
        boolean result = instance.registaAlteracao(serv, index);

        boolean result2 = serv.getMassa() == 4;
        assertTrue("A massa deveria ter mudado.", result2);
    }

    /**
     * Test of getServidor method, of class RegistoServidores.
     */
    @Test
    public void testGetServidor() {
        System.out.println("getServidor");
        assertTrue("Servidores deviam ser iguais", instance.getServidor("S1").equals(serv1));
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

}
