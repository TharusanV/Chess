package ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import gameLogic.ChessLogic;
import gameLogic.Move;
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
	public Piece[][] currentBoard;
	
	Color myWhite = new Color(240, 217, 181);
	Color myBrown = new Color(181, 136, 99);
	
	int numberOfAttackers = 0;
	
	public Board(GamePanel p_gp) {
		this.gp = p_gp;
		
		defaultTilesColours = new Color[8][8];
		currentBoard = new Piece[8][8]; 
		initialiseBoard();
	}
	
	private void initialiseBoard() {
		//Draw tiles
		for (int row = 0; row < defaultTilesColours.length; row++) {
	        for (int col = 0; col < defaultTilesColours[row].length; col++) {
	        	if((row + col) % 2 == 0) {defaultTilesColours[row][col] = myWhite;}
				else {defaultTilesColours[row][col] = myBrown;}
	        }
	    }
		
		//Pawns
		for(int col = 0; col < 8; col++) {
			currentBoard[1][col] = new Pawn( currentBoard, 1, col, "black");
			currentBoard[6][col] = new Pawn( currentBoard, 6, col, "white");	
		}
		
		int rowForWhite = 7;
		int rowForBlack = 0;
		
		// Rook
		currentBoard[rowForBlack][0] = new Rook( currentBoard, rowForBlack, 0, "black");
		currentBoard[rowForBlack][7] = new Rook( currentBoard, rowForBlack, 7, "black");
		currentBoard[rowForWhite][0] = new Rook( currentBoard, rowForWhite, 0, "white");
		currentBoard[rowForWhite][7] = new Rook( currentBoard, rowForWhite, 7, "white");

		// Knight
		currentBoard[rowForBlack][1] = new Knight( currentBoard, rowForBlack, 1, "black");
		currentBoard[rowForBlack][6] = new Knight( currentBoard, rowForBlack, 6, "black");
		currentBoard[rowForWhite][1] = new Knight( currentBoard, rowForWhite, 1, "white");
		currentBoard[rowForWhite][6] = new Knight( currentBoard, rowForWhite, 6, "white");

		// Bishop
		currentBoard[rowForBlack][2] = new Bishop( currentBoard, rowForBlack, 2, "black");
		currentBoard[rowForBlack][5] = new Bishop( currentBoard, rowForBlack, 5, "black");
		currentBoard[rowForWhite][2] = new Bishop( currentBoard, rowForWhite, 2, "white");
		currentBoard[rowForWhite][5] = new Bishop( currentBoard, rowForWhite, 5, "white");

		// King
		currentBoard[rowForBlack][3] = new King( currentBoard, rowForBlack, 3, "black");
		currentBoard[rowForWhite][3] = new King( currentBoard, rowForWhite, 3, "white");

		// Queen
		currentBoard[rowForBlack][4] = new Queen( currentBoard, rowForBlack, 4, "black");
		currentBoard[rowForWhite][4] = new Queen( currentBoard, rowForWhite, 4, "white");
	}

	public void draw(Graphics2D p_g2) {
		this.g2 = p_g2;
		
		for (int row = 0; row < currentBoard.length; row++) {
	        for (int col = 0; col < currentBoard[row].length; col++) {
	        	int x = col * gp.getTileSize();
				int y = row * gp.getTileSize();
	        	
				if(gp.getSelectedPiece() != null && gp.getSelectedPiece().findLegalMoves().contains(new Point(col, row))) {
					g2.setColor(Color.GRAY);
					g2.fillRect(x, y, gp.getTileSize() - 3, gp.getTileSize() - 3);
					
					g2.setStroke(new BasicStroke(3));
					g2.setColor(Color.BLACK);
					g2.drawRect(x, y, gp.getTileSize(), gp.getTileSize());
				}
				else {
					g2.setColor(defaultTilesColours[row][col]);
					g2.fillRect(x, y, gp.getTileSize(), gp.getTileSize());
				}
				
				g2.setStroke(new BasicStroke(1));
				
				if(ChessLogic.checkTileForPiece(row, col, currentBoard) != null) {
					g2.drawImage(ChessLogic.checkTileForPiece(row, col, currentBoard).getPieceImage(), x, y, gp.getTileSize(), gp.getTileSize(), null);
				}
	        }
	    }
	}
	
	public void update() {
		if(gp.getSelectedPiece() != null) {
			gp.getSelectedPiece().findLegalMoves();
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	

	
	public void movePiece(int startCol, int startRow, int newCol, int newRow) {		
		Piece movingPiece = ChessLogic.checkTileForPiece(startRow, startCol, currentBoard);
        Piece capturedPiece = ChessLogic.checkTileForPiece(newRow, newCol, currentBoard);
		
        if (capturedPiece != null && capturedPiece.getColour() == movingPiece.getColour()) {
            return;
        }
        
        Move move = new Move(startCol, startRow, newCol, newRow, movingPiece, capturedPiece);
        gp.getMoveHistory().push(move);
        currentBoard[newRow][newCol] =  movingPiece;
        currentBoard[startRow][startCol] = null;
        
        movingPiece.setCurrentRowAndPos(newRow, newCol);
        //capturedPiece.setCurrentRowAndPos(-1, -1); 
        
		gp.setWhiteTurn(!gp.isWhiteTurn());
	}
	
	
	
	public void undoLastMove() {
        if (gp.getMoveHistory().isEmpty()) return;

        Move lastMove = gp.getMoveHistory().pop();
        
        currentBoard[lastMove.getOriginalRow()][lastMove.getOriginalCol()] =  lastMove.getMovingPiece();
        currentBoard[lastMove.getNowRow()][lastMove.getNowCol()] =  lastMove.getCapturedPiece();  // Restore captured piece if any
        
        lastMove.getMovingPiece().setCurrentRowAndPos(lastMove.getOriginalRow(), lastMove.getOriginalCol());
        lastMove.getCapturedPiece().setCurrentRowAndPos(lastMove.getNowRow(), lastMove.getNowCol());; 

        gp.setWhiteTurn(!gp.isWhiteTurn()); // Reverse turn
    }
	
}
