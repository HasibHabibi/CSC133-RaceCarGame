package com.mycompany.a2;
import java.util.Random;


public class Bird extends MovableObject {
	
	//Java object to create random numbers
	private Random rand = new Random();
	
	public Bird() {
		//Sets speed of bird random from 5-10.
		super.setSpeed(rand.nextInt(11)+4);
		//Sets color of bird
		super.setColor(new ObjectColor(255,0,255));
		//Sets heading of bird(0-359)
		super.setHeading(rand.nextInt(360));
		//Sets size of bird
		super.setSize(15);
		
		//Sets location of bird.
		Location randomLocation = new Location((double)rand.nextInt(1608), (double)rand.nextInt(1298));
		super.setLocation(randomLocation);
	}
	
	public int getSize() {
		return super.getSize();
	}
	
	/**
	 * This overrides the updateHeading() method to make sure that the 
	 * bird's heading is updated by increments or decrements of 5.
	 * The "subtractOrAdd" variable represents a 0/1 bit to see if
	 * the bird's heading should add by 5 or subtract by 5.
	 */
	@Override
	public void fixHeading() {
		int subtractOrAdd = rand.nextInt(2);
		
		if (subtractOrAdd == 1) {
			super.setHeading(super.getHeading() + 5);
		} else {
			super.setHeading(super.getHeading() - 5);
		}
	}
	
	//Method Overrides the toString() to print out the object with better format.
	@Override
	public String toString() {
		Location birdLocation = super.getLocation();
		ObjectColor birdColor = super.getColor();
		
		return "Bird: " +
				"loc=" + Math.round(birdLocation.getX()*10.0)/10.0 + ", " + Math.round(birdLocation.getY()*10.0)/10.0 + " " +
				"color=[" + birdColor.getR() + ", " + birdColor.getG() + ", " + birdColor.getB() + "] " +
				"heading=" + this.getHeading() + " " +
				"speed=" + this.getSpeed() + " " +
				"size=" + super.getSize();
	}
	
	
	//Method does not do anything to avoid Bird from changing heading. 
	@Override
	protected void setHeading(int heading) {}
	
	//The purpose of this is to make sure no one can change the size of the object. 
	@Override
	protected void setSize(int size) {}
		
	//The purpose of this is to make sure no one can change the color of the object.
	@Override
	protected void setColor(ObjectColor color) {}
		
	//Method does not do anything to avoid the object from changing location.
	@Override
	protected void setLocation(Location location) {}
	
}
