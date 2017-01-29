package ru.epatko.qualityControl;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         29.01.17.
 */
public class QualityControlTest {
    private final int WARE_HOUSE = 0;
    private final int SHOP = 1;
    private final int TRASH = 2;

    @Test
    public void whenPutFoodInToStoreThenGetFoodFromWareHouse() throws Exception {

        QualityControl qControl = new QualityControl(4);
        qControl.fillStores();
        Meat meat = new Meat();
        meat.setCreateDate(1);
        meat.setExpirationDate(16);
        qControl.putFood(meat);
        assertThat(qControl.getStores().get(WARE_HOUSE).getFoods().contains(meat), is(true));
    }

    @Test
    public void whenPutFoodInToStoreThenGetFoodFromShop() throws Exception {

        QualityControl qControl = new QualityControl(8);
        qControl.fillStores();
        Apple apple = new Apple();
        apple.setCreateDate(1);
        apple.setExpirationDate(17);
        qControl.putFood(apple);
        assertThat(qControl.getStores().get(SHOP).getFoods().contains(apple), is(true));
    }

    @Test
    public void whenPutFoodInToStoreThenGetFoodFromTrash() throws Exception {

        QualityControl qControl = new QualityControl(14);
        qControl.fillStores();
        Apple apple = new Apple();
        apple.setCreateDate(1);
        apple.setExpirationDate(17);
        qControl.putFood(apple);
        assertThat(qControl.getStores().get(TRASH).getFoods().contains(apple), is(true));
    }

    @Test
    public void whenChangeCurrentDSateThenControllerTransfersFoodToTheNextStore() throws Exception {

        QualityControl qControl = new QualityControl(4);
        qControl.fillStores();
        Apple apple = new Apple();
        apple.setCreateDate(1);
        apple.setExpirationDate(17);
        qControl.putFood(apple);
        assertThat(qControl.getStores().get(WARE_HOUSE).getFoods().contains(apple), is(true));
        assertThat(qControl.getStores().get(SHOP).getFoods().isEmpty(), is(true));
        assertThat(qControl.getStores().get(TRASH).getFoods().isEmpty(), is(true));
        qControl.setCurrentDate(8);
        qControl.checkQuality();
        assertThat(qControl.getStores().get(SHOP).getFoods().contains(apple), is(true));
        assertThat(qControl.getStores().get(WARE_HOUSE).getFoods().isEmpty(), is(true));
        assertThat(qControl.getStores().get(TRASH).getFoods().isEmpty(), is(true));
        qControl.setCurrentDate(14);
        qControl.checkQuality();
        assertThat(qControl.getStores().get(TRASH).getFoods().contains(apple), is(true));
        assertThat(qControl.getStores().get(SHOP).getFoods().isEmpty(), is(true));
        assertThat(qControl.getStores().get(WARE_HOUSE).getFoods().isEmpty(), is(true));
    }
}
