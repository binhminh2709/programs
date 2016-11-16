package ru.epatko.tracker;

/**
* Tracker Test
* @author Mikhail Epatko 
*/
import ru.epatko.models.Order;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


public class TrackerTest {

/**
* Testing metods: tracker.getAllOrders(), tracker.add().
*/	
	@Test
	public void wenGiveOrdersToTrackerThenCanGetOrdersArray() {
		Order[] orderArray = new Order[15]; 
		orderArray[0] = new Order();
		orderArray[1] = new Order(123, 112233L, "Some order name", "Some order description");
		
		Tracker tracker = new Tracker();
		tracker.add(orderArray[0]);
		tracker.add(orderArray[1]);

		assertThat(orderArray, is(tracker.getAllOrders()));
	}

/**
* Testing metods: tracker.getOrderById(), tracker.change(comment).
*/
	@Test
	public void wenTryChangeOrderCommentsThenCanGetNewOrderComment() {
		//Order order = new Order();
		Tracker tracker = new Tracker();
		tracker.add(new Order());
		
		tracker.change(0, "comment", "New comment");

		assertThat("New comment", is(tracker.getOrderById(0).getComment()));
	}

/**
* Testing metods: tracker.getOrderById(), tracker.change(description).
*/
	@Test
	public void wenTryChangeOrderDescriptionThenCanGetNewOrderDescription() {
		Tracker tracker = new Tracker();
		tracker.add(new Order());
		
		tracker.change(0, "description", "New description");

		assertThat("New description", is(tracker.getOrderById(0).getDescription()));
	}

/**
* Testing metods: tracker.getOrderById.
*/
	@Test
	public void wenAskToGetOrdersByIdThenGetOrdersById() {
		Order first = new Order(1, 111L, "first name", "first description");
		Order second = new Order(2, 222L, "second name", "second description");
		Order third = new Order(3, 111L, "third name", "third description");

		Tracker tracker = new Tracker();
		
		tracker.add(first);
		tracker.add(second);
		tracker.add(third);

		assertThat(second, is(tracker.getOrderById(2)));
	}

/**
* Testing metods: tracker.delete.
*/
	@Test
	public void wenAskToDeletOrderThenTrackerDeletOrder() {

		Order first = new Order(1, 111L, "first name", "first description");
		Order second = new Order(2, 222L, "second name", "second description");
		Order third = new Order(3, 333L, "third name", "third description");

		Tracker tracker = new Tracker();

		tracker.add(first);
		tracker.add(second);
		tracker.add(third);

		tracker.delete(2);

		assertThat(null, is(tracker.getOrderByIndex(1)));
	}

/**
* Testing metods: tracker.getOrdersByFilter.
*/
	@Test
	public void wenSetAFilterThenGetFilteredArrayOfOrders() {

		Order first = new Order(1, 111L, "aaa sss fff lggg", "first description");
		Order second = new Order(2, 222L, "jjjff llldd ;;;", "second description");
		Order third = new Order(3, 333L, "nnn ee aaa", "third description");

		Tracker tracker = new Tracker();

		tracker.add(first);
		tracker.add(second);
		tracker.add(third);

		Order[] testArray = new Order[tracker.getAllOrders().length];
		testArray[0] = first;
		testArray[1] = second;

		assertThat(testArray, is(tracker.getOrdersByFilter("f l")));
	}
}