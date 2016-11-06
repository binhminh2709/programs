package ru.epatko.rotatematrix;

public class RotateMatrix{

	public static void main(String[] args){

		int arrayLength = 4;
		int[][] array = new int[arrayLength][arrayLength];
		
		Creator creator = new Creator();
		creator.create(array);
		
		Printer printer  = new Printer();
		// Print array
		printer.print(array);
		
		Rotator rotator = new Rotator();
		rotator.rotate(array);
		
		// Print rotated array
		printer.print(array);

	}
}