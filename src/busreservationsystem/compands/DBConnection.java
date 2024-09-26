package busreservationsystem.compands;

import busreservationsystem.Bus;
import busreservationsystem.Customer;
import busreservationsystem.compands.AVLTree;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

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
    
    private boolean createDatabase() {
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
                    + "bus_id SERIAL PRIMARY KEY, "
                    + "bus_num_plate VARCHAR(100) NOT NULL, "
                    + "start_point VARCHAR(200) NOT NULL, "
                    + "end_point VARCHAR(200) NOT NULL, "
                    + "start_time TIME NOT NULL, "
                    + "seat_fare REAL NOT NULL, "
                    + "bus_seats INT NOT NULL"
                    + ")";
        return executeQuery(createTableQuery);
    }
    
    protected boolean createCustomerTable() {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS customers ("
                    + "cust_id SERIAL PRIMARY KEY, "
                    + "cust_name VARCHAR(100) NOT NULL, "
                    + "cust_phone_num VARCHAR(200) NOT NULL, "
                    + "cust_email VARCHAR(200) NOT NULL, "
                    + "cust_city VARCHAR(100) NOT NULL, "
                    + "cust_age INT NOT NULL, "
                    + "seat_nums integer[3]"
                    + ")";
        
        return executeQuery(createTableQuery);
    }
    
    protected boolean createBookingTable() {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS booking ("
                    + "booking_id SERIAL PRIMARY KEY, "
                    + "booked_bus SERIAL, "
                    + "booked_customer SERIAL, "
                    + "CONSTRAINT fk_buses FOREIGN KEY(booked_bus) REFERENCES buses(bus_id),"
                    + "CONSTRAINT fk_customer FOREIGN KEY(booked_customer) REFERENCES customers(cust_id)"
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
                Bus bus = new Bus(
                    rs.getString("bus_num_plate"),
                    rs.getString("start_point"),
                    rs.getString("end_point"),
                    rs.getString("start_time"),
                    rs.getInt("bus_seats"),
                    rs.getFloat("seat_fare")
                );
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
            
            String getAllSQL = "SELECT * FROM buses";
            ResultSet rs = stmt.executeQuery(getAllSQL);
            
            while (rs.next()) {
                Customer customer = new Customer(
                        rs.getString("cust_name"),
                        rs.getString("cust_phone_num"),
                        rs.getString("cust_email"),
                        rs.getString("cust_city"),
                        rs.getInt("cust_age")
                );
                customers.insert(customer);
            }
            
        }catch (SQLException e) {
            e.printStackTrace();
        }
        
        return customers;
    }
    
}

class Main {
    
    public static void main(String [] args) {
        DBConnection conn = new DBConnection();
        
        conn.createBusTable();
        conn.createCustomerTable();
        conn.createBookingTable();
    }
    
}
