package ru.epatko.scientificCalc;

import ru.epatko.interactcalc.*;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         28.01.17.
 */
public class ScientificCalcStart extends InteractCalc {
    /**
     * Constructor.     *
     * @param input - input method.
     * @param menu  - calculator menu.
     */
    public ScientificCalcStart(Input input, CalcMenu menu) {
        super(input, menu);
    }

    /**
     * Main method.
     * @param args - no arguments.
     */
    public static void main(String[] args) {
        ScientificCalculator scientificCalculator = new ScientificCalculator();
        Input input = new ConsoleInput();
        CalcMenu menu = new ScientificCalcMenu(input, scientificCalculator);
        menu.fillMenu();
        ScientificCalcStart calc = new ScientificCalcStart(input, menu);
        menu.compute("h");
        calc.start();
    }
}