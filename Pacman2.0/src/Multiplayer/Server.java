package Multiplayer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import main.GamePanel;
import main.MainMenu;

public class Server extends Thread{
	public final int port = 4000;
	private ServerSocket ss;
	private int numPlayers;
	private ServerSideConnection player1;
	private ServerSideConnection player2;

	public Server() {
		System.out.println("----GAME SERVER----");
		numPlayers = 0;
		try {
			ss = new ServerSocket(port);
		}catch (Exception e) {
			System.out.println("IOException from GameServer Constractor");
		}
	}
	public void acceptConnections() {
		try {
			System.out.println("WAITING FOR CONNECTIONS...");
			while(numPlayers < 2) {
				Socket s = ss.accept();
				numPlayers++;
				System.out.println("player #" + numPlayers + "has connected!");
				ServerSideConnection ssc = new ServerSideConnection(s, numPlayers);
				if(numPlayers == 1) {
					player1 = ssc;
				}else {
					player2 = ssc;
				}
				Thread t = new Thread(ssc);
				t.start();
				if(numPlayers == 2) {
					
				}else {
					
				}
				}
		System.out.println("we now have 2 player");
		}catch (Exception e) {
			System.out.println("IOException from acceptConnections");
		}
	}
	private class ServerSideConnection implements Runnable{

		private Socket socket;
		private DataInputStream dataIn;
		private DataOutputStream dataOut;
		// ?
		private int playerID;
		
		public ServerSideConnection(Socket s, int id) {
			socket = s;
			playerID = id;
			try {
				dataIn = new DataInputStream(socket.getInputStream());
				dataOut = new DataOutputStream(socket.getOutputStream());

			}catch (IOException e) {
				System.out.println("IOException from SSC constructor");
			}
		}
		
		@Override
		public void run() {
			try {
				dataOut.writeInt(playerID);
				dataOut.flush();
				
				while(true) {
					
				}
			}catch (IOException e) {
				System.out.println("IOException from run() SSC");
			}
		}
		
	}
	public static void main(String[] args) {
		Server server = new Server();
		server.acceptConnections();
	}	
}