package com.mycompany.a2;
//Will check if objects are out of bounds(map)

public class GameHelper {
		

	public static Location checkOutOfBounds(Location newLocation) {
		double newX = newLocation.getX();
		double newY = newLocation.getY();
		
		if (newX > 1608.0) {
			newX = 1608.0;
		} else if (newX < 0.0) {
			newX = 0.0;
		}
		
		if (newY > 1359.0) {
			newY = 1359.0;
		} else if (newY < 0.0) {
			newY = 0.0;
		}
		
		newLocation.setX(newX);
		newLocation.setY(newY);
		
		return newLocation;
	}
	
	public static int checkSpeedAgainstFactors(int speed, int maxSpeed, int fuelLevel) {
		if (speed > maxSpeed) {
			System.out.println("max speed has been reached.");
			return maxSpeed;
		}
		
		if (fuelLevel == 0) {
			System.out.println("Fuel level has reached 0. Speed has returned to 0");
			return 0;
		}
		//Shows the speed is fine and accurate after the check.
		return speed;
	}
}
