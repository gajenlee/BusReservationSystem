/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package busreservationsystem;

import java.util.Scanner;
import busreservationsystem.compands.LinkedList;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author gajen
 */
public class ReservationInterface {
    
    public static void print_art() {
        String art = "██████  ███████ ███████ ███████ ██████  ██    ██  █████  ████████ ██  ██████  ███    ██ \n" +
"██   ██ ██      ██      ██      ██   ██ ██    ██ ██   ██    ██    ██ ██    ██ ████   ██ \n" +
"██████  █████   ███████ █████   ██████  ██    ██ ███████    ██    ██ ██    ██ ██ ██  ██ \n" +
"██   ██ ██           ██ ██      ██   ██  ██  ██  ██   ██    ██    ██ ██    ██ ██  ██ ██ \n" +
"██   ██ ███████ ███████ ███████ ██   ██   ████   ██   ██    ██    ██  ██████  ██   ████ \n" +
"                                                                                        \n" +
"                                                                                        ";
        
        String art_2 = "███████ ██    ██ ███████ ████████ ███████ ███    ███ \n" +
"██       ██  ██  ██         ██    ██      ████  ████ \n" +
"███████   ████   ███████    ██    █████   ██ ████ ██ \n" +
"     ██    ██         ██    ██    ██      ██  ██  ██ \n" +
"███████    ██    ███████    ██    ███████ ██      ██ \n" +
"                                                     \n" +
"                                                     ";
        
        System.out.println("\n");
        System.out.println(art);
        System.out.println(art_2);
    }
    
    public static int  mainInterface() {
        
        Scanner scan = new Scanner(System.in);
        int getInput;
        print_art();
        System.out.println(" ================================== Bus Reservation System ================================== \n");
        System.out.println("1. Book a Seat");
        System.out.println("2. Search Bus and Informations");
        System.out.println("3. Company Usage");
        System.out.println("4. Exit");
        
        System.out.print("\nEnter the value (1, 2, 3, 4) > ");
        getInput = scan.nextInt();
        
        return getInput;
    }
    
    public static LinkedList<String> registerInterface() {
        Scanner scan = new Scanner(System.in);
        String name;
        String phoneNum;
        String email;
        String city;
        int age;
        LinkedList<String> array = new LinkedList<>(5);
        print_art();
        System.out.println(" ================================== Bus Reservation System | Register ================================== \n");
        System.out.print("Customer Name > ");
        name = scan.nextLine();
        array.push(name);
        
        System.out.print("Customer Phone Number > ");
        phoneNum = scan.nextLine();
        array.push(phoneNum);
        
        System.out.print("Customer Email > ");
        email = scan.nextLine();
        array.push(email);
        
        System.out.print("Customer City > ");
        city = scan.nextLine();
        array.push(city);
        
        System.out.print("Customer Age > ");
        age = scan.nextInt();
        array.push(Integer.toString(age));
        
        return array;
    }
    
    public static LinkedList<String> registerInterfaceBus() {
        Scanner scan = new Scanner(System.in);
        String numberPlate;
        int seatTotal;
        String startPoint;
        String endPoint;
        String startTime;
        double fare;
        LinkedList<String> array = new LinkedList<>(5);
        print_art();
        System.out.println(" ================================== Bus Reservation System | Register ================================== \n");
        System.out.print("Bus Number Plate > ");
        numberPlate = scan.nextLine();
        array.push(numberPlate);
        
        System.out.print("Total Bus Seats > ");
        seatTotal = scan.nextInt();
        array.push(Integer.toString(seatTotal));
        
        System.out.print("Bus Starting Point > ");
        startPoint = scan.nextLine();
        array.push(startPoint);
        
        System.out.print("Bus Ending Point > ");
        endPoint = scan.nextLine();
        array.push(endPoint);
        
        System.out.print("Starting Time > ");
        startTime = scan.nextLine();
        array.push(startTime);
        
        System.out.print("Bus One Seat Fare > ");
        fare = scan.nextDouble();
        array.push(Double.toString(fare));
        
        return array;
    }
    
    public static int bookingInterface() {
        
        Scanner scan = new Scanner(System.in);
        int getInput;
        print_art();
        System.out.println(" ================================== Bus Reservation System | Register ================================== \n");
        System.out.println("1. Register");
        System.out.println("2. Already Register");
        System.out.println("3. Back");
        
        System.out.print("\nEnter the value (1, 2, 3) > ");
        getInput = scan.nextInt();
        
        return getInput;
    }
    
    public static String getSearchBusNumberPlate() {
        Scanner scan = new Scanner(System.in);
        String numberPlate;
        print_art();
        System.out.println(" ================================== Bus Reservation System | Booking ================================== \n");
        System.out.print("\nEnter the Bus Number Plate Number > ");
        numberPlate = scan.nextLine();
        
        return numberPlate;
    }
    
    public static int getSeatNumber(String txt) {
        
        Scanner scan = new Scanner(System.in);
        int getInput;
        print_art();
        System.out.println(" ================================== Bus Reservation System | Booking ================================== \n");
        System.out.print("\nEnter the " + txt + " bus seat number: ");
        getInput = scan.nextInt();
        
        return getInput;
    }
    
    public static String getStringVal(String txt) {
        Scanner scan = new Scanner(System.in);
        String getInput;
        
        System.out.print(txt);
        getInput = scan.nextLine();
        
        return getInput;
    }
    
    public static int getIntegerVal(String txt) {
        Scanner scan = new Scanner(System.in);
        int getInput;
        
        System.out.print(txt);
        getInput = scan.nextInt();
        
        return getInput;
    }
    
    public static double getDoubleVal(String txt) {
        Scanner scan = new Scanner(System.in);
        double getInput;
        
        System.out.print(txt);
        getInput = scan.nextDouble();
        
        return getInput;
    }
    
    public static int displayBusEdit() {
        Scanner scan = new Scanner(System.in);
        int getInput;
        print_art();
        System.out.println(" ================================== Bus Reservation System | Company Usage ================================== \n");
        System.out.println("1. Edit Number Plate");
        System.out.println("2. Delete");
        System.out.println("3. Edit Total Seats");
        System.out.println("4. Edit Starting Point");
        System.out.println("5. Edit Ending Point");
        System.out.println("6. Edit Starting Time");
        System.out.println("7. Edit Fare");
        System.out.println("8. Back");
        
        System.out.print("\nEnter the value (1, 2, 3, 4, 5, 6, 7, 8) > ");
        getInput = scan.nextInt();
        return getInput;
    }
    
    
    public static int bookingMenu() {
        Scanner scan = new Scanner(System.in);
        int getInput;
        print_art();
        System.out.println(" ================================== Bus Reservation System | Booking ================================== \n");
        System.out.println("1. Book Seat");
        System.out.println("2. Cancel Booking");
        System.out.println("3. Replace Seat");
        System.out.println("4. Back");
        
        System.out.print("\nEnter the value (1, 2, 3, 4) > ");
        getInput = scan.nextInt();
        
        return getInput;
    }
    
    public static int companyUeage() {
        Scanner scan = new Scanner(System.in);
        int getInput;
        print_art();
        System.out.println(" ========================================== Bus Reservation System | Company Ueage ========================================== \n");
        System.out.println("1. Ragister Bus");
        System.out.println("2. Search Bus");
        System.out.println("3. Back");
        
        System.out.print("\nEnter the value (1, 2, 3) > ");
        getInput = scan.nextInt();
        
        return getInput;
    }
    
    public void waitConsole(){
        System.out.print("\nContinue");
        try {
            System.in.read();
        } catch (IOException ex) {
            Logger.getLogger(ReservationInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
