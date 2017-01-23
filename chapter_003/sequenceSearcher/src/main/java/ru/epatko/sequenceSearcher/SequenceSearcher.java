package ru.epatko.sequenceSearcher;
/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         23.01.17.
 */
public class SequenceSearcher {

    /**
     * @param array - source array;
     * @return - result array: result[0] - digit of maximum sequence; result[1] - length of maximal sequence.
     */
    public int[] search(int[] array) {
        /**
         * Temp counter.
         */
        int counter = 1;
        /**
         * Maximal sequence.
         */
        int maxSequence = 0;
        /**
         * Digit of maximum sequence.
         */
        int digit = 0;
        for (int i = 0; i < (array.length - 1); i++) {
            if (array[i] == array[i + 1]) {
                counter++;
                if (counter > maxSequence) {
                    maxSequence = counter;
                    digit = array[i];
                }
            } else {
                counter = 1;
            }
        }
        int[] result = {digit, maxSequence};
        return result;
    }
}
