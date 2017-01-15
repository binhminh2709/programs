package ru.epatko.searcher;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         15.01.17.
 */
public class Validator {

    /**
     * Print help.
     */
    private Usage usage = new Usage();
    /**
     * String pattern to compare.
     */
    private String pattern = null;

    /**
     * Check arguments, print help and make string pattern to compare.
     * @param args - user arguments.
     * @return - string pattern to compare.
     */
    public String isBad(String[] args) {

        if (args.length != 7 || !"-d".equals(args[0]) || !"-n".equals(args[2]) || !"-o".equals(args[5])) {
            this.usage.help();
        }
            if ("-m".equals(args[4])) {
            if (args[3].contains("*") || args[3].contains(".")) {
                this.usage.help();
            } else {
                this.pattern = String.format(".*\\.%s$", args[3]);
            }
        } else if ("-f".equals(args[4])) {
                this.pattern = String.format("^%s$", args[3]);

        } else if ("-r".equals(args[4])) {
                this.pattern = args[3];

        } else {
                this.usage.help();
        }
        return this.pattern;
    }
}
