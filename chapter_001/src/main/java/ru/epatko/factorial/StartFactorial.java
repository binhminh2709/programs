package ru.epatko.factorial;

import java.util.Scanner;

public class StartFactorial{
	
	public static void main(String [] args){

		Scanner scanner = new Scanner(System.in);
		System.out.print("Input natural number to calculate factorial: ");

		int input = scanner.nextInt();
		
		Factorial factorial = new Factorial();
		
		System.out.printf("%d! = %d\n", input, factorial.calculate(input));
		
		scanner.close();
	}
}