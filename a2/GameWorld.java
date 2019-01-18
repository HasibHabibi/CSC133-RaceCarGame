package com.mycompany.a2;
import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.mycompany.a2.GameObjectCollection.Iterator;

public class GameWorld extends Observable {
	//Collection of game objects
	GameObjectCollection gameObjects = new GameObjectCollection();
	
	private int gameClock = 0;
	private int gameLives = 3;
	private boolean sound = false;
	
	private double mapViewX;
	private double mapViewY;
	
	
	private Car car; 
	private NonPlayerCar npc; 
	
	public void init() {

		gameObjects.addGameObject(new Car());
		createPylons(gameObjects);
		createBirds(gameObjects);
		createFuelCans(gameObjects);
		createNonPlayerCars(gameObjects);
		
		//Gets the Player's car for reference
		car = (Car)gameObjects.getPlayerCar();
		npc = (NonPlayerCar)gameObjects.getNPC();
		
		
		
		
	}
	
	//Getters/Setters
	public GameObjectCollection getGameObjectCollection() {
		return this.gameObjects;
	}
	
	public int getGameClock() {
		return this.gameClock;
	}
	
	public int getGameLives() {
		return this.gameLives;
	}
	
	public boolean getSoundBoolean() {
		return this.sound;
	}
	
	public int getFuelRemaining() {
		return this.car.getFuelLevel();
	}
	
	public int getCarDamageLevel() {
		return this.car.getDamageLevel();
	}
	
	public int getHighestPylonReached() {
		return this.car.getLastPylonReached();
	}
	
	public void updateMapViewSize(int x, int y) {
		this.mapViewX = (double)x;
		this.mapViewY = (double)y;
	}
	
	

	public void accelerateCar() {
		this.car.accelerate();
		System.out.println("Speed currently at " + car.getSpeed());
	}
	
	/**
	 * Slows the car speed by 3.
	 */
	public void brakeCar() {
		this.car.brake();
		System.out.println("After Braking, Speed currently at " + car.getSpeed());
	}
	
	/**
	 * Turns the wheel left 5 degrees.
	 */
	public void steerWheelLeft() {
		this.car.steerLeft();
		System.out.println("Steering direction is at " + this.car.getSteeringDirection());
	}
	
	/**
	 * Turns the wheel right 5 degrees.
	 */
	public void steerWheelRight(){
		this.car.steerRight();
		System.out.println("Steering direction is at " + this.car.getSteeringDirection());
	}
	
	/**
	 * Applies car damage.
	 * Also applies damage to NPC car. 
	 */
	public void tookDamageByCar() {
		this.car.tookDamage(2);
		System.out.println("Car acquired 2 damage.");
		System.out.println("Your damage level is at " + this.car.getDamageLevel());
		this.npc.tookDamage(2);
		System.out.println("NPC acquired 2 damage.");
		System.out.println("NPC damage level is at " + this.npc.getDamageLevel());
	}
	
	/**
	 * Applies bird damage.
	 */
	public void tookDamageByBird() {
		this.car.tookDamage(1);
		System.out.println("Car acquired 1 damage.");
		System.out.println("Your damage level is at " + this.car.getDamageLevel());
	}

	
	public void overPassedPylon(int pylonNumber) {
		System.out.println("Exisiting Pylon #" + this.car.getLastPylonReached());
		System.out.println("Over Passed Pylon #" +  pylonNumber);
		this.car.pylonReached(pylonNumber);
	}
	
	
	public void gameTick(int timeElapsed) {
		if (this.car.getDamageLevel() < this.car.getDamageLevelMax() && this.car.getFuelLevel() > 0) {
			updateUserCar();
			updateBirds();
			updateMovableObjects(timeElapsed);
			this.gameClock++;
			collideDetection();
			updateCarFuelLevel();
		} else  if (this.gameLives == 0) {
			System.out.println("No lives. No ticks :(");
		} else {
			System.out.println("Your car exploded! Lost a life. Try Again!");
			this.gameLives--;
			gameObjects.resetPlayerCar();
			car = (Car)gameObjects.getPlayerCar();
		}
		
		this.setChanged();
		this.notifyObservers();
	}
	
	protected void gotFuelCan() {
		int leftOverFuelCapacity, totalFuelLevel;
		
		//Gets the first Fuel Can for now.
		FuelCan fuelCan = (FuelCan)gameObjects.getFuelCan();
		
		System.out.println("+" + fuelCan.getCapacity() + " Fuel!");
		
		//The total fuel from adding both the fuel can and the current fuel level of the car
		totalFuelLevel = this.car.getFuelLevel() + fuelCan.getCapacity();
		
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
			System.out.println("Added another fuel can.");
			gameObjects.addGameObject(new FuelCan());
		}
		
