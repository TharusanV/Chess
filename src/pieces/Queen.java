package pieces;

import java.awt.Point;

import main.GamePanel;

public class Queen extends Piece {
	
	public Queen(GamePanel p_gp, Point p_startingPos, String p_colour) {
		super(p_gp);
		
		this.title = "queen";
		this.currentPos = p_startingPos;
		this.colour = p_colour;
		
		loadPieceIcon(p_colour + "_queen");
	}
	
	public void findPossibleMoves() {
		allPossibleMovesList.clear();
		
        // check all possible moves in the top-left diagonal
        for (int i = 1; currentPos.y-i >= 0 && currentPos.x-i >= 0; i++) {
            if (gp.getBoard().checkTileForPiece(currentPos.x-i, currentPos.y-i)  == null) { // empty square
                allPossibleMovesList.add(new Point(currentPos.x-i, currentPos.y-i));
            } 
            else if (gp.getBoard().checkTileForPiece(currentPos.x-i, currentPos.y-i).getColour() != colour) { // capture opponent's piece
                allPossibleMovesList.add(new Point(currentPos.x-i, currentPos.y-i));
                break; // can't move further in this direction
            } 
            else { // own piece blocking the way
                break; // can't move further in this direction
            }
        }
        // check all possible moves in the top-right diagonal
        for (int i = 1; currentPos.y-i >= 0 && currentPos.x+i < 8; i++) {
            if (gp.getBoard().checkTileForPiece(currentPos.x+i, currentPos.y-i) == null) { // empty square
                allPossibleMovesList.add(new Point(currentPos.x+i, currentPos.y-i));
            } 
            else if (gp.getBoard().checkTileForPiece(currentPos.x+i, currentPos.y-i).getColour() != colour) { // capture opponent's piece
                allPossibleMovesList.add(new Point(currentPos.x+i, currentPos.y-i));
                break; // can't move further in this direction
            } 
            else { // own piece blocking the way
                break; // can't move further in this direction
            }
        }
        // check all possible moves in the bottom-left diagonal
        for (int i = 1; currentPos.y+i < 8 && currentPos.x-i >= 0; i++) {
            if (gp.getBoard().checkTileForPiece(currentPos.x-i, currentPos.y+i) == null) { // empty square
                allPossibleMovesList.add(new Point(currentPos.x-i, currentPos.y+i));
            } 
            else if (gp.getBoard().checkTileForPiece(currentPos.x-i, currentPos.y+i).getColour() != colour) { // capture opponent's piece
                allPossibleMovesList.add(new Point(currentPos.x-i, currentPos.y+i));
                break; // can't move further in this direction
            } 
            else { // own piece blocking the way
                break; // can't move further in this direction
            }
        }
        // check all possible moves in the bottom-right diagonal
        for (int i = 1; currentPos.y+i < 8 && currentPos.x+i < 8; i++) {
            if (gp.getBoard().checkTileForPiece(currentPos.x+i, currentPos.y+i) == null) { // empty square
                allPossibleMovesList.add(new Point(currentPos.x+i, currentPos.y+i));
            } 
            else if (gp.getBoard().checkTileForPiece(currentPos.x+i, currentPos.y+i).getColour() != colour) { // capture opponent's piece
                allPossibleMovesList.add(new Point(currentPos.x+i, currentPos.y+i));
                break; // can't move further in this direction
            } 
            else { // own piece blocking the way
                break; // can't move further in this direction
            }
        }

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
