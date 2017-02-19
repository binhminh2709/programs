package ru.epatko.maps;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         19.02.17.
 */
public class MyMapTest {


    MyMap<User, Integer> map;
    Calendar birthday = new GregorianCalendar(1971, 01, 01);
    UserHE userOne = new UserHE("Name", 2, birthday);
    UserHE userTwo = new UserHE("Name", 2, birthday);
    UserHE userThree = new UserHE("Name1", 1, new GregorianCalendar(1972, 02, 02));
    Iterator<Integer> it;

    @Before
    public void initialize() {
        map = new MyMap<>();
        map.insert(userOne, 1);
        map.insert(userTwo, 2);
        map.insert(userThree, 3);
        it = map.iterator();
    }

    @Test
    public void whenTryAddDifferentObjectsWithSameKeysThenMyMapReplaceOldElementByKey() {
        assertThat(map.get(userOne), is(2));
        assertThat(map.get(userTwo), is(2));
        assertThat(map.get(userThree), is(3));

        assertThat(map.getSize(), is(2));

    }

    @Test
    public void whenTryToGetElementByWrongKeyThenGetNull() {
        UserHE userFour = new UserHE("Name4", 0, birthday);
        assertNull(map.get(userFour));
    }

    @Test
    public void whenCreateForEachLoopThenCanGetEachElementOfMyMap() {

        int i = 0;
        for (Object element : map) {
            assertEquals(2, (Integer) element, 1);
        }
    }

    @Test
    public void whenCallNextInMyIteratorOfMyMapThenGetNextElement() {
        assertEquals(2, it.next(), 1);
        assertEquals(3, it.next(), 1);
    }

    @Test (expected = NoSuchElementException.class)
    public void whenCallIteratorNextMoreThanMyMapHasElementsThenGetNoSuchElementException() {
        it.next();
        it.next();
        it.next();
    }

    @Test
    public void whenDeleteElementThanCantGetItFromMyMap() {
        assertThat(map.delete(userOne), is(true));
        assertEquals(null, map.get(userTwo));
    }

    @Test
    public void whenTryDeleteElementByWrongKeyThenGetFalse() {
        UserHE userFour = new UserHE("Name4", 0, birthday);
        assertThat(map.delete(userFour), is(false));
    }

//************************Test MyMap contains one node************************************//


    MyMap<User, Integer> oneNodeMap;
    UserHE userFour = new UserHE("Name4", 0, birthday);
    Iterator<Integer> itr;

    @Before
    public void initializeOneNodeMap() {
        oneNodeMap = new MyMap<>(1);
        oneNodeMap.insert(userOne, 1);
        oneNodeMap.insert(userTwo, 2);
        oneNodeMap.insert(userThree, 3);
        oneNodeMap.insert(userFour, 4);
        itr = oneNodeMap.iterator();
    }

    @Test
    public void whenAddElementToOneNodeMapThenChangeSizeOfMap() {
        assertThat(oneNodeMap.getSize(), is(3));
    }

    @Test
    public void whenAddElementToOneNodeMapThenCanGetThem() {

        assertThat(oneNodeMap.get(userOne), is(2));
        assertThat(oneNodeMap.get(userTwo), is(2));
        assertThat(oneNodeMap.get(userThree), is(3));
        assertThat(oneNodeMap.get(userFour), is(4));
    }
    @Test
    public void whenDeleteFirstElementFromOneNodeMapThenChangeSizeOfMapAndCantGetDeletedElement() {

        assertThat(oneNodeMap.delete(userOne), is(true));

        assertThat(oneNodeMap.getSize(), is(2));
        assertNull(oneNodeMap.get(userOne));
        assertNull(oneNodeMap.get(userTwo));
    }
    @Test
    public void whenDeleteMiddleElementFromOneNodeMapThenChangeSizeOfMapAndCantGetDeletedElement() {

        assertThat(oneNodeMap.delete(userThree), is(true));

        assertThat(oneNodeMap.getSize(), is(2));
        assertNull(oneNodeMap.get(userThree));
    }

    @Test
    public void whenDeleteLastElementFromOneNodeMapThenChangeSizeOfMapAndCantGetDeletedElement() {

        assertThat(oneNodeMap.delete(userFour), is(true));

        assertThat(oneNodeMap.getSize(), is(2));
        assertNull(oneNodeMap.get(userFour));
    }

    @Test
    public void whenCreateForEachLoopThenCanGetEachElementOfOneNodeMap() {

        int i = 2;
        for (Object element : oneNodeMap) {
            assertThat(element, is(i++));
        }
    }

    @Test
    public void whenCallNextInMyIteratorOfOneNodeMapThenGetNextElement() {

        assertThat(itr.next(), is(2));
        assertThat(itr.next(), is(3));
        assertThat(itr.next(), is(4));
    }

    @Test (expected = NoSuchElementException.class)
    public void whenCallIteratorNextMoreThanOneNodeMapHasElementsThenGetNoSuchElementException() {
        itr.next();
        itr.next();
        itr.next();
        itr.next();
    }

    @Test
    public void whenTryDeleteElementFromOneNodeMapByWrongKeyThenGetFalse() {
        UserHE wrongUser = new UserHE("NameWr", 100, birthday);
        assertThat(oneNodeMap.delete(wrongUser), is(false));
    }
}