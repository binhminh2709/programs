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
    public void whenCreateOrderThenCanGetNameOfOrder() {
        String answers[] = {"0", "Some Order Name", "Some Order Description", "5"}; //Create an order #1 and exit cycle.
        Input inputMethod = new StubInput(answers);
        Tracker tracker = new Tracker();
        StartUI start = new StartUI(inputMethod, tracker);
        start.init();

        assertThat(tracker.getOrderName(1), is("Some Order Name"));

    }

   @Test
    public void whenDeleteOrderThenCantGetNameOfOrder() {
        String answers[] = {"0", "Order-1", "Description-1",             // Create order #1.
                            "0", "Order", "Description",                 // Create order #2.
                            "2", "2",                                    // Delete order #2.
                            "5"};                                        // Exit cycle.
        Input inputMethod = new StubInput(answers);
        Tracker tracker = new Tracker();
        StartUI start = new StartUI(inputMethod, tracker);
        start.init();

        assertThat(tracker.getOrderName(2), is("No order with this ID."));

    }

    @Test
    public void whenCreateOrderCommentThenCanGetCommentOfOrder() {
        String answers[] = {"0", "Order-1", "Description-1",             // Create order #1.
                            "1", "1", "comment", "Comment",              // Change comment of order #1.
                            "5"};                                        // Exit cycle.
        Input inputMethod = new StubInput(answers);
        Tracker tracker = new Tracker();
        StartUI start = new StartUI(inputMethod, tracker);
        start.init();

        assertThat(tracker.getOrderComment(1), is("Comment"));
    }

    @Test
    public void whenChangeOrderDescriptionThenCanGetNewDescriptionOfOrder() {
        String[] answers = {"0", "Order-1", "Description-1",                    // Create order #1.
                            "0", "Order-2", "Description-2",                    // Create order #2.
                            "0", "Order-3", "Description-3",                    // Create order #3.
                            "1", "3", "description", "Some New description-3",  // Change description of order #3.
                            "5"};                                               // Exit cycle.
        Input inputMethod = new StubInput(answers);
        Tracker tracker = new Tracker();
        StartUI start = new StartUI(inputMethod, tracker);
        start.init();

        assertThat(tracker.getOrderDescription(3), is("Some New description-3"));
    }
}