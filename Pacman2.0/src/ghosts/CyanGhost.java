package ghosts;

import java.io.IOException;
import java.util.Random;

import main.GamePanel;

public class CyanGhost extends Ghost {

	public CyanGhost(GamePanel gp) {
		super(gp);
		name ="ghost";
		speed = 3;
		isEdible = false;
		
		solidArea.x = 2;
		solidArea.y = 2;
		solidArea.width = gp.tileSize - 15;
		solidArea.height = gp.tileSize - 15;
		
		solidAreDefaultX = solidArea.x;
		solidAreDefaultY = solidArea.y;
		
		getImage();
	}

	public void getImage() {
		try {
			up = setupImage("/ghosts/CYANUP.png");
			down = setupImage("/ghosts/CYANDOWN.png");
			right = setupImage("/ghosts/CYANRIGHT.png");
			left = setupImage("/ghosts/CYANLEFT.png");
			edibleImage = setupImage("/ghosts/EDIBLEGHOST.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
//	public void setAction() {
//		actionLockCounter++;
//		
//		if(actionLockCounter == 10) {
//			Random random = new Random();
//			int i = random.nextInt(100)+1; // 1 to 100
//			
//			if(i <= 25) {
//				direction = "up";
//			}
//			if(i > 25 && i <= 50) {
//				direction = "down";
//			}
//			if(i > 50 && i <= 75) {
//				direction = "left";
//			}
//			if(i > 75 && i <= 100) {
//				direction = "right";
//			}
//			actionLockCounter = 0;
//			
//		}
//
//	}

}
