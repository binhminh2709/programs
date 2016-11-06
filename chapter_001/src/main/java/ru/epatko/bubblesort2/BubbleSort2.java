package ru.epatko.bubblesort2;

/**
*This program is in a functional style. :0)
*/

public class BubbleSort2{

	public static void main(String[] args){
	
		// Creating unsorted array	
		int[] array = new int[10];
		for (int k = 0; k < array.length; k++){
			array[k] = (int)(Math.random()*100);
		}

		// Printing unsorted array
		for(int element : array){
			System.out.print(element + ", ");
		}
		     // Return carriage on two symbols (", "),
		     // print there two spaces and go to the new line.
		System.out.print("\b\b  \n");

		// Sorting array
		int tmp;
		for(int i = array.length - 1; i >= 0; i--){
			
			for(int j = 0; j <= i-1; j++){
				
				if(array[j] > array[j+1]){
					tmp = array[j];
					array[j] = array[j+1];
					array[j+1] = tmp;
				}
			}
		}
		
		// Printing unsorted array
		for(int element : array){
			System.out.print(element + ", ");
		}
		     // Return carriage on two symbols (", "),
		     // print there two spaces and go to the new line.
		System.out.print("\b\b  \n");



	}
}