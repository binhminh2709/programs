package ru.epatko.startui;

import java.util.*;

/**
 * Console input.
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 */
public class ConsoleInput implements Input {
    /**
     * Create new scanner.
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     *
     * @param question - question.
     * @return - String.
     */
    public String ask(String question) {
        System.out.print(question);
        return this.scanner.nextLine();
    }
}
