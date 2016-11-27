package ru.epatko.tracker;

/**
* Tracker Test
 * @author Mikhail Epatko (epatko-m-i@rambler.ru)
 */
import ru.epatko.models.Order;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


public class TrackerTest {

/**
* Testing methods: tracker.getAllOrders(), tracker.add().
*/	
	@Test
	public void wenGiveOrdersToTrackerThenCanGetOrdersArray() {
		Order[] orderArray = new Order[15]; 
		orderArray[0] = new Order(1, "","");
		orderArray[1] = new Order(2, "Some order name", "Some order description");
		
		Tracker tracker = new Tracker();
		tracker.add(orderArray[0]);
		tracker.add(orderArray[1]);

		assertThat(tracker.getAllOrders(), is(orderArray));
	}

/**
* Testing methods: tracker.getOrderById(), tracker.change(comment).
*/
	@Test
	public void wenTryChangeOrderCommentThenCanGetNewOrderComment() {
		Tracker tracker = new Tracker();
		Order order = new Order();
		tracker.add(order);
		
		tracker.change(order.getId(), "comment", "New comment");

		assertThat("New comment", is(tracker.getOrderById(order.getId()).getComment()));
	}
/**
* Testing methods: tracker.getOrderById(), tracker.change(description).
*/
	@Test
	public void wenTryChangeOrderDescriptionThenCanGetNewOrderDescription() {
		Tracker tracker = new Tracker();
		Order order = new Order();
		tracker.add(order);
		
		tracker.change(order.getId(), "description", "New description");

		assertThat("New description", is(tracker.getOrderById(order.getId()).getDescription()));
	}

/**
* Testing methods: tracker.getOrderById.
*/
	@Test
	public void wenAskToGetOrderByIdThenGetOrderById() {
		Order first = new Order(1, "first name", "first description");
		Order second = new Order(2, "second name", "second description");
		Order third = new Order(3, "third name", "third description");

		Tracker tracker = new Tracker();
		
		tracker.add(first);
		tracker.add(second);
		tracker.add(third);

		assertThat(second, is(tracker.getOrderById(second.getId())));
	}

/**
* Testing methods: tracker.delete.
*/
	@Test
	public void wenAskToDeletOrderThenTrackerDeletOrder() {

		Order first = new Order(1, "first name", "first description");
		Order second = new Order(2, "second name", "second description");
		Order third = new Order(3, "third name", "third description");

		Tracker tracker = new Tracker();

		tracker.add(first);
		tracker.add(second);
		tracker.add(third);


		tracker.delete(second.getId());

		assertThat(null, is(tracker.getOrderById(second.getId())));
	}

/**
* Testing methods: tracker.getOrdersByFilter.
*/
	@Test
	public void wenSetAFilterThenGetFilteredArrayOfOrders() {

		Order first = new Order(1, "aaa sss fff lggg", "first description");
		Order second = new Order(2, "jjjff llldd ;;;", "second description");
		Order third = new Order(3, "nnn ee aaa", "third description");

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