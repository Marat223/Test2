/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.mustaphin.project.constant;

/**
 *
 * @author me
 */
public interface Message {

    String SEPARATION_START_GENERATING = "Start genering new passengers";
    String SEPARATION_STOP_GENERATING = "Stop genering new passengers";
    String GENERATED = "Passengers value after adding are: ";
    String REMOVED = "Passengers value after removing are: ";
    String TOTAL_PASSENGERS = "Passengers new value in the stop-bus is: ";
    String[] ENTERING = {"Passenger try to enter in bus: ", "Passanger get in bus: "};
    String[] LEAVING = {"Passengers try to leave the bus: ", "Passanger got out bus: "};
    String ON_BUS = "Passengers value in bus: ";
    String ON_BUSSTOP = ", Passengers value in bus-stop:";
}
