package ru.epatko.stringCalculator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         10.03.17.
 */
public class StringCalculator {

    private static final Logger LOGGER = LoggerFactory.getLogger(StringCalculator.class);
    /**
     * Words counter.
     */
    public static final class WordsCounter implements Runnable {

        private String string;

        /**
         * Count words in string.
         * @param string string
         */
        public WordsCounter(String string) {
            this.string = string;
        }

        /**
         * When an object implementing interface <code>Runnable</code> is used
         * to create a thread, starting the thread causes the object's
         * <code>run</code> method to be called in that separately executing
         * thread.
         */
        @Override
        public void run() {
            for (int i = 0; i < 10; i++ ) {
                if (!Thread.interrupted()) {
                    System.out.println(string.split(" +").length);
                } else {
                    return;
                }
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    LOGGER.info(e.getMessage());
                    return;
                }
            }
        }
    }

    /**
     * Spacess counter.
     */
    public static final class SpaceCounter implements Runnable {

        private String string;

        /**
         * Count spaces in string.
         * @param string string
         */
        public SpaceCounter(String string) {
            this.string = string;
        }

        /**
         * When an object implementing interface <code>Runnable</code> is used
         * to create a thread, starting the thread causes the object's
         * <code>run</code> method to be called in that separately executing
         * thread.
         */
        @Override
        public void run() {
            for (int i = 0; i < 10; i++ ) {
                if (!Thread.interrupted()) {
                    System.out.println(string.split(" +").length - 1);
                } else {
                    return;
                }
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    LOGGER.info(e.getMessage());
                    return;
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Start program.");

        Thread spaceCounterThread = new Thread(new SpaceCounter("111 222    333  444   555"));
        spaceCounterThread.start(); //Print "4"

        Thread wordCounterThread = new Thread(new WordsCounter("111 222    333  444   555"));
        wordCounterThread.start(); //Print "5"

        try {
            spaceCounterThread.join(500);
            wordCounterThread.join(500);
            spaceCounterThread.interrupt();
            wordCounterThread.interrupt();
        } catch (InterruptedException e) {
            LOGGER.info(e.getMessage());
        }

        System.out.println("End program.");
    }
}
