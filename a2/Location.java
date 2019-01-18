package com.mycompany.a2;


/**
 * The object for the object's location.
 * It's main purpose is to have the double X/Y coordinates
 * It will also have getters and setters for accessing the data.
 */
public class Location {
	private double x;
	private double y;
	
	public Location() {}
	
	public Location(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX(){
		return this.x;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public double getY(){
		return this.y;
	}
	
	public void setY(double y) {
		this.y = y;
	}
}
