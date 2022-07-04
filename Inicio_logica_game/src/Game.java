import java.util.ArrayList;

public class Game implements Runnable{

	private boolean isRunning;
	private Thread thread;
	
	private ArrayList<Entidade> entidades = new ArrayList<Entidade>();
	
	public Game() {
		
		entidades.add(new Entidade());
		entidades.add(new Entidade());
		entidades.add(new Entidade());
		entidades.add(new Entidade());
		entidades.add(new Entidade());
		
		for (int i = 0; i < entidades.size(); i++) {
			System.out.println("Hey!");
			Entidade e = entidades.get(i);
		}
		
	}
	
	public static void main(String[] args) {
		
		Game game = new Game();
		game.start();
		
	}
	
	public synchronized void start() {
		// Start game
		isRunning = true;
		// Start Thread
		thread = new Thread(this);
		thread.start();		
	}
	
	public void tick() {
		// Logic Time
		//System.out.println("TICK");
	}
	
	public void render() {
		// Render Time
		//System.out.println("RENDER");		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		while(isRunning) {
			tick();
			render();
		}
		
	}

}
