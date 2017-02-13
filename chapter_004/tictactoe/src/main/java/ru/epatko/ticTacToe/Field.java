package ru.epatko.ticTacToe;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Playing field.
 *
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         07.02.17.
 */
public class Field {

    /**
     * Count of cells by vertical and horizontal in playing field.
     */
    private final int cells;

    /**
     * Win count.
     */
    private final int winCount;

    /**
     * Counter of sign in line.
     */
    private int lineCount = 0;

    /**
     * Playing field.
     */
    private String[][] field;
    /**
     * Full cells count.
     */
    private int fullCellsCount = 0;

    /**
     * Print playing field and messages.
     */
    private final Printer printer;

    /**
     * Default constructor.
     *
     * @param printer print playing field and messages
     */
    public Field(final Printer printer) {
        this.cells = 3;
        this.winCount = 3;
        this.field = new String[this.cells][this.cells];
        this.printer = printer;
    }
    /**
     * Constructor.
     *
     * @param cells count of cells by vertical and horizontal
     * @param winCount win count
     * @param printer print playing field and messages
     */
    public Field(final int cells, final int winCount, final Printer printer) {
        this.cells = cells;
        this.winCount = winCount;
        this.field = new String[this.cells][this.cells];
        this.printer = printer;
    }

    /**
     * Get playing field.
     *
     * @return playing field
     */
    public String[][] getField() {
        return this.field;
    }

    /**
     * Set sign to cell.
     * @param coordinates sign coordinates
     * @param sign sign
     * @throws IllegalArgumentException scanner exception
     * @throws NoSuchElementException scanner exception
     * @return result of method execution
     */
    public boolean setSign(String coordinates, String sign) throws IllegalArgumentException,
                                                                   NoSuchElementException {
        /**
         * Result of method execution.
         */
        boolean result = false;
        /**
         * Scanner.
         */
        Scanner scanner = new Scanner(coordinates);
        try {
            int row = scanner.nextInt();
            int column = scanner.nextInt();
            int x = column - 1;
            int y = row - 1;

            if (x >= 0 && y >= 0 && row <= this.cells && column <= this.cells && this.field[x][y] == null) {
                this.field[x][y] = sign;
                this.fullCellsCount++;
                checkLines(x, y, sign);
                result = true;
            }
            if (fullCellsCount == cells * cells) {
                exitGame("No free cells already. Game over");
            }
        } catch (IllegalArgumentException | NoSuchElementException exc) {
            this.printer.printMessage("Incorrect input");
        }
        return result;
    }

    /**
     * Check sign lines.
     *
     * @param x row index
     * @param y column index
     * @param sign sign
     */
    private void checkLines(int x, int y, String sign) {

        checkLeftRightDown(x, y, sign);
        checkRightLeftUp(x - 1, y - 1, sign);
        checkLineCount(sign);

        checkLeftRightUp(x, y, sign);
        checkRightLeftDown(x - 1, y + 1, sign);
        checkLineCount(sign);

        checkLeftRight(x, y, sign);
        checkRightLeft(x - 1, y, sign);
        checkLineCount(sign);

        checkUpDown(x, y, sign);
        checkDownUp(x, y - 1, sign);
        checkLineCount(sign);
    }

    /**
     * Check Left-Right-Down diagonal.
     *
     * @param x row index
     * @param y column index
     * @param sign sign
     */
    public void checkLeftRightDown(int x, int y, String sign) {
        if (x < cells && y < cells && this.field[x][y] != null && this.field[x][y].equals(sign)) {
            this.lineCount++;
            checkLeftRightDown(++x, ++y, sign);
        }
    }
    /**
     * Check Right-Left-Up diagonal.
     *
     * @param x row index
     * @param y column index
     * @param sign sign
     */
    public void checkRightLeftUp(int x, int y, String sign) {
        if (x >= 0 && y >= 0 && this.field[x][y] != null && this.field[x][y].equals(sign)) {
            this.lineCount++;
            checkRightLeftUp(--x, --y, sign);
        }
    }

    /**
     * Check Left-Right-Up diagonal.
     *
     * @param x row index
     * @param y column index
     * @param sign sign
     */
    public void checkLeftRightUp(int x, int y, String sign) {
        if (x < cells && y >= 0 && this.field[x][y] != null && this.field[x][y].equals(sign)) {
            this.lineCount++;
            checkLeftRightUp(++x, --y, sign);
        }
    }

    /**
     * Check Right-Left-Down diagonal.
     *
     * @param x row index
     * @param y column index
     * @param sign sign
     */
    public void checkRightLeftDown(int x, int y, String sign) {
        if (x >= 0 && y < cells && this.field[x][y] != null && this.field[x][y].equals(sign)) {
            this.lineCount++;
            checkRightLeftDown(--x, ++y, sign);
        }
    }

    /**
     * Check Left-Right horizontal.
     *
     * @param x row index
     * @param y column index
     * @param sign sign
     */
    public void checkLeftRight(int x, int y, String sign) {
        if (x < cells && this.field[x][y] != null && this.field[x][y].equals(sign)) {
            this.lineCount++;
            checkLeftRight(++x, y, sign);
        }
    }

    /**
     * Check Right-Left horizontal.
     *
     * @param x row index
     * @param y column index
     * @param sign sign
     */
    public void checkRightLeft(int x, int y, String sign) {
        if (x >= 0 && this.field[x][y] != null && this.field[x][y].equals(sign)) {
            this.lineCount++;
            checkRightLeft(--x, y, sign);
        }
    }

    /**
     * Check Up-Down vertical.
     *
     * @param x row index
     * @param y column index
     * @param sign sign
     */
    public void checkUpDown(int x, int y, String sign) {
        if (y < cells && this.field[x][y] != null && this.field[x][y].equals(sign)) {
            this.lineCount++;
            checkUpDown(x, ++y, sign);
        }
    }

    /**
     * Check Down-Up vertical.
     *
     * @param x row index
     * @param y column index
     * @param sign sign
     */
    public void checkDownUp(int x, int y, String sign) {
        // Check vertical down-up.
        if (y >= 0 && this.field[x][y] != null && this.field[x][y].equals(sign)) {
            this.lineCount++;
            checkDownUp(x, --y, sign);
        }
    }

    /**
     * Check the number of signs in the line.
     *
     * @param sign sign
     */
    public void checkLineCount(String sign) {
        if (this.lineCount == this.winCount) {
            exitGame(String.format("%s - is winner. Game over.", sign));
        } else {
            this.lineCount = 0;
        }
    }

    /**
     * Exit game.
     *
     * @param message reason to exit
     */
    private void exitGame(String message) {
        this.printer.printField(this.field);
        this.printer.printMessage(message);
        System.exit(0);
    }
}
