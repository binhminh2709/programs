package ru.epatko.taskMenu;

import java.util.ArrayList;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         04.02.17.
 */
public interface AdvancedTask extends Task {

    /**
     * Subtasks arraylist.
     * @return - subtasks arraylist.
     */
    ArrayList<Task> getSubTasks();

    /**
     * Add subtask to arraylist.
     * @param subTask - subtask.
     */
    void addSubTask(Task subTask);
}
