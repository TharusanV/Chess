package pieces;

import java.awt.Point;

import main.GamePanel;

public class Pawn extends Piece {

	int startingPosRow;
	
	public Pawn(GamePanel p_gp, Point p_startingPos, String p_colour) {
		super(p_gp);
		
		this.currentPos = p_startingPos;
		this.colour = p_colour;
		
		this.startingPosRow = p_startingPos.y;
	}

	public void findPossibleMoves() {
		allPossibleMovesList.clear();
		int moveDirection = colour == "White" ? -1 : 1;
		
		//Straight
		if(gp.getBoard().checkTileForPiece(currentPos.x, currentPos.y + moveDirection) == null) {
			allPossibleMovesList.add(new Point(currentPos.x, currentPos.y + moveDirection));
			//Check if they can move two points forward
			if(currentPos.y != startingPosRow) {
				if(gp.getBoard().checkTileForPiece(currentPos.x, currentPos.y + moveDirection * 2) == null) {
					allPossibleMovesList.add(new Point(currentPos.x, currentPos.y + moveDirection * 2));
				}
			}
		}
		
		//Possible diagonal (left White / right Black)
		
		
		
		
	}
	
}
