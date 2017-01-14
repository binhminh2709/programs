package ru.epatko.clientSide;

import ru.epatko.serverSide.Command;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         14.01.17.
 */
public class ClientMenu {

    /**
     * Input stream.
     */
    private final InputStream inpStream;
    /**
     * Output stream.
     */
    private final OutputStream outStream;

    /**
     * Object output stream.
     */
    private final ObjectOutputStream objOutStream;
    /**
     * Data input stream.
     */
    private DataInputStream dataInpStream;
    /**
     * Data output stream.
     */
    private DataOutputStream dataOutStream;
    /**
     * Buffer size.
     */
    private final int bufferSize = 64 * 1024;
    /**
     * Actions HashMap.
     */
    private Map<String, ClientAction> clientActions = new HashMap<>();
    /**
     * Constructor.
     * @param inpStream - input stream.
     * @param outStream - output stream.
     * @throws IOException - exception.
     */
    public ClientMenu(InputStream inpStream, OutputStream outStream) throws IOException {
        this.inpStream = inpStream;
        this.outStream = outStream;
        this.dataInpStream = new DataInputStream(inpStream);
        this.dataOutStream = new DataOutputStream(outStream);
        this.objOutStream = new ObjectOutputStream(this.outStream);
    }

    /**
     * To fill servermenu.
     */
    public void fillMenu() {

        this.clientActions.put("ls", new ListDirectory());
        this.clientActions.put("cd", new ChangeDirectory());
        this.clientActions.put("pwd", new PrintWorkingDirectory());
        this.clientActions.put("dl", new DownloadFile());
        this.clientActions.put("ul", new UploadFile());
        this.clientActions.put("help", new Help());
        this.clientActions.put("exit", new Exit());
    }
    /**
     * Processing user command.
     * @param command - user command.
     * @throws IOException - exception.
     * @throws ClassNotFoundException - exception.
     */
    public void choice(Command command) throws IOException, ClassNotFoundException {
        String serverMessage;
        if (this.clientActions.containsKey(command.getAction())) {
            this.clientActions.get(command.getAction()).execute(command);
        } else {
            System.out.println("Incorrect command.");
        }
    }

    /****************************************************************************************************/
    private class DownloadFile implements ClientAction {
        /**
         * Action name.
         * @return - name.
         */
        @Override
        public String name() {
            return "dl";
        }

        /**
         * Info.
         * @return - info about action.
         */
        @Override
        public String info() {
            return String.format(" %s [FILE] - download FILE from server", this.name());
        }
        /**
         * Action.
         * @param command - user command.
         * @throws IOException - exception.
         */
        @Override
        public void execute(Command command) throws IOException {
            objOutStream.writeObject(command);
            dataOutStream.flush();

            if ("<ok>".equals(dataInpStream.readUTF())) {
                System.out.print("File downloading...");

                byte[] data = new byte[bufferSize];
                int count;

                Path pathSource = Paths.get(command.getParam());
                String fileName = pathSource.getFileName().toString();

                try (BufferedOutputStream fileWriter = new BufferedOutputStream(new FileOutputStream(fileName))) {
                    int enumerator = dataInpStream.readInt();
                    for (int i = 0; i < enumerator; i++) {
                        count = dataInpStream.read(data);
                        fileWriter.write(data, 0, count);
                        System.out.print(".");
                    }
                    System.out.println(" Ok.");
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            } else {
                System.out.println("File is not exists or file can't be downloaded.");
            }
        }
    }
    /****************************************************************************************************/
    private class UploadFile implements ClientAction {

        /**
         * Action name.
         * @return - name.
         */
        @Override
        public String name() {
            return "ul";
        }

