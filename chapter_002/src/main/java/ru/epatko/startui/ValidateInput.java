package ru.epatko.startui;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         01.12.16.
 */
public class ValidateInput extends ConsoleInput {

    /**
     *
     * @param question question.
     * @param validKeys array of valid keys.
     * @return int key.
     */
    public int ask(String question, int[] validKeys) {
        boolean invalid = true;
        int key = -1;
        do {
            try {
                key = super.ask(question, validKeys);
                invalid = false;
            } catch (MenuOutException moe) {
                System.out.println("Please, enter number from menu.");
            } catch (NumberFormatException nfe) {
                System.out.println("Incorrect input. Try again.");
            }
        } while (invalid);
        return key;
    }
}
