package ru.epatko.rotatematrix;

public class Creator {
	void create(int[][] array){

		int arrayLength = array.length;

		for (int i = 0; i < arrayLength; i++){
			for(int j = 0; j < arrayLength; j++){
				array[i][j] = arrayLength * i + j;
			}
		}
	}
}