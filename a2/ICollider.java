package com.mycompany.a2;

public interface ICollider {
	boolean collidesWith(GameObject otherObject);
	void handleCollision(GameObject otherObject, GameWorld gw);
}
