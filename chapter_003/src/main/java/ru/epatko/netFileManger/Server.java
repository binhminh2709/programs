package ru.epatko.netFileManger;

import java.io.*;
import java.nio.file.*;
import java.nio.file.Path;
import java.net.*;

/**
 * Server side of net file manager.
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         01.01.17.
 */

public class Server {

    /**
     * Connection port.
     */
    private int port;
    /**
     * Server working directory.
     */
    private Path workPath;
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
     * Buffer size.
     */
    private final int bufferSize = 64 * 1024;

    /**
     * Constructor. Get port, address and working directory.
     */
    public Server() {
        Settings settings = new Settings();
        this.port = settings.getPort();
        this.workPath = Paths.get(System.getProperty("user.dir"));
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

                String userMessage;
                String[] command;

                this.out.writeUTF("You have connection to the server.");
                this.out.flush();

                while (true) {
                    userMessage = this.in.readUTF();
                    command = userMessage.split(" ");

                    if (command.length == 2) {

                        if (command[0].equals("ls")) {
                            listDirectory(command[1]);
                            continue;

                        } else if (command[0].equals("cd")) {
                            changeDirectory(command[1]);
                            continue;

                        } else if (command[0].equals("dl")) {
                            download(command[1]);
                            continue;

                        } else if (command[0].equals("ul")) {
                            upload(command[1]);
                            continue;
                        }

                    } else if (command.length == 1) {
                        if (command[0].equals("ls")) {
                            listDirectory("./");
                            continue;

                        } else if (command[0].equals("pwd")) {
                            this.out.writeUTF(this.workPath.toString());
                            this.out.flush();
                            continue;
                        }
                    }
                    this.out.writeUTF("Incorrect command.");
                    this.out.flush();
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    /**
     * View directory list.
     * @param directory - directory to list.
     * @throws IOException - exception.
     */
    public void listDirectory(String directory) throws IOException {
        Path resultPath = this.workPath.resolve(directory).normalize();

        if (Files.exists(resultPath, LinkOption.NOFOLLOW_LINKS)
            & Files.isDirectory(resultPath, LinkOption.NOFOLLOW_LINKS)
            & Files.isReadable(resultPath)) {

            try (DirectoryStream<Path> stream = Files.newDirectoryStream(resultPath)) {
                StringBuilder sb = new StringBuilder();
                for (Path file: stream) {
                    sb.append(String.format("%s    ", file.getFileName()));
                }
                this.out.writeUTF(sb.toString());
                this.out.flush();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        } else {
            this.out.writeUTF("Directory is not exist.");
            this.out.flush();
        }
    }
    /**
     * Change directory.
     * @param directory - destination directory.
     * @throws IOException - exception.
     */
    public void changeDirectory(String directory) throws  IOException {
        Path resultPath = this.workPath.resolve(directory).normalize();
        if (Files.exists(resultPath, LinkOption.NOFOLLOW_LINKS)
            & Files.isDirectory(resultPath, LinkOption.NOFOLLOW_LINKS)) {
            this.workPath = resultPath;
            this.out.writeUTF(String.format("Now working directory is: %s", this.workPath.toString()));
            this.out.flush();
        }
    }
    /**
     * Download file from server.
     * @param fileName - file to download from server.
     * @throws IOException - exception.
     */
    public void download(String fileName) throws IOException {

        Path pathSource = this.workPath.resolve(fileName).normalize();
        if (Files.exists(pathSource, LinkOption.NOFOLLOW_LINKS) && Files.isReadable(pathSource)
                && !Files.isDirectory(pathSource)) {

            this.out.writeUTF("<ok>");
            this.out.flush();
            byte[] data = new byte[this.bufferSize];
            int count;

            try (BufferedInputStream fileReader = new BufferedInputStream(new FileInputStream(pathSource.toFile()))) {

                long fileLength = pathSource.toFile().length();
                int enumerator = (fileLength % this.bufferSize == 0) ? (int) (fileLength / this.bufferSize)
                                                                     : (int) (fileLength / this.bufferSize + 1);
                this.out.writeInt(enumerator);
                this.out.flush();

                for (int i = 0; i < enumerator; i++) {
                    count = fileReader.read(data);
                    this.out.write(data, 0, count);
                }
                this.out.flush();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        } else {
            this.out.writeUTF("<error>");
            this.out.flush();
        }
    }

    /**
     * Upload file to server.
     * @param fileName - file to upload to server.
     * @throws IOException - exception.
     */
    public void upload(String fileName) throws IOException {
        byte[] data = new byte[bufferSize];
        int count;
        try (BufferedOutputStream fileWriter = new BufferedOutputStream(new FileOutputStream(fileName))) {
            int enumerator = this.in.readInt();
            for (int i = 0; i < enumerator; i++) {
                count = this.in.read(data);
                fileWriter.write(data, 0, count);
            }
        }
    }

    /**
     * Main method.
     * @param args - no arguments.
     * @throws IOException - exception.
     */
    public static void main(String[] args) throws IOException {
        try {
            Server server = new Server();
            server.connect();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
