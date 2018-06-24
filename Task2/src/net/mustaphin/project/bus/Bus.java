/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.mustaphin.project.bus;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import net.mustaphin.project.passenger.Passenger;
import net.mustaphin.project.station.BusStop;

/**
 *
 * @author me
 */
public class Bus implements Callable<Integer> {

    private int sum = 0;
    private final List<Passenger> passengerIn = new ArrayList<>();
    private List<BusStop> route;

    public Bus(String routeName, List<BusStop> route) {
	this.route = route;
    }

    @Override
    public Integer call() throws Exception {
	for (BusStop busStop : route) {
	    busStop.interract(passengerIn);
	}
	return sum;
    }

}
