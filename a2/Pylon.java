package com.mycompany.a2;



public class Pylon extends NonMovableObjects {
	private int sequenceNumber;
	 
	public Pylon(int sequenceNumber, double x, double y) {
		//The Pylon size(square length) will be 10x10. 
		super.setSize(40);
		//When Pylon is instantiated, the color blue will be set.
		super.setColor(new ObjectColor(0, 0, 255));
		//This will set Pylon the sequenceNumber
		setPylonSequenceNumber(sequenceNumber);
		
		Location pylonLocation = new Location(x, y);
		super.setLocation(pylonLocation);
	}
	
	public int getSize() {
		return super.getSize();
	}
	
	public int getPylonSequenceNumber() {
		return this.sequenceNumber;
	}
	
	private void setPylonSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	
	//Overrides toString() method to make sure that the object will print out with good format.
	@Override
	public String toString() {
		Location pylonLocation = super.getLocation();
		ObjectColor pylonColor = super.getColor();
		return "Pylon: " +
			   "loc=" + pylonLocation.getX() + ","+ pylonLocation.getY() + 
			   " color=[" + pylonColor.getR() + ", " + pylonColor.getG() + ", " + pylonColor.getB() + "] " +
			   " size=" + super.getSize() +
			   " seqNum=" + this.sequenceNumber;
	}
	
	
	//Method does not do anything to avoid the object from changing size.
	@Override
	protected void setSize(int size) {}
	//Method does not do anything to avoid the object from setting a new color.
	@Override
	protected void setColor(ObjectColor color) {}
	//Override setLocation so the position is fixed.
	@Override
	protected void setLocation(Location location) {}
	
}
