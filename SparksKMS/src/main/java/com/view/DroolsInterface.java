package com.view;

import com.controller.KnowledgeDiscoverySession;
import java.util.Collection;


import org.kie.api.runtime.ClassObjectFilter;
import org.kie.api.runtime.KieSession;

import com.diagnostics.KBSEngine;
import com.model.Evidence;
import com.model.Thyroid;
import com.model.interfaces.UserInput;
import com.model.interfaces.UserInputETL;
import com.web.Main;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DroolsInterface {

    private KieSession KS;
    
    private KnowledgeDiscoverySession kds;
    
    public static final String CHOICE = "choice";
    public static final String INPUT = "input";
    
    public DroolsInterface(KBSEngine hmr,KnowledgeDiscoverySession kds)
    {
        KS = hmr.getKieSession();
        this.kds=kds;
        KS.setGlobal("dUI", this);
    }

    public boolean compare(String ev,String operator,String value)
    {
        boolean result = false;
        
        Evidence e = fetchEvidence(ev);
        if (e!=null)
        {
            try
            {
                
                Double v = Double.parseDouble(value);
                Double val = Double.parseDouble(e.getValue());
                
                
                
                if (operator.equals("="))
                {
                    result = val==v;
                }
                else if (operator.equals(">"))
                {
                    result = val>v;
                }
                else if (operator.equals("<"))
                {
                    result = val<v;
                    
                }
                else if (operator.equals(">="))
                {
                    result = val>=v;
                    
                }
                else if (operator.equals("<="))
                {
                    result = val<=v;
                }
            }
            catch (NumberFormatException ex)
            {
                //Do nothing.
                
            }
        }
        return result;
    }
    
    public boolean answer(String ev, String v) 
    {
        return baseAnswer(ev, v, (Object o) -> getFromChoiceDialog(ev,new String[]{"sim","não"}));
    }
    
    public boolean complexAnswer(String ev,String value,UserInput i)
    {
        return baseAnswer(ev, value, i);
    }

    public boolean answerChoices(String ev, String v, String[] choices/*,UserInputETL<String> uif*/) 
    {
        return baseAnswer(ev, v, (Object o) -> getFromChoiceDialog(ev, choices));
    }
    
    public Double regularInputDouble(String question)
    {
        Double result = 0d;
        String answer = null;
        try {
            if (kds.addInputRequest(INPUT,question,null))
            {
                kds.inputSemaphore.release();
                kds.requestSemaphore.acquire(); //Wait for input request to be fulfilled.
                answer = kds.getRequestedInput(); //Pops from the stack
                result = Double.parseDouble(answer);
            }
        } catch (InterruptedException | NumberFormatException ex) {
            Logger.getLogger(DroolsInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public String regularInputChoices(String ev, String[] answers) {
        String answer = null;
        try {
            if (kds.addInputRequest(CHOICE,ev,answers))
            {
                kds.inputSemaphore.release();
                kds.requestSemaphore.acquire(); //Wait for input request to be fulfilled.
                answer = kds.getRequestedInput(); //Pops from the stack
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(DroolsInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
        return answer;
    }
    
    public boolean insertIfNotExistsInputDouble(String ev)
    {
        return insertIfNotExists(ev, (Object o)->{return String.valueOf(regularInputDouble(ev));});
    }
    
    public boolean insertIfNotExistsInputChoices(String ev,String[] choices)
    {
        return insertIfNotExists(ev, (Object o)->{return regularInputChoices(ev, choices);});
    }
    
    public boolean insertIfNotExistsCombineTSH(String ev)
    {
        return insertIfNotExists(ev, (Object o)->{
            
            Evidence age = fetchEvidence(Evidence.IDADE_TSH);
            Evidence value = fetchEvidence(Evidence.VALOR_TSH);
            
            return Thyroid.fromTSHValueToClassification(Double.parseDouble(value.getValue()), 
                    age.getValue());
        });
    }
    
    public boolean insertIfNotExistsCombineT4L(String ev)
    {
        return insertIfNotExists(ev, (Object o)->{
            
            Evidence age = fetchEvidence(Evidence.IDADE_T4L);
            Evidence value = fetchEvidence(Evidence.VALOR_T4L);
            
            return Thyroid.fromT4LValueToClassification(Double.parseDouble(value.getValue()), 
                    age.getValue());
        });
    }
    
    private Evidence fetchEvidence(String ev)
    {
        @SuppressWarnings("unchecked")
        Collection<Evidence> evidences = (Collection<Evidence>) KS.getObjects(new ClassObjectFilter(Evidence.class));
        Evidence evidence = null;
        for (Evidence e : evidences) {
            if (e.getEvidence().compareTo(ev) == 0) {
                evidence = e;
                break;
            }
        }
        return evidence;
    }
    
    public boolean insertIfNotExists(String ev,UserInput i)
    {
        Evidence evidence = fetchEvidence(ev);
        
        boolean questionFound = evidence!=null;
        if (questionFound) {
            
                return true;
        }

        String value = i.getUserInput(ev);
        
        Evidence e = new Evidence(ev, value);
        KS.insert(e);
        KBSEngine.getAgendaEventListener().addLhs(e);

        return true;
    }
    
    public boolean answerManual(String ev, String v, UserInputETL etl)
    {
        return baseAnswer(ev, v, (Object o) -> getFromManualInputDialog(ev, etl));
    }

    private boolean baseAnswer(String ev, String v, UserInput i) {
        @SuppressWarnings("unchecked")
        Collection<Evidence> evidences = (Collection<Evidence>) KS.getObjects(new ClassObjectFilter(Evidence.class));
        boolean questionFound = false;
        Evidence evidence = null;
        for (Evidence e : evidences) {
            if (e.getEvidence().compareTo(ev) == 0) {
                questionFound = true;
                evidence = e;
                break;
            }
        }
        
        if (questionFound) {
            if (evidence.getValue().compareTo(v) == 0) {
                KBSEngine.getAgendaEventListener().addLhs(evidence);
                return true;
            } else {
                return false;
            }
        }

        //If we don't have enough input, we'll set a request for it.
        String value = i.getUserInput(null);
        
        Evidence e = new Evidence(ev, value);
        KS.insert(e);

        if (value.compareTo(v) == 0) {
            KBSEngine.getAgendaEventListener().addLhs(e);
            //gui.addEvidence(e.toString());
            return true;
        } else {
            return false;
        }
    }
    
    private String getFromManualInputDialog(String ev, UserInputETL<String> etl) {
        String answer = null;
        kds.inputSemaphore.release();
        try {
            if (kds.addInputRequest(INPUT,ev,null))
            {
                kds.requestSemaphore.acquire(); //Wait for input request to be fulfilled.
                answer = kds.getRequestedInput(); //Pops from the stack
                answer = etl.performETL(answer);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(DroolsInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
        return answer;
    }

    private String getFromChoiceDialog(String ev, String[] answers) {
        String answer = null;
        kds.inputSemaphore.release();
        try {
            if (kds.addInputRequest(CHOICE,ev,answers))
            {
                kds.requestSemaphore.acquire(); //Wait for input request to be fulfilled.
                answer = kds.getRequestedInput(); //Pops from the stack
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(DroolsInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
        return answer;
    }
}
