package pieces;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import main.GamePanel;

public class Bishop extends Piece {
	
	public Bishop(Piece[][] p_currentBoard, int p_currentRow, int p_currentCol, String p_colour) {
		this.currentBoard =  p_currentBoard;
		
		this.title = "bishop";
		this.currentRow =  p_currentRow;
		this.currentCol = p_currentCol;
		this.colour = p_colour;
		
		loadPieceIcon(p_colour + "_bishop");
	}
	
	public List<Point> findLegalMoves() {
		List<Point> legalMoves = new ArrayList<>();
		int boardSize = 8;
		
	    checkDirection(legalMoves, -1, -1, boardSize); // Check top-left diagonal
	    checkDirection(legalMoves, -1, 1, boardSize); // Check top-right diagonal
	    checkDirection(legalMoves, 1, -1, boardSize); // Check bottom-left diagonal
	    checkDirection(legalMoves, 1, 1, boardSize); // Check bottom-right diagonal
        
        return legalMoves;
	}
}
