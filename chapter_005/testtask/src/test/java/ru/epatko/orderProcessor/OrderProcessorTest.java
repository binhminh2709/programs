package ru.epatko.orderProcessor;

import org.junit.Test;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         25.02.17.
 */
public class OrderProcessorTest {
    @Test
    public void whenStartOrderProcessorThenItPrintSortedOrdersBooks() throws Exception {

        OrderProcessor op = new OrderProcessor();
        final long startTime = System.currentTimeMillis();
        op.process("test.xml");
        final long endTime = System.currentTimeMillis();
        System.out.println(String.format("Time: %f s", (endTime - startTime)/1000f));
    }
}