package ru.epatko.maps;

import java.util.Calendar;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         18.02.17.
 */
public class UserHE extends User {
    public UserHE(String name, int children, Calendar birthday) {
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
        return (getName().hashCode() + getChildren() + getBirthday().hashCode()) / 3 ;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param obj the reference object with which to compare.
     * @return {@code true} if this object is the same as the obj
     * argument; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        UserHE user =(UserHE) obj;
        return getName().equals(user.getName())
                && getBirthday().equals(user.getBirthday())
                && getChildren() == user.getChildren();
    }

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return String.format("Name: %s, Birthday: %tF, Children: %d", getName(), getBirthday(), getChildren());
    }
}
