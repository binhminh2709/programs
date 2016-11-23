package ru.epatko.startui;
/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 * 23.11.16.
 */

/**
 * User input imitator.
 */
public class StubInput implements Input {
    /**
     * Array of user answers.
     */
    private String[] answers = {"1", "Order-1", "Description-1", // Create order #1.
                                "1", "Order-2", "Description-2", // Create order #2.
                                "2", "1", "comment", "Comment-1", // Change comment of order #1.
                                "2", "2", "description", "New description-2", // Change description of order #2.
                                "5", "r-2", // Find order which name contains "r-2".
                                "7", // Try input incorrect number.
                                "4", // Print all orders.
                                "3", "1", // Delete order #1.
                                "4", // Print all orders.
                                "6"}; // Exit program.
    /**
     * Index of array elements.
     */
    private int index;

    /**
     *
     * @param question - question.
     * @return answer - user answer.
     */
    public String ask(String question) {
        System.out.println(question);
        return this.answers[this.index++];
    }
}
