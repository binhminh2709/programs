package ru.epatko.testTask;

import java.util.*;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         23.02.17.
 */
public class Book {

    String name;
    List<Bid> bids = new ArrayList<>();
    List<Ask> asks = new ArrayList<>();
    /**
     * Line separator.
     */
    private final String lineSeparator = System.getProperty("line.separator");

    public Book(String name) {
        this.name = name;
    }
    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int bs = bids.size();
        int as = asks.size();

        sb.append(String.format("%2$s%2$s       Order book: %s%s%2$s       BID              ASK%2$s%2$s", name, lineSeparator));

        if (bs < as) {
            for (int i = 0; i < bs; i++) {
                sb.append(String.format("%7d@%6.2f - %7d@%.2f%s", bids.get(i).volume, bids.get(i).price,
                                                                  asks.get(i).volume, asks.get(i).price,
                                                                  lineSeparator));
            }
            for (int i = bs; i < as; i++) {
                sb.append(String.format("%14s - %7d@%.2f%s", "---------", asks.get(i).volume, asks.get(i).price, lineSeparator));
            }
        } else if (bs > as) {
            for (int i = 0; i < as; i++) {
                sb.append(String.format("%7d@%6.2f - %7d@%.2f%s", bids.get(i).volume, bids.get(i).price,
                                                                  asks.get(i).volume, asks.get(i).price,
                                                                  lineSeparator));
            }
            for (int i = as; i < bs; i++) {
                sb.append(String.format("%7d@%6.2f - ---------%s", bids.get(i).volume, bids.get(i).price, lineSeparator));
            }
        } else {
            for (int i = 0; i < as; i++) {
                sb.append(String.format("%7d@%6.2f - %7d@%.2f%s", bids.get(i).volume, bids.get(i).price,
                                                                  asks.get(i).volume, asks.get(i).price,
                                                                  lineSeparator));
            }
        }
        return sb.toString();
    }
}
