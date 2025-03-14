package pieces;

import java.awt.Point;

import main.GamePanel;

public class Bishop extends Piece {
	
	public Bishop(GamePanel p_gp, int p_currentRow, int p_currentCol, String p_colour) {
		super(p_gp);
		
		this.title = "bishop";
		this.currentRow =  p_currentRow;
		this.currentCol = p_currentCol;
		this.colour = p_colour;
		
		loadPieceIcon(p_colour + "_bishop");
	}
	
	public void findPossibleMoves() {
		allPossibleMovesList.clear();
		
		// check all possible moves in the top-left diagonal
        for (int i = 1; currentRow - i >= 0 && currentCol - i >= 0; i++) {
        	if(gp.getBoard().checkTileForPiece(currentRow - i, currentCol - i) == null) {
        		allPossibleMovesList.add(new Point(currentCol-i, currentRow-i));
            } 
        	else if (gp.getBoard().checkTileForPiece(currentRow - i, currentCol - i).getColour() != colour) { // capture opponent's piece
        		allPossibleMovesList.add(new Point(currentCol-i, currentRow-i));
                break;
            } else { // own piece blocking the way
                break; 
            }
        }
        
        // check all possible moves in the top-right diagonal
        for (int i = 1; currentRow - i >= 0 && currentCol + i < 8; i++) {
            if (gp.getBoard().checkTileForPiece(currentRow - i, currentCol + i)== null) { // empty square
            	allPossibleMovesList.add(new Point(currentCol+i, currentRow-i));
            } 
            else if (gp.getBoard().checkTileForPiece(currentRow - i, currentCol + i).getColour() != colour) { // capture opponent's piece
            	allPossibleMovesList.add(new Point(currentCol+i, currentRow-i));
                break; 
            } else { // own piece blocking the way
                break; 
            }
        }
        
        // check all possible moves in the bottom-left diagonal
        for (int i = 1; currentRow+i < 8 && currentCol-i >= 0; i++) {
            if (gp.getBoard().checkTileForPiece(currentRow+i, currentCol-i) == null) { // empty square
            	allPossibleMovesList.add(new Point(currentCol-i, currentRow+i));
            } 
            else if (gp.getBoard().checkTileForPiece(currentRow+i, currentCol-i).getColour() != colour) { // capture opponent's piece
            	allPossibleMovesList.add(new Point(currentCol-i, currentRow+i));
                break; 
            } 
            else { // own piece blocking the way
                break;
            }
        }
        
        // check all possible moves in the bottom-right diagonal
        for (int i = 1; currentRow+i < 8 && currentCol+i < 8; i++) {
            if (gp.getBoard().checkTileForPiece(currentRow+i, currentCol+i) == null) { // empty square
            	allPossibleMovesList.add(new Point(currentCol+i, currentRow+i));
            } else if (gp.getBoard().checkTileForPiece(currentRow+i, currentCol+i).getColour() != colour) { // capture opponent's piece
            	allPossibleMovesList.add(new Point(currentCol+i, currentRow+i));
                break; 
            } else { // own piece blocking the way
                break;
            }
        }
	}
}
