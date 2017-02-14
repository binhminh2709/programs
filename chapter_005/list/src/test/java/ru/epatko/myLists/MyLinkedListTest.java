package ru.epatko.myLists;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         14.02.17.
 */
public class MyLinkedListTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void whenAddElementsThenCanGetThem() {

        MyLinkedList<String> mll = new MyLinkedList<>();

        mll.add("1");
        mll.add("2");
        mll.add("3");

        assertThat(mll.get(0), is("1"));
        assertThat(mll.get(2), is("3"));
        assertThat(mll.get(1), is("2"));
    }

    @Test
    public void whenCallElementByWrongIndexThenGetIndexOutOfBoundsException() {

        expectedException.expect(IndexOutOfBoundsException.class);
        MyLinkedList<String> mll = new MyLinkedList<>();

        mll.add("1");

        mll.get(1);
    }

    @Test
    public void whenCreateForEachLoopThenCanGetEachElementOfMyArrayList() {

        MyLinkedList<Integer> mll = new MyLinkedList<>();

        mll.add(0);
        mll.add(1);
        mll.add(2);
        int i = 0;

        for (Object element : mll) {
            assertThat(element, is(i++));
        }
    }

}