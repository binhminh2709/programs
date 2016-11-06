package ru.epatko.arraymerger;

import ru.epatko.bubblesort.Creator;
import ru.epatko.bubblesort.Sorter;
import ru.epatko.bubblesort.Printer;

public class ArrayMerger{

	public static void main (String[] args){

		int[] arrayA = new int[5];
		int[] arrayB = new int[6];
		int[] arrayC = new int[arrayA.length + arrayB.length];

		Creator creator = new Creator();
		Sorter sorter = new Sorter();
		Printer printer = new Printer();
		
		creator.create(arrayA);
		System.out.print("Unsorted arrayA: ");
		printer.print(arrayA);
		
		sorter.sort(arrayA);
		System.out.print("Sorted arrayA: ");
		printer.print(arrayA);
		
		creator.create(arrayB);
		System.out.print("Unsorted arrayB: ");
		printer.print(arrayB);

		sorter.sort(arrayB);
		System.out.print("Sorted arrayB: ");
		printer.print(arrayB);

		Copier copier = new Copier();
		copier.copy(arrayA, arrayB, arrayC);
		System.out.print("Merge arrayA and arrayB to arrayC (unsorted): ");
		printer.print(arrayC);

		sorter.sort(arrayC);
		System.out.print("Sorted arrayC: ");
		printer.print(arrayC);
	}
}




