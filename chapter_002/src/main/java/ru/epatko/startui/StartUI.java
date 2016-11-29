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
			menu.choice(Integer.parseInt(key));
		} while (!"y".equals(this.input.ask("Exit? y/n ")));
	}

	/**
	 * Main method.
	 * @param args - no arguments.
	 */
	public static void main(String[] args) {
		Tracker tracker = new Tracker();
		Input inputMethod = new ConsoleInput();
		StartUI start = new StartUI(inputMethod, tracker);
		start.init();
	}
}
