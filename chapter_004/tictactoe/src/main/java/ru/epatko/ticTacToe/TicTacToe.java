package ru.epatko.ticTacToe;

import ru.epatko.chat.ConsoleInput;
import ru.epatko.chat.Input;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         08.02.17.
 */
public class TicTacToe {
    Input input;
    Field field;

    public TicTacToe(Input input, Field field) {
        this.input = input;
        this.field = field;
    }

    public void start() {
        String coordinates;
        field.printField();
        while (true) (!"exit".equals(coordinates = input.say("Enter sign coordinates (x y) or \"exit\": "))) {
            field.


        }
    }


    public static void main(String[] args) {
        Input input = new ConsoleInput();
        Field field = new Field();
        TicTacToe game = new TicTacToe(input, field);
         game.start();
    }


}
