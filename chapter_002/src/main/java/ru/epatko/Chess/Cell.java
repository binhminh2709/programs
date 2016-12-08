package ru.epatko.Chess;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         06.12.16.
 */
public class Cell {
    /**
     * Cell coordinate (column).
     */
    private int column;
    /**
     * Cell coordinate (row).
     */
    private int row;

    /**
     *
     * @param column - cell column.
     * @param row - cell row.
     */
    public Cell(int column, int row) {
        this.column = column;
        this.row = row;
    }

    /**
     *
     * @return - number of column.
     */
    public int getColumn() {
        return column;
    }

    /**
     *
     * @return - number of row.
     */
    public int getRow() {
        return row;
    }
}
