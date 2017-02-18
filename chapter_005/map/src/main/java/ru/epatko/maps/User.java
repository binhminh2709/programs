package ru.epatko.maps;

import java.util.Calendar;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         17.02.17.
 */
public class User {

    private String name;
    private int children;
    private final Calendar birthday;

    public User(String name, int children, final Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }
}
