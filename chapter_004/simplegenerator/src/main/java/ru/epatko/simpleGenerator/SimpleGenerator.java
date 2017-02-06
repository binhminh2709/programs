package ru.epatko.simpleGenerator;

import java.util.*;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         05.02.17.
 */
public class SimpleGenerator {

    /**
     *
     * @param text - source text.
     * @param map - keys map.
     * @return - formatted text.
     */
    public String generate(String text, Map<String, String> map) {
        /**
         * Match counter.
         */
        int counter = 0;
        /**
         * Map size.
         */
        int mapSize = map.size();

        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String pattern = key.replace("${", "\\$\\{").replace("}", "\\}");
            if (text.contains(key)) {
                counter++;
                text = text.replaceAll(pattern, map.get(key));
            }
        }
        if (text.contains("${")) {
            throw new SimpleGeneratorException("The map don't contains enough keys.");
        }
        if (counter < mapSize) {
            throw new SimpleGeneratorException("The map contains to mach keys.");
        }
        return text;
    }

    /**
     * Exception.
     */
    public class SimpleGeneratorException extends RuntimeException {
        /**
         * Exception.
         * @param message - exception message.
         */
        public SimpleGeneratorException(String message) {
            super(message);
        }
    }
}
