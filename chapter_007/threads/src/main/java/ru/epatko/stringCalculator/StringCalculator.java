package ru.epatko.stringCalculator;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         10.03.17.
 */
public class StringCalculator {

    /**
     * Words counter.
     */
    public static final class WordsCounter implements Runnable {

        String string;

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
            System.out.println(string.split(" +").length);
        }
    }

    /**
     * Spacess counter.
     */
    public static final class SpaceCounter implements Runnable {

        String string;

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
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(string.split(" +").length - 1);
        }
    }

    public static void main(String[] args) {
        new Thread(new SpaceCounter("111 222    333  444   555")).start(); //Print "4"
        new Thread(new WordsCounter("111 222    333  444   555")).start(); //Print "5"
    }
}
