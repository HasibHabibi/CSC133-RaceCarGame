package com.mycompany.a2;

import java.util.Observable;
import java.util.Observer;

import com.codename1.ui.Container;
import com.mycompany.a2.GameObjectCollection.Iterator;

public class MapView extends Container implements Observer {
	
	private GameWorld gw;
	
	public MapView(GameWorld gw) {
		this.gw = gw;
	}
	
	
	//Iterates through GameObjectCollection and executes the toString
	public void outputMap(GameObjectCollection gameObjects) {
		Iterator gameIterator = gameObjects.getIterator();
		while (gameIterator.hasNext()) {
			System.out.println(gameIterator.getNext().toString());
		}
	}


	public void update(Observable observable, Object data) {
		// TODO Auto-generated method stub
		
	}
}
