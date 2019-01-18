package com.mycompany.a2;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Label;

public class ScoreView extends Container implements Observer {
	
	private int time;
	private int livesLeft;
	private int playerFuelRemaining;
	private int playerDamageLevel;
	private int highestPylonReached;
	private boolean soundBoolean;
	private String sound;
	
	private Label timeLabel;
	private Label timeLabelValue;
	private Label livesLeftLabel;
	private Label livesLeftLabelValue;
	private Label playerFuelRemainingLabel;
	private Label playerFuelRemainingLabelValue;
	private Label playerDamageLevelLabel;
	private Label playerDamageLevelLabelValue;
	private Label highestPylonReachedLabel;
	private Label highestPylonReachedLabelValue;
	private Label soundLabel;
	private Label soundLabelValue;
	
	public ScoreView() {
		
		//Initialize starting stats. Does not call resetLabelText() because the scoreView will get instantiated first before GameWorld
		//Which Holds all the data. 
		this.time = 0;
		this.livesLeft = 3;
		this.playerFuelRemaining = 35;
		this.playerDamageLevel = 0;
		//this.highestPylonReached = 0;
		this.sound = "OFF";
		
		//Creates Label and Label Values
		timeLabel = new Label("Time:");
		timeLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		timeLabelValue = new Label("" + this.time);
		timeLabelValue.getAllStyles().setFgColor(ColorUtil.BLUE);
		livesLeftLabel = new Label("Lives Left:");
		livesLeftLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		livesLeftLabelValue = new Label("" + this.livesLeft);
		livesLeftLabelValue.getAllStyles().setFgColor(ColorUtil.BLUE);
		playerFuelRemainingLabel = new Label("Player Fuel Remaining:");
		playerFuelRemainingLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		playerFuelRemainingLabelValue = new Label("" + this.playerFuelRemaining);
		playerFuelRemainingLabelValue.getAllStyles().setFgColor(ColorUtil.BLUE);
		playerDamageLevelLabel = new Label("Player Damage Level:");
		playerDamageLevelLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		playerDamageLevelLabelValue = new Label("" + this.playerDamageLevel);
		playerDamageLevelLabelValue.getAllStyles().setFgColor(ColorUtil.BLUE);
		highestPylonReachedLabel = new Label("Highest Pylon Reached:");
		highestPylonReachedLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		highestPylonReachedLabelValue = new Label("" + this.highestPylonReached);
		highestPylonReachedLabelValue.getAllStyles().setFgColor(ColorUtil.BLUE);
		soundLabel = new Label("Sound:");
		soundLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		soundLabelValue = new Label("" + this.sound);
		soundLabelValue.getAllStyles().setFgColor(ColorUtil.BLUE);
		
		//Adds label/values to the component
		this.add(timeLabel);
		this.add(timeLabelValue);
		this.add(livesLeftLabel);
		this.add(livesLeftLabelValue);
		this.add(playerFuelRemainingLabel);
		this.add(playerFuelRemainingLabelValue);
		this.add(playerDamageLevelLabel);
		this.add(playerDamageLevelLabelValue);
		this.add(highestPylonReachedLabel);
		this.add(highestPylonReachedLabelValue);
		this.add(soundLabel);
		this.add(soundLabelValue);
	}
	
	//Updates ScoreView's data with fresh changed data
	
	public void update(Observable observable, Object data) {
		
		GameWorld gameWorldReference = (GameWorld)observable;
		
		this.time = gameWorldReference.getGameClock();
		this.livesLeft = gameWorldReference.getGameLives();
		this.playerFuelRemaining = gameWorldReference.getFuelRemaining();
		this.playerDamageLevel = gameWorldReference.getCarDamageLevel();
		this.highestPylonReached = gameWorldReference.getHighestPylonReached();
		
		if (soundBoolean == true) {
			this.sound = "ON";
		} else {
			this.sound = "OFF";
		}
		
		resetLabelText();
	}
	
	private void resetLabelText() {
		this.timeLabelValue.setText("" + this.time);
		this.livesLeftLabelValue.setText("" + this.livesLeft);
		this.playerFuelRemainingLabelValue.setText("" + this.playerFuelRemaining);
		this.playerDamageLevelLabelValue.setText("" + this.playerDamageLevel);
		this.highestPylonReachedLabelValue.setText("" + this.highestPylonReached);
	}

}
