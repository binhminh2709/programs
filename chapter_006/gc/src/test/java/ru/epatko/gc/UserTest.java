package ru.epatko.gc;

import org.junit.Test;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         01.03.17.
 */
public class UserTest {

    @Test
    public void whenCreateEmptyObjectsThenCanCalculateUsedMemory() {
        Runtime rt = Runtime.getRuntime();
        for( int i = 0; i < 1000000; i++) {
            UserWithoutFields user = new UserWithoutFields();
        }
        System.out.println("Used memory after creating objects: " + (rt.totalMemory() - rt.freeMemory()));
    }


    @Test
    public void whenCreateObjectsWithFieldsThenCanCalculateUsedMemory() {
        Runtime rt = Runtime.getRuntime();
        for( int i = 0; i < 1000; i++) {
            User user = new User("q", i);
        }
        System.out.println("Used memory after creating objects: " + (rt.totalMemory() - rt.freeMemory()));
    }

}