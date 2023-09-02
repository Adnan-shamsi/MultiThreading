package com.solution;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ChopStick {
	private int id;
	private Lock lock;
	
	public ChopStick(int id) {
		System.out.println("Placing chopstick " + id);
		this.id = id;
		this.lock = new ReentrantLock();
	}
	
	public boolean pickUp(Philosopher philosopher, State state) throws InterruptedException {
		//this is imp as lock could have led to dead lock so used tryLock instead
		if(lock.tryLock(10, TimeUnit.MILLISECONDS)) {
			System.out.println(philosopher + " pickedup " + state.toString() + " " + this);
			return true;
		}
		return false;
	}
	
	public void putDown(Philosopher philosopher, State state) {
		lock.unlock();
		System.out.println(philosopher + " puts down " + state.toString() + " " + this);
	}
	
	@Override
	public String toString() {
		return "Chopsticks "+ id;
	}
}
