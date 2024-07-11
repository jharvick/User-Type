package com.example.CarDealership;

import com.example.CarDealership.Models.Vehicle;
import com.example.CarDealership.Repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class CarDealershipApplication implements CommandLineRunner {

	@Autowired
	private VehicleRepository vehicleRepository;

	public static void main(String[] args) {
		SpringApplication.run(CarDealershipApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Welcome to the Car Dealership Application!");

		boolean running = true;
		while (running) {
			System.out.println("\nMenu:");
			System.out.println("1. List all vehicles");
			System.out.println("2. Search vehicles by price range");
			System.out.println("3. Search vehicles by make and model");
			System.out.println("4. Exit");
			System.out.print("Enter your choice: ");
			int choice = scanner.nextInt();
			scanner.nextLine(); // Consume newline left-over

			switch (choice) {
				case 1:
					listAllVehicles();
					break;
				case 2:
					searchByPriceRange(scanner);
					break;
				case 3:
					searchByMakeAndModel(scanner);
					break;
				case 4:
					running = false;
					break;
				default:
					System.out.println("Invalid choice. Please try again.");
			}
		}

		System.out.println("Thank you for using the Car Dealership Application!");
	}

	private void listAllVehicles() {
		List<Vehicle> vehicleList = vehicleRepository.getAllVehicles();
		System.out.println("Vehicles:");
		vehicleList.forEach(System.out::println);
	}

	private void searchByPriceRange(Scanner scanner) {
		System.out.print("Enter minimum price: ");
		double minPrice = scanner.nextDouble();
		System.out.print("Enter maximum price: ");
		double maxPrice = scanner.nextDouble();

		List<Vehicle> vehicles = vehicleRepository.findVehiclesByPriceRange(minPrice, maxPrice);

		System.out.println("Vehicles between $" + minPrice + " and $" + maxPrice + ":");
		vehicles.forEach(System.out::println);
	}

	private void searchByMakeAndModel(Scanner scanner) {
		System.out.print("Enter make: ");
		String make = scanner.nextLine();
		System.out.print("Enter model: ");
		String model = scanner.nextLine();

		List<Vehicle> vehicles = vehicleRepository.findVehiclesByMakeAndModel(make, model);

		System.out.println("Vehicles with make " + make + " and model " + model + ":");
		vehicles.forEach(System.out::println);
	}
}