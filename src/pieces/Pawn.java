package pieces;

import java.awt.Point;

import main.GamePanel;

public class Pawn extends Piece {

	int startingPosRow;
	
	public Pawn(GamePanel p_gp, int p_currentRow, int p_currentCol, String p_colour) {
		super(p_gp);
		
		this.title = "pawn";
		this.currentRow =  p_currentRow;
		this.currentCol = p_currentCol;
		this.colour = p_colour;
		
		this.startingPosRow = p_currentRow;
		
		loadPieceIcon(p_colour + "_pawn");
	}

	public void findPossibleMoves() {
		allPossibleMovesList.clear();
		int moveDirection = colour == "white" ? -1 : 1;
		
		//Straight
		if((currentRow + moveDirection >= 0) && (currentRow + moveDirection < 8)) {
			if(gp.getBoard().checkTileForPiece(currentRow + moveDirection, currentCol) == null) {
				allPossibleMovesList.add(new Point(currentCol, currentRow + moveDirection));
				
				//Check if they can move two points forward
				if(currentRow == startingPosRow) {
					if(gp.getBoard().checkTileForPiece(currentRow + moveDirection * 2, currentCol) == null) {
						allPossibleMovesList.add(new Point(currentCol, currentRow + moveDirection * 2));
					}
				}
			}
		}
		
		//Possible diagonal (left White / right Black (flipped left))
		if((currentCol - moveDirection >= 0) && (currentCol - moveDirection < 8) && (currentRow + moveDirection >= 0) && (currentRow + moveDirection < 8)) {
			if(gp.getBoard().checkTileForPiece(currentRow + moveDirection, currentCol - moveDirection) != null) {
				if(gp.getBoard().checkTileForPiece(currentRow + moveDirection, currentCol - moveDirection).getColour() != colour) {
					allPossibleMovesList.add(new Point(currentCol - moveDirection, currentRow + moveDirection));
				}
			}
		}
		
		
		//Possible diagonal (right White / left Black (flipped right))
		if((currentCol + moveDirection >= 0) && (currentCol + moveDirection < 8) && (currentRow + moveDirection >= 0) && (currentRow + moveDirection < 8)) {
			if(gp.getBoard().checkTileForPiece(currentRow + moveDirection, currentCol + moveDirection) != null) {
				if(gp.getBoard().checkTileForPiece(currentRow + moveDirection, currentCol + moveDirection).getColour() != colour) {
					allPossibleMovesList.add(new Point(currentCol + moveDirection, currentRow + moveDirection));
				}
			}
		}
	}
	
}
