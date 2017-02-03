package ru.epatko.newQualityControl;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         04.02.17.
 */
public class ProcessingFactory extends RecycleWareHouse {
    /**
     * Get store name.
     * @return - store name.
     */
    @Override
    public String getName() {
        return "Processing Factory";
    }

    /**
     * Check is food appropriate this storage.
     * @param food        - recyclable food.
     * @param currentDate - current date.
     * @return - true or false.
     */
    @Override
    public boolean isAppropriate(RecycleFood food, long currentDate) {
        boolean checkResult;
        double remainingPeriod = 1d - (double) (currentDate - food.getCreateDate())
                                        / (double) ((food.getExpirationDate() - food.getCreateDate()));
        if ((food.isRecyclable() && remainingPeriod <= 0d) && addFood(food)) {
            checkResult = true;
        } else {
            checkResult = false;
        }
        return checkResult;
    }
}
