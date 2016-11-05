package ru.epatko.bubblesort;

public class BubbleSort{

	public static void main(String[] args){

		int[] array = new int[10];
		
		createArray(array);
		printArray(array);
		
		bubbleSort(array);
		printArray(array);

	}

	public static void createArray(int[] array){

		for (int k = 0; k < array.length; k++){
			array[k] = (int)(Math.random()*100);
		}
	}

	public static void bubbleSort(int[] array){
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
	} 

	public static void printArray(int[] array){

		for(int element : array){
			System.out.print(element + ", ");
		}

		// Return carriage on two symbols (", "), print there two spaces and go to the new line.
		System.out.print("\b\b  \n"); 		
	}
}