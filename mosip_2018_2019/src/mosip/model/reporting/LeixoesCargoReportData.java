/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mosip.model.reporting;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Diogo
 */
@XmlRootElement
public class LeixoesCargoReportData
{
    public ReportBundle globalResults;
    public Map<String,Integer> numberShipsProcessed;
    public Map<String,Integer> numberShipsInDock;
    public Map<String,Integer> numberShipsPool;
    public Map<String,ReportBundle> bayData;
    
    private boolean doFinalMaths;
    
    public LeixoesCargoReportData()
    {
        numberShipsProcessed = new HashMap<>();
        numberShipsInDock = new HashMap<>();
        numberShipsPool = new HashMap<>();
        bayData = new HashMap<>();
        globalResults = new ReportBundle();
        doFinalMaths = false;
    }
    
    /**
     * Applies to a bay or a specific context
     */
    @XmlRootElement
    public static class ReportBundle {
        public Map<String,Double> averageDocking;
        public Map<String,Double> averageUndocking;
        public Map<String,Double> averageLoading;
        public Map<String,Double> averageUnloading;
        public Map<String,Integer> numberOfDocks;
        public Map<String,Integer> numberOfUndocks;
        public Map<String,Integer> numberOfLoadings;
        public Map<String,Integer> numberOfUnloadings;
        public int numberOfCranes;
        public int numberOfStairs;
        public int numberOfPumps;
        public double averageWaitingTime;
        public double averageDelayTime;
        
        public ReportBundle()
        {
            numberOfUndocks = new HashMap<>();
            numberOfDocks = new HashMap<>();
            numberOfLoadings = new HashMap<>();
            numberOfUnloadings = new HashMap<>();
            averageLoading = new HashMap<>();
            averageUnloading = new HashMap<>();
            averageDocking = new HashMap<>();
            averageUndocking = new HashMap<>();
        }

        public double getAverageFor(Map<String, Double> dataStructure)
        {
            double result = 0;
            for (Entry<String,Double> elem:dataStructure.entrySet())
            {
                result+=elem.getValue();
            }
            result/=Math.max((double)dataStructure.size(),1);
            return result;
        }
    }
    
    public void doFinalComputations()
    {
        if (!doFinalMaths)
        {
            for (Entry<String,ReportBundle> elem:bayData.entrySet())
            {
                ReportBundle b = elem.getValue();
                for (Entry<String,Double> e:b.averageDocking.entrySet())
                {
                    globalResults.averageDocking.put(e.getKey(),
                            globalResults.averageDocking.getOrDefault(e.getKey(), 0.0)+e.getValue());
                    b.averageDocking.put(e.getKey(), 
                            b.averageDocking.get(e.getKey())/b.numberOfDocks.get(e.getKey()).doubleValue());
                }
                for (Entry<String,Double> e:b.averageUndocking.entrySet())
                {
                    globalResults.averageUndocking.put(e.getKey(),
                            globalResults.averageUndocking.getOrDefault(e.getKey(), 0.0)+e.getValue());
                    b.averageUndocking.put(e.getKey(), 
                            b.averageUndocking.get(e.getKey())/b.numberOfUndocks.get(e.getKey()).doubleValue());
                }
                for (Entry<String,Double> e:b.averageLoading.entrySet())
                {
                    globalResults.averageLoading.put(e.getKey(),
                            globalResults.averageLoading.getOrDefault(e.getKey(), 0.0)+e.getValue());
                    b.averageLoading.put(e.getKey(), 
                            b.averageLoading.get(e.getKey())/b.numberOfLoadings.get(e.getKey()).doubleValue());
                }
                for (Entry<String,Double> e:b.averageUnloading.entrySet())
                {
                    globalResults.averageUnloading.put(e.getKey(),
                            globalResults.averageUnloading.getOrDefault(e.getKey(), 0.0)+e.getValue());
                    b.averageUnloading.put(e.getKey(), 
                            b.averageUnloading.get(e.getKey())/b.numberOfUnloadings.get(e.getKey()).doubleValue());
                }
                for (Entry<String,Integer> e:b.numberOfDocks.entrySet())
                {
                    globalResults.numberOfDocks.put(e.getKey(),
                            globalResults.numberOfDocks.getOrDefault(e.getKey(), 0)+e.getValue());
                }
                for (Entry<String,Integer> e:b.numberOfUndocks.entrySet())
                {
                    globalResults.numberOfUndocks.put(e.getKey(),
                            globalResults.numberOfUndocks.getOrDefault(e.getKey(), 0)+e.getValue());
                }
                for (Entry<String,Integer> e:b.numberOfLoadings.entrySet())
                {
                    globalResults.numberOfLoadings.put(e.getKey(),
                            globalResults.numberOfLoadings.getOrDefault(e.getKey(), 0)+e.getValue());
                }
                for (Entry<String,Integer> e:b.numberOfUnloadings.entrySet())
                {
                    globalResults.numberOfUnloadings.put(e.getKey(),
                            globalResults.numberOfUnloadings.getOrDefault(e.getKey(), 0)+e.getValue());
                }
            }
            ReportBundle b = globalResults;
            for (Entry<String,Double> e:b.averageDocking.entrySet())
            {
                b.averageDocking.put(e.getKey(), 
                        b.averageDocking.get(e.getKey())/b.numberOfDocks.get(e.getKey()).doubleValue());
            }
            for (Entry<String,Double> e:b.averageUndocking.entrySet())
            {
                b.averageUndocking.put(e.getKey(), 
                        b.averageUndocking.get(e.getKey())/b.numberOfUndocks.get(e.getKey()).doubleValue());
            }
            for (Entry<String,Double> e:b.averageLoading.entrySet())
            {
                b.averageLoading.put(e.getKey(), 
                        b.averageLoading.get(e.getKey())/b.numberOfLoadings.get(e.getKey()).doubleValue());
            }
            for (Entry<String,Double> e:b.averageUnloading.entrySet())
            {
                b.averageUnloading.put(e.getKey(), 
                        b.averageUnloading.get(e.getKey())/b.numberOfUnloadings.get(e.getKey()).doubleValue());
            }
            
            doFinalMaths=true;
        }
    }
}
