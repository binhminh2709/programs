package ru.epatko.startui;

import org.junit.Test;
import ru.epatko.tracker.Tracker;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         24.11.16.
 */
public class StartUITest {

    @Test
    public void whenCreateOrderThenCanGetNameOfOrder() {
        String answers[] = {"Some Order Name", "Some Order Description"}; //Create an order #1 and exit cycle.
        Input inputMethod = new StubInput(answers);
        Tracker tracker = new Tracker();
        StartUI start = new StartUI(inputMethod, tracker);
        start.init();

        assertThat(tracker.getOrderName(1), is("Some Order Name"));

    }


    @Test
    public void whenCreateToMachOrdersThenOutOfMemoryError() {
        Input testInput = new TestInput();
        Tracker tracker = new Tracker();
        StartUI start = new StartUI(testInput, tracker);
        start.init();



    }

    private class TestInput implements Input {
        String[] answers = new String[] {"0", "0", ""};
        int index = 0;
        int count = 1;
        public String ask(String question) {
            if (count > 24000) {
                return "y";
            }

            if (this.index == 3) {
                this.index = 0;
            }
            count++;
            return this.answers[this.index++];
        }

        public int ask(String question, int[] range) {
            return 0;
        }

    }
}



