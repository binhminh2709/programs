package ru.epatko.fileSearcher;


import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         09.03.17.
 */
public class FileSearcher {
    private File result = null;

    public File find(Folder folder, String name) throws IOException {
        ArrayList<Folder> subfolders = folder.getSubfolders();
        ArrayList<File> files = folder.getFiles();

        for (File file : files) {
            if (name.equals(file.getName())) {
                this.result = file;
            }
        }
        if (this.result == null) {
            for (Folder entry : subfolders) {
                find(entry, name);
            }
        }
        return this.result;
    }
}

class Folder {
    private final Path folderPath;

    public Folder(final Path folderPath) {
        this.folderPath = folderPath;
    }

    public ArrayList<Folder> getSubfolders() throws IOException {
        ArrayList<Folder> subfolders = new ArrayList<>();
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(folderPath)) {
            for (Path entry : entries) {
                if (entry.toFile().isDirectory()) {
                    subfolders.add(new Folder(entry));
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return subfolders;
    }

    public ArrayList<File> getFiles() {
        ArrayList<File> files = new ArrayList<>();
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(folderPath)) {
            for (Path entry : entries) {
                if (!entry.toFile().isDirectory()) {
                    files.add(new File(entry));
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return files;
    }
}

class File {
    private final Path filePath;
    private String name;

    public File(final Path filePath) {
        this.filePath = filePath;
        this.name = filePath.getFileName().toString();
    }

    public String getName() {
        return this.name;
    }
}
