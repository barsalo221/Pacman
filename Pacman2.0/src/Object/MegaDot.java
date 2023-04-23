package Object;

import java.io.IOException;

import javax.imageio.ImageIO;
 
public class MegaDot extends SuperObject {

	public MegaDot() {
		name = "megadot";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/tiles/Mega_Dot.png"));
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
