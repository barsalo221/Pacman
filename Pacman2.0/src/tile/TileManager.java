package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {

	public GamePanel gp;
	public Tile[] tile;
	public int mapTilenum[][];

	public TileManager(GamePanel gamePanel) {
		// TODO Auto-generated constructor stub
		this.gp = gamePanel;
		
		tile = new Tile[20];
		mapTilenum = new int [gp.maxScreenCol][gp.maxScreenRow];
	
		getTileImage();
		loadMap();
	}
	public void loadMap() {
		try {
			InputStream is = getClass().getResourceAsStream("/maps/map_pacman.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
		
			while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
				
				
				String line = br.readLine();
				
				while(col < gp.maxScreenCol) {
					String numbers[] = line.split(" ");
					
					int num = Integer.parseInt(numbers[col]);
					
					mapTilenum[col][row] = num; 
					col++;
				}
				if(col == gp.maxScreenCol) {
					col = 0;
					row++;
				}
				
			}
			br.close();

		} catch (Exception e) {
			
		}
	}
	
	public void getTileImage() {
		try {
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResource("/tiles/TILE_EAT.jpg"));
			
			
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResource("/tiles/TILE_BLOCK.jpg"));
			tile[1].collision = true;
			
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResource("/tiles/TILE_EAT.jpg"));
 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void draw(Graphics2D g2d) {
		int col = 0;
		int row = 0;
		int x = 0;
		int y = 0;
		
		while(col < gp.maxScreenCol && row < gp.maxScreenRow) {
			int tileNum = mapTilenum[col][row];
			
			g2d.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
			col++;
			x += gp.tileSize;
			
			if(col == gp.maxScreenCol) {
				col = 0;
				x = 0;
				row++;
				y += gp.tileSize  ;
			}
			
		}

	}
}