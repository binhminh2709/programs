package ru.epatko.myTree;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         20.02.17.
 */
public class MyTree<E> {

    private Leaf<E> root;



    /**
     * Basic leaf.
     */
    static class Leaf<E> implements Comparable<Leaf<E>> {

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
        public Leaf(final E value, Leaf<E> parent, Leaf<E> left, Leaf<E> right) {
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


    /**
     * Adds value as a child of parent leaf.
     * @param parent parent leaf
     * @param value value
     * @return true if value was added
     */
    public boolean addChild(Leaf<E> parent, E value) {
        boolean result = false;
        if (parent.left != null && parent.right != null) {
            result = false;
        } else if (parent.left == null) {
            parent.left = new Leaf<>(value, parent, null, null);
        } else {
            parent.right = new Leaf<>(value, parent, null, null);
        }
        return result;
    }

    /**
     * Get list of values.
     * @return list of values
     */
    public List<E> getChildren() {
        List<E> list = new ArrayList<E>();
        Leaf<E> temp = root;
        return getValuesList(list, temp);
    }

    /**
     * Running by tree and filling list of values.
     * @param list list
     * @param temp temporary leaf
     * @return list of values
     */
    public List<E> getValuesList(List list, Leaf<E> temp) {
        if (temp != null) {
            getValuesList(list, temp.left);
            list.add(temp.value);
            getValuesList(list, temp.right);
        }
        return list;
    }
}
