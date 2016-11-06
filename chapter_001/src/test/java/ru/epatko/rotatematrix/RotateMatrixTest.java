package ru.epatko.rotatematrix;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import org.apache.commons.io.output.ByteArrayOutputStream;
import java.io.PrintStream;

public class RotateMatrixTest{

	@Test
	public void wenGiveArrayLengthThenGivArray(){

		int[][] array = new int[3][3];

		Creator creator = new Creator();
		creator.create(array);
		assertEquals(4, array[1][1]);
	}


	@Test
	public void wenGiveArrayThenGetRotatedArray(){

		int[][] array = {{0,1}, {2,3}};

		Rotator rotator = new Rotator();
		rotator.rotate(array);
		assertEquals(3, array[0][1]);
	}

	@Test
	public void wenGiveArrayThenPrintArray(){

		ByteArrayOutputStream output = new ByteArrayOutputStream();
		System.setOut (new PrintStream(output));
		
		Printer printer = new Printer();
		int[][] array = {{1,2}, {3,4}};
		printer.print(array);
		assertThat(output.toString(), is("\n 1  2 \n 3  4 \n"));
	}
}