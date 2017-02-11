package ru.epatko.evenNumberIterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         11.02.17.
 */
public class SimpleNumberIterator implements Iterator {
    /**
     * Array.
     */
    private final int[] array;
    /**
     * Array index.
     */
    private int index = 0;

    public SimpleNumberIterator(int[] array) {
        this.array = array;
    }

    /**
     * Returns {@code true} if the iteration has more elements.
     * (In other words, returns {@code true} if {@link #next} would
     * return an element rather than throwing an exception.)
     *
     * @return {@code true} if the iteration has more elements.
     */
    @Override
    public boolean hasNext() {
        return this.array.length > this.index;
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next simple number in the iteration.
     * @throws NoSuchElementException if the iteration has no more elements.
     */

    @Override
    public Object next() throws NoSuchElementException {
        boolean result = false;

        for (int i = this.index; i < this.array.length; i++) {
            int num = this.array[i];
            if (num < 2) {
                continue;
            }
            if (num == 2 || num == 3) {
                this.index = i;
                result = true;
                break;
            }

            int count = (int) Math.sqrt(num) + 1;

            for (int j = 2; j <= count; j++) {
                if (num % j == 0) {
                    result = false;
                    break;
                } else result = true;
            }
            if (result == true) {
                this.index = i;
                break;
            }
        }
        if (result == true) {

            return this.array[this.index++];
        } else {
            throw new NoSuchElementException();
        }
    }
}
