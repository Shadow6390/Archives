/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuckyoupingu.multiprocess;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 2DJ
 */
public class MultiController<T>
{
    
    private ExecutorService pool;
    
    public MultiController(int threadAmount)
    {
        pool = Executors.newFixedThreadPool(threadAmount);
    }
    
    public void submitTask(Runnable task)
    {
        pool.submit(task);
    }
    
    public void awaitAllTasks()
    {
        try 
        {
            pool.shutdown();
            pool.awaitTermination(Long.MAX_VALUE,TimeUnit.NANOSECONDS);
        } catch (InterruptedException ex) {
            Logger.getLogger(MultiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
