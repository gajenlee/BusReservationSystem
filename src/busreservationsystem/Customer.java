/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package busreservationsystem;
/**
 *
 * @author gajen
 */
import java.util.UUID;

public class Customer implements Comparable<Customer> {
    private String custName;
    private String custPhoneNumber;
    private String custEmail;
    private String custCity;
    private int custAge;
    private int booked_seat = 0;
    private String customerId;
    private UUID uuid = UUID.randomUUID();
    
    
    public Customer(String name, String phoneNum, String email, String city, int age) {
        setCustomerName(name);
        setCustomerPhoneNumber(phoneNum);
        setCustomerEmail(email);
        setCustomerCity(city);
        setCustomerAge(age);
        this.customerId = uuid.toString();
    }
    
    
    //    comapare to object number plate
    @Override
    public int compareTo(Customer other) {
        return this.customerId.compareToIgnoreCase(other.getCustomerId());
    }
    
    //    Objct print string
    @Override
    public String toString() {return "Customer [ID = " + customerId + "]";}
    
    //    Getters
    public String getCustomerName(){
        return custName;
    }
    public String getCustomerPhoneNumber(){
        return custPhoneNumber;
    }
    public String getCustomerEmail(){
        return custEmail;
    }
    public String getCustomerCity(){
        return custCity;
    }
    public int getCustomerAge(){
        return custAge;
    }
    
    //    Setters
    public void setCustomerName(String name){
        custName = name;
    }
    public void setCustomerPhoneNumber(String phoneNum){
        custPhoneNumber = phoneNum;
    }
    public void setCustomerEmail(String email){
        custEmail = email;
    }
    public void setCustomerCity(String city){
        custCity = city;
    }
    public void setCustomerAge(int age){
        custAge = age;
    }
    
    public void setBookedSeat(int seats){
        this.booked_seat = seats;
    }
    public int getBookedSeat(){
        return this.booked_seat;
    }
    
    public String getCustomerId() {
        return customerId;
    } 
    public void setCustomerId(String cust_id) {
        this.customerId = cust_id;
    }
}
