package ru.epatko.maps;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         19.02.17.
 */
public class MyMap<K, V> implements Iterable<V> {

    /**
     * The default initial capacity - MUST be a power of two.
     */
    static final int DEFAULT_INITIAL_CAPACITY = 128;

    private Node<K, V>[] nodes;

    private int size;

    public MyMap() {
        this.nodes = (Node<K, V>[]) new Node[DEFAULT_INITIAL_CAPACITY];
    }

    public MyMap(int capacity) {
        this.nodes = (Node<K, V>[]) new Node[capacity];
    }

    public int getSize() {
        return this.size;
    }


    /**
     * Basic node, used for most entries.
     */
    static class Node<K, V> {
        final int hash;
        final K key;
        V value;
        Node<K, V> next;

        Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public final String toString() {
            return String.format("%d = %s", key, value);
        }

        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        public final boolean equals(Object o) {
            boolean result = false;
            if (o == this) {
                result = true;
            } else if (o instanceof Node) {
                Node<K, V> e = (Node<K, V>) o;
                if (Objects.equals(key, e.key) && Objects.equals(value, e.value)) {
                    result = true;
                }
            }
            return result;
        }
    }

    /**
     * Computes hash.
     * @param key object key
     * @return hash
     */
    public int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    public int getPosition(int hash) {
        return (nodes.length - 1) & hash;
    }


    /**
     * Insert new element into map.
     * @param key - key
     * @param value value
     * @return {@code true} if value was added
     */
    boolean insert(K key, V value) {
        int hash = hash(key);
        int position = getPosition(hash);
        boolean result = false;
        if (nodes[position] == null) {
            nodes[position] = new Node<>(hash, key, value, null);
            result = true;
            size++;
        } else {
            Node<K, V> e = nodes[position];
            Node<K, V> lastNode;
            K k;
            do {
                lastNode = e;
                if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k)))) {
                    e.value = value;
                    result = true;
                    break;
                }
            } while ((e = e.next) != null);
            if (!result) {
                lastNode.next = new Node<>(hash, key, value, null);
                result = true;
                size++;
            }
        }
        return result;
    }


    /**
     * Get element by key.
     * @param key key
     * @return element
     */
    V get(K key) {
        int hash = hash(key);
        int position = getPosition(hash);
        V result = null;
        if (nodes[position] != null) {
            Node<K, V> e = nodes[position];
            K k;
            do {
                if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k)))) {
                    result = e.value;
                    break;
                }
            } while ((e = e.next) != null);
        }
        return result;
    }

    /**
     * Delete element by key.
     * @param key key
     * @return {@code true} if element was deleted
     */
    boolean delete(K key) {

        int hash = hash(key);
        int position = getPosition(hash);
        boolean result = false;
        Node<K, V> e;
        Node<K, V> previousNode = null;
        K k;
        if ((e = nodes[position]) == null) {
            result = false;
        } else if (e.next == null) {
            if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k)))) {
                nodes[position] = null;
                e = null;
                result = true;
                size--;
            }
        } else {
            do {
                if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k)))) {
                    if (previousNode == null) {
                        nodes[position] = e.next;
                        e = null;
                        result = true;
                        size--;
                        break;
                    } else {
                        previousNode.next = e.next;
                        e = null;
                        result = true;
                        size--;
                        break;
                    }
                }
                previousNode = e;
            } while ((e = e.next) != null);
        }
        return result;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<V> iterator() {
        return new MyValueIterator<V>();
    }

    private class MyValueIterator<V> extends MyHashIterator implements Iterator<V> {
        public V next() {
            return (V) nextNode().value;
        }
    }

    abstract class MyHashIterator<K, V> {

        Node<K, V> nextNode = null;        // next node to return
        int itIndex;                      // current slot
        Node<K, V>[] table;

        private MyHashIterator() {
            this.itIndex = 0;
            table = (Node<K, V>[]) nodes;
        }

        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #nextNode} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        public boolean hasNext() {
            boolean result = false;
            if (nextNode == null) {
                while (itIndex < table.length && nextNode == null) {
                    nextNode = table[itIndex++];
                }
            } else if (nextNode.next != null) {
                result = true;
            }
            return (nextNode != null || result);
        }

        /**
         * Returns the next node in the iteration.
         *
         * @return the next node in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        public Node<K, V> nextNode() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node<K, V> result = nextNode;
            nextNode = nextNode.next;
            return result;
        }
    }
}
