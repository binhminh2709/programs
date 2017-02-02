package ru.epatko.decorators;

import ru.epatko.qualityControl.Food;
import ru.epatko.qualityControl.Store;

import java.util.ArrayList;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         02.02.17.
 */
public class ExtendedStore extends StoreDecorator {

    /**
     * Maximum main foods store size (store filling simulation).
     */
    public static final int MAX_STORE_SIZE = 2;

    /**
     * Extended foods array list.
     */
    private ArrayList<Food> extendedFoods = new ArrayList<>();

    /**
     * Constructor.
     *
     * @param store - store.
     */
    public ExtendedStore(Store store) {
        super(store);
    }

    /**
     * Add food to array list.
     *
     * @param food - food.
     * @return boolean add or no.
     */
    @Override
    public boolean addFood(Food food) {
        boolean added = false;
        if (store.getFoods().size() == MAX_STORE_SIZE) {      //Store filling simulation.
            if (!this.extendedFoods.contains(food)) {
                this.extendedFoods.add(food);
                added = true;
            }
        } else {
            added = store.addFood(food);
        }
        return added;
    }

    /**
     * Get foods array list.
     * @return - foods array list.
     */
    @Override
    public ArrayList<Food> getFoods() {
        ArrayList<Food> foods = new ArrayList<>();
        foods.addAll(store.getFoods());
        foods.addAll(this.extendedFoods);
        return foods;
    }

    /**
     * Test method. Get extended foods array list.
     * @return - extended foods array list.
     */
    public ArrayList<Food> getExtendedFoods() {
        return this.extendedFoods;
    }
}
