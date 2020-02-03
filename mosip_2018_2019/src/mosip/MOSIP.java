/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mosip;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import mosip.ui.MainFrame;
/**
 *
 * @author jbraga
 */
public class MOSIP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        /*Simulation sim = new Simulation(new ArrayList());
        sim.setupClock(0, 200);
        SimulationConfiguration config = new SimulationConfiguration(sim);
        
        SimulationConfiguration.DummyDockingBay dBay = new SimulationConfiguration.DummyDockingBay();
        dBay.bridgeHeight=4;
        dBay.bridgeSpeed=0.5;
        dBay.extractors.put(Extractors.CRANE, 2);
        config.bays=new SimulationConfiguration.DummyDockingBay[]{dBay};
        config.expectedShipCount=10;
        config.distributions=new IRandomDistribution[]{new UniformRandomDistribution(),new UniformRandomDistribution(),
        new UniformRandomDistribution()};
        config.shipType=new BaseShip.ShipType[]{BaseShip.ShipType.CARGO};
        config.spawnPercentage=new double[]{1.0};
        config.simControllerDist = new UniformRandomDistribution();
        
        SimulationController controller = new SimulationController(sim.getRandomFactory(),config);
        controller.generateShips(config,10,170);
        sim.addComponent(controller);
        sim.run();
        System.out.println("Lol.");*/
        MainFrame.main(args);
    }
    
}
