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
	                    value = piece.getPieceValue();
	                    // Center control (d4, e4, d5, e5)
	                    if (i >= 3 && i <= 4 && j >= 3 && j <= 4) { value += 10; }
	                    // Back rows of their opponent
	                    else if ((j == 0 && isPlayingWhite) || (j == 7 && !isPlayingWhite)) { value += 5; }
	                    // Prior to back row
	                    else if ((j == 1 && isPlayingWhite) || (j == 6 && !isPlayingWhite)) { value += 3; }
	                    break;

	                case "knight":
	                    value = piece.getPieceValue();
	                    // Center control (4x4)
	                    if (i >= 2 && i <= 5 && j >= 2 && j <= 5) { value += 3; }
	                    break;

	                case "bishop":
	                    value = piece.getPieceValue();
	                    break;

	                case "rook":
	                    value = piece.getPieceValue();
	                    break;

	                case "queen":
	                    value = piece.getPieceValue();
	                    break;

	                case "king":
	                    value = piece.getPieceValue();
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

		
	
	public int minimax(Piece[][] board, int depth, int alpha, int beta, boolean isMaximising, boolean isPlayingWhite, Stack<Move> moveHistory) {
	    // Base case: if depth is 0 or game over, return the board evaluation
		if (depth == 0 || ChessLogic.isGameOver(board, isPlayingWhite)) {
	        return evaluateBoard(board, isPlayingWhite);
	    }
	
	    if (isMaximising) {  
	        int maxEval = Integer.MIN_VALUE;

	        for (Piece piece : ChessLogic.findMoveablePieces(board, isPlayingWhite)) {
	            List<Point> legalMoves = piece.findLegalMoves();
	            
	            legalMoves.sort((move1, move2) -> {
	                Piece captured1 = board[move1.y][move1.x];
	                Piece captured2 = board[move2.y][move2.x];
	                int value1 = (captured1 != null) ? captured1.getPieceValue() : 0;
	                int value2 = (captured2 != null) ? captured2.getPieceValue() : 0;
	                return Integer.compare(value2, value1); // Sort highest value first
	            });

	            for (Point movePos : legalMoves) {
	                ChessLogic.movePiece(piece.getCurrentPosCol(), piece.getCurrentPosRow(), movePos.x, movePos.y, board, moveHistory);
	                
	                int eval = minimax(board, depth - 1, alpha, beta, false, !isPlayingWhite, moveHistory);
	                
	                ChessLogic.undoLastMove(board, moveHistory);
	                
	                maxEval = Math.max(maxEval, eval);
	                alpha = Math.max(alpha, eval);
	                if (beta <= alpha) break;  // Alpha-Beta Pruning
	            }
	        }
	        return maxEval;
	    } 
	    else {  
	        int minEval = Integer.MAX_VALUE;

	        for (Piece piece : ChessLogic.findMoveablePieces(board, !isPlayingWhite)) {
	            for (Point movePos : piece.findLegalMoves()) {
	                ChessLogic.movePiece(piece.getCurrentPosCol(), piece.getCurrentPosRow(), movePos.x, movePos.y, board, moveHistory);
	                
	                int eval = minimax(board, depth - 1, alpha, beta, true, !isPlayingWhite, moveHistory);
	                
	                ChessLogic.undoLastMove(board, moveHistory);
	                
	                minEval = Math.min(minEval, eval);
	                beta = Math.min(beta, eval);
	                if (beta <= alpha) break;  // Alpha-Beta Pruning
	            }
	        }
	        return minEval;
	    }
	}
	
	public Move findBestMove(Piece[][] board, int depth, boolean isPlayingWhite, Stack<Move> moveHistory) {
	    Move bestMove = null;
	    int bestValue = Integer.MIN_VALUE;

	    for (Piece piece : ChessLogic.findMoveablePieces(board, isPlayingWhite)) {
	        for (Point movePos : piece.findLegalMoves()) {
	            ChessLogic.movePiece(piece.getCurrentPosCol(), piece.getCurrentPosRow(), movePos.x, movePos.y, board, moveHistory);
	            
	            int moveValue = minimax(board, depth - 1, Integer.MIN_VALUE, Integer.MAX_VALUE, false, !isPlayingWhite, moveHistory);
	            
	            ChessLogic.undoLastMove(board, moveHistory);
	            
	            if (moveValue > bestValue) {
	                bestValue = moveValue;
	                bestMove = new Move(piece.getCurrentPosCol(), piece.getCurrentPosRow(), movePos.x, movePos.y, piece, null);
	            }
	        }
	    }
	    return bestMove;
	}


	
	
}
