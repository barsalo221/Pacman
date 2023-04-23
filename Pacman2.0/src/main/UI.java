package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Object.Heart;
import Object.SuperObject;

public class UI {
	GamePanel gp;
	Font arial_40;
	Graphics2D g2;
	public int commandNum = 0;
	BufferedImage heart_full, heart_blank;
	public String currentDialogue = "";
	int subState = 0;
	
	public UI(GamePanel gp) {
		this.gp = gp;
		arial_40 = new Font("Arial", Font.PLAIN, 40);
	
		//create HUD object
		SuperObject heart = new Heart(gp);
		heart_full = heart.image;
		heart_blank = heart.image2;
	}
	
	public void draw(Graphics2D g2) {
		this.g2 = g2;
		g2.setFont(arial_40);
		g2.setColor(Color.black);
		g2.drawString("SCORE = " + gp.pacman.score, getXforCenteredText("SCORE = ") - 1, 30);
		
		// Title State
		if(gp.gameState == gp.titleState) {
			drawTitleScreen();
		}
		//play State
		if(gp.gameState == gp.playState) {
			drawPacmanLife();	
		}
		//dialogue state
		if(gp.gameState == gp.dialogueState) {
			drawPacmanLife();
			drawDialogueScreen();
		}
		//option state
		if(gp.gameState == gp.optionsState) {
			drawoptionsScreen();
		}
		//game over state
		if(gp.gameState == gp.gameOverState) {
			drawGameOverScreen();
		}
		
	}
	public void drawoptionsScreen() {
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32f));
		//sub window
		int frameX = gp.titleState;
		int frameY = gp.tileSize ;
		int frameWidth = gp.tileSize * 20;
		int frameHeight = gp.tileSize * 20;
		
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);

		switch (subState) {
		case 0: options_top(frameX, frameY); break;
		case 1: options_control(frameX, frameY); break;
		case 2: options_endGameConfirmation(frameX, frameY); break;
		}
		gp.keyH.enterPressed = false;
	}
	public void options_endGameConfirmation(int frameX, int frameY) {
		
		int textX = frameX + gp.tileSize;
		int textY = frameY + gp.tileSize*3;
		
		currentDialogue = "QUIT THE GAME AND \nRETURN TO THE TITLE SCREEN?";
		
		for (String line : currentDialogue.split("\n")) {
			g2.drawString(line, textX, textY);
			textY += 40;
		}
		//yes
		String text = "YES";
		textX = getXforCenteredText(text);
		textY += gp.tileSize*3;
		
		g2.drawString(text, textX, textY);
		if(commandNum == 0) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 0;
				gp.gameState = gp.titleState;
				gp.stopMusic();
				gp.playMusic(0);
			}
		}
		//no
		text = "NO";
		textX = getXforCenteredText(text);
		textY += gp.tileSize;
		g2.drawString(text, textX, textY);
		if(commandNum == 1) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 0;
				commandNum = 2;
			}
		}
		
	}
	public void options_top(int frameX, int frameY) {
		int textX;
		int textY;
		//Title
		String text = "OPTIONS";
		textX = getXforCenteredText(text);
		textY = frameY + gp.tileSize;
		g2.drawString(text, textX, textY);
		
		//music
		textX = (frameX * gp.tileSize) + 44;
		textY += gp.tileSize * 2;
		g2.drawString("MUSIC", textX, textY);
		if(commandNum ==  0) {
			g2.drawString(">", textX - 25, textY);
		}
		// control
		textX = frameX + gp.tileSize;
		textY = frameY + gp.tileSize*4;
		g2.drawString("CONTROL", textX, textY);
		if(commandNum ==  1) {
			g2.drawString(">", textX - 25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 1; 
				commandNum = 0;
			}
		} 
		//end game
		textY += gp.tileSize;
		g2.drawString("END GAME :O", textX, textY);
		if(commandNum ==  2) {
			g2.drawString(">", textX - 25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 2; 
				commandNum = 0;
			}
		}
		//back 
		textY += gp.tileSize * 2;
		g2.drawString("BACK TO GAME :)", textX, textY);
		if(commandNum ==  3) {
			g2.drawString(">", textX - 25, textY);
			if(gp.keyH.enterPressed == true) {
				gp.gameState = gp.playState;
				commandNum = 0;
			}
		}
		textX = (frameX * gp.tileSize) + 250;
		textY = frameY + gp.tileSize * 2 - 22;
		//music volume
		textY += gp.tileSize;
		g2.drawRect(textX, textY, 120, 20); //120 / 5 =24
		int volumeWidth = 24 * gp.sound.volumeScale;
		g2.fillRect(textX, textY, volumeWidth, 24);
	}
	public void options_control(int frameX,int frameY) {
		int textX;
		int textY;
		//Title
		String text = "CONTROL";
		textX = getXforCenteredText(text);
		textY = frameY + gp.tileSize;
		g2.drawString(text, textX, textY);
		
		textX = frameX + gp.tileSize;
		textY += gp.tileSize;
		g2.drawString("MOVE UP", textX, textY); textY += gp.tileSize;
		g2.drawString("MOVE RIGHT", textX, textY); textY += gp.tileSize;
		g2.drawString("MOVE LEFT", textX, textY); textY += gp.tileSize;
		g2.drawString("MOVE DOWN", textX, textY); textY += gp.tileSize;
		g2.drawString("PASUE", textX, textY); textY += gp.tileSize;
		g2.drawString("OPTIONS", textX, textY); textY += gp.tileSize;
		
		textX = frameX + gp.tileSize * 10;
		textY = frameY + gp.tileSize * 2;
		
		g2.drawString("W", textX, textY); textY += gp.tileSize;
		g2.drawString("D", textX, textY); textY += gp.tileSize;
		g2.drawString("A", textX, textY); textY += gp.tileSize;
		g2.drawString("S", textX, textY); textY += gp.tileSize;
		g2.drawString("P", textX, textY); textY += gp.tileSize;
		g2.drawString("ESC", textX, textY); textY += gp.tileSize;
		//back
		textX = frameX + gp.tileSize;
		textY = frameY + gp.tileSize * 9;
		g2.drawString("BACK", textX, textY);
		if(commandNum == 0) {
			g2.drawString(">", textX - 25, textY);
			if(gp.keyH.enterPressed != false) {
				subState = 0;
				commandNum = 1;
			}
		}
		
	}
	
	private void drawGameOverScreen() {
		g2.setColor(new Color(0,0,0,150));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		int x;
		int y;
		String text;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110F));
		
		text = "Game Over";
		//shadow
		g2.setColor(Color.black);
		x = getXforCenteredText(text);
		y = gp.tileSize * 4;
		g2.drawString(text, x, y);
		//main
		g2.setColor(Color.white);
		g2.drawString(text, x-4, y-4);
		//retry
		g2.setFont(g2.getFont().deriveFont(50F));
		text = "RETRY!";
		x = getXforCenteredText(text);
		y += gp.tileSize * 4;
		g2.drawString(text, x, y);
		if(commandNum == 0) {
			g2.drawString(">", x - 40, y);
		}
		//back to titlescreen
		text = "QUIT";
		x = getXforCenteredText(text);
		y += 55;
		g2.drawString(text, x, y);
		if(commandNum == 1) {
			g2.drawString(">", x - 40, y);
		}
	}

	public void drawPacmanLife() {
		
		int x = gp.tileSize/2;
		int y = gp.tileSize/2;
		int i = 0;
		//
		while( i < gp.pacman.maxLife/2) {
			
			g2.drawImage(heart_blank, x, y, null);
			i++;
			x += gp.tileSize;
		}
		
		//reset
		x = gp.tileSize/2;
		y = gp.tileSize/2;
		i = 0;
		
		//draw current life
		while(i < gp.pacman.life){
			g2.drawImage(heart_blank, x, y,null);
			i++;
			if(i < gp.pacman.life) {
				g2.drawImage(heart_full, x, y, null);

			}
			i++;
			x += gp.tileSize;
		}
	}
	public void drawTitleScreen() {
		
		
		g2.setColor(new Color(0, 0,0));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
			
		//TITLE NAME
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110F));
		String text = "Pacman";
		int x = getXforCenteredText(text);
		int y = gp.tileSize * 2;
		g2.setColor(new Color(51, 51,51));
		g2.drawString(text, x, y);
		
		//shadow
		g2.setColor(Color.black);
		g2.drawString(text,x+5,y+5);
		
		//main color
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		
		//draw pacman image
		x = gp.screenWidth/2 - (gp.tileSize * 2)/2;
		y += gp.tileSize * 2;
		g2.drawImage(gp.pacman.right, x, y, gp.tileSize * 2, gp.tileSize * 2, null);
		
		//menu
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,60F));
		text ="PLAY !";
		x = getXforCenteredText(text);
		y += gp.tileSize*4;
		g2.drawString(text, x, y);
		
		if(commandNum == 0) {
			g2.drawString(">", x-gp.tileSize, y);
		}
		
		text ="MULTIPLAYER!";
		x = getXforCenteredText(text);
		y += gp.tileSize*2;
		g2.drawString(text, x, y);
		if(commandNum == 1) { 
			g2.drawString(">", x-gp.tileSize, y);
		}
		
		text ="QUIT :(";
		x = getXforCenteredText(text);
		y += gp.tileSize*2;
		g2.drawString(text, x, y);
		if(commandNum == 2) {
			g2.drawString(">", x-gp.tileSize, y);
		}
		
	}
	public void drawDialogueScreen() {
		//window 
		int x, y, width, height;
		x= gp.tileSize * 2;
		y = gp.tileSize / 2;
		width = gp.screenWidth - (gp.tileSize * 5);		
		height = gp.screenHeight * 4;		
				
		drawSubWindow(x,y,width,height);
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
		x += gp.tileSize;
		y += gp.tileSize;
		
		g2.drawString(currentDialogue, x, y);
	}
	 
	private void drawSubWindow(int x, int y, int width, int height) {
		Color c = new Color(0,0,0,210);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		
		c = new Color(255,255,255);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(1));
		g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
	}

	public int getXforCenteredText(String text) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth / 2 - length / 2;
		return x;
		
	}

}
