package ru.epatko.square;

public class Square{

	private float b;
	private float c;
	private float a;

	public Square(float a, float b, float c){
		this.a = a;
		this.b = b;
		this.c = c;
	}

/**
* Clculate quadratic equation 
*/
	float calculate(int x){
		return (float) (this.a * Math.pow(x, 2) + this.b * x + this.c);
	}

/**
* Calculate quadratic equation whith a specified step
*/

	void show (int start, int finish, int step){

		for(start=start; start <= finish; start += step){
			System.out.print(calculate(start)+" ");
		}

	}







}