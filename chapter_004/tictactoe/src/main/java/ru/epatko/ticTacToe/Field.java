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

        //Check diagonal left-right-down.
        int i = x;
        int j = y;
        while (i < cells && j < cells) {
            if (this.field[i][j] != null && this.field[i][j].equals(sign)) {
                this.lineCount++;
            } else {
                break;
            }
            i++;
            j++;
        }
        //Check diagonal right-left-up.
        i = x - 1;
        j = y - 1;
        while (i >= 0 && j >= 0) {
            if (this.field[i][j] != null && this.field[i][j].equals(sign)) {
                this.lineCount++;
            } else {
                break;
            }
            i--;
            j--;
        }
        checkLineCount(sign);

        //Check diagonal left-right-up.
        i = x;
        j = y;
        while (i < cells && j >= 0) {
            if (this.field[i][j] != null && this.field[i][j].equals(sign)) {
                this.lineCount++;
            } else {
                break;
            }
            i++;
            j--;
        }
        //Check diagonal right-left-down.
        i = x - 1;
        j = y + 1;
        while (i >= 0 && j < cells) {
            if (this.field[i][j] != null && this.field[i][j].equals(sign)) {
                this.lineCount++;
            } else {
                break;
            }
            i--;
            j++;
        }
        checkLineCount(sign);

        // Check horizontal left-right.
        i = x;
        j = y;
        while (i < cells) {
            if (this.field[i][j] != null && this.field[i][j].equals(sign)) {
                this.lineCount++;
            } else {
                break;
            }
            i++;
        }

        // Check horizontal right-left.
        i = x - 1;
        while (i >= 0) {
            if (this.field[i][j] != null && this.field[i][j].equals(sign)) {
                this.lineCount++;
            } else {
                break;
            }
            i--;
        }
        checkLineCount(sign);

        // Check vertical up-down.
        i = x;
        j = y;
        while (j < cells) {
            if (this.field[i][j] != null && this.field[i][j].equals(sign)) {
                this.lineCount++;
            } else {
                break;
            }
            j++;
        }

        // Check vertical down-up.
        j = y - 1;
        while (j >= 0) {
            if (this.field[i][j] != null && this.field[i][j].equals(sign)) {
                this.lineCount++;
            } else {
                break;
            }
            j--;
        }
        checkLineCount(sign);
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
