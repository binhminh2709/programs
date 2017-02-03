package ru.epatko.newQualityControl;

import java.util.ArrayList;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         03.02.17.
 */
public class ExtendedRecycleWareHouse extends RecycleWareHouse {

    /**
     * Maximum main recyclable foods store size (store filling simulation).
     */
    public static final int MAX_STORE_SIZE = 2;

    /**
     * Recycle warehouse storage.
     */
    private RecycleWareHouse storage;

    /**
     * Recycle food array list.
     */
    private ArrayList<RecycleFood> extendedStorage = new ArrayList<>();

    /**
     * Constructor.
     * @param storage - Recycle warehouse storage.
     */
    public ExtendedRecycleWareHouse(RecycleWareHouse storage) {
        this.storage = storage;
    }

    /**
     * Get storage name.
     * @return - store name.
     */
    @Override
    public String getName() {
        return "Extended Recycle Ware House";
    }

    /**
     * Add recyclable food to array list.
     * @param food - recyclable food.
     * @return boolean add or no.
     */
    @Override
    public boolean addFood(RecycleFood food) {

        /**
         * Added flag.
         */
        boolean added = false;
        if (storage.getRecycleFoods().size() == MAX_STORE_SIZE) {      //Store filling simulation.
            if (!this.extendedStorage.contains(food)) {
                this.extendedStorage.add(food);
                added = true;
            }
        } else {
            added = storage.addFood(food);
        }
        return added;
    }

    /**
     * Get foods array list.
     *
     * @return - recyclable foods array list.
     */
    @Override
    public ArrayList<RecycleFood> getRecycleFoods() {
        ArrayList<RecycleFood> foods = new ArrayList<>();
        foods.addAll(storage.getRecycleFoods());
        foods.addAll(this.extendedStorage);
        return foods;
    }

    /**
     * Remove food from foods array list.
     *
     * @param food - food to remove.
     */
    @Override
    public void removeFood(RecycleFood food) {
        if (this.extendedStorage.contains(food)) {
            this.extendedStorage.remove(food);
        } else if (storage.getRecycleFoods().contains(food)) {
            storage.removeFood(food);
        }
    }
}
