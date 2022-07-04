package Controller.Obj;

import java.awt.Color;
import java.awt.Graphics;

import Controller.Game;

public class Border {
	
	public int x,y;
	public int width,height;

	public Color colorB;
	public int timer = 0;

	public boolean accept = false;
	public boolean accept_comand = true;

	public Border() {

	}
	
	public void tick() {		
				
		int max_time = 35;
		timer++;
		
		if (timer > 0) {
			colorB = new Color(0, 0, 0, 255);
			accept = false;
		}
		
		if (timer > max_time) {
			colorB = new Color(255, 255, 255, 255);	
			accept = true;			
		}
		
		if (timer >= max_time*2) {
			timer = 0;
			accept = false;
			
			if (accept_comand == true) {
				// não teve tecla
				Game.commander.burref_comands.removeAll(Game.commander.burref_comands);
			}
			
			accept_comand = true;
		}			
		
	}
	
	public void render(Graphics g) {
		
		g.setColor(colorB);
		g.fillRect( 10, 10, Game.WIDTH-20, 1);
		g.fillRect( 10, Game.HEIGHT-10, Game.WIDTH-20, 1);
		
		g.fillRect( 10, 10, 1, Game.HEIGHT-20);
		g.fillRect( Game.WIDTH-10, 10, 1, Game.HEIGHT-20);		
		
	}
	
}
