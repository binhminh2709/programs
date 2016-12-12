package ru.epatko.inputChecker;

import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         10.12.16.
 */
public class InputChecker {

    /**
     *
     * @param in - input byte stream.
     * @return - true or false.
     */
  
    public boolean isNumber(InputStream in) {
        boolean result = false;
        try (Scanner scanner = new Scanner(in)) {
                int number = scanner.nextInt();
                if (number!=0 && (number % 2 == 0) && !scanner.hasNext()) {
                    result = true;
                }
            } catch (NoSuchElementException | IllegalStateException ex) {
                return result;
        }
        return result;
    }
}
