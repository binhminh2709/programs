package ru.epatko.qualityControl;

import java.util.*;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         29.01.17.
 */
public class QualityControl {

    /**
     * Ware house array index.
     */
    private final int wareHouse = 0;
    /**
     * shop array index.
     */
    private final int shop = 1;
    /**
     * trash array index.
     */
    private final int trash = 2;

    /**
     * Stores array list.
     */
    private ArrayList<Store> stores = new ArrayList<>();

    /**
     * Current date.
     */
    private long currentDate;

    /**
     * Quality control.
     * @param currentDate - current date (long format).
     */
    public QualityControl(long currentDate) {
        this.currentDate = currentDate;
    }

    /**
     * Constructor.
     * @param currentDate - current date (long format).
     */
    public void setCurrentDate(long currentDate) {
        this.currentDate = currentDate;
    }

    /**
     * Get array list stores.
     * @return - array list stores.
     */
    public ArrayList<Store> getStores() {
        return this.stores;
    }

    /**
     * Fill stores array list.
     */
    public void fillStores() {
        this.stores.add(new WareHouse());
        this.stores.add(new Shop());
        this.stores.add(new Trash());
    }

    /**
     * Put food to the store.
     * @param food - food.
     */
    public void putFood(Food food) {
        double remainingPeriod = 1d - (double) (this.currentDate - food.getCreateDate())
                                       / (double) ((food.getExpirationDate() - food.getCreateDate()));
        if (remainingPeriod < 0.25) {
            this.stores.get(trash).addFood(food);
        } else if (remainingPeriod <= 0.75 && remainingPeriod >= 0.25) {
            this.stores.get(shop).addFood(food);
        } else {
            this.stores.get(wareHouse).addFood(food);
        }
    }

    /**
     * Check food quality.
     */
    public void checkQuality() {
        for (int i = 0; i < this.stores.size(); i++) {

            ArrayList<Food> foods = stores.get(i).getFoods();
            for (int j = 0; j < foods.size(); j++) {
                Food food = foods.get(j);
                double remainingPeriod = 1d - (double) (this.currentDate - food.getCreateDate())
                                                / (double) ((food.getExpirationDate() - food.getCreateDate()));
                if (remainingPeriod > 0.75) {
                    this.stores.get(wareHouse).addFood(food);
                    stores.get(i).removeFood(food);
                } else if (remainingPeriod <= 0.75 && remainingPeriod >= 0.25) {
                    this.stores.get(shop).addFood(food);
                    stores.get(i).removeFood(food);
                } else if (remainingPeriod < 0.25) {
                    this.stores.get(trash).addFood(food);
                    stores.get(i).removeFood(food);
                }
            }
        }
    }
}
