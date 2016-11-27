package ru.epatko.models;

import java.util.Date;

/**
* An order.
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 */
public class Order {

/**
* Order ID.
*/
	private int id;
/**
* Order date.
*/
	private long date;
/**
* Order name.
*/
	private String name;
/**
* Order description.
*/
	private String description;
/**
* Order comment.
*/
	private String comment;

/**
* Empty constructor.
*/
	public Order() {
		this(0, "default", "default");
	}

	/**
	 * Constructor.
	 * @param orderID - order ID.
	 * @param orderName - order Name.
	 * @param orderDescription - order description.
	 */
	public Order(int orderID, String orderName, String orderDescription) {
		this.id = orderID;
		this.date = new Date().getTime();
		this.name = orderName;
		this.description = orderDescription;
	}

/**
* Get order ID.
* @return order ID.
*/
	public int getId() {
		return this.id;
	}

/**
* Get order date.
* @return order date.
*/
	public long getDate() {
		return this.date;
	}




/**
* Set new order description.
* @param newDescription - new order description.
*/
	public void setNewDescription(String newDescription) {
		this.description = newDescription;
	}

/**
* Get order description.
* @return order description.
*/
	public String getDescription() {
		return this.description;
	}

/**
* Set new order comment.
* @param newComment - new order comment.
*/
	public void setNewComment(String newComment) {
		this.comment = newComment;
	}

/**
* Get order comment.
* @return order comment.
*/
	public String getComment() {
		return this.comment;
	}

/**
* Get order name.
* @return order name.
*/
	public String getName() {
		return this.name;
	}
}