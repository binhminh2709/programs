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
     * Socket input stream.
     */
    private InputStream inpStream;
    /**
     * Socket output stream.
     */
    private OutputStream outStream;
    /**
     * Data input stream.
     */
    private DataInputStream dataInpStream;
    /**
     * Data output stream.
     */
    private DataOutputStream dataOutStream;
    /**
     * Object input stream.
     */
    private ObjectInputStream objInpStream;

    /**
     * Constructor. Get port, address and working directory.
     * @param inputStream - input stream.
     * @param outputStream - output stream.
     */
    public StartServer(InputStream inputStream, OutputStream outputStream) {

        this.inpStream = inputStream;
        this.outStream = outputStream;
    }
    /**
     * Set up connection.
     * @throws IOException - exception.
     * @throws ClassNotFoundException - exception.
     */
    public void listen() throws IOException, ClassNotFoundException {

        this.dataInpStream = new DataInputStream(this.inpStream);
        this.dataOutStream = new DataOutputStream(this.outStream);
        this.objInpStream = new ObjectInputStream(this.inpStream);

        this.dataOutStream.writeUTF("<Ok>");
        this.dataOutStream.flush();

        ServerMenu serverMenu = new ServerMenu(this.dataInpStream, this.dataOutStream);
        serverMenu.fillMenu();

        while (true) {
            Command command = (Command) objInpStream.readObject();
            serverMenu.choice(command);
        }
    }
    /**
     * Main method.
     * @param args - no arguments.
     * @throws IOException - exception.
     * @throws ClassNotFoundException - exception.
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        /**
         * Connection port.
         */
        ServerSettings serverSettings = new ServerSettings();
        int port = serverSettings.getPort();

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            Socket connection = serverSocket.accept();
            InputStream socketInputStream = connection.getInputStream();
            OutputStream socketOutputStream = connection.getOutputStream();
            StartServer startServer = new StartServer(socketInputStream, socketOutputStream);
            startServer.listen();
        } catch (IOException | ClassNotFoundException exc) {
            exc.printStackTrace();
        }
    }
}