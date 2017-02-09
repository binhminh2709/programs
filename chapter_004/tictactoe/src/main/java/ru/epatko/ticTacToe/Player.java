package ru.epatko.ticTacToe;

/**
 * Tic Tac Toe player.
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         09.02.17.
 */
public class Player {

    /**
     * Get player coordinates.
     * @return - player coordinates.
     */
    public String play() {
        int x = (int) (Math.random() * 4);
        int y = (int) (Math.random() * 4);
        return String.format("%s %s", Integer.toString(x), Integer.toString(y));
    }
}