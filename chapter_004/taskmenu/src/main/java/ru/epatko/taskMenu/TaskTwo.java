package ru.epatko.taskMenu;

import java.util.ArrayList;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         04.02.17.
 */
public class TaskTwo implements AdvancedTask {

    /**
     * Subtasks arraylist.
     */
    private ArrayList<Task> subTasks = new ArrayList<>();

    /**
     * Task name.
     * @return - task name.
     */
    @Override
    public String name() {
        return "2. TaskTwo";
    }

    /**
     * Info about task.
     * @return - info about task.
     */
    @Override
    public String info() {
        StringBuilder info = new StringBuilder();
        info.append(String.format("%s:",
                this.name())).append(System.getProperty("line.separator"));
        this.subTasks.forEach((subTask) -> info.append(subTask.info()));
        return info.toString();
    }

    /**
     * Get subtasks arraylist.
     * @return - subtasks arraylist.
     */
    @Override
    public ArrayList<Task> getSubTasks() {
        return this.subTasks;
    }

    /**
     * Add subtask to arraylist.
     * @param subTask - subtask.
     */
    @Override
    public void addSubTask(Task subTask) {
        this.subTasks.add(subTask);
    }

    /**
     * Execute task.
     */
    @Override
    public void execute(String[] command, int commandIndex) {

        int subTaskIndex = Integer.parseInt(command[commandIndex]);
        if (subTaskIndex <= subTasks.size()) {
            this.subTasks.get(--subTaskIndex).execute(command, ++commandIndex);
        } else {
            System.out.println("Incorrect input.");
        }
    }
}
