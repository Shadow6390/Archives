/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mosip.model.simulation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import mosip.model.GlobalClock;
import mosip.model.RandomSeedFactory;
import mosip.model.events.states.CompletedEventState;
import mosip.model.events.states.TerminatedEventState;
import mosip.model.interfaces.IReport;
import mosip.model.interfaces.IReportComponent;
import mosip.model.interfaces.ISimulationEvent;
import mosip.model.simulation.states.SimulationComponentTerminatedState;

/**
 *
 * @author jbraga
 */
public class Simulation {
    
    private List<SimulationComponent> components;
    private GlobalClock clock;
    private RandomSeedFactory randomFactory;
    private List<ISimulationEvent> events;
    private List<ISimulationEvent> completedEvents;
    
    public Simulation()
    {
        this(new ArrayList());
    }
    
    public Simulation(List<SimulationComponent> components)
    {
        this.components=components;
        this.clock=new GlobalClock();
        randomFactory = new RandomSeedFactory();
        events = new LinkedList<>();
        completedEvents = new ArrayList<>();
    }
    
    public void setupClock(double start, double end)
    {
        getClock().setStart(start);
        getClock().setEnd(end);
        getClock().resetClock();
    }
    
    public void clearPastSimulation()
    {
        completedEvents.clear();
        events.clear();
        components.clear();
    }
    
    @Deprecated
    public void runActivityBased()
    {
        /*while (!clock.finished())
        {
            System.out.println(getClock());
            for (int i=0;i<components.size();i++)
            {
                SimulationComponent elem=components.get(i);
                elem.doStep();
                elem.onTick();
            }
            getClock().tick();
        }
        //When it's finished, we terminate all processes
        terminatePendingProcesses();*/
    }
    
    public void runEventBased(IReport report)
    {
        
        while (!clock.finished() && !events.isEmpty())
        {
            System.out.println(getClock());
            events.sort((ISimulationEvent o1, ISimulationEvent o2) -> Double.compare(o1.getEventDetails().getTimeStamp(),o2.getEventDetails().getTimeStamp()));
            ISimulationEvent nextEvent = events.remove(0);
            //clock.incrementBy(Math.max(nextEvent.getEventDetails().getTimeStamp()-clock.getTime(),0));
            /*
            //CANNOT BE BASED ON DIFF DUE TO MATH PRECISION. INSTEAD, MUST JUMP TO THE TIMESTAMP
            double diff = nextEvent.getEventDetails().getTimeStamp()-clock.getTime();
            if (diff>0)
            {
                clock.incrementBy(diff);
                if (clock.getTime()<nextEvent.getEventDetails().getTimeStamp())
                {
                    throw new RuntimeException("Damn you maths!!!! ("+diff+","+clock.getTime()+","+nextEvent.getEventDetails().getTimeStamp()+")");
                }
            }*/
            clock.forwardTo(nextEvent.getEventDetails().getTimeStamp());
            nextEvent.onEventFinished();
            if (nextEvent.getState() instanceof CompletedEventState)
            {
                completedEvents.add(nextEvent);
            }
        }
        //When it's finished, we terminate all processes
        terminatePendingEvents();
        if (report!=null)
        {
            fillDataReport(report);
        }
        else
        {
            System.out.println("Completed Events:\n\n"+completedEvents);
            System.out.println("Remaining Events:\n\n"+events);
        }
    }
    
    private void fillDataReport(IReport data)
    {
        for (ISimulationEvent elem:completedEvents)
        {
            if (elem instanceof IReportComponent)
            {
                IReportComponent c = (IReportComponent)elem;
                c.reportResults(data);
            }
        }
        data.getReportData().doFinalComputations();
    }
    
    /**
     * Terminates all pending processes.
     */
    protected void terminatePendingProcesses()
    {
        for (SimulationComponent elem:components)
        {
            elem.changeState(new SimulationComponentTerminatedState());
        }
    }
    
    /**
     * Terminates all pending processes.
     */
    protected void terminatePendingEvents()
    {
        for (ISimulationEvent elem:events)
        {
            elem.changeEventState(new TerminatedEventState());
        }
    }
    
    public boolean addEvent(ISimulationEvent event)
    {
        boolean result = false;
        if (event!=null)
        {
            if (event.getEventDetails().getTimeStamp()>=clock.getTime())
            {
                result = events.add(event);
            }
            else
            {
                throw new IllegalArgumentException("Time travel not allowed! (event is due to happen at "+
                        event.getEventDetails().getTimeStamp()+" but simulation is at "+clock.getTime()+")");
            }
        }
        return result;
    }
    
    /**
     * Adds a new component to this simulation.
     * @param component
     * @return 
     */
    public boolean addComponent(SimulationComponent component)
    {
        if (component==null)
        {
            throw new IllegalArgumentException("The component cannot be null!");
        }
        component.setParentSimulation(this);
        return components.add(component);
    }

    /**
     * @return the clock
     */
    public GlobalClock getClock()
    {
        return clock;
    }

    /**
     * @return the randomFactory
     */
    public RandomSeedFactory getRandomFactory()
    {
        return randomFactory;
    }
}
