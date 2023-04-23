package main;

import javax.swing.JFrame;


public class MainMenu extends JFrame {
	/**
	 * 
	 */ 
	private static final long serialVersionUID = 1L;
	
	public GamePanel gp;
	
	public MainMenu() {
		
		gp = new GamePanel();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("Pacmam by Bar Salomon");
		
		this.add(gp);
		
		this.pack();
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		gp.setupGame();
		gp.startGameThread();

	}
	
	public static void main(String[] args) {
		MainMenu main = new MainMenu();
	}

}
