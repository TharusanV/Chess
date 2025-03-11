package pieces;

import java.awt.Point;

import main.GamePanel;

public class Bishop extends Piece {
	
	public Bishop(GamePanel p_gp, Point p_startingPos, String p_colour) {
		super(p_gp);
		
		this.currentPos = p_startingPos;
		this.colour = p_colour;
		
		loadPieceIcon(p_colour + "_bishop");
	}
	
	public void findPossibleMoves() {
		allPossibleMovesList.clear();
		
		// check all possible moves in the top-left diagonal
        for (int i = 1; currentPos.y - i >= 0 && currentPos.x - i >= 0; i++) {
        	if(gp.getBoard().checkTileForPiece(currentPos.x - i, currentPos.y - i) == null) {
        		allPossibleMovesList.add(new Point(currentPos.x - i, currentPos.y - i));
            } 
        	else if (gp.getBoard().checkTileForPiece(currentPos.x - i, currentPos.y - i).getColour() != colour) { // capture opponent's piece
        		allPossibleMovesList.add(new Point(currentPos.x - i, currentPos.y - i));
                break;
            } else { // own piece blocking the way
                break; 
            }
        }
        
        // check all possible moves in the top-right diagonal
        for (int i = 1; currentPos.y - i >= 0 && currentPos.x + i < 8; i++) {
            if (gp.getBoard().checkTileForPiece(currentPos.x + i, currentPos.y - i)== null) { // empty square
            	allPossibleMovesList.add(new Point(currentPos.x + i, currentPos.y - i));
            } 
            else if (gp.getBoard().checkTileForPiece(currentPos.x + i, currentPos.y - i).getColour() != colour) { // capture opponent's piece
                allPossibleMovesList.add(new Point(currentPos.x + i, currentPos.y - i));
                break; 
            } else { // own piece blocking the way
                break; 
            }
        }
        
        // check all possible moves in the bottom-left diagonal
        for (int i = 1; currentPos.y+i < 8 && currentPos.x-i >= 0; i++) {
            if (gp.getBoard().checkTileForPiece(currentPos.x-i, currentPos.y+i) == null) { // empty square
            	allPossibleMovesList.add(new Point(currentPos.x-i, currentPos.y+i));
            } 
            else if (gp.getBoard().checkTileForPiece(currentPos.x-i, currentPos.y+i).getColour() != colour) { // capture opponent's piece
            	allPossibleMovesList.add(new Point(currentPos.x-i, currentPos.y+i));
                break; 
            } 
            else { // own piece blocking the way
                break;
            }
        }
        
        // check all possible moves in the bottom-right diagonal
        for (int i = 1; currentPos.y+i < 8 && currentPos.x+i < 8; i++) {
            if (gp.getBoard().checkTileForPiece(currentPos.x+i, currentPos.y+i) == null) { // empty square
            	allPossibleMovesList.add(new Point(currentPos.x+i, currentPos.y+i));
            } else if (gp.getBoard().checkTileForPiece(currentPos.x+i, currentPos.y+i).getColour() != colour) { // capture opponent's piece
            	allPossibleMovesList.add(new Point(currentPos.x+i, currentPos.y+i));
                break; 
            } else { // own piece blocking the way
                break;
            }
        }
	}
}
