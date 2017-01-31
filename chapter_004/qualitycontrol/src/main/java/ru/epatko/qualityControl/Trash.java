package ru.epatko.qualityControl;

import java.util.ArrayList;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         29.01.17.
 */
public class Trash implements Store {

    /**
     * Store name.
     */
    private String name = "Trash";

    /**
     * Foods array list.
     */
    private ArrayList<Food> foods = new ArrayList<>();

    /**
     * Result of food appropriateness checking.
     */
    private boolean checkResult;

    /**
     * Get store name.
     *
     * @return - store name.
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Check is food appropriate this store.
     *
     * @param food - food.
     * @return - true or false.
     */
    @Override
    public boolean isAppropriate(Food food, long currentDate) {

        double remainingPeriod = 1d - (double) (currentDate - food.getCreateDate())
                                        / (double) ((food.getExpirationDate() - food.getCreateDate()));
        if (remainingPeriod <= 0d) {
            if (addFood(food)) {
                this.checkResult = true;
            } else {
                this.checkResult = false;
            }
        } else {
            this.checkResult = false;
        }
        return this.checkResult;
    }

    /**
     * Add food to array list.
     * @param food - food.
     * @return boolean add or no.
     */
    @Override
    public boolean addFood(Food food) {
        boolean added = false;
        if (!this.foods.contains(food)) {
            this.foods.add(food);
            added = true;
        }
        return added;
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
     * @param food - to remove.
     */
    @Override
    public void removeFood(Food food) {
        this.foods.remove(food);

    }
}
