package ru.epatko.ticTacToe;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         07.02.17.
 */
public class ImpossibleCellException extends Exception {

    /**
     * Exception.
     * @param message - exception message.
     */
    public ImpossibleCellException(String message) {
        super(message);
    }
}
