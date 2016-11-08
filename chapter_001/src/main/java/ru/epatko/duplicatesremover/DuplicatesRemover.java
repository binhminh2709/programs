package ru.epatko.duplicatesremover;

/**
* Remove duplicates in string array
* @autor Mikhail Epatko
*/

public class DuplicatesRemover {

	void removeDuplicates (String[] stringArray) {
/**
* Calculate array length
*/
		int length = stringArray.length;
		
/**
* The index of the etalon element of stringArray
*/
		for(int i = 0; i < length; ++i) {
		
/**
* The index of the check element of stringArray
*/
			for(int j = i+1; j < length; ++j) {
				
/**
* Compare etalon element and check element of stringArray
*/
				if(stringArray[i] == stringArray[j]) {
/**
* Make duplicate equal empty string
*/
					stringArray[j] = "";
				}
			}
		}
	}
}