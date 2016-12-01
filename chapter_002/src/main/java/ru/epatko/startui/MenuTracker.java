package ru.epatko.startui;

import ru.epatko.tracker.Tracker;
import ru.epatko.models.Order;
import java.text.SimpleDateFormat;

/**
 * New Tracker menu.
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         27.11.16.
 */
public class MenuTracker {
    /**
     * Input method.
     */
    private Input input;
    /**
     * Tracker.
     */
    private Tracker tracker;
    /**
     * Order ID.
     */
    private int orderID;
    /**
     * Actions array.
     */
    private UserAction[] actions = new UserAction[5];

    private int[] keys = new int[this.actions.length];

    /**
     * MenuTracker constructor.
     * @param input - input method.
     * @param tracker - tracker.
     */
    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Print user menu.
     */
    public void showUserMenu() {
        for (UserAction action : actions) {
            System.out.println(action.info());
        }
    }

    /**
     * Print an order.
     * @param order - order to print.
     */
    public void printOrder(Order order) {
        System.out.printf("Order ID: %d.\nOrder name: %s.\nOrder description: %s.\nOrder comment: %s.\n", order.getId(), order.getName(), order.getDescription(), order.getComment());
        System.out.print("Order date: ");
        System.out.println(new SimpleDateFormat("dd.MM.yyyy hh:mm").format(order.getDate()));
        System.out.println("*******************************************");
    }

    /**
     * Start the selected action.
     * @param key - number of action.
     */
    public void choice(int key) {
        this.actions[key].execute(this.input, this.tracker);
    }

    /**
     * To fill actions array.
     */
    public void fillActions() {
        this.actions[0] = this.new AddOrder();
        this.actions[1] = this.new ChangeOrder();
        this.actions[2] = this.new DeleteOrder();
        this.actions[3] = new MenuTracker.GetListOfOrders();
        this.actions[4] = this.new GetOrdersFilteredByName();

    /**
     * To fill keys array.
      */
        for (int i = 0; i < this.actions.length; i++) {
            keys[i] = this.actions[i].key();
        }
    }

    /**
     * To get keys array.
      * @return - keys array.
     */
    public int[] getKeys() {
        return this.keys;
    }


    //****************************************//
    //************* Inner Classes ************//
    //****************************************//

    /**
     * Add new order to array.
     */
    private class AddOrder implements UserAction {
        /**
         * Number of action.
         * @return - number of action.
         */
        @Override
        public int key() {
            return 0;
        }

        /**
         * Information about action.
         * @return - information about action.
         */
        @Override
        public String info() {
            return String.format("-------------------------------------------\n%d - %s", this.key(), "Add new order.");
        }

        /**
         * Action.
         * @param input - input method.
         * @param tracker - tracker.
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Enter order name: ");
            String description = input.ask("Enter order description: ");
            Order order = new Order(++orderID, name, description);
            tracker.add(order);
            System.out.println("--------------------------");
            System.out.printf("| Order ID: %d was added. |\n", order.getId());
            System.out.println("--------------------------");
        }
    }

    /**
     * Change order comment or order description.
     */
    private class ChangeOrder implements UserAction {
        /**
         * Number of action.
         * @return - number of action.
         */
        @Override
        public int key() {
            return 1;
        }

        /**
         * Information about action.
         * @return - information about action.
         */
        @Override
        public String info() {
            return String.format("%d - %s", this.key(), "Change order comment/description.");
        }

        /**
         * Action.
         * @param input - input method.
         * @param tracker - tracker.
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            boolean invalid = true;
            int id = 0;
            do {
                try {
                    id = Integer.parseInt(input.ask("Enter order ID to change: "));
                    invalid = false;
                } catch (NumberFormatException nfe) {
                    System.out.println("Incorrect input. Try again.");
                }
            } while (invalid);

            if (tracker.getOrderById(id) != null) {
                String field = input.ask("Enter field to change: \"comment\" or \"description\": ");
                String description = input.ask("Enter new description \\ comment: ");
                tracker.change(id, field, description);
            } else {
                System.out.println("There is no order with this ID.");
            }
        }
    }

    /**
     * Delete order by ID.
     */
    private class DeleteOrder implements UserAction {
        /**
         * Number of action.
         * @return - number of action.
         */
        @Override
        public int key() {
            return 2;
        }

        /**
         * Information about action.
         * @return - information about action.
         */
        @Override
        public String info() {
            return String.format("%d - %s", this.key(), "Delete order.");
        }

        /**
         * Action.
         * @param input - input method.
         * @param tracker - tracker.
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            boolean invalid = true;
            int id = 0;
            do {
                try {
                    id = Integer.parseInt(input.ask("Enter order ID to delete: "));
                    invalid = false;
                } catch (NumberFormatException nfe) {
                    System.out.println("Incorrect input. Try again.");
                }
            } while (invalid);

            if (tracker.getOrderById(id) != null) {
                tracker.delete(id);
            } else {
                System.out.println("There is no order with this ID.");
            }
        }
    }

    /**
     * Print all orders.
     */
    private static class GetListOfOrders implements UserAction {
        /**
         * Number of action.
         * @return - number of action.
         */
        @Override
        public int key() {
            return 3;
        }

        /**
         * Information about action.
         * @return - information about action.
         */
        @Override
        public String info() {
            return String.format("%d - %s", this.key(), "Print all orders.");
        }

        /**
         * Action.
         * @param input - input method.
         * @param tracker - tracker.
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            MenuTracker menu = new MenuTracker(input, tracker);
            int counter = 0;
            for (Order element : tracker.getAllOrders()) {
                if (element != null) {
                    menu.printOrder(element);
                    counter++;
                }
            }
            if (counter == 0) {
                System.out.println("There are no orders to print.");
            }
        }
    }

    /**
     * Get Orders Filtered By Name.
     */
    private class GetOrdersFilteredByName implements UserAction {
        /**
         * Number of action.
         * @return - number of action.
         */
        @Override
        public int key() {
            return 4;
        }

        /**
         * Information about action.
         * @return - information about action.
         */
        @Override
        public String info() {
            return String.format("%d - %s", this.key(), "Print orders filtered by name content.");
        }

        /**
         * Action.
         * @param input - input method.
         * @param tracker - tracker.
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            int counter = 0;
            String name = input.ask("Enter keyword: ");
            for (Order element : tracker.getOrdersByFilter(name)) {
                if (element != null) {
                    printOrder(element);
                    counter++;
                }
            }
            if (counter == 0) {
                System.out.println("There are no orders contain this keyword in the name.");
            }
        }
    }
}
