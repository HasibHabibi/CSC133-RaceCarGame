package com.mycompany.a2;



public class NonPlayerCar extends Car {
	
	
	public NonPlayerCar(Location location) {
		super.setLocation(location);
		super.setDamageLevelMax(16);
		
	}
	
	//Override toString() to print out NPC with a better format.
	@Override
	public String toString() {
		Location carLocation = super.getLocation();
		ObjectColor carColor = super.getColor();
		
		String carString =  "NPC: " +
				"loc=" + carLocation.getX() + ", " + carLocation.getY() + " " +
				"color=[" + carColor.getR() + ", " + carColor.getG() + ", " + carColor.getB() + "] " + 
				"heading=" + this.getHeading() + " " +
				"speed=" + this.getSpeed() + " " +
				"size=" + this.getSize() + " " +
				"maxSpeed=" + this.getMaximumSpeed() + " " +
				"steeringDirection=" + this.getSteeringDirection() + " " + 
				"fuelLevel=" + this.getFuelLevel() + " " +
				"damageLevel=" + this.getDamageLevel(); 
		
		return carString;
	}
	

}
