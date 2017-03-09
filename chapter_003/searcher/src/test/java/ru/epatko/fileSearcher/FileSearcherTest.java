package ru.epatko.fileSearcher;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         09.03.17.
 */
public class FileSearcherTest {

    @Test
    public void whenSearchExistingFileThenGetFile() throws IOException {
        FileSearcher fs = new FileSearcher();
        Path path = Paths.get(".");
        Folder folder = new Folder(path);
        File searchedFile = fs.find(folder, "fileToSearch");
        assertThat(searchedFile.getName(), is("fileToSearch"));
    }

    @Test
    public void whenSearchNotExistingFileThenGetNull() throws IOException {
        FileSearcher fs = new FileSearcher();
        Path path = Paths.get(".");
        Folder folder = new Folder(path);
        File searchedFile = fs.find(folder, "fileTo");
        assertNull(searchedFile);
    }
}