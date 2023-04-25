package multiplayer2;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import main.MainMenu;

public class Client2 extends Thread {

	private int[] previousSettings;
	private String HOST = "localhost";
	static final int PORT = Server2.PORT;
	Socket socket;
	ObjectInputStream inputStream;
	ObjectOutputStream outputStream;
	MainMenu game;

	public Client2(String ipAddress) throws Exception {
		game = new MainMenu();
		HOST = ipAddress;
		clientConnection();
		start();
	}

	private void clientConnection() throws Exception {

		try {
			// create a client socket
			socket = new Socket(HOST, PORT);

			// get the input and output streams
			inputStream = new ObjectInputStream(socket.getInputStream());
			outputStream = new ObjectOutputStream(socket.getOutputStream());

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
 
		sleep(1000);
	}

	@Override
	public void run() {
		write();
		read();
	}

	private void write() {

		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {

					// write a Data object to the client
					Data2 dataClient = new Data2(game);
					try {
						outputStream.writeObject(dataClient);
						System.out.println("Sent data to server: clientScore:  " + dataClient.clientScore);
						System.out.println("Sent data to server: enemyScore: " + dataClient.enemyScore);
					} catch (IOException ignored) {
					}

					// sleep
					try {
						sleep(1000);
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
					}

				}
			}
		});
		thread.start();
	}

	private void read() {

		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {

					// read a Data object from the client
					Data2 dataServer;
					try {
						dataServer = (Data2) inputStream.readObject();
						System.out.println("recieved data from server: clientScore: " + dataServer.clientScore);
						System.out.println("recieved data from server: enemyScore: " + dataServer.enemyScore); 
						game.gp.pacman.enemyScore = dataServer.clientScore;
					} catch (IOException | ClassNotFoundException ignored) {
					}

					// sleep
					try {
						sleep(1000);
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
					}

			}
		}});
		thread.start();
	}

	public static void main(String[] args) {
		String ip = "localhost";
		try {
			Client2 clientGame = new Client2(ip);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
