package ru.epatko.myLists;

import org.junit.*;
import org.junit.rules.ExpectedException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         14.02.17.
 */
public class MyArrayListTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void whenAddElementsToMyArrayListThenCanGetThem() {
        MyArrayList<Integer> arrayList = new MyArrayList<>(3);

        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);

        assertThat(arrayList.get(0), is(1));
        assertThat(arrayList.get(1), is(2));
        assertThat(arrayList.get(2), is(3));
    }

    @Test
    public void whenAddTwoElementsToMyArrayListAndCallThirdThenGetNull() throws Exception {

        MyArrayList<Integer> arrayList = new MyArrayList<>(3);

        arrayList.add(1);
        arrayList.add(2);

        assertEquals(arrayList.get(2), null);
    }

    @Test
    public void whenCallElementByWrongIndexThenGetIndexOutOfBoundsException() throws Exception {

        expectedException.expect(IndexOutOfBoundsException.class);
        MyArrayList<Integer> arrayList = new MyArrayList<>(2);

        arrayList.add(1);
        arrayList.add(2);

        arrayList.get(3);
    }

    @Test
    public void whenCreateForEachLoopThenCanGetEachElementOfMyArrayList() throws Exception {

        MyArrayList<Integer> arrayList = new MyArrayList<>(3);

        arrayList.add(0);
        arrayList.add(1);
        arrayList.add(2);
        int i = 0;

        for (Object element : arrayList) {
            assertThat(element, is(i++));
        }
    }
}