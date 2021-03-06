package ru.epatko.bubblesorter;

/**
* Sorting array
*/

public class BubbleSorter {
	
	/**
	* @param array - unsorted array
	*/
	void sort(int[] array) {
		
		int tmp;

		for(int i = array.length - 1; i >= 0; i--) {
			
			for(int j = 0; j <= i-1; j++) {
				
				if(array[j] > array[j+1]) {
					tmp = array[j];
					array[j] = array[j+1];
					array[j+1] = tmp;
				}
			}
		}
	} 
}