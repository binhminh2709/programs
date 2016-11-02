package ru.epatko.triangle;

public class MaxSide{

	//double maxDistance;

	public double max(double sideAB, double sideBC, double sideCA){

		double maxDistance = sideAB;
		if (maxDistance  < sideBC){
			maxDistance = sideBC;
		}
		if (maxDistance < sideCA){
			maxDistance = sideCA;
		  }
	return maxDistance;
	}
}