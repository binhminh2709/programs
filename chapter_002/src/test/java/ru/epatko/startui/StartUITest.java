package ru.epatko.startui;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import org.apache.commons.io.output.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         24.11.16.
 */
public class StartUITest {

    @Test
    public void wenStartTheProgramThenPrintResult() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut (new PrintStream(output));

       // Input inputMethod = new StubInput();
        //StartUI start = new StartUI(inputMethod);
        StartUI.main(new String[2]);

        assertThat(output.toString(), is("-----------------------\nMenu:\n-----------------------\n"
                                        + "0 - Print this menu.\n1 - Add new order.\n2 - Change order.\n"
                                        + "3 - Delete order.\n4 - Get list of orders.\n5 - Get order by name.\n"
                                        + "6 - Quit program.\nEnter your choice number: \nEnter order name: \n"
                                        + "Enter order description: \nOrder ID: 1 was added.\nEnter your choice number: \n"));
    }

    @Test
    public void wenEnterIncorrectNumberThenGetWarning() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut (new PrintStream(output));

        Input inputMethod = new TestInput();
        StartUI start = new StartUI(inputMethod);
        start.choice();
        assertThat(output.toString(), is("Enter your choice number: \nIncorrect input. Try again.\nEnter your choice number: \n"));

    }

    private class TestInput implements Input {

        private String[] answers = {"7", "6"};
        private int index;

        public String ask(String question) {
            System.out.println(question);
            return this.answers[this.index++];
        }
    }
}