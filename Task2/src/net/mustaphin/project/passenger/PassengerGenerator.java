/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.mustaphin.project.passenger;

import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author marat
 */
public class PassengerGenerator {

    private int lastId = 0;
    private static Lock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();
    private static PassengerGenerator instance = null;
    private volatile static boolean instanceCreated = false;

    public static PassengerGenerator getInstance() {
	if (!instanceCreated) {
	    lock.lock();
	    if (!instanceCreated) {
		instance = new PassengerGenerator();
		instanceCreated = true;
	    }
	    condition.signalAll();
	    lock.unlock();
	}
	return instance;
    }

    private PassengerGenerator() {
    }

    public int getNextId() {
	return ++lastId;
    }

    public void passangerGoneAndCame(List<Passenger> passengers) {
	ListIterator<Passenger> passenger = passengers.listIterator();
	while (passenger.hasNext()) {
	    int random = new Random().nextInt(passengers.size()) - 1;
	    if (random / 2 > passengers.size()) {
		passenger.remove();
	    } else if (random / 2 < passengers.size()) {
		passenger.add(new Passenger(lastId++));
	    }
	}
    }
}
