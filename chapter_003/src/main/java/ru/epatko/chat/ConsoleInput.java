package ru.epatko.chat;

import java.util.Scanner;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         25.12.16.
 */
public class ConsoleInput implements Input {

    /**
     * Console input scanner.
     */
    private Scanner scanner = new Scanner(System.in);
    /**
     * Console input phrase.
     */
    private String phrase;

    /**
     * @param prefix - prefix.
     * @return - input phrase.
     */
    public String say(String prefix) {
        System.out.print(prefix);
        this.phrase = this.scanner.nextLine();

        return this.phrase;
    }
}
