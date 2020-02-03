/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author jbraga
 */
public class Main {

    static JTextArea logger;
    private static final boolean EXHIBIT_FRAME_LOG=false;
    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        
        if (EXHIBIT_FRAME_LOG)
        {
            JFrame frame = new JFrame();
            logger = new JTextArea(20,40);
            logger.setEditable(false);
            frame.add(new JScrollPane(logger));
            frame.pack();
            new DiagnosticsAPI(4567).setupWebServer();
            new DiagnosticsWeb(4566).setupWebServer();
            frame.setVisible(true);
        }
        else
        {
            new DiagnosticsAPI(4567).setupWebServer();
            new DiagnosticsWeb(4566).setupWebServer();
        }
    }
    
    public static void log(String message)
    {
        if (EXHIBIT_FRAME_LOG)
        {
            logger.append(message+"\n");
        }
    }
}
