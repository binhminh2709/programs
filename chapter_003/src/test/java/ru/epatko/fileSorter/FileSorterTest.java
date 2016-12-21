package ru.epatko.fileSorter;

import org.junit.Test;

import java.io.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         16.12.16.
 */
public class FileSorterTest {
    @Test
    public void sort() throws Exception {
        FileSorter sorter = new FileSorter();
        try {
            File source = new File("source.txt");
            File destination = new File("dest.txt");
            System.out.println(source.getAbsolutePath());
            System.out.println(destination.getAbsolutePath());
            sorter.sort(source, destination);


            assertThat(destination.length(), is (source.length()));
        } catch (FileNotFoundException e) {
            System.out.println("File not found" + e);
        }


    }

}