package com.mycompany.a2;

import com.mycompany.a2.GameObjectCollection.Iterator;

public interface ICollection {
	void addGameObject(GameObject gameObject);
	Iterator getIterator();
}
