package ru.epatko.ticTacToe;

import ru.epatko.chat.ConsoleInput;
import ru.epatko.chat.Input;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         08.02.17.
 */
public class TicTacToe {
    private final Player player;
    private final Input input;
    private final Field field;

    public TicTacToe(Input input, Field field, Player player) {
        this.input = input;
        this.field = field;
        this.player = player;
    }

    public void start() {
        String coordinates;
        String signX = "X";
        String signO = "O";
        System.out.println("Enter sign coordinates: x y");
        System.out.println("To quit program enter \"q\".");
        field.printField();
        while (!"q".equals(coordinates = input.say(String.format("%s: ", signX)))) {

            if (field.setSign(coordinates, signX)) {
                while (!field.setSign(player.play(), signO)) {}
                field.printField();
            }
        }
    }


    public static void main(String[] args) {
        Input input = new ConsoleInput();
        Field field = new Field();
        Player player = new Player();
        TicTacToe game = new TicTacToe(input, field, player);
         game.start();
    }


}
