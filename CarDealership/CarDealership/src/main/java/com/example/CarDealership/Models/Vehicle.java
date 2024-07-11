package com.example.CarDealership.Models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {

    private int vehicle_id;
    private String vin;
    private String make;
    private String model;
    private int year;
    private String color;
    private int odometer;
    private double price;
    private boolean sold;
}

