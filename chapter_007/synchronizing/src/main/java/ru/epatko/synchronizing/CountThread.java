package ru.epatko.synchronizing;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         13.03.17.
 */
public class CountThread implements Runnable {

    private final Count count;

    public CountThread(Count count) {
        this.count = count;
    }

    @Override
    public void run() {
        count.increment();
    }
}
