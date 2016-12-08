package ru.epatko.Chess;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         08.12.16.
 */
public class FreePlace extends Figure {


    public FreePlace() {
        super(null);
    }

    @Override
    public Cell[] way(Cell dest) throws ImpossibleMoveException {
        return new Cell[0];
    }
}
