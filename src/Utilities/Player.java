package Utilities;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    private int color = 0;

    public Player(int color){
    	this.color = color;
    }
    
    public int getColor() {
    	return this.color;
    }

    public void move(Board board) {
		Scanner reader = new Scanner(System.in);
		char X;
		char Y;
		String aux;
		boolean check = true;
		
		
		ArrayList<Coordinates> playerMoves = board.possibleMoves(this.color);
		if(playerMoves.isEmpty()) {
			System.out.println("You have no possible moves, your adversary will continue playing");
		}
		else {
			do{				
				do{
					System.out.println();
					System.out.print("Your possible moves are: ");
					for(Coordinates move: playerMoves) {
						System.out.printf("%c%c ",move.getX(),move.getY());
					}
					System.out.println("Write where to place your piece");

					aux = reader.next();
				}while(aux.length() < 2);
				X = aux.charAt(0); 
				Y = aux.charAt(1);
				for(Coordinates move: playerMoves) {
					if((move.getX() == X) && (move.getY() == Y)) {
						board.move(new Coordinates(X,Y), this.color);
						check = false;
					}
					
				}
			}while(check);
		}
		//reader.close();
    }

}
