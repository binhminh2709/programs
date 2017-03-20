package ru.epatko.synchronizing;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         13.03.17.
 */
public class Count {

    /**
     * Count.
     */
    private int count = 0;

    /**
     * Getter.
     * @return count
     */
    public synchronized int getCount() {
        return this.count;
    }

    /**
     * Increment.
     */
    public synchronized void increment() {
        this.count++;
    }
}
