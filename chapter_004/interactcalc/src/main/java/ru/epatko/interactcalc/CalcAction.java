package ru.epatko.interactcalc;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         25.01.17.
 */
public interface CalcAction {
    /**
     * Action name.
     * @return - name.
     */
    String name();

    /**
     * Info.
     * @return - info about action.
     */
    String info();

    /**
     * Action.
     */
    void execute();
}
