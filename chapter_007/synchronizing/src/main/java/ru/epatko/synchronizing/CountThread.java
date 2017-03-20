package ru.epatko.synchronizing;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         13.03.17.
 */
public class CountThread implements Runnable {

    /**
     * Count.
     */
    private final Count count;

    /**
     * Constructor.
     * @param count count
     */
    public CountThread(Count count) {
        this.count = count;
    }

    /**
     * Run count increment.
     */
    @Override
    public void run() {
        count.increment();
    }
}
