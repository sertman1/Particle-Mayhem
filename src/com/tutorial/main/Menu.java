package com.tutorial.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends MouseAdapter {

	private Game game;

	public Menu(Game game) {
		this.game = game;
	}

	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();

		if (Game.state == Game.State.Menu ) {
			if (mouseClicked(mx, my, Game.WIDTH / 2 - 155, 125, 250, 50)) { // start
				game.startLevelOne();
				Game.state = Game.State.Game;
			} else if (mouseClicked(mx, my, Game.WIDTH / 2 - 155, 225, 250, 50)) { // help
				Game.state = Game.State.Help;
			} else if (mouseClicked(mx, my, Game.WIDTH / 2 - 155, 325, 250, 50)) { // quit
				System.exit(0);
			}	
		} else if (Game.state == Game.State.Lost) {
			if (mouseClicked(mx, my, Game.WIDTH / 2 - 115, 300, 250, 50)) { // play again?
				game.handler.clearObjects();
				HUD.HEALTH = 100;
				Game.state = Game.State.Game;
				game.startLevelOne();
			} else if (mouseClicked(mx, my, Game.WIDTH / 2 - 115, 380, 250, 50)) { // quit
				System.exit(0);
			}
		} else if (Game.state == Game.State.Help) {
			if (mouseClicked(mx, my, Game.WIDTH / 2 - 115, 250, 250, 50)) { // back to menu
				Game.state = Game.State.Menu;
			} else if (mouseClicked(mx, my, Game.WIDTH / 2 - 115, 330, 250, 50)) { // quit
				System.exit(0);
			}
		}
	}

	public void render(Graphics g) {
		Font fnt = new Font("Arial", 1, 60);
		g.setFont(fnt);
		g.setColor(Color.white);
		g.drawString("MENU", Game.WIDTH / 2 - 85, 67);

		g.setColor(Color.gray); // Menu option boxes:
		g.fillRect(Game.WIDTH / 2 - 115, 125, 250, 50);
		g.fillRect(Game.WIDTH / 2 - 115, 225, 250, 50);
		g.fillRect(Game.WIDTH / 2 - 115, 325, 250, 50);
		
		g.setColor(Color.cyan); // Menu option boxes outline:
		g.drawRect(Game.WIDTH / 2 - 115, 125, 250, 50);
		g.drawRect(Game.WIDTH / 2 - 115, 225, 250, 50);
		g.drawRect(Game.WIDTH / 2 - 115, 325, 250, 50);
		
		g.setColor(Color.white);// Menu option text:
		Font fnt2 = new Font("Arial", 1, 30);
		g.setFont(fnt2);
		g.drawString("START", Game.WIDTH / 2 - 42, 160);
		g.drawString("HELP", Game.WIDTH / 2 - 30, 260);
		g.drawString("QUIT", Game.WIDTH / 2 - 30, 360);
	}

	public void renderHelp(Graphics g) {
		g.setFont(new Font("Arial", 1, 50));
		g.setColor(Color.pink);
		g.drawString("HELP MENU:", 170, 100);

		g.setFont(new Font("Arial", 1, 16));
		g.setColor(Color.white);
		g.drawString("Use standard WASD or arrow keys to navigate the board and avoid the monsters!", 13,
				Game.HEIGHT / 2 - 50);

		g.setColor(Color.cyan);
		g.drawRect(Game.WIDTH / 2 - 115, 230, 250, 50);
		g.drawRect(Game.WIDTH / 2 - 115, 330, 250, 50);
	}

	private boolean mouseClicked(int mx, int my, int x, int y, int width, int height) {
		if (mx > x && mx < x + width && my > y && my < y + height) {
			return true;
		}
		return false;
	}

}
