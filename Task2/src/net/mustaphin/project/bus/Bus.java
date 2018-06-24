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
    private final List<Passenger> passengerList = new ArrayList<>();
    private List<BusStop> route = new ArrayList<>();
    private final String PLACE_IN;
    private final String DROP;

    public Bus(String routeName, List<BusStop> route) {
	this.route.addAll(route);
	PLACE_IN = "bus" + routeName + " route number" + route.getClass().getName() + " picked up the passenger";
	DROP = "bus" + routeName + " route number" + route.getClass().getName() + " droped the passenger";
    }

    @Override
    public Integer call() throws Exception {
	for (BusStop busStop : route) {
	    offload(busStop);
	    take(busStop);
	}
	return sum;
    }

    public void take(BusStop busStop) {
	List<Passenger> passenger = busStop.offload();
	passangerInterract.take(passenger, passengerList, PLACE_IN);
	sum++;
    }

    public void offload(BusStop busStop) {
	busStop.take(passangerInterract.offload(passengerList, DROP));
    }
}
