package gameLogic;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import main.GamePanel;
import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Piece;
import pieces.Queen;
import pieces.Rook;


public class ChessLogic {

	private GamePanel gp;
	
	public ChessLogic(GamePanel gp) {
		this.gp = gp;
	}
	
	public List<Piece> getMoveablePieces(Piece[][] board, boolean playerIsWhite) {
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
	
	public List<Piece> getAttackingPiecesOnKing(Piece[][] board, boolean playerIsWhite, Piece oppositeKing) {
		List<Piece> allPiecesList = new ArrayList<>();
		
		for (int row = 0; row < board.length; row++) {
	        for (int col = 0; col < board[row].length; col++) {
	            Piece piece = board[row][col];
	            if (piece != null && piece.isWhite() == playerIsWhite && piece.getAllPossibleMovesList().contains(new Point(oppositeKing.getCurrentPosCol(), oppositeKing.getCurrentPosRow()))) {
	            	allPiecesList.add(piece);
	            }
	        }
	    }
		
		return allPiecesList;
	}
	
	public Set<Point> findMoveableTiles(Piece[][] board, boolean playerIsWhite) {
		Set<Point> allAttackableTilesList = new HashSet<>();
		
		for (int row = 0; row < board.length; row++) {
	        for (int col = 0; col < board[row].length; col++) {
	            Piece piece = board[row][col];
	            if (piece != null && piece.isWhite() == playerIsWhite) {
	            	allAttackableTilesList.addAll(piece.getAllPossibleMovesList());
	            }
	        }
	    }
		
		return allAttackableTilesList;
	}
	
	public List<Point> getPathBetween(Piece attacked, Piece attacker) {
	    List<Point> path = new ArrayList<>();
	    
	    int kingRow = attacked.getCurrentPosRow();
	    int kingCol = attacked.getCurrentPosCol();
	    int attRow = attacker.getCurrentPosRow();
	    int attCol = attacker.getCurrentPosCol();

	    int rowStep = Integer.compare(attRow, kingRow);  // -1, 0, or 1
	    int colStep = Integer.compare(attCol, kingCol);  // -1, 0, or 1

	    int row = kingRow + rowStep;
	    int col = kingCol + colStep;

	    while (row != attRow || col != attCol) {
	        path.add(new Point(col, row));
	        row += rowStep;
	        col += colStep;
	    }

	    return path;
	}

	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
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
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/*
	public boolean isCheckAfterMove(Piece[][] board, Move move) {
		Piece[][] tempBoard = board;
		
		tempBoard[move.nowRow][move.nowCol] = move.movingPiece;
		tempBoard[move.originalRow][move.originalCol] = null;
	    
	    if isInCheck(tempBoard) {
	    	return true;
	    }
	        
	    return false;
	}
	*/
	
	
	public boolean isKingInDanger(Piece[][] board, boolean currentPlayerIsWhite) {
		//Find the King
		Piece king = findKing(board, currentPlayerIsWhite);
		if(king == null) {return true;}
		
		
		//Check for Attacks from opponent on King
		if(getAttackingPiecesOnKing(board, !currentPlayerIsWhite ,king).size() > 0) {
			return true;
		}
		
		return false;
	}
	
	public boolean canKingEscape(Piece[][] board, Piece king, boolean currentPlayerIsWhite) {
		List<Piece> attackers = getAttackingPiecesOnKing(board, !currentPlayerIsWhite, king);
		
		List<Point> kingMoves = king.getAllPossibleMovesList();
		
		for (int i = kingMoves.size() - 1; i >= 0; i--) {
		    Point potentialKingMove = kingMoves.get(i);
		    
		    for (Piece attacker : attackers) {
		    	if(attacker.getAllPossibleMovesList().contains(new Point(potentialKingMove.x, potentialKingMove.y))) {
		    		kingMoves.remove(i);
		    	}
		    }
		}
		
		king.setAllPossibleMoves(kingMoves);
		
		if(kingMoves.size() > 0) {
			return true;
		}
		
	
		return false;
	}
	
	public boolean canBlockOrCaptureAttacker(Piece[][] board, Piece king, boolean currentPlayerIsWhite) {
		List<Piece> attackers = getAttackingPiecesOnKing(board, !currentPlayerIsWhite ,king);
		
		if(attackers.size() > 1) {
	        return false;  // If there are multiple attackers, only the king can move.
	    }
		
	    Piece attacker = attackers.get(0);  // Single attacker
	    
	    List<Piece> playersMoveablePieces = getMoveablePieces(board, currentPlayerIsWhite);
	    for(Piece playerPiece : playersMoveablePieces) {
	    	if(playerPiece.getAllPossibleMovesList().contains(new Point(attacker.getCurrentPosCol(),attacker.getCurrentPosRow()))) {
	    		return true;  // A piece can capture the attacker
	    	}
	    	
	    	if (attacker instanceof Rook || attacker instanceof Bishop || attacker instanceof Queen) {
	            List<Point> attackPath = getPathBetween(king, attacker);  // Get all squares in attack path

	            for (Point square : attackPath) {
	                if (playerPiece.getAllPossibleMovesList().contains(square)) {
	                    return true;  // A piece can block the attack
	                }
	            }
	        }
	    }
	    
	    return false;  // No piece can block or capture
		
	}
	
	public boolean checkForCheckmate(Piece[][] board, boolean currentPlayerIsWhite) {		
		if(isKingInDanger(board, currentPlayerIsWhite) == false) {
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
