/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.mustaphin.project;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marat
 */
public class RunnableClassExample implements Runnable {

    StringBuilder builder;

    public RunnableClassExample(StringBuilder builder) {
	this.builder = builder;
    }

    @Override
    public void run() {
	int i = 0;
	while (i < 3) {
	    try {
		TimeUnit.SECONDS.sleep(1);
	    } catch (InterruptedException ex) {
		Logger.getLogger(RunnableClassExample.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    System.out.println("Runnable " + i++);
	    builder.append("Runnable " + i++);
	}

    }

}
