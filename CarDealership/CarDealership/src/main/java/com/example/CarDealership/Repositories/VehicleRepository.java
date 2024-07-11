package com.example.CarDealership.Repositories;

import com.example.CarDealership.Models.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.awt.image.VolatileImage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class VehicleRepository {

    @Autowired
    private DataSource dataSource;

    public List<Vehicle> getAllVehicles(){
        //write your sql query
        //open a connection to the db
        //prepare the query to be sent to sql
        //execute the query and get back a result set
        //loop through each row in the result set
        //grab data column by column and put it into a new Java object
        //put it in a list
        //at the end of the loop, return the list
        String query = "SELECT * FROM vehicles";
        List<Vehicle> vehicles = new ArrayList<>();

        //try-with
        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery()){

            while(rs.next()){
                //grab the data from the columns
                int vehicle_id = rs.getInt("vehicle_id");
                String vin = rs.getString("VIN");
                String make = rs.getString("make");
                String model = rs.getString("model");
                int year = rs.getInt("year");
                String color = rs.getString("color");
                int odometer = rs.getInt("odometer");
                double price = rs.getDouble("price");
                boolean sold = rs.getBoolean("sold");
                Vehicle v = new Vehicle(vehicle_id, vin, make, model, year, color, odometer, price, sold);
                vehicles.add(v);
            }
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }

        return vehicles;
    }

    // Method to search vehicles by price range
    public List<Vehicle> findVehiclesByPriceRange(double minPrice, double maxPrice) {
        String query = "SELECT * FROM vehicles WHERE price BETWEEN ? AND ?";
        List<Vehicle> vehicles = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setDouble(1, minPrice);
            ps.setDouble(2, maxPrice);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Vehicle vehicle = mapRowToVehicle(rs);
                    vehicles.add(vehicle);
                }
            }

        } catch (SQLException ex) {
            // Handle the exception appropriately, e.g., log it
            ex.printStackTrace();
        }

        return vehicles;
    }

    public List<Vehicle> findVehiclesByMakeAndModel(String make, String model) {
        String query = "SELECT * FROM vehicles WHERE make = ? AND model = ?";
        List<Vehicle> vehicles = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, make);
            ps.setString(2, model);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Vehicle vehicle = mapRowToVehicle(rs);
                    vehicles.add(vehicle);
                }
            }

        } catch (SQLException ex) {
            // Handle the exception appropriately, e.g., log it
            ex.printStackTrace();
        }

        return vehicles;
    }

    private Vehicle mapRowToVehicle(ResultSet rs) throws SQLException {
        int vehicle_id = rs.getInt("vehicle_id");
        String vin = rs.getString("VIN");
        String make = rs.getString("make");
        String model = rs.getString("model");
        int year = rs.getInt("year");
        String color = rs.getString("color");
        int odometer = rs.getInt("odometer");
        double price = rs.getDouble("price");
        boolean sold = rs.getBoolean("sold");

        Vehicle vehicle = new Vehicle(vehicle_id, vin, make, model, year, color, odometer, price, sold);
        return vehicle;
    }
}


