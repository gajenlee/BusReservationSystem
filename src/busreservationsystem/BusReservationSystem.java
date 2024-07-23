/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package busreservationsystem;

import busreservationsystem.Booking;
import busreservationsystem.Bus;
import busreservationsystem.Customer;
import busreservationsystem.Ragister;
import busreservationsystem.ReservationInterface;

import busreservationsystem.compands.LinkedList;
import busreservationsystem.compands.AVLTree;

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
        Bus bus = new Bus("1234", 20, "Colombo", "Banarawela", "18/07/2024 10:00 AM", 2000);
        Bus bus1 = new Bus("2303", 40, "Moratuwa", "Colombo", "04/07/2024 09:00 AM", 80);
        Bus bus2 = new Bus("3844", 20, "asdjaskljd", "asdaskasd", "08/07/2024 08:00 AM", 50);
        Bus bus3 = new Bus("2724", 60, "asdlas", "asjdajsdlk", "13/07/2024 02:00 PM", 30);
        Bus bus4 = new Bus("5633", 20, "sdjasd", "asdasdk", "12/07/2024 10:00 AM", 200);
        LinkedList<Bus> buses = new LinkedList<>();
        buses.push(bus);
        buses.push(bus1);
        buses.push(bus2);
        buses.push(bus3);
        buses.push(bus4);
        Ragister reg = new Ragister(buses); 
        
        Customer cust = new Customer("John", "4234234234234", "john@gmail.com", "Moratuwa", 20);
        Bus targetedBus = reg.getBusStoredArray().binarySearchByString("3844");
        targetedBus.displayBusInfo();
        Booking book = new Booking(targetedBus, cust);
        book.bookASeat(7);
        targetedBus.displayBusInfo(); 
        
        System.out.println(ReservationInterface.getSearchBusNumberPlate());
    }
}
