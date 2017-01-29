package ru.epatko.qualityControl;

import java.util.ArrayList;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         29.01.17.
 */
public class WareHouse implements Store {

    /**
     * Foods array list.
     */
    private ArrayList<Food> foods = new ArrayList<>();

    /**
     * Add food to array list.
     * @param food - food.
     */
    @Override
    public void addFood(Food food) {
        this.foods.add(food);
    }

    /**
     * Get foods array list.
     * @return - foods array list.
     */
    @Override
    public ArrayList<Food> getFoods() {
        return this.foods;
    }

    /**
     * Remove food from foods array list.
     * @param food - food to remove.
     */
    @Override
    public void removeFood(Food food) {
        this.foods.remove(food);
    }
}
