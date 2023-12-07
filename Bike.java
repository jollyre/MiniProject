package com.bikerentalsystem;

public class Bike extends BikeRentalSystem {
	 private String bikeId;
	    private String bikeName;
	    private boolean isRented;
	    private String customerName; // Added for storing customer names

	    public Bike(String bikeId, String bikeName) {
	        this.bikeId = bikeId;
	        this.bikeName = bikeName;
	        this.isRented = false;
	        this.customerName = "";
	    }

	    public String getBikeId() {
	        return bikeId;
	    }

	    public String getBikeName() {
	        return bikeName;
	    }

	    public boolean isRented() {
	        return isRented;
	    }

	    public String getCustomerName() {
	        return customerName;
	    }

	    public void rentBike(String customerName) {
	        isRented = true;
	        this.customerName = customerName;
	    }

	    public void returnBike() {
	        isRented = false;
	        this.customerName = "";
	    }

}
