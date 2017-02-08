package ru.epatko.taskMenu;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         04.02.17.
 */
public class Task {
    /**
     * Subtasks arraylist.
     */
    private ArrayList<Task> subTasks = new ArrayList<>();

    /**
     * Task name.
     */
    private String number;

    /**
     * Parsed task name.
     */
    private String[] parsedNumber;

    /**
     * Constructor.
     * @param number - task number.
     */
    public Task(String number) {
        this.number = number;
        this.parsedNumber = number.split("\\.");
    }

     /**
     * Info about task.
     * @return - info about task.
     */

    public String info() {
        StringBuilder info = new StringBuilder();
        info.append(String.format("Task %s.", this.number)).append(System.getProperty("line.separator"));
        if (!this.subTasks.isEmpty()) {
            this.subTasks.forEach((subTask) -> {
                for (int i = 0; i < parsedNumber.length; i++) {
                    info.append("   ");
                }
                info.append(subTask.info());
            });
        }
        return info.toString();
    }

    /**
     * Get subtasks arraylist.
     * @return - subtasks arraylist.
     */
    public ArrayList<Task> getSubTasks() {
        return this.subTasks;
    }

    /**
     * Add subtask to arraylist.
     * @param subTask - subtask.
     */

    public void addSubTask(Task subTask) {
        this.subTasks.add(subTask);
    }

    /**
     * Execute task.
     * @param command - command.
     * @param commandIndex - command index.
     * @throws NumberFormatException - exception.
     * @throws IndexOutOfBoundsException - exception.
     */
    public void execute(String[] command, int commandIndex) throws NumberFormatException, IndexOutOfBoundsException {

        if (Arrays.equals(command, this.parsedNumber)) {
            System.out.printf("Executed Task %s.%s", this.number, System.getProperty("line.separator"));
        } else if (!this.subTasks.isEmpty()) {
            int nextCommand = Integer.parseInt(command[commandIndex]);
            this.subTasks.get(--nextCommand).execute(command, ++commandIndex);
        } else {
            System.out.println("Incorrect input.");
        }
    }
}
