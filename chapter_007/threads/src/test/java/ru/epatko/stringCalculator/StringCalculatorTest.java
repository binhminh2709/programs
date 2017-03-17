package ru.epatko.stringCalculator;

import org.junit.Test;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         16.03.17.
 */
public class StringCalculatorTest {


    @Test
    public void when___Then___() {


        Thread AnotherSpaceCounterThread = new Thread(new AnotherSpaceCounter("111 222    333  444   555"));
        AnotherSpaceCounterThread.start(); //Print "4"
        try {
            AnotherSpaceCounterThread.join(1);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
            AnotherSpaceCounterThread.interrupt();

    }
}


class AnotherSpaceCounter implements Runnable {

    private String string;

    /**
     * Count spaces in string.
     *
     * @param string string
     */
    public AnotherSpaceCounter(String string) {
        this.string = string;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
//            try {
                System.out.println(string.split(" +").length - 1);
//                Thread.sleep(200);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//                return;
//            }
            if (Thread.interrupted()) {
                return;
            }
        }
    }
}
