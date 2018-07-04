/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.mustaphin.project.station;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import net.mustaphin.project.bus.Bus;

/**
 *
 * @author me
 */
public class DuispatcherStation {

    public void startShift(List<Bus> busPark) throws InterruptedException, ExecutionException {
	ExecutorService executorService = Executors.newSingleThreadExecutor();
	List<Future<Integer>> totalSum = new ArrayList<>();
	int total = 0;
	for (Bus bus : busPark) {
	    Future<Integer> submit = executorService.submit(bus);
	    total += submit.get();
	}
	executorService.shutdown();
	System.out.println("TOTAL: " + total);
    }
}
