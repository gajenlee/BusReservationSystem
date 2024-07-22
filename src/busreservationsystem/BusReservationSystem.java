/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package busreservationsystem;

import busreservationsystem.compands.LinkedList;
import busreservationsystem.compands.AVLTree;
import busreservationsystem.compands.Queue;

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
        bus.bookSeat(20);
        bus.bookSeat(19);
        bus.bookSeat(11);
        bus.bookSeat(12);
        bus.bookSeat(13);
        bus.displayBusInfo();
        
    }
}
