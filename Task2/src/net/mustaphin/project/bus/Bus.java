/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.mustaphin.project.bus;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import net.mustaphin.project.passenger.Passenger;
import net.mustaphin.project.station.BusStop;

/**
 *
 * @author me
 */
public class Bus implements Callable<Integer> {

    private int sum;

    private  List<Passenger> passengerIn = new ArrayList<>();
    private List<BusStop> route;
    private String routeName;

    public Bus(String routeName, List<BusStop> route) {
	this.route = route;
	this.routeName = routeName;
    }

    public void setSum(int sum) {
	this.sum = sum;
    }

    public String getRouteName() {
	return routeName;
    }

    @Override
    public Integer call() throws Exception {
	for (BusStop busStop : route) {
	    TimeUnit.MILLISECONDS.sleep(500);
	    busStop.interract(this);
	}
	return sum;
    }

    public List<Passenger> getPassanger() {
	return passengerIn;
    }

}
