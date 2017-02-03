package ru.epatko.qualityControl;

import org.junit.Test;
import ru.epatko.newQualityControl.*;

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
    private static final int FACTORY = 3;

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

    @Test
    public void whenCreateMilkThenCanGetsFields() {
        Milk milk = new Milk();
        milk.setDiscount(0.3);
        milk.setPrice(1d);
        milk.setCreateDate(10L);
        milk.setExpirationDate(15L);
        milk.setQuantity(2d);
        milk.setName("Moo");

        assertThat(milk.getCreateDate(), is(10L));
        assertThat(milk.getDiscount(), is(0.3d));
        assertThat(milk.getExpirationDate(), is(15L));
        assertThat(milk.getName(), is("Moo"));
        assertThat(milk.getQuantity(), is(2d));
        assertThat(milk.getPrice(), is(1d));
        assertThat(milk.isRecyclable(), is(true));
    }
    @Test
    public void whenCreateRecycleWareHouseThenCanAddRecycleFood() {
        Milk milkOne = new Milk();
        Milk milkTwo = new Milk();
        RecycleWareHouse storage = new RecycleWareHouse();

//        Check addFood method.
        storage.addFood(milkOne);
        storage.addFood(milkTwo);
        assertThat(storage.getRecycleFoods().get(0), is(milkOne));
        assertThat(storage.getRecycleFoods().get(1), is(milkTwo));

//        Check removeFood method.
        storage.removeFood(milkOne);
        assertThat(storage.getRecycleFoods().contains(milkOne), is(false));

//        Check isAppropriate method.
        milkOne.setExpirationDate(17L);
        milkOne.setCreateDate(1);
        assertThat(storage.isAppropriate(milkOne, 4), is(true));
        assertThat(storage.getRecycleFoods().contains(milkOne), is(true));

        storage.getRecycleFoods().remove(milkOne);
        milkOne.setExpirationDate(17L);
        milkOne.setCreateDate(1);
        assertThat(storage.isAppropriate(milkOne, 15), is(false));
        assertThat(storage.getRecycleFoods().contains(milkOne), is(false));
    }

    @Test
    public void whenCreateExtendedRecycleWareHouseThenCanGetItsMethods() {
        Milk milkOne = new Milk();
        Milk milkTwo = new Milk();
        Milk milkThree = new Milk();
        RecycleWareHouse storage = new RecycleWareHouse();
        ExtendedRecycleWareHouse extendedStorage = new ExtendedRecycleWareHouse(storage);
        extendedStorage.addFood(milkOne);
        extendedStorage.addFood(milkTwo);
        extendedStorage.addFood(milkThree);
        assertThat(extendedStorage.getRecycleFoods().size(), is(3));
        assertThat(storage.getRecycleFoods().size(), is(2));

        extendedStorage.removeFood(milkTwo);
        assertThat(extendedStorage.getRecycleFoods().size(), is(2));
        assertThat(storage.getRecycleFoods().size(), is(1));
    }

    @Test
    public void whenCreateNewTrashThenCanGetItsMethods() {
        Milk milk = new Milk();
        milk.setCreateDate(1);
        milk.setExpirationDate(17);
        NewTrash trash = new NewTrash();
        trash.isAppropriate(milk, 18);
        assertThat(trash.getRecycleFoods().contains(milk), is(false));

        milk.setRecyclableFlag(false);
        trash.isAppropriate(milk, 18);
        assertThat(trash.getRecycleFoods().contains(milk), is(true));
    }

    @Test
    public void whenCreateProcessingFactoryThenCanGetItsMethods() {
        Milk milk = new Milk();
        milk.setCreateDate(1);
        milk.setExpirationDate(17);
        ProcessingFactory factory = new ProcessingFactory();
        factory.isAppropriate(milk, 18);
        assertThat(factory.getRecycleFoods().contains(milk), is(true));

        factory.getRecycleFoods().remove(milk);
        milk.setRecyclableFlag(false);
        factory.isAppropriate(milk, 18);
        assertThat(factory.getRecycleFoods().contains(milk), is(false));
    }

    @Test
    public void whenCreateNewShopThenCanGetItsMethods() {
        Milk milk = new Milk();
        milk.setCreateDate(1);
        milk.setExpirationDate(17);
        NewShop shop = new NewShop();
        shop.isAppropriate(milk, 8);
        assertThat(shop.getRecycleFoods().contains(milk), is(true));


        shop.getRecycleFoods().get(0).setPrice(1d);
        shop.isAppropriate(shop.getRecycleFoods().get(0), 15);
        assertThat(shop.getRecycleFoods().get(0).getPrice(), is(0.5));

        shop.getRecycleFoods().remove(milk);
        milk.setRecyclableFlag(false);
        shop.isAppropriate(milk, 2);
        assertThat(shop.getRecycleFoods().contains(milk), is(false));
    }

    @Test
    public void whenChangeCurrentDateThenNewControllerTransfersFoodToTheNextStore() {

        NewQualityControl controller = new NewQualityControl();
        controller.fillStores();
        controller.setCurrentDate(2L);
        Milk milk = new Milk();
        milk.setCreateDate(1L);
        milk.setExpirationDate(17L);
        controller.putFood(milk);

        assertThat(controller.getNewStores().get(WARE_HOUSE).getRecycleFoods().contains(milk), is(true));
        assertThat(controller.getNewStores().get(SHOP).getRecycleFoods().isEmpty(), is(true));
        assertThat(controller.getNewStores().get(TRASH).getRecycleFoods().isEmpty(), is(true));

        controller.setCurrentDate(8L);
        controller.checkQuality();
        assertThat(controller.getNewStores().get(SHOP).getRecycleFoods().contains(milk), is(true));
        assertThat(controller.getNewStores().get(WARE_HOUSE).getRecycleFoods().isEmpty(), is(true));
        assertThat(controller.getNewStores().get(TRASH).getRecycleFoods().isEmpty(), is(true));

        controller.setCurrentDate(17);
        controller.checkQuality();
        assertThat(controller.getNewStores().get(FACTORY).getRecycleFoods().contains(milk), is(true));
        assertThat(controller.getNewStores().get(SHOP).getRecycleFoods().isEmpty(), is(true));
        assertThat(controller.getNewStores().get(WARE_HOUSE).getRecycleFoods().isEmpty(), is(true));
        assertThat(controller.getNewStores().get(TRASH).getRecycleFoods().isEmpty(), is(true));
    }
}