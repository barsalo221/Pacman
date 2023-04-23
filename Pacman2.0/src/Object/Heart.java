package Object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Heart extends SuperObject {
	
	GamePanel gp;
	
	public Heart(GamePanel gp) {
		this.gp = gp;
		
		name = "heart";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/heart_full.png"));
			image2 = ImageIO.read(getClass().getResourceAsStream("/objects/heart_blank.png"));
		
			image = uTool.scaleImage(image, gp.tileSize,gp.tileSize);
			image2 = uTool.scaleImage(image2, gp.tileSize,gp.tileSize);

		}catch (IOException e) {
			// TODO: handle exception
		}
	}

}
