package pieces;

import java.awt.Point;

import main.GamePanel;

public class Knight extends Piece {
	
	int[][] possibilities = { 
		{-1, 2},			{1, 2},
	{-2,1},						{2, 1},
			
	{-2,-1},					{2, -1},
		{-1,-2},			{1,-2}					
	
	};
	
	public Knight(GamePanel p_gp, Point p_startingPos, String p_colour) {
		super(p_gp);
		
		this.title = "knight";
		this.currentPos = p_startingPos;
		this.colour = p_colour;
		
		loadPieceIcon(p_colour + "_knight");
	}
	
	
	public void findPossibleMoves() {
		allPossibleMovesList.clear();
		
		for (int[] pair : possibilities) {
			int col = currentPos.x + pair[0];
            int row = currentPos.y + pair[1];
            

            // Check if the row and column indexes are valid
            if (row >= 0 && row < gp.getMaxScreenRow() && col >= 0 && col < gp.getMaxScreenCol()) {
            	if(gp.getBoard().checkTileForPiece(col, row) != null){
            		if(gp.getBoard().checkTileForPiece(col, row).getColour() != colour){
                    	allPossibleMovesList.add(new Point(col, row));
                    }
                }
            	else{
            		allPossibleMovesList.add(new Point(col, row));
                }
            }
        }
	}
	
	
	public boolean canKingCastle() {
		return false;
	}
	
	public boolean isKingInDanger() {
		return false;
	}
	
	
}
