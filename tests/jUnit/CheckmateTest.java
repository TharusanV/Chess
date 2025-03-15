package jUnit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gameLogic.ChessLogic;
import main.GamePanel;
import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Piece;
import pieces.Queen;
import pieces.Rook;

class CheckmateTest {

	private GamePanel gp;
	
	private ChessLogic chessLogic;
	
	@BeforeEach
	void setUp() {
		gp = new GamePanel();
		chessLogic = new ChessLogic(gp);
	}
	
	@Test
	void test() {
		
		String[][] boardFoolsMate = {
	            {"r", "n", "b", ".", "k", "b", "n", "r"},
	            {"p", "p", "p", "p", ".", "p", "p", "p"},
	            {".", ".", ".", ".", "p", ".", ".", "."},
	            {".", ".", ".", ".", ".", ".", ".", "."},
	            {".", ".", ".", ".", ".", ".", "P", "q"},
	            {".", ".", ".", ".", ".", "P", ".", "."},
	            {"P", "P", "P", "P", "P", ".", ".", "P"},
	            {"R", "N", "B", "Q", "K", "B", "N", "R"}
	        };
		
	    Piece[][] board1 = new Piece[8][8];

	    for (int row = 0; row < boardFoolsMate.length; row++) {
	        for (int col = 0; col < boardFoolsMate[row].length; col++) {
	            switch (boardFoolsMate[row][col]) {
	                case "r":
	                    board1[row][col] = new Rook(gp, row, col, "black");
	                    break;
	                case "R":
	                    board1[row][col] = new Rook(gp, row, col, "white");
	                    break;
	                case "n":
	                    board1[row][col] = new Knight(gp, row, col, "black");
	                    break;
	                case "N":
	                    board1[row][col] = new Knight(gp, row, col, "white");
	                    break;
	                case "b":
	                    board1[row][col] = new Bishop(gp, row, col, "black");
	                    break;
	                case "B":
	                    board1[row][col] = new Bishop(gp, row, col, "white");
	                    break;
	                case "q":
	                    board1[row][col] = new Queen(gp, row, col, "black");
	                    break;
	                case "Q":
	                    board1[row][col] = new Queen(gp, row, col, "white");
	                    break;
	                case "k":
	                    board1[row][col] = new King(gp, row, col, "black");
	                    break;
	                case "K":
	                    board1[row][col] = new King(gp, row, col, "white");
	                    break;
	                case "p":
	                    board1[row][col] = new Pawn(gp, row, col, "black");
	                    break;
	                case "P":
	                    board1[row][col] = new Pawn(gp, row, col, "white");
	                    break;
	                default:
	                    board1[row][col] = null; // Empty square
	                    break;
	            }
	        }
	    }

	    boolean result = chessLogic.checkForCheckmate(board1, true);
	    
	    assertEquals(true, result);

	}


}
