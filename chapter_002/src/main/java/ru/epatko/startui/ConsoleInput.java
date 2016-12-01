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

    /**
     *
     * @param question question.
     * @param validKeys array of valid keys.
     * @return int key.
     */
    public int ask(String question, int[] validKeys) {
        int key = Integer.parseInt(this.ask(question));
        boolean exist = false;

        for (int element : validKeys) {
            if (key == element) {
                exist = true;
                break;
            }
        }
        if (exist) {
            return key;
        } else {
            throw new MenuOutException("Out of menu range.");
        }
    }
}
