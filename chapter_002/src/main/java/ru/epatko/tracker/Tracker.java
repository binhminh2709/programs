package ru.epatko.tracker;

import ru.epatko.models.Order;

/**
 * Manager of orders.
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 */

public class Tracker {

/**
* Number of elements.
*/
	private final int n = 15;
/**
* Create array of orders.
*/
	private Order[] orders = new Order[n];
/**
* Index of array of orders.
*/
	private int index = 0;
/**
* Add new order to array.
* @param order - new order.
*/
 	public void add(Order order) {
		for (int i = 0; i < this.orders.length; i++) {
			if (this.orders[i] == null) {
				this.orders[i] = order;
				break;
			}
		}
		this.orders[this.index] = order;
		this.index++;
	}

/**
* Change order.
* @param orderId - order ID.
* @param field - "comment" - change order comment, "description" - change order description.
* @param newDescription - new description or new comment.
*/
 	public void change(int orderId, String field, String newDescription) {

 		for (Order element : this.orders) {
			if (element.getId() == orderId) {
				if (field.equals("description")) {
 				element.setNewDescription(newDescription);
 				}
 				if (field.equals("comment")) {
 				element.setNewComment(newDescription);
 				}
				break;
			}
		} 		
 	}

/**
* Delete order from array.
* @param orderId - ID of order to delete.
*/
	public void delete(int orderId) {
		for (int i = 0; i < this.orders.length; i++) {
			if (orders[i].getId() == orderId) {
				orders[i] = null;
				break;
			}
		}
	}

/**
* Get order list.
* @return All orders array.
*/
	public Order[] getAllOrders() {
		return this.orders;
	}

/**
* Get filtered order.
* @param orderId - ID of order to get.
* @return Order by ID.
*/
	public Order getOrderById(int orderId) {
		Order result = new Order();
		for (Order element : this.orders) {
			if (element.getId() == orderId) {
				result = element;
				break;
			}
		}
		return result;
	}

/**
* Get order by index.
* @param orderIndex - order index.
* @return Order by index.
*/
	public Order getOrderByIndex(int orderIndex) {
		return this.orders[orderIndex];
	}

/**
* Get orders by filter.
* @param filter - filter by symbols in name.
* @return filtered array of orders.
*/
	public Order[] getOrdersByFilter(String filter) {
		Order[] result = new Order[this.orders.length];
		int i = 0;
		for (Order element : this.orders) {
			if (element == null) {
				continue;
			}
			if (element.getName().contains(filter)) {
				result[i] = element;
				i++;
			}
		}
		return result;
	}
}

