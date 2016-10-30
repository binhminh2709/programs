package ru.epatko;

import java.util.Scanner;

public class Raven {
	
	public static void main(String[] argv){
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Введите число ворон на ветке: ");
		
		int raven = sc.nextInt();
		int raven1 = raven % 10;
		int raven2 = raven % 100;
		String appendix = "";
		
		if (raven2 >= 11 && raven2 <= 14)			
			appendix = "";		
		else {
			  switch(raven1) {			
			  case 1: appendix = "а"; break;
			  case 2: ;
			  case 3: ;
			  case 4: appendix = "ы"; break;
			  }
		}
	
		System.out.printf("На ветке %d ворон%s.\n", raven, appendix);
		String result = String.format("Точно. На ветке ровно %d ворон%s.", raven, appendix);
		System.out.println(result);
		
		sc.close();
	}
}
