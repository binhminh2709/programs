package ru.epatko.searcher;

import org.junit.Test;

import java.io.File;
import java.io.RandomAccessFile;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         06.01.17.
 */
public class SearcherTest {
//Find.main("-d", "./", "-n"  -{m, f, r} "-o", "log.txt");
    @Test
    public void whenSetSearchByMaskOptionThenCanFindFile() throws Exception {

        Searcher.main(new String[] {"-d", "./", "-n", "ololo", "-m", "-o", "log.txt"});
        try(RandomAccessFile raFile = new RandomAccessFile("log.txt", "r")) {
            assertThat(raFile.readLine(), is ("./1.ololo"));
            assertThat(raFile.readLine(), is ("------------------------------------------------"));
            assertThat(raFile.readLine(), is ("Matched: 1"));
        }
    }
    @Test
    public void whenSetSearchByFileNameOptionsThenCanFindFile() throws Exception {

        Searcher.main(new String[] {"-d", "./", "-n", "1.ololo", "-f", "-o", "log.txt"});
        try(RandomAccessFile raFile = new RandomAccessFile("log.txt", "r")) {
            assertThat(raFile.readLine(), is ("./1.ololo"));
            assertThat(raFile.readLine(), is ("------------------------------------------------"));
            assertThat(raFile.readLine(), is ("Matched: 1"));
        }
    }
    @Test
    public void whenSetSearchByRegexOptionsThenCanFindFile() throws Exception {

        Searcher.main(new String[] {"-d", "./", "-n", ".*\\.ololo", "-r", "-o", "log.txt"});
        try(RandomAccessFile raFile = new RandomAccessFile("log.txt", "r")) {
            assertThat(raFile.readLine(), is ("./1.ololo"));
            assertThat(raFile.readLine(), is ("------------------------------------------------"));
            assertThat(raFile.readLine(), is ("Matched: 1"));
        }
    }
    @Test
    public void whenCreateLoggerThenCanLogToFile() throws Exception {

        File file = new File ("log2.txt");
        Logger logger = new Logger(file);
        logger.log("12345");
        try(RandomAccessFile raFile = new RandomAccessFile(file, "r")) {
            assertThat(raFile.readLine(), is ("12345"));
        }
    }
}
