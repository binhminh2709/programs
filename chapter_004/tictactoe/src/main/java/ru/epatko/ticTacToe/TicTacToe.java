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
     * Print playing field and messages.
     */
    private final Printer printer;

    /**
     * Constructor.
     *
     * @param input input method
     * @param field playing field
     * @param player computer player
     * @param printer print playing field and messages
     */
    public TicTacToe(final Input input, final Field field, final Player player, final Printer printer) {
        this.input = input;
        this.field = field;
        this.player = player;
        this.printer = printer;
    }

    /**
     * Starts game.
     */
    public void start() {
        String coordinates;
        String signX = "X";
        String signO = "O";
        printer.printMessage("To play enter sign coordinates: x y");
        printer.printMessage("To quit program enter \"q\".");
        printer.printField(field.getField());

        /**
         * Ask computer player starts first.
         */
        if ("comp".equals(input.say("To computer starts first, enter \"comp\": "))) {
            while (!field.setSign(player.play(field.getField()), signO)) {
                continue;
            }
            printer.printField(field.getField());
        }

        /**
         * Main loop.
         */
        while (!"q".equals(coordinates = input.say(String.format("%s: ", signX)))) {

            if (field.setSign(coordinates, signX)) {
                while (!field.setSign(player.play(field.getField()), signO)) {
                    continue;
                }
                printer.printField(field.getField());
            }
        }
    }

    /**
     * Main class.
     *
     * @param args - no arguments
     */
    public static void main(String[] args) {
        Input input = new ConsoleInput();
        Printer printer = new ConsolePrinter();
        Field field = new Field(printer);
        Player player = new Player();
        TicTacToe game = new TicTacToe(input, field, player, printer);
        game.start();
    }
}
