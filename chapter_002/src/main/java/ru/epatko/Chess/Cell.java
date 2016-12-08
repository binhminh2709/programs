package ru.epatko.Chess;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         06.12.16.
 */
public class Cell {
    private int column;
    private int row;

    public Cell(int column, int row) {
        this.column = column;
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }
}
