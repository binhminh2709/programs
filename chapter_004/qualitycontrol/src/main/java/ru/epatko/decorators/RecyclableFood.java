package ru.epatko.decorators;

import ru.epatko.qualityControl.Food;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         02.02.17.
 *
 * Recyclable food class.
 */
public class RecyclableFood extends FoodDecorator {

    /**
     * Recyclable flag.
     */
    private boolean recyclable = false;

    /**
     * Constructor.
     * @param food - food.
     */
    public RecyclableFood(Food food) {
        super(food);
    }

    /**
     * Recyclable flag.
     * @return - true or false.
     */
    @Override
    public boolean isRecyclable() {
        return this.recyclable;
    }

    /**
     * Set recyclable flag.
     */
    @Override
    public void setRecyclable() {
        this.recyclable = true;
    }
}
