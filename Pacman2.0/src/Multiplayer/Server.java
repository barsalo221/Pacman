	package Multiplayer;

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

    final static int PORT = 8888;
    Socket socket;
    ObjectOutputStream outputStream;
    ObjectInputStream inputStream;
    MainMenu mainMenu;
    boolean flag = true;

    public Server() throws InterruptedException {
        serverConnection();
        this.mainMenu = new MainMenu();
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

    public void start() {
        write();
        read();
    }

    private void write() {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {

                    // write a Data object to the client
                    DataServer dataServer = new DataServer(mainMenu.gp);
                    try {
                        outputStream.writeObject(dataServer);
                        System.out.println("Sent data to client: " + dataServer.gp.getName());
                    } catch (IOException ignored) {
                    }

                    // sleep
                    try {
                        sleep(50);
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
                    DataClient dataClient;
                    try {
                        dataClient = (DataClient) inputStream.readObject();
                        System.out.println("Received data from client: " + dataClient.gp.getName());
//                        gameFrame.panel.paddle1.y = dataClient.yPaddle;
                    } catch (IOException | ClassNotFoundException ignored) {
                    }

                    // sleep
                    try {
                        sleep(50);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        });
        thread.start();
    }

    public static void main(String[] args) throws InterruptedException {
    	new Server();
    }
}
