package ru.epatko.simpleArray;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         12.02.17.
 */
public class Role extends Base {

    /**
     * ID.
     */
    private String id;

    /**
     * Constructor.
     *
     * @param id ID.
     */
    public Role(String id) {
        this.id = id;
    }

    /**
     * Get ID.
     *
     * @return {@code String} ID.
     */
    @Override
    public String getId() {
        return this.id;
    }

    /**
     * Set ID.
     *
     * @param id ID.
     */
    @Override
    public void setId(String id) {
        this.id = id;
    }
}
