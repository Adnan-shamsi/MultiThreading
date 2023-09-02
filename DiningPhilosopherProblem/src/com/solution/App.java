package com.solution;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class App {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService executorService = null;
		Philosopher[] philosophers = null;
		ChopStick[] chopSticks = null;
		
		try {
			executorService = Executors.newFixedThreadPool(Constant.MAX_NUMBER_OF_PHILOSOPHERS);
			philosophers = new Philosopher[Constant.MAX_NUMBER_OF_PHILOSOPHERS];
			chopSticks = new ChopStick[Constant.MAX_NUMBER_OF_CHOPSTICKS];
			
			for(int i = 0; i < Constant.MAX_NUMBER_OF_CHOPSTICKS; i++) {
				chopSticks[i] = new ChopStick(i);
			}
			
			for(int i = 0; i < Constant.MAX_NUMBER_OF_PHILOSOPHERS; i++) {
				philosophers[i] = new Philosopher(i, chopSticks[i], chopSticks[(i+1) % Constant.MAX_NUMBER_OF_PHILOSOPHERS]);
				executorService.execute(philosophers[i]);
			}
			
			Thread.sleep(Constant.SIMULATION_TIME); // simulation
			
			//now try to stop
			for (Philosopher philosopher : philosophers) {  
				philosopher.setFull(true);
			}
			
		} 
		finally {
			executorService.shutdown(); // stop the new thread request but it will not stop the the already working thread
			while(executorService.isTerminated()) { //wait until every one finishes
				Thread.sleep(1000);
			}
			
			//get how much eaten
			for (Philosopher philosopher : philosophers) {
				System.out.println(philosopher + " eat #" + philosopher.getEatingCounter()); 
			}
		}
	}
}
