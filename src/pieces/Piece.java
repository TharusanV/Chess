package pieces;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import main.GamePanel;

public class Piece {

	GamePanel gp;
	int id;
	Point currentPos;
	String colour;
	BufferedImage pieceImage;
	List<Point> allPossibleMovesList = new ArrayList<>();
	
	public Piece(GamePanel p_gp) {
		this.gp = p_gp;
	}

	
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Point getCurrentPos() {
		return currentPos;
	}


	public void setCurrentPos(Point pos) {
		this.currentPos = pos;
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

	public List<Point> getAllPossibleMoves() {
		return allPossibleMoves;
	}

	public void setAllPossibleMoves(List<Point> allPossibleMoves) {
		this.allPossibleMoves = allPossibleMoves;
	}
	
	
	
	
}
