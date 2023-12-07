package com.userauthentication;

import java.util.HashMap;
import java.util.Map;

import com.bikerentalsystem.BikeRentalSystem;

public class UserAuthentication extends BikeRentalSystem {
	 private static Map<String, String> userCredentials = new HashMap<>();

	    static {
	        // Pre-populate user credentials (username, password)
	        userCredentials.put("admin", "admin");
	    }

	    public static boolean authenticateUser(String username, String password) {
	        return userCredentials.containsKey(username) && userCredentials.get(username).equals(password);
	    }

}
