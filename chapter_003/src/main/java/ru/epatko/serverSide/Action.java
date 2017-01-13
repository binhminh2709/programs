package ru.epatko.serverSide;


import java.io.IOException;

/**
 * Server action interface.
 *
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         27.11.16.
 */
public interface Action {
    /**
     * Name of action.
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
     * @param parameter - parameter.
     */
    void execute(String parameter) throws IOException;
}
