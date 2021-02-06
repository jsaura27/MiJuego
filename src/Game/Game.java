package Game;
import java.util.Scanner;
import Utilities.*;
import AI.*;

public class Game {
	public static int player1;
	public static int player2;
	
	public static void print(Board reversi){
		for (int j = 0; j < reversi.Board.length; j++) {
			System.out.print(j+1 + " | ");
			for (int i = 0; i < reversi.Board.length ; i++) {

		        System.out.print(reversi.Board[i][j] + " ");
		    }
			System.out.print("|");
		    System.out.println();
		}
		System.out.println("    a b c d e f g h  ");
	}
	
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		String color;
		int thinkTime;
		AIPlayer machine;
		Player human;
		
		System.out.println("Welcome to Reversi game!");
		do{
			System.out.println("Choose your color[White]/[Black]");
			color = reader.next();
		} while(!(color.equals("White") || color.equals("Black")));
		
		System.out.println("Colors have been chosen");
		
		do{
			System.out.println("Choose how many seconds the algorithm will have to \"think\" (min 1)");
			thinkTime = reader.nextInt();
		}while(thinkTime < 1);
		
		if(color.equals("White")) {
			human = new Player(Board.White);
			machine =  new AIPlayer(Board.Black,thinkTime);
		} else {
			human = new Player(Board.Black);
			machine = new AIPlayer(Board.White,thinkTime);
		}
		
		Board reversi = new Board();
		reversi.startGame();
		print(reversi);
		
		
		do{
			
			if(human.getColor() == Board.Black) {
				human.move(reversi);
			} else {
				machine.move(reversi);
			}
			
			print(reversi);
			
			if(human.getColor() == Board.Black) {
				machine.move(reversi);

			} else {
				human.move(reversi);
			}
			
			print(reversi);
			
		
			
		}while(reversi.isThereMoves(human.getColor()) || reversi.isThereMoves(machine.getColor()));
		
		System.out.println("The game is finished");
		int count = 0;
		for(int i = 0; i < reversi.Board.length; i++){
			for(int j = 0; j < reversi.Board.length; j++){
				if(reversi.Board[i][j] == human.getColor())
					count++;
				else
					count--;
			}
		}
		
		if(count == 0){
			System.out.println("The game was a draw.");
		}
		else{
			if(count > 0){
				System.out.println("Congratulations!! You win.");
			}
			else{
				System.out.println("Sorry, you lose.");
			}
		}
		
		reader.close();
		System.out.println("Thank you for playing!");
	}





}
