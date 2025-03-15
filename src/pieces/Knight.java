package pieces;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import gameLogic.ChessLogic;
import main.GamePanel;

public class Knight extends Piece {
	
	int[][] possibilities = { 
		{-1, 2},			{1, 2},
	{-2,1},						{2, 1},
			
	{-2,-1},					{2, -1},
		{-1,-2},			{1,-2}					
	
	};
	
	public Knight(Piece[][] p_currentBoard, int p_currentRow, int p_currentCol, String p_colour) {
		this.currentBoard =  p_currentBoard;
		
		this.title = "knight";
		this.currentRow =  p_currentRow;
		this.currentCol = p_currentCol;
		this.colour = p_colour;
		
		loadPieceIcon(p_colour + "_knight");
	}
	
	public List<Point> findLegalMoves() {
		List<Point> legalMoves = new ArrayList<>();
		
		for (int[] pair : possibilities) {
			int col = currentCol + pair[0];
			int row = currentRow + pair[1];
			
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
