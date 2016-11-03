package ru.epatko.square;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import org.apache.commons.io.output.ByteArrayOutputStream;
import java.io.PrintStream;



public class SquareTest{

	@Test
	public void wehenGiveParametersThenGetRightCalculateResult(){

		Square newSquare = new Square(1,2,3);
		assertEquals(11, newSquare.calculate(2), 1e-3);
	}

	@Test
	public void wehenRunShowMetodThenPrintOut(){

		ByteArrayOutputStream output = new ByteArrayOutputStream();
		System.setOut (new PrintStream(output));
		Square newSquare = new Square(1,2,3);
		newSquare.show(1,2,1);
		assertThat(output.toString(), is("6.0 11.0 "));
	}


}