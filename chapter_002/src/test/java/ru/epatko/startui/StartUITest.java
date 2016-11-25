package ru.epatko.startui;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;
import ru.epatko.tracker.Tracker;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         24.11.16.
 */
public class StartUITest {


    @Test
    public void whenCreateOrderOneThenCanGetNameOfOrderOne() {
        Input inputMethod = new StubInput();
        Tracker tracker = new Tracker();
        StartUI start = new StartUI(inputMethod, tracker);
        start.choice();

        assertThat(tracker.getOrderName(1), is("Order-1"));
    }

    @Test
    public void whenDeleteOrderTwoThenCantGetNameOfOrderTwo() {
        Input inputMethod = new StubInput();
        Tracker tracker = new Tracker();
        StartUI start = new StartUI(inputMethod, tracker);
        start.choice();

        assertThat(tracker.getOrderName(2), is("No order with this ID."));
    }

    @Test
    public void whenCreateOrderOneCommentThenCanGetCommentOfOrderOne() {
        Input inputMethod = new StubInput();
        Tracker tracker = new Tracker();
        StartUI start = new StartUI(inputMethod, tracker);
        start.choice();

        assertThat(tracker.getOrderComment(1), is("Comment-1"));
    }

    @Test
    public void whenChangeOrderThreeDescriptionThenCanGetNewDescriptionOfOrderThree() {
        Input inputMethod = new StubInput();
        Tracker tracker = new Tracker();
        StartUI start = new StartUI(inputMethod, tracker);
        start.choice();

        assertThat(tracker.getOrderDescription(3), is("New description-3"));
    }
}