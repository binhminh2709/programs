package ru.epatko.producerconsumer;

import org.slf4j.*;

import java.util.*;


/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         28.03.17
 */
public class ProducerConsumer<T> {

    /**
     * List.
     */
    private LinkedList<T> list = new LinkedList();
    /**
     * Locking object.
     */
    private Object lock = new Object();
    /**
     * Logger.
     */
    private final Logger logger;

    /**
     * Constructor.
     * @param logger logger.
     */
    public ProducerConsumer(final Logger logger) {
        this.logger = logger;
    }

    /**
     * Get element from list.
     * @return element type T
     */
    public T get() {
        T result  = null;
        synchronized(this.lock) {
            while (list.isEmpty()) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    logger.info(e.getMessage());
                }
            }
            result = list.poll();
            System.out.println(String.format("Thread ID: %s returned: %s",
                                              Thread.currentThread().getId(),
                                              result.toString()));
            return result;
        }
    }

    /**
     * Add element to list.
     * @param newElement new element type T
     */
    public void add(T newElement) {
        synchronized (this.lock) {
            list.add(newElement);
            System.out.println(String.format("Thread ID: %s added: %s",
                                              Thread.currentThread().getId(),
                                              newElement.toString()));
            lock.notifyAll();
        }
    }


    /**
     * Main method.
     * @param args no arguments
     */
    public static  void main(String[] args) {

        /**
         * Logger.
         */
        final Logger logger = LoggerFactory.getLogger("Searcher");

        /**
         * Blocking queue.
         */
        ProducerConsumer<Integer> integerBlockingQueue = new ProducerConsumer<>(logger);

        /**
         * Thread producer.
         */
        Thread producerOne = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    integerBlockingQueue.add(i);
                }
            }
        };

        /**
         * Thread consumer.
         */
        Thread consumerOne = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    integerBlockingQueue.get();
                }
            }
        };

        /**
         * Thread consumer.
         */
        Thread consumerTwo = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    integerBlockingQueue.get();
                }
            }
        };
        consumerOne.start();
        consumerTwo.start();
        producerOne.start();
    }
}
