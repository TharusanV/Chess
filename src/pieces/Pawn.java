package pieces;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import gameLogic.ChessLogic;
import main.GamePanel;

public class Pawn extends Piece {

	int startingPosRow;
	
	public Pawn(Piece[][] p_currentBoard, int p_currentRow, int p_currentCol, String p_colour) {
		this.currentBoard =  p_currentBoard;
		
		this.title = "pawn";
		this.currentRow =  p_currentRow;
		this.currentCol = p_currentCol;
		this.colour = p_colour;
		this.pieceValue = 100;
		
		this.startingPosRow = p_currentRow;
		
		loadPieceIcon(p_colour + "_pawn");
	}
	
	public List<Point> findLegalMoves() {
		List<Point> legalMoves = new ArrayList<>();
		
		int moveDirection = colour == "white" ? -1 : 1;
		
		//Straight
		if((currentRow + moveDirection >= 0) && (currentRow + moveDirection < 8)) {
			if(ChessLogic.checkTileForPiece(currentRow + moveDirection, currentCol, currentBoard) == null) {
				legalMoves.add(new Point(currentCol, currentRow + moveDirection));
				
				//Check if they can move two points forward
				if(currentRow == startingPosRow) {
					if(ChessLogic.checkTileForPiece(currentRow + moveDirection * 2, currentCol, currentBoard) == null) {
						legalMoves.add(new Point(currentCol, currentRow + moveDirection * 2));
					}
				}
			}
		}
		
		//Possible diagonal (left White / right Black (flipped left))
		if((currentCol - moveDirection >= 0) && (currentCol - moveDirection < 8) && (currentRow + moveDirection >= 0) && (currentRow + moveDirection < 8)) {
			if(ChessLogic.checkTileForPiece(currentRow + moveDirection, currentCol - moveDirection, currentBoard) != null) {
				if(ChessLogic.checkTileForPiece(currentRow + moveDirection, currentCol - moveDirection, currentBoard).getColour() != colour) {
					legalMoves.add(new Point(currentCol - moveDirection, currentRow + moveDirection));
				}
			}
		}
		
		
		//Possible diagonal (right White / left Black (flipped right))
		if((currentCol + moveDirection >= 0) && (currentCol + moveDirection < 8) && (currentRow + moveDirection >= 0) && (currentRow + moveDirection < 8)) {
			if(ChessLogic.checkTileForPiece(currentRow + moveDirection, currentCol + moveDirection, currentBoard) != null) {
				if(ChessLogic.checkTileForPiece(currentRow + moveDirection, currentCol + moveDirection, currentBoard).getColour() != colour) {
					legalMoves.add(new Point(currentCol + moveDirection, currentRow + moveDirection));
				}
			}
		}
        
        return legalMoves;
	}	
	
}
