package ru.epatko.chat;


/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         25.12.16.
 */
public interface Input {

    /**
     *
     * @param message - output message.
     * @return - input phrase.
     */
    String say(String message);
}
