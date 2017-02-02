package ru.epatko.decorators;

import ru.epatko.qualityControl.*;
import java.util.ArrayList;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         02.02.17.
 */
public abstract class StoreDecorator implements Store {

    /**
     * Store.
     */
    protected Store store;

    /**
     * Constructor.
     * @param store - store.
     */
    public StoreDecorator(Store store) {
        this.store = store;
    }

    /**
     * Get store name.
     * @return - store name.
     */
    @Override
    public String getName() {
        return this.store.getName();
    }

    /**
     * Add food to array list.
     * @param food - food.
     * @return boolean add or no.
     */
    public abstract boolean addFood(RecyclableFood food);

    /**
     * Get foods array list.
     * @return - foods array list.
     */
    @Override
    public abstract ArrayList<Food> getFoods();

    /**
     * Remove food from foods array list.
     * @param food - food to remove.
     */
    @Override
    public void removeFood(Food food) {
        this.store.removeFood(food);
    }

    /**
     * Check is food appropriate store.
     * @param food        - food.
     * @param currentDate - current date.
     * @return - true or false.
     */
    @Override
    public boolean isAppropriate(Food food, long currentDate) {
        return this.store.isAppropriate(food, currentDate);
    }
}
