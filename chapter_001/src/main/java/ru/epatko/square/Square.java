package ru.epatko.square;
/*
1. Создать класс Square в нем один методы float calculate(int x)
 и void show(start, finish, step).
 Метод должен вычислять значение функции y = a*x2 + b * x + c;
 параметры a b c задаются через конструктор.
 void show(start, finish, step) - вывести на экран значение функции в диапазоне
  от x1 до x2 c шагом step. 
2. Обязательно нужно добавить тесты проверяющие методы.
*/

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