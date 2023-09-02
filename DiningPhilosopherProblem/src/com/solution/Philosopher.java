package com.solution;

import java.util.Random;

public class Philosopher implements Runnable{
	
	private int id;
	private boolean full;
	private ChopStick leftChopStick, rightChopStick;
	private Random random;
	private int eatingCounter;
	
	public Philosopher(int id, ChopStick leftChopStick, ChopStick rightChopStick) {
		this.id = id;
		this.leftChopStick = leftChopStick;
		this.rightChopStick = rightChopStick;
		this.random = new Random();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		//after eating 1000 time we will terminate this thread
		try {
			while(!full) {
				think();
				if(leftChopStick.pickUp(this, State.LEFT)) {
					if(rightChopStick.pickUp(this, State.RIGHT)) {
						eat();
						
						rightChopStick.putDown(this, State.RIGHT);
					}
					leftChopStick.putDown(this, State.LEFT);
				}
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	private void think() throws InterruptedException {
		System.out.println(this + " is thinking....");
		//the philosopher thinks for the time between [0,1000]
		Thread.sleep(random.nextInt(1000));
	}
	
	private void eat() throws InterruptedException {
		System.out.println(this + " is eating....");
		eatingCounter++;
		//the philosopher eating for the time between [0,1000]
		Thread.sleep(random.nextInt(1000));
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Philosopher " + id;
	}

	public boolean isFull() {
		return full;
	}

	public void setFull(boolean full) {
		this.full = full;
	}
	
	public int getEatingCounter() {
		return eatingCounter;
	}
}
