/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package busreservationsystem;
import busreservationsystem.Bus;

/**
 *
 * @author gajen
 */
public class Booking {
    
    private Bus targetedBusObject;
    private Customer customer;
    
    
    public Booking(Bus object, Customer customer) {
        this.targetedBusObject = object;
        this.customer = customer;
    }
    
    public void bookASeat(int seatNum){
        targetedBusObject.bookSeat(seatNum);
    }
    
    public void cancelASeat(int seatNum) {
        targetedBusObject.cancelSeat(seatNum);
    }
    
    public void replaceASeat(int currentSeatNum, int newSeatNum){
        targetedBusObject.cancelSeat(currentSeatNum);
        targetedBusObject.bookSeat(newSeatNum);
    }
    
    public void noteifyBesideCustomer(){
        
    }
}
