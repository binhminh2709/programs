package ru.epatko.ticTacToe;

/**
 * Tic Tac Toe player.
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         09.02.17.
 */
public class Player {

    /**
     * Count of cells by vertical and horizontal.
     */
    private final int cells;

    /**
     * Default constructor.
     */
    public Player() {
        this.cells = 3;
    }

    /**
     * Constructor.
     * @param cells - count of cells by vertical and horizontal.
     */
    public Player(int cells) {
        this.cells = cells;
    }

    /**
     * Get player coordinates.
     * @param field - playing field.
     * @return - player coordinates.
     */
    public String play(String[][] field) {
        int x = (int) (Math.random() * (cells + 1));
        int y = (int) (Math.random() * (cells + 1));
        return String.format("%s %s", Integer.toString(x), Integer.toString(y));
    }
}