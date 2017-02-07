package ru.epatko.ticTacToe;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         07.02.17.
 */
public class Zero implements Sign {
    /**
     * Row number.
     */
    private final int row;
    /**
     * Sign number.
     */
    private final int column;

    /**
     * Constructor.
     * @param row - sign row number.
     * @param column - sign column number.
     */
    public Zero(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Get sign row number.
     * @return - row number.
     */
    @Override
    public int getRow() {
        return this.row;
    }

    /**
     * Get sign column number.
     * @return - column number.
     */
    @Override
    public int getColumn() {
        return this.column;
    }
}
