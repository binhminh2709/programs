package ru.epatko.NetFileManger;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         01.01.17.
 */
public interface ServerInterface {

    /**
     * Get directory list.
     * @path - directory to list.
     * @return - directory list.
     */
    String listDirectory(Path path);

    /**
     * Change directory.
     * @param path - destination directory.
     * @return - boolean: changed directory or no.
     */
    boolean changeDirectory(Path path);

    /**
     * Download file from server.
     * @param path - file to download from server.
     * @param outputStream - output stream.
     */
    void download(Path path, OutputStream outputStream);
    /**
     * Upload file to server.
     * @param path - file to upload to server.
     * @param inputStream - input stream.
     */
    void upload(Path path, InputStream inputStream);
}
