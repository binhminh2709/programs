package ru.epatko.startui;

import ru.epatko.tracker.Tracker;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         27.11.16.
 */
public interface UserAction {
    /**
     * Number of action.
     * @return - number.
     */
    int key();

    /**
     * Action.
     * @param input - input method.
     * @param tracker - tracker.
     */
    void execute(Input input, Tracker tracker);

    /**
     * Info.
      * @return - info about action.
     */
    String info();
}
