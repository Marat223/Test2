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
import net.mustaphin.project.passenger.Passenger;
import net.mustaphin.project.passenger.PassengerGenerator;

/**
 *
 * @author me
 */
public class BusStop {

    private final String PLACE_IN;
    private final String DROP;
    private List<Passenger> passengerIn;
    private Semaphore semaphore;

    public BusStop(int permit, String name) {
	this.semaphore = new Semaphore(permit);
	PLACE_IN = "bus-stop" + name + " picked up the passenger";
	DROP = "bus-stop" + name + " droped the passenger";
	passengerIn = Arrays.asList(new Passenger(), new Passenger(), new Passenger());
    }

    public void interract(List<Passenger> passengerOut) {
	try {
	    semaphore.acquire();
	    action(passengerIn);
	    TimeUnit.MILLISECONDS.sleep(500);
	} catch (InterruptedException ex) {
	}
	semaphore.release();
	PassengerGenerator.getInstance().passangerCameAndGone(passengerIn);
    }

    public void action(final List<Passenger> passengerOut) {//сделать декомпозицию
	List<Passenger> goOutPassenger = new ArrayList<>();
	if (0 < passengerOut.size()) {
	    ListIterator<Passenger> passenger = passengerOut.listIterator();
	    int random = new Random().nextInt(passengerOut.size()) - 1;
	    while (passenger.hasNext()) {
		if (random / 2 > passengerOut.size()) {
		    goOutPassenger.add(passenger.next());
		    passenger.remove();
		}
	    }
	}
	List<Passenger> goInPassenger = new ArrayList<>();
	if (0 < passengerIn.size()) {
	    ListIterator<Passenger> passenger = passengerIn.listIterator();
	    int random = new Random().nextInt(passengerIn.size()) - 1;
	    while (passenger.hasNext()) {
		if (random / 2 > passengerIn.size()) {
		    goInPassenger.add(passenger.next());
		    passenger.remove();
		}
	    }
	}
	passengerIn.addAll(goOutPassenger);
	passengerOut.addAll(goInPassenger);
    }

}
