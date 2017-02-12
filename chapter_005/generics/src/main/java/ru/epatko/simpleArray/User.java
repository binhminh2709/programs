package ru.epatko.simpleArray;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         12.02.17.
 */
public class User extends Base {

    /**
     * ID.
     */
    private String id;

    /**
     * Constructor.
     *
     * @param id ID.
     */
    public User(String id) {
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
