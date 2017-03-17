package ru.epatko.synchronizing;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         13.03.17.
 */
public class User {
    private final int id;
    private String name;
    private double amount = 0f;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAmmount(double ammount) {
        this.amount = amount;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public double getAmount() {
        return this.amount;
    }
}
