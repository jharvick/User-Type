-- Drop database if exists
DROP DATABASE IF EXISTS CarDealership;

-- Create database
CREATE DATABASE CarDealership;

-- Switch to CarDealership database
\c CarDealership;

-- Create dealerships table
CREATE TABLE dealerships (
    dealership_id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    address VARCHAR(50) NOT NULL,
    phone VARCHAR(12) NOT NULL
);

-- Create vehicles table
CREATE TABLE vehicles (
    vehicle_id SERIAL PRIMARY KEY,
    VIN VARCHAR(17) NOT NULL,
    make VARCHAR(50),
    model VARCHAR(50),
    year INT,
    color VARCHAR(20),
    odometer INT,
    price DECIMAL(10, 2),
    sold BOOLEAN DEFAULT FALSE
);

-- Create inventory table
CREATE TABLE inventory (
    dealership_id INT,
    vehicle_id INT,
    PRIMARY KEY (dealership_id, vehicle_id),
    FOREIGN KEY (dealership_id) REFERENCES dealerships(dealership_id),
    FOREIGN KEY (vehicle_id) REFERENCES vehicles(vehicle_id)
);

-- Create customers table
CREATE TABLE customers (
    customer_id SERIAL PRIMARY KEY,
    name VARCHAR(50),
    address VARCHAR(100),
    phone VARCHAR(12),
    email VARCHAR(50)
);

-- Create sales_contracts table
CREATE TABLE sales_contracts (
    sale_id SERIAL PRIMARY KEY,
    vehicle_id INT,
    customer_id INT,
    sale_date DATE,
    sale_price DECIMAL(10, 2),
    FOREIGN KEY (vehicle_id) REFERENCES vehicles(vehicle_id),
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);

-- Create financing table (optional)
CREATE TABLE financing (
    sale_id INT PRIMARY KEY,
    rate DECIMAL(5, 2),
    term_length INT,
    monthly_payment DECIMAL(10, 2),
    FOREIGN KEY (sale_id) REFERENCES sales_contracts(sale_id)
);

-- Insert data into dealerships table
INSERT INTO dealerships (name, address, phone) VALUES
('Stonebriar Chevrolet', '123 Main St', '555-1234'),
('Clay Cooley', '456 Elm St', '555-5678'),
('Glenn Polk Autoplex', '123 Broadway Ave', '555-1111'),
('Five Star Ford', '789 Oak St', '555-2222'),
('James Wood Motors', '456 Pine St', '555-3333'),
('Park Place Lexus', '789 Cedar St', '555-4444');

-- Insert data into vehicles table
INSERT INTO vehicles (VIN, make, model, year, color, odometer, price, sold) VALUES
('1HGBH41JXMN109186', 'Honda', 'Civic', 2020, 'Blue', 0, 20000, FALSE),
('1HGCM82633A123456', 'Toyota', 'Camry', 2019, 'Red', 0, 18000, FALSE),
('2G1WF55KX4937383', 'Chevrolet', 'Impala', 2018, 'Silver', 0, 15000, FALSE),
('3FAHP0HA1CR215173', 'Ford', 'Fusion', 2017, 'White', 0, 17000, FALSE),
('JHMCG6694XC012345', 'Honda', 'Accord', 2021, 'Black', 0, 22000, FALSE),
('1G1JC5241Y7308001', 'Chevrolet', 'Cavalier', 2000, 'Green', 100000, 5000, TRUE);


-- Insert data into inventory table
INSERT INTO inventory (dealership_id, vehicle_id) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6);

-- Insert data into customers table
INSERT INTO customers (name, address, phone, email) VALUES
('John Doe', '789 Maple St', '555-7890', 'johndoe@example.com'),
('Jane Smith', '321 Oak St', '555-4321', 'janesmith@example.com'),
('Michael Johnson', '456 Pine St', '555-5678', 'michael@example.com'),
('Emily Davis', '123 Broadway Ave', '555-1234', 'emily@example.com'),
('Christopher Brown', '789 Cedar St', '555-8765', 'chris@example.com'),
('Sarah Wilson', '321 Elm St', '555-9876', 'sarah@example.com');

-- Insert data into sales_contracts table
INSERT INTO sales_contracts (vehicle_id, customer_id, sale_date, sale_price) VALUES
(1, 1, '2023-07-01', 19500),
(2, 2, '2023-07-02', 17500),
(3, 3, '2023-07-03', 16000),
(4, 4, '2023-07-04', 18000),
(5, 5, '2023-07-05', 21000),
(6, 6, '2023-07-06', 4500);

-- Insert data into financing table (optional)
INSERT INTO financing (sale_id, rate, term_length, monthly_payment) VALUES
(1, 3.5, 60, 350.00),
(2, 4.0, 72, 300.00),
(3, 3.8, 48, 400.00),
(4, 4.2, 60, 320.00),
(5, 3.9, 72, 280.00),
(6, 0.0, 0, 0.00);
