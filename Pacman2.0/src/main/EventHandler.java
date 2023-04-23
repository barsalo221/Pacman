package main;

public class EventHandler {
	GamePanel gp;
	EventRect eventRect[][]; 
	  
	public EventHandler(GamePanel gp) {
		this.gp = gp;
		
		eventRect = new EventRect[gp.maxScreenCol][gp.maxScreenRow];
		
		int col = 0;
		int row = 0;
		while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
			
			eventRect[col][row] = new EventRect();
			eventRect[col][row].x = 23;
			eventRect[col][row].y = 23;
			eventRect[col][row].width = 2;
			eventRect[col][row].height = 2;
			
			eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
			eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;
			
			col++;
			if(col == gp.maxScreenCol) {
				col = 0;
				row++;
			}
		}
	}
	
	public void checkEvent() {
		if(hit(0,9,"left") == true) {
			teleport(19, 9);
		}
		else if(hit(19,9,"right") == true) {
			teleport(1, 9);
		}
	
	}
	
	public boolean hit(int col, int row, String reqDirection) {
		
		boolean hit = false;
		gp.pacman.solidArea.x = gp.pacman.x + gp.pacman.solidArea.x;
		gp.pacman.solidArea.y = gp.pacman.y + gp.pacman.solidArea.y;
		
		eventRect[col][row].x = col*gp.tileSize + eventRect[col][row].x; 
		eventRect[col][row].y = row*gp.tileSize + eventRect[col][row].y; 

		if(gp.pacman.solidArea.intersects(eventRect[col][row])) {
			if(gp.pacman.direction.contentEquals(reqDirection)  || reqDirection.contentEquals("any")) {
				hit = true;
			}	
		}
		 
		gp.pacman.solidArea.x = gp.pacman.solidAreDefaultX;
		gp.pacman.solidArea.y = gp.pacman.solidAreDefaultY;
		eventRect[col][row].x = eventRect[col][row].eventRectDefaultX; 
		eventRect[col][row].y = eventRect[col][row].eventRectDefaultY; 

		return hit;
	} 
		
	public void teleport(int x, int y) {
		gp.ui.currentDialogue ="Teleport dude!";
		gp.pacman.x = gp.tileSize * x;
		gp.pacman.y = gp.tileSize * y;
	}
	

}
