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
    private String[] answers;

    /**
     * Default constructor.
     */
    public StubInput() {
        this(new String[] {"0", "Order-1", "Description-1",             // Create order #1.
                           "0", "Order-2", "Description-2",             // Create order #2.
                           "0", "Order-3", "Description-3",             // Create order #3.
                           "1", "1", "comment", "Comment-1",            // Change comment of order #1.
                           "1", "3", "description", "New description-3", // Change description of order #3.
                           "4", "r-2",                                  // Find order which name contains "r-2".
                           "7",                                         // Try input incorrect number.
                           "3",                                         // Print all orders.
                           "2", "2",                                    // Delete order #2.
                           "3",                                         // Print all orders.
                           "5"});                                        // Exit program.
    }

    /**
     * Constructor.
     * @param newAnswers - new test answers.
     */
    public StubInput(String[] newAnswers) {
        this.answers = newAnswers;
    }

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
