package ru.epatko.Chess;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         05.12.16.
 */
public abstract class Figure implements Cloneable {
    /**
     * Figure position.
     */
     final Cell position;

    /**
     *
     * @param source - source cell (figure position).
     */
    public Figure(Cell source) {
       this.position = source;
    }
/**
     * Check right way.
     * @param dest - destination cell.
     * @return - array of the way cells.
     * @throws ImpossibleMoveException - can't move figure (incorrect destination cell).
     */
    public abstract Cell[] way(Cell dest) throws ImpossibleMoveException;
/**
     * Clone figure to destination cell.
     * @param destination - destination cell.
     * @return - new figure whose position equals destination cell.
     */
    public abstract Figure clone(Cell destination);

}
