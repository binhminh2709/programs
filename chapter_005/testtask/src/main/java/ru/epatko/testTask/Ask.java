package ru.epatko.testTask;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         23.02.17.
 */
public class Ask implements Comparable<Ask> {
    final float price;
    int volume;
    final int id;

    public Ask(final float price, final int volume, final int id) {
        this.price = price;
        this.volume = volume;
        this.id = id;
    }

    /**
     * Compares this object with the specified object for order.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     */

    public int compareTo(Ask o) {

        if (id == o.id || price == o.price) {
            return 0;
        }
        if (price < o.price) {
            return -1;
        }
            return 1;
    }
}
