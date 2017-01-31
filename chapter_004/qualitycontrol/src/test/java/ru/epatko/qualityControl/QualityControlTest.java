package ru.epatko.qualityControl;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         29.01.17.
 */
public class QualityControlTest {
    private static final int WARE_HOUSE = 0;
    private static final int SHOP = 1;
    private static final int TRASH = 2;

    @Test
    public void whenPutFoodInToStoreThenGetFoodFromWareHouse() {

        QualityControl controller = new QualityControl();
        controller.fillStores();
        controller.setCurrentDate(4);
        Meat meat = new Meat();
        meat.setCreateDate(1);
        meat.setExpirationDate(16);
        controller.putFood(meat);
        assertThat(controller.getStores().get(WARE_HOUSE).getFoods().contains(meat), is(true));
    }

    @Test
    public void whenPutFoodInToStoreThenGetFoodFromShop() {

        QualityControl controller = new QualityControl();
        controller.fillStores();
        controller.setCurrentDate(8);

        Apple apple = new Apple();
        apple.setCreateDate(1);
        apple.setExpirationDate(17);
        controller.putFood(apple);
        assertThat(controller.getStores().get(SHOP).getFoods().contains(apple), is(true));
    }

    @Test
    public void whenPutFoodInToStoreThenGetFoodFromTrash() {

        QualityControl controller = new QualityControl();
        controller.fillStores();
        controller.setCurrentDate(18);
        Apple apple = new Apple();
        apple.setCreateDate(1);
        apple.setExpirationDate(17);
        controller.putFood(apple);
        assertThat(controller.getStores().get(TRASH).getFoods().contains(apple), is(true));
    }

    @Test
    public void whenChangeCurrentDateThenControllerTransfersFoodToTheNextStore() {

        QualityControl controller = new QualityControl();
        controller.fillStores();
        controller.setCurrentDate(4);
        Apple apple = new Apple();
        apple.setCreateDate(1);
        apple.setExpirationDate(17);
        controller.putFood(apple);

        assertThat(controller.getStores().get(WARE_HOUSE).getFoods().contains(apple), is(true));
        assertThat(controller.getStores().get(SHOP).getFoods().isEmpty(), is(true));
        assertThat(controller.getStores().get(TRASH).getFoods().isEmpty(), is(true));

        controller.setCurrentDate(8);
        controller.checkQuality();
        assertThat(controller.getStores().get(SHOP).getFoods().contains(apple), is(true));

        assertThat(controller.getStores().get(WARE_HOUSE).getFoods().isEmpty(), is(true));
        assertThat(controller.getStores().get(TRASH).getFoods().isEmpty(), is(true));

        controller.setCurrentDate(17);
        controller.checkQuality();
        assertThat(controller.getStores().get(TRASH).getFoods().contains(apple), is(true));
        assertThat(controller.getStores().get(SHOP).getFoods().isEmpty(), is(true));
        assertThat(controller.getStores().get(WARE_HOUSE).getFoods().isEmpty(), is(true));
    }
    @Test
    public void whenSetAppleFieldsThenCanGetFields() {
        Apple apple = new Apple();
        apple.setExpirationDate(1l);
        apple.setCreateDate(1l);
        apple.setDiscount(1d);
        apple.setName("1");
        apple.setPrice(1d);
        apple.setQuantity(1d);

        assertThat(apple.getCreateDate(), is(1L));
        assertThat(apple.getDiscount(), is(1d));
        assertThat(apple.getExpirationDate(), is(1L));
        assertThat(apple.getName(), is("1"));
        assertThat(apple.getQuantity(), is(1d));
        assertThat(apple.getPrice(), is(1d));
    }
    @Test
    public void whenSetMeatFieldsThenCanGetFields() {
        Meat meat = new Meat();
        meat.setExpirationDate(1L);
        meat.setCreateDate(1L);
        meat.setDiscount(1d);
        meat.setName("1");
        meat.setPrice(1d);
        meat.setQuantity(1d);

        assertThat(meat.getCreateDate(), is(1L));
        assertThat(meat.getDiscount(), is(1d));
        assertThat(meat.getExpirationDate(), is(1L));
        assertThat(meat.getName(), is("1"));
        assertThat(meat.getQuantity(), is(1d));
        assertThat(meat.getPrice(), is(1d));
    }

    @Test
    public void whenSetSmallRemainingPeriodThenGetDiscountPrice() {
        QualityControl controller = new QualityControl();
        controller.fillStores();
        Apple apple = new Apple();
        apple.setCreateDate(1);
        apple.setExpirationDate(17);
        apple.setPrice(1d);
        controller.putFood(apple);

        controller.setCurrentDate(14);
        controller.checkQuality();
        assertThat(controller.getStores().get(SHOP).getFoods().contains(apple), is(true));
        assertThat(controller.getStores().get(SHOP).getFoods().get(0).getPrice(), is(0.5));
        assertThat(controller.getStores().get(TRASH).getFoods().isEmpty(), is(true));
        assertThat(controller.getStores().get(WARE_HOUSE).getFoods().isEmpty(), is(true));
    }
}