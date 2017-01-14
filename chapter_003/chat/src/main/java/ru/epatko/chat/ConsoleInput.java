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
     * @param prefix - prefix.
     * @return - input phrase.
     */
    public String say(String prefix) {
        System.out.print(prefix);

        return this.scanner.nextLine();
    }
}
