package ru.epatko;

public class StartCalc {

	public static void main(String[] args) {
	
		Calculator calc = new Calculator();
		
		// call method addition operation from Calculator and print rezult:
		
		calc.add(7.40, 8.50);
		System.out.printf("7.40 + 8.50 = %.2f\n", calc.getResult());
		
		// call method subtraction operation from Calculator and print rezult:
		
		calc.sub(7.40, 8.50);
		System.out.printf("7.40 - 8.50 = %.2f\n", calc.geteResult());
		
		// call method multiplication operation from Calculator and print rezult:
		
		calc.mult(7.40, 8.50);
		System.out.printf("7.40 * 8.50 = %.2f\n", calc.getResult());
		
		// call method division operation from Calculator and print rezult:
		
		calc.div(7.40, 8.50);
		System.out.printf("7.40 / 8.50 = %.2f\n", calc.getResult());
	}
}
