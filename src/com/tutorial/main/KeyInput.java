package com.tutorial.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

	private Handler handler;
	private boolean keysPressed[] = new boolean[4];
	
	public KeyInput(Handler handler) {
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
		
		for (int i = 0; i < handler.gameObjects.size(); i++) {
			GameObject temp = handler.gameObjects.get(i);
			
			if (temp.getID() == ID.Player) {
				// handle player keyPressedEvents
				if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
					temp.setVelY(-5);
					keysPressed[0] = true;
				} else if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
					temp.setVelY(5);
					keysPressed[1] = true;
				} else if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
					temp.setVelX(5);
					keysPressed[2] = true;
				} else if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
					temp.setVelX(-5);
					keysPressed[3] = true;
				} 
			}
		} 
	}
	 
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		for (int i = 0; i < handler.gameObjects.size(); i++) {
			GameObject temp = handler.gameObjects.get(i);
			if (temp.getID() == ID.Player) {
				// handle player keyReleasedEvents
				if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
					keysPressed[0] = false;
				} else if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
					keysPressed[1] = false;
				} else if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
					keysPressed[2] = false;
				} else if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
					keysPressed[3] = false;
				} 
				
				if (!keysPressed[0] && !keysPressed[1]) {
					temp.setVelY(0);
				}
				if (!keysPressed[2] && !keysPressed[3]) {
					temp.setVelX(0);
				}
			}
		}
	} 
}
