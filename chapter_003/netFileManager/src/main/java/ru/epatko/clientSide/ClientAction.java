package ru.epatko.clientSide;

import ru.epatko.serverSide.Command;
import java.io.IOException;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         14.01.17.
 */
public interface ClientAction {

    /**
     * Name of action.
     * @return - name.
     */
    String name();

    /**
     * Info.
     * @return - info about action.
     */
    String info();
    /**
     * Action.
     * @param command - user command.
     * @throws IOException - exception.
     * @throws ClassNotFoundException - exception.
     */
    void execute(Command command) throws IOException, ClassNotFoundException;
}
