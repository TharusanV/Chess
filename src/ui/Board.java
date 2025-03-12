package ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import main.GamePanel;
import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Piece;
import pieces.Queen;
import pieces.Rook;

public class Board {

	private GamePanel gp;
	Graphics2D g2;
	
	private Color[][] defaultTilesColours;
	private Piece[][] currentBoard;
	
	Color myWhite = new Color(240, 217, 181);
	Color myBrown = new Color(181, 136, 99);
	
	public Board(GamePanel p_gp) {
		this.gp = p_gp;
		
		defaultTilesColours = new Color[8][8];
		currentBoard = new Piece[8][8]; 
		initialiseBoard();
	}
	
	private void initialiseBoard() {
		int col = 0;
		int row = 0;
		
		while(col < gp.getMaxScreenCol() && row < gp.getMaxScreenRow()) {
			if((row + col) % 2 == 0) {defaultTilesColours[col][row] = myWhite;}
			else {defaultTilesColours[col][row] = myBrown;}
			
			col++;
			
			if(col == gp.getMaxScreenCol()) {
				col = 0;
				row++;
			}
		}
		
		
		//Pawns
		for(int i = 0; i < 8; i++) {
			currentBoard[i][1] = new Pawn(gp, new Point(i, 1), "black");
			currentBoard[i][6] = new Pawn(gp, new Point(i, 6), "white");	
		}
		
		// Rook
		currentBoard[0][0] = new Rook(gp, new Point(0, 0), "black");
		currentBoard[7][0] = new Rook(gp, new Point(7, 0), "black");
		currentBoard[0][7] = new Rook(gp, new Point(0, 7), "white");
		currentBoard[7][7] = new Rook(gp, new Point(7, 7), "white");

		// Knight
		currentBoard[1][0] = new Knight(gp, new Point(1, 0), "black");
		currentBoard[6][0] = new Knight(gp, new Point(6, 0), "black");
		currentBoard[1][7] = new Knight(gp, new Point(1, 7), "white");
		currentBoard[6][7] = new Knight(gp, new Point(6, 7), "white");

		// Bishop
		currentBoard[2][0] = new Bishop(gp, new Point(2, 0), "black");
		currentBoard[5][0] = new Bishop(gp, new Point(5, 0), "black");
		currentBoard[2][7] = new Bishop(gp, new Point(2, 7), "white");
		currentBoard[5][7] = new Bishop(gp, new Point(5, 7), "white");

		// King
		currentBoard[3][0] = new King(gp, new Point(3, 0), "black");
		currentBoard[3][7] = new King(gp, new Point(3, 7), "white");

		// Queen
		currentBoard[4][0] = new Queen(gp, new Point(4, 0), "black");
		currentBoard[4][7] = new Queen(gp, new Point(4, 7), "white");
	}

	public void draw(Graphics2D p_g2) {
		this.g2 = p_g2;
		
		int col = 0;
		int row = 0;
		
		while(col < gp.getMaxScreenCol() && row < gp.getMaxScreenRow()) {
			int x = col * gp.getTileSize();
			int y = row * gp.getTileSize();
			
			if(gp.getSelectedPiece() != null && gp.getSelectedPiece().getAllPossibleMovesList().contains(new Point(col, row))) {
				g2.setColor(Color.GRAY);
				g2.fillRect(x, y, gp.getTileSize() - 3, gp.getTileSize() - 3);
				
				g2.setStroke(new BasicStroke(3));
				g2.setColor(Color.BLACK);
				g2.drawRect(x, y, gp.getTileSize(), gp.getTileSize());
			}
			else {
				g2.setColor(defaultTilesColours[col][row]);
				g2.fillRect(x, y, gp.getTileSize(), gp.getTileSize());
			}
			
			g2.setStroke(new BasicStroke(1));
			
			if(checkTileForPiece(col, row) != null) {
				g2.drawImage(checkTileForPiece(col, row).getPieceImage(), x, y, gp.getTileSize(), gp.getTileSize(), null);
			}
			
			col++;
			
			if(col == gp.getMaxScreenCol()) {
				col = 0;
				row++;
			}
		}
	}
	
	public void update() {
		if(gp.getSelectedPiece() != null) {
			gp.getSelectedPiece().findPossibleMoves();
		}
	}
	
	public Piece checkTileForPiece(int col, int row) {
		if(currentBoard[col][row] != null) {
			return currentBoard[col][row];
		}
		
		return null;
	}
	
	public void movePiece(int newCol, int newRow) {
		int currentPieceCol = gp.getSelectedPiece().getCurrentPos().x;
		int currentPieceRow = gp.getSelectedPiece().getCurrentPos().y;
		
		if(checkTileForPiece(newCol, newRow) == null) {
			currentBoard[newCol][newRow] = currentBoard[currentPieceCol][currentPieceRow];
			gp.getSelectedPiece().setCurrentPos(newCol, newRow);
			currentBoard[currentPieceCol][currentPieceRow] = null;
		}
		else {
			if(currentBoard[newCol][newRow].getColour() != gp.getSelectedPiece().getColour()) {
				currentBoard[newCol][newRow] = currentBoard[currentPieceCol][currentPieceRow];
				gp.getSelectedPiece().setCurrentPos(newCol, newRow);
				currentBoard[currentPieceCol][currentPieceRow] = null;
			}
		}
	
		gp.setSelectedPiece(null);
	}
	
	public void drawPawnUpgradeOptions() {
		
	}
}
