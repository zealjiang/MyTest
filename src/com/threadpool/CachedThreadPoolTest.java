package com.threadpool;

import java.util.concurrent.ExecutorService; 
import java.util.concurrent.Executors; 
public class CachedThreadPoolTest { 
    public static void main(String[] args) { 
        ExecutorService exec = Executors.newCachedThreadPool(); 
        for(int i = 0; i < 10; i++) { 
            exec.execute(new LiftOff()); 
        } 
        exec.shutdown();     
    } 
} 
