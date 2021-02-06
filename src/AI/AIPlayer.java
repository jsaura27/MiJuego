package AI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.*;

import Utilities.*;

public class AIPlayer {

    private int color = 0;
    private int maxdepth = 5;
    private int oppositeColor = 0;
    private long startTime;
    private long maxTime;
    //Constructor
    public AIPlayer(int color, int time) {
    	this.color = color;
    	//We make sure that maxdepth is even, so it is congruent with our needs (starts in zero)
    	this.maxdepth = 2*this.maxdepth - 1;
    	this.maxTime = time*1000;
    	
    	if(color == Board.White){
    		oppositeColor = Board.Black;
    	}
    	else
    		oppositeColor = Board.White;
    }
    
    public int getColor() {
    	return this.color;
    }

    private int minMax(Board board, int depth, int ref_value) {
    	int checkValue;
    	int color_this_call;
    	boolean min;
    	ArrayList<Integer> values;
    	Board localBoard = new Board();
    	
    	if((System.nanoTime()/1000000 -  startTime) > maxTime){
    		System.out.println("The algorithm ran out of time");
    		return heruistic(board);
    	}
    	
    	if((depth%2) == 1)
    		min = true;
    	else
    		min = false;
    	
    	
    	if(min){
    		color_this_call = oppositeColor;
    		values = new ArrayList<Integer>(Arrays.asList(Integer.MAX_VALUE));
    	}	
    	else{
    		color_this_call = color;
    		values = new ArrayList<Integer>(Arrays.asList(Integer.MIN_VALUE));
    	}
    	ArrayList<Coordinates> moves = board.possibleMoves(color_this_call);
    	

    	//If we have reached the maximum depth, then we are in a min. Check all possible moves and return the minimum 
    	if(depth == this.maxdepth) {
    		if(moves.isEmpty()){
        		return heruistic(board);
        	}
        	values = new ArrayList<Integer>();

    		for(Coordinates nextMove : moves) {
            	localBoard.copyBoard(board);
    			localBoard.move(nextMove,color_this_call);
    			
    			checkValue = heruistic(localBoard);
    			if(ref_value >= checkValue)
    				return Integer.MIN_VALUE;
    			else
    				values.add(checkValue);
    		}
    		return Collections.min(values);
    	}
    	
    	if(moves.isEmpty()){
    		return minMax(board,depth+1,values.get(0));
    	}
    	
    	for(Coordinates nextMove : moves){
        	localBoard.copyBoard(board);
    		localBoard.move(nextMove,color_this_call);
    		
    		//Check if we are in min or max to avoid keeping with the for if needed.
    		if(min){
        		checkValue = minMax(localBoard,depth + 1,Collections.min(values));
    			if(ref_value >= checkValue)
    				return Integer.MIN_VALUE;
    			else
    				values.add(checkValue);
    		}
    		else{
        		checkValue = minMax(localBoard,depth + 1,Collections.max(values));
    			if(ref_value <= checkValue)
    				return Integer.MAX_VALUE;
    			else
    				values.add(checkValue);
    		}
    	}

		//We start with depth of 1 (as 0 is the inizialization, max) , which is min
		if(min)
			return Collections.min(values);
		else
			return Collections.max(values);
    	
    	
    	
    }
    
    private int heruistic(Board board) {
    	int count = 0;
    	int mycolor = 0;
    	
    	for(int i = 0; i < board.Board.length; i++){
    		for(int j = 0; j < board.Board.length; j++){
	    		if(board.Board[i][j] == this.color){
	    			count++;
	    			mycolor++;
	    		}
	    		if(board.Board[i][j] == this.oppositeColor)
	    			count--;
    		}
    	}
    
    	if(count == mycolor)
    		return Integer.MAX_VALUE;
    	else
    		return count;
    }
    
    public void move(Board board) {
    	startTime = System.nanoTime()/1000000;
    	
    	int depth = 0;
    	Board localBoard = new Board();
    	ArrayList<Coordinates> moves = board.possibleMoves(this.color);
    	
    	if(moves.isEmpty()){
    		System.out.println("AI cannot move, human player turn again");
    	}
    	else{
    		ArrayList<Integer> values = new ArrayList<Integer>(Arrays.asList(Integer.MIN_VALUE));
	    	for(Coordinates nextMove : moves){
		       	localBoard.copyBoard(board);
		    	localBoard.move(nextMove,color);
		    	values.add(minMax(localBoard,depth + 1,Collections.max(values)));
		    }
	    	Coordinates bestMove = moves.get(values.indexOf(Collections.max(values))-1);
	    	System.out.println();
	    	System.out.println("The AI move is: " + bestMove.getX() + bestMove.getY());
	    	board.move(bestMove, color);
    	}
    	
    }
}
