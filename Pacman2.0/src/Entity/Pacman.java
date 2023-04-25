package Entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import main.GamePanel;
import main.KeyHandler;

public class Pacman extends Entity {
	 
	protected KeyHandler keyH;
	public int life; 
	public int maxLife;
	
	// max score is 225
	public int score;
	public int maxScore = 225;
	public int enemyScore;
	
	
	public Pacman(GamePanel gp, KeyHandler keyH) {
		super(gp);
		this.keyH = keyH;
		
		solidArea = new Rectangle(2,2, gp.tileSize - 20,gp.tileSize-20);
		solidAreDefaultX = solidArea.x;
		solidAreDefaultY = solidArea.y;
		
		setDefaultValues();
		getPacmanImage();
	}
		
	public void setDefaultValues()
	{
		this.x = 100;
		this.y = 100;
		this.speed = 3;
		direction = "down";
		score = 0;
		//pacman status
		maxLife = 5;
		life = maxLife;
		invincible = false;
	}
	public void setDefaultPositions() {
		this.x = 100;
		this.y = 100;
		direction = "down";
	}
	public void restoreLife() {
		life = maxLife; 
		invincible = false;
	}

	public void getPacmanImage() {
		try {
		up = setupImage("/pacman/PacmanUp.png");
		down = setupImage("/pacman/PacmanDown.png");
		left = setupImage("/pacman/PacmanLeft.png");
		right = setupImage("/pacman/PacmanRight.png");

		} catch (IOException e) {
			// TODO: handle exception
		}
	}
	
	
	public void update() {
		if(keyH.upPressed == true) {
			direction ="up";
		}
		else if (keyH.downPressed == true) {
			direction ="down";
		}
		else if (keyH.rightPressed == true) {
			direction ="right";
		}
		else if (keyH.leftPressed == true) {
			direction ="left";
		}
		// check tile collision
		collisionOn = false;
		gp.cChecker.checkTile(this);
		
		//check dots collision
		int dotIndex = gp.cChecker.checkDot(this, true);
		eatDot(dotIndex);
		
		//check ghosts collision
		int ghostIndex = gp.cChecker.checkEntity(this, gp.ghosts);
		contactGhost(ghostIndex);
		//		interactGhost(ghostIndex);
		//check event
		gp.eHandler.checkEvent();
		
		//if collision is false, player can move
		if(collisionOn == false) {
			switch (direction) {
			case "up":
				y -= speed;
				break;
			case "down":
				y += speed;
				break;
			case "left":
				x -= speed;
				break;
			case "right":
				x += speed;
				break;
			}
		}
		if(invincible == true) {
			invincibleCounter++;
			System.out.println("invincibleCounter: " + invincibleCounter);
			if(invincibleCounter > 10 ) {
				invincible = false;
				invincibleCounter = 0; 
			}
		}
		if(life <= 0) {
			gp.gameState = gp.gameOverState;
			gp.playSE(3);
		}
	}
	

	public void contactGhost(int i) {
		if(i != 999) {
			collisionOn = true;
		}
	}

	public void eatDot(int i) {
		if(i != 999) {
			String objectName = gp.obj[i].name;
			 
			switch(objectName){
			case "dot":
				score++;
				gp.obj[i] = null;
				break;
			case "megadot":
				score += 10;
				gp.obj[i] = null;
				break;
			
			}
		}
	}
//	public void interactGhost(int i) {
//		if(i != 999) {
//			gp.gameState = gp.dialogueState;
//			gp.ghosts[i].speak();
//			
//			}
//	}
	
	public void draw(Graphics2D g2d) {
//		g2d.setColor(Color.white);
//		
//		g2d.fillRect(x, y, gp.tileSize, gp.tileSize);
		BufferedImage image = null;
		
		switch (direction) {
		case "up": image = up; break;
		case "left": image = left; break;
		case "down": image = down; break;
		case "right": image = right; break;
		}
		g2d.drawImage(image, x, y, gp.originalTileSize * (gp.scale - 1), gp.originalTileSize * (gp.scale - 1), null);
	}
}
