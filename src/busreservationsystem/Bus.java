/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package busreservationsystem;

import busreservationsystem.compands.LinkedList;
import java.util.UUID;

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
    private String bus_id;
    private UUID uuid = UUID.randomUUID();
    
    private void assignEmptySeatValues() {
        for(int i = 0; i<TOTAL_SEAT; i++){
            seats.push(0);
        }
    }
    
    
    public Bus(String number, String startPoint, String endPoint, String startTime, int total_seat, float fare){
        this.bus_id = uuid.toString();
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
    
    
    public int compareToNumberPlate(Bus other) { return this.busNumberPlate.compareTo(other.getNumberPlate());}
    
    //    comapare to object number plate
    @Override
    public int compareTo(Bus other) {return this.endPoint.compareToIgnoreCase(other.getEndPoint());}
    
    //    Objct print string
    @Override
    public String toString() {return "Bus [Bus ID = " + bus_id + "]";}
    
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
        if (!(seatNumber - 1 < TOTAL_SEAT) || !(0 <= seatNumber - 1)) {
            throw new IllegalStateException("The seat number " + seatNumber + " is not found it");
        }
        seats.set(seatNumber - 1, 1);
    }
    
    
    //    Cancel Seat
    public void cancelSeat(int seatNumber){
        if (!(seatNumber - 1 < TOTAL_SEAT) || !(0 <= seatNumber - 1)) {
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
    
    public String getBusId() {
        return bus_id;
    }
    public LinkedList<Integer> getSeats() {
        return this.seats;
    }
    public void setBusId(String bus_id) {
        this.bus_id = bus_id;
    }
    
    public void setBusSeatArray(LinkedList<Integer> seats) {
        this.seats = seats;
    }
    public LinkedList<Integer> getBusSeatsArray(){
        return seats;
    }
}
