package ru.epatko.Chess;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         06.12.16.
 */
public class Bishop extends Figure {

    public Bishop(Cell source) {
        super(source);

    }

    public Cell[] way(Cell dest) throws ImpossibleMoveException {

        Cell[] result = new Cell[8];
        int positionColumn = this.position.getColumn();
        int positionRow = this.position.getRow();
        int destColumn = dest.getColumn();
        int destRow = dest.getRow();
        int stepColumn = 1;
        int stepRow = 1;

        if ((Math.abs(positionColumn - destColumn) == Math.abs(positionRow - destRow)) &&
                                                            (positionColumn != destColumn)) {
            if (positionColumn > destColumn) {
                stepColumn = -1;
            }
            if (positionRow > destRow) {
                stepRow = -1;
            }

            int count = Math.abs(positionColumn - destColumn);
            for (int i = 0; i < count; i++) {
                positionColumn += stepColumn;
                positionRow += stepRow;
                result[i] = new Cell(positionColumn, positionRow);
            }
        } else {
            throw new ImpossibleMoveException("The Bishop can't go there.");
        }
        return result;
    }
}
