package ru.epatko.newQualityControl;

import ru.epatko.qualityControl.*;

import java.util.ArrayList;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         03.02.17.
 */
public class RecycleWareHouse extends WareHouse {

    /**
     * Recycle foods array list.
     */
    protected ArrayList<RecycleFood> foods = new ArrayList<>();

    /**
     * Get store name.
     *
     * @return - store name.
     */
    @Override
    public String getName() {
        return "Recycle Ware House";
    }

    /**
     * Check is food appropriate this store.
     * @param currentDate - current date.
     * @param food - food.
     * @return - true or false.
     */
    public boolean isAppropriate(RecycleFood food, long currentDate) {

        double remainingPeriod = 1d - (double) (currentDate - food.getCreateDate())
                / (double) ((food.getExpirationDate() - food.getCreateDate()));

        boolean checkResult;

        if (remainingPeriod > 0.75 && addFood(food)) {
            checkResult = true;
        } else {
            checkResult = false;
        }
        return checkResult;
    }

    /**
     * Add food to array list.
     * @param food - recyclable food.
     * @return boolean add or no.
     */
    public boolean addFood(RecycleFood food) {
        boolean added = false;
        if (!this.foods.contains(food)) {
            this.foods.add(food);
            added = true;
        }
        return added;
    }

    /**
     * Get foods array list.
     * @return - recyclable foods array list.
     */
    public ArrayList<RecycleFood> getRecycleFoods() {
        return this.foods;
    }

    /**
     * Remove food from foods array list.
     * @param food - food to remove.
     */
    public void removeFood(RecycleFood food) {
        this.foods.remove(food);
    }
}
