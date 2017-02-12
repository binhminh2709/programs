package ru.epatko.simpleArray;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         12.02.17.
 */
public abstract class Base {
    /**
     * ID.
     */
    private String id;
    /**
     * Get ID.
     *
     * @return {@code String} ID.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Set ID.
     *
     * @param id ID.
     */
    public void setId(String id) {
        this.id = id;
    }
}
