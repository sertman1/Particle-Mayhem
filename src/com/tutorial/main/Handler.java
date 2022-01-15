package com.tutorial.main;

import java.awt.Graphics;

import java.util.LinkedList;

public class Handler {

	public LinkedList<GameObject> gameObjects;
	
	public Handler() {
		gameObjects = new LinkedList<>();
	}
	
	public void tick() {
		for (int i = 0; i < gameObjects.size(); i++) {
			GameObject temp = gameObjects.get(i);
			temp.tick();
		}
	}
	
	public void render(Graphics g) {
		for (int i = 0; i < gameObjects.size(); i++) {
			GameObject temp = gameObjects.get(i);
			temp.render(g);
		}
	}
	
	public void add(GameObject obj) {
		gameObjects.add(obj);
	}
	
	public void remove(GameObject obj) {
		gameObjects.remove(obj);
	}
	
	public void clearObjects() {
		int oldSize = gameObjects.size();
		for (int i = 0; i < oldSize; i++) {
			gameObjects.remove(0);
		}
	}
	
}
