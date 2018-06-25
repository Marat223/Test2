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

    public void passangerComeAndGone(List<Passenger> passengerAlreadyPlaced) {//TODO
	if (passengerAlreadyPlaced.isEmpty()) {
	    int random = new Random().nextInt(7);
	    while (0 != random) {
		passengerAlreadyPlaced.add(new Passenger());
		random--;
	    }
	} else {
	    ListIterator<Passenger> passanger = passengerAlreadyPlaced.listIterator();
	    int random = new Random().nextInt(passengerAlreadyPlaced.size());
	    if (passengerAlreadyPlaced.size() > random / 2) {
		while (passanger.hasNext()) {
		    passanger.remove();
		}
	    }
	}
    }
}
