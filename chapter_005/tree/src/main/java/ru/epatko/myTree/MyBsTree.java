package ru.epatko.myTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Binary search tree.
 *
 * @author Mikhail Epatko (mikhail.epatko@gmail.com)
 *         21.02.17.
 */
public class MyBsTree<E> {


    /**
     * Root.
     */
    private Leaf<E> root;

    /**
     * Basic leaf.
     */
    static class Leaf<E> implements Comparable<Leaf<E>> {


        final E value;
        Leaf<E> left;
        Leaf<E> right;

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
            return value.hashCode();
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
     * @param value value
     * @return true if value was added
     */
    public boolean addChild(E value) {
        boolean result = false;
        if (root == null) {
            root = new Leaf<>(value);
            result = true;
        } else {
            Leaf<E> child = new Leaf<>(value);
            Leaf<E> parent = root;
            int tmp;
            while (!result) {
                tmp = child.compareTo(parent);
                if (tmp > 0) {
                    if (parent.right != null) {
                        parent = parent.right;
                    } else {
                        parent.right = child;
                        result = true;
                    }
                } else {
                    if (parent.left != null) {
                        parent = parent.left;
                    } else {
                        parent.left = child;
                        result = true;
                    }
                }
            }
        }
        return result;
    }

    /**
     * Calls getValuesList with parameters necessary to get list of values.
     * @return list of values
     */
    public List<E> getChildren() {
        List<E> list = new ArrayList<>();
        return getValuesList(list, root);
    }

    /**
     * Runs by tree and fills list of values.
     * @param list list
     * @param startPoint start point
     * @return list of values
     */
    private List<E> getValuesList(List<E> list, Leaf<E> startPoint) {

        if (startPoint.left != null) {
            getValuesList(list, startPoint.left);
        }
        list.add(startPoint.value);
        if (startPoint.right != null) {
            getValuesList(list, startPoint.right);
        }
        return list;
    }

    /**
     * Calls findLeafs with parameters necessary to find all the leafs containing the value.
     * @param value value
     * @return list of leafs containing the value
     */
    public List<Leaf<E>> findElement(E value) {
        List<Leaf<E>> list = new ArrayList<>();
        if (root.value.equals(value)) {
            list.add(root);
        }
        return  findLeafs(list, root, value);
    }

    /**
     * Finds all the leafs containing the value.
     * @param list list of leafs containing the value
     * @param startPoint start point to search (root)
     * @param value value
     * @return list of leafs containing the value
     */
    private List<Leaf<E>> findLeafs(List<Leaf<E>> list, Leaf<E> startPoint, E value) {
        if (startPoint.left != null) {
            findLeafs(list, startPoint.left, value);
        }
        if (value.equals(startPoint.value)) {
            list.add(startPoint);
        }
        if (startPoint.right != null) {
            findLeafs(list, startPoint.right, value);
        }
        return list;
    }

    /**
     * Binary search.
     * @param value value
     * @return all the leafs containing the value
     */
    public List <Leaf<E>> binarySearch(E value) {
        Leaf<E> temp = root;
        List<Leaf<E>> list = new ArrayList<>();
        while (temp != null) {
            int v = value.hashCode();
            int t = temp.hashCode();
            if (v < t) {
                temp = temp.left;
            } else if (v > t) {
                temp = temp.right;
            } else {
                list.add(temp);
                temp = temp.left;
            }
        }
        return list;
    }

    /**
     * Call tree balance checker with start point parameter.
     * @return {@code true} if tree has ideal balance
     */
    public boolean checkBalance() {
        return checkTreeBalance(root);
    }

    /**
     * Check tree balance.
     * @param startPoint point to start checking
     * @return {@code true} if tree has ideal balance
     */
    private boolean checkTreeBalance (Leaf<E> startPoint) {
        if (startPoint.left == null && startPoint.right == null) {
            return true;
        } else if (!(startPoint.left != null && startPoint.right != null)) {
            return false;
        }
        return checkTreeBalance(startPoint.left) && checkTreeBalance(startPoint.right);
    }
}
