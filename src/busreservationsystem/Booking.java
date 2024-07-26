/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package busreservationsystem;

import busreservationsystem.compands.Queue;
import busreservationsystem.compands.AVLTree;

import java.util.ArrayList;

/**
 *
 * @author gajen
 */
public class Booking {
    
    private Bus targetedBusObject;
    private Customer customer;
    
    private Queue<ArrayList> bookRequestQueue  = new Queue<>();
    private Queue<ArrayList> cancelRequestQueue  = new Queue<>();
    private Queue<ArrayList> replaceSeatRequestQueue  = new Queue<>();
    
    private Customer customerSeatRequest;
    
    private void bookSeatRequest() {
        if (bookRequestQueue.lenght() != 0) {
            ArrayList firstArray = bookRequestQueue.dequeue();
            Bus busSeatRequest = (Bus) firstArray.get(0);
            Customer customerSeatRequest = (Customer) firstArray.get(1);
            int seatNumberRequest = (Integer) firstArray.get(firstArray.size() - 1);
            busSeatRequest.bookSeat(seatNumberRequest);
        }
    }
    private void cancelSeatRequest() {
        if (cancelRequestQueue.lenght() != 0) {
            ArrayList firstArray = cancelRequestQueue.dequeue();
            Bus busSeatRequest = (Bus) firstArray.get(0);
            Customer customerSeatRequest = (Customer) firstArray.get(1);
            int seatNumberRequest = (Integer) firstArray.get(firstArray.size() - 1);
            busSeatRequest.cancelSeat(seatNumberRequest);
        }
    }
    private void replaceSeatRequest() {
        if (replaceSeatRequestQueue.lenght() != 0) {
            ArrayList firstArray = replaceSeatRequestQueue.dequeue();
            Bus busSeatRequest = (Bus) firstArray.get(0);
            Customer customerSeatRequest = (Customer) firstArray.get(1);
            int currentSeatRequest = (Integer) firstArray.get(firstArray.size() - 2);
            int newSeatRequest = (Integer) firstArray.get(firstArray.size() - 1);
            busSeatRequest.cancelSeat(currentSeatRequest);
            busSeatRequest.bookSeat(newSeatRequest);
        }
    }
    
    
    public Booking(Bus object, Customer customer) {
        this.targetedBusObject = object;
        this.customer = customer;
        
        bookSeatRequest();
        cancelSeatRequest();
        replaceSeatRequest();
    }
    
    public boolean bookASeat(int seatNum){
        ArrayList bookInfo = new ArrayList();
        if (targetedBusObject.isSeatAvailable(seatNum)) {
            bookInfo.add(targetedBusObject);
            bookInfo.add(customer);
            bookInfo.add(seatNum);
            bookRequestQueue.enqueue(bookInfo);
            return true;   
        }
        
        bookSeatRequest();
        return false;
    }
    
    public boolean cancelASeat(int seatNum) {
        ArrayList bookInfo = new ArrayList();
        if (targetedBusObject.isSeatAvailable(seatNum)) {
            bookInfo.add(targetedBusObject);
            bookInfo.add(customer);
            bookInfo.add(seatNum);
            cancelRequestQueue.enqueue(bookInfo);
            return true;   
        }
        
        cancelSeatRequest();
        return false;
    }
    
    public boolean replaceASeat(int currentSeatNum, int newSeatNum){
        ArrayList bookInfo = new ArrayList();
        if (targetedBusObject.isSeatAvailable(newSeatNum)) {
            bookInfo.add(targetedBusObject);
            bookInfo.add(customer);
            bookInfo.add(currentSeatNum);
            bookInfo.add(newSeatNum);
            replaceSeatRequestQueue.enqueue(bookInfo);
            return true;
        }
        
        replaceSeatRequest();
        return false;
    }
    
    public void noteifyBesideCustomer(AVLTree<Customer> custArray){
        int index = custArray.indexOf(customerSeatRequest);
        if (custArray.length() > index + 1 && index + 1 < 0) {
           Customer besideCustomer  = custArray.getByIndex( index + 1);
        }
    }
}
