package main;

import java.lang.annotation.Target;

import Entity.Entity;
import Entity.Pacman;

public class CollisionChekers {
	

	private GamePanel gp;
	
	public CollisionChekers(GamePanel gp) {
		this.gp = gp;
		}
	
	public void checkTile(Entity entity) {
		
		int entityLeftWorldX = entity.x + entity.solidArea.x;
		int entityRightWorldx = entity.x + entity.solidArea.x + entity.solidArea.width;

		int entityTopWorldY = entity.y + entity.solidArea.y;
		int entityButtomWorldY = entity.y + entity.solidArea.y + entity.solidArea.height;
		
		// COL AND ROW
		
		int entityLeftCol = entityLeftWorldX / gp.tileSize;
		int entityRightCol = entityRightWorldx / gp.tileSize;
		 
		int entityTopRow = entityTopWorldY / gp.tileSize;
		int entityBottomRow = entityButtomWorldY / gp.tileSize;

		int tileNum1, tileNum2;
		
		switch (entity.direction) {
		case "up":
			entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
			tileNum1 = gp.tileM.mapTilenum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTilenum[entityRightCol][entityTopRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "down":
			entityBottomRow = (entityButtomWorldY + entity.speed) / gp.tileSize;
			tileNum1 = gp.tileM.mapTilenum[entityLeftCol][entityBottomRow];
			tileNum2 = gp.tileM.mapTilenum[entityRightCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break; 
		case "left":
			entityLeftCol= (entityLeftWorldX - entity.speed) / gp.tileSize;
			tileNum1 = gp.tileM.mapTilenum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTilenum[entityLeftCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "right":
			entityRightCol = (entityRightWorldx + entity.speed) / gp.tileSize;
			tileNum1 = gp.tileM.mapTilenum[entityRightCol][entityTopRow];
			tileNum2 = gp.tileM.mapTilenum[entityRightCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		}
	}
	
	public int checkDot(Pacman pacman, boolean ispacman) {
		int index = 999;
		
		for(int i = 0; i< gp.obj.length; i++) {
			if(gp.obj[i] != null) {
				//get pacman's solid area position
				pacman.solidArea.x = pacman.x + pacman.solidAreDefaultX;
				pacman.solidArea.y = pacman.y + pacman.solidAreDefaultY;
				//get the Dot's soild area position
				gp.obj[i].soildArea.x = gp.obj[i].x + gp.obj[i].soildArea.x;
				gp.obj[i].soildArea.y = gp.obj[i].y + gp.obj[i].soildArea.y;

				switch (pacman.direction) {
				case "up":pacman.solidArea.y -= pacman.speed;break;
				case "down":pacman.solidArea.y += pacman.speed; break;
				case "left":pacman.solidArea.x -= pacman.speed;break;
				case "right":pacman.solidArea.x += pacman.speed; break;
				}
				
				if(pacman.solidArea.intersects(gp.obj[i].soildArea)){
					if(gp.obj[i].collision == true) {
						pacman.collisionOn = true;
					}
					if(ispacman == true) {
						index = i;
					}
				}
				
				pacman.solidArea.x = pacman.solidAreDefaultX;
				pacman.solidArea.y = pacman.solidAreDefaultY;
				gp.obj[i].soildArea.x = gp.obj[i].soildAreaDefaultX;
				gp.obj[i].soildArea.y = gp.obj[i].soildAreaDefaultY;

			}
		}
		
		return index;
		
	}
	public int checkEntity(Entity pacman, Entity[] ghoststemp) {
		int index = 999;
		
		for(int i = 0; i< ghoststemp.length; i++) {
			if(ghoststemp[i] != null) {
				//get pacman's solid area position
				pacman.solidArea.x = pacman.x + pacman.solidAreDefaultX;
				pacman.solidArea.y = pacman.y + pacman.solidAreDefaultY;
				//get the ghost's soild area position
				ghoststemp[i].solidArea.x = ghoststemp[i].x + ghoststemp[i].solidArea.x;
				ghoststemp[i].solidArea.y = ghoststemp[i].y + ghoststemp[i].solidArea.y;

				switch (pacman.direction) {
				case "up":pacman.solidArea.y -= pacman.speed;	break;
				case "down":pacman.solidArea.y += pacman.speed;break;
				case "left":pacman.solidArea.x -= pacman.speed;break;
				case "right":pacman.solidArea.x += pacman.speed;break;
				}
				
				if(pacman.solidArea.intersects(ghoststemp[i].solidArea)){
					if(ghoststemp[i] != pacman) {
						pacman.collisionOn = true;
						index = i;
					}
				}
				pacman.solidArea.x = pacman.solidAreDefaultX;
				pacman.solidArea.y = pacman.solidAreDefaultY;
				ghoststemp[i].solidArea.x = ghoststemp[i].solidAreDefaultX;
				ghoststemp[i].solidArea.y = ghoststemp[i].solidAreDefaultY;

			}
		}
		
		return index;		
	}
	public boolean checkPacman(Entity entity) {
		
		boolean contactPacman = false;
		
		//get ghost's solid area position
		entity.solidArea.x = entity.x + entity.solidAreDefaultX;
		entity.solidArea.y = entity.y + entity.solidAreDefaultY;
		//get the pacman's soild area position
		gp.pacman.solidArea.x = gp.pacman.x + gp.pacman.solidArea.x;
		gp.pacman.solidArea.y = gp.pacman.y + gp.pacman.solidArea.y;

		switch (entity.direction) {
		case "up": entity.solidArea.y -= entity.speed; break;
		case "down": entity.solidArea.y += entity.speed; break;
		case "left": entity.solidArea.x -= entity.speed; break;
		case "right": entity.solidArea.x += entity.speed; break;
		}
		
		if(entity.solidArea.intersects(gp.pacman.solidArea)){
			entity.collisionOn = true;
			contactPacman = true;
		}
		
		entity.solidArea.x = entity.solidAreDefaultX;
		entity.solidArea.y = entity.solidAreDefaultY;
		gp.pacman.solidArea.x = gp.pacman.solidAreDefaultX;
		gp.pacman.solidArea.y = gp.pacman.solidAreDefaultY;
		
		return contactPacman;
	}


}
