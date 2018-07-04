/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.mustaphin.project.passenger;

import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import net.mustaphin.project.constant.Message;

/**
 *
 * @author marat
 */
public class PassengerGenerator {

    private static PassengerGenerator instance = null;
    private volatile static boolean instanceCreated = false;

    public static PassengerGenerator getInstance() {
	Lock lock = new ReentrantLock();
	Condition condition = lock.newCondition();
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

    public void passangerComeAndGone(List<Passenger> passengerAlreadyPlaced) {
	if (passengerAlreadyPlaced.isEmpty()) {
	    System.out.println(Message.SEPARATION_START_GENERATING);
	    int random = new Random().nextInt(7);//Для примера
	    while (0 != random) {
		passengerAlreadyPlaced.add(new Passenger());
		random--;
	    }
	    System.out.println(Message.GENERATED + passengerAlreadyPlaced.size());
	} else {
	    System.out.println(Message.SEPARATION_STOP_GENERATING);
	    int last = passengerAlreadyPlaced.size() - 1;
	    int random = new Random().nextInt(passengerAlreadyPlaced.size()) + 1;
	    while (last >= random / 2) {//Для примера
		passengerAlreadyPlaced.remove(last--);
	    }
	    System.out.println(Message.REMOVED + passengerAlreadyPlaced.size());
	}
	System.out.println(Message.TOTAL_PASSENGERS + passengerAlreadyPlaced.size());
    }
}
