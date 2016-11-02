package ru.epatko.calculator;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CalculatorTest {

	@Test
	public void whenGiveTwoNumbersThenGetTheirSum() {
		Calculator calc = new Calculator();
		calc.add (10, 8);
		assertEquals(18, calc.getResult(), 1e-3);	
	}


/*
	@Test
	public void whenGiveTwoNumbersThenCantGetUnrightSum() {
		Calculator calc = new Calculator();
		calc.add (10.0, 8.0);
		assertEquals(8.0, calc.getResult(), 1e-3);	
	}
*/	
	@Test
	public void whenGiveTwoNumbersThenGetTheirSubtraction() {
		Calculator calc = new Calculator();
		calc.sub (10, 8);
		assertEquals(2, calc.getResult(), 1e-3);	
	}
	
	@Test
	public void when_Give_Two_Numbers_Then_Get_Their_Multiplication() {
		Calculator calc = new Calculator();
		calc.mult (10, 8);
		assertEquals(80, calc.getResult(), 1e-3);	
	}
	
	@Test
	public void when_Give_Two_Numbers_Then_Get_Their_Division() {
		Calculator calc = new Calculator();
		calc.div (10, 8);
		assertEquals(1.25, calc.getResult(), 1e-3);	
	}
	
	@Test
	public void when_Call_GetResult_Then_Get_Zero() {
		Calculator calc = new Calculator();
		assertEquals(0, calc.getResult(), 1e-3);	
	}

}
