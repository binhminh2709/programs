package ru.epatko.maxnumbersearcher;

/**
* Max number searcher
* @author Mikhail Epatko
*/
public class MaxNumberSearcher{

	private double maxNumber;

/**
* Search max double number
* @param numbers - a few double numbers
*/	
	public double searchMaxNumber (double ... numbers){

		this.maxNumber = numbers[0];

		for (int i = 1; i < numbers.length; ++i) {
			if (maxNumber < numbers[i]) {
				this.maxNumber = numbers[i];
			}
		}

	return this.maxNumber;
	}
}