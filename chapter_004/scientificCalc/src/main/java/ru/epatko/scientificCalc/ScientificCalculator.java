package ru.epatko.scientificCalc;

import ru.epatko.interactcalc.*;
/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         28.01.17.
 */
public class ScientificCalculator extends Calculator {

    /**
     * Sinus operation.
     * @param a - double value.
     */
    public void sin(double a) {
        result = Math.sin(Math.toRadians(a));
    }

    /**
     * Cos operation.
     * @param a - double value.
     */
    public void cos(double a) {
        result = Math.cos(Math.toRadians(a));
    }
}
