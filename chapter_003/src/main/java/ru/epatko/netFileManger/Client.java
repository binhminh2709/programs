package ru.epatko.netFileManger;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         01.01.17.
 */
public class Client {

    /**
     * Connection address.
     */
    private String address;
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
     * Console input scanner.
     */
    private Scanner scanner;
    /**
     * Client message.
     */
    private String clientMessage;
    /**
     * Array to parsing client message.
     */
    private String[] command;
    /**
     * Server message.
     */
    private String serverMessage;
    /**
     * Socket input stream.
     */
    private InputStream socketInputStream;
    /**
     * Socket output stream.
     */
    private OutputStream socketOutputStream;
    /**
     * Buffer size.
     */
    private final int bufferSize = 64 * 1024;


    /**
     * Constructor. Get port and address.
     */
    public Client() {
        Settings settings = new Settings();
        this.address = settings.getAddress();
        this.port = settings.getPort();
    }

    /**
     * @return - connection address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * @return - connection port number.
     */
    public int getPort() {
        return port;
    }

    /**
     * Set up connection.
     * @throws IOException - exception.
     */
    public void connect() throws IOException {

        try (Socket connection = new Socket(this.address, this.port)) {

            this.socketInputStream = connection.getInputStream();
            this.socketOutputStream = connection.getOutputStream();

           this.in = new DataInputStream(this.socketInputStream);
            this.out = new DataOutputStream(this.socketOutputStream);

            this.scanner = new Scanner(System.in);
            System.out.println(in.readUTF());

            while (true) {

                System.out.print(">: ");
                this.clientMessage = scanner.nextLine();
                this.command = this.clientMessage.split(" ");

                if (this.command[0].equals("dl") && this.command.length != 1) {
                    downloadFile();
                    continue;
                }

                if (this.command[0].equals("ul") && this.command.length != 1) {
                    uploadFile();
                    continue;
                }

                if (this.command[0].equalsIgnoreCase("help")) {
                    help();
                    continue;
                }
                if (this.command[0].equalsIgnoreCase("exit")) {
                    break;
                }

                this.out.writeUTF(clientMessage);
                this.out.flush();

                serverMessage = in.readUTF();
                System.out.println(serverMessage);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Download file from server.
     * @throws IOException - exception.
     */
    public void downloadFile() throws IOException {

        this.out.writeUTF(clientMessage);
        this.out.flush();

        if ("<ok>".equals(this.in.readUTF())) {
            System.out.print("File downloading...");

            String[] temp = command[1].split(System.getProperty("file.separator"));
            String fileName = temp[temp.length - 1];

            byte[] data = new byte[bufferSize];
            int count;
            try (BufferedOutputStream fileWriter = new BufferedOutputStream(new FileOutputStream(fileName))) {
                int enumerator = this.in.readInt();
                for (int i = 0; i < enumerator; i++) {
                    count = this.in.read(data);
                    fileWriter.write(data, 0, count);
                    System.out.print(".");
                }
                System.out.println(" Ok.");
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        } else {
            System.out.println("Incorrect command or FILE can't be downloaded.");
        }
    }
    /**
     * Upload file to server.
     * @throws IOException - exception.
     */
    public void uploadFile() throws IOException {
        Path pathSource = Paths.get(command[1]);
        if (Files.exists(pathSource, LinkOption.NOFOLLOW_LINKS) && Files.isReadable(pathSource)
            && !Files.isDirectory(pathSource)) {

            try (BufferedInputStream fileReader = new BufferedInputStream(new FileInputStream(pathSource.toFile()))) {

                System.out.print("File uploading...");

                String fileName = pathSource.getFileName().toString();
                this.out.writeUTF(String.format("ul %s", fileName));
                this.out.flush();

                byte[] data = new byte[bufferSize];
                int count;
                long fileLength = pathSource.toFile().length();
                int enumerator = (fileLength % bufferSize == 0) ? (int) (fileLength / bufferSize)
                                                                : (int) (fileLength / bufferSize + 1);
                this.out.writeInt(enumerator);
                this.out.flush();

                for (int i = 0; i < enumerator; i++) {
                    count = fileReader.read(data);
                    this.out.write(data, 0, count);
                    System.out.print(".");
                }
                this.out.flush();
                System.out.println(" Ok.");
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        } else {
            System.out.println("Incorrect command or FILE can't be uploaded.");
        }
    }

    /**
     * Print help instruction.
     */
    public void help() {
        System.out.println(" ---------------------------------------------------------");
        System.out.println("| ls [DIRECTORY] - view DIRECTORY list;                   |");
        System.out.println("| cd [DIRECTORY] - change current directory to DIRECTORY; |");
        System.out.println("| dl [FILE] - download FILE from server;                  |");
        System.out.println("| ul [FILE] - upload FILE to server;                      |");
        System.out.println("| exit - close connection and exit program;               |");
        System.out.println("| help - show this help.                                  |");
        System.out.println(" ---------------------------------------------------------");
    }

    /**
     * Main method.
     * @param args - no arguments.
     * @throws IOException - exception.
     */
    public static void main(String[] args) throws IOException {

        try {
            Client client = new Client();
            System.out.println(" ---------------------------------------------------------");
            System.out.println("| Wait connection message and use next command:           |");
            client.help();
            client.connect();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

}
