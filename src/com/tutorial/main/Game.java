package com.tutorial.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1550691097823471818L;

	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;

	private Thread thread;
	private boolean running = false;

	public Handler handler;
	private HUD hud;
	private Spawner spawner;
	private Random random;
	private Menu menu;

	public enum State {
		Menu, Help, Game, Lost;
	}

	public static State state;

	public Game() {
		state = State.Menu; 
		handler = new Handler();
		menu = new Menu(this);
		hud = new HUD();
		
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(menu);
		this.requestFocus();

		new Window(WIDTH, HEIGHT, "Let's Build a Game!", this);
		
		random = new Random();
		for (int i = 0; i < 20; i++) {
			handler.gameObjects.add(new MenuParticle(random.nextInt(Game.WIDTH - 35), random.nextInt(Game.HEIGHT - 35), ID.MenuParticle));
		}
	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
	//	int frames = 0;

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}

			if (running) {
				render();
			}

		//	frames++;
			if (System.currentTimeMillis() - timer > 1000) {
			//	System.out.println("FPS: " + frames);
				timer += 1000;
			//	frames = 0;
			}
		}
		stop();
	}

	private void tick() {
		handler.tick();
		if (Game.state == State.Game) {
			hud.tick();
			spawner.tick();
		}
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		handler.render(g);
		if (Game.state == State.Game) {
			hud.render(g);
		} else if (Game.state == State.Menu) {
			menu.render(g);
		} else if (Game.state == State.Help) {
			renderHelpScreen(g);
		} else if (Game.state == State.Lost) {
			renderLostScreen(g);
		}

		g.dispose();
		bs.show();
	}

	public void startLevelOne() {
		hud.setLevel(1);
		hud.setScore(0);
		handler.clearObjects();
		Player player = new Player(WIDTH / 2 - Player.WIDTH, HEIGHT / 2 - Player.HEIGHT, ID.Player, handler);
		handler.add(player);
		spawner = new Spawner(hud, handler, player);
	}
	
	private void renderHelpScreen(Graphics g) {
		g.setFont(new Font("Wide Latin", 1, 50));
		g.setColor(Color.orange);
		g.drawString("HELP", 251, 100);
		g.setColor(Color.white);
		g.setFont(new Font("Arial", 1, 20));
		g.drawString("Use WASD or arrow keys to dodge the particle monsters!", 50, 200);
		g.setColor(Color.gray); // Option boxes:
		g.fillRect(Game.WIDTH / 2 - 115, 250, 250, 50);
		g.fillRect(Game.WIDTH / 2 - 115, 330, 250, 50);
		
		g.setColor(Color.cyan); // Option boxes' outline:
		g.drawRect(Game.WIDTH / 2 - 115, 250, 250, 50);
		g.drawRect(Game.WIDTH / 2 - 115, 330, 250, 50);
		
		g.setColor(Color.white);// Options' text:
		Font fnt2 = new Font("Arial", 1, 30);
		g.setFont(fnt2);
		g.drawString("Back to Menu", Game.WIDTH / 2 - 88, 285);
		g.drawString("QUIT", Game.WIDTH / 2 - 30, 365);
	}
	
	private void renderLostScreen(Graphics g) {
		if (handler.gameObjects.size() == 0) { 
			for (int i = 0; i < 20; i++) { // add colorful affect
				handler.gameObjects.add(new MenuParticle(random.nextInt(Game.WIDTH - 35), random.nextInt(Game.HEIGHT - 35), ID.MenuParticle));
			}
		}
		
		g.setFont(new Font("Wide Latin", 1, 50));
		g.setColor(Color.orange);
		g.drawString("YOU LOST!", 180, 100);
		g.setFont(new Font("Arial", 1, 25));
		g.setColor(Color.white);
		g.drawString("You reached level", 50, 200);
		g.setColor(Color.red);
		g.drawString(" " + hud.getLevel(), 265, 200);
		g.setColor(Color.white);
		g.drawString("!", 296, 200);
		g.drawString("Final score:", 50, 250);
		g.setColor(Color.green);
		g.drawString(" " + hud.getScore(), 200, 250);
		
		g.setColor(Color.gray); // Option boxes:
		g.fillRect(Game.WIDTH / 2 - 115, 300, 250, 50);
		g.fillRect(Game.WIDTH / 2 - 115, 380, 250, 50);
		
		g.setColor(Color.cyan); // Option boxes' outline:
		g.drawRect(Game.WIDTH / 2 - 115, 300, 250, 50);
		g.drawRect(Game.WIDTH / 2 - 115, 380, 250, 50);
		
		g.setColor(Color.white);// Options' text:
		Font fnt2 = new Font("Arial", 1, 30);
		g.setFont(fnt2);
		g.drawString("Play Again?", Game.WIDTH / 2 - 70, 335);
		g.drawString("QUIT", Game.WIDTH / 2 - 30, 418);
	}

	public static float clamp(float val, float min, float max) {
		if (val > max) {
			return max;
		} else if (val < min) {
			return min;
		}
		return val;
	}

	public static void main(String[] args) {
		new Game();
	}

}
