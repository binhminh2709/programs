package ru.epatko.Chess;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         07.12.16.
 */
public class OccupiedWayException extends Exception {

    /**
     *
     * @param message - exception message.
     */
    public OccupiedWayException(String message) {
        super(message);
    }
}
