package ru.epatko.triangle;

public class Triangle {
	public Point a;
	public Point b;
	public Point c;

	public Triangle(Point a, Point b, Point c) {
	this.a = a;
	this.b = b;
	this.c = c;
	}

	public double area() {										//calculate the triangle area

		double sideAB = this.a.distanceTo(b);					//AB side distance
		double sideBC = this.b.distanceTo(c);					//BC side distance
		double sideCA = this.c.distanceTo(a);					//CA side distance

		if (!((sideAB + sideBC) > sideCA
		  &&  (sideBC + sideCA) > sideAB
		  &&  (sideCA + sideAB) > sideBC) ){

			System.out.println("These points don't form a triangle!");
		}
		else {
			double semiperimeter = (sideAB + sideBC + sideCA)/2;	//semiperimeter

			return Math.sqrt(semiperimeter*(semiperimeter - sideAB)*(semiperimeter - sideBC)*(semiperimeter - sideCA));	
		}
	return -1;
	}
}


		