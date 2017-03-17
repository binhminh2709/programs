package ru.epatko.clientCounter;

import java.time.LocalTime;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         17.03.17.
 */
public class User {
    private final LocalTime startTime;
    private LocalTime endTime;

    public User(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalTime getStartTime() {
        return this.startTime;
    }

    public LocalTime getEndTime() {
        return this.endTime;
    }


    @Override
    public String toString() {
        String result = String.format("Time: %s - %s", startTime.toString(), endTime.toString());
        return result;
    }

    public boolean check(User u) {
        boolean result = false;
        if ((startTime.isBefore(u.getStartTime()) || startTime.equals(u.getStartTime())) && u.startTime.isBefore(endTime)
             || (u.startTime.isBefore(startTime) && startTime.isBefore(u.endTime))) {
            result = true;
        }
        return result;
    }
}
