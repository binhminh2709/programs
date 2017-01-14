package ru.epatko.serverSide;

import java.io.*;
import java.nio.file.*;
import java.util.*;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         13.01.17.
 */
public class ServerMenu {

    /**
     * Buffer size.
     */
    private final int bufferSize = 64 * 1024;
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
     * Actions HashMap.
     */
    private Map<String, Action> actions = new HashMap<>();
    /**
     * Constructor.
     * @param in - DataInputStream.
     * @param out - DataOutputStream.
     */
    public ServerMenu(DataInputStream in, DataOutputStream out) {
        this.workPath = Paths.get(System.getProperty("user.dir"));
        this.in = in;
        this.out = out;
    }

    /**
     * To fill servermenu.
     */
    public void fillMenu() {
        this.actions.put("ls", new ListDirectory());
        this.actions.put("cd", new ChangeDirectory());
        this.actions.put("dl", new Download());
        this.actions.put("ul", new Upload());
        this.actions.put("pwd", new PrintWorkingDirectory());
    }

    /**
     * Get array of server actions info.
     * @return - array of server actions info.
     */
    public String[] getActions() {
        String[] serverActions = new String[actions.size()];
        int i = 0;
        for (Map.Entry<String, Action> action : actions.entrySet()) {
            serverActions[i++] = action.getValue().info();
        }
        return serverActions;
    }

    /**
     * Starting selected action.
     * @param command - number of action.
     * @throws IOException - exception.
     */
    public void choice(Command command) throws IOException {
        if (actions.containsKey(command.getAction())) {
            this.actions.get(command.getAction()).execute(command.getParam());
        } else {
            this.out.writeUTF("Incorrect command.");
            this.out.flush();
        }
    }
    /****************************************************************************************************/
    private class ListDirectory implements Action {

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
         * View directory list.
         * @param directory - directory to list.
         * @throws IOException - exception.
         */
        @Override
        public void execute(String directory)  throws IOException {
            Path resultPath = workPath.resolve(directory).normalize();

            if (Files.exists(resultPath, LinkOption.NOFOLLOW_LINKS)
                    & Files.isDirectory(resultPath, LinkOption.NOFOLLOW_LINKS)
                    & Files.isReadable(resultPath)) {

                try (DirectoryStream<Path> stream = Files.newDirectoryStream(resultPath)) {
                    StringBuilder sb = new StringBuilder();
                    for (Path file: stream) {
                        sb.append(String.format("%s    ", file.getFileName()));
                    }
                    out.writeUTF(sb.toString());
                    out.flush();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            } else {
                out.writeUTF("Directory is not exist.");
                out.flush();
            }
        }
    }

    /****************************************************************************************************/
    private class ChangeDirectory implements Action {
        /**
         * Name of action.
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
         * Change working directory.
         * @param directory - destination directory.
         * @throws IOException - exception.
         */
        @Override
        public void execute(String directory) throws  IOException {
            Path resultPath = workPath.resolve(directory).normalize();
            if (Files.exists(resultPath, LinkOption.NOFOLLOW_LINKS)
                    & Files.isDirectory(resultPath, LinkOption.NOFOLLOW_LINKS)) {
                workPath = resultPath;
                out.writeUTF(String.format("Now working directory is: %s", workPath.toString()));
                out.flush();
            }
        }
    }
    /****************************************************************************************************/
    private class Download implements Action {
        /**
         * Name of action.
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
         * Download file from server.
         * @param fileName - file to download from server.
         * @throws IOException - exception.
         */
        @Override
        public void execute(String fileName) throws IOException {
            Path pathSource = workPath.resolve(fileName).normalize();
            if (Files.exists(pathSource, LinkOption.NOFOLLOW_LINKS) && Files.isReadable(pathSource)
                    && !Files.isDirectory(pathSource)) {

                out.writeUTF("<ok>");
                out.flush();
                byte[] data = new byte[bufferSize];
                int count;

                try (BufferedInputStream fileReader = new BufferedInputStream(new FileInputStream(pathSource.toFile()))) {

                    long fileLength = pathSource.toFile().length();
                    int enumerator;
                    if (fileLength % bufferSize == 0) {
                        enumerator = (int) (fileLength / bufferSize);
                    } else {
                        enumerator = (int) (fileLength / bufferSize + 1);
                    } 
                    out.writeInt(enumerator);
                    out.flush();

                    for (int i = 0; i < enumerator; i++) {
                        count = fileReader.read(data);
                        out.write(data, 0, count);
                    }
                    out.flush();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            } else {
                out.writeUTF("Incorrect command.");
                out.flush();
            }
        }
    }
    /****************************************************************************************************/
    private class Upload implements Action {
        /**
         * Name of action.
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
         * Upload file to server.
         * @param fileName - file to upload to server.
         * @throws IOException - exception.
         */
        @Override
        public void execute(String fileName) throws IOException {

            byte[] data = new byte[bufferSize];
            int count;
            try (BufferedOutputStream fileWriter = new BufferedOutputStream(new FileOutputStream(fileName))) {
                int enumerator = in.readInt();
                for (int i = 0; i < enumerator; i++) {
                    count = in.read(data);
                    fileWriter.write(data, 0, count);
                }
            }
        }
    }
    /****************************************************************************************************/
    public class PrintWorkingDirectory implements Action {

        /**
         * Name of action.
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
         * @param parameter - parameter.
         */
        @Override
        public void execute(String parameter) throws IOException {
            out.writeUTF(workPath.toString());
            out.flush();
        }
    }
}
