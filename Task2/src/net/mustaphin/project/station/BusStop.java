/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.mustaphin.project.station;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.mustaphin.project.action.PassangerInterractor;
import net.mustaphin.project.passenger.Passenger;
import net.mustaphin.project.passenger.PassengerGenerator;

/**
 *
 * @author me
 */
public class BusStop {

    private PassangerInterractor passangerInterract = new PassangerInterractor();

    private final String PLACE_IN;
    private final String DROP;
    private List<Passenger> passengerIn = new ArrayList<>();
    private Semaphore semaphore;

    public BusStop(int permit, String name) {
	this.semaphore = new Semaphore(permit);
	PLACE_IN = "bus-stop" + name + " picked up the passenger";
	DROP = "bus-stop" + name + " droped the passenger";
    }

    public void take(List<Passenger> passengerOut) {
	try {
	    semaphore.acquire();
	    passangerInterract.take(passengerOut, passengerIn, PLACE_IN);
	    TimeUnit.MILLISECONDS.sleep(200);
	    PassengerGenerator.getInstance().passangerGoneAndCame(passengerIn);
	} catch (InterruptedException ex) {
	    Logger.getLogger(BusStop.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    public List<Passenger> offload() {
	try {
	    TimeUnit.MILLISECONDS.sleep(500);
	} catch (InterruptedException ex) {
	    Logger.getLogger(BusStop.class.getName()).log(Level.SEVERE, null, ex);
	}
	semaphore.release();
	return passangerInterract.offload(passengerIn, DROP);
    }
}
