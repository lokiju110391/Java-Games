import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable{

	public static JFrame frame;
	
	private Thread thread;
	
	private final int WIDTH  = 240;
	private final int HEIGHT = 120;	
	private final int SCALE  = 3;
	private boolean isRunning;
	
	private BufferedImage image;
	
	private int pos          = 0;	
	private int frames       = 0;
	private int max_frames   = 20;
	private int curAnimation = 0;
	private int maxAnimation = 3;
	private boolean signal   = true;		
	private int frames_atual = 0;
	
	public Player player;
	
	public Game() {
				
		setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		initFrame();
		image  = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		player = new Player();
		
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
		
	
	}
	
	public void render() {
		
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = image.getGraphics();
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
				
		g.setFont(new Font("Arial", Font.BOLD, 10));
		g.setColor(Color.WHITE);
		g.drawString("FPS: "+frames_atual, 0, 10);
				
		g.dispose();
		g = bs.getDrawGraphics();		
		g.drawImage(image, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null);
		
		player.render(g);
		
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
				System.out.println("FPS " + frames);
				frames_atual = frames;
				frames = 0;
				timer += 1000000000;
			}	
			
			
		}
		
		stop();
		
	}	
	
}
