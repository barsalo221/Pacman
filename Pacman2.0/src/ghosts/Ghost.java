package ghosts;


import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import Entity.Entity;
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
	public void setAction() {
	
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
		
		
	}
	
}
