package ru.epatko.synchronizing;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.junit.Test;

import java.io.PrintStream;
import java.util.HashMap;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.Is.isA;
import static org.junit.Assert.*;

/**
 * Created by sten on 20.03.17.
 */
public class UserStorageTest {
    private User userOne = new User(1, "One");
    private User userTwo = new User(2, "Two");
    private User userThree = new User(3, "Three");
    UserStorage us = new UserStorage();

    @Test
    public void whenAddUserThenCanGetUserInfo() {
        userOne.setAmount(10);
        us.add(userOne);

        assertThat(us.read(1), is("User name: One, ID: 1, amount: 10.00"));

    }

    @Test
    public void whenChangeUserNameThenItChanged() {
        us.add(userTwo);
        us.changeName(2, "Second");

        assertThat(userTwo.getName(), is("Second"));
    }

    @Test
    public void whenChangeUserAmountThenItChanged() {
        us.add(userThree);
        us.changeAmount(3, 30d);
        assertThat(userThree.getAmount(), is(30d));
    }

    @Test
    public void whenDeleteUserThenCantGetUserInfo() {
        us.add(userOne);
        us.delete(1);
        assertThat(us.read(1), is("There are no user with this ID."));
    }

    @Test
    public void whenTransferAmountThenAmountChanged() throws Exception {
        us.add(userOne);
        userOne.setAmount(10);
        userTwo.setAmount(20);
        us.add(userTwo);

        us.transfer(1, 2, 5d);

        assertThat(userOne.getAmount(), is(5d));
        assertThat(userTwo.getAmount(), is(25d));
    }

    @Test
    public void whenTryingTransferByWrongUserIDThenGetFalse() {
        us.add(userOne);
        userOne.setAmount(10);
        userTwo.setAmount(20);
        us.add(userTwo);
        assertThat(us.transfer(1, 3, 5d), is(false));
    }

    @Test
    public void whenTryingDeleteUserByWrongIDThenGetErrorMessage() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut (new PrintStream(out));
        us.delete(1);
        assertThat(out.toString(), is("Incorrect ID." + System.getProperty("line.separator")));
    }

    @Test
    public void whenTryingReadUserInfoByWrongIDThenGetErrorMessage() {

        assertThat(us.read(1), is("There are no user with this ID."));
    }

    @Test
    public void whenAmountIsNotEnoughThenCantTransferAmountAndGetErrorMessage() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut (new PrintStream(out));
        us.add(userOne);
        userOne.setAmount(10);
        userTwo.setAmount(20);
        us.add(userTwo);
        assertThat(us.transfer(1, 2, 15d), is(false));
        assertThat(out.toString(), is("Amount isn't enough." + System.getProperty("line.separator")));
        assertThat(userOne.getAmount(), is(10d));
        assertThat(userTwo.getAmount(), is(20d));
    }


}