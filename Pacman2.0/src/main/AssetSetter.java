package main;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import Object.Dot;
import Object.MegaDot;
import ghosts.CyanGhost;
import ghosts.OrangeGhost;
import ghosts.PinkGhost;
import ghosts.RedGhost;

public class AssetSetter {
	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;		
	}
	
	public void setObject() {
		try {
			InputStream is = getClass().getResourceAsStream("/maps/map_pacman.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			int i = 0;
		
			while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
				
				
				String line = br.readLine();
				
				while(col < gp.maxScreenCol) {
					String numbers[] = line.split(" ");
					
					int num = Integer.parseInt(numbers[col]);
					if(num == 0 ) {
						gp.obj[i] = new Dot();
						gp.obj[i].x = col * gp.tileSize;
						gp.obj[i].y = row * gp.tileSize;
						i++;
					}
					 
					if(num == 2) {
						gp.obj[i]= new MegaDot();
						gp.obj[i].x = col * gp.tileSize;
						gp.obj[i].y = row * gp.tileSize;
						i++;
					}
					
					col++;
				}
				if(col == gp.maxScreenCol) {
					col = 0;
					row++;
				}
				
			}
			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void setGhosts() {
		gp.ghosts[0] = new RedGhost(gp);
		gp.ghosts[0].x = gp.tileSize * 2;
		gp.ghosts[0].y = gp.tileSize * 1;
		
		gp.ghosts[1] = new PinkGhost(gp);
		gp.ghosts[1].x = gp.tileSize * 18;
		gp.ghosts[1].y = gp.tileSize * 1;
		
		gp.ghosts[2] = new CyanGhost(gp);
		gp.ghosts[2].x = gp.tileSize * 1;
		gp.ghosts[2].y = gp.tileSize * 19;
		
		gp.ghosts[3] = new OrangeGhost(gp);
		gp.ghosts[3].x = gp.tileSize * 18;
		gp.ghosts[3].y = gp.tileSize * 18;

	}
}
