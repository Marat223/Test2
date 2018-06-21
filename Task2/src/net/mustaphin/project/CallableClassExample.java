/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.mustaphin.project;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author marat
 */
public class CallableClassExample implements Callable<Integer> {

    StringBuilder builder;

    public CallableClassExample(StringBuilder builder) {
	this.builder = builder;
    }

    @Override
    public Integer call() throws Exception {
	int i = 0;
	while (i < 3) {
	    TimeUnit.SECONDS.sleep(1);
	    System.out.println("Callable " + i++);
	    builder.append("Runnable " + i++);
	}
	return i;
    }

}
