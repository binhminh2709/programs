package ru.epatko.decorators;

import ru.epatko.qualityControl.Food;
import ru.epatko.qualityControl.Store;

import java.util.ArrayList;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         02.02.17.
 */
public class RecyclingStorage extends StoreDecorator {
    /**
     * Constructor.
     * @param store - store.
     */
    public RecyclingStorage(Store store) {
        super(store);
    }

    /**
     * Add food to array list.
     * @param food - Recyclable food.
     * @return boolean add or no.
     */
    @Override
    public boolean addFood(RecyclableFood food) {
        boolean added = false;
        if (food.isRecyclable()) {
            store..add(food);
            added = true;
        }
        return added;
    }

    /**
     * Add food to array list.
     *
     * @param food - food.
     * @return boolean add or no.
     */
    @Override
    public boolean addFood(Food food) {
        return false;
    }

    /**
     * Get foods array list.
     * @return - foods array list.
     */
    @Override
    public ArrayList<Food> getFoods() {
        return store.getFoods();
    }
}
