package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {

	public static float HEALTH = 100;
	private int level = 1;
	private long score = 0;
	private short greenValue = 255;
	
	public void tick() {
		HEALTH = Game.clamp(HEALTH, 0, 100);
		
		
		greenValue = (short) (255 * HEALTH / 100);
		greenValue = (short) Game.clamp(greenValue, 0, 255);
		
		score++;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.gray); // background of bar
		g.fillRect(8, 8, 200, 22);
		
		g.setColor(new Color(150, greenValue, 0)); // health bar
		g.fillRect(8, 8, (int)HEALTH * 2, 22);
		
		g.setColor(Color.white); // boarder
		g.drawRect(7,  7, 201, 23);
		
		g.setColor(Color.white);
		g.drawString("Score: " + score, 8, 48);
		g.drawString("Level: " + level, 8, 64);
	}
	
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
	public long getScore() {
		return score;
	}
	public void setScore(long score) {
		this.score = score;
	}
}
