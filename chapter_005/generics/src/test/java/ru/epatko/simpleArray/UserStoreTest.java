package ru.epatko.simpleArray;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         12.02.17.
 */
public class UserStoreTest {

    @Test
    public void whenAddOneElementToArrayAndThenCallSecondElementThenShouldGetNull() {
        SimpleArray<Base> array = new SimpleArray<>(2);
        User one = new User("1");
        array.add(one);
        assertEquals(array.get(1), null);
    }

    @Test
    public void whenAddUserThenCanGetIt() {
        SimpleArray<Base> array = new SimpleArray<>(2);
        UserStore storage = new UserStore(array);
        User one = new User("1");
        User two = new User("2");

        storage.add(one);
        storage.add(two);

        assertThat(storage.get("1"), is(one));
        assertThat(storage.get("2"), is(two));
        assertThat(array.get(0), is(one));
        assertThat(array.get(1), is(two));
    }

    @Test
    public void whenDeleteUserThenCantGetIt() {
        SimpleArray<Base> array = new SimpleArray<>(2);
        UserStore storage = new UserStore(array);
        User one = new User("1");
        User two = new User("2");

        storage.add(one);
        storage.add(two);
        storage.delete("1");

        assertEquals(storage.get("1"), null);
        assertThat(array.get(0), is(two));
    }

    @Test
    public void whenUpdateUserThenCanGetNewElement() {
        SimpleArray<Base> array = new SimpleArray<>(1);
        UserStore storage = new UserStore(array);
        User one = new User("1");
        User two = new User("2");

        storage.add(one);
        storage.update("1", two);

        assertThat(array.get(0), is(two));
    }
    //****************************************************************************************

    @Test
    public void whenAddRoleThenCanGetIt() {
        SimpleArray<Base> array = new SimpleArray<>(2);
        RoleStore storage = new RoleStore(array);
        Role one = new Role("1");
        Role two = new Role("2");

        storage.add(one);
        storage.add(two);

        assertThat(storage.get("1"), is(one));
        assertThat(storage.get("2"), is(two));
        assertThat(array.get(0), is(one));
        assertThat(array.get(1), is(two));
    }

    @Test
    public void whenDeleteRoleThenCantGetIt() {
        SimpleArray<Base> array = new SimpleArray<>(2);
        RoleStore storage = new RoleStore(array);
        Role one = new Role("1");
        Role two = new Role("2");

        storage.add(one);
        storage.add(two);
        storage.delete("1");

        assertEquals(storage.get("1"), null);
        assertThat(array.get(0), is(two));
    }

    @Test
    public void whenUpdateRoleThenCanGetNewElement() {
        SimpleArray<Base> array = new SimpleArray<>(1);
        RoleStore storage = new RoleStore(array);
        Role one = new Role("1");
        Role two = new Role("2");

        storage.add(one);
        storage.update("1", two);

        assertThat(array.get(0), is(two));
    }


}