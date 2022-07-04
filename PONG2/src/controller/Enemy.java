package controller;

import java.awt.Color;
import java.awt.Graphics;

public class Enemy {

	public double x,y;
	public int width,height;

	public Enemy(int x, int y) {

		this.x = x;
		this.y = y;
		this.width = 40;
		this.height = 10;
		
	}
	
	public void tick() {
		
		x += ((Main.ball.x) - x)*2;
			
		// Limit
		if (x < 0) {
			x = 0;
		}
		
		if (x+width > Main.width) {
			x = Main.width - width;
		}		
		
	}
	
		
	public void render(Graphics g) {
		
		g.setColor(Color.BLUE);
		g.fillRect((int)x, (int)y, width, height);
		
	}
	
	
	
}
