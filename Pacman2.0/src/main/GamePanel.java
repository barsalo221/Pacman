package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Entity.Entity;
import Entity.Pacman;
import Multiplayer.Client;
import Multiplayer.Server;
import Object.Sound;
import Object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final int originalTileSize = 13;
	public final int scale = 3;
	public final int tileSize = originalTileSize * scale;
	public final int maxScreenCol = 20;
	public final int maxScreenRow = 21;
	public final int screenWidth = tileSize * maxScreenCol;	// 765 pixels
	public final int screenHeight = tileSize * maxScreenRow;	// 576 pixels
	private int FPS = 60;
	
	// system
	public Sound sound = new Sound();
	public CollisionChekers cChecker = new CollisionChekers(this);
	public TileManager tileM = new TileManager(this);
	public KeyHandler keyH = new KeyHandler(this);
	protected Thread gameThread;
	protected AssetSetter assetSetter = new AssetSetter(this);
	public UI ui = new UI(this);
	public EventHandler eHandler = new EventHandler(this);
	
	// entity and objects
	public Pacman pacman = new Pacman(this,keyH);
	public SuperObject obj[] = new SuperObject[191];
	public Entity ghosts[] = new Entity[4];
	
	// game state
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int dialogueState= 3;
	public final int gameOverState = 4;
	public final int optionsState = 5;
	
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth,screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
//		playMusic(1);
		
	} 
	public void setupGame() {
		assetSetter.setObject();
		assetSetter.setGhosts();
		playMusic(0);
		gameState = titleState;
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	public void stopGameThread() {
		gameThread.stop();
	}
	
	@Override
	public void run() {
		double drawInterval = 1000000000/FPS;	//0.01666 sec
		double nextDrawTime = System.nanoTime() + drawInterval;
			
		while(gameThread != null) {
	
			update();
			
			repaint();
			
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime / 1000000;
			
				if(remainingTime < 0) {
					remainingTime = 0;
				}
				Thread.sleep((long) remainingTime);
				nextDrawTime += drawInterval; 
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
	}
	public void retry() {
		pacman.setDefaultPositions();
		assetSetter.setGhosts();
//		if(pacman.life <= 0) {
//			gameState = gameOverState;
//		}
	}
	public void restart() {
		pacman.setDefaultValues();
		assetSetter.setObject();
		assetSetter.setGhosts();
	}
	 
	public void update() {
	
		if(gameState == playState) {
			//pacman 
			pacman.update();
			// ghosts
			for(int i = 0; i < ghosts.length; i++) {
				if(ghosts[i] != null) {
					ghosts[i].update();;
				}
			}
		}
		if(gameState == pauseState) {
			//noting
		}
	}
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
	
		Graphics2D g2d = (Graphics2D)g;
		// title screen
		if(gameState == titleState){
			ui.draw(g2d);
		}
		//others
		else {
			//draw tile
			tileM.draw(g2d);
			// object
			for(int i = 0 ; i < obj.length; i++) {
				if(obj[i] != null) {
					obj[i].draw(g2d, this);
				}
			}
			// ghosts
			for(int i = 0; i < ghosts.length; i++) {
				if(ghosts[i] != null) {
					ghosts[i].draw(g2d);
				}
			}
			//draw player
			pacman.draw(g2d);
			ui.draw(g2d);
		}
		System.out.println("score is: " + pacman.score);
		g2d.dispose();
	}
	 public void playMusic(int i) {
	    	sound.setFile(i);
	    	sound.play();
	    	sound.loop();
	    }
	 public void stopMusic() {
			sound.stop();
		}
	 public void playSE(int i) {
	    	sound.setFile(i);
	    	sound.play();
		}
		
	 public synchronized void startServerClient() {
		 new Server();
		 new Client();
		 new Client();
	 }

}
