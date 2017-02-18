package ru.epatko.maps;

import java.util.Calendar;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         18.02.17.
 */
public class UserE extends User {

    public UserE(String name, int children, Calendar birthday) {
        super(name, children, birthday);
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
        UserE user =(UserE) obj;
        return getName().equals(user.getName())
                && getBirthday().equals(user.getBirthday())
                && getChildren() == user.getChildren();
    }
}
