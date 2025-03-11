package pieces;

import java.awt.Point;

import main.GamePanel;

public class Rook extends Piece {
	
	public Rook(GamePanel p_gp, Point p_startingPos, String p_colour) {
		super(p_gp);
		
		this.currentPos = p_startingPos;
		this.colour = p_colour;
		
		loadPieceIcon(p_colour + "_rook");
	}
	
	public void findPossibleMoves() {
		allPossibleMovesList.clear();
		
		// Check moves in the same row to the right
        for(int col = currentPos.x + 1; col < 8; col++){
            if (gp.getBoard().checkTileForPiece(col, currentPos.y) == null){
            	allPossibleMovesList.add(new Point(col, currentPos.y));
            }
            else if (gp.getBoard().checkTileForPiece(col, currentPos.y) != null && gp.getBoard().checkTileForPiece(col, currentPos.y).getColour() != colour){
            	allPossibleMovesList.add(new Point(col, currentPos.y));
                break;
            }
            else{
                break;
            }
        }

        // Check moves in the same row to the left
        for (int col = currentPos.x - 1; col >= 0; col--) {
            if (gp.getBoard().checkTileForPiece(col, currentPos.y) == null){
            	allPossibleMovesList.add(new Point(col, currentPos.y));
            }
            else if (gp.getBoard().checkTileForPiece(col, currentPos.y) != null && gp.getBoard().checkTileForPiece(col, currentPos.y).getColour() != colour){
            	allPossibleMovesList.add(new Point(col, currentPos.y));
                break;
            }
            else{
                break;
            }
        }

        // Check moves in the same column upwards
        for (int row = currentPos.y - 1; row >= 0; row--) {
            if (gp.getBoard().checkTileForPiece(currentPos.x, row) == null){
            	allPossibleMovesList.add(new Point(currentPos.x, row));
            }
            else if (gp.getBoard().checkTileForPiece(currentPos.x, row) != null && gp.getBoard().checkTileForPiece(currentPos.x, row).getColour() != colour){
            	allPossibleMovesList.add(new Point(currentPos.x, row));
                break;
            }
            else{
                break;
            }
        }

        for (int row = currentPos.y + 1; row < 8; row++) {
            if (gp.getBoard().checkTileForPiece(currentPos.x, row) == null){
            	allPossibleMovesList.add(new Point(currentPos.x, row));
            }
            else if (gp.getBoard().checkTileForPiece(currentPos.x, row) != null && gp.getBoard().checkTileForPiece(currentPos.x, row).getColour() != colour){
            	allPossibleMovesList.add(new Point(currentPos.x, row));
                break;
            }
            else{
                break;
            }
        }
	}
}
