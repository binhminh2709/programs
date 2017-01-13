package ru.epatko.serverSide;

import java.io.*;
import java.net.*;

/**
 * Server side of net file manager.
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         01.01.17.
 */

public class StartServer {

    /**
     * Connection port.
     */
    private int port;
    /**
     * Data input stream.
     */
    private DataInputStream in;
    /**
     * Data output stream.
     */
    private DataOutputStream out;

    /**
     * Socket input stream.
     */
    private InputStream socketInputStream;
    /**
     * Socket output stream.
     */
    private OutputStream socketOutputStream;
    /**
     * Constructor. Get port, address and working directory.
     */
    public StartServer() {
        ServerSettings serverSettings = new ServerSettings();
        this.port = serverSettings.getPort();
    }

    /**
     *
     * @return - connection port number.
     */
    public int getPort() {
        return this.port;
    }

    /**
     * Set up connection.
     * @throws IOException - exception.
     */
    public void connect() throws IOException {

        try (ServerSocket serverSocket = new ServerSocket(this.port)) {
            try (Socket connection = serverSocket.accept()) {

                this.socketInputStream = connection.getInputStream();
                this.socketOutputStream = connection.getOutputStream();
                this.in = new DataInputStream(this.socketInputStream);
                this.out = new DataOutputStream(this.socketOutputStream);

                ServerMenu serverMenu = new ServerMenu(this.in, this.out);
                serverMenu.fillMenu();
                /**
                 * Array of server actions info.
                 */
                String[] serverActions = serverMenu.getActions();

                ObjectOutputStream oos = new ObjectOutputStream(this.socketOutputStream);
                oos.writeObject(serverActions);
                oos.flush();

                while (true) {
                    Command command = new Command(this.in.readUTF());
                    serverMenu.choice(command);
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    /**
     * Main method.
     * @param args - no arguments.
     * @throws IOException - exception.
     */
    public static void main(String[] args) throws IOException {
        try {
            StartServer startServer = new StartServer();
            startServer.connect();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}