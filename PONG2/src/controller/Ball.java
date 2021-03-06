package controller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball {

	public double x,y;
	public int width,height;

	public double dx,dy;
	public double speed = 1.6;

	
	public Ball(int x, int y) {

		this.x = x;
		this.y = y;
		this.width = 4;
		this.height = 4;
		
		dx = new Random().nextGaussian();
		dy = new Random().nextGaussian();
		
	}
	
	public void tick() {
		
		x += dx*speed;
		y += dy*speed;
		
		
		// Limit
		if (x < 0) {
			x = 0;
		}
		
		if (x+width > Main.width) {
			dx *= -1;
		} else if (x < 0) {
			dx *= -1;			
		}
		
		if (y+height > Main.height) {
			dy *= -1;
		} else if (y < 0) {
			dy *= -1;			
		}
		
		Rectangle bounds       = new Rectangle((int)x,(int)y,width,height);
		Rectangle boundsPlayer = new Rectangle((int)Main.player.x,(int)Main.player.y,Main.player.width,Main.player.height);
		Rectangle boundsEnemy  = new Rectangle((int)Main.enemy.x,(int)Main.enemy.y,Main.enemy.width,Main.enemy.height);

		if (bounds.intersects(boundsPlayer) || bounds.intersects(boundsEnemy)) {
			dy = new Random().nextGaussian();
			dx = new Random().nextGaussian();
		}
		
		if (y > Main.height) {
			// Ponto Inimigo
		} else if (y < 0) {
			// Ponto Jogador			
		}
		
	}
	
		
	public void render(Graphics g) {
		
		g.setColor(Color.yellow);
		g.fillRect((int)x, (int)y, width, height);
		
	}
	
	
}
