package ru.epatko.clientCounter;

import java.time.LocalTime;
import java.util.*;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         17.03.17.
 */
public class ClientCounter {
    /**
     * Bank opening time.
     */
    private final LocalTime openTime = LocalTime.of(9, 0);
    /**
     * Bank closing time.
     */
    private final LocalTime closedTime = LocalTime.of(18, 0);
    /**
     * Number of control time periods in hour.
     */
    private final int pointsInHour = 2;
    /**
     * Number of control periods during work time.
     */
    private final int pointsNumber = (closedTime.getHour() - openTime.getHour()) * pointsInHour;
    /**
     * Duration of the control period.
     */
    private final int period = 60 / pointsInHour;

    /**
     * Clients counter.
     */
    private TreeMap<User, Integer> result = new TreeMap<>(new Comparator<User>() {
        @Override
        public int compare(User o1, User o2) {
            return o1.getStartTime().compareTo(o2.getStartTime());
        }
    });

    /**
     * Getter.
     * @return - clients counter
     */
    public TreeMap<User, Integer> getResult() {
        return this.result;
    }

    /**
     * Preparing the counter. Filling it with control periods.
     */
    public void prepareCounter() {
        LocalTime userTime = openTime;

        for (int i = 0; i < pointsNumber; i++) {
            result.put(new User(userTime, userTime.plusMinutes(period)), 0);
            userTime = userTime.plusMinutes(period);
        }
    }

    /**
     * Adding new user into counter.
     * @param user
     */
    public void addUser(User user) {

        for (Map.Entry<User, Integer> entry : result.entrySet()) {
            if (user.check(entry.getKey())) {
                result.put(entry.getKey(), result.get(entry.getKey()) + 1);
            }
        }
    }

    /**
     * Printing result.
     */
    public void showResult() {
        for (Map.Entry<User, Integer> entry : result.entrySet()) {
            System.out.printf("%s Users: %d%s", entry.getKey(), entry.getValue(), System.getProperty("line.separator"));
        }
    }
}
