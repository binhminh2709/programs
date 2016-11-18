package ru.epatko.startui;

import ru.epatko.models.Order;
import ru.epatko.tracker.Tracker;

/**
* StartUI.
* @author Mikhail Epatko.
*/

public class StartUI {

/**
* Main method.
* @param args - no arguments.
*/
	public static void main(String[] args) {

		Tracker tracker = new Tracker();
		Order order = new Order();
		tracker.add(order);
		System.out.println(tracker.getOrderByIndex(0).getName());
	}
}
