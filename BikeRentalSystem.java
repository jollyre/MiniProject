package com.bikerentalsystem;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.userauthentication.UserAuthentication;

public class BikeRentalSystem {
    private static Map<String, Bike> bikeInventory = new HashMap<>();
    private static Map<String, String> rentedBikes = new HashMap<>();

    static {
        // Pre-populate bike inventory
        bikeInventory.put("B001", new Bike("B001", "Mountain Bike"));
        bikeInventory.put("B002", new Bike("B002", "City Bike"));
        bikeInventory.put("B003", new Bike("B003", "Road Bike"));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // User authentication
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();

        if (UserAuthentication.authenticateUser(username, password)) {
            System.out.println("Authentication successful!");
            displayMenu();

            int choice;
            do {
                System.out.println("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1:
                        addNewBike();
                        break;
                    case 2:
                        updateBikeList();
                        break;
                    case 3:
                        viewBikeList();
                        break;
                    case 4:
                        searchBikeDetails();
                        break;
                    case 5:
                        rentBike();
                        break;
                    case 6:
                        viewRentedBikeList();
                        break;
                    case 7:
                        addCustomerName();
                        break;
                    case 8:
                        displayCustomers();
                        break;
                    case 9:
                        System.out.println("Exiting the system. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } while (choice != 9);

        } else {
            System.out.println("Authentication failed. Exiting the system.");
        }

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\nBike Rental System Menu:");
        System.out.println("1. Add New Bike");
        System.out.println("2. Update Bike List");
        System.out.println("3. View Bike List");
        System.out.println("4. Search Bike Details");
        System.out.println("5. Rent Bike");
        System.out.println("6. View Rented Bike List");
        System.out.println("7. Add Customer Name");
        System.out.println("8. Display Customers");
        System.out.println("9. Exit");
    }

    private static void addNewBike() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Bike ID:");
        String bikeId = scanner.nextLine();
        System.out.println("Enter Bike Name:");
        String bikeName = scanner.nextLine();

        if (!bikeInventory.containsKey(bikeId)) {
            bikeInventory.put(bikeId, new Bike(bikeId, bikeName));
            System.out.println("New bike added successfully!");
        } else {
            System.out.println("Bike ID already exists. Try again with a different ID.");
        }
    }

    private static void updateBikeList() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Bike ID to update:");
        String bikeId = scanner.nextLine();

        if (bikeInventory.containsKey(bikeId)) {
            Bike bike = bikeInventory.get(bikeId);
            System.out.println("Enter new Bike Name:");
            String newBikeName = scanner.nextLine();
            bikeInventory.get(bikeId).returnBike();
            bikeInventory.put(bikeId, new Bike(bikeId, newBikeName));
            System.out.println("Bike details updated successfully!");
        } else {
            System.out.println("Bike ID not found. Please enter a valid ID.");
        }
    }

    private static void viewBikeList() {
        System.out.println("\nBike List:");
        for (Map.Entry<String, Bike> entry : bikeInventory.entrySet()) {
            Bike bike = entry.getValue();
            System.out.println("Bike ID: " + bike.getBikeId() + ", Bike Name: " + bike.getBikeName() +
                    ", Available: " + !bike.isRented() +
                    (bike.isRented() ? ", Rented by: " + bike.getCustomerName() : ""));
        }
    }

    private static void searchBikeDetails() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Bike ID to search:");
        String bikeId = scanner.nextLine();

        if (bikeInventory.containsKey(bikeId)) {
            Bike bike = bikeInventory.get(bikeId);
            System.out.println("Bike ID: " + bike.getBikeId() + ", Bike Name: " + bike.getBikeName() +
                    ", Available: " + !bike.isRented() +
                    (bike.isRented() ? ", Rented by: " + bike.getCustomerName() : ""));
        } else {
            System.out.println("Bike ID not found. Please enter a valid ID.");
        }
    }

    private static void rentBike() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Bike ID to rent:");
        String bikeId = scanner.nextLine();

        if (bikeInventory.containsKey(bikeId)) {
            Bike bike = bikeInventory.get(bikeId);
            if (!bike.isRented()) {
                System.out.println("Enter Customer Name:");
                String customerName = scanner.nextLine();
                bike.rentBike(customerName);
                rentedBikes.put(bikeId, customerName);
                System.out.println("Bike rented successfully!");
            } else {
                System.out.println("Bike is already rented. Choose another bike.");
            }
        } else {
            System.out.println("Bike ID not found. Please enter a valid ID.");
        }
    }

    private static void viewRentedBikeList() {
        System.out.println("\nRented Bike List:");
        for (Map.Entry<String, String> entry : rentedBikes.entrySet()) {
            String bikeId = entry.getKey();
            String customerName = entry.getValue();
            System.out.println("Bike ID: " + bikeId + ", Rented by: " + customerName);
        }
    }

    private static void addCustomerName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Customer Name:");
        String customerName = scanner.nextLine();
        rentedBikes.put("CUSTOMER", customerName);
        System.out.println("Customer name added successfully!");
    }

    private static void displayCustomers() {
        System.out.println("\nCustomers:");
        for (String customerName : rentedBikes.values()) {
            System.out.println("Customer: " + customerName);
        }
    }
}