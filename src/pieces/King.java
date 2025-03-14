package pieces;

import java.awt.Point;

import main.GamePanel;

public class King extends Piece {
	
	int[][] offsets = {
			{-1, -1}, {-1, 0}, {-1, 1},
	        {0, -1},           {0, 1},
	        {1, -1},  {1, 0},  {1, 1}
	};
	
	public King(GamePanel p_gp, int p_currentRow, int p_currentCol, String p_colour) {
		super(p_gp);
		
		this.title = "king";
		this.currentRow =  p_currentRow;
		this.currentCol = p_currentCol;
		this.colour = p_colour;
		
		loadPieceIcon(p_colour + "_king");
	}
	
	public void findPossibleMoves() {
		allPossibleMovesList.clear();
		
		for (int[] offset : offsets) {
			int col = currentCol + offset[0];
			int row = currentRow + offset[1];
			
            if (row >= 0 && row < gp.getMaxScreenRow() && col >= 0 && col < gp.getMaxScreenCol()) {
            	if(gp.getBoard().checkTileForPiece(row, col) != null){
                    if(gp.getBoard().checkTileForPiece(row, col).getColour() != colour){
                        allPossibleMovesList.add(new Point(col, row));
                    }
                }
                else{
                	allPossibleMovesList.add(new Point(col, row));
                }
            }
		}
	}
	
	
	
	
}
