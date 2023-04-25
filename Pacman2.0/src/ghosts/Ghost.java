package ghosts;


import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import Entity.Entity;
import Entity.Pacman;
import main.GamePanel;

public class Ghost extends Entity {
	
	public boolean isEdible;
	public BufferedImage edibleImage;
	
	public Ghost(GamePanel gp)
	{
		super(gp);
		
		type = 1;
		name ="ghost";
		speed = 5;
		isEdible = false;
		
		solidArea.x = 2;
		solidArea.y = 2;
		solidArea.width = gp.tileSize - 15;
		solidArea.height = gp.tileSize - 15;
		
		solidAreDefaultX = solidArea.x;
		solidAreDefaultY = solidArea.y;
		
		getImage();
//		setDialogue();
	}
//	public void setDialogue() {
//		dialogues[0] = "you dead!!, press enter to restart";
//	}
//	public void speak() { 
//		gp.ui.currentDialogue = dialogues[0];
//	}
	
	public void getImage() {
		try {
			up = setupImage("/ghosts/REDUP.png");
			down = setupImage("/ghosts/REDDOWN.png");
			right = setupImage("/ghosts/REDRIGHT.png");
			left = setupImage("/ghosts/REDLEFT.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void setAction(Pacman pacman) {
		
		actionLockCounter++;
		if(actionLockCounter == 10) {
			Random random = new Random();
			int i = random.nextInt(100)+1; // 1 to 100
			
			if(i <= 25) {
				direction = "up";
			}
			if(i > 25 && i <= 50) {
				direction = "down";
			}
			if(i > 50 && i <= 75) {
				direction = "left";
			}
			if(i > 75 && i <= 100) {
				direction = "right";
			}
			actionLockCounter = 0;
		}

//		actionLockCounter++;
//		if(actionLockCounter == 10) {
//			double ghostCol = this.x + this.solidArea.x;
//			double ghostRow = this.y + this.solidArea.y;
//			double pacmanCol = pacman.x + pacman.solidAreDefaultX;;
//			double pacmanRow = pacman.y + pacman.solidAreDefaultY;;
//			double sumRight = 0;
//			double sumLeft = 0;
//			double sumUp = 0;
//			double sumDown = 0;
//			if(gp.tileM.mapTilenum[(this.x + this.solidArea.x) + this.speed][this.y + this.solidArea.y] != 1) {
//				sumRight = Math.sqrt(Math.pow((ghostCol + this.speed) - pacmanCol, 2) + Math.pow(ghostRow - pacmanRow, 2));
//			}
//			else if(gp.tileM.mapTilenum[(this.x + this.solidArea.x) - this.speed][this.y + this.solidArea.y] != 1) {
//				sumLeft = Math.sqrt(Math.pow((ghostCol - this.speed) - pacmanCol, 2) + Math.pow(ghostRow - pacmanRow, 2));
//			}
//			else if(gp.tileM.mapTilenum[this.x + this.solidArea.x][(this.y + this.solidArea.y) + this.speed] != 1) {
//				sumUp = Math.sqrt(Math.pow(ghostCol - pacmanCol, 2) + Math.pow((ghostRow + this.speed) - pacmanRow, 2));
//			}
//			else if(gp.tileM.mapTilenum[this.x + this.solidArea.x][(this.y + this.solidArea.y) - this.speed] != 1) {
//				sumDown = Math.sqrt(Math.pow(ghostCol - pacmanCol, 2) + Math.pow((ghostRow - this.speed) - pacmanRow, 2));
//			}
//			if(sumRight > sumDown || sumRight > sumLeft || sumRight > sumUp) {
//				direction = "right";
//			}
//			if(sumLeft > sumRight || sumLeft > sumDown || sumLeft > sumUp) {
//				direction = "left";
//			}
//			if(sumUp > sumDown || sumUp > sumLeft || sumUp > sumRight) {
//				direction = "up";
//			}
//			if(sumDown > sumRight || sumDown > sumLeft || sumDown > sumUp) {
//				direction = "down";
//			}
//			actionLockCounter = 0;	
//		}		
	}
	
	
//	public int dist(int x, int y) {
//		
//		int distSum = (x*x) +(y*y);
//		
//		return distSum;
//	}
	
}
