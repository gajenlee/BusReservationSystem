package busreservationsystem;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import busreservationsystem.compands.LinkedList;
import busreservationsystem.compands.AVLTree;


/**
 *
 * @author gajen
 */
public class MainBusReservationSystemRunner extends ReservationInterface{
    
    private AVLTree<Bus> busTree;
    private AVLTree<Customer> customerTree;
    private AVLTree<Booking> bookings;
    
    public MainBusReservationSystemRunner() {
        initialize();
        this.busTree = loadAllDataFromBus();
        this.customerTree = loadAllDataFromCustomer();
        this.bookings = loadBookings();
    }
    
    public void mainSystemLoop() {
        boolean mainLoop = true;
        while (mainLoop) {
            int mainInput = mainInterface();
            if (mainInput == 1){
                int getBookInput = bookingInterface();
                if (getBookInput == 1){
                    LinkedList<String> array = registerInterface();
                    Customer customer = registerCustomer(array);
                    System.out.println("The Customer Sccueefully Registered .... ");
                    waitConsole();
                    if (bookingconnecter(customer) == -1) continue;
                    
                } else if (getBookInput == 2) {
                    continue;
                } else {
                    mainLoop = false;
                    break;
                }
                
            } else if (mainInput == 2) {
                searchAndDisplayInfo();
            } else if (mainInput == 3) {
                int getInput = companyUeage();
                
                if (getInput == 1) {
                    LinkedList<String> array = registerInterfaceBus();
                    registerABus(array);
                    System.out.println("The bus sccueefully registered .... ");
                } else if (getInput == 2) {
                    if (searchBus() == -1) continue;
                } else {
                    continue;
                }
                
            } else {
                mainLoop = false;
                break;
            }
            
        }
    }
    
    private int bookingconnecter(Customer cust) {
        int getInput = bookingMenu();
        if(getInput == 1){
            bookASeat(cust);
            
        } else if (getInput == 2) {
            cancelASeat(cust);
            
        } else if (getInput == 3) {
            replaceASeat(cust);
            
        } else {
            return -1;
        }
        
        return 1;
    }
    
    public void bookASeat(Customer cust) {
        String name = getSearchBusNumberPlate();
        Bus bus = busTree.binarySearchByString(name);
        bus.displayBusInfo();
        int seatNum = getSeatNumber("");
        Booking book = new Booking(bus, cust);
        book.bookASeat(seatNum);
        insertBooking(book);
        System.out.println("Seat Booked .... ");
        waitConsole();
    }
    public void cancelASeat(Customer cust) {
        String name = getSearchBusNumberPlate();
        Bus bus = busTree.binarySearchByString(name);
        bus.displayBusInfo();
        int seatNum = getSeatNumber("");
        Booking book = new Booking(bus, cust);
        book.cancelASeat(seatNum);
        System.out.println("Seat Canceled .... ");
        System.out.println("The message sent to besite customer.... ");
        waitConsole();
    }
    public void replaceASeat(Customer cust) {
        String name = getSearchBusNumberPlate();
        Bus bus = busTree.binarySearchByString(name);
        bus.displayBusInfo();
        int seatNum = getSeatNumber("");
        int newSeatNum = getSeatNumber("new");
        Booking book = new Booking(bus, cust);
        book.replaceASeat(seatNum, newSeatNum);
        System.out.println("New seat allocated .... ");
        System.out.println("The message sent to besite customer.... ");
        waitConsole();
    }
    
    public Customer registerCustomer(LinkedList<String> arr) {
        Customer customer = new Customer(arr.get(0), arr.get(1), arr.get(2), arr.get(3), Integer.parseInt(arr.get(4)));
        customerTree.insert(customer);
        insertCustomer(customer);
        return customer;
    }

    
    public void searchBusAndGetInfo(){}
    
    public void registerABus(LinkedList<String> arr) {
        Bus newBus = new Bus(
                            arr.get(0), 
                            arr.get(1), 
                            arr.get(2), 
                            arr.get(3),
                            Integer.parseInt(arr.get(4)),
                            Float.parseFloat(arr.get(5)));
        busTree.insert(newBus);
        insertBus(newBus);
    }
    public int searchBus() {
        String busName = getStringVal("\nEnter the bus number plate: ");
        Bus targetedBus = busTree.binarySearchByString(busName);
        targetedBus.displayBusInfo();
        
        int getInput = displayBusEdit();
        switch(getInput) {
            case 1:
                renameBus(targetedBus);
                return 1;
            case 2:
                deleteBus(targetedBus);
                return 1;
            case 3:
                editSeatTotal(targetedBus);
                return 1;
            case 4:
                editStartingPoint(targetedBus);
                return 1;
            case 5:
                editEndingPoint(targetedBus);
                return 1;
            case 6:
                editStartingTime(targetedBus);
                return 1;
            case 7:
                editFare(targetedBus);
                return 1;
                
            case 8:
                return -1;

        }
        
        return -1;
    }
    
    public void renameBus(Bus obj){
        String busNumberPlate = getStringVal("\nEnter the number plate: ");
        obj.setNumberPlate(busNumberPlate);
        System.out.println("The bus sccessfully changed ... ");
        waitConsole();
    }
    public void deleteBus(Bus obj){
        busTree.delete(obj);
        System.out.println("The bus sccessfully deleted ... ");
        waitConsole();
    }
    
    public void editSeatTotal(Bus obj){
        int getInput = getIntegerVal("\nEnter the Total Seat Value: ");
        obj.setTotalSeats(getInput);
        System.out.println("The bus sccessfully changed ... ");
        waitConsole();
    }
    public void editStartingPoint(Bus obj){
        String startingPoint = getStringVal("\nEnter the starting point: ");
        obj.setStartPoint(startingPoint);
        System.out.println("The bus sccessfully changed ... ");
        waitConsole();
    }
    
    public void editEndingPoint(Bus obj){
        String endingPoint = getStringVal("\nEnter the ending point: ");
        obj.setEndPoint(endingPoint);
        System.out.println("The bus sccessfully changed ... ");
        waitConsole();
    }
    
    public void editStartingTime(Bus obj){
        String startingTime = getStringVal("\nEnter the starting time: ");
        obj.setStartTime(startingTime);
        System.out.println("The bus sccessfully changed ... ");
        waitConsole();
    }
    
    public void editFare(Bus obj) {
        float fare = getFloatVal("\nEnter the fare value: ");
        obj.setFare(fare);
        System.out.println("The bus sccessfully changed ... ");
        waitConsole();
    }
    
    public void searchAndDisplayInfo() {
        String busName = getStringVal("\nEnter the bus number plate: ");
        Bus bus = busTree.binarySearchByString(busName);
        LinkedList<Integer> bookedList = findBookedSeats(bus.getBusId());
        for (int i =0; i<bookedList.length(); i++){
            bus.bookSeat(bookedList.get(i));
        }
        bus.displayBusInfo();
        System.out.println("Seat Fee: " + bus.getFare());
        
        waitConsole();
    }
    
    private void initialize() {
        createDatabase();
        createBusTable();
        createCustomerTable();
        createBookingTable();
    } 
}
