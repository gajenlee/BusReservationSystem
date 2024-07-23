/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package busreservationsystem;

import java.util.Scanner;
import busreservationsystem.compands.LinkedList;
/**
 *
 * @author gajen
 */
public class ReservationInterface {
    
    public static void  mainInterface() {
        
        Scanner scan = new Scanner(System.in);
        int getInput;
        
        System.out.println(" ========================================== Bus Reservation System ========================================== \n");
        System.out.println("1. Book a Seat");
        System.out.println("2. Search Bus and Informations");
        System.out.println("3. Company Usage\n");
        
        System.out.print("Enter the value (1, 2, 3): ");
        getInput = scan.nextInt();
    }
    
    public static LinkedList<String> registerInterface() {
        Scanner scan = new Scanner(System.in);
        String name;
        String phoneNum;
        String email;
        String city;
        int age;
        LinkedList<String> array = new LinkedList<>(5);
        
        System.out.println(" ========================================== Bus Reservation System | Register ========================================== \n");
        System.out.print("Customer Name: ");
        name = scan.nextLine();
        array.push(name);
        
        System.out.print("Customer Phone Number: ");
        phoneNum = scan.nextLine();
        array.push(phoneNum);
        
        System.out.print("Customer Email: ");
        email = scan.nextLine();
        array.push(email);
        
        System.out.print("Customer City: ");
        city = scan.nextLine();
        array.push(city);
        
        System.out.print("Customer Age: ");
        age = scan.nextInt();
        array.push(Integer.toString(age));
        
        return array;
    }
    
    public static LinkedList<String> registerInterfaceBus() {
        Scanner scan = new Scanner(System.in);
        String numberPlate;
        String startPoint;
        String endPoint;
        String startTime;
        double fare;
        LinkedList<String> array = new LinkedList<>(5);
        
        System.out.println(" ========================================== Bus Reservation System | Register ========================================== \n");
        System.out.print("Bus Number Plate: ");
        numberPlate = scan.nextLine();
        array.push(numberPlate);
        
        System.out.print("Bus Starting Point: ");
        startPoint = scan.nextLine();
        array.push(startPoint);
        
        System.out.print("Bus Ending Point: ");
        endPoint = scan.nextLine();
        array.push(endPoint);
        
        System.out.print("Starting Time: ");
        startTime = scan.nextLine();
        array.push(startTime);
        
        System.out.print("Bus One Seat Fare: ");
        fare = scan.nextDouble();
        array.push(Double.toString(fare));
        
        return array;
    }
    
    public static void bookingInterface() {
        
        Scanner scan = new Scanner(System.in);
        int getInput;
        
        System.out.println(" ========================================== Bus Reservation System | Register ========================================== \n");
        System.out.println("1. Register");
        System.out.println("2. Already Register");
        
        System.out.print("Enter the value (1, 2): ");
        getInput = scan.nextInt();
    }
    
    public static String getSearchBusNumberPlate() {
        Scanner scan = new Scanner(System.in);
        String numberPlate;
        
        System.out.println(" ========================================== Bus Reservation System | Booking ========================================== \n");
        System.out.print(" Enter the Bus Number Plate Number: ");
        numberPlate = scan.nextLine();
        
        return numberPlate;
    }
    
    public static void bookingMenu() {
        Scanner scan = new Scanner(System.in);
        int getInput;
        
        System.out.println(" ========================================== Bus Reservation System | Booking ========================================== \n");
        System.out.println("1. Book Seat");
        System.out.println("2. Cancel Booking");
        System.out.println("3. Replace Seat");
        
        System.out.print("Enter the value (1, 2): ");
        getInput = scan.nextInt();
    }
    
    public static void companyUeage() {
        Scanner scan = new Scanner(System.in);
        int getInput;
        
        System.out.println(" ========================================== Bus Reservation System | Company Ueage ========================================== \n");
        System.out.println("1. Ragister Bus");
        System.out.println("2. Search Bus");
        
        System.out.print("Enter the value (1, 2): ");
        getInput = scan.nextInt();
    }
    
}
