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
     * Current date.
     */
    private long currentDate;

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
     * Setter.
     * @param currentDate - current date (long format).
     */
    @Override
    public void setCurrentDate(long currentDate) {
        this.currentDate = currentDate;
    }

    /**
     * Check is food appropriate this store.
     *
     * @param food - food.
     * @return - true or false.
     */
    @Override
    public boolean isAppropriate(Food food) {

        double remainingPeriod = 1d - (double) (this.currentDate - food.getCreateDate())
                / (double) ((food.getExpirationDate() - food.getCreateDate()));
        if (remainingPeriod < 0.25) {
            this.checkResult = true;
        } else {
            this.checkResult = false;
        }
        return this.checkResult;
    }

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
     * @param food - to remove.
     */
    @Override
    public void removeFood(Food food) {
        this.foods.remove(food);

    }
}