		this.car.setFuelLevel(totalFuelLevel);
	}
	
	//Detects for collision
	private void collideDetection() {
		//Two iterators for the nested searching
		Iterator gameObjectIterator = gameObjects.getIterator();
		for (int i = 0; i < gameObjects.getSizeOfGameObjectCollection(); i++) {
			GameObject gameObject = gameObjectIterator.getNext();
			if(gameObject != null && gameObject instanceof Car) {
				Car carObject = (Car)gameObject;
				Iterator secondGameObjectIterator = gameObjects.getIterator();
				for (int j = i; j < gameObjects.getSizeOfGameObjectCollection(); j++) {
					GameObject otherObject = secondGameObjectIterator.getNext();
					if (i != j && carObject.collidesWith(otherObject) && carObject.hasNotCollidedWith(otherObject)) {
						carObject.handleCollision(otherObject, this);
					}
					carObject.removeCollidedObject();
				}
			}
		}
	}
	
	/**
	 * Updates the car's heading if the car speed is not 0.
	 */
	private void updateUserCar() {
		if (this.car.getSpeed() != 0) {
			this.car.fixHeading();
		}
	}
	
	/**
	 * Updates the each bird's random heading.
	 */
	private void updateBirds() {
		Iterator gameObjectIterator = gameObjects.getIterator();
		for (int i = 0; i < gameObjects.getSizeOfGameObjectCollection(); i++) {
			GameObject gameObject = gameObjectIterator.getNext();
			if(gameObject != null && gameObject instanceof Bird) {
				Bird bird = (Bird)gameObject;
				bird.fixHeading();
			}
		}
	}
	
	/**
	 * Moves all the movable Objects
	 */
	private void updateMovableObjects(int timeElapsed) {
		Iterator gameObjectIterator = gameObjects.getIterator();
		for (int i = 0; i < gameObjects.getSizeOfGameObjectCollection(); i++) {
			GameObject gameObject = gameObjectIterator.getNext();
			if(gameObject != null && gameObject instanceof MovableObject) {
				MovableObject movableObjects = (MovableObject)gameObject;
				movableObjects.move(timeElapsed);
			}
		}
	}
	
	// go through and change strategies of all NPCs
	public void changeNPCStrategies(){
		IIterator anIterator = gameObjects.getIterator();
		
		Object currentObj = new Object();
		
		while( anIterator.hasNext() ){
			currentObj = anIterator.getNext();
			if( currentObj instanceof Car){ // found a car
				if( ((Car)currentObj).isNPC() ){ // is the car a NPC
					if( Car.getStrategy() instanceof FastTrackStrategy ){
						((Car)currentObj).setStrategy(new EvolutionTrackStrategy(((Car)currentObj), car ));
						System.out.println("DS");
					} else if ( Car.getStrategy() instanceof EvolutionTrackStrategy ){
						((Car)currentObj).setStrategy(new FastTrackStrategy(((Car)currentObj), this ));
						System.out.println("RS");
					}
				}
			}
		}
		notifyObservers();
	}
	
	
	/**
	 * Updates the car's fuel level by subtracting the fuel level by
	 * consumption rate.
	 */
	private void updateCarFuelLevel() {
		this.car.updateFuelLevel();
		System.out.println("Car fuel level updated. Fuel level = " + this.car.getFuelLevel());
	}
	
	
	/**
	 * Creates four "Pylon" objects
	 * Pylon Params
	 * new Pylon(int numberSequence, double X, double Y)
	 */
	
	private void createPylons(GameObjectCollection gameObjects) {
		gameObjects.addGameObject(new Pylon(1, 500.0, 300.0));
		gameObjects.addGameObject(new Pylon(2, 700.0, 500.0));	
		gameObjects.addGameObject(new Pylon(3, 900.0, 800.0));
		gameObjects.addGameObject(new Pylon(4, 1300.0, 1000.0));
	}
	
	//Creates two "Bird" objects
	private void createBirds(GameObjectCollection gameObjects) {
		gameObjects.addGameObject(new Bird());
		gameObjects.addGameObject(new Bird());
	}
	
	//Creates two "FuelCan" objects
	private void createFuelCans(GameObjectCollection gameObjects) {
		gameObjects.addGameObject(new FuelCan());
		gameObjects.addGameObject(new FuelCan());
	}
	
	//Create Three NonPlayerCars NEAR first Pylon locations
	private void createNonPlayerCars(GameObjectCollection gameObjects) {
		gameObjects.addGameObject(new NonPlayerCar(new Location(800.0, 600.0)));
		gameObjects.addGameObject(new NonPlayerCar(new Location(550.0, 450.0)));
		gameObjects.addGameObject(new NonPlayerCar(new Location(1000.0, 1000.0)));
	}
	
}
