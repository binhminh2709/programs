package ru.epatko.gameguess;

public class GuessGame {
	Player p1;
	Player p2;
	Player p3;
	
	public void startGame(){
		p1 = new Player();
		p2 = new Player();
		p3 = new Player();
		
		int guessp1 = 0;
		int guessp2 = 0;
		int guessp3 = 0;
				
		int targetNumber = (int) (Math.random() * 10);
		System.out.println("The target Number is: " + targetNumber);
		
		while(true) {
			p1.guess();
			p2.guess();
			p3.guess();
			
			guessp1 = p1.number;
			guessp2 = p2.number;
			guessp3 = p3.number;
			
			System.out.println("\nThe Player-1 think that the number is: " + guessp1);
			System.out.println("The Player-2 think that the number is: " + guessp2);
			System.out.println("The Player-3 think that the number is: " + guessp3);
			
			if(guessp1 == targetNumber){
				System.out.println("The Player-1 is win!!!!");
				System.out.println("End Game");
				break;
			}
			if(guessp2 == targetNumber){
				System.out.println("The Player-2 is win!!!!");
				System.out.println("End Game");
				break;
			}
			if(guessp3 == targetNumber){
				System.out.println("The Player-3 is win!!!!");
				System.out.println("\nEnd Game");
				break;
			}
			
			System.out.println("\nNobody wins!\nPlayers may try again...\n");
			
		}		
	}
}
