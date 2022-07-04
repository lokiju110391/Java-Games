package Controller;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import Controller.Obj.Border;
import Controller.Obj.Commander;

public class Game extends Canvas implements Runnable, KeyListener{


	private static final long serialVersionUID = 1L;

	public static JFrame frame;
	private int frames_atual = 0;
	
	private Thread thread;
	
	public final static int WIDTH  = 720;
	public final static int HEIGHT = 360;	
	public final static int SCALE  = 2;
	private boolean isRunning;
		
	public BufferedImage layer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	
	public static Border border;
	public static Commander commander;
	
	public Game() {
		
		setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		this.addKeyListener(this);

		border = new Border();
		commander = new Commander();
		
		initFrame();
		
	}
		
	private void initFrame() {

		frame = new JFrame("Game 1");		
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}

	public synchronized void start() {
		
		thread = new Thread(this);		
		isRunning = true;
		thread.start();
		
	}
	
	public synchronized void stop() {
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		game.start();		
	}

	public void tick() {

		border.tick();	
		commander.tick();	
	
	}
	
	public void render() {
		
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = layer.getGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		g.setFont(new Font("Arial", Font.BOLD, 10));
		g.setColor(Color.WHITE);
		g.drawString("FPS: "+frames_atual, 0, 9);
		
		border.render(g);
		commander.render(g);
		
		g = bs.getDrawGraphics();
		g.drawImage(layer, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null);
		bs.show();
		
	}		
	
	public void run() {
		
		long   lastTime      = System.nanoTime();
		double amountofTicks = 60.0;
		double ns            = 1000000000/amountofTicks;
		double delta         = 0;
		int    frames        = 0;
		double timer         = System.nanoTime();
		
		while (isRunning) {

			long now = System.nanoTime();
			
			delta += (now - lastTime) / ns;
			
			lastTime = now;
			
			if (delta >= 1) {
				tick();
				render();
				frames++;
				delta--;
			}			
			
			if (System.nanoTime() - timer > 1000000000) {				
				//System.out.println("FPS " + frames);
				frames_atual = frames;
				frames = 0;
				timer += 1000000000;
			}
			
			
			
		}
		
		stop();
		
	}

	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_NUMPAD1) {			
			commander.add_comand("1");
		} else if (e.getKeyCode() == KeyEvent.VK_NUMPAD2) {
			commander.add_comand("2");
		}  else if (e.getKeyCode() == KeyEvent.VK_NUMPAD3) {
			commander.add_comand("3");
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}	
	
}
