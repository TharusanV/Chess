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
		
		
	}
}
