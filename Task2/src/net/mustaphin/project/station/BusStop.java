/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.mustaphin.project.station;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import net.mustaphin.project.bus.Bus;
import net.mustaphin.project.passenger.Passenger;

/**
 *
 * @author me
 */
public class BusStop {

    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private List<Passenger> passengerBusStop;
    private Semaphore semaphore;
    private String name;

    public BusStop(int permit, String name) {
	this.semaphore = new Semaphore(permit);
	passengerBusStop = Arrays.asList(new Passenger(), new Passenger(), new Passenger());
	this.name = name;
    }

    public void interract(Bus bus) {
	List<Passenger> passengerBus = bus.getPassanger();
	try {
	    semaphore.acquire();
	    lock.lock();
	    System.out.println("Bus " + bus.getRouteName() + " came on bus-stop " + name);
	    System.out.println("Passengers value in bus: " + passengerBus.size() + ", Passengers value in bus-stop:" + passengerBusStop.size());
	    passengerBusStop.addAll(goOutBus(passengerBus));
	    passengerBus.addAll(goOnBus());
	} catch (InterruptedException ex) {
	} finally {
	    condition.signalAll();
	    lock.unlock();
	    semaphore.release();
	}
	System.out.println("Bus " + bus.getRouteName() + " leave bus-stop " + name);
//	PassengerGenerator.getInstance().passangerComeAndGone(passengerBusStop);
    }

    private List<Passenger> goOnBus() throws InterruptedException {
	List<Passenger> goInBusPassenger = new ArrayList<>();
	if (0 < passengerBusStop.size()) {
	    Iterator<Passenger> passengerIterator = passengerBusStop.iterator();
	    int random = new Random().nextInt(passengerBusStop.size()) - 1;
//	    System.out.println("Random to standing on stop-bus: " + random);
	    while (passengerIterator.hasNext()) {
		System.out.println("Passengers try to board the bus");
//		if (random / 2 > passengerIn.size()) {
		goInBusPassenger.add(passengerIterator.next());
		passengerIterator.remove();//TODO не удаляются
		System.out.println("Passanger got in bus");
		TimeUnit.MILLISECONDS.sleep(200);
//		}
	    }
	}
	return goInBusPassenger;
    }

    private List<Passenger> goOutBus(List<Passenger> passengerBus) throws InterruptedException {
	List<Passenger> goOutBusPassenger = new ArrayList<>();
	if (0 < passengerBus.size()) {
	    Iterator<Passenger> passenger = passengerBus.iterator();
	    int random = new Random().nextInt(passengerBus.size()) - 1;
	    System.out.println("Random to exit from bus: " + random);
	    while (passenger.hasNext()) {
		System.out.println("Passengers try to leave the bus");
		if (random / 2 > passengerBus.size()) {
		    goOutBusPassenger.add(passenger.next());
		    passenger.remove();
		    System.out.println("Passanger got out bus");
		    TimeUnit.MILLISECONDS.sleep(200);
		}
	    }
	}
	return goOutBusPassenger;
    }
}
