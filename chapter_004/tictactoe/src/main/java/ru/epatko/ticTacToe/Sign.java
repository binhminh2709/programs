package ru.epatko.ticTacToe;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         07.02.17.
 */
public class Sign {

    /**
     * Sign name.
     */
    private final String name;

    /**
     * Constructor.
     * @param name - sign name.
     */
    public Sign( String name) {
        this.name = String.format("[%s]", name);
    }
    /**
     * Get sign name.
     * @return - sign name.
     */
    public String getName() {
        return this.name;
    }

}
