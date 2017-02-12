package ru.epatko.simpleArray;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         13.02.17.
 */
public class BasicStore implements Store<Base> {

    /**
     * Storage.
     */
    private SimpleArray<Base> storage;

    /**
     * Constructor.
     *
     * @param storage storage.
     */
    public BasicStore(SimpleArray<Base> storage) {
        this.storage = storage;
    }

    /**
     * Constructor.
     *
     * @param size storage size.
     */
    public BasicStore(int size) {
        this.storage = new SimpleArray<Base>(size);
    }

    /**
     * Add element.
     *
     * @param element new element to adding.
     */
    @Override
    public void add(Base element) {
        this.storage.add(element);
    }

    /**
     * Delete element.
     *
     * @param id ID of element to deleting.
     */
    @Override
    public void delete(String id) {
        Base temp;
        int i = 0;
        try {
            while ((temp = this.storage.get(i)) != null) {
                if (id.equals(temp.getId())) {
                    this.storage.delete(i);
                    break;
                }
                i++;
            }
        } catch (ArrayIndexOutOfBoundsException ibe) {
            ibe.getMessage();
        }
    }

    /**
     * Update element.
     *
     * @param id ID element to update
     * @param newElement new element
     */
    @Override
    public void update(String id, Base newElement) {
        Base temp;
        int i = 0;
        try {
            while ((temp = this.storage.get(i)) != null) {
                if (id.equals(temp.getId())) {
                    this.storage.update(i, newElement);
                    break;
                }
                i++;
            }
        } catch (ArrayIndexOutOfBoundsException ibe) {
            ibe.getMessage();
        }
    }

    /**
     * Get element from storage.
     *
     * @param id - ID of element
     * @return type T element
     */
    @Override
    public Base get(String id) {
        Base result = null;
        Base temp;
        int i = 0;
        try {
            while ((temp = this.storage.get(i)) != null) {
                if (id.equals(temp.getId())) {
                    result = temp;
                    break;
                }
                i++;
            }
        } catch (ArrayIndexOutOfBoundsException ibe) {
            ibe.getMessage();
        }
        return result;
    }
}
