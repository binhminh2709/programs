package ru.epatko.orderProcessor;

import java.util.*;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         25.02.17.
 */
public class Book {

    final String name;
    Map<Integer, Order> keyMap = new HashMap<>();
    TreeMap<Float, Integer> asks = new TreeMap<>();
    TreeMap<Float, Integer> bids = new TreeMap<>(new Comparator<Float>() {

        @Override
        public int compare(Float f1, Float f2) {
            return -f1.compareTo(f2);
        }
    });

    public Book(String name) {
        this.name = name;
    }
}
