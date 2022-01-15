package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class SmartEnemy extends GameObject {

	public static final int WIDTH = 14, HEIGHT = 14;
	private Player player;

	SmartEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		for (int i = 0; i < handler.gameObjects.size(); i++) {
			if (handler.gameObjects.get(i).getID() == ID.Player) {
				player = (Player) handler.gameObjects.get(i);
			}
		}
	}

	public void tick() {
		x += velX;
		y += velY;
		
		float diffX = (x - WIDTH) - player.getX();
		float diffY = (y - HEIGHT) - player.getY();
		float distance = (float) Math.sqrt(Math.pow(x - player.getX(), 2) + Math.pow(y - player.getY(), 2));
		
		velX = (float) ((-1.0/distance) * diffX);
		if (velX < 0) {
			velX -= 0.3f;
		} else {
			velX += 0.3f;
		}
		velY = (float) ((-1.0/distance) * diffY);
		if (velY < 0) {
			velY -= 0.5f;
		} else {
			velY += 0.5f;
		}
		
		if (x < 0 || x + WIDTH > Game.WIDTH) {
			velX *= -1;
		}

		if (y < 0 || y + HEIGHT + 22 > Game.HEIGHT) {
			velY *= -1;
		} 
		
	}

	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect((int)x, (int)y, WIDTH, HEIGHT);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int) y, WIDTH, HEIGHT);
	}
	
}

