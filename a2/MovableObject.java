package com.mycompany.a2;

//Movable Object Class

public abstract class MovableObject extends GameObject {
	private int heading;
	private int speed;
	
	abstract void fixHeading();
	
	public void move(int timeElapsed) {
		double degree = Math.toRadians(getHeading() + 180);
		double deltaX = (Math.cos(degree) * this.getSpeed())/timeElapsed; 
		double deltaY = (Math.sin(degree) * this.getSpeed())/timeElapsed;
		
		Location oldLocation = super.getLocation();
		Location newLocation = new Location(oldLocation.getX() + deltaX, oldLocation.getY() + deltaY);
		
		newLocation = GameHelper.checkOutOfBounds(newLocation);
		
		super.setLocation(newLocation);
	}
	
	
	protected void setHeading(int heading) {
		if (heading < 0) {
			heading = 360 + heading;
		} else if (heading > 360) {
			heading = heading - 360;
		}
		this.heading = heading;
	}
	
	public int getHeading() {
		return this.heading;
	}
	
	protected void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public int getSpeed() {
		return this.speed;
	}
}
