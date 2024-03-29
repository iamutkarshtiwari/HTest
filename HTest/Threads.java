import java.io.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.CountDownLatch;
import java.util.*;


public class Threads {
    // Global counter
    public static CyclicBarrier gate = new CyclicBarrier(4);
    
    public static int counter1 = 0;
    public static AtomicInteger counter = new AtomicInteger(0);
    
    public static void main(String[] args) throws IOException, InterruptedException  {
    
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch endSignal = new CountDownLatch(3);
        
        MyThread thread1 = new MyThread(startSignal, endSignal);
        MyThread thread2 = new MyThread(startSignal, endSignal);
        MyThread thread3 = new MyThread(startSignal, endSignal);
        Thread a = new Thread(thread1);
        Thread b = new Thread(thread2);
        Thread c = new Thread(thread3);
        a.start();
        b.start();
        c.start();
        // Initiates the trigger to start all the threads
        startSignal.countDown();
        // Wait until all threads the interrupted
        endSignal.await();
        System.out.println();
        System.out.println("Collection of thread A" + thread1.myList + "\n");
        System.out.println("Collection of thread B" + thread2.myList + "\n");
        System.out.println("Collection of thread C" + thread3.myList + "\n");
    }
}
        
        
class MyThread implements Runnable
{
    CountDownLatch startSignal, endSignal;
    ArrayList myList = new ArrayList();
    public MyThread(CountDownLatch startSignal, CountDownLatch endSignal) 
    {
        this.startSignal = startSignal;
        this.endSignal = endSignal;
    }
    public synchronized void run() 
    {
        try 
        { 
            //The thread keeps waiting until triggered            
            startSignal.await();         
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread " + Thread.currentThread().getName() + " has started...");
        System.out.println();
        while (Threads.counter.get() < 100) {
            
            int countr = Threads.counter.incrementAndGet();
            myList.add(countr);
            System.out.println("New value by " + Thread.currentThread().getName() + " is " + countr);
            
        
        }
        // Thread interrupted
        Thread.currentThread().interrupt();
        // Initiates the trigger to display all the collections
        endSignal.countDown();
        
        
    }
}
    