package gameLogic;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import pieces.King;
import pieces.Piece;


public class ChessLogic {

	public List<Piece> getAttackingPieces(Piece[][] board, boolean playerIsWhite) {
		List<Piece> allPiecesList = new ArrayList<>();
		
		for (int row = 0; row < board.length; row++) {
	        for (int col = 0; col < board[row].length; col++) {
	            Piece piece = board[row][col];
	            if (piece != null && piece.isWhite() == playerIsWhite && piece.getAllPossibleMovesList().size() > 0) {
	            	allPiecesList.add(piece);
	            }
	        }
	    }
		
		return allPiecesList;
	}
	
	public Set<Point> findOppositionAttackingTilesForAllMoves(Piece[][] board, boolean oppositePlayerColour) {
		Set<Point> allAttackableTilesList = new HashSet<>();
		
		for (int row = 0; row < board.length; row++) {
	        for (int col = 0; col < board[row].length; col++) {
	            Piece piece = board[row][col];
	            if (piece != null && piece.isWhite() == oppositePlayerColour) {
	            	allAttackableTilesList.addAll(piece.getAllPossibleMovesList());
	            }
	        }
	    }
		
		return allAttackableTilesList;
	}
	
	public Piece findKing(Piece[][] board, boolean currentPlayerIsWhite) {
	    for (int row = 0; row < board.length; row++) {
	        for (int col = 0; col < board[row].length; col++) {
	            Piece piece = board[row][col];
	            if (piece != null && piece instanceof King && piece.isWhite() == currentPlayerIsWhite) {
	                return piece;
	            }
	        }
	    }
	    return null;  // Should never happen in a valid game
	}
	
	public boolean isKingInDanger(Piece[][] board, boolean currentPlayerIsWhite) {
		//Find the King
		Piece king = findKing(board, currentPlayerIsWhite);
		if(king == null) {return true;}
		
		
		//Check for Attacks from opponent on King
		for (int row = 0; row < board.length; row++) {
	        for (int col = 0; col < board[row].length; col++) {
	            Piece piece = board[row][col];
	            if (piece != null && piece.isWhite() != currentPlayerIsWhite) {
	            	if(piece.getAllPossibleMovesList().contains(new Point(king.getCurrentPosCol(), king.getCurrentPosRow()))){
	            		numberOfAttackers++;
	            	}
	            }
	        }
	    }
		
		if(numberOfAttackers > 0) {
			return true;
		}
		
		return false;
	}
	
	public boolean canKingEscape(Piece[][] board, Piece king, boolean currentPlayerIsWhite) {
		Set<Point> allTilesOpponentCanAttack = findAllOppositionAttackingTiles(board, !currentPlayerIsWhite);
		
		for(Point movePos : king.getAllPossibleMovesList()) {
			if(!allTilesOpponentCanAttack.contains(new Point(movePos.x, movePos.y))) {
				return true;
			}
		}
		
		
		return false;
	}
	
	public boolean canBlockOrCaptureAttacker(Piece[][] board, Piece king, boolean currentPlayerIsWhite) {
	    if(numberOfAttackers > 1) {
	        return false;  // If there are multiple attackers, only the king can move.
	    }
		
	    attacker = attackers[0]  // Single attacker
	
	    for each piece in board:
	        if piece belongs to currentPlayer:
	            legalMoves = piece.getLegalMoves(board)
	            if attacker.position in legalMoves:
	                return true  // A piece can capture the attacker
	
	            if piece is not a knight and can move to block attack path:
	                return true  // A piece can block the attack
	
	    return false;  // No piece can block or capture
		
	}
	
	public boolean checkForCheckmate(Piece[][] board, boolean currentPlayerIsWhite) {
		numberOfAttackers = 0;
		
		if(!isKingInDanger(board, currentPlayerIsWhite)) {
			return false; // King is not in check, so it's not checkmate
		}
		
		Piece king = findKing(board, currentPlayerIsWhite);
		
		if(canKingEscape(board, king, currentPlayerIsWhite)) {
			return false;  // King has a way out
		}
        

	    if(canBlockOrCaptureAttacker(board, king, currentPlayerIsWhite)) {
	        return false;  // A piece can block or capture the attacker
	    }

        return true;  // No escape, it's checkmate
	}
}
