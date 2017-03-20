package ru.epatko.clientCounter;

import java.time.LocalTime;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         17.03.17.
 */
public class User {
    /**
     * When client enter to the bank.
     */
    private final LocalTime startTime;
    /**
     * When client get out of the bank.
     */
    private LocalTime endTime;

    /**
     * Constructor.
     * @param startTime When client enter to the bank.
     * @param endTime When client get out of the bank.
     */
    public User(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Getter.
     * @return Time When client enter to the bank.
     */
    public LocalTime getStartTime() {
        return this.startTime;
    }

    /**
     * Getter.
     * @return Time When client get out of the bank.
     */
    public LocalTime getEndTime() {
        return this.endTime;
    }


    @Override
    public String toString() {
        String result = String.format("Time: %s - %s", startTime.toString(), endTime.toString());
        return result;
    }

    /**
     * Check if user staying in bank during control period.
     * @param u  control time period.
     * @return  boolean true or false.
     */
    public boolean check(User u) {
        boolean result = false;
        if ((startTime.isBefore(u.getStartTime()) || startTime.equals(u.getStartTime())) && u.startTime.isBefore(endTime)
             || (u.startTime.isBefore(startTime) && startTime.isBefore(u.endTime))) {
            result = true;
        }
        return result;
    }
}
