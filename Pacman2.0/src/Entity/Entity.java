package Entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Entity {
	
	public int x,y;
	public int speed;
	public boolean invincible = false;
	public int invincibleCounter = 0;
	public BufferedImage up, down,right,left;
	public String direction;
	public Rectangle solidArea;
	public int solidAreDefaultX, solidAreDefaultY;
	public boolean collisionOn = false;
	public String name;
	protected GamePanel gp;
	public int actionLockCounter = 0;
	public int type; // 0 = pacman 1 = ghost

//	protected String dialogues[] = new String[20];


	public Entity(GamePanel gp) {
		this.gp = gp;
		solidArea = new Rectangle();
		direction = "down";
		// TODO Auto-generated constructor stub
		}
	public BufferedImage setupImage(String s) throws IOException {
		BufferedImage bi = ImageIO.read(getClass().getResourceAsStream(s));
		return bi;
	}
	public void speak() {
	}
	public void draw(Graphics2D g2) {
		BufferedImage image = null;

		switch (direction) {
		case "up": image = up; break;
		case "left": image = left; break;
		case "down": image = down; break;
		case "right": image = right; break;
		}
		g2.drawImage(image, x,y, gp.originalTileSize * (gp.scale - 1),gp.originalTileSize * (gp.scale - 1), null);
	}
	
	public void setAction(Pacman pacman) {}
	public void update() {
		setAction(gp.pacman);
		collisionOn = false;
		gp.cChecker.checkTile(this);
		gp.cChecker.checkDot(gp.pacman, false);
		gp.cChecker.checkEntity(this, gp.ghosts);
		gp.cChecker.checkPacman(this);
		boolean contactPacman = gp.cChecker.checkPacman(this);
		
		if(this.type == 1 && contactPacman == true) {
			if(gp.pacman.invincible == false) {
				gp.pacman.life--;
				gp.pacman.invincible = true;
				gp.retry();
			}
		}
		
		
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
	}
}
