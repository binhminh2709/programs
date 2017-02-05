package ru.epatko.taskMenu;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         04.02.17.
 */
public class TaskTwoOneTwo implements Task {
    /**
     * Task name.
     * @return - task name.
     */
    @Override
    public String name() {
        return "2.1.2. TaskTwoOneTwo";
    }

    /**
     * Info about task.
     * @return - info about task.
     */
    @Override
    public String info() {
        return String.format("      %s - execute TaskTwoOneTwo.%s",
                             this.name(), System.getProperty("line.separator"));
    }

    /**
     * Execute task.
     */
    @Override
    public void execute(String[] command, int index) {
        int counter = command.length - 1;
        if (index > counter && "2".equals(command[counter])) {
            System.out.println("Executed TaskTwoOneTwo.");
        } else {
            System.out.println("Incorrect input.");
        }
    }
}
