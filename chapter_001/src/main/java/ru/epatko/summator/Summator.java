package ru.epatko.summator;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         09.03.17.
 */
public class Summator {
    private int result;

    public int calculateSumm(int n) {
        result = 0;
        for (int i = 1; i <= n; i++) {
            result += i;
        }
        return result;
    }
}
