package ru.epatko.netFileManger;

import java.io.InputStream;
import java.nio.file.Path;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         01.01.17.
 */
public class Client implements ClientInterface, Connection {


    /**
     * Set up connection.
     */
    @Override
    public void connect() {

    }

    /**
     * Download file from server.
     *
     * @param path        - file to download from server.
     * @param inputStream - output stream.
     */
    @Override
    public void download(Path path, InputStream inputStream) {

    }

    /**
     * Upload file to server.
     *
     * @param path         - file to upload to server.
     * @param outputStream - input stream.
     */
    @Override
    public void upload(Path path, InputStream outputStream) {

    }


}
