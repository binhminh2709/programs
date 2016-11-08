package ru.epatko.substringsearcher;

/**
* Search substring in string
* @autor Mikhail Epatko 
*/

public class SubstringSearcher {

	private boolean result = false;
	int count;

	boolean search (String substring, String string) {
		char[] charArraySubstring = substring.toCharArray();
		char[] charArrayString = string.toCharArray();

		for(int i = 0; i < charArrayString.length && (this.result == false); ++i) {

			if(charArraySubstring.length < charArrayString.length) {

				if(charArraySubstring[0] == charArrayString[i]) {
					count = 1;
					
					for(int j = 1; j < charArraySubstring.length; ++j) {
						
						if(charArraySubstring[j] == charArrayString[i+j]) {
							count++;
						} else {
							break;
						}
					}

					if(count == charArraySubstring.length) {
						this.result = true;
					} else {
						count = 0;
					}
				}

			} else {
				break;
			}
		}
		return this.result;
	}
}
