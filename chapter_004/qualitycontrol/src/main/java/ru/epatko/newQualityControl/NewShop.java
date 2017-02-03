package ru.epatko.newQualityControl;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         04.02.17.
 */
public class NewShop extends RecycleWareHouse {
    /**
     * Get store name.
     * @return - store name.
     */
    @Override
    public String getName() {
        return "New Shop";
    }

    /**
     * Check is food appropriate this store.
     * @param food        - recyclable food.
     * @param currentDate - current date.
     * @return - true or false.
     */
    @Override
    public boolean isAppropriate(RecycleFood food, long currentDate) {
        boolean checkResult;
        double remainingPeriod = 1d - (double) (currentDate - food.getCreateDate())
                                        / (double) ((food.getExpirationDate() - food.getCreateDate()));

        if (remainingPeriod <= 0.75 && remainingPeriod > 0d) {
            if (remainingPeriod < 0.25 && !food.getDiscountFlag()) {
                food.setPrice(food.getPrice() * (1 - food.getDiscount()));
                food.setDiscountFlag();
            }
            if (addFood(food)) {
                checkResult = true;
            } else {
                checkResult = false;
            }
        } else {
            checkResult = false;
        }
        return checkResult;
    }
}
