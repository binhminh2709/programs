package ru.epatko.rotatematrix;

public class Printer {
	void print(int[][] array){

		int arrayLength = array.length;
		System.out.print("\n");

		for(int i = 0; i < arrayLength; i++){
			for(int j = 0; j < arrayLength; j++){
				System.out.printf("%2d ", array[i][j]);
			}
			System.out.print("\n");			
		}
	}
}