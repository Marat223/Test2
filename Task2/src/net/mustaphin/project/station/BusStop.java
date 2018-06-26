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
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.mustaphin.project.bus.Bus;
import net.mustaphin.project.passenger.Passenger;
import net.mustaphin.project.passenger.PassengerGenerator;

/**
 *
 * @author me
 */
public class BusStop {

    private ReentrantLock lock = new ReentrantLock();
    private static List<Passenger> passengerBusStop = new ArrayList<>(Arrays.asList(new Passenger(), new Passenger(), new Passenger()));
    private Semaphore semaphore;
    private String name;

    public BusStop(int permit, String name) {
	this.semaphore = new Semaphore(permit);
	this.name = name;
    }

    public void interract(Bus bus) {
	List<Passenger> passengerBus = bus.getPassanger();
	String messages[][] = {
	    {"Passenger try to enter in bus: " + bus.getRouteName(),
		"Passanger get in bus: " + bus.getRouteName()},
	    {"Passengers try to leave the bus: " + bus.getRouteName(),
		"Passanger got out bus: " + bus.getRouteName()}};
	try {
	    semaphore.acquire();
	    System.out.println("\nBus " + bus.getRouteName() + " came on bus-stop " + name);
	    System.out.println("Passengers value in bus: " + passengerBus.size() + ", Passengers value in bus-stop:" + passengerBusStop.size());
	    passengerBus.addAll(passangerMoving(passengerBusStop, messages[0]));
	    passengerBusStop.addAll(passangerMoving(passengerBus, messages[1]));
	    System.out.println("Passengers value in bus: " + passengerBus.size() + ", Passengers value in bus-stop:" + passengerBusStop.size());
	} catch (InterruptedException ex) {
	} finally {
	    semaphore.release();
	}
	System.out.println("Bus " + bus.getRouteName() + " leave bus-stop " + name + "\n");
	PassengerGenerator.getInstance().passangerComeAndGone(passengerBusStop);
    }

    private List<Passenger> passangerMoving(List<Passenger> passengersList, String message[]) {
	List<Passenger> passangerMoving = new ArrayList<>();
	if (!passengersList.isEmpty()) {
	    Iterator<Passenger> passenger = passengersList.iterator();
	    int random = new Random().nextInt(passengersList.size()) + 1;
	    System.out.println("Random: " + random);
	    while (passenger.hasNext()) {
		Passenger passengerReplace = passenger.next();
		System.out.println(message[0]);
		if (random >= passengersList.size()) {
		    try {
			lock.lock();
			passangerMoving.add(passengerReplace);
			TimeUnit.MILLISECONDS.sleep(200);
			passenger.remove();
			System.out.println(message[1]);
		    } catch (InterruptedException ex) {
			Logger.getLogger(BusStop.class.getName()).log(Level.SEVERE, null, ex);
		    } finally {
			lock.unlock();
		    }
		}
	    }
	}
	return passangerMoving;
    }
}
