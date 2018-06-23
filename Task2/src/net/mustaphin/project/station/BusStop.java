/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.mustaphin.project.station;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import net.mustaphin.project.action.PassangerInterractor;
import net.mustaphin.project.passenger.Passenger;

/**
 *
 * @author me
 */
public class BusStop {

    private PassangerInterractor passangerInterract = new PassangerInterractor();

    private String name;
    private final String PLACE_IN = "bus-stop" + name + " picked up the passenger";
    private final String DROP = "bus-stop" + name + " droped the passenger";
    private final List<Passenger> passengerList = new ArrayList<>();
    private Semaphore semaphore;

    public BusStop(int permit, String name) {
	this.semaphore = new Semaphore(permit);
	this.name = name;
    }

    public void take(Passenger passenger) {
	passangerInterract.take(passenger, passengerList, PLACE_IN);
	// часть пассажиров удалять и генерировать
    }

    public Passenger offload() {
	return passangerInterract.offload(passengerList, DROP);
    }
}
