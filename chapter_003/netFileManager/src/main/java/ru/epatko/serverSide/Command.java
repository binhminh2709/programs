package ru.epatko.serverSide;

import java.util.Scanner;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         13.01.17.
 */
public class Command {
    /**
     * Server action name.
     */
    private String action;
    /**
     * Server action parameter.
     */
    private String param;

    /**
     * constructor.
     * @param userMessage - user message.
     */
    public Command(String userMessage) {
        Scanner scanner = new Scanner(userMessage);
        if (scanner.hasNext()) {
            this.action = scanner.next();
        }
        if (scanner.hasNext()) {
            this.param = scanner.next();
        } else {
            this.param = "./";
        }
    }
    /**
     * Getter.
     * @return - server action name.
     */
    public String getAction() {
       return this.action;
    }

    /**
     * Getter.
     * @return - server action parameter.
     */
    public String getParam() {
        return this.param;
    }
}
