package ru.epatko.simpleGenerator;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         05.02.17.
 */
public class SimpleGeneratorTest {
    @Test
    public void whenSetTextAndMapThenGetFormattedText() {

        SimpleGenerator generator = new SimpleGenerator();
        String source = "111 ${key1} 333 ${key2}";
        String result = "111 222 333 444";
        Map<String, String> map = new HashMap<>();
        map.put("${key1}", "222");
        map.put("${key2}", "444");

        assertThat(generator.generate(source, map), is(result));
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void whenSetEnoughKeysThenGetSimpleGeneratorException() throws SimpleGenerator.SimpleGeneratorException {

        expectedException.expect(SimpleGenerator.SimpleGeneratorException.class);
        SimpleGenerator generator = new SimpleGenerator();
        String source = "111 ${key1} 333 ${key2} ${key3}";
        Map<String, String> map = new HashMap<>();
        map.put("${key1}", "222");
        map.put("${key2}", "444");

        generator.generate(source, map);
    }

    @Test
    public void whenSetTooMachKeysThenGetSimpleGeneratorException() throws SimpleGenerator.SimpleGeneratorException {

        expectedException.expect(SimpleGenerator.SimpleGeneratorException.class);
        SimpleGenerator generator = new SimpleGenerator();
        String source = "111 ${key1} 333 ${key2}";
        Map<String, String> map = new HashMap<>();
        map.put("${key1}", "222");
        map.put("${key2}", "444");
        map.put("${key3}", "555");

        generator.generate(source, map);
    }
}