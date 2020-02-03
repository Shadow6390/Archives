/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.diagnostics.Explanation;
import com.model.Conclusion;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Represents a knowledge discovery session, holding the current state
 * of the session.
 * @author jbraga
 */
public class KnowledgeDiscoverySession {
    
    private List<Conclusion> conclusions;
    
    private int expectedReplyCount=0;
    
    private ExecutorService es;
    
    private Queue<InputRequest> inputRequests;
    
    private Queue<String> replies;
    
    public Semaphore requestSemaphore;
    
    public Semaphore inputSemaphore;
    
    private Map<Conclusion,Explanation> explanations;
    
    public KnowledgeDiscoverySession()
    {
        es = Executors.newSingleThreadExecutor();
        inputRequests = new LinkedList<>();
        replies = new LinkedList<>();
        requestSemaphore = new Semaphore(0);
        inputSemaphore = new Semaphore(0);
        expectedReplyCount=0;
        conclusions = new LinkedList<>();
        explanations = new HashMap<>();
    }
    
    /**
     * Adds an input request and locks the calling thread until it succeeds.
     * @param type
     * @param question
     * @param answers
     * @return 
     */
    public boolean addInputRequest(String type,String question,String[] answers) throws InterruptedException
    {
        boolean result = inputRequests.add(new InputRequest(type, question, answers));
        return result;
    }
    
    public InputRequest peekInputRequest()
    {
        return inputRequests.peek();
    }
    
    public boolean addReply(String reply)
    {
        return replies.add(reply);
    }
    
    public String getRequestedInput()
    {
        //inputSemaphore.release();
        expectedReplyCount--;
        return replies.poll();
    }

    public boolean hasInputRequests() {
        return !inputRequests.isEmpty();
    }

    public InputRequest getInputRequest() {
        expectedReplyCount++;
        return inputRequests.poll();
    }

    public boolean hasRepliesToGive() {
        return expectedReplyCount>0;
    }
    
    public boolean hasConclusion()
    {
        return !conclusions.isEmpty();
    }

    public boolean addConclusion(Conclusion conclusion) {
        return conclusions.add(conclusion);
    }
    
    public void addExplanation(Conclusion c,Explanation e)
    {
        explanations.put(c, e);
    }
    
    public Explanation explain(Conclusion c)
    {
        return explanations.get(c);
    }

    public Conclusion popConclusion() {
        return conclusions.remove(0);
    }

    public int numReplies() {
        return expectedReplyCount;
    }
    
    public class InputRequest
    {
        public String type;
        public String question;
        public String[] answers;
        
        public InputRequest(String type,String question,String[] answers)
        {
            this.type=type;
            this.question=question;
            this.answers=answers;
        }
    }
}
