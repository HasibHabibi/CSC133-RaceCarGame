package com.mycompany.a2;


public class EvolutionTrackStrategy implements INpcStrategy {
	
	private Car myCar;
	private Car targetCar;
	
	public EvolutionTrackStrategy(Car thisCar, Car target){
		myCar = thisCar;
		targetCar = target;
	}
	
	public String toString(){
		return "EvolutionTrack";
	}
	
	public void applyStrategy(){
		// code here to find the given player car and set heading towards it
		
		double dx;
		// change in x and y
		double dy;
		
		dx = targetCar.getLocation().getX() - myCar.getLocation().getX();
		dy = targetCar.getLocation().getY() - myCar.getLocation().getY();
		
		// check for needing to move on axis, this also accounts
		// for checking of division by 0
		
		// is the y val the same?
		if( dy == 0 )
			if( dx < 0 ){
				// target car is directly to the left
				myCar.setHeading(270);
				return;
			}
			else if( dx > 0 ){
				// target car is directly to the right
				myCar.setHeading(90);
				return;
			}
		
		// is x val the same?
		if( dx == 0 )
			if( dy > 0 ){
				// target car is directly above
				myCar.setHeading(0);
				return;
			}else if( dy < 0 ){
				// target car is directly below
				myCar.setHeading(180);
				return;
			}
		
		// target wasn't at a 90 degree interval,
		// need to determine the angle
		
		int angle = (int)Math.toDegrees( Math.tan( (dx/dy) ) );
		
		// got the angle, now figure out which quadrant to put it in
		if( dx > 0 ){ 
			if( dy > 0 ){
				// top right
				myCar.setHeading( Math.abs(angle) );
				return;
			}else if( dy < 0){
				// bottom right
				myCar.setHeading( 180 - angle );
				return;
			}
		}else if( dx < 0 ){
			if( dy > 0){
				// top left
				myCar.setHeading( 360 - angle );
				return;
			}else if( dy < 0){
				// bottom left
				myCar.setHeading( 180 + Math.abs(angle) );
				return;
			}
		}
	}
}
