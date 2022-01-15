package com.tutorial.main;

import java.util.Random;

public class Spawner {

	private HUD hud;
	private Handler handler;
	private Random random;
	private Player player;
	
	public Spawner(HUD hud, Handler handler, Player player) {
		this.hud = hud;
		this.handler = handler;
		random = new Random();
		this.player = player;
		SmartEnemy enemy = new SmartEnemy(random.nextInt(Game.WIDTH - 35), random.nextInt(Game.HEIGHT - 35), ID.BasicEnemy, handler);
		while (enemy.getBounds().intersects(player.getBounds())) {
			enemy.setX(random.nextInt(Game.WIDTH - 35));
			enemy.setY(random.nextInt(Game.HEIGHT - 35));
		}
		handler.add(enemy);
	}
	
	public void tick() {
		if (hud.getScore() % 250 == 0) {
			hud.setLevel(hud.getLevel() + 1);
			
			int randX = random.nextInt(Game.WIDTH - 35);
			int randY = random.nextInt(Game.HEIGHT - 35);
			
			GameObject enemy;
			if (hud.getLevel() % 5 == 0) {
				enemy = new FastEnemy(randX, randY, ID.FastEnemy);
				while (enemy.getBounds().intersects(player.getBounds())) {
					enemy.setX(random.nextInt(Game.WIDTH - 35));
					enemy.setY(random.nextInt(Game.HEIGHT - 35));
				}
			} else {
				enemy = new BasicEnemy(randX, randY, ID.BasicEnemy);
				while (enemy.getBounds().intersects(player.getBounds())) {
					enemy.setX(random.nextInt(Game.WIDTH - 35));
					enemy.setY(random.nextInt(Game.HEIGHT - 35));
				}
			}
			handler.add(enemy);
		}
	}
	
}
