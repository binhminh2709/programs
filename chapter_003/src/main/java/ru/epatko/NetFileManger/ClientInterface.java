package ru.epatko.NetFileManger;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         01.01.17.
 */
public interface ClientInterface {

     /**
     * Download file from server.
     * @param path - file to download from server.
     * @param inputStream - output stream.
     */
    void download(Path path, InputStream inputStream);
    /**
     * Upload file to server.
     * @param path - file to upload to server.
     * @param outputStream - input stream.
     */
    void upload(Path path, InputStream outputStream);
}










