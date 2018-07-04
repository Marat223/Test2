/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.mustaphin.project.station;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.mustaphin.project.bus.Bus;
import net.mustaphin.project.constant.Message;
import net.mustaphin.project.passenger.Passenger;
import net.mustaphin.project.passenger.PassengerGenerator;

/**
 *
 * @author me
 */
public class BusStop {

    private List<Passenger> passengerBusStop = new ArrayList<>();
    private Semaphore semaphore;
    private String name;

    public BusStop(int permit, String name, List<Passenger> passengerBusStop) {
	this.semaphore = new Semaphore(permit);
	this.name = name;
	this.passengerBusStop.addAll(passengerBusStop);
    }

    public void interract(Bus bus) {//TODO получние оплаты
	List<Passenger> passengerBus = bus.getPassanger();
	String messages[][] = {{Message.ENTERING[0] + bus.getRouteName(), Message.ENTERING[1] + bus.getRouteName()},
	{Message.LEAVING[0] + bus.getRouteName(), Message.LEAVING[1] + bus.getRouteName()}};
	try {
	    semaphore.acquire();
	    System.out.println(bus.getRouteName() + " came on " + name);
	    System.out.println(Message.ON_BUS + passengerBus.size() + Message.ON_BUSSTOP + passengerBusStop.size());
	    passengerBus.addAll(passangerMoving(passengerBusStop, messages[0]));
	    passengerBusStop.addAll(passangerMoving(passengerBus, messages[1]));
	    System.out.println(Message.ON_BUS + passengerBus.size() + Message.ON_BUSSTOP + passengerBusStop.size());
	} catch (InterruptedException ex) {
	    ex.printStackTrace();
	} finally {
	    semaphore.release();
	}
	System.out.println(bus.getRouteName() + " leave bus-stop " + name + "\n");
	PassengerGenerator.getInstance().passangerComeAndGone(passengerBusStop);
    }

    private List<Passenger> passangerMoving(List<Passenger> passengersList, String message[]) {
	ReentrantLock lock = new ReentrantLock();
	List<Passenger> passangerMoving = new ArrayList<>();
	if (!passengersList.isEmpty()) {
	    Iterator<Passenger> passenger = passengersList.iterator();
	    int random = new Random().nextInt(passengersList.size()) + 1;
	    System.out.println("Random: " + random);
	    while (passenger.hasNext()) {
		Passenger passengerReplace = passenger.next();
		System.out.println(message[0]);
		if (random >= passengersList.size()) {
		    try {
			lock.lock();
			passangerMoving.add(passengerReplace);
			TimeUnit.MILLISECONDS.sleep(200);
			passenger.remove();
			System.out.println(message[1]);
		    } catch (InterruptedException ex) {
			Logger.getLogger(BusStop.class.getName()).log(Level.SEVERE, null, ex);
		    } finally {
			lock.unlock();
		    }
		}
	    }
	}
	return passangerMoving;
    }
}
