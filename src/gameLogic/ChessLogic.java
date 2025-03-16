package gameLogic;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import main.GamePanel;
import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Piece;
import pieces.Queen;
import pieces.Rook;


public class ChessLogic {
	
	public static Piece checkTileForPiece(int row, int col, Piece[][] board) {
		if(board[row][col] != null) {
			return board[row][col];
		}
		
		return null;
	}
	
	public static List<Point> getPathBetween(Piece attacked, Piece attacker) {
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
	
	
	public static List<Piece> findMoveablePieces(Piece[][] board, boolean playerIsWhite) {
		List<Piece> allPiecesList = new ArrayList<>();
		
		for (int row = 0; row < board.length; row++) {
	        for (int col = 0; col < board[row].length; col++) {
	            Piece pieceTile = board[row][col];
	            if (pieceTile == null) { continue; }
	            
	            if (pieceTile.isWhite() == playerIsWhite && pieceTile.findLegalMoves().size() > 0) {
	            	allPiecesList.add(pieceTile);
	            }
	        }
	    }
		
		return allPiecesList;
	}
	
	public static List<Piece> findAttackingPiecesOnKing(Piece[][] board, Piece king) {
	    List<Piece> allPiecesList = new ArrayList<>();

	    Point kingPoint = new Point(king.getCurrentPosCol(),king.getCurrentPosRow());
	    
	    for (int row = 0; row < board.length; row++) {
	        for (int col = 0; col < board[row].length; col++) {
	            Piece piece = board[row][col];
	            if (piece == null) { continue;}
	            
	            List<Point> movePos = piece.findLegalMoves();         
	            
	            if (piece.isWhite() != king.isWhite() && movePos.contains(kingPoint)) {
	            	allPiecesList.add(piece);
	            }
	            
	        }
	    }

	    return allPiecesList;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void movePiece(int startCol, int startRow, int newCol, int newRow, Piece[][] currentBoard, Stack<Move> moveHistory) {		
		Piece movingPiece = checkTileForPiece(startRow, startCol, currentBoard);
        Piece capturedPiece = checkTileForPiece(newRow, newCol, currentBoard);
		
        if (capturedPiece != null && capturedPiece.getColour() == movingPiece.getColour()) {
            return;
        }
        
        Move move = new Move(startCol, startRow, newCol, newRow, movingPiece, capturedPiece);
        moveHistory.push(move);
        currentBoard[newRow][newCol] =  movingPiece;
        currentBoard[startRow][startCol] = null;
        
        movingPiece.setCurrentRowAndPos(newRow, newCol);
        //capturedPiece.setCurrentRowAndPos(-1, -1); 


	}
	
	
	
	public static void undoLastMove(Piece[][] currentBoard, Stack<Move> moveHistory) {
        if (moveHistory.isEmpty()) return;

        Move lastMove = moveHistory.pop();
        
        // Restore moving piece to its original position
        currentBoard[lastMove.getOriginalRow()][lastMove.getOriginalCol()] = lastMove.getMovingPiece();
        lastMove.getMovingPiece().setCurrentRowAndPos(lastMove.getOriginalRow(), lastMove.getOriginalCol());

        // Restore captured piece (if any)
        if (lastMove.getCapturedPiece() != null) {
            currentBoard[lastMove.getNowRow()][lastMove.getNowCol()] = lastMove.getCapturedPiece();
            lastMove.getCapturedPiece().setCurrentRowAndPos(lastMove.getNowRow(), lastMove.getNowCol());
        } 
        else {
            currentBoard[lastMove.getNowRow()][lastMove.getNowCol()] = null; // Clear if no piece was captured
        }
    }

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static Piece findKing(Piece[][] board, boolean currentPlayerIsWhite) {
	    for (int row = 0; row < board.length; row++) {
	        for (int col = 0; col < board[row].length; col++) {
	            Piece piece = board[row][col];
	            if (piece == null) { continue;}
	            
	            if (piece instanceof King && piece.isWhite() == currentPlayerIsWhite) {
	                return piece;
	            }
	        }
	    }
	    return null;  // Should never happen in a valid game
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static boolean isKingInDanger(Piece[][] board, boolean currentPlayerIsWhite) {
		//Find the King
		Piece king = findKing(board, currentPlayerIsWhite);
		if(king == null) {return true;}
		
		List<Piece> attackers = findAttackingPiecesOnKing(board, king);
		
		//System.out.println(attackers.size());
		
		//Check for Attacks from opponent on King
		if(attackers.size() > 0) {
			return true;
		}
		
		return false;
	}
	
	public static boolean canKingEscape(Piece[][] board, Piece king, boolean currentPlayerIsWhite) {
		List<Piece> attackers = findAttackingPiecesOnKing(board, king);
		
		List<Point> kingMoves = king.findLegalMoves();
		
		//System.out.println(kingMoves.size());
		
		for (int i = kingMoves.size() - 1; i >= 0; i--) {
		    Point potentialKingMove = kingMoves.get(i);
		    
		    //System.out.println(potentialKingMove);
		    
		    for (Piece attacker : attackers) {
		    	if(attacker.findLegalMoves().contains(new Point(potentialKingMove.x, potentialKingMove.y))) {
		    		kingMoves.remove(i);
		    	}
		    }
		}
		
		if(kingMoves.size() > 0) {
			return true;
		}
		
		return false;
	}
	
	public static boolean canBlockOrCaptureAttacker(Piece[][] board, Piece king, boolean currentPlayerIsWhite) {
		List<Piece> attackers = findAttackingPiecesOnKing(board, king);
		
		if(attackers.size() > 1) {
	        return false;  // If there are multiple attackers, only the king can move.
	    }
		
	    Piece attacker = attackers.get(0);  // Single attacker
	    
	    List<Piece> playersMoveablePieces = findMoveablePieces(board, currentPlayerIsWhite);
	    
	    for(Piece playerPiece : playersMoveablePieces) {
	    	if(playerPiece instanceof King) {continue;}
	    	
	    	if(playerPiece.findLegalMoves().contains(new Point(attacker.getCurrentPosCol(),attacker.getCurrentPosRow()))) {
	    		
	    		return true;  // A piece can capture the attacker
	    	}
	    	
	    	if (attacker instanceof Rook || attacker instanceof Bishop || attacker instanceof Queen) {
	            List<Point> attackPath = getPathBetween(king, attacker);  // Get all squares in attack path

	            for (Point square : attackPath) {
	                if (playerPiece.findLegalMoves().contains(square)) {
	                    return true;  // A piece can block the attack
	                }
	            }
	        }
	    }
	    
	    return false;  // No piece can block or capture
		
	}
	
	public static boolean checkForCheckmate(Piece[][] board, boolean currentPlayerIsWhite) {		
		if(isKingInDanger(board, currentPlayerIsWhite) == false) {
			//System.out.println("a");
			return false; // King is not in check, so it's not checkmate
		}
		
		Piece king = findKing(board, currentPlayerIsWhite);
		
		if(canKingEscape(board, king, currentPlayerIsWhite)) {
			//System.out.println("b");
			return false;  // King has a way out
		}
        

	    if(canBlockOrCaptureAttacker(board, king, currentPlayerIsWhite)) {
	    	//System.out.println("c");
	        return false;  // A piece can block or capture the attacker
	    }

        return true;  // No escape, it's checkmate
	}

	
	public static boolean isGameOver(Piece[][] board, boolean isPlayingWhite) {
		// 1. Checkmate
	    if (checkForCheckmate(board, isPlayingWhite)) {
	        return true;
	    }
	    /*
	    // 2. Stalemate
	    if (isStalemate(board, isPlayingWhite)) {
	        return true;
	    }
	
	    // 3. Insufficient Material
	    if (isInsufficientMaterial(board)) {
	        return true;
	    }
	
	    // 4. Threefold Repetition (Requires tracking previous board states)
	    if (isThreefoldRepetition()) {
	        return true;
	    }
	
	    // 5. 50-Move Rule (Requires tracking move history)
	    if (isFiftyMoveRule()) {
	        return true;
	    }
	    */
	
	    return false;  // Game is still ongoing
	}
}
