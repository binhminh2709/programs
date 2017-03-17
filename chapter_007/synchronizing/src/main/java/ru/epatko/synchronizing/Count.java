package ru.epatko.synchronizing;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         13.03.17.
 */
public class Count {

    private int count = 0;

    public synchronized int getCount() {
        return this.count;
    }

    public synchronized void increment() {
        this.count++;
    }
}
