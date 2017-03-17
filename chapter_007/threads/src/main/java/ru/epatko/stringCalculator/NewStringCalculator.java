package ru.epatko.stringCalculator;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         16.03.17.
 */
public class NewStringCalculator {

    public static final class SpaceCalculator implements Runnable {

        private final String string;

        public SpaceCalculator(final String string) {
            this.string = string;
        }

        public void run() {

            for (int i = 0; i < 10; i++) {
                char[] chars = string.toCharArray();
                int result = 0;
                boolean isSpace = false;
                try {
                    for (char ch : chars) {
                        if (ch == ' ' && !isSpace) {
                            result++;
                            isSpace = true;
                        } else if (ch != ' ') {
                            isSpace = false;
                        }
                    }
                    Thread.sleep(200);
                } catch (InterruptedException e) {

                    return;
                }
                System.out.println(result);
            }
        }
    }


    public static final class WordsCalculator implements Runnable {

        private final String string;

        public WordsCalculator(final String string) {
            this.string = string;
        }

        public void run() {

            for (int i = 0; i < 10; i++) {
                char[] chars = string.toCharArray();
                int  result = 0;
                boolean isWord = false;
                try {
                    for (char ch : chars) {
                        if (ch != ' ' && !isWord) {
                            result++;
                            isWord = true;
                        } else if (ch == ' ') {
                            isWord = false;
                        }

                    }
                    Thread.sleep(200);
                } catch (InterruptedException e) {

                    return;
                }
                System.out.println(result);
            }
        }
    }


        public static void main(String[] args) {

            final long startTime = System.currentTimeMillis();

            System.out.println("Start program.");

            Thread wCalcThread = new Thread(new WordsCalculator("111 222    333  444   555"));
            Thread sCalcThread = new Thread(new SpaceCalculator("111 222    333  444   555"));

            wCalcThread.start();
            sCalcThread.start();
            try {
                wCalcThread.join(500);
                sCalcThread.join(500);
                wCalcThread.interrupt();
                sCalcThread.interrupt();
            } catch (InterruptedException e) {
            }
            System.out.println("End program.");

            final long endTime = System.currentTimeMillis();

            System.out.printf("Work time: %d ms", endTime - startTime);

        }
}