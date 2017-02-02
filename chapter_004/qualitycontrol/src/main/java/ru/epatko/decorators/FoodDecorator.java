package ru.epatko.decorators;

import ru.epatko.qualityControl.Food;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         02.02.17.
 */
public abstract class FoodDecorator implements Food {

    /**
     * Food.
     */
    private Food food;

    /**
     * Constructor.
     * @param food - food.
     */
    public FoodDecorator(Food food) {
        this.food = food;
    }

    /**
     * Recyclable flag.
     * @return - true or false.
     */
    public abstract boolean isRecyclable();
    /**
     * Set recyclable flag.
     */
    public abstract void setRecyclable();

    /**
     * Set name.
     * @param name - product name.
     */
    @Override
    public void setName(String name) {
        this.food.setName(name);
    }

    /**
     * Set product expiration date.
     * @param expirationDate - product expiration date.
     */
    @Override
    public void setExpirationDate(long expirationDate) {
        this.food.setExpirationDate(expirationDate);
    }

    /**
     * Set product creation date.
     * @param createDate - product creation date.
     */
    @Override
    public void setCreateDate(long createDate) {
        this.food.setCreateDate(createDate);
    }

    /**
     * Set product quantity.
     * @param quantity - product quantity.
     */
    @Override
    public void setQuantity(double quantity) {
        this.food.setQuantity(quantity);
    }

    /**
     * Set product price.
     * @param price - product price.
     */
    @Override
    public void setPrice(double price) {
        this.food.setPrice(price);
    }

    /**
     * Set discount.
     * @param discount - discount.
     */
    @Override
    public void setDiscount(double discount) {
        this.food.setDiscount(discount);
    }

    /**
     * Set discount flag.
     */
    @Override
    public void setDiscountFlag() {
        this.food.setDiscountFlag();
    }

    /**
     * Get discount flag.
     * @return - boolean, apply discount or no.
     */
    @Override
    public boolean getDiscountFlag() {
        return this.food.getDiscountFlag();
    }

    /**
     * get product name.
     * @return -  product name.
     */
    @Override
    public String getName() {
        return this.food.getName();
    }

    /**
     * Get product expiration date.
     * @return - product expiration date (long).
     */
    @Override
    public long getExpirationDate() {
        return this.food.getExpirationDate();
    }

    /**
     * Get product creation date.
     * @return - product creation date (long).
     */
    @Override
    public long getCreateDate() {
        return this.food.getCreateDate();
    }

    /**
     * Get product quantity.
     * @return - product quantity.
     */
    @Override
    public double getQuantity() {
        return this.food.getQuantity();
    }

    /**
     * Get product price.
     * @return - product price.
     */
    @Override
    public double getPrice() {
        return this.food.getPrice();
    }

    /**
     * Get product discount.
     * @return - discount.
     */
    @Override
    public double getDiscount() {
        return this.food.getDiscount();
    }
}
