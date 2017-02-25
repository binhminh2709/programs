package ru.epatko.orderProcessor;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         25.02.17.
 */
public class Order {

    String type;
    float price;
    int volume;
    int id;

    public Order(String type, float price, int volume, int id) {
        this.type = type;
        this.price = price;
        this.volume = volume;
        this.id = id;
    }
}
