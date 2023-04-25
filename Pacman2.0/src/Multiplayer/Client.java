package Multiplayer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import main.MainMenu;

public class Client extends Thread{
	
		 
	private Socket socket;
	private DataInputStream dataIn;
	private DataOutputStream dataOut;
	private int playerID;
	private int otherplayer;
	private int scorePlayer = 0;
	private int enemyScore = 0;
	private MainMenu main = new MainMenu();
	
	public Client()
	{		
		System.out.println("----CLIENT----");
		try {
			socket = new Socket("localhost", 4000);
			dataIn = new DataInputStream(socket.getInputStream());
			dataOut = new DataOutputStream(socket.getOutputStream());
			playerID = dataIn.readInt();
			
			System.out.println("Connection to server as player #" + playerID);
			
			}catch (IOException e) {
				System.out.println("IOException from CSC constructor");
			}
	}
	
	public static void main(String[] args) {
		Client c = new Client();
	}
}
