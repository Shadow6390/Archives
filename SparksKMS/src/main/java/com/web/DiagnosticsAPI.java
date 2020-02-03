/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web;

import com.controller.KnowledgeDiscoverySession;
import com.diagnostics.KBSEngine;
import com.model.Conclusion;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.RandomStringUtils;
import spark.Service;
import static spark.Service.ignite;

/**
 * The Diagnostics API.
 *
 * @author jbraga
 */
public class DiagnosticsAPI {

    private final Service service;
    
    private Map<String,KBSEngine> engines;    
    
    public DiagnosticsAPI(int port) {
        service = ignite();
        service.port(port);
        engines = new HashMap<>();
    }

    /**
     * Sets up the API web server and starts it..
     */
    public void setupWebServer() {
        setupCrossOrigin();
        service.post("/api/thyroid-start",(req,res) ->{
            String key = RandomStringUtils.randomAlphanumeric(16); //Generate 16char key
            KBSEngine engine = new KBSEngine("Rules");
            engines.put(key, engine);
            engine.runEngine();
            JSONFormatter f = new JSONFormatter();
            try {
                while (!engine.kds.hasInputRequests())
                    Thread.sleep(100); //Wait for engine to boot and ask first question
                if (engine.kds.hasInputRequests())
                {
                    KnowledgeDiscoverySession.InputRequest elem = engine.kds.getInputRequest();
                    engine.kds.inputSemaphore.acquire(); //Consume so that next query waits.
                    f.startBuilding();
                    f.addKeyString("key", key);
                    f.addKeyString("input_type", elem.type);
                    f.addKeyString("question", elem.question);
                    if (elem.type.equals("choice"))
                    {
                        f.addKeyArray("choices", elem.answers);
                    }
                    f.finalFormat();
                    System.out.println(f.getBuiltJSON());
                    return f.getBuiltJSON();
                }
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
            return "ERR_STARTING_QUERY";
        });
        service.post("/api/thyroid-finish-query",(req, res) ->{
            String key = req.queryParams("key");
            KBSEngine engine = engines.get(key);
            String result = "NO_ENGINE";
            if (engine!=null)
            {
                engine.kSession.dispose();
                result = "TERMINATED";
            }
            return result;
        });
        service.post("/api/thyroid", (req, res) -> {
            String answer=req.queryParams("answer"),key=req.queryParams("key");
            Main.log("[API]Key="+key);
            Main.log(answer);
            KBSEngine engine = engines.get(key);
            if (engine==null)
            {
                String error = "ERR_NO_ENGINE";
                Main.log("No engine found for key "+key);
                return error;
            }
            res.type("application/json");
            JSONFormatter f = new JSONFormatter();
            try {
                if (!engine.kds.hasConclusion())
                {
                    if (engine.kds.hasInputRequests() || engine.kds.hasRepliesToGive())
                    {
                        engine.kds.requestSemaphore.release(); //Signal knowledge engine to continue
                        engine.kds.addReply(answer);
                        engine.kds.inputSemaphore.acquire(); //Wait for new input
                        if (!engine.kds.hasConclusion())
                        {
                            KnowledgeDiscoverySession.InputRequest elem = engine.kds.getInputRequest();
                            while (elem==null) //In case there are no inputs due to thread delay, we need to wait!
                            {
                                Thread.sleep(100);
                                Main.log("No input request detected. However, engine has count "+engine.kds.numReplies()+" of replies to give");
                                elem = engine.kds.getInputRequest();
                            }
                            f.startBuilding();
                            f.addKeyString("input_type", elem.type);
                            f.addKeyString("question", elem.question);
                            if (elem.type.equals("choice"))
                            {
                                f.addKeyArray("choices", elem.answers);
                            }
                            f.finalFormat();
                            return f.getBuiltJSON();
                        }
                        else
                        {
                            return sendConclusion(f,engine);
                        }
                    }
                }
                else
                {
                    return sendConclusion(f,engine);
                }
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
            return "ERR_INTERNAL_SERVER_ERROR_REPEAT_PROCESS";
            });
        
        service.post("/api/hypertension-start",(req,res) ->{
            String key = RandomStringUtils.randomAlphanumeric(16); //Generate 16char key
            Main.log("[API]Key="+key);
            KBSEngine engine = new KBSEngine("HeartRules");
            engines.put(key, engine);
            engine.runEngine();
            JSONFormatter f = new JSONFormatter();
            try {
                while (!engine.kds.hasInputRequests())
                    Thread.sleep(100); //Wait for engine to boot and ask first question
                Main.log("[API]Engine input request status:"+engine.kds.hasInputRequests());
                if (engine.kds.hasInputRequests())
                {
                    KnowledgeDiscoverySession.InputRequest elem = engine.kds.getInputRequest();
                    engine.kds.inputSemaphore.acquire(); //Consume so that next query waits.
                    f.startBuilding();
                    f.addKeyString("key", key);
                    f.addKeyString("input_type", elem.type);
                    f.addKeyString("question", elem.question);
                    if (elem.type.equals("choice"))
                    {
                        f.addKeyArray("choices", elem.answers);
                    }
                    f.finalFormat();
                    System.out.println(f.getBuiltJSON());
                    return f.getBuiltJSON();
                }
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
            return "ERR_STARTING_QUERY";
        });
        service.post("/api/hypertension-finish-query",(req, res) ->{
            String key = req.queryParams("key");
            Main.log("[API]Key '"+key+"' requested dispose on engine.");
            KBSEngine engine = engines.get(key);
            String result = "NO_ENGINE";
            if (engine!=null)
            {
                engine.kSession.dispose();
                result = "TERMINATED";
            }
            return result;
        });
        service.post("/api/hypertension", (req, res) -> {
            String answer=req.queryParams("answer"),key=req.queryParams("key");
            Main.log("[API]Key="+key);
            Main.log(answer);
            KBSEngine engine = engines.get(key);
            if (engine==null)
            {
                String error = "ERR_NO_ENGINE";
                Main.log("No engine found for key "+key);
                return error;
            }
            res.type("application/json");
            JSONFormatter f = new JSONFormatter();
            try {
                if (!engine.kds.hasConclusion())
                {
                    if (engine.kds.hasInputRequests() || engine.kds.hasRepliesToGive())
                    {
                        engine.kds.requestSemaphore.release(); //Signal knowledge engine to continue
                        engine.kds.addReply(answer);
                        engine.kds.inputSemaphore.acquire(); //Wait for new input
                        if (!engine.kds.hasConclusion())
                        {
                            KnowledgeDiscoverySession.InputRequest elem = engine.kds.getInputRequest();
                            while (elem==null) //In case there are no inputs due to thread delay, we need to wait!
                            {
                                Thread.sleep(100);
                                Main.log("No input request detected. However, engine has count "+engine.kds.numReplies()+" of replies to give");
                                elem = engine.kds.getInputRequest();
                            }
                            f.startBuilding();
                            f.addKeyString("input_type", elem.type);
                            f.addKeyString("question", elem.question);
                            if (elem.type.equals("choice"))
                            {
                                f.addKeyArray("choices", elem.answers);
                            }
                            f.finalFormat();
                            return f.getBuiltJSON();
                        }
                        else
                        {
                            return sendConclusion(f,engine);
                        }
                    }
                }
                else
                {
                    return sendConclusion(f,engine);
                }
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
            return "ERR_INTERNAL_SERVER_ERROR_REPEAT_PROCESS";
            });
    }

    private String sendConclusion(JSONFormatter f,KBSEngine engine)
    {
        f.startBuilding();
        Conclusion c = engine.kds.popConclusion();
        f.addKeyString("hasConclusion", "yes");
        f.addKeyString("conclusion", c.getDescription());
        String addInfo = c.getAdditionalInfo();
        f.addKeyString("conclusionInfo", (addInfo==null) ? "" : addInfo);
        String explain = engine.kds.explain(c).getHowExplanationArray(c.getId());
        f.addKeyValue("explanation",(explain==null) ? "" : explain);
        f.finalFormat();
        System.out.println(f.getBuiltJSON());
        return f.getBuiltJSON();
    }
    
    /**
     * Sets up the cross origin capabilities.
     */
    private void setupCrossOrigin() {
        service.options("/*",
                (request, response) -> {

                    String accessControlRequestHeaders = request
                            .headers("Access-Control-Request-Headers");
                    if (accessControlRequestHeaders != null) {
                        response.header("Access-Control-Allow-Headers",
                                accessControlRequestHeaders);
                    }

                    String accessControlRequestMethod = request
                            .headers("Access-Control-Request-Method");
                    if (accessControlRequestMethod != null) {
                        response.header("Access-Control-Allow-Methods",
                                accessControlRequestMethod);
                    }

                    return "OK";
                });

        service.before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));
    }
}
