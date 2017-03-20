package ru.epatko.synchronizing;

import java.util.HashMap;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         13.03.17.
 */
public class UserStorage {

    /**
     * User storage.
     */
    private HashMap<Integer, User> storage = new HashMap<>();

    /**
     * Add user to storage.
     * @param user User object
     */
    public void add(User user) {
        synchronized (this) {
            if (!storage.containsKey(user.getId()) && !storage.containsValue(user)) {
                storage.put(user.getId(), user);
            } else {
                System.out.println("Incorrect ID.");
            }
        }
    }

    /**
     * Change user name.
     * @param userID user ID
     * @param newName new user name
     */
    public void changeName(int userID, String newName) {
        synchronized (this) {
            if (storage.containsKey(userID)) {
                storage.get(userID).setName(newName);
            } else {
                System.out.println("Incorrect ID.");
            }
        }
    }

    /**
     * Change user ammount.
     * @param userID user ID
     * @param operand operand
     * @return true or false
     */
    public boolean changeAmount(int userID, Double operand) {
        boolean result = false;
        synchronized (this) {
            if (storage.containsKey(userID)) {
                double newAmount = storage.get(userID).getAmount() + operand;
                if (newAmount >= 0f) {
                    storage.get(userID).setAmount(newAmount);
                    result = true;
                } else {
                    System.out.println("Amount isn't enough.");
                }
            } else {
                System.out.println("Incorrect ID.");
            }
        }
        return result;
    }

    /**
     * Read user info.
     * @param userID  user ID
     * @return user info or wrong message
     */
    public String read(int userID) {
        String result = "There are no user with this ID.";
        synchronized (this) {
            if (storage.containsKey(userID)) {
                result = storage.get(userID).toString();
            }
        }
        return result;
    }

    /**
     * Delete user from storage.
     * @param userID user ID
     */
    public void delete(int userID) {
        synchronized (this) {
            if (storage.containsKey(userID)) {
                storage.remove(userID);
            }  else {
                System.out.println("Incorrect ID.");
            }
        }
    }

    /**
     * Transfer amount.
     * @param sourceUserID source user ID
     * @param destinationUserID destination user ID
     * @param sum sum to transfer
     * @return return true or false
     */
    public boolean transfer(int sourceUserID, int destinationUserID, double sum) {
        boolean result = false;
        synchronized (this) {
            if (storage.containsKey(sourceUserID) && storage.containsKey(destinationUserID)
                && changeAmount(sourceUserID, -sum)) {
                changeAmount(destinationUserID, sum);
                result = true;
            }
        }
        return result;
    }
}
