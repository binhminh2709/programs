package ru.epatko.taskMenu;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         04.02.17.
 */
public class TaskTwoOneOne implements Task {
    /**
     * Task name.
     * @return - task name.
     */
    @Override
    public String name() {
        return "2.1.1. TaskTwoOneOne";
    }

    /**
     * Info about task.
     * @return - info about task.
     */
    @Override
    public String info() {
        return String.format("      %s - execute TaskTwoOneOne.%s",
                             this.name(), System.getProperty("line.separator"));
    }

    /**
     * Execute task.
     */
    @Override
    public void execute(String[] command, int index) {
        int count = command.length - 1;
        if (index > count && "1".equals(command[count])) {
            System.out.println("Executed TaskTwoOneOne.");
        } else {
            System.out.println("Incorrect input.");
        }
    }
}
