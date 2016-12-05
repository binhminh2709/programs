package ru.epatko.ParenthesisCheker;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         05.12.16.
 */
public class ParenthesisChecker {

    /**
     * Checker.
     * @param string - string with parentheses.
     * @return - true or false.
     */
    public boolean check(String string) {
        boolean result = false;
        int counter = 0;
        char[] symbols = string.toCharArray();
        for (char symbol : symbols) {
            if (symbol == '(') {
                counter++;
            } else if (symbol == ')') {
                counter--;
            }
            if (counter < 0) {
                break;
            }
        }
        if (counter == 0) {
            result = true;
        }
        return result;
    }
}
