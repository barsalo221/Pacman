package Object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Dot extends SuperObject{
	
	
	public Dot() {
		name = "dot";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/tiles/normalDot.png"));
		}catch (IOException e) {
			e.printStackTrace();
		}	
	}
}
