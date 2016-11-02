package ru.epatko.calculator;

public class Calculator {

	private double result;

	public void add(double a, double b) {   // method addition operation
		this.result = a + b;
	}
			
	public void sub(double a, double b) {   // method subtraction operation
		this.result = a - b;
	}
		
	public void mult(double a, double b) {   // method multiplication operation
		this.result = a * b;
	}
	
	public void div(double a, double b) {   // method division operation
		this.result = a / b;
	}
	
	public double getResult(){
		return this.result;
	}
}