package com.mycompany.a2;

import java.util.ArrayList;
import java.util.List;

public class GameObjectCollection implements ICollection {
	private List<GameObject> gameObjects = new ArrayList<GameObject>();
	
	public GameObject getPlayerCar() {
		return this.gameObjects.get(0);
	}
	
	public GameObject getNPC() {
		return this.gameObjects.get(10);
	}
	
	public GameObject getFuelCan() {
		return this.gameObjects.get(7);
	}
	
	public void resetPlayerCar() {
		this.gameObjects.set(0, new Car());
	}
	
	public int getSizeOfGameObjectCollection() {
		return this.gameObjects.size();
	}
	
	public void addGameObject(GameObject gameObject) {
		gameObjects.add(gameObject);
	}
	
	
	public Iterator getIterator() {
		return new Iterator();
	}
	
	public class Iterator implements IIterator{
		private int index;
		
		public Iterator() {
			this.index = 0;
		}
		
		public boolean hasNext() {
			//If the index has passed the size of the array that means no more elements to grab
			if (index >= gameObjects.size()) {
				return false;
			}
			return true;
		}
		
		//Grabs elements from list
		public GameObject getNext() {
			this.index++;
			return gameObjects.get(index-1);
		}
	}
}
