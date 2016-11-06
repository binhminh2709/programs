package ru.epatko.bubblesort;

public class BubbleSort{

	public static void main(String[] args){

		int[] array = new int[10];
		
		Creator creator = new Creator();
		creator.create(array);
		
		Printer printer  = new Printer();
		// Print unsorted array
		printer.print(array);
		
		Sorter sorter = new Sorter();
		sorter.bubbleSort(array);
		
		// Print sorted array
		printer.print(array);

	}
}