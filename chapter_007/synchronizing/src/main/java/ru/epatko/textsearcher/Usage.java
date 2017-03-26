package ru.epatko.textsearcher;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *        23.03.17.
 */
public class Usage {
    /**
     * Print help and exit program.
     */
    static void help() {
        System.out.print("Incorrect argument(s). Use next command:  ");
        System.out.println("java -jar find.jar [text to search]");
        System.out.println("To find first match use command:          java -jar find.jar -fm [text to search]");
        System.exit(0);
    }
}
