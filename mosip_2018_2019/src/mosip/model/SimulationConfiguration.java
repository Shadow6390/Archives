/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mosip.model;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlElement;
import mosip.model.interfaces.IRandomDistribution;
import mosip.model.randoms.ExponentialRandomDistribution;
import mosip.model.randoms.UniformRandomDistribution;
import mosip.model.simulation.Simulation;
import mosip.ui.MainFrame.Distributions;

/**
 *
 * @author Diogo
 */
public class SimulationConfiguration
{
    public double[] spawnPercentage;
    public BaseShip.ShipType[] shipType;
    public IRandomDistribution[] distributions;
    public int[] randomSeeds;
    public int expectedShipCount;
    public Simulation simulation;
    public DummyDockingBay[] bays;
    public IRandomDistribution simControllerDist;
    public int simRandomSeed;
    public double shipSpawnFrequency;
    
    public SimulationConfiguration(Simulation simulation)
    {
        this.simulation=simulation;
    }
    
    public static class DistributionConfig{
        public Distributions dist;
        public String lambda;

        
        public DistributionConfig()
        {
            
        }
        
        public DistributionConfig(Distributions distributions, String lambda)
        {
            this.dist=distributions;
            this.lambda=lambda;
        }

        public IRandomDistribution convert()
        {
            IRandomDistribution result = null;
            switch (dist)
            {
                case Uniform:
                    result = new UniformRandomDistribution();
                    break;
                case Exponential:
                    result = new ExponentialRandomDistribution(Double.parseDouble(lambda));
                    break;
            }
            return result;
        }
    }
    
    public static class DummyDockingBay{
        public double bridgeHeight;
        public double bridgeSpeed;
        public Policies policy;
        /**
         * An unique identifier.
         * Just for debugging and presentation purposes.
         */
        private int id;
        
        private static int ID_COUNTER=1;
        
        public static enum Extractors{
            CRANE,PUMP,STAIRS
        }
        
        public static enum Policies{
            ALL,ONLY_SHORT_DURATION
        }
        
        public Map<Extractors,Integer> extractors = new HashMap<>();
        public Map<Extractors,Integer> extRS = new HashMap<>();
        public Map<Extractors,DistributionConfig> extDists = new HashMap<>();
        public Map<Extractors,Double> failureChance = new HashMap<>();
        
        public DummyDockingBay()
        {
            id=ID_COUNTER++;
        }
        
        @Override
        public String toString()
        {
            return "Docking Bay "+id;
        }
    }
}
