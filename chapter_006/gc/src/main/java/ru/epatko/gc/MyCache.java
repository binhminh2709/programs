package ru.epatko.gc;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         06.03.17.
 */
public class MyCache {

    private TreeMap<String, SoftReference<StringBuilder>> cache = new TreeMap<>();
    private Path sourcePath;
    private static final String LS = System.getProperty("line.separator");

    public MyCache(String pathToSourceFolder) {
        this.sourcePath = Paths.get(pathToSourceFolder);
    }

    public TreeMap<String, SoftReference<StringBuilder>> getCache() {
        return this.cache;
    }

    public StringBuilder showFile(String fileName) throws IOException {
        StringBuilder textFileContent = null;
        if (cache.containsKey(fileName)) {
            textFileContent = cache.get(fileName).get();
        }
        if (textFileContent == null) {
            textFileContent = getFileContent(fileName);
            SoftReference<StringBuilder> softReference = new SoftReference(textFileContent);
            cache.put(fileName, softReference);
        }
        return textFileContent;
    }

    private StringBuilder getFileContent(String fileName) throws IOException {
        StringBuilder sb = new StringBuilder();
        Path file = sourcePath.resolve(fileName).normalize();
        if (Files.exists(file) && !Files.isDirectory(file) && Files.isReadable(file)) {
            try (InputStream in = Files.newInputStream(file)) {
                Scanner scanner = new Scanner(in);
                while (scanner.hasNext()) {
                    sb.append(scanner.nextLine()).append(LS);
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        return sb;
    }
}
