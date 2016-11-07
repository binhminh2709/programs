package ru.epatko.arraymerger;

public class ArrayMerger {
	
	/**
	* @param arrayA  - first source array
	* @param arrayB - second source array
	* @param arrayC - destination array
	*/

	void merge (int[] arrayA, int[] arrayB, int[] arrayC) {
		
		/**
		* Copy arrayA and arrayB to arrayC
		*/
		int lengthArrayA = arrayA.length;
		for(int i = 0; i < lengthArrayA; i++) {
			arrayC[i] = arrayA[i];
		}

		int lengthArrayB = arrayB.length;
		for(int i = 0; i < lengthArrayB; i++) {
			arrayC[i + lengthArrayA] = arrayB[i];
		}	
		
		/**
		* Sort arrayC
		*/
		int tmp;

		for(int i = arrayC.length - 1; i >= 0; i--) {
			
			for(int j = 0; j <= i-1; j++) {
				
				if(arrayC[j] > arrayC[j+1]) {
					tmp = arrayC[j];
					arrayC[j] = arrayC[j+1];
					arrayC[j+1] = tmp;
				}
			}
		}
	}	
}
