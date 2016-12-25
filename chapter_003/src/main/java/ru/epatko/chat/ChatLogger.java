package ru.epatko.chat;

import java.io.*;
/**
 * Chat logger.
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         25.12.16.
 */
public class ChatLogger {

    /**
     * Log file.
     */
    private File logFile;
    /**
     *
     * @param file - log file.
     */
    public ChatLogger(File file) {
        this.logFile = file;
    }
    /**
     * File pointer for random access to file.
     */
    private long filePointer = 0L;
    /**
     * System line separator.
     */
    private final String lineSeparator = System.getProperty("line.separator");

    /**
     * Log method.
     * @param message - message to log.
     * @throws IOException - exception.
     */
    public void log(String message) throws IOException {
        try (RandomAccessFile rafLogFile = new RandomAccessFile(this.logFile, "rw")) {
            rafLogFile.seek(this.filePointer);
            rafLogFile.writeBytes(String.format("%s%s", message, this.lineSeparator));
            this.filePointer = rafLogFile.getFilePointer();
        } catch (IOException ioe) {
            ioe.getStackTrace();
        }
    }
}
