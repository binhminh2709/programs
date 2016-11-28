package ru.epatko.startui;

import ru.epatko.tracker.Tracker;

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
	private int orderID;

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
	 * Start menu.
	 */
	public void init() {
		MenuTracker menu = new MenuTracker(this.input, this.tracker);
		menu.fillActions();

		/**
		 * Cycle.
		 */
		do {
			menu.showUserMenu();
			String key = input.ask("Enter your choice number: ");
			if (key.equals("0")
			 || key.equals("1")
			 ||	key.equals("2")
			 ||	key.equals("3")
			 ||	key.equals("4")) {
				menu.choice(Integer.parseInt(key));
			} else if (key.equals("5")) {
				return;
			} else {
				System.out.println("Incorrect input. Try again.");
			}
		} while (true);
	}

	/**
	 * Main method.
	 * @param args - no arguments.
	 */
	public static void main(String[] args) {
		Tracker tracker = new Tracker();
		Input inputMethod = new StubInput();
		StartUI start = new StartUI(inputMethod, tracker);
		start.init();
	}
}
