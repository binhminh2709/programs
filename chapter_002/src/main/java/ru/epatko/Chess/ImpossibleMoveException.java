package ru.epatko.Chess;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         06.12.16.
 */
public class ImpossibleMoveException  extends Exception {

    public ImpossibleMoveException(String message) {
        super(message);
    }
}
