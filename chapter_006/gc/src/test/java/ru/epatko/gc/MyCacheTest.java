package ru.epatko.gc;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         06.03.17.
 */
public class MyCacheTest {
    public static final String LS = System.getProperty("line.separator");

    @Test
    public void whenRunMyCacheThenCanGetFileFromCache() throws IOException {

        MyCache mc = new MyCache("./sourceFolder");
        String result = Joiner.on(LS).join("AAA AAA AAA",
                                           "BBB BBB BBB",
                                           "CCC CCC CCC","");

        String s1 = mc.get("Names.txt");
        assertThat(s1, is(result));

        String s2 = mc.get("Names.txt");
        assertThat(s2, is(result));
    }
}