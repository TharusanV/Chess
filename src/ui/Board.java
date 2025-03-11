package ui;

import java.awt.Color;
import java.awt.Graphics2D;

import main.GamePanel;
import pieces.Pawn;
import pieces.Piece;

public class Board {

	private GamePanel gp;
	private Piece[][] board;
	
	public Board(GamePanel p_gp) {
		this.gp = p_gp;
		
		board = new Piece[8][8]; 
		initialiseBoard();
	}
	
	private void initialiseBoard() {
		//Pawns
		for(int i = 0; i < 8; i++) {
			board[i][1] = new Pawn(gp, "Black");
			board[i][6] = new Pawn(gp, "White");	
		}
		
		//Rook
		board[0][0] = new Rook(gp, "Black");
		board[7][0] = new Rook(gp, "Black");
		board[0][7] = new Rook(gp, "White");
		board[7][7] = new Rook(gp, "White");
		
		//Knight
		board[1][0] = new Knight(gp, "Black");
		board[6][0] = new Knight(gp, "Black");
		board[1][7] = new Knight(gp, "White");
		board[6][7] = new Knight(gp, "White");
		
		//Bishop
		board[2][0] = new Bishop(gp, "Black");
		board[5][0] = new Bishop(gp, "Black");
		board[2][7] = new Bishop(gp, "White");
		board[5][7] = new Bishop(gp, "White");
		
		//King
		board[3][0] = new King(gp, "Black");
		board[3][7] = new King(gp, "White");
		
		//Queen
		board[4][0] = new Queen(gp, "Black");
		board[4][7] = new Queen(gp, "White");
	}

	public void draw(Graphics2D p_g2) {
		int col = 0;
		int row = 0;
		
		while(col < gp.getMaxScreenCol() && row < gp.getMaxScreenRow()) {
			if((row + col) % 2 == 0) {
				p_g2.setColor(Color.WHITE);
			}
			else {
				p_g2.setColor(Color.RED);
			}
			
			int x = col * gp.getTileSize();
			int y = row * gp.getTileSize();
			
			p_g2.fillRect(x, y, gp.getTileSize(), gp.getTileSize());
			
			col++;
			
			if(col == gp.getMaxScreenCol()) {
				col = 0;
				row++;
			}
		}
		
		
	}
	
	
	public Piece checkTileForPiece(int col, int row) {
		if(board[col][row] != null) {
			return board[col][row];
		}
		
		return null;
	}
	
}
