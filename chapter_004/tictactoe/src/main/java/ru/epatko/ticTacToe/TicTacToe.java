package ru.epatko.ticTacToe;

import ru.epatko.chat.ConsoleInput;
import ru.epatko.chat.Input;

/**
 * TicTacToe game.
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         08.02.17.
 */
public class TicTacToe {
    /**
     * Computer player.
     */
    private final Player player;
    /**
     * Input method.
     */
    private final Input input;
    /**
     * Playing field.
     */
    private final Field field;

    /**
     * Constructor.
     * @param input - input method.
     * @param field - playing field.
     * @param player - computer player.
     */
    public TicTacToe(Input input, Field field, Player player) {
        this.input = input;
        this.field = field;
        this.player = player;
    }

    /**
     * Starts game.
     */
    public void start() {
        String coordinates;
        String signX = "X";
        String signO = "O";
        System.out.println("To play enter sign coordinates: x y");
        System.out.println("To quit program enter \"q\".");
        field.printField();

        /**
         * Ask computer player starts first.
         */
        if ("comp".equals(input.say("To computer starts first, enter \"comp\": "))) {
            while (!field.setSign(player.play(), signO)) {
                continue;
            }
            field.printField();
        }

        /**
         * Main loop.
         */
        while (!"q".equals(coordinates = input.say(String.format("%s: ", signX)))) {

            if (field.setSign(coordinates, signX)) {
                while (!field.setSign(player.play(), signO)) {
                    continue;
                }
                field.printField();
            }
        }
    }

    /**
     * Main class.
     * @param args - no arguments.
     */
    public static void main(String[] args) {
        Input input = new ConsoleInput();
        Field field = new Field();
        Player player = new Player();
        TicTacToe game = new TicTacToe(input, field, player);
        game.start();
    }
}
