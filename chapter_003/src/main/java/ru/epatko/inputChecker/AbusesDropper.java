package ru.epatko.inputChecker;

import java.io.*;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         12.12.16.
 */
public class AbusesDropper {

    /**
     * Drop abuses.
     * @param in input stream.
     * @param out output stream.
     * @param abuses array of abuses.
     * @throws IOException
     */
    public void dropAbuses(InputStream in, OutputStream out, String[] abuses) throws IOException {
        /**
         * Strig without abuses.
         */
        String result;

        try (BufferedReader input = new BufferedReader(new InputStreamReader(in));
                                        PrintStream printer = new PrintStream(out)) {

            while ((result = input.readLine()) != null) {

                for (String abuse : abuses) {
                    if (result.contains(abuse)) {
                        result = result.replace(abuse, "\"BAD_WORD\"");
                    }
                }
                printer.print(result);
            }
        }
    }
}