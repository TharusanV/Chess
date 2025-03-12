package ai;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import main.GamePanel;
import pieces.Piece;

public class IntermediateAI {

	//Uses Minimax algorithm.
	
	GamePanel gp;
	List<Piece> currentMoveablePieces = new ArrayList<>();
	
	public IntermediateAI(GamePanel p_gp) {
		this.gp = p_gp;
	}
	
	public void evaluateBoard(Piece[][] board) {
		int playerScore = 0;
		int aiScore = 0;
	}
	
	public int evaluateBoardByPieces(Piece[][] board, boolean isPlayingWhite) {
		int score = 0;
		
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				if(board[i][j] != null) {
					switch(board[i][j].getTitle()) {
					case "pawn":
						if((board[i][j].getColour() == "white" && isPlayingWhite) || (board[i][j].getColour() == "black" && !isPlayingWhite)) {
							score += 1;
						}
					case "knight":
						if((board[i][j].getColour() == "white" && isPlayingWhite) || (board[i][j].getColour() == "black" && !isPlayingWhite)) {
							score += 3;
						}
					case "bishop":
						if((board[i][j].getColour() == "white" && isPlayingWhite) || (board[i][j].getColour() == "black" && !isPlayingWhite)) {
							score += 3;
						}
					case "rook":
						if((board[i][j].getColour() == "white" && isPlayingWhite) || (board[i][j].getColour() == "black" && !isPlayingWhite)) {
							score += 5;
						}
					case "queen":
						if((board[i][j].getColour() == "white" && isPlayingWhite) || (board[i][j].getColour() == "black" && !isPlayingWhite)) {
							score += 9;
						}
					}
				}
			}
		}
		
		
		return score;
	}
	
	
	
	public void findAllMoveablePieces(boolean isPlayingWhite) {
		currentMoveablePieces.clear();
		
		int col = 0;
		int row = 0;
			
		while(col < gp.getMaxScreenCol() && row < gp.getMaxScreenRow()) {
				
			if(gp.getBoard().checkTileForPiece(col, row) !=  null){
				Piece currentPiece = gp.getBoard().checkTileForPiece(col, row);
				
				if(isPlayingWhite && currentPiece.getColour() == "white") {
					currentMoveablePieces.add(currentPiece);
				}
				else if(!isPlayingWhite && currentPiece.getColour() == "black") {
					currentMoveablePieces.add(currentPiece);
				}
			}
				
			col++;
				
			if(col == gp.getMaxScreenCol()) {
				col = 0;
				row++;
			}
		}
	}
	
	public void generateMovesFromMoveablePieces() {
		
	}
	
}
