package pieces;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import main.GamePanel;

public class Rook extends Piece {
	
	public Rook(Piece[][] p_currentBoard, int p_currentRow, int p_currentCol, String p_colour) {
		this.currentBoard =  p_currentBoard;
		
		this.title = "rook";
		this.currentRow =  p_currentRow;
		this.currentCol = p_currentCol;
		this.colour = p_colour;
		this.pieceValue = 500;
		
		loadPieceIcon(p_colour + "_rook");
	}
	
	public List<Point> findLegalMoves() {
		List<Point> legalMoves = new ArrayList<>();
		int boardSize = 8;
		
		checkDirection(legalMoves, 1, 0, boardSize);  // Right (same row)
	    checkDirection(legalMoves, -1, 0, boardSize); // Left (same row)
	    checkDirection(legalMoves, 0, -1, boardSize); // Up (same column)
	    checkDirection(legalMoves, 0, 1, boardSize);  // Down (same column)
        
        return legalMoves;
	}
	
	
}
