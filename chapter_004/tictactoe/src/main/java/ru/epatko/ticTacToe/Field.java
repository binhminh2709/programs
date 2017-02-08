package ru.epatko.ticTacToe;

import java.util.Scanner;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         07.02.17.
 */
public class Field {

    /**
     * Count of cells by vertical and horizontal.
     */
    private int cells;

    private int winCount = 3;

    private int lineCount = 0;

    /**
     * Playing field.
     */
    public Sign[][] field = new Sign[this.cells][this.cells];
    /**
     * Full cells count.
     */
    private int fullCellsCount = 0;

    /**
     * Default constructor.
     */
    public Field() {
        this.cells = 3;
    }
    /**
     * Constructor.
     * @param cells - count of cells by vertical and horizontal.
     */
    public Field(int cells, int winCount) {
        this.cells = cells;
    }


    /**
     * Set zero to cell.
     * @param coordinates - sign coordinates.
     */
    public void setSign(String coordinates, String sign) throws IllegalArgumentException {
        Scanner scanner = new Scanner(coordinates);

        try {
            int row = scanner.nextInt();
            int column = scanner.nextInt();

            if (this.field[row - 1][column - 1] != null) {
                this.field[row - 1][column - 1] = new Sign(sign);
            }
        } catch (IllegalArgumentException iae) {
            System.out.println("Incorrect input");
        }
    }
    /**
     * Set cross to cell.
     * @param row - row number.
     * @param column - column number.
     * @param sign - sign.
     */
    public void setCross(int row, int column, String sign) throws ImpossibleSetException {

        int x = row - 1;
        int y = column - 1;
        if (x < 0 || y < 0 || row > this.cells || column > this.cells || this.field[x][y] != null) {
            throw new ImpossibleSetException("You can't set sign there.");
        } else {
            this.field[x][y] = new Sign(sign);
            this.fullCellsCount++;
            checkLines(x, y, sign);
        }
        if (fullCellsCount == cells * cells) {
            exitGame("No free cells already.");
        }
    }

    /**
     * Check sign lines.
     * @param x - row index.
     * @param y - column index.
     * @param sign - sign.
     */
    private void checkLines(int x, int y, String sign) {

        int i = x;
        int j = y;
        while (i < cells && j < cells) {
            if (this.field[i][j].getName().equals(sign)) {
                this.lineCount++;
            }
            i++;
            j++;
        }
        i = x - 1;
        j = y - 1;
        while (i >= 0 && j >= 0) {
            if (this.field[i][j].getName().equals(sign)) {
                this.lineCount++;
            }
            i--;
            j--;
        }
        checkLineCount(sign);

        i = x;
        j = y;
        while (i < cells) {
            if (this.field[i][j].getName().equals(sign)) {
                this.lineCount++;
            }
            i++;
        }

        i = x - 1;
        while (i >= 0) {
            if (this.field[i][j].getName().equals(sign)) {
                this.lineCount++;
            }
            i--;
        }
        checkLineCount(sign);

        i = x;
        j = y;
        while (j < cells) {
            if (this.field[i][j].getName().equals(sign)) {
                this.lineCount++;
            }
            j++;
        }

        j = y - 1;
        while (j >= 0) {
            if (this.field[i][j].getName().equals(sign)) {
                this.lineCount++;
            }
            j--;
        }
        checkLineCount(sign);
    }

    public void checkLineCount(String sign) {
        if (lineCount == winCount) {
            exitGame(String.format("%s - is winner.", sign));
        } else {
            lineCount = 0;
        }
    }




    /**
     * Exit game.
     * @param message - reason to exit.
     */
    private void exitGame(String message) {
        System.out.println(message);
        System.exit(0);
    }

    /**
     * Print field.
     */
    public void printField() {
        String cell;
        System.out.println("[1][2][3]");
        for (int i = 1; i < this.cells; i++) {
            System.out.printf("[%d]", i + 1);
            for (int j = 0; j < this.cells; j++) {
                if (this.field[i][j] != null) {
                    cell = this.field[i][j].getName();
                } else {
                    cell = "[ ]";
                }
            System.out.print(cell);
            }
        }
    }



}
