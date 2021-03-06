package ru.epatko.qualityControl;

import java.util.*;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         29.01.17.
 */
public class QualityControl {

    /**
     * Current date.
     */
    private long currentDate;
    /**
     * Stores array list.
     */
    private ArrayList<Store> stores = new ArrayList<>();

    /**
     * Get stores array list.
     * @return - stores array list.
     */
    public ArrayList<Store> getStores() {
        return this.stores;
    }

    /**
     * Setter.
     * @param currentDate - current date (long format).
     */
    public void setCurrentDate(long currentDate) {
        this.currentDate = currentDate;
    }

    /**
     * Add store to stores array list.
     * @param store - store.
     */
    public void addStore(Store store) {
        this.stores.add(store);
    }

    /**
     * Fill stores array list.
     */
    public void fillStores() {
        addStore(new WareHouse());
        addStore(new Shop());
        addStore(new Trash());
    }

    /**
     * Put food to the store.
     * @param food - food.
     * @return - boolean, moved food or no.
     */
    public boolean putFood(Food food) {
        boolean moved = false;
        for (int i = 0; i < this.stores.size(); i++) {
            if (this.stores.get(i).isAppropriate(food, this.currentDate)) {
                moved = true;
                break;
            }
        }
        return moved;
    }

    /**
     * Check food quality.
     */
    public void checkQuality() {
        for (int i = 0; i < this.stores.size(); i++) {
            ArrayList<Food> foods = stores.get(i).getFoods();
            for (int j = 0; j < foods.size(); j++) {
                Food food = foods.get(j);
                if (putFood(food)) {
                    stores.get(i).removeFood(food);
                }
            }
        }
    }
}
