package ru.epatko.ticTacToe;

/**
 * Tic Tac Toe player.
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         09.02.17.
 */
public class Player {

    private final int cells;

    public Player() {
        this.cells = 3;
    }
    public Player(int cells) {
        this.cells = cells;
    }

    /**
     * Get player coordinates.
     * @param field - playing field.
     * @return - player coordinates.
     */
    public String play(String[][] field) {
        int x = (int) (Math.random() * 4);
        int y = (int) (Math.random() * 4);
        return String.format("%s %s", Integer.toString(x), Integer.toString(y));
    }
}