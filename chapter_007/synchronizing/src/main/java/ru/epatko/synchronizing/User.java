package ru.epatko.synchronizing;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         20.03.17.
 */
public class User {
    /**
     * User ID.
     */
    private final int id;
    /**
     * User name.
     */
    private String name;
    /**
     * User amount.
     */
    private double amount = 0f;

    /**
     * Constructor.
     * @param id - user ID
     * @param name user name
     */
    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Setter.
     * @param name new user name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter.
     * @param amount new amount
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Getter.
     * @return user ID.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Getter.
     * @return user name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter.
     * @return amount
     */
    public double getAmount() {
        return this.amount;
    }

    @Override
    public String toString() {
        return String.format("User name: %s, ID: %d, amount: %.2f", this.name, this.id, this.amount);
    }
}
