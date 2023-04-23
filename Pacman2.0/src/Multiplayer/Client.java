package Multiplayer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import main.GamePanel;
import main.MainMenu;

public class Client extends Thread{
    private String HOST = "localhost";
    static final int PORT = Server.PORT;
    Socket socket;
    ObjectInputStream inputStream;
    ObjectOutputStream outputStream;
    MainMenu mainMenu;


    public Client(String ipAddress) throws Exception {
        HOST = ipAddress;
        clientConnection();
        this.mainMenu = new MainMenu();
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

    public void start() {
        read();
        write();
    }

    private void write() {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {

                    // write a Data object to the server
                    DataClient dataClient = new DataClient(mainMenu.gp);
                    try {
                        outputStream.writeObject(dataClient);
                        System.out.println("Sent data to server: " + mainMenu.gp.getName());
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

                    // read a Data object from the server
                    DataServer dataServer = null;
                    try {
                        dataServer = (DataServer) inputStream.readObject();
                        System.out.println("Received data from server: " + dataServer.gp.getName());
                        mainMenu.gp.pacman.score = dataServer.gp.pacman.score;
                        mainMenu.gp.pacman.x = dataServer.gp.pacman.x;
                        mainMenu.gp.pacman.y = dataServer.gp.pacman.y;
                        mainMenu.gp.pacman.speed = dataServer.gp.pacman.speed;
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


    public static void main(String[] args) throws Exception {

        Client client = new Client("localhost");
    }}
