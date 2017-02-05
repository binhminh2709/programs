package ru.epatko.taskMenu;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         04.02.17.
 */
public class TaskThree implements Task {
    /**
     * Task name.
     * @return - task name.
     */
    @Override
    public String name() {
        return "3. TaskThree";
    }

    /**
     * Info about task.
     * @return - info about task.
     */
    @Override
    public String info() {
        return String.format("%s - execute TaskThree.%s", this.name(), System.getProperty("line.separator"));
    }

    /**
     * Execute task.
     */
    @Override
    public void execute(String[] command, int index) {
        int counter = command.length - 1;
        if (index > counter && "3".equals(command[counter])) {
            System.out.println("Executed TaskThree.");
        } else {
            System.out.println("Incorrect input.");
        }
    }
}
