package main;

import java.awt.Event;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	public boolean upPressed, downPressed, rightPressed, leftPressed, enterPressed;
	GamePanel gp;
	
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}
	
	@Override	
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
	 
			//TITLE STATE 
		if(gp.gameState == gp.titleState) {
			titleState(code);
		}
		//play state
		else if(gp.gameState == gp.playState) {
			playState(code);
		}		
		//pause state
		else if(gp.gameState == gp.pauseState) {
			pauseState(code);
		}
		//dialogue state
		else if(gp.gameState == gp.dialogueState) {
			dialogueState(code);
		}
		//options state
		else if(gp.gameState == gp.optionsState) {
			optionsState(code);
		}
		//game over state
		else if(gp.gameState == gp.gameOverState) {
			gameOverState(code);
		}
		
//		if(code == KeyEvent.VK_F1) {
//			gp.stopMusic();
//		}
//		
//		if(code == KeyEvent.VK_F3) {
//			gp.playMusic(1);
//		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W) {
			upPressed = false;
		}
		
		if(code == KeyEvent.VK_S) {
			downPressed = false;

		}
		if(code == KeyEvent.VK_A) {
			leftPressed = false;

		}
		if(code == KeyEvent.VK_D) {
			rightPressed = false;

		}
	}
	
	public void titleState(int code) {
		if(code == KeyEvent.VK_W) {
			
			gp.ui.commandNum--;
			if(gp.ui.commandNum < 0 ) {
				gp.ui.commandNum = 2;
			}
		}
		if(code == KeyEvent.VK_S) {
			gp.ui.commandNum++;
			if(gp.ui.commandNum > 2 ) {
				gp.ui.commandNum = 0;
			}
		}
		if(code == KeyEvent.VK_ENTER) {
			if(gp.ui.commandNum == 0) {
				gp.gameState = gp.playState;
				gp.stopMusic();
				gp.playMusic(1);
			}
			if(gp.ui.commandNum == 1) {
				gp.startServerClient();
				gp.setVisible(false);
				gp.startServerClient();
			}
			if(gp.ui.commandNum == 2) { 
				System.exit(0);
			}
		}
		
	}
	
	public void playState(int code) {


		//play game
		if(code == KeyEvent.VK_W) {
			upPressed = true;
		}
		
		if(code == KeyEvent.VK_S) {
			downPressed = true;

		}
		if(code == KeyEvent.VK_A) {
			leftPressed = true;

		}
		if(code == KeyEvent.VK_D) {
			rightPressed = true;

		}
		if(code == KeyEvent.VK_P) {
			gp.gameState = gp.pauseState;
			
//			if(gp.gameState == gp.playState) {
//				
//				gp.gameState = gp.pauseState;
//				gp.stopMusic();
//				gp.gameThread.stop(); 
//				
//			}else if(gp.gameState == gp.pauseState){
//				
//				gp.gameState = gp.playState;
//				gp.startGameThread();
//				gp.playMusic(1);
//				
//			}
		}
		if(code == KeyEvent.VK_ENTER) {
			enterPressed = true;
		}
		if(code == KeyEvent.VK_ESCAPE) {
			gp.gameState = gp.optionsState;
		}
	
	}

	public void pauseState(int code) {
		if(code == KeyEvent.VK_P) {
			gp.gameState = gp.playState;
		}
	}
	public void dialogueState(int code) {
		if(code == KeyEvent.VK_ENTER) {
			gp.gameState = gp.playState;
			if(gp.pacman.direction == "down") {
				gp.pacman.direction = "up";
			}
			else if(gp.pacman.direction == "up") {
				gp.pacman.direction = "down";
			}
			else if(gp.pacman.direction == "right") {
				gp.pacman.direction = "left";
			}
			else if(gp.pacman.direction == "left") {
				gp.pacman.direction = "right";
			}
		}
	}
	public void optionsState(int code){
		
		if(code == KeyEvent.VK_ESCAPE) {
			gp.gameState = gp.playState;
		}
		if(code == KeyEvent.VK_ENTER) {
			enterPressed = true;
		}
		int maxCommandNum = 0 ;
		
		switch (gp.ui.subState) {
		case 0: maxCommandNum = 4; break;
		case 2: maxCommandNum = 1; break;

		}
		
		if(code == KeyEvent.VK_W) {
			gp.ui.commandNum--;
			if(gp.ui.commandNum < 0 ) {
				gp.ui.commandNum = maxCommandNum;
			}
		}
		if(code == KeyEvent.VK_S) {
			gp.ui.commandNum++;
			if(gp.ui.commandNum > maxCommandNum ) {
				gp.ui.commandNum = 0;
			}
		}
		if(code == KeyEvent.VK_A) {
			if(gp.ui.subState == 0) {
				if(gp.ui.commandNum == 0 && gp.sound.volumeScale > 0) {
					gp.sound.volumeScale--;
					gp.sound.checkVolume();
				}
			}
		}
		if(code == KeyEvent.VK_D) {
			if(gp.ui.subState == 0) {
				if(gp.ui.commandNum == 0 && gp.sound.volumeScale < 5) {
					gp.sound.volumeScale++;
					gp.sound.checkVolume();
				}
			
			} 
		}
	}
	public void gameOverState(int code){
		
		if(code == KeyEvent.VK_W) {
			gp.ui.commandNum--;
			if(gp.ui.commandNum < 0) {
				gp.ui.commandNum = 1;
			}
		}
		if(code == KeyEvent.VK_S) {
			gp.ui.commandNum++;
			if(gp.ui.commandNum > 1) {
				gp.ui.commandNum = 0;
			}
		}
		if(code == KeyEvent.VK_ENTER) {
			if(gp.ui.commandNum == 0) {
				gp.gameState = gp.playState;
				gp.retry();
			}
			else if(gp.ui.commandNum == 1) {
				gp.gameState = gp.playState;
				gp.restart();
			}
		}
	}
}
