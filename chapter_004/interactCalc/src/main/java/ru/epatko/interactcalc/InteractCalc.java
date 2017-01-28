package ru.epatko.interactcalc;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         24.01.17.
 */
public class InteractCalc {

    /**
     * Calculator menu.
     */
    private final CalcMenu menu;
    /**
     * Input method.
     */
    private final Input input;

    /**
     * Constructor.
     * @param input - input method.
     * @param menu - calculator menu.
     */
    public InteractCalc(Input input, CalcMenu menu) {
        this.input = input;
        this.menu = menu;
    }

    /**
     * Loop.
     */
    public void start() {

        while (true) {
            this.menu.compute(this.input.ask("Enter command: "));
        }
    }


    /**
     * Main method.
     * @param args - no arguments.
     */
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        Input input = new ConsoleInput();
        CalcMenu menu = new CalcMenu(input, calc);
        menu.fillMenu();
        InteractCalc iCalc = new InteractCalc(input, menu);
        menu.compute("h");
        iCalc.start();
    }
}
