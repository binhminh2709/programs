package ru.epatko.factorial;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import org.apache.commons.io.output.ByteArrayOutputStream;
import java.io.PrintStream;



public class FactorialTest{

	@Test
	public void whenInputNumberThenGetResult(){

		Factorial factorial = new Factorial();
		assertEquals(6, factorial.calculate(3));
	}
}