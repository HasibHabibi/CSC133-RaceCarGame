package com.mycompany.a2;


public class GameObject {
	
	private int size;
	//Instantiates the two main field objects of the GameObject.
	private Location location = new Location();
	private ObjectColor color = new ObjectColor(0, 0, 0);
	private GameWorld gw;
	
	public GameObject() {
		
	}
	
	
	public int getSize() {
		return this.size;
	}
	
	protected void setSize(int size) {
		this.size = size;
	}
	
	public Location getLocation() {
		return this.location;
	}
	
	protected void setLocation(Location location) {
		this.location.setX(location.getX());
		this.location.setY(location.getY());
	};
	
	public ObjectColor getColor() {
		return this.color;
	}
	
	protected void setColor(ObjectColor color) {
		this.color = color;
	}

	
}
