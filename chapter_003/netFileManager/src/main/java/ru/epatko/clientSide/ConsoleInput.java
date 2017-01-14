package ru.epatko.clientSide;

import java.util.Scanner;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         25.12.16.
 */
public class ConsoleInput implements Input {

    /**
     * Console input scanner.
     */
    private final Scanner scanner = new Scanner(System.in);
      /**
     * @return - input phrase.
     */
    public String message() {
        System.out.print(">: ");

        return this.scanner.nextLine();
    }
}
