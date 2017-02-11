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
     * Random logic is not good idea.
     *
     * @param field - playing field.
     * @return - player coordinates.
     */
    public String play(String[][] field) {
        int a, b;
        do {
            a = (int) (Math.random() * cells) + 1;
            b = (int) (Math.random() * cells) + 1;

        } while (field[a - 1][b - 1] != null);

        return String.format("%s %s", Integer.toString(b), Integer.toString(a));
    }
}