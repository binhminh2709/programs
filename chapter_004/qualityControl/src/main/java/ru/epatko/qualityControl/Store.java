package ru.epatko.qualityControl;

import java.util.ArrayList;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         29.01.17.
 */
public interface Store {

    /**
     * Add food to array list.
     * @param food - food.
     */
    void addFood(Food food);

    /**
     * Get foods array list.
     * @return - foods array list.
     */
    ArrayList<Food> getFoods();

    /**
     * Remove food from foods array list.
     * @param food - food to remove.
     */
    void removeFood(Food food);
}
