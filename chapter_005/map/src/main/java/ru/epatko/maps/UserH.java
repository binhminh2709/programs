package ru.epatko.maps;

import java.util.Calendar;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         18.02.17.
 */
public class UserH extends User {


    public UserH(String name, int children, Calendar birthday) {
        super(name, children, birthday);
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object.
     * @see Object#equals(Object)
     * @see System#identityHashCode
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = result * 37 + getName().hashCode();
        result = result * 37 + getChildren();
        result = result * 37 + getBirthday().hashCode();
        return result;
    }
}
