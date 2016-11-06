package ru.epatko.bubblesort;

public class Printer {
	void print(int[] array){

		for(int element : array){
			System.out.print(element + ", ");
		}

		// Return carriage on two symbols (", "), print there two spaces and go to the new line.
		System.out.print("\b\b  \n"); 	
	}
}