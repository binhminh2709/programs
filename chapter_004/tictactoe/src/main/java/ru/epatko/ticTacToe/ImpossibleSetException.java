package ru.epatko.ticTacToe;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         07.02.17.
 */
public class ImpossibleSetException extends Exception {

    /**
     * Exception.
     * @param message - exception message.
     */
    public ImpossibleSetException(String message) {
        super(message);
    }
}
