package pieces;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import main.GamePanel;

public class Queen extends Piece {
	
	public Queen(Piece[][] p_currentBoard, int p_currentRow, int p_currentCol, String p_colour) {
		this.currentBoard =  p_currentBoard;
		
		this.title = "queen";
		this.currentRow =  p_currentRow;
		this.currentCol = p_currentCol;
		this.colour = p_colour;
		this.pieceValue = 900;
		
		loadPieceIcon(p_colour + "_queen");
	}
	
	public List<Point> findLegalMoves() {
		List<Point> legalMoves = new ArrayList<>();
		int boardSize = 8;
		
		checkDirection(legalMoves, 1, 0, boardSize);  // Right (same row)
	    checkDirection(legalMoves, -1, 0, boardSize); // Left (same row)
	    checkDirection(legalMoves, 0, -1, boardSize); // Up (same column)
	    checkDirection(legalMoves, 0, 1, boardSize);  // Down (same column)
	    
	    checkDirection(legalMoves, -1, -1, boardSize); // Check top-left diagonal
	    checkDirection(legalMoves, -1, 1, boardSize); // Check top-right diagonal
	    checkDirection(legalMoves, 1, -1, boardSize); // Check bottom-left diagonal
	    checkDirection(legalMoves, 1, 1, boardSize); // Check bottom-right diagonal
        
        return legalMoves;
	}
}