        /**
         * Info.
         * @return - info about action.
         */
        @Override
        public String info() {
            return String.format(" %s [FILE] - upload FILE to server", this.name());
        }
        /**
         * Action.
         * @param command - user command.
         * @throws IOException - exception.
         */
        @Override
        public void execute(Command command) throws IOException {
            Path pathSource = Paths.get(command.getParam());
            if (Files.exists(pathSource, LinkOption.NOFOLLOW_LINKS) && Files.isReadable(pathSource)
                                                                    && !Files.isDirectory(pathSource)) {

                try (BufferedInputStream fileReader = new BufferedInputStream(new FileInputStream(pathSource.toFile()))) {

                    System.out.print("File uploading...");

                    Command commandToServer = new Command(String.format("ul %s", pathSource.getFileName().toString()));

                    objOutStream.writeObject(commandToServer);
                    objOutStream.flush();

                    byte[] data = new byte[bufferSize];
                    int count;
                    long fileLength = pathSource.toFile().length();
                    int enumerator;
                    if (fileLength % bufferSize == 0) {
                        enumerator = (int) (fileLength / bufferSize);
                    } else {
                        enumerator = (int) (fileLength / bufferSize + 1);
                    }

                    dataOutStream.writeInt(enumerator);
                    dataOutStream.flush();

                    for (int i = 0; i < enumerator; i++) {
                        count = fileReader.read(data);
                        dataOutStream.write(data, 0, count);
                        System.out.print(".");
                    }
                    dataOutStream.flush();
                    System.out.println(" Ok.");
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            } else {
                System.out.println("Incorrect filename or file can't be uploaded.");
            }
        }
    }
    /****************************************************************************************************/

    private class Help implements ClientAction {
        /**
         * Action name.
         * @return - name.
         */
        @Override
        public String name() {
            return "help";
        }

        /**
         * Info.
         * @return - info about action.
         */
        @Override
        public String info() {
            return String.format(" %s - print help.", this.name());
        }

        /**
         * Action.
         * @param command - user command.
         * @throws IOException - exception.
         */
        @Override
        public void execute(Command command) throws IOException, ClassNotFoundException {

            System.out.println(" ---------------------------------------------------------");
            System.out.println("|                     Server commands:                    |");
            System.out.println(" ---------------------------------------------------------");
            clientActions.forEach((k, v) -> System.out.println(v.info()));
            System.out.println(" ---------------------------------------------------------");
        }
    }
    /****************************************************************************************************/

    private class Exit implements ClientAction {
        /**
         * Action name.
         * @return - name.
         */
        @Override
        public String name() {
            return "exit";
        }

        /**
         * Info.
         * @return - info about action.
         */
        @Override
        public String info() {
            return String.format(" %s - exit program.", this.name());
        }
        /**
         * Action.
         * @param command - user command.
         * @throws IOException - exception.
         */
        @Override
        public void execute(Command command) throws IOException, ClassNotFoundException {
            System.exit(0);
        }
    }
    /****************************************************************************************************/

    private class ListDirectory implements ClientAction {

        /**
         * Name of action.
         * @return - name.
         */
        @Override
        public String name() {

            return "ls";
        }
        /**
         * Info.
         * @return - info about action.
         */
        @Override
        public String info() {
            return String.format(" %s [DIRECTORY] - view DIRECTORY list", this.name());
        }
        /**
         * Action.
         * @param command - User command.
         * @throws IOException - exception.
         */
        @Override
        public void execute(Command command) throws IOException, ClassNotFoundException {
            objOutStream.writeObject(command);
            dataOutStream.flush();
            System.out.println(dataInpStream.readUTF());
        }
    }

    /****************************************************************************************************/
    private class ChangeDirectory implements ClientAction {
        /**
         * Action name.
         * @return - name.
         */
        @Override
        public String name() {
            return "cd";
        }

        /**
         * Info.
         * @return - info about action.
         */
        @Override
        public String info() {
            return String.format(" %s [DIRECTORY] - change current directory to DIRECTORY", this.name());
        }

        /**
         * Action.
         * @param command - User command.
         * @throws IOException - exception.
         */
        @Override
        public void execute(Command command) throws  IOException {
            objOutStream.writeObject(command);
            dataOutStream.flush();
            System.out.println(dataInpStream.readUTF());
        }

    }
    /****************************************************************************************************/

    private class PrintWorkingDirectory implements ClientAction {

        /**
         * Action name.
         * @return - name.
         */
        @Override
        public String name() {
            return "pwd";
        }

        /**
         * Info.
         * @return - info about action.
         */
        @Override
        public String info() {
            return String.format(" %s - print working directory", this.name());
        }

        /**
         * Action.
         * @param command - User command.
         */
        @Override
        public void execute(Command command) throws IOException {
            objOutStream.writeObject(command);
            dataOutStream.flush();
            System.out.println(dataInpStream.readUTF());
        }
    }
}