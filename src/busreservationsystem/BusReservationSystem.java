/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package busreservationsystem;

import busreservationsystem.Customer;
import busreservationsystem.Booking;
import busreservationsystem.Bus;
import busreservationsystem.Ragister;

import java.util.Scanner;

/**
 *
 * @author gajen
 */
public class BusReservationSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Customer cust = new Customer("John", "7234827384", "john@gmail.com", "Colombo", 20);
        System.out.println(cust.getCustomerInfoArray());
        
    }
}
