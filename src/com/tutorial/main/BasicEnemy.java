package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BasicEnemy extends GameObject {

	public static final int WIDTH = 14, HEIGHT = 14;

	BasicEnemy(int x, int y, ID id) {
		super(x, y, id);

		velX = 3;
		velY = 3;
	}

	public void tick() {
		x += velX;
		y += velY;
		
		if (x < 0 || x + WIDTH > Game.WIDTH) {
			velX *= -1;
		}

		if (y < 0 || y + HEIGHT + 22 > Game.HEIGHT) {
			velY *= -1;
		} 
		
	}

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, WIDTH, HEIGHT);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, WIDTH, HEIGHT);
	}

}
