package ru.epatko.netFileManger;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         01.01.17.
 */

public class Server implements ServerInterface, Connection {

    /**
     * Set up connection.
     */

    public void connect() {

    }

    /**
     * Get directory list.
     *
     * @param path
     * @return - directory list.
     * @path - directory to list.
     */
    @Override
    public String listDirectory(Path path) {
        return null;
    }

    /**
     * Change directory.
     *
     * @param path - destination directory.
     * @return - boolean: changed directory or no.
     */
    @Override
    public boolean changeDirectory(Path path) {
        return false;
    }

    /**
     * Download file from server.
     *
     * @param path         - file to download from server.
     * @param outputStream - output stream.
     */
    @Override
    public void download(Path path, OutputStream outputStream) {

    }

    /**
     * Upload file to server.
     *
     * @param path        - file to upload to server.
     * @param inputStream - input stream.
     */
    @Override
    public void upload(Path path, InputStream inputStream) {

    }


}
