package multiplayer2;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import main.MainMenu;

public class Server2 extends Thread {
	public final static int PORT = 8888;
	Socket socket;
	ObjectOutputStream outputStream;
	ObjectInputStream inputStream;
	MainMenu game;

	public Server2() throws InterruptedException {
		game = new MainMenu();
		serverConnection();
		start();
	}

	private void serverConnection() throws InterruptedException {

		try {
			// create a server socket
			try (ServerSocket serverSocket = new ServerSocket(PORT)) {

				// wait for a client to connect
				socket = serverSocket.accept();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}

			// get the input and output streams
			outputStream = new ObjectOutputStream(socket.getOutputStream());
			inputStream = new ObjectInputStream(socket.getInputStream());

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
					Data2 dataServer = new Data2(game);
					try {
						outputStream.writeObject(dataServer);
						System.out.println("Sent data to client: clientScore: " + dataServer.clientScore);
						System.out.println("Sent data to client: enemyScore: " + dataServer.enemyScore);
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
					Data2 dataClient;
					try {
						dataClient = (Data2) inputStream.readObject();
						System.out.println("recieved data from client: score: " + dataClient.clientScore);
						System.out.println("recieved data from client: enemyScore: " + dataClient.enemyScore);
						game.gp.pacman.enemyScore = dataClient.enemyScore;
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
		try {
			Server2 game = new Server2();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}