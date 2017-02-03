package ru.epatko.newQualityControl;

import ru.epatko.qualityControl.*;

import java.util.ArrayList;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         04.02.17.
 */
public class NewQualityControl extends QualityControl {

    /**
     * Current date.
     */
    private long currentDate;
    /**
     * Stores array list.
     */
    private ArrayList<RecycleWareHouse> stores = new ArrayList<>();

    @Override
    public void setCurrentDate(long currentDate) {
        this.currentDate = currentDate;
    }

    /**
     * Get stores array list.
     * @return - stores array list.
     */
    public ArrayList<RecycleWareHouse> getNewStores() {
        return this.stores;
    }

    /**
     * Fill stores array list.
     */
    @Override
    public void fillStores() {
        addStore(new ExtendedRecycleWareHouse(new RecycleWareHouse()));
        addStore(new NewShop());
        addStore(new NewTrash());
        addStore(new ProcessingFactory());
    }

    /**
     * Add store to stores array list.
     * @param store - store.
     */
    public void addStore(RecycleWareHouse store) {
        this.stores.add(store);
    }

    /**
     * Put food to the store.
     * @param food - food.
     * @return - boolean, moved food or no.
     */
    public boolean putFood(RecycleFood food) {
        boolean moved = false;
        for (int i = 0; i < this.stores.size(); i++) {
            if (this.stores.get(i).isAppropriate(food, this.currentDate)) {
                moved = true;
                break;
            }
        }
        return moved;
    }

    /**
     * Check food quality.
     */
    public void checkQuality() {
        for (int i = 0; i < this.stores.size(); i++) {
            ArrayList<RecycleFood> foods = stores.get(i).getRecycleFoods();
            for (int j = 0; j < foods.size(); j++) {
                RecycleFood food = foods.get(j);
                if (this.putFood(food)) {
                    stores.get(i).removeFood(food);
                }
            }
        }
    }
}
