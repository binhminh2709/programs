package ru.epatko.testTask;

import org.junit.Test;

import java.nio.file.*;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         23.02.17.
 */
public class ParserTest {


    @Test
    public void when___Then___() {

        System.out.println(Paths.get(System.getProperty("user.dir")));
        System.out.printf("%4d@%6.2f - %4d@%.2f", 57, 100.6f, 56, 99.8);
        System.out.println();
        System.out.printf("%3d@%6.2f - %3d@%.2f", 7, 100f, 6, 199.8);

    }


}