package ru.epatko.simpleArray;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         12.02.17.
 */
public class UserStore extends BasicStore {

    /**
     * Constructor.
     *
     * @param storage storage.
     */
    public UserStore(SimpleArray<Base> storage) {
        super(storage);
    }

    /**
     * Constructor.
     *
     * @param size storage size.
     */
    public UserStore(int size) {
        super(size);
    }
}
