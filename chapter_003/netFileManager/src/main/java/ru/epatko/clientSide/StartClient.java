package ru.epatko.clientSide;

import ru.epatko.serverSide.*;

import java.io.*;
import java.net.Socket;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         01.01.17.
 */
public class StartClient {

    /**
     * Input method.
     */
    private Input inputMethod;
    /**
     * Connection address.
     */
    private String address;
    /**
     * Connection port.
     */
    private int port;
    /**
     * Socket input stream.
     */
    private InputStream inpStream;
    /**
     * Socket output stream.
     */
    private OutputStream outStream;

    /**
     * Constructor. Get port and address.
     * @param inputMethod - input method.
     */
    public StartClient(Input inputMethod) {
        this.inputMethod = inputMethod;
        ServerSettings serverSettings = new ServerSettings();
        this.address = serverSettings.getAddress();
        this.port = serverSettings.getPort();
    }

    /**
     * Getter.
     * @return - connection address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Getter.
     * @return - connection port number.
     */
    public int getPort() {
        return port;
    }

    /**
     * Set up connection.
     * @throws IOException - exception.
     * @throws ClassNotFoundException - exception.
     */
    public void connect() throws IOException, ClassNotFoundException {

        try (Socket connection = new Socket(this.address, this.port)) {

            this.inpStream = connection.getInputStream();
            this.outStream = connection.getOutputStream();
            DataInputStream dataInpStream = new DataInputStream(inpStream);

            ClientMenu clientMenu = new ClientMenu(this.inpStream, this.outStream);
            clientMenu.fillMenu();

            if ("<Ok>".equals(dataInpStream.readUTF())) {
                System.out.println(" You have connection to the server.");
                Command command = new Command("help");
                clientMenu.choice(command);

                while (true) {

                    command = new Command(this.inputMethod.message());
                    clientMenu.choice(command);
                }
            } else {
                System.out.println(" Connection failed.");
            }
        } catch (IOException | ClassNotFoundException exc) {
                exc.printStackTrace();
        }
    }
    /**
     * Main method.
     * @param args - no arguments.
     * @throws IOException - exception.
     * @throws ClassNotFoundException - exception.
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Input inputMethod = new ConsoleInput();
        try {
            StartClient client = new StartClient(inputMethod);
            System.out.println(" ---------------------------------------------------------");
            System.out.println("| Wait connection message and use server commands.        |");
            System.out.println(" ---------------------------------------------------------");
            client.connect();
        } catch (IOException | ClassNotFoundException exc) {
            exc.printStackTrace();
        }
    }

}
