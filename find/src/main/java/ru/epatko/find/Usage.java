package ru.epatko.find;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         07.01.17.
 */
public class Usage {
    /**
     * Print help and exit program.
     */
    static void help() {
        System.out.print("Incorrect argument(s). Use next command syntax:  ");
        System.out.println("java -jar find.jar -d [START DIRECTORY] -n [FILE NAME] -{m, f, r} -o [LOG FILE]");
        System.out.println();
        System.out.println("-d [START DIRECTORY]         - start directory name;");
        System.out.println("-n [FILE NAME]               - file name to search;");
        System.out.println("-{m, f, r}                   - find options: m - by mask (example: txt, exe),");
        System.out.print(" f - by file name (example: file.txt), r - by regular expression;");
        System.out.println("-o [LOG FILE]                - log file name.");
        System.exit(0);
    }
}
