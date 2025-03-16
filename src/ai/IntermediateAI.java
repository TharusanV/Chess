package ai;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import gameLogic.ChessLogic;
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

	    for (int i = 0; i < board.length; i++) {
	        for (int j = 0; j < board[i].length; j++) {
	            Piece piece = board[i][j];
	            if (piece == null) { continue; }

	            int value = 0;
	            switch (piece.getTitle()) {
	                case "pawn":
	                    value = 100;
	                    // Center control (d4, e4, d5, e5)
	                    if (i >= 3 && i <= 4 && j >= 3 && j <= 4) { value += 10; }
	                    // Back rows of their opponent
	                    else if ((j == 0 && isPlayingWhite) || (j == 7 && !isPlayingWhite)) { value += 5; }
	                    // Prior to back row
	                    else if ((j == 1 && isPlayingWhite) || (j == 6 && !isPlayingWhite)) { value += 3; }
	                    break;

	                case "knight":
	                    value = 310;
	                    // Center control (4x4)
	                    if (i >= 2 && i <= 5 && j >= 2 && j <= 5) { value += 3; }
	                    break;

	                case "bishop":
	                    value = 330;
	                    break;

	                case "rook":
	                    value = 500;
	                    break;

	                case "queen":
	                    value = 900;
	                    break;

	                case "king":
	                    value = 5000;
	                    break;
	            }

	            if (piece.getColour().equals("white")) {
	                score += (isPlayingWhite) ? value : -value;
	            } 
	            else {
	                score += (isPlayingWhite) ? -value : value;
	            }
	        }
	    }
	    return score;
	}

		
	
	public int minimax(Piece[][] board, int depth, int alpha, int beta, boolean isMaximizing, boolean isPlayingWhite, Stack<Move> moveHistory) {
	    // Base case: if depth is 0 or game over, return the board evaluation
	    if (depth == 0 || ChessLogic.isGameOver(board, isPlayingWhite)) {
	        return evaluateBoard(board, isPlayingWhite);
	    }
	
	    if (isMaximizing) {  
	        // Maximizing player (AI)
	        int maxEval = Integer.MIN_VALUE;
	        
	        for (Piece piece : ChessLogic.findMoveablePieces(board, isPlayingWhite)) {
	        	for(Point movePos : piece.findLegalMoves()) {
	        		ChessLogic.movePiece(piece.getCurrentPosCol(), piece.getCurrentPosRow(), movePos.x, movePos.y, board, moveHistory, isPlayingWhite);
	        		int eval = minimax(board, depth - 1, alpha, beta, false, isPlayingWhite, moveHistory);
	        		ChessLogic.undoLastMove(board, moveHistory, isPlayingWhite);  // Undo move
		            
		            maxEval = Math.max(maxEval, eval);
		            alpha = Math.max(alpha, eval);
		            if (beta <= alpha) break;  // Alpha-Beta Pruning
	        		
	        	}
	        	
	        }
	        
	        return maxEval;
	    } 
	    else {  
	        // Minimizing player (Opponent)
	        int minEval = Integer.MAX_VALUE;
	        
	        for (Piece piece : ChessLogic.findMoveablePieces(board, !isPlayingWhite)) {
	        	for(Point movePos : piece.findLegalMoves()) {
	        		ChessLogic.movePiece(piece.getCurrentPosCol(), piece.getCurrentPosRow(), movePos.x, movePos.y, board, moveHistory, !isPlayingWhite);
	        		int eval = minimax(board, depth - 1, alpha, beta, true, isPlayingWhite, moveHistory);
		            ChessLogic.undoLastMove(board, moveHistory, !isPlayingWhite);  // Undo move
		            
		            minEval = Math.min(minEval, eval);
		            beta = Math.min(beta, eval);
		            if (beta <= alpha) break;  // Alpha-Beta Pruning
	        	}
	        }
	        	
	        return minEval;
	    }
	}

	
	
}
