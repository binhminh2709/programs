package ru.epatko.arraymerger;
/**
*Copy array A and array B to array C.
*/
public class Copier{
	/**
	*
	* @param arrayA  - source array
	* @param arrayB - source array
	* @param arrayC - destination array
	*/

	public void copy (int[] arrayA, int[] arrayB, int[] arrayC){
		
		int lengthArrayA = arrayA.length;
		for(int i = 0; i < lengthArrayA; i++){
			arrayC[i] = arrayA[i];
		}

		int lengthArrayB = arrayB.length;
		for(int i = 0; i < lengthArrayB; i++){
			arrayC[i + lengthArrayA] = arrayB[i];
		}
	}
	
}
