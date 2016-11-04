package ru.epatko.factorial;


public class Factorial{
	
	int calculate (int input){
		int result = 1;
		
		for (int i = 1; i <= input; ++i){
			result *= i; 
		}
		return result;
	}
}