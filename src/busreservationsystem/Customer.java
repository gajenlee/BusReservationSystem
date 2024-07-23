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
public class Customer {
    private String custName;
    private String custPhoneNumber;
    private String custEmail;
    private String custCity;
    private int custAge;
    private LinkedList<String> custList = new LinkedList<>(5);
    
    
    public Customer(String name, String phoneNum, String email, String city, int age) {
        setCustomerName(name);
        setCustomerPhoneNumber(phoneNum);
        setCustomerEmail(email);
        setCustomerCity(city);
        setCustomerAge(age);
        
        //     Store the customer info
        storeCustomer(custName, custPhoneNumber, custEmail, custCity, custAge);
    }
    
    private void storeCustomer(String name, String phoneNum, String email, String city, int age) {
        custList.push(name);
        custList.push(phoneNum);
        custList.push(email);
        custList.push(city);
        custList.push(Integer.toString(age));
    }
    
    
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
    
    public LinkedList<String> getCustomerInfoArray(){
        return custList;
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
