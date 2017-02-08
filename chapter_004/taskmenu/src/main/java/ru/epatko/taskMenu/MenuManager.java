package ru.epatko.taskMenu;

import java.util.ArrayList;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         04.02.17.
 */
public class MenuManager {

    /**
     * Tasks arraylist.
     */
    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Fill tasks menu.
     */
    public void fillMenu() {
        Task taskOne = new Task("1");
        Task taskTwo = new Task("2");
        Task taskThree = new Task("3");
        Task taskOneOne = new Task("1.1");
        Task taskTwoOne = new Task("2.1");
        Task taskTwoOneOne = new Task("2.1.1");
        Task taskTwoOneTwo = new Task("2.1.2");

        taskTwoOne.addSubTask(taskTwoOneOne);
        taskTwoOne.addSubTask(taskTwoOneTwo);

        taskOne.addSubTask(taskOneOne);
        taskTwo.addSubTask(taskTwoOne);

        this.tasks.add(taskOne);
        this.tasks.add(taskTwo);
        this.tasks.add(taskThree);
    }

    /**
     * Show tasks menu.
     */
    public void showMenu() {
        this.tasks.forEach((task) -> System.out.print(task.info()));
    }

    /**
     * Print error message.
     */
    public void error() {
        System.out.println("Incorrect input.");
    }

    /**
     * Execute task.
     * @param taskNumber - task number to execute.
     * @throws NumberFormatException - exception.
     * @throws IndexOutOfBoundsException - exception.
     */
    public void executeTask(String taskNumber) throws NumberFormatException, IndexOutOfBoundsException {
        String[] parseCommand = taskNumber.split("\\.");
        int commandIndex = 0;
        try {
            int firstCommand = Integer.parseInt(parseCommand[commandIndex++]);
            if (firstCommand <= this.tasks.size()) {
                this.tasks.get(--firstCommand).execute(parseCommand, commandIndex);
            } else {
                error();
            }
        } catch (NumberFormatException | IndexOutOfBoundsException exc) {
            error();
        }
    }
}
