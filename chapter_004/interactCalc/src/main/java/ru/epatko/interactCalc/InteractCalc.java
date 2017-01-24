package ru.epatko.interactCalc;


import ru.epatko.calculator.Calculator;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         24.01.17.
 */
public class InteractCalc {
    /**
     * Calculator.
     */
    private final Calculator calculator;
    /**
     * User input.
     */
    private String userInput = "";
    /**
     * Operation sign.
     */
    private String operator = "";
    /**
     * First value.
     */
    private double firstValue = 0d;
    /**
     * Second value.
     */
    private double secondValue = 0d;
    /**
     * result.
     */
    private double result = 0d;
    /**
     * Console input scanner.
     */
    private final Scanner scanner = new Scanner(System.in);
    /**
     * Sign pattern.
     */
    private final Pattern signPattern = Pattern.compile("[+\\-*/]{1}");
    /**
     * Result reuse flag.
     */
    private boolean reuse = false;

    /**
     * Constructor.
     * @param aCalc - calculator.
     */
    public InteractCalc(Calculator aCalc) {
        calculator = aCalc;
    }

    /**
     * Starts loop.
     */
    public void start() {

        /**
         * Loop
         */
        while (true) {

            userInput = scanner.nextLine();
            Scanner parser = new Scanner(userInput);
            

            if (userInput.equals("h")) {
                help();
                continue;

            } else if (userInput.equals("q")) {
                scanner.close();
                parser.close();
                break;

            } else if (userInput.equals("c")) {
                reuse = false;
                continue;

            } else if (parser.hasNext(signPattern) && reuse) {
                firstValue = result;
                operator = parser.next(signPattern);

                if (parser.hasNextDouble()) {
                    secondValue = parser.nextDouble();
                } else {
                    error();
                    continue;
                }
                compute();
                continue;

            } else if (userInput.equals("=") && !operator.equals("")) {
                firstValue = result;
                compute();
                continue;

            } else if (parser.hasNextDouble()) {
                firstValue = parser.nextDouble();
            } else {
                error();
                continue;
            }

            if (parser.hasNext(signPattern)) {

                operator = parser.next(signPattern);
            } else {
                error();
                continue;
            }

            if (parser.hasNextDouble()) {
                secondValue = parser.nextDouble();
            } else {
                error();
                continue;
            }

            compute();
            reuse = true;
        }
    }

    /**
     * Compute extension.
     */
    private void compute() {
        if (operator.equals("+")) {
            calculator.add(firstValue, secondValue);
            result = calculator.getResult();
            printResult();

        } else if (operator.equals("-")) {
            calculator.sub(firstValue, secondValue);
            result = calculator.getResult();
            printResult();

        } else if (operator.equals("*")) {
            calculator.mult(firstValue, secondValue);
            result = calculator.getResult();
            printResult();

        } else if (operator.equals("/")) {
            if (secondValue != 0d) {
                calculator.div(firstValue, secondValue);
                result = calculator.getResult();
                printResult();
            } else {
                error();
            }
        } else {
            error();
        }
    }

    /**
     * Print result.
     */
    private void printResult() {
        System.out.printf("Result: %g%s", result, System.getProperty("line.separator"));
    }

    /**
     * Print error message and reset values.
     */
    private void error() {
        System.out.println("Wrong input.");
        firstValue = 0;
        secondValue = 0;
        operator = "";
        userInput = "";
    }

    /**
     * Print help.
     */
    private void help() {
        System.out.println("Type expression, than press \"Enter\".");
        System.out.println("USE WHITESPACE to delimit signs and values.");
        System.out.println("Examples:  \"-1 + 3\" ; \"2.5 * -7\" etc.");
        System.out.println("REMEMBER! In Intellij Idea decimal separator is comma ','.");
        System.out.println("Enter 'c' to don't reuse (clear) result.");
        System.out.println("Enter 'h' to print help.");
        System.out.println("Enter 'q' to quit.");
    }

    /**
     * Main method.
     * @param args - no arguments.
     */
    public static void main(String[] args) {
        Calculator aCalc = new Calculator();
        InteractCalc interCalc = new InteractCalc(aCalc);
        interCalc.help();
        interCalc.start();
    }
}
