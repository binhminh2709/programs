package ru.epatko.gc;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         06.03.17.
 */
public class MyCache {

    private HashMap<String, SoftReference<StringBuilder>> cache = new HashMap<>();
    private Path sourcePath;
    private static final String LS = System.getProperty("line.separator");

    public MyCache(String sourceFolderPath) {
        this.sourcePath = Paths.get(sourceFolderPath);
    }

    public String get(String fileName) throws IOException {
        StringBuilder textFileContent = new StringBuilder();
        if (cache.containsKey(fileName)) {
            textFileContent = cache.get(fileName).get();
        } else {
            Path file = this.sourcePath.resolve(fileName);
            if (Files.exists(file) && !Files.isDirectory(file) && Files.isReadable(file)) {
                try (Scanner scanner = new Scanner(Files.newInputStream(file))) {
                    while (scanner.hasNext()) {
                        textFileContent.append(scanner.nextLine()).append(LS);
                    }
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
            cache.put(fileName, new SoftReference(textFileContent));
        }
        return textFileContent.toString();
    }
}
