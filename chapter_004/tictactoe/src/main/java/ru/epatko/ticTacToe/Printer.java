package ru.epatko.ticTacToe;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         13.02.17.
 */
public interface Printer {

    /**
     * Print message to console.
     *
     * @param message message to print
     */
    void printMessage(String message);

    /**
     * Print playing field to console.
     *
     * @param field playing field to print
     */
    void printField(String[][] field);
}
