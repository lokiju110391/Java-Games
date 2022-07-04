package Controller.Obj;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import Controller.Game;

public class Commander {

	private int life;
	private int attack;
	private int defense;
	
	private int life_e;
	private int attack_e;

	public int timer = 0;

	public ArrayList<String>  attack_par     = new ArrayList<String>();
	public ArrayList<String>  defence_par    = new ArrayList<String>();
	public ArrayList<String>  heal_par       = new ArrayList<String>();	
	public ArrayList<String>  burref_comands = new ArrayList<String>();

	
	public Commander() {

		life    = 100;
		attack  = 0;
		defense = 5;
		
		life_e    = 100;
		attack_e  = 0;
		
		// Attack
		attack_par.add("1");
		attack_par.add("1");
		attack_par.add("2");
		attack_par.add("3");
		
		// Defence
		defence_par.add("3");
		defence_par.add("1");
		defence_par.add("1");
		defence_par.add("2");
		
		// Heal
		heal_par.add("3");
		heal_par.add("3");
		heal_par.add("3");
		heal_par.add("1");		
		
	}
	
	public void add_comand(String command) {		
		
		System.out.println(Game.border.accept_comand);
		
		if (Game.border.accept && Game.border.accept_comand) {
			
			if (burref_comands.size() == 4) {
				burref_comands.removeAll(burref_comands);
			}
			
			burref_comands.add(command);
			Game.border.accept_comand = false;
			
		} else {
			burref_comands.removeAll(burref_comands);
		}
		
	}
	
	public void tick() {
		
		timer++;
		
		if (timer >= 15) {
			attack_e++;
			timer = 0;
		}	
		
		if (attack_e == 100) {
			attack_e = 0;
			
			// Shield
			if (defense < 15) {
				
				int dano =  15 - defense;
				defense  = 0;
				life     -= dano; 
			} else {
				defense -= 15;
			}
			
		}
		
		if (burref_comands.containsAll(attack_par)) {
			attack += 20;
			
			if (attack == 100) {
				attack = 0;
				life_e -= 10;
			}
			
			burref_comands.removeAll(burref_comands);
		}
		
		if (burref_comands.containsAll(defence_par)) {
			defense += 10;
			burref_comands.removeAll(burref_comands);
		}
		
		if (burref_comands.containsAll(heal_par)) {
			life += 5;
			if (life > 100) {
				life = 100;
			}
			burref_comands.removeAll(burref_comands);

		}
		
	}

	public void render(Graphics g) {
		
		g.setFont(new Font("Arial", Font.BOLD, 10));
		g.setColor(Color.WHITE);
		g.drawString("L: " + life, 20, 30);
		g.drawString("A: " + attack, 20, 45);
		g.drawString("D: " + defense, 20, 60);
		
		g.drawString("L: " + life_e, Game.WIDTH-45, 30);
		g.drawString("A: " + attack_e, Game.WIDTH-45, 45);	

		g.setFont(new Font("Arial", Font.BOLD, 15));
		g.drawString("Commands", Game.WIDTH/2-80, 100);	
		g.drawString(String.join(",", burref_comands), Game.WIDTH/2-80, 120);	
			
	}

}