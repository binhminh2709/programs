package ru.epatko.startui;

import ru.epatko.models.Order;
import ru.epatko.tracker.Tracker;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * StartUI.
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
  */

public class StartUI {
	/**
	 * Input.
 	 */
	private Input input;
	/**
	 * Order ID (counter).
 	 */
	private int orderID = 0;
	/**
	 * Order create date.
 	 */
	private Date date = new Date();
	/**
	 * Tracker.
	 */
	private Tracker tracker;

	/**
	 * Constructor.
	 *
	 * @param inputMethod - input interface.
	 * @param manager - orders manager (tracker).
	 */
	public StartUI(Input inputMethod, Tracker manager) {
		this.input = inputMethod;
		this.tracker = manager;
	}

	/**
	 * Method print main user menu.
	 */
	public void printMenu() {
		System.out.println("-----------------------\nMenu:\n-----------------------");
		System.out.println("0 - Print this menu.");
		System.out.println("1 - Add new order.");
		System.out.println("2 - Change order.");
		System.out.println("3 - Delete order.");
		System.out.println("4 - Get list of orders.");
		System.out.println("5 - Get order by name.");
		System.out.println("6 - Quit program.");
	}

	/**
	 * Processing user choice.
	 */
	public void choice() {
		Order order;
		String name;
		String description;
		String field;
		String choice;
		int id;
		/**
		 * Cycle.
		 */
		do {
			choice = this.input.ask("Enter your choice number: ");

			if (choice.equals("0")) {
				printMenu();

			} else if (choice.equals("1")) {
				name = this.input.ask("Enter order name: ");
				description = this.input.ask("Enter order description: ");
				this.orderID++;
				order = new Order(this.orderID, this.date.getTime(), name, description);
				this.tracker.add(order);
				System.out.printf("Order ID: %d was added.\n", this.orderID);

			} else if (choice.equals("2")) {
				id = Integer.parseInt(this.input.ask("Enter order ID: "));
				field = this.input.ask("Enter field to change: \"comment\" - change order comment, \"description\" - change order description: ");
				description = this.input.ask("Enter new description/commit: ");
				this.tracker.change(id, field, description);

			} else if (choice.equals("3")) {
				id = Integer.parseInt(this.input.ask("Enter ID of order to delete: "));
				this.tracker.delete(id);

			} else if (choice.equals("4")) {
				for (Order element : this.tracker.getAllOrders()) {
					if (element != null) {
						printOrder(element);
					}
				}

			} else if (choice.equals("5")) {
				name = this.input.ask("Enter keyword: ");
				for (Order element : this.tracker.getOrdersByFilter(name)) {
					if (element != null) {
						printOrder(element);
					}
				}
			} else if (choice.equals("6")) {
				return;
			} else if (choice.equals("pause")) {
				continue;
			} else {
				System.out.println("Incorrect input. Try again.");
			}
		} while (true);
	}

	/**
	 * Print an order.
 	 * @param order - order to print.
	 */
	public void printOrder(Order order) {
		System.out.printf("Order ID: %d.\nOrder name: %s.\nOrder description: %s.\nOrder comment: %s.\n", order.getId(),
						  order.getName(), order.getDescription(), order.getComment());
		System.out.print("Order date: ");
		System.out.println(new SimpleDateFormat("dd.MM.yyyy hh:mm").format(order.getDate()));
		System.out.println("************************************************************");
	}

	/**
	 * Main method.
	 * @param args - no arguments.
	 */
	public static void main(String[] args) {
		Tracker tracker = new Tracker();
		Input inputMethod = new StubInput();
		StartUI start = new StartUI(inputMethod, tracker);
		start.printMenu();
		start.choice();
	}
}
