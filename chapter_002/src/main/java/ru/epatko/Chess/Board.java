package ru.epatko.Chess;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         06.12.16.
 */
public class Board {
    /**
     * Board cells.
     */
    public Figure[][] cells = new Figure[8][8];

    /**
     *
     * @param source - source cell.
     * @param dest - destination cell.
     * @return - boolean "true" or "false" (move is done).
     * @throws ImpossibleMoveException - can't move figure (incorrect source or destination cell).
     * @throws OccupiedWayException - there is another figure on the way.
     * @throws FigureNotFoundException - figure not found at source cell.
     */
    boolean move(Cell source, Cell dest) throws ImpossibleMoveException, OccupiedWayException,
                                                                      FigureNotFoundException {
        boolean result = false;

        try {
            int sourceColumn = source.getColumn();
            int sourceRow = source.getRow();

            /**
             * Checking source cell is on the board.
             */
            if ((cells[sourceColumn][sourceRow] == null)
                                                        || sourceColumn < 0
                                                        || sourceColumn > 7
                                                        || sourceRow    < 0
                                                        || sourceRow    > 7) {
                throw new FigureNotFoundException("Figure not found.");
            }

            /**
             * Checking destination cell is on the board.
             */
            if (dest.getColumn() < 0 || dest.getRow() > 7) {
                throw new ImpossibleMoveException("Destination cell is out of the board.");
            }

            /**
             * Checking the figure can go by the way.
             */
            Cell[] way = cells[sourceColumn][sourceRow].way(dest);

            /**
             * Checking the way is free.
             */
            for (Cell cell : way) {

                if (cell == null) {
                    break;
                }

                if (cells[cell.getColumn()][cell.getRow()] != null) {
                    throw new OccupiedWayException("There is another figure on the way.");
                }
            }

            /**
             * Clone figure to destination cell.
             */
            cells[dest.getColumn()][dest.getRow()] = cells[sourceColumn][sourceRow].clone(dest);

            /**
             * Empty source cell.
             */
            cells[sourceColumn][sourceRow] = null;

            result = true;

        } catch (ImpossibleMoveException | OccupiedWayException | FigureNotFoundException exc) {
            System.out.println(exc);
        }
        return result;
    }
}
