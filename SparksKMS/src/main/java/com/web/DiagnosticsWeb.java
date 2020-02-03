/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web;

import com.mashape.unirest.http.Unirest;
import spark.Service;
import static spark.Service.ignite;

/**
 *
 * @author jbraga
 */
public class DiagnosticsWeb {
    private final Service service;
    
    private static final String API_URL="http://localhost:4567";
    
    public DiagnosticsWeb(int port) {
        service = ignite();
        service.port(port);
    }
    
    /**
     * Sets up the API web server and starts it..
     */
    public void setupWebServer() {
        Thread.currentThread().setContextClassLoader(Main.class.getClassLoader());
        //service.staticFileLocation("/web/index.html");
        service.staticFiles.location("/public");
        setServices();
        service.init();
    }
    
    /**
     * Set the services.
     */
    private void setServices()
    {
        service.get("/queryThyroid-start", (req, res) -> {
            String result = Unirest.post(API_URL+"/api/thyroid-start")
                    .asStringAsync().get().getBody();
            Main.log("Website: "+result);
            res.type("application/json");
            return result;
            });
        
        service.get("/queryThyroid", (req, res) -> {
            String answer = req.queryParams("answer");
            String key = req.queryParams("key");
            String result = Unirest.post(API_URL+"/api/thyroid")
                    .field("key", key)
                    .field("answer", answer)
                    .asStringAsync().get().getBody();
            Main.log("Website: "+answer);
            Main.log("Website: "+result);
            res.type("application/json");
            return result;
            });
        
        service.get("/queryThyroidStop", (req, res) -> {
            String key = req.queryParams("key");
            String result = Unirest.post(API_URL+"/api/thyroid-finish-query")
                    .field("key", key)
                    .asStringAsync().get().getBody();
            Main.log("Website: "+result);
            return result;
            });
        
        service.get("/queryHypertension-start", (req, res) -> {
            String result = Unirest.post(API_URL+"/api/hypertension-start")
                    .asStringAsync().get().getBody();
            Main.log("Website: "+result);
            res.type("application/json");
            return result;
            });
        
        service.get("/queryHypertension", (req, res) -> {
            String answer = req.queryParams("answer");
            String key = req.queryParams("key");
            String result = Unirest.post(API_URL+"/api/hypertension")
                    .field("key", key)
                    .field("answer", answer)
                    .asStringAsync().get().getBody();
            Main.log("Website: "+answer);
            Main.log("Website: "+result);
            res.type("application/json");
            return result;
            });
        
        service.get("/queryHypertensionStop", (req, res) -> {
            String key = req.queryParams("key");
            String result = Unirest.post(API_URL+"/api/hypertension-finish-query")
                    .field("key", key)
                    .asStringAsync().get().getBody();
            Main.log("Website: "+result);
            return result;
            });
    }
}
