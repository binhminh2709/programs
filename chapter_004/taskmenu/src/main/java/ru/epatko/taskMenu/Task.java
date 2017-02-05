package ru.epatko.taskMenu;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         04.02.17.
 */
public interface Task {
    /**
     * Task name.
     * @return - task name.
     */
    String name();

    /**
     * Info about task.
     * @return - info about task.
     */
    String info();

    /**
     * Execute task.
     * @param command - array command to execute.
     * @param commandIndex - command index to execute.
     */
    void execute(String[] command, int commandIndex);

}
