package pieces;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import gameLogic.ChessLogic;
import main.GamePanel;

public class King extends Piece {
	
	int[][] offsets = {
			{-1, -1}, {-1, 0}, {-1, 1},
	        {0, -1},           {0, 1},
	        {1, -1},  {1, 0},  {1, 1}
	};
	
	public King(Piece[][] p_currentBoard, int p_currentRow, int p_currentCol, String p_colour) {
		this.currentBoard =  p_currentBoard;
		
		this.title = "king";
		this.currentRow =  p_currentRow;
		this.currentCol = p_currentCol;
		this.colour = p_colour;
		this.pieceValue = 5000;
		
		loadPieceIcon(p_colour + "_king");
	}
	
	public List<Point> findLegalMoves() {
		List<Point> legalMoves = new ArrayList<>();
		
		for (int[] offset : offsets) {
			int col = currentCol + offset[0];
			int row = currentRow + offset[1];
			
            if (row >= 0 && row < 8 && col >= 0 && col < 8) {
            	Piece tilePiece = ChessLogic.checkTileForPiece(row, col, currentBoard);
            	
            	if(tilePiece == null) {
            		legalMoves.add(new Point(col, row));
            	}
            	else {
            		if(tilePiece.getColour() != colour){
            			legalMoves.add(new Point(col, row));
            		}
                }	
            }
            
		}
        
        return legalMoves;
	}	
	
}
