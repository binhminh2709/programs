package ru.epatko.taskMenu;

import java.util.ArrayList;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         04.02.17.
 */
public class MenuManager {

    /**
     * Command index to execute.
     */
    private int commandIndex = 0;

    /**
     * Tasks arraylist.
     */
    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Fill tasks menu.
     */
    public void fillMenu() {
        TaskOne taskOne = new TaskOne();
        TaskTwo taskTwo = new TaskTwo();
        TaskThree taskThree = new TaskThree();
        TaskOneOne taskOneOne = new TaskOneOne();
        TaskTwoOne taskTwoOne = new TaskTwoOne();
        TaskTwoOneOne taskTwoOneOne = new TaskTwoOneOne();
        TaskTwoOneTwo taskTwoOneTwo = new TaskTwoOneTwo();

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
     */
    public void executeTask(String taskNumber) {
        String[] parseCommand = taskNumber.split("\\.");
        try {
            int i = Integer.parseInt(parseCommand[commandIndex]);
            if (i <= this.tasks.size()) {
                this.tasks.get(--i).execute(parseCommand, ++commandIndex);
            } else {
                error();
            }
        } catch (NumberFormatException | IndexOutOfBoundsException exc) {
            error();
        }
    }
}
