/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package busreservationsystem;
import busreservationsystem.compands.Queue;
import busreservationsystem.compands.AVLTree;
import busreservationsystem.compands.DBConnection;
import busreservationsystem.compands.LinkedList;

import java.util.ArrayList;
import java.util.UUID;

/**
 *
 * @author gajen
 */
public class Booking extends DBConnection  implements Comparable<Booking> {
    
    private Bus targetedBusObject;
    private Customer customer;
    
    private Queue<ArrayList> bookRequestQueue  = new Queue<>();
    private Queue<ArrayList> cancelRequestQueue  = new Queue<>();
    private Queue<ArrayList> replaceSeatRequestQueue  = new Queue<>();
    private UUID uuid = UUID.randomUUID();
    private String booking_id;
    private String cust_id;
    private String bus_id;
    private int seatNum;
    
    private Customer customerSeatRequest;
      
    private void bookSeatRequest() {
        if (bookRequestQueue.lenght() != 0) {
            ArrayList firstArray = bookRequestQueue.dequeue();
            Bus busSeatRequest = (Bus) firstArray.get(0);
            Customer customerSeatRequest = (Customer) firstArray.get(1);
            int seatNumberRequest = (Integer) firstArray.get(firstArray.size() - 1);
            busSeatRequest.bookSeat(seatNumberRequest);
            insertBooking(this);
        }
    }
    private void cancelSeatRequest() {
        if (cancelRequestQueue.lenght() != 0) {
            ArrayList firstArray = cancelRequestQueue.dequeue();
            Bus busSeatRequest = (Bus) firstArray.get(0);
            Customer customerSeatRequest = (Customer) firstArray.get(1);
            int seatNumberRequest = (Integer) firstArray.get(firstArray.size() - 1);
            busSeatRequest.cancelSeat(seatNumberRequest);
            deleteBooking(busSeatRequest, customerSeatRequest);
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
            updateBooking(busSeatRequest, customerSeatRequest, newSeatRequest);
        }
    }
    
    public void sendRequests() {
        bookSeatRequest();
        cancelSeatRequest();
        replaceSeatRequest();
    }
    
    public Booking(Bus object, Customer customer) {
        this.targetedBusObject = object;
        this.customer = customer;
        this.booking_id = uuid.toString();
        this.cust_id = customer.getCustomerId();
        this.bus_id = object.getBusId();
        sendRequests();
    }
    
    public boolean bookASeat(int seatNum){
        ArrayList bookInfo = new ArrayList();
        this.seatNum = seatNum;
        if (targetedBusObject != null && customer != null) {
            bookInfo.add(targetedBusObject);
            bookInfo.add(customer);
            bookInfo.add(seatNum);
            bookRequestQueue.enqueue(bookInfo);
            return true;   
        }
        return false;
    }
    
    public boolean cancelASeat(int seatNum) {
        ArrayList bookInfo = new ArrayList();
        if (isSeat(targetedBusObject, seatNum)) {
            bookInfo.add(targetedBusObject);
            bookInfo.add(customer);
            bookInfo.add(seatNum);
            cancelRequestQueue.enqueue(bookInfo);
            return true;   
        }
        return false;
    }
    
    public boolean replaceASeat(int currentSeatNum, int newSeatNum){
        ArrayList bookInfo = new ArrayList();
        if (isSeat(targetedBusObject, currentSeatNum)) {
            bookInfo.add(targetedBusObject);
            bookInfo.add(customer);
            bookInfo.add(currentSeatNum);
            bookInfo.add(newSeatNum);
            replaceSeatRequestQueue.enqueue(bookInfo);
            return true;
        }
        return false;
    }
    
    public void noteifyBesideCustomer(AVLTree<Customer> custArray){
        int index = custArray.indexOf(customerSeatRequest);
        if (custArray.length() > index + 1 && index + 1 < 0) {
           Customer besideCustomer  = custArray.getByIndex( index + 1);
        }
    }
    
    public String getBookingId() {
        return this.booking_id;
    }
    public String getBusId() {
        return this.bus_id;
    }
    public String getCustId() {
        return this.cust_id;
    }
    public int getSeatNum() {
        return this.seatNum;
    }
    
    public void setBookingId(String bookingId) {
        this.booking_id = bookingId;
    }
    public void setBusId(String busId) {
        this.bus_id = busId;
    }
    public void setCustId(String custId) {
        this.cust_id = custId;
    }
    public void setSeatNum(int seatNum) {
        this.seatNum = seatNum;
    }
    
    public boolean isSeat(Bus bus, int seatNum) {
        LinkedList<Integer> booked = findBookedSeats(bus.getBusId());
        for (int i =0; i<booked.length(); i++) {
            if (booked.get(i) == seatNum) {
                return true;
            }
        }
        return false;
    }

    
    //    comapare to object number plate
    @Override
    public int compareTo(Booking other) {return this.booking_id.compareToIgnoreCase(other.getBookingId());}
    
    public int compareBusTo(Booking other) { return this.bus_id.compareTo(other.getBusId());}
    
    //    Objct print string
    @Override
    public String toString() {return "Booking [Booking ID = " + this.getBookingId() + "]";}
}
