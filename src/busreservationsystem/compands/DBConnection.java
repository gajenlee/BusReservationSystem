package busreservationsystem.compands;

import busreservationsystem.Booking;
import busreservationsystem.Bus;
import busreservationsystem.Customer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Array;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {
    
    // PostgreSQL database credentials
    private  final String DEFAULT_DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private final String DB_URL = "jdbc:postgresql://localhost:5432/bus_reservation_system";
    private  final String USER = "postgres";
    private  final String PASS = "0000";
    private  final String TARGET_DB = "bus_reservation_system";
    
    protected Connection conn = null;
    

    public DBConnection() {
        try {
            // 1. Register PostgreSQL JDBC driver
            Class.forName("org.postgresql.Driver");

            // 2. Connect to the default 'postgres' database
            conn = (Connection) DriverManager.getConnection(DEFAULT_DB_URL, USER, PASS);
            createDatabase();
            
            conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
            
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
            
    }
    
    protected boolean createDatabase() {
        Statement stmt = null;
        try{
            stmt = conn.createStatement();
            boolean isCreated = false;

            // 3. Check if the target database exists
            String checkDatabaseSQL = "SELECT datname FROM pg_catalog.pg_database WHERE datname = '" + TARGET_DB + "'";
            ResultSet rs = stmt.executeQuery(checkDatabaseSQL);

            if (rs.next()) {
                isCreated = true;
            } else {
                // 4. Create the database if it does not exist
                String createDatabaseSQL = "CREATE DATABASE " + TARGET_DB;
                stmt.executeUpdate(createDatabaseSQL);
                isCreated = true;
            }
            rs.close();
            stmt.close();
            return isCreated;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    protected boolean executeQuery(String query) {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
            stmt.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    protected boolean createBusTable() {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS buses ("
                    + "bus_id VARCHAR(200) PRIMARY KEY, "
                    + "bus_num_plate VARCHAR(100) NOT NULL, "
                    + "start_point VARCHAR(200) NOT NULL, "
                    + "end_point VARCHAR(200) NOT NULL, "
                    + "start_time VARCHAR(100) NOT NULL, "
                    + "seat_fare REAL NOT NULL, "
                    + "bus_seats INT NOT NULL,"
                    + "bus_array INTEGER[] NOT NULL"
                    + ")";
        return executeQuery(createTableQuery);
    }
    
    protected boolean createCustomerTable() {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS customers ("
                    + "cust_id VARCHAR(200) PRIMARY KEY, "
                    + "cust_name VARCHAR(100) NOT NULL, "
                    + "cust_phone_num VARCHAR(200) NOT NULL, "
                    + "cust_email VARCHAR(200) NOT NULL, "
                    + "cust_city VARCHAR(100) NOT NULL, "
                    + "cust_age INT NOT NULL, "
                    + "seat_nums INT"
                    + ")";
        
        return executeQuery(createTableQuery);
    }
    
    protected boolean createBookingTable() {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS booking ("
                    + "booking_id VARCHAR(200) PRIMARY KEY, "
                    + "bus_seats INT NOT NULL,"
                    + "booked_bus VARCHAR(200) NOT NULL,"
                    + "booked_customer VARCHAR(200) NOT NULL, "
                    + "FOREIGN KEY(booked_bus) REFERENCES buses(bus_id) on delete cascade on update cascade,"
                    + "FOREIGN KEY(booked_customer) REFERENCES customers(cust_id) on delete cascade on update cascade"
                    + ")";
        return executeQuery(createTableQuery);
    }
    
    protected AVLTree<Bus> loadAllDataFromBus() {
        Statement stmt = null;
        AVLTree<Bus> buses = new AVLTree<>();
        
        try{
            stmt = conn.createStatement();
            
            String getAllSQL = "SELECT * FROM buses";
            ResultSet rs = stmt.executeQuery(getAllSQL);

            while (rs.next()) {
                String bus_id = rs.getString("bus_id");
                Array bus_array = rs.getArray("bus_array");
                LinkedList<Integer> seats = convertLinkedList(bus_array);
                Bus bus = new Bus(
                    rs.getString("bus_num_plate"),
                    rs.getString("start_point"),
                    rs.getString("end_point"),
                    rs.getString("start_time"),
                    rs.getInt("bus_seats"),
                    rs.getFloat("seat_fare")
                );
                bus.setBusSeatArray(seats);
                bus.setBusId(bus_id);
                buses.insert(bus);
            }
            
        }catch (SQLException e) {
            e.printStackTrace();
        }
        
        return buses;
    }
    
    protected AVLTree<Customer> loadAllDataFromCustomer() {
        Statement stmt = null;
        AVLTree<Customer> customers = new AVLTree<>();
        
        try{
            stmt = conn.createStatement();
            
            String getAllSQL = "SELECT * FROM customers";
            ResultSet rs = stmt.executeQuery(getAllSQL);
            
            while (rs.next()) {
                String cust_id = rs.getString("cust_id");
                int seat = rs.getInt("seat_nums");
                Customer customer = new Customer(
                        rs.getString("cust_name"),
                        rs.getString("cust_phone_num"),
                        rs.getString("cust_email"),
                        rs.getString("cust_city"),
                        rs.getInt("cust_age")
                );
                customer.setCustomerId(cust_id);
                customer.setBookedSeat(seat);
                customers.insert(customer);
            }
            
        }catch (SQLException e) {
            e.printStackTrace();
        }
        
        return customers;
    }
    
    protected void updateCustomerBookedSeat(int bookedSeat, String customerID) {
        String updateQuery = "UPDATE customers SET seat_nums = '" + bookedSeat +"' WHERE cust_id = '"+ customerID +"'";
        executeQuery(updateQuery);
    }

    protected void insertCustomer(Customer cust) {
        String insertQuery = "INSERT INTO customers ( "
            + "cust_id, cust_name, cust_phone_num, cust_email, cust_city, cust_age, seat_nums) "
            + "VALUES ("
            + "'"+ cust.getCustomerId() +"', "
            + "'"+ cust.getCustomerName() +"', '"
            + cust.getCustomerPhoneNumber() +"', "
            + "'"+ cust.getCustomerEmail() +"',"
            + " '"+ cust.getCustomerCity() +"',"
            + " '"+ cust.getCustomerAge() +"', '"+ cust.getBookedSeat() +"' )";
        executeQuery(insertQuery);
    }
    
    private Array convertArray(int[] seats) {
        Array sqlArray = null;
        try {
            Integer[] quantitiesWrapper = new Integer[seats.length];
            for (int i = 0; i < seats.length; i++) {
                quantitiesWrapper[i] = seats[i];
            }
            sqlArray = conn.createArrayOf("INTEGER", quantitiesWrapper);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sqlArray;
    }
    
    private int[] convertIntArr(Array array){
        Integer[] seatWrapper = new Integer[0];
        try {
            seatWrapper = (Integer[]) array.getArray();
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        int[] seats = new int[seatWrapper.length];
        for (int i=0; i < seatWrapper.length; i++) {
            seats[i] = seatWrapper[i];
        }
        return seats;
    }

    private Array convertArray(LinkedList<Integer> seats) {
        Array sqlArray = null;
        try {
            Integer[] quantitiesWrapper = new Integer[seats.length()];
            for (int i = 0; i < seats.length(); i++) {
                quantitiesWrapper[i] = seats.get(i);
            }
            sqlArray = conn.createArrayOf("INTEGER", quantitiesWrapper);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sqlArray;
    }
    
    private LinkedList<Integer> convertLinkedList(Array array){
        Integer[] seatWrapper = new Integer[0];
        try {
            seatWrapper = (Integer[]) array.getArray();
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        LinkedList<Integer> seats = new LinkedList<>(seatWrapper.length);
        for (int i=0; i < seatWrapper.length; i++) {
            seats.push(seatWrapper[i]);
        }
        return seats;
    }
    
    protected void insertBus(Bus bus) {
        LinkedList<Integer> seats = bus.getBusSeatsArray();
        Array arr = convertArray(seats);
        
        String insertQuery = "INSERT INTO buses ( "
            + "bus_id, bus_num_plate, start_point, end_point, start_time, seat_fare, bus_seats, bus_array)"
            + "VALUES ( "
            + "'"+ bus.getBusId() +"', "
            + "'"+ bus.getNumberPlate() +"', '"
            + bus.getStartPoint() +"', "
            + "'"+ bus.getEndPoint() +"',"
            + " '"+ bus.getStartTime() +"',"
            + " '"+ bus.getFare() +"', '"+ bus.getTotalSeats() +"',"
            + " '"+ arr +"' )";
        executeQuery(insertQuery);
    }
    
    protected void deleteBus(int bus_id){
        String deleteQuery = "DELETE FROM buses WHERE bus_id = '"+ bus_id +"'";
        executeQuery(deleteQuery);
    }
    protected void deleteCustomer(int cust_id){
        String deleteQuery = "DELETE FROM customers WHERE cust_id = '"+ cust_id +"'";
        executeQuery(deleteQuery);
    }
    protected void insertBooking(Booking book) {
        String insertQuery = "INSERT INTO booking(booking_id, bus_seats, booked_bus, booked_customer)"
                + "VALUES ('"+ book.getBookingId() +"', '"+ book.getSeatNum() +"',"
                + " ( SELECT bus_id FROM buses WHERE bus_id='"+ book.getBusId() +"'), "
                + "( SELECT cust_id FROM customers WHERE cust_id= '"+ book.getCustId() +"'))";
        executeQuery(insertQuery);
    }
    
    protected Customer getCustomerInfo(String customer_id) {
        Customer customer = null;
        Statement stmt = null;
        String customerQuery = "SELECT * FROM customers WHERE cust_id='"+ customer_id +"'";
        try{
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(customerQuery);

            while (rs.next()) {
                String cust_id = rs.getString("cust_id");
                int seat = rs.getInt("seat_nums");
                customer = new Customer(rs.getString("cust_name"),
                  rs.getString("cust_phone_num"),
                  rs.getString("cust_email"),
                  rs.getString("cust_city"),
                  rs.getInt("cust_age")
                );
                customer.setCustomerId(cust_id);
                customer.setBookedSeat(seat);
            }
            
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }
    
    protected Bus getBusInfo(String bus_id) {
        Bus bus = null;
        Statement stmt = null;
        String customerQuery = "SELECT * FROM buses WHERE bus_id='"+ bus_id +"'";
        try{
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(customerQuery);
            
            while (rs.next()) {
                Array bus_array = rs.getArray("bus_array");
                LinkedList<Integer> seats = convertLinkedList(bus_array);
                bus = new Bus(rs.getString("bus_num_plate"),
                    rs.getString("start_point"),
                    rs.getString("end_point"),
                    rs.getString("start_time"),
                    rs.getInt("bus_seats"),
                    rs.getFloat("seat_fare")
                );
                bus.setBusSeatArray(seats);
                bus.setBusId(bus_id);
            }
            
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return bus;
    }
    
    protected AVLTree<Booking> loadBookings() {
        AVLTree<Booking> bookings = new AVLTree<Booking>();
        Statement stmt = null;
        
        try{
            stmt = conn.createStatement();
            String getAllSQL = "SELECT * FROM booking";
            ResultSet rs = stmt.executeQuery(getAllSQL);
            
            while (rs.next()) {
                
                String cust_id = rs.getString("booked_customer");
                String bus_id = rs.getString("booked_bus");
                String booking_id = rs.getString("booking_id");
                int bus_seat = rs.getInt("bus_seats");
                
                Customer customer = getCustomerInfo(cust_id);
                Bus bus = getBusInfo(bus_id);
                bus.bookSeat(bus_seat);
                Booking booking = new Booking(bus, customer);
                booking.setBookingId(booking_id);
                booking.setSeatNum(bus_seat);
                bookings.insert(booking);
                System.out.println("The Booking data added");
                
            }
            
        }catch (SQLException e) {
            e.printStackTrace();
        }
        
        return bookings;
    }
    
    protected LinkedList<Integer> findBookedSeats(String busId){
        String busQuery = "SELECT * FROM booking WHERE booked_bus='"+ busId +"'";
        Statement stmt = null;
        LinkedList<Integer> bookedSeats = new LinkedList<>();
        
        try{
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(busQuery);
            
            while (rs.next()) {
                int bus_seat = rs.getInt("bus_seats");
                bookedSeats.push(bus_seat);                
            }
            
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return bookedSeats;
    }

}