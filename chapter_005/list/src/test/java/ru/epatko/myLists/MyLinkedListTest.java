package ru.epatko.myLists;

import org.junit.*;
import org.junit.rules.ExpectedException;

import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         14.02.17.
 */
public class MyLinkedListTest {

    public MyLinkedList<String> mll;


    @Before
    public void initialize() {
        mll = new MyLinkedList<>();
        mll.add("1");
        mll.add("2");
        mll.add("3");
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void whenAddElementsThenCanGetThem() {
        assertThat(mll.get(0), is("1"));
        assertThat(mll.get(2), is("3"));
        assertThat(mll.get(1), is("2"));
    }

    @Test
    public void whenCallElementByWrongIndexThenGetIndexOutOfBoundsException() {

        expectedException.expect(IndexOutOfBoundsException.class);

        mll.get(4);
    }

    @Test
    public void whenCreateForEachLoopThenCanGetEachElementOfMyArrayList() {

        MyLinkedList<Integer> mlli = new MyLinkedList<>();

        mlli.add(0);
        mlli.add(1);
        mlli.add(2);
        int i = 0;

        for (Object element : mlli) {
            assertThat(element, is(i++));
        }
    }

    @Test
    public void whenCallNextInMyIteratorOfMyLinkedListThenGetNextElement() {
        Iterator<String> it = mll.iterator();

        assertThat(it.next(), is("1"));
        assertThat(it.next(), is("2"));
        assertThat(it.next(), is("3"));
    }

    @Test
    public void whenMyLinkedListHasNoMoreElementsAndWeCallNextInMyIteratorThenGetNoSuchElementException() {
        expectedException.expect(NoSuchElementException.class);

        Iterator<String> it = mll.iterator();
        it.next();
        it.next();
        it.next();

        it.next();
    }

    @Test
    public void whenGetFirstElementFromMyQueueThenSecondElementBecomesTheFirst() {
        MyQueue<Integer> mq = new MyQueue<>();
        mq.enQueue(1);
        mq.enQueue(2);

        mq.deQueue();

        assertThat(mq.deQueue(), is(2));
    }

    @Test
    public void whenMyQueueHasNoElementsAndWeTryGetFirstElementThenGetNoSuchElementException() {
        expectedException.expect(NoSuchElementException.class);

        MyQueue<Integer> mq = new MyQueue<>();
        mq.enQueue(1);
        mq.deQueue();

        mq.deQueue();
    }

    @Test
    public void whenGetElementFromMyStackThenSecondElementBecomesTheFirst() {

        MyStack<Integer> ms = new MyStack<>();
        ms.push(1);
        ms.push(2);

        assertThat(ms.pop(), is(2));
        assertThat(ms.pop(), is(1));
    }

    @Test
    public void whenMyStackHasNoElementsAndWeTryGetElementThenGetNoSuchElementException() {
        expectedException.expect(NoSuchElementException.class);

        MyStack<Integer> ms = new MyStack<>();
        ms.push(1);
        ms.pop();

        ms.pop();
    }








}