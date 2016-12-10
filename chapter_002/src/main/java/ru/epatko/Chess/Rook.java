package ru.epatko.Chess;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         10.12.16.
 */
public class Rook extends Figure {

    /**
     * @param source - source cell (figure position).
     */
    public Rook(Cell source) {
        super(source);
    }

    /**
     * Check right way.
     *
     * @param dest - destination cell.
     * @return - array of the way cells.
     * @throws ImpossibleMoveException - can't move figure (incorrect destination cell).
     */
    @Override
    public Cell[] way(Cell dest) throws ImpossibleMoveException {
        Cell[] result = new Cell[8];

        int positionColumn = this.position.getColumn();
        int positionRow = this.position.getRow();
        int destColumn = dest.getColumn();
        int destRow = dest.getRow();
        int step = 1;
        int count;
        /**
         * Check right way.
         */
        if ((positionColumn == destColumn || positionRow == destRow)
                && !(positionColumn == destColumn && positionRow == destRow)) {

            if (positionColumn > destColumn || positionRow > destRow) {
                step = -1;
            }

            if (positionColumn == destColumn) {
                count = Math.abs(positionRow - destRow);
                for (int i = 0; i < count; i++) {
                    positionRow += step;
                    result[i] = new Cell(positionColumn, positionRow);
                }
            } else {
                count = Math.abs(positionColumn - destColumn);
                for (int i = 0; i < count; i++) {
                    positionColumn += step;
                    result[i] = new Cell(positionColumn, positionRow);
                }
            }

        } else {
            throw new ImpossibleMoveException("The Rook can't go there.");
        }
        return result;
    }

    /**
     * Clone figure to destination cell.
     *
     * @param destination - destination cell.
     * @return - new figure whose position equals destination cell.
     */
    @Override
    public Rook clone(Cell destination) {
        return new Rook(destination);
    }

}
