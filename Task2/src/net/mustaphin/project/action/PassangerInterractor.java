/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.mustaphin.project.action;

import java.util.List;
import java.util.Random;
import net.mustaphin.project.passenger.Passenger;

/**
 *
 * @author me
 */
public class PassangerInterractor {

    public void take(Passenger passenger, List<Passenger> passengerList, String message) {
	if (null != passenger) {
	    passengerList.add(passenger);
	    System.out.println(message);
	}
    }

    public Passenger offload(List<Passenger> passengerList, String message) {
	Passenger passenger = null;
	if (0 < passengerList.size()) {
	    passenger = passengerList.remove(new Random().nextInt(passengerList.size()) - 1);
	    System.out.println(message);
	}
	return passenger;
    }
}
