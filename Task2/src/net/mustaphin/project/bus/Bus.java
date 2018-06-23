/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.mustaphin.project.bus;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import net.mustaphin.project.action.PassangerInterractor;
import net.mustaphin.project.passenger.Passenger;
import net.mustaphin.project.station.BusStop;

/**
 *
 * @author me
 */
public class Bus implements Callable<Integer> {

    private PassangerInterractor passangerInterract = new PassangerInterractor();
    private int sum = 0;
    private List<BusStop> line;
    private final String PLACE_IN = "bus line number" + Thread.currentThread().getName() + " picked up the passenger";
    private final String DROP = "bus line number" + Thread.currentThread().getName() + " droped the passenger";
    private final List<Passenger> passengerList = new ArrayList<>();

    public Bus(String lineName, List<BusStop> line) {
	Thread.currentThread().setName(lineName);
	this.line = line;
    }

    @Override
    public Integer call() throws Exception {

	BusStop busStop = new BusStop(3, "Novynki");
	offload(busStop);
	take(busStop);
	return sum;
    }

    public void take(BusStop busStop) {
	Passenger passenger = busStop.offload();
	passangerInterract.take(passenger, passengerList, PLACE_IN);
	sum++;
    }

    public void offload(BusStop busStop) {
	busStop.take(passangerInterract.offload(passengerList, DROP));
    }
}
