package ru.epatko.scientificMenu;

import ru.epatko.interactcalc.*;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         26.01.17.
 */
public class ScientificMenu extends CalcMenu {

    /**
     * Constructor.
     * @param input - input method.
     * @param calc - calculator.
     */
    public ScientificMenu(Input input, Calculator calc) {
        super(input, calc);
    }

    /**
     * To fill scientific calculator menu.
     */
    @Override
    public void fillMenu() {
        super.fillMenu();
        calcActions.put("sin", new Sinus());
        calcActions.put("cos", new Cos());
    }

    /**
     * Sinus.
     */
    private class Sinus implements CalcAction {
        /**
         * Action name.
         * @return - name.
         */
        @Override
        public String name() {
            return "sin";
        }

        /**
         * Info.
         * @return - info about action.
         */
        @Override
        public String info() {
            return String.format(" %s - calculate sinus, parameter - in degrees", this.name());
        }

        /**
         * Action.
         */
        @Override
        public void execute() {
            try {
                result = Math.sin(Math.toRadians(takeFirstValue()));
                printResult();
                reuse = true;
            } catch (NumberFormatException nfe) {
                error();
            }
        }
    }

    /**
     * Cos.
     */

    private class Cos implements CalcAction {
        /**
         * Action name.
         * @return - name.
         */
        @Override
        public String name() {
            return "cos";
        }

        /**
         * Info.
         * @return - info about action.
         */
        @Override
        public String info() {
            return String.format(" %s - calculate cos, parameter - in degrees", this.name());
        }

        /**
         * Action.
         */
        @Override
        public void execute() {
            try {
                result = Math.cos(Math.toRadians(takeFirstValue()));
                printResult();
                reuse = true;
            } catch (NumberFormatException nfe) {
                error();
            }
        }
    }
}
