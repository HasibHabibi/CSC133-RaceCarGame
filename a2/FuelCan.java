package com.mycompany.a2;

import java.util.Random;



public class FuelCan extends NonMovableObjects {

	private int capacity;
	
	public FuelCan() {
		//"rand" is the object that will help us generate random numbers
		Random rand = new Random();
		
		//The FuelCan size(square length) will be dependent on random number(10-50). 
		super.setSize(rand.nextInt(61)+40);
		//The FuelCan capacity will be dependent on the its size.
		this.capacity = (int)Math.round(super.getSize() * .75);
		//Sets FuelCan color to green.
		super.setColor(new ObjectColor(0, 255, 0));
		
		Location randomLocation = new Location((double)rand.nextInt(1608), (double)rand.nextInt(1298));
		super.setLocation(randomLocation);
	}
	
	public int getCapacity() {
		return this.capacity;
	}
	
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	//Overrides the toString() method to make sure FuelCan prints out with good format.
	@Override
	public String toString() {
		Location fuelCanLocation = this.getLocation();
		ObjectColor fuelCanColor = this.getColor();
		return "FuelCan: " +
				"loc=" + fuelCanLocation.getX() + ", " + fuelCanLocation.getY() + " " +
				"color=[" + fuelCanColor.getR() + ", " + fuelCanColor.getG() + ", " + fuelCanColor.getB() + "] " +
				"size=" + this.getSize();
	}
	

	
	//Method does not do anything to avoid the object from changing size.
	@Override
	protected void setSize(int size) {}
	//Method does not do anything to avoid the object from setting a new color.
	@Override
	protected void setColor(ObjectColor color) {}
	@Override
	protected void setLocation(Location location) {}
}
