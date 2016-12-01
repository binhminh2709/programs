package ru.epatko.startui;

/**
 * Input interface.
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 */
public interface Input {
    /**
     *
      * @param question - question.
     * @return - String.
     */
    String ask(String question);

    /**
     *
     * @param question - question.
     * @param validKeys - array of valid keys.
     * @return int key.
     */
    int ask(String question, int[] validKeys);
}
