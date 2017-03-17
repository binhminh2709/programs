package ru.epatko.clientCounter;

import java.time.LocalTime;
import java.util.*;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         17.03.17.
 */
public class ClientCounter {
    private final LocalTime openTime = LocalTime.of(9, 0);
    private final LocalTime closedTime = LocalTime.of(18, 0);
    private final int pointsInHour = 2;
    private final int pointsNumber = (closedTime.getHour() - openTime.getHour()) * pointsInHour;
    private final int period = 60 / pointsInHour;

    private TreeMap<User, Integer> result = new TreeMap<>(new Comparator<User>() {
        @Override
        public int compare(User o1, User o2) {
            return o1.getStartTime().compareTo(o2.getStartTime());
        }
    });

    public TreeMap<User, Integer> getResult() {
        return this.result;
    }

    public void prepareCounter() {
        LocalTime userTime = openTime;

        for (int i = 0; i < pointsNumber; i++) {
            result.put(new User(userTime, userTime.plusMinutes(period)), 0);
            userTime = userTime.plusMinutes(period);
        }
    }

    public void addUser(User user) {

        for (Map.Entry<User, Integer> entry : result.entrySet()) {
            if (user.check(entry.getKey())) {
                result.put(entry.getKey(), result.get(entry.getKey()) + 1);
            }
        }
    }

    public void showResult() {
        for (Map.Entry<User, Integer> entry : result.entrySet()) {
            System.out.printf("%s Users: %d%s", entry.getKey(), entry.getValue(), System.getProperty("line.separator"));
        }
    }
}
