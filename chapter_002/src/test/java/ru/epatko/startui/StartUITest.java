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
        String answers[] = {"1", "Some Order Name", "Some Order Description", "6"}; //Create order #1 and exit cycle.
        Input inputMethod = new StubInput(answers);
        Tracker tracker = new Tracker();
        StartUI start = new StartUI(inputMethod, tracker);
        start.choice();

        assertThat(tracker.getOrderName(1), is("Some Order Name"));
    }

   @Test
    public void whenDeleteOrderTwoThenCantGetNameOfOrderTwo() {
        String answers[] = {"1", "Order-1", "Description-1",             // Create order #1.
                            "1", "Order", "Description",                 // Create order #2.
                            "3", "2",                                    // Delete order #2.
                            "6"};                                        // Exit cycle.
        Input inputMethod = new StubInput(answers);
        Tracker tracker = new Tracker();
        StartUI start = new StartUI(inputMethod, tracker);
        start.choice();

        assertThat(tracker.getOrderName(2), is("No order with this ID."));
    }

    @Test
    public void whenCreateOrderOneCommentThenCanGetCommentOfOrderOne() {
        String answers[] = {"1", "Order-1", "Description-1",             // Create order #1.
                            "2", "1", "comment", "Comment",              // Change comment of order #1.
                            "6"};                                        // Exit cycle.
        Input inputMethod = new StubInput(answers);
        Tracker tracker = new Tracker();
        StartUI start = new StartUI(inputMethod, tracker);
        start.choice();

        assertThat(tracker.getOrderComment(1), is("Comment"));
    }

    @Test
    public void whenChangeOrderThreeDescriptionThenCanGetNewDescriptionOfOrderThree() {
        String[] answers = {"1", "Order-1", "Description-1",                    // Create order #1.
                            "1", "Order-2", "Description-2",                    // Create order #2.
                            "1", "Order-3", "Description-3",                    // Create order #3.
                            "2", "3", "description", "Some New description-3",  // Change description of order #3.
                            "6"};                                               // Exit cycle.
        Input inputMethod = new StubInput(answers);
        Tracker tracker = new Tracker();
        StartUI start = new StartUI(inputMethod, tracker);
        start.choice();

        assertThat(tracker.getOrderDescription(3), is("Some New description-3"));
    }
}