package ru.epatko.interactcalc;

import java.util.*;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         25.01.17.
 */
public class CalcMenu {

    /**
     * Input method.
     */
    private final Input input;
    /**
     * Calculator.
     */
    private final Calculator calculator;

    /**
     * Second value.
     */
    private double secondValue = 0d;
    /**
     * result.
     */
    private double result = 0d;
    /**
     * Operation sign.
     */
    private String operator = "";
    /**
     * Result reuse flag.
     */
    private boolean reuse = false;
    /**
     * Repeat last operation flag.
     */
    private boolean repeat = false;
    /**
     * Actions HashMap.
     */
    private Map<String, CalcAction> calcActions = new HashMap<>();

    /**
     * Constructor.
     * @param input - input method.
     * @param calc - calculator.
     */
    public CalcMenu(Input input, Calculator calc) {
        this.input = input;
        this.calculator = calc;
    }

    /**
     * To fill calculator menu.
     */
    public void fillMenu() {
        this.calcActions.put("+", new Addition());
        this.calcActions.put("-", new Substraction());
        this.calcActions.put("*", new Multiplication());
        this.calcActions.put("/", new Division());
        this.calcActions.put("=", new Repeat());
        this.calcActions.put("c", new Reset());
        this.calcActions.put("h", new Help());
        this.calcActions.put("q", new Quit());
    }

    /**
     * Search the command and execute action.
     * @param message - user input.
     */
    public void compute(String message) {
        if (this.calcActions.containsKey(message)) {
            this.calcActions.get(message).execute();
            this.operator = message;
        } else {
            error();
        }
    }

    /**
     * Ask first value input.
     * @return - double value.
     */
    private double takeFirstValue() {

        double firstValue = 0d;

        if (this.reuse) {
            firstValue = this.result;
        } else {
            firstValue = Double.parseDouble(this.input.ask("Enter first value: "));
        }
        return firstValue;
    }

    /**
     * Ask second value input.
     * @return - double value.
     */
    private double takeSecondValue() {

        if (!this.repeat) {
            this.secondValue = Double.parseDouble(this.input.ask("Enter second value: "));
        }
        return this.secondValue;
    }

    /**
     * Print result.
     */
    private void printResult() {
        System.out.printf("Result: %g%s", this.result, System.getProperty("line.separator"));
    }

    /**
     * Print error message and reset values.
     */
    private void error() {
        System.out.println("Incorrect input.");
        compute("c");
    }

    //********************************************* Addition *******************************************************/

    /**
     * Addition action.
     */
    private class Addition implements CalcAction {
        /**
         * Action name.
         * @return - name.
         */
        @Override
        public String name() {
            return "+";
        }

        /**
         * Info.
         * @return - info about action.
         */
        @Override
        public String info() {
            return String.format(" %s - addition operation", this.name());
        }

        /**
         * Action.
         */
        @Override
        public void execute() {
            try {
                calculator.add(takeFirstValue(), takeSecondValue());
                result = calculator.getResult();
                printResult();
                reuse = true;
            } catch (NumberFormatException nfe) {
                error();
            }
        }
    }

    //********************************************** Subtraction ******************************************************/

    /**
     * Substraction action.
     */
    private class Substraction implements CalcAction {
        /**
         * Action name.
         * @return - name.
         */
        @Override
        public String name() {
            return "-";
        }

        /**
         * Info.
         * @return - info about action.
         */
        @Override
        public String info() {
            return String.format(" %s - subtraction operation", this.name());
        }

        /**
         * Action.
         */
        @Override
        public void execute() {
            try {
                calculator.sub(takeFirstValue(), takeSecondValue());
                result = calculator.getResult();
                printResult();
                reuse = true;
            } catch (NumberFormatException nfe) {
                error();
            }
        }
    }

    //********************************************* Multiplication ******************************************************/

    /**
     * Multiplication action.
     */
    private class Multiplication implements CalcAction {
        /**
         * Action name.
         * @return - name.
         */
        @Override
        public String name() {
            return "*";
        }

        /**
         * Info.
         * @return - info about action.
         */
        @Override
        public String info() {
            return String.format(" %s - multiplication operation", this.name());
        }

        /**
         * Action.
         */
        @Override
        public void execute() {
            try {
                calculator.mult(takeFirstValue(), takeSecondValue());
                result = calculator.getResult();
                printResult();
                reuse = true;
            } catch (NumberFormatException nfe) {
                error();
            }

        }
    }


    //******************************************** Division ********************************************************/

    /**
     * Division action.
     */
    private class Division implements CalcAction {
        /**
         * Action name.
         * @return - name.
         */
        @Override
        public String name() {
            return "/";
        }

        /**
         * Info.
         * @return - info about action.
         */
        @Override
        public String info() {
            return String.format(" %s - division operation", this.name());
        }

        /**
         * Action.
         */
        @Override
        public void execute() {

            try {
                double first = takeFirstValue();
                double second = takeSecondValue();

                if (second != 0d) {
                    calculator.div(first, second);
                    result = calculator.getResult();
                    printResult();
                    reuse = true;
                } else {
                    error();
                }

            } catch (NumberFormatException nfe) {
                error();
            }
        }
    }

    //******************************************* Repeat *********************************************************/

    /**
     * Repeat action.
     */
    private class Repeat implements CalcAction {
        /**
         * Action name.
         * @return - name.
         */
        @Override
        public String name() {
            return "=";
        }

        /**
         * Info.
         * @return - info about action.
         */
        @Override
        public String info() {
            return String.format(" %s - repeat previous operation", this.name());
        }

        /**
         * Action.
         */
        @Override
        public void execute() {
            if (reuse) {
                repeat = true;
                compute(operator);
                repeat = false;
            } else {
                error();
            }
        }
    }

    //*********************************************** Reset *****************************************************/

    /**
     * Reset action.
     */
    private class Reset implements CalcAction {
        /**
         * Action name.
         * @return - name.
         */
        @Override
        public String name() {
            return "c";
        }

        /**
         * Info.
         * @return - info about action.
         */
        @Override
        public String info() {
            return String.format(" %s - reset result", this.name());
        }

        /**
         * Action.
         */
        @Override
        public void execute() {
            operator = "";
            secondValue = 0d;
            result = 0d;
            reuse = false;
            repeat = false;
        }
    }

    //*********************************************** Help *****************************************************/

    /**
     * Help action.
     */
    private class Help implements CalcAction {
        /**
         * Action name.
         * @return - name.
         */
        @Override
        public String name() {
            return "h";
        }

        /**
         * Info.
         * @return - info about action.
         */
        @Override
        public String info() {
            return String.format(" %s - print help", this.name());
        }

        /**
         * Action.
         */
        @Override
        public void execute() {

            System.out.println(" ---------------------------------------------------------");
            System.out.println("|                   Calculator commands:                  |");
            System.out.println(" ---------------------------------------------------------");
            calcActions.forEach((k, v) -> System.out.println(v.info()));
            System.out.println(" ---------------------------------------------------------");
            compute("c");
        }
    }
    //********************************************** Quit ******************************************************/

    /**
     * Quit action.
     */
    private class Quit implements CalcAction {
        /**
         * Action name.
         * @return - name.
         */
        @Override
        public String name() {
            return "q";
        }

        /**
         * Info.
         * @return - info about action.
         */
        @Override
        public String info() {
            return String.format(" %s - quit", this.name());
        }
        /**
         * Action.
         */
        @Override
        public void execute() {
            System.exit(0);
        }
    }
}
