/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package busreservationsystem;
/**
 *
 * @author gajen
 */
public class Customer implements Comparable<Customer> {
    private String custName;
    private String custPhoneNumber;
    private String custEmail;
    private String custCity;
    private int custAge;
    
    
    public Customer(String name, String phoneNum, String email, String city, int age) {
        setCustomerName(name);
        setCustomerPhoneNumber(phoneNum);
        setCustomerEmail(email);
        setCustomerCity(city);
        setCustomerAge(age);
    }
    
    
    //    comapare to object number plate
    @Override
    public int compareTo(Customer other) {
        return this.custName.compareToIgnoreCase(other.getCustomerName());
    }
    
    //    Objct print string
    @Override
    public String toString() {return "Bus [Number Plate = " + custName + "]";}
    
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
}
