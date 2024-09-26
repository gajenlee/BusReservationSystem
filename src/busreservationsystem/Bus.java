/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package busreservationsystem;

import busreservationsystem.compands.LinkedList;

/**
 *
 * @author gajen
 */
public class Bus implements Comparable<Bus> {
    
    private int TOTAL_SEAT = 60;
    private LinkedList<Integer> seats;
    
    private String busNumberPlate;
    private String startPoint = "";
    private String endPoint = "";
    private String startTime = "";
    private float fare;
    
    private void assignEmptySeatValues() {
        for(int i = 0; i<TOTAL_SEAT; i++){
            seats.push(0);
        }
    }
    
    
    public Bus(String number, String startPoint, String endPoint, String startTime, int total_seat, float fare){
        this.busNumberPlate = number;
        this.TOTAL_SEAT = total_seat;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.startTime = startTime;
        this.fare = fare;
        
        //   create a bus seats
        seats = new LinkedList<>(TOTAL_SEAT);
        
        //   assign empty seats for bus
        assignEmptySeatValues();
    }
    
    //    comapare to object number plate
    @Override
    public int compareTo(Bus other) {return this.busNumberPlate.compareToIgnoreCase(other.busNumberPlate);}
    
    //    Objct print string
    @Override
    public String toString() {return "Bus [Number Plate = " + busNumberPlate + "]";}
    
    //    get infromation
    public String getNumberPlate() {return busNumberPlate;}
    public int getTotalSeats() {return TOTAL_SEAT;}
    public String getStartPoint() {return startPoint;}
    public String getEndPoint() {return endPoint;}
    public String getStartTime() {return startTime;}
    public double getFare() {return fare;}
    
    
    //    set infromation
    public void setNumberPlate(String numberPlate) {this.busNumberPlate = numberPlate;}
    public void setTotalSeats(int totalSeat) {this.TOTAL_SEAT = totalSeat;}
    public void setStartPoint(String startPoint) {
        if (this.endPoint != startPoint) this.startPoint = startPoint;
    }
    public void setEndPoint(String endPoint) {
        if (this.startPoint != endPoint) this.endPoint = endPoint;
    }
    public void setStartTime(String startTime) {this.startTime = startTime;}
    public void setFare(float fare) {this.fare = fare;}
    
    //    book a seat
    public void bookSeat(int seatNumber){
        if (!(seatNumber - 1 < TOTAL_SEAT) || !(0 < seatNumber - 1)) {
            throw new IllegalStateException("The seat number " + seatNumber + " is not found it");
        }
        seats.set(seatNumber - 1, 1);
    }
    
    public boolean isSeatAvailable(int seatNum) {
        if ( seats.get(seatNum - 1) == 0 ) return true;
        else return false;
    } 
    
    //    Cancel Seat
    public void cancelSeat(int seatNumber){
        if (!(seatNumber - 1 < TOTAL_SEAT) || !(0 < seatNumber - 1)) {
            throw new IllegalStateException("The seat number " + seatNumber + " is not found it");
        }
        seats.set(seatNumber - 1, 0);
    }
    
    
    //   Display the bus Seats
    public void displayBusInfo() {
        
        StringBuilder txt = new StringBuilder();
        int seatRow = 0;
        
        txt.append("  ------------- Bus Informations -------------  \n\n");
        txt.append(" Bus Number Plate   : " + busNumberPlate + "\n");
        txt.append(" Bus Starting Point : " + startPoint + "\n");
        txt.append(" Bus Ending Point   : " + endPoint + "\n");
        txt.append(" Bus Starting Time  : " + startTime + "\n");
        txt.append("\n\n  ------------- Bus Seats and Reservations -------------  \n\n");

        for (int i = 0; i < seats.length(); i++) {
            String seatNumber = (i < 9) ? "Seat 0" + (i + 1) : "Seat " + (i + 1);
            String status = (seats.get(i) == 0) ? ": Not-Reserved  " : ": Reserved      ";

            txt.append(seatNumber).append(status);
            seatRow++;

            if (seatRow == 3) {
                txt.append("      ");
            }
            if (seatRow == 5) {
                txt.append("\n");
                seatRow = 0;
            }
        }
        
        System.out.println(txt.toString());
        
    }

    String getCustomerName() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
