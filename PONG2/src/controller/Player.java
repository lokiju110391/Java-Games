package controller;

import java.awt.Color;
import java.awt.Graphics;

public class Player {

	public boolean right;
	public boolean left;
	
	public int x,y;
	public int width,height;

	public Player(int x, int y) {

		this.x = x;
		this.y = y;
		this.width = 40;
		this.height = 10;
	}
	
	public void tick() {
		
		if (right) {
			x+=2;
		} else if (left) {
			x-=2;
		}
		
		if (x < 0) {
			x = 0;
		}
		
		if (x+width > Main.width) {
			x = Main.width - width;
		}
		
	}
	
	public void render(Graphics g) {
		
		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, height);
		
	}
	
}
