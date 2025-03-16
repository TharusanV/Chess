package pieces;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import gameLogic.ChessLogic;
import main.GamePanel;

public class Piece {

	GamePanel gp;
	String title;
	int currentRow;
	int currentCol;
	String colour;
	BufferedImage pieceImage;
	Piece[][] currentBoard;
	int pieceValue;
	
	public Piece() {
	
	}

	
	public void loadPieceIcon(String name) {
		try {
			this.pieceImage = ImageIO.read(getClass().getResourceAsStream("/pieces/"+ name + ".png"));
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public List<Point> findLegalMoves() {
		return null;
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getCurrentPosRow() {
		return currentRow;
	}

	public int getCurrentPosCol() {
		return currentCol;
	}

	public void setCurrentRowAndPos(int newRow, int newCol) {
		this.currentRow = newRow;
		this.currentCol = newCol;
	}


	public String getColour() {
		return colour;
	}


	public void setColour(String colour) {
		this.colour = colour;
	}


	public BufferedImage getPieceImage() {
		return pieceImage;
	}


	public void setPieceImage(BufferedImage pieceImage) {
		this.pieceImage = pieceImage;
	}

	
	public boolean isWhite() {
		if(colour == "white") {
			return true;
		}
		
		return false;
	}
	
	public int getPieceValue() {
		return pieceValue;
	}
	
	public void checkDirection(List<Point> legalMoves, int rowIncrement, int colIncrement, int boardSize) {
	    int row = currentRow;
	    int col = currentCol;

	    while (true) {  // Keep moving in the given direction
	        row += rowIncrement;
	        col += colIncrement;

	        // Stop if out of bounds
	        if (row < 0 || row >= boardSize || col < 0 || col >= boardSize) {
	            break;
	        }

	        Piece tilePiece = ChessLogic.checkTileForPiece(row, col, currentBoard);
	         
	        if (tilePiece == null) {
	            legalMoves.add(new Point(col, row));  // Empty square, keep moving
	        } else {
	            if (tilePiece.getColour() != colour) {
	                legalMoves.add(new Point(col, row));  // Capture opponent piece
	            }
	            break; // Stop moving after hitting any piece
	        }
	    }
	}


	
}
