package ru.epatko.textsearcher;

import org.slf4j.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.*;

import static java.lang.System.exit;
import static java.nio.file.FileVisitResult.*;

import java.util.*;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         23.03.17.
 */

public class Searcher extends SimpleFileVisitor<Path> {

    /**
     * Text to search.
     */
    private final String textToSearch;
    /**
     * Total number of matches.
     */
    private int numMatches = 0;
    /**
     * Logger.
     */
    private final Logger logger;

    /**
     * Arraylist of threads.
     */
    private LinkedList<Thread> threads = new LinkedList<>();
    /**
     * Flag: find one or all matches.
     */
    private boolean findOne;
    /**
     * Stop search flag.
     */
    private boolean stop = false;

    /**
     * Constructor.
     * @param textToSearch text to search
     * @param findOne flag: find one or all matches
     * @param logger logger
     */
    Searcher(String textToSearch, boolean findOne, final Logger logger) {
        this.textToSearch = textToSearch;
        this.findOne = findOne;
        this.logger = logger;
    }

    /**
     * Show find result.
     * @param checkedFile checked file
     * @param string string containing searched text
     */
    private void showResult(Path checkedFile, String string) {
        synchronized (this) {
            if (!stop) {
                numMatches++;
                System.out.println(String.format("Text \"%s\" finded in the file: %s",
                                   textToSearch, checkedFile));
                System.out.println(String.format("In the line -> \"%s\"", string));
                if (findOne) {
                    stop = true;
                }
            }
        }
    }

    /**
     * Interrupt all threads & log total number of matches to log file.
     * @throws InterruptedException exception
     */
    public void done() throws InterruptedException {
        if (!stop) {
            for (Thread thread : threads) {
                if (thread.isAlive()) {
                    thread.join();
                }
            }
        }
        String result = String.format("The text \"%s\" finded in %d file(s)", textToSearch, numMatches);
        this.logger.info(result);
        System.out.println(result);
    }
    /**
     * Invoke the finding method on each file.
     * @param file path by file to find
     * @param attrs basic file attributes
     * @return file visit result
     * @throws IOException - exception
     */
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
       if (!stop) {
           this.threads.add(new Thread(new Finder(file)));
           threads.getLast().start();
            return CONTINUE;
       } else {
            return TERMINATE;
        }
    }
    /**
     * Invoke the finding method on each directory.
     * @param dir path by directory to compare
     * @param attrs basic directory attributes
     * @return directory visit result
     * @throws IOException - exception
     */
    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        if (!stop) {
            return CONTINUE;
        } else {
            return TERMINATE;
        }
    }

    /**
     * Reaction by path access exception.
     * @param file path by file or by directory
     * @param exc exception
     * @return reaction by exception
     * @throws IOException exception
     */
    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        if (!stop) {
            this.logger.info(exc.getMessage());
            return CONTINUE;
        } else {
            return TERMINATE;
        }
    }
    /**
     * Main method.
     * @param args [-fm] find first match, [text to search]
     */
    public static void main(String[] args) {

        boolean findOne = false;
        String textToSearch = "";
        if (args.length == 0) {
            Usage.help();
            exit(0);
        } else if (args[0].equals("-fm") && args.length > 1) {
            findOne = true;
            textToSearch = args[1];
        } else {
            textToSearch = args[0];
        }
        final Logger logger = LoggerFactory.getLogger("Searcher");
        Searcher searcher = new Searcher(textToSearch, findOne, logger);

        try {
            Path startDir = Paths.get(System.getProperty("user.dir"));
            Files.walkFileTree(startDir, searcher);
            searcher.done();
        } catch (IOException | InterruptedException exc) {
            logger.info(exc.getMessage());
        }
    }


    /**
     * Multithreading find class.
     */
    private class Finder implements Runnable {

        /**
         * Checked file.
         */
        private Path fileToCheck;

        /**
         * Constructor.
         * @param fileToCheck file to check
         */
        Finder(Path fileToCheck) {
            this.fileToCheck = fileToCheck;
        }

        /**
         * Search text in file.
         */
        @Override
        public void run() {
            try {
                File file = this.fileToCheck.toFile();
                if (file.exists() && file.canRead() && isFileText()) {
                    List<String> stringList = Files.readAllLines(fileToCheck, StandardCharsets.UTF_8);
                    for (String string : stringList) {
                        if (Thread.interrupted()) {
                            break;
                        } else if (string.contains(textToSearch)) {
                            showResult(fileToCheck, string);
                            break;
                        }
                    }
                }
            } catch (IOException ioe) {
                logger.info(ioe.getMessage());
            }
        }

        /**
         * Check if file is text file.
         * @return true or false
         * @throws IOException exception
         */
        private boolean isFileText() throws IOException {
            try {
                if (Files.probeContentType(fileToCheck).contains("text")
                    || Files.probeContentType(fileToCheck).contains("xml")
                    || Files.probeContentType(fileToCheck).contains("pdf")) {
                    return true;
                }
            } catch (IOException ioe) {
                logger.info(ioe.getMessage());
            }
            return false;
        }
    }
}
