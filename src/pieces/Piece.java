package pieces;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Piece {

	GamePanel gp;
	String title;
	Point currentPos;
	String colour;
	BufferedImage pieceImage;
	List<Point> allPossibleMovesList = new ArrayList<>();
	
	public Piece(GamePanel p_gp) {
		this.gp = p_gp;
	}

	
	public void loadPieceIcon(String name) {
		try {
			this.pieceImage = ImageIO.read(getClass().getResourceAsStream("/pieces/"+ name + ".png"));
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void findPossibleMoves() {}
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Point getCurrentPos() {
		return currentPos;
	}


	public void setCurrentPos(int newCol, int newRow) {
		this.currentPos.x = newCol;
		this.currentPos.y = newRow;
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

	public List<Point> getAllPossibleMovesList() {
		return allPossibleMovesList;
	}

	public void setAllPossibleMoves(List<Point> allPossibleMovesList) {
		this.allPossibleMovesList = allPossibleMovesList;
	}
	
	
	
	
}
