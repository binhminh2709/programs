package ru.epatko.matrixrotator;

/**
* Rotate square array from right to left
*/

public class MatrixRotator {
	
	/**
	* @param array - square array[n][n]
	*/
	void rotate(int[][] array) {
		
		int n = array.length;
		int tmp;

		for (int i = 0; i < n / 2; i++){
			
			for (int j = i; j < (n - 1 - i); j++){
				
				tmp = array[i][j];
				array[i][j] = array[j][n - 1 - i];
				array[j][n - 1 - i] = array[n - 1 - i][n - 1 - j];
				array[n - 1 - i][n - 1- j] = array[n - 1 - j][i];
				array[n - 1 - j][i] = tmp;
			}
		}
	} 
}