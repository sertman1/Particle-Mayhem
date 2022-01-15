package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class MenuParticle extends GameObject {

	public static final int WIDTH = 12, HEIGHT = 12;
	private Random random;
	private Color color;
	
	MenuParticle(int x, int y, ID id) {
		super(x, y, id);
		random = new Random();
		color = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
		
		if (random.nextInt(2) < 1) {
			velX = random.nextInt(7) + 1;
			velY = random.nextInt(3) + 1;
		} else {
			velY = random.nextInt(7) + 1;
			velX = random.nextInt(3) + 1;
		}
		
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
		g.setColor(color);
		g.fillRect((int)x, (int)y, WIDTH, HEIGHT);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, WIDTH, HEIGHT);
	}

}
