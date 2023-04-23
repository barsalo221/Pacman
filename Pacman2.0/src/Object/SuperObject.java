package Object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;
import main.UtilityTool;

public class SuperObject {
	public BufferedImage image, image2, image3;
	public String name;
	public boolean collision = false;
	public int x,y;
	public Rectangle soildArea = new Rectangle(0,0,39,39);
	public int soildAreaDefaultX = 0;
	public int soildAreaDefaultY = 0;
	UtilityTool uTool = new UtilityTool();

	
	public void draw(Graphics2D g2, GamePanel gp) {
//		int screenX = x - gp.pacman.x;
//		int screenY = y - gp.pacman.y;
//		
//		if( x + gp.tileSize > gp.pacman.x &&
//			x - gp.tileSize > gp.pacman.x &&
//			y + gp.tileSize > gp.pacman.y &&
//			y - gp.tileSize > gp.pacman.y) {
//			
//			g2.drawImage(image, screenX,screenY,gp.tileSize, gp.tileSize, null);
//		}
		g2.drawImage(image, x,y, gp.tileSize,gp.tileSize, null);
		
	}
}
