package pieces;

import java.awt.Point;

import main.GamePanel;

public class Queen extends Piece {
	
	public Queen(GamePanel p_gp, int p_currentRow, int p_currentCol, String p_colour) {
		super(p_gp);
		
		this.title = "queen";
		this.currentRow =  p_currentRow;
		this.currentCol = p_currentCol;
		this.colour = p_colour;
		
		loadPieceIcon(p_colour + "_queen");
	}
	
	public void findPossibleMoves() {
		allPossibleMovesList.clear();
		
        // check all possible moves in the top-left diagonal
        for (int i = 1; currentRow-i >= 0 && currentCol-i >= 0; i++) {
            if (gp.getBoard().checkTileForPiece(currentRow-i, currentCol-i)  == null) { // empty square
                allPossibleMovesList.add(new Point(currentCol-i, currentRow-i));
            } 
            else if (gp.getBoard().checkTileForPiece(currentRow-i, currentCol-i).getColour() != colour) { // capture opponent's piece
                allPossibleMovesList.add(new Point(currentCol-i, currentRow-i));
                break; // can't move further in this direction
            } 
            else { // own piece blocking the way
                break; // can't move further in this direction
            }
        }
        // check all possible moves in the top-right diagonal
        for (int i = 1; currentRow-i >= 0 && currentCol+i < 8; i++) {
            if (gp.getBoard().checkTileForPiece(currentRow-i, currentCol+i) == null) { // empty square
                allPossibleMovesList.add(new Point(currentCol+i, currentRow-i));
            } 
            else if (gp.getBoard().checkTileForPiece(currentRow-i, currentCol+i).getColour() != colour) { // capture opponent's piece
                allPossibleMovesList.add(new Point(currentCol+i, currentRow-i));
                break; // can't move further in this direction
            } 
            else { // own piece blocking the way
                break; // can't move further in this direction
            }
        }
        // check all possible moves in the bottom-left diagonal
        for (int i = 1; currentRow+i < 8 && currentCol-i >= 0; i++) {
            if (gp.getBoard().checkTileForPiece(currentRow+i, currentCol-i) == null) { // empty square
                allPossibleMovesList.add(new Point(currentCol-i, currentRow+i));
            } 
            else if (gp.getBoard().checkTileForPiece(currentRow+i, currentCol-i).getColour() != colour) { // capture opponent's piece
                allPossibleMovesList.add(new Point(currentCol-i, currentRow+i));
                break; // can't move further in this direction
            } 
            else { // own piece blocking the way
                break; // can't move further in this direction
            }
        }
        // check all possible moves in the bottom-right diagonal
        for (int i = 1; currentRow+i < 8 && currentCol+i < 8; i++) {
            if (gp.getBoard().checkTileForPiece(currentRow+i, currentCol+i) == null) { // empty square
                allPossibleMovesList.add(new Point(currentCol+i, currentRow+i));
            } 
            else if (gp.getBoard().checkTileForPiece(currentRow+i, currentCol+i).getColour() != colour) { // capture opponent's piece
                allPossibleMovesList.add(new Point(currentCol+i, currentRow+i));
                break; // can't move further in this direction
            } 
            else { // own piece blocking the way
                break; // can't move further in this direction
            }
        }

        // Check moves in the same row to the right
        for(int col = currentCol + 1; col < 8; col++){
            if (gp.getBoard().checkTileForPiece(currentRow, col) == null){
                allPossibleMovesList.add(new Point(col, currentRow));
            }
            else if (gp.getBoard().checkTileForPiece(currentRow, col) != null && gp.getBoard().checkTileForPiece(currentRow, col).getColour() != colour){
                allPossibleMovesList.add(new Point(col, currentRow));
                break;
            }
            else{
                break;
            }
        }

        // Check moves in the same row to the left
        for (int col = currentCol - 1; col >= 0; col--) {
            if (gp.getBoard().checkTileForPiece(currentRow, col) == null){
                allPossibleMovesList.add(new Point(col, currentRow));
            }
            else if (gp.getBoard().checkTileForPiece(currentRow, col) != null && gp.getBoard().checkTileForPiece(currentRow, col).getColour() != colour){
                allPossibleMovesList.add(new Point(col, currentRow));
                break;
            }
            else{
                break;
            }
        }

        // Check moves in the same column upwards
        for (int row = currentRow - 1; row >= 0; row--) {
            if (gp.getBoard().checkTileForPiece(row, currentCol) == null){
                allPossibleMovesList.add(new Point(currentCol, row));
            }
            else if (gp.getBoard().checkTileForPiece(row, currentCol) != null && gp.getBoard().checkTileForPiece(row, currentCol).getColour() != colour){
                allPossibleMovesList.add(new Point(currentCol, row));
                break;
            }
            else{
                break;
            }
        }

        for (int row = currentRow + 1; row < 8; row++) {
            if (gp.getBoard().checkTileForPiece(row, currentCol) == null){
                allPossibleMovesList.add(new Point(currentCol, row));
            }
            else if (gp.getBoard().checkTileForPiece(row, currentCol) != null && gp.getBoard().checkTileForPiece(row, currentCol).getColour() != colour){
                allPossibleMovesList.add(new Point(currentCol, row));
                break;
            }
            else{
                break;
            }
        }
		
	}
}
