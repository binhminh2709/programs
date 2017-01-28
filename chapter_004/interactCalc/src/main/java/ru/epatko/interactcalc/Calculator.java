package ru.epatko.interactcalc;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         24.01.17.
 */

public class Calculator {

	/**
	 * Operation result.
	 */
	protected double result;

	/**
	 * Addition operation.
	 * @param a - first value.
	 * @param b - second value.
	 */
	public void add(double a, double b) {
		this.result = a + b;
	}
			
	/**
	 * Subtraction operation.
	 * @param a - first value.
	 * @param b - second value.
	 */
	public void sub(double a, double b) {
		this.result = a - b;
	}
		
	/**
	 * Multiplication operation.
	 * @param a - first value.
	 * @param b - second value.
	 */
	public void mult(double a, double b) {
		this.result = a * b;
	}
	
	/**
	 * Division operation.
	 * @param a - first value.
	 * @param b - second value.
	 */
	public void div(double a, double b) {
		this.result = a / b;
	}

	/**
	 * Getter.
	 * @return - operation result.
	 */
	public double getResult() {
		return result;
	}
}