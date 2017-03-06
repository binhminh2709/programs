package ru.epatko.gc;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.TreeMap;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         06.03.17.
 */
public class MyCacheTest {
    public static final String LS = System.getProperty("line.separator");

    @Test
    public void whenRunMyCacheThenCanGetFileNamesDotTxtContent() throws IOException {

        MyCache mc = new MyCache("./sourceFolder");
        String result = Joiner.on(LS).join("AAA AAA AAA",
                                           "BBB BBB BBB",
                                           "CCC CCC CCC","");

        StringBuilder sb = mc.showFile("Names.txt");

        assertThat(sb.toString(), is(result));
    }

    @Test
    public void whenRunMyCacheThenCanGetFileAddressesDotTxtContentFromCache() throws IOException {

        MyCache mc = new MyCache("./sourceFolder");
        String result = Joiner.on(LS).join("DDDDDDDDDDDDDD",
                                           "EEEEEEEEEEEEEE",
                                           "FFFFFFFFFFFFFF", "");

        StringBuilder sb = mc.showFile("Addresses.txt");
        TreeMap<String, SoftReference<StringBuilder>> cache = mc.getCache();

        assertThat(cache.containsKey("Addresses.txt"), is(true));
        assertThat(sb.toString(), is(result));
    }
}