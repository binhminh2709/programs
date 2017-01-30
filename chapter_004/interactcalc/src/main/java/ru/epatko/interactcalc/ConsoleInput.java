package ru.epatko.interactcalc;

import java.util.Scanner;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         25.01.17.
 */
public class ConsoleInput implements Input {
    /**
     * Create new scanner.
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * @param question - question.
     * @return - user input.
     */
    public String ask(String question) {
        System.out.print(question);
        return this.scanner.nextLine();
    }
}
