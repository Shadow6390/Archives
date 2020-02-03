/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mosip.model;

/**
 *
 * @author jbraga
 */
public class GlobalClock {
    public static final double TICK_TIME=1;//1 MINUTE
    private TimeClock timeClock;
    
    public GlobalClock()
    {
        timeClock=new TimeClock();
    }
    
    public void tick()
    {
        timeClock.tick(TICK_TIME);
    }
    
    public void incrementBy(double timeInterval)
    {
        timeClock.time+=timeInterval;
    }

    public void setStart(double start) {
        timeClock.setStart(start);
    }
    
    public void resetClock()
    {
        timeClock.reset();
    }
    
    public void setEnd(double end)
    {
        timeClock.setEnd(end);
    }
    
    public double getEnd()
    {
        return timeClock.getEnd();
    }
    
    public double getStart()
    {
        return timeClock.getStart();
    }

    public boolean finished() {
        return timeClock.getTime()>=timeClock.getEnd();
    }

    public Double getTime()
    {
        return timeClock.time;
    }
    
    @Override
    public String toString()
    {
        return "GlobalClock["+timeClock.start+","+timeClock.end+","+timeClock.time+"]";
    }

    public void forwardTo(double timeStamp)
    {
        timeClock.time=timeStamp;
    }
    
    /**
     * A clock to be used by simulation components. 
     * Represents a start and end.
     */
    public static class TimeClock{
        private double start;
        private double end;
        private double time;
        
        public TimeClock()
        {
        }
        
        public TimeClock(double start,double end)
        {
            this.start=start;
            this.end=end;
            this.time=start;
        }

        public void tick(double elapsedTime)
        {
            time+=elapsedTime;
        }
        
        public void reset()
        {
            time=this.start;
        }
        
        public double getTime()
        {
            return time;
        }
        
        /**
         * @return the start
         */
        public double getStart()
        {
            return start;
        }

        /**
         * @param start the start to set
         */
        public void setStart(double start)
        {
            this.start = start;
        }

        /**
         * @return the end
         */
        public double getEnd()
        {
            return end;
        }

        /**
         * @param end the end to set
         */
        public void setEnd(double end)
        {
            this.end = end;
        }
        
        public boolean hasFinished()
        {
            return time>=end;
        }
    }
}
