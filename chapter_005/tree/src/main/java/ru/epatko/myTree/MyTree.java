package ru.epatko.myTree;


import java.util.*;

/**
 * Simple tree.
 * @author Mikhail Epatko (mikhail.epatko@gmail.com)
 *         20.02.17.
 */
public class MyTree<E> {

    private Leaf<E> root;

    /**
     * Basic leaf.
     */
    static class Leaf<E> implements Comparable<Leaf<E>> {


        final E value;
        List<Leaf<E>> children = new ArrayList<>();

        /**
         * Constructs leaf.
         * @param value object {@code E} type
         */
        Leaf(final E value) {
            this.value = value;

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
                int hash = hashCode();
                if (hash == e.hashCode() && Objects.equals(value, e.value)) {
                    result = true;
                }
            }
            return result;
        }

        /**
         * Returns a hash code value for the object.
         *
         * @return a hash code value for this object.
         */
        @Override
        public int hashCode() {
            return value.hashCode() + children.hashCode();
        }

        /**
         * Compares this object with the specified object for order.
         */
        @Override
        public int compareTo(Leaf<E> l) {
            return hashCode() - l.hashCode();
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
        if (root == null) {
            root = parent;
        }
            parent.children.add(new Leaf<E>(value));
            result = true;
        return result;
    }

    /**
     * Get list of values.
     * @return list of values
     */
    public List<E> getChildren() {
        List<E> list = new ArrayList<>();
        list.add(root.value);
        return getValuesList(list, root);
    }

    /**
     * Running by tree and filling list of values.
     * @param list list
     * @param temp temporary leaf
     * @return list of values
     */
    private List<E> getValuesList(List<E> list, Leaf<E> temp) {

        for (Leaf<E> child : temp.children) {
            list.add(child.value);
            if (!child.children.isEmpty()) {
                getValuesList(list, child);
            }
        }
        return list;
    }

    public List<Leaf<E>> findElement(E element) {
        List<Leaf<E>> list = new ArrayList<>();
        if (root.value.equals(element)) {
            list.add(root);
        }
        return  findLeafs(list, root, element);
    }

    /**
     * Finds all the leafs containing the element.
     * @param list list of leafs containing the element
     * @param temp start point to search (root)
     * @param element element
     * @return list of leafs containing the element
     */
    private List<Leaf<E>> findLeafs(List<Leaf<E>> list, Leaf<E> temp, E element) {
        for (Leaf<E> child : temp.children) {
            if (child.value.equals(element)) {
                list.add(child);
            }
            if (!child.children.isEmpty()) {
                findLeafs(list, child, element);
            }
        }
        return list;
    }


}
