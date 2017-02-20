package ru.epatko.myTree;

import java.util.Objects;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         20.02.17.
 * Basic leaf.
 */
public class Leaf<E> implements Comparable<Leaf<E>> {

    final int hash;
    final E value;
    Leaf<E> left;
    Leaf<E> right;
    Leaf<E> parent;

    /**
     * Constructs leaf.
     * @param value object {@code E} type
     * @param left left leaf
     * @param right right leaf
     * @param parent parent leaf
     */
    public Leaf(E value, Leaf<E> parent, Leaf<E> left, Leaf<E> right) {
        this.value = value;
        hash = Objects.hashCode(value);
        this.left = left;
        this.right = right;
        this.parent = parent;
    }

    @Override
    public String toString() {
        return String.format("Value: %s", value.toString());
    }

    @Override
    public final boolean equals(Object o) {
        boolean result = false;
        if (o == this) {
            result = true;
        } else if (o instanceof Leaf) {
            Leaf<E> e = (Leaf<E>) o;
            if (hash == e.hash && Objects.equals(value, e.value)) {
                result = true;
            }
        }
        return result;
    }

    /**
     * Compares this object with the specified object for order.
     */
    @Override
    public int compareTo(Leaf<E> l) {
        return hash - l.hash;
    }
}




