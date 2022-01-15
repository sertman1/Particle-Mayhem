package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject {
	
	public static final int WIDTH = 32, HEIGHT = 32;
	private Handler handler;
	
	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}
	
	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		x = Game.clamp(x, 0, Game.WIDTH - WIDTH);
		y = Game.clamp(y, 0, Game.HEIGHT - HEIGHT - 22);	
		
		checkCollision();
	}

	@Override
	public void render(Graphics g) {		
		g.setColor(Color.blue);
		g.fillRect((int)x, (int)y, WIDTH, HEIGHT);
	}
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, WIDTH, HEIGHT);
	}
	
	public void checkCollision() {
		for (int i = 0; i < handler.gameObjects.size(); i++) {
			GameObject temp = handler.gameObjects.get(i);
			if (temp.getID() == ID.BasicEnemy || temp.getID() == ID.FastEnemy || temp.getID() == ID.SmartEnemy) {
				if (this.getBounds().intersects(temp.getBounds())) {
					HUD.HEALTH -= 1.3;
					if (HUD.HEALTH <= 0) {
						Game.state = Game.State.Lost;
						handler.clearObjects();
					}
				}
			}
		}
	}
}
