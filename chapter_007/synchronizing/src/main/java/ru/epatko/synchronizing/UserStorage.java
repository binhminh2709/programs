package ru.epatko.synchronizing;

import java.util.HashMap;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         13.03.17.
 */
public class UserStorage {

    private HashMap<Integer, User> storage = new HashMap<>();

    private final Object lock = new Object();

    public void add(int userID, User user) {
        synchronized(this.lock) {
            if (!storage.containsKey(userID) && !storage.containsValue(user)) {
                storage.put(userID, user);
            } else {
                System.out.println("Incorrect ID.");
            }
        }
    }

    public void changeName(int userID, String newName) {
        synchronized(this.lock) {
            if (storage.containsKey(userID)) {
                storage.get(userID).setName(newName);
            } else {
                System.out.println("Incorrect ID.");
            }
        }
    }

    public void changeAmount(int userID, Double operand) {
        synchronized(this.lock) {
            if (storage.containsKey(userID)) {
                double newAmount = storage.get(userID).getAmount() + operand;
                if (newAmount >= 0f) {
                    storage.get(userID).setAmmount(newAmount);
                } else {
                    System.out.println("Amount isn't enough.");
                }
            } else {
                System.out.println("Incorrect ID.");
            }
        }
    }






    public void read(int userID) {

    }

    public void delete(int userID) {

    }
}
