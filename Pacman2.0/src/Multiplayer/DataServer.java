package Multiplayer;

import java.io.Serializable;

import main.GamePanel;

public class DataServer implements Serializable {

    private static final long serialVersionUID = 1L;

    GamePanel gp;
   

    public DataServer(GamePanel gp) {
    	this.gp = gp;
    }

}