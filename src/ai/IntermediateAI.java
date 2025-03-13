package ai;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import gameLogic.Move;
import main.GamePanel;
import pieces.Piece;

public class IntermediateAI {

	//Uses Minimax algorithm.
	
	GamePanel gp;
	List<Piece> currentMoveablePieces = new ArrayList<>();
	
	public IntermediateAI(GamePanel p_gp) {
		this.gp = p_gp;
	}
	
	
	public int evaluateBoard(Piece[][] board, boolean isPlayingWhite) {
		int score = 0;
		
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				Piece piece = board[i][j];
				if(piece == null) {continue;}
				
				int value = 0;
				switch(piece.getTitle()) {
					case "pawn":
						value = 100;
							
						//Center control (d4,e4,d5,e5) 2x2
						if(i >= 3 && i <= 4 && j >= 3 && j <= 4) {value += 10;}
						//Backrows of their opponent
						else if((j == 0 & isPlayingWhite) || (j == 7 && !isPlayingWhite)) {value += 5;}
						//Prior to backrow
						else if((j == 1 & isPlayingWhite) || (j == 6 && !isPlayingWhite)) {value += 3;}
						
					case "knight":
						value = 310;
							
						//Center control 4x4
						if(i >= 2 && i <= 5 && j >= 2 && j <= 5) {value += 3;}
						
					case "bishop":
						value = 330;
						
					case "rook":
						value = 500;
						
					case "queen":
						value = 900;
						
					case "king":
						value = 5000;
				}
				
				if (piece.getColour() == "white" && isPlayingWhite) {
	                score += value;
	            } else {
	                score -= value;
	            }
				
			}
		}
		
		
		return score; //If score is negative favours black whilst positive favours white
	}
	
	
	
	public ArrayList<Move> generateMoves(Piece[][] board, boolean isWhiteTurn) {
		ArrayList<Move> moves = new ArrayList<>();
		
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				Piece piece = board[i][j];
				if(piece == null || (piece.getColour() == "white" && !isWhiteTurn) || (piece.getColour() == "black" && isWhiteTurn) ) {continue;}
			
				for(Point movePointer : piece.getAllPossibleMovesList()) {
					moves.add(new Move(piece.getCurrentPos().x, piece.getCurrentPos().y, movePointer.x, movePointer.y, piece, gp.getBoard().checkTileForPiece(movePointer.x, movePointer.y)));
				}
			}
		}
		
		return moves;
	}
	
	public int minimax(Piece[][] board, int depth, int alpha, int beta, boolean isMaximising) {
	    if (depth == 0 || isGameOver(board)) {
	        return evaluateBoard(board, true);
	    }

	    if (isMaximising) {  // Maximising player's turn (typically the player playing white)
	        int maxEval = Integer.MIN_VALUE;
	        for (char[][] newBoard : generateMoves(board, true)) {
	            int eval = minimax(newBoard, depth - 1, alpha, beta, false);
	            maxEval = Math.max(maxEval, eval);
	            alpha = Math.max(alpha, eval); // Update alpha

	            if (beta <= alpha) {  // Prune remaining branches
	                break;
	            }
	        }
	        return maxEval;

	    } 
	    else {  // Minimising player's turn (typically the player playing black)
	        int minEval = Integer.MAX_VALUE;
	        for (char[][] newBoard : generatePossibleMoves(board, false)) {
	            int eval = minimax(newBoard, depth - 1, alpha, beta, true);
	            minEval = Math.min(minEval, eval);
	            beta = Math.min(beta, eval); // Update beta

	            if (beta <= alpha) {  // Prune remaining branches
	                break;
	            }
	        }
	        return minEval;
	    }
		
		
		return 0;
	}
	
}
