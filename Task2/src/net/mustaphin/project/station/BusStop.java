/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.mustaphin.project.station;

import java.util.ArrayList;
import java.util.List;
import net.mustaphin.project.action.PassangerInterractor;
import net.mustaphin.project.passenger.Passenger;

/**
 *
 * @author me
 */
public class BusStop {

    private PassangerInterractor passangerInterract = new PassangerInterractor();

    private final String PLACE_IN = "bus line number" + Thread.currentThread().getName() + " picked up the passenger";
    private final String DROP = "bus line number" + Thread.currentThread().getName() + " droped the passenger";
    private final List<Passenger> passengerList = new ArrayList<>();

    public void take(Passenger passenger) {
	// часть пассажиров удалять и генерировать
	passangerInterract.take(passenger, passengerList, PLACE_IN);
	// часть пассажиров удалять и генерировать
    }

    public Passenger offload() {
	return passangerInterract.offload(passengerList, DROP);
    }
}
