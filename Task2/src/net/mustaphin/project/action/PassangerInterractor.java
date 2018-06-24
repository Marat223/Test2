/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.mustaphin.project.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.mustaphin.project.passenger.Passenger;

/**
 *
 * @author me
 */
public class PassangerInterractor {

    public void take(List<Passenger> passengerOut, List<Passenger> passengerIn, String message) {
	if (0 != passengerOut.size()) {
	    passengerIn.addAll(passengerOut);
	    System.out.println(message + ", passangers value: " + passengerOut.size());
	}
    }

    public List<Passenger> offload(List<Passenger> passengerIn, String message) {
	List<Passenger> passengerOut = new ArrayList<>();
	if (0 < passengerIn.size()) {
	    Iterator<Passenger> passenger = passengerIn.iterator();
	    while (passenger.hasNext()) {
		int random = new Random().nextInt(passengerIn.size()) - 1;
		if (random / 2 > passengerIn.size()) {
		    Passenger passengerGone = passenger.next();
		    passenger.remove();
		    passengerOut.add(passengerGone);
		    System.out.println(message);
		}
	    }
	}
	return passengerOut;
    }

}
