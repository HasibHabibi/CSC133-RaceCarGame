package com.mycompany.a2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Car extends MovableObject implements ISteerable, ICollider {
	
	private List<GameObject> objectsCollidedWith = new ArrayList<GameObject>();
	private int steeringDirection;
	private int maximumSpeed;
	private int fuelLevel;
	private int fuelConsumption;
	private int damageLevel;
	private int damageLevelMax;
	private int lastPylonReached;
	private boolean validPlayer;
	protected static INpcStrategy newStrategy;
	private Random rand = new Random();
	
	public Car() {
		//Sets Car size to 40x40
		super.setSize(40);
		//Sets Car color to Red
		super.setColor(new ObjectColor(255,0,0));
		//Set Location of Car 
		Location randomLocation = new Location((double)rand.nextInt(1608), (double)rand.nextInt(1298));
		super.setLocation(randomLocation);
		//Sets the last pylon reached, which is Pylon #1
		this.lastPylonReached = 1;
		//Sets initial speed of Car to 0.
		super.setSpeed(0);
		//Sets initial car heading to 0.
		super.setHeading(0);
		//Sets initial steering direction of car to 0.
		this.steeringDirection = 0;
		//Sets maximum speed that a car can accelerate too
		this.maximumSpeed = 60;
		//Sets initial fuel level for the car
		this.fuelLevel = 35;
		//Sets the consumption rate of fuel for the car
		this.fuelConsumption = 1;
		//Sets the initial damage level of the car, which is 0.
		this.damageLevel = 0;
		//Sets the max amount of damage a car can take.
		setDamageLevelMax(10);
	}
	
	//Getter and Setters
	/*public void setSound(boolean sound) {
		this.sound = sound;
	}*/
	
	public boolean isPlayer(){
		return validPlayer;
	}
	
	public boolean isNPC(){
		return !validPlayer;
	}
	
	public static INpcStrategy getStrategy(){
		return newStrategy;
	}
	
	public void setStrategy(INpcStrategy c){
		newStrategy = c;
	}
	
	public static void invokeStrategy(){
		newStrategy.applyStrategy();
	}
	
	public int getDamageLevelMax() {
		return this.damageLevelMax;
	}
	
	public void setDamageLevelMax(int damamgeLvlMax) {
		this.damageLevelMax = damamgeLvlMax;
	}
	
	public int getMaximumSpeed() {
		return this.maximumSpeed;
	}
	
	public int getFuelLevel() {
		return this.fuelLevel;
	}
	
	public void setFuelLevel(int fuelLevel) {
		this.fuelLevel = fuelLevel;
	}
	
	public int getLastPylonReached() {
		return this.lastPylonReached;
	}
	
	public void setSteeringDirection(int steeringDirection) {
		this.steeringDirection = steeringDirection;
	}
	
	public int getSteeringDirection() {
		return this.steeringDirection;
	}
	
	public int getDamageLevel() {
		return this.damageLevel;
	}
	
	//Updates the FuelLevel by decrementing by the fuel consumption rate.
	public void updateFuelLevel() {
		this.fuelLevel = this.fuelLevel - this.fuelConsumption;
	}
	

	
	public void pylonReached(int pylonNumber) {
		if (pylonNumber - this.lastPylonReached == 1) {
			this.lastPylonReached = pylonNumber;
			System.out.println("Last Pylon Reach Updated to #" + pylonNumber);
		}
	}
	

	
	@Override
	public void fixHeading() {
		super.setHeading(super.getHeading() + this.steeringDirection);
	}
	

	
	public void steerLeft() {
		if (this.steeringDirection > -41) {
			setSteeringDirection(this.steeringDirection - 5);
		}
	}
	
	public void steerRight() {
		if (this.steeringDirection < 41) {
			setSteeringDirection(this.steeringDirection + 5);
		}
	}
	

	
	public void tookDamage(int damage) {
		this.damageLevel = this.damageLevel + damage;
		if (this.damageLevel > this.damageLevelMax) {
			this.damageLevel = this.damageLevelMax;
		}
		this.maximumSpeed = 60 - (this.damageLevel * 6);
		setSpeed(super.getSpeed(), this.maximumSpeed, this.fuelLevel);
	}
	

	public void accelerate() {
		if (super.getSpeed() < maximumSpeed) {
			setSpeed(super.getSpeed() + 4, this.maximumSpeed, this.fuelLevel);
		} else {
			System.out.println("Car is already at max speed");
		}
	}
	

	
	public void brake() {
		if (super.getSpeed() > 0) {
			super.setSpeed(super.getSpeed() - 4);
		} else {
			System.out.println("Car is already at a stop.");
		}
	}
	

	private void setSpeed(int speed, int maxSpeed, int fuelLevel) {
		super.setSpeed(GameHelper.checkSpeedAgainstFactors(speed, maxSpeed, fuelLevel)); 
	}
	//Method does nothing to make sure no can "directly" access speed
	@Override
	protected void setSpeed(int speed) {}
	//Method does nothing to make sure no can "directly" access heading
	@Override
	protected void setHeading(int heading) {}
	
	
	//Override toString() to print out Car with a better format.
	@Override
	public String toString() {
		Location carLocation = super.getLocation();
		ObjectColor carColor = super.getColor();
		
		String carString =  "Car: " +
				"loc=" + Math.round(carLocation.getX()*10.0)/10.0 + ", " + Math.round(carLocation.getY()*10.0)/10.0 + " " +
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
	
	
	//The purpose of this is to make sure no one can change the size of the object. 
	@Override
	protected void setSize(int size) {}
		
	
	//Removes objects that are not colliding with the main object anymore
	protected void removeCollidedObject() {
		for (int i = 0; i < this.objectsCollidedWith.size(); i++) {
			if (!collidesWith(this.objectsCollidedWith.get(i))) {
				this.objectsCollidedWith.remove(i);
			}
		}
	}
	
	//Adds the object to the main object's collection to avoid further repetitive damage.
	protected void addCollidedObject(GameObject otherObject) {
		this.objectsCollidedWith.add(otherObject);
	}
	
	//Checks to see if the main object contains the object that it has collided with.
	protected boolean hasNotCollidedWith(GameObject otherObject) {
		if (!this.objectsCollidedWith.contains(otherObject)) {
			return true;
		} else {
			return false;
		}
	}
	
	//Handles the collision calculations
	
	public void handleCollision(GameObject otherObject, GameWorld gw) {
		if (otherObject instanceof Bird) {
			this.tookDamage(1);
			System.out.println("bird has been hit");
		} else if (otherObject instanceof NonPlayerCar) {
			this.tookDamage(2);
			((NonPlayerCar) otherObject).addCollidedObject(this);
			System.out.println("NPC has been hit");
		} else if (otherObject instanceof FuelCan) {
			System.out.println("Fuel Can has been hit");
			gotFuelCan(otherObject);
		} else if (otherObject instanceof Pylon) {
			System.out.println("Pylon has been hit");
			overPassPylon(((Pylon) otherObject).getPylonSequenceNumber());
		}
		this.addCollidedObject(otherObject);
	}
	
	//Checks to see if two objects collide

	public boolean collidesWith(GameObject otherObject) {
		//Gets location of both objects
		Location carLocation = this.getLocation();
		Location otherObjectLocation = otherObject.getLocation();
		//Runs a check if the two objects overlap.
		if ((objectsOverlapping(carLocation, (double)this.getSize(), otherObjectLocation, (double)otherObject.getSize()))
		|| (objectsOverlapping(otherObjectLocation, (double)otherObject.getSize(), carLocation, (double)this.getSize()))) {
			return true;
		}
		return false;
	}
	
	/*
	 * This function checks to see if the two objects overlap. It does this by creating a square area for the object
	 * and then it will check to see if the square's corners overlap the other square.
	 */
	protected boolean objectsOverlapping(Location mainObject, double mainObjectSize, Location otherObjectLocation, double otherObjectSize) {
		//This will create a square around the mainObject
		double mainObjectTop = mainObject.getY() - mainObjectSize;
		double mainObjectBottom = mainObject.getY() + mainObjectSize;
		double mainObjectLeft = mainObject.getX() - mainObjectSize;
		double mainObjectRight = mainObject.getX() + mainObjectSize;
		
		//This runs a check on all four corners to see if any of the corners are within the other object.
		if ((checkToSeeIfCornerInsideOfBox(mainObjectTop, mainObjectBottom, mainObjectLeft, mainObjectRight, otherObjectLocation.getX()+otherObjectSize, otherObjectLocation.getY()+otherObjectSize))
		|| (checkToSeeIfCornerInsideOfBox(mainObjectTop, mainObjectBottom, mainObjectLeft, mainObjectRight, otherObjectLocation.getX()-otherObjectSize, otherObjectLocation.getY()+otherObjectSize))
		|| (checkToSeeIfCornerInsideOfBox(mainObjectTop, mainObjectBottom, mainObjectLeft, mainObjectRight, otherObjectLocation.getX()+otherObjectSize, otherObjectLocation.getY()-otherObjectSize))
		|| (checkToSeeIfCornerInsideOfBox(mainObjectTop, mainObjectBottom, mainObjectLeft, mainObjectRight, otherObjectLocation.getX()-otherObjectSize, otherObjectLocation.getY()-otherObjectSize)))
		{
			return true;
		}
		return false;
	}
	
	//Compares the second object's corner to the main object's box to see if the specific corner is inside the box.
	//This will decide if two objects are touch or not.
	protected boolean checkToSeeIfCornerInsideOfBox(double top, double bot, double left, double right, double x, double y) {
		if (left <= x && x <= right && bot >= y && y >= top) {
			return true;
		}
		return false;
	}
	

	protected void gotFuelCan(GameObject gameObject) {
		int leftOverFuelCapacity, totalFuelLevel;
		
		//Gets the first Fuel Can for now.
		FuelCan fuelCan = (FuelCan)gameObject;
		
		System.out.println("+" + fuelCan.getCapacity() + " Fuel!!!");
		
		//The total fuel from adding both the fuel can and the current fuel level of the car
		totalFuelLevel = this.getFuelLevel() + fuelCan.getCapacity();
		
		//If the car fuel level is over 100 then it will subtract the rest and give it to the fuel can as "leftOverFuelCapacity"
		//It will then set the total fuel level back to 100
		//If it's not over 100 then it will just take all of the fuel from fuel can and create another fuel can.
		if (totalFuelLevel > 100) {
			leftOverFuelCapacity = totalFuelLevel % 100;
			fuelCan.setCapacity(leftOverFuelCapacity);
			totalFuelLevel = totalFuelLevel - leftOverFuelCapacity;
		} else {
			leftOverFuelCapacity = 0;
			fuelCan.setCapacity(0);

		}
		this.setFuelLevel(totalFuelLevel);
	}
	
	protected void overPassPylon(int pylonNumber) {
		System.out.println("Existing Pylon #" + this.getLastPylonReached());
		System.out.println("Over Passed Pylon #" +  pylonNumber);
		this.pylonReached(pylonNumber);
	}
}