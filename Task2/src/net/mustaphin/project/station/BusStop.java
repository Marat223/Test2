/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.mustaphin.project.station;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import net.mustaphin.project.bus.Bus;
import net.mustaphin.project.passenger.Passenger;
import net.mustaphin.project.passenger.PassengerGenerator;

/**
 *
 * @author me
 */
public class BusStop {

    private List<Passenger> passengerIn;
    private Semaphore semaphore;
    private String name;

    public BusStop(int permit, String name) {
	this.semaphore = new Semaphore(permit);
	passengerIn = Arrays.asList(new Passenger(), new Passenger(), new Passenger());
	this.name = name;
    }

    public void interract(Bus bus) {
	List<Passenger> passengerOut = bus.getPassanger();
	try {
	    semaphore.acquire();
	    System.out.println("Bus " + bus.getRouteName() + " came on bus-stop " + name);
	    action(passengerOut);
	} catch (InterruptedException ex) {
	}
	semaphore.release();
	System.out.println("Bus " + bus.getRouteName() + " leave bus-stop " + name);
	PassengerGenerator.getInstance().passangerComeAndGone(passengerIn);
    }

    public void action(final List<Passenger> passengerOut) throws InterruptedException {//сделать декомпозицию
	System.out.println("Passengers value in bus: " + passengerOut.size() + ", Passengers value in bus-stop:" + passengerIn.size());
	List<Passenger> goOutPassenger = new ArrayList<>();
	if (0 < passengerOut.size()) {
	    ListIterator<Passenger> passenger = passengerOut.listIterator();
	    int random = new Random().nextInt(passengerOut.size()) - 1;
	    System.out.println("Random to exit from bus: " + random);
	    while (passenger.hasNext()) {
		System.out.println("Passengers try to leave the bus");
		if (random / 2 > passengerOut.size()) {
		    goOutPassenger.add(passenger.next());
		    passenger.remove();
		    System.out.println("Passanger got out bus");
		    TimeUnit.MILLISECONDS.sleep(200);
		}
	    }
	}
	List<Passenger> goInPassenger = new ArrayList<>();
	if (0 < passengerIn.size()) {
	    ListIterator<Passenger> passenger = passengerIn.listIterator();
	    int random = new Random().nextInt(passengerIn.size()) - 1;
	    System.out.println("Random to standing on stop-bus: " + random);
	    while (passenger.hasNext()) {
		System.out.println("Passengers try to board the bus");
		if (random / 2 > passengerIn.size()) {
		    goInPassenger.add(passenger.next());
		    passenger.remove();
		    System.out.println("Passanger got in bus");
		    TimeUnit.MILLISECONDS.sleep(200);
		}
	    }
	}
	passengerIn.addAll(goOutPassenger);
	passengerOut.addAll(goInPassenger);
    }

}
