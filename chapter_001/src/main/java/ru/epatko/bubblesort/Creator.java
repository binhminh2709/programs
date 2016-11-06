package ru.epatko.bubblesort;

public class Creator {
	public void create(int[] array){

		for (int k = 0; k < array.length; k++){
			array[k] = (int)(Math.random()*100);
		}
	}
}