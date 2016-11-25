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
     * Index of array elements.
     */
    private int index;
    /**
     * Array of user answers.
     */
    private String[] answers = {"1", "Order-1", "Description-1",    // Create order #1.
            "1", "Order-2", "Description-2",                        // Create order #2.
            "1", "Order-3", "Description-3",                        // Create order #3.
            "2", "1", "comment", "Comment-1",                       // Change comment of order #1.
            "2", "3", "description", "New description-3",           // Change description of order #3.
            "5", "r-2",                                             // Find order which name contains "r-2".
            "7",                                                    // Try input incorrect number.
            "4",                                                    // Print all orders.
            "3", "2",                                               // Delete order #2.
            "4",                                                    // Print all orders.
            "6"};                                                   // Exit program.

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
