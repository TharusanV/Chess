package jUnit;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;

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

	
	@BeforeEach
	void setUp() {

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
	                    board1[row][col] = new Rook( board1, row, col, "black");
	                    break;
	                case "R":
	                    board1[row][col] = new Rook( board1, row, col, "white");
	                    break;
	                case "n":
	                    board1[row][col] = new Knight( board1, row, col, "black");
	                    break;
	                case "N":
	                    board1[row][col] = new Knight( board1, row, col, "white");
	                    break;
	                case "b":
	                    board1[row][col] = new Bishop( board1, row, col, "black");
	                    break;
	                case "B":
	                    board1[row][col] = new Bishop( board1, row, col, "white");
	                    break;
	                case "q":
	                    board1[row][col] = new Queen( board1, row, col, "black");
	                    break;
	                case "Q":
	                    board1[row][col] = new Queen( board1, row, col, "white");
	                    break;
	                case "k":
	                    board1[row][col] = new King( board1, row, col, "black");
	                    break;
	                case "K":
	                    board1[row][col] = new King( board1, row, col, "white");
	                    break;
	                case "p":
	                    board1[row][col] = new Pawn( board1, row, col, "black");
	                    break;
	                case "P":
	                    board1[row][col] = new Pawn( board1, row, col, "white");
	                    break;
	                default:
	                    board1[row][col] = null; // Empty square
	                    break;
	            }
	        }
	    }
	    

	    boolean result = ChessLogic.checkForCheckmate(board1, true);
	    
	    assertEquals(true, result);

	}
	
	

    @Test
    void testKingInCheckButNotCheckmate() {
        String[][] boardCheck = {
                {"r", "n", "b", "q", "k", "b", "n", "r"},
                {"p", "p", "p", ".", "p", "p", "p", "p"},
                {".", ".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", "p", ".", ".", ".", "."},
                {".", ".", ".", ".", "P", "Q", ".", "."},
                {".", ".", ".", ".", ".", ".", ".", "."},
                {"P", "P", "P", "P", ".", "P", "P", "P"},
                {"R", "N", "B", ".", "K", "B", "N", "R"}
        };

        Piece[][] board3 = new Piece[8][8];
        
        for (int row = 0; row < boardCheck.length; row++) {
            for (int col = 0; col < boardCheck[row].length; col++) {
                switch (boardCheck[row][col]) {
                    case "r": board3[row][col] = new Rook(board3, row, col, "black"); break;
                    case "R": board3[row][col] = new Rook(board3, row, col, "white"); break;
                    case "n": board3[row][col] = new Knight(board3, row, col, "black"); break;
                    case "N": board3[row][col] = new Knight(board3, row, col, "white"); break;
                    case "b": board3[row][col] = new Bishop(board3, row, col, "black"); break;
                    case "B": board3[row][col] = new Bishop(board3, row, col, "white"); break;
                    case "q": board3[row][col] = new Queen(board3, row, col, "black"); break;
                    case "Q": board3[row][col] = new Queen(board3, row, col, "white"); break;
                    case "k": board3[row][col] = new King(board3, row, col, "black"); break;
                    case "K": board3[row][col] = new King(board3, row, col, "white"); break;
                    case "p": board3[row][col] = new Pawn(board3, row, col, "black"); break;
                    case "P": board3[row][col] = new Pawn(board3, row, col, "white"); break;
                    default: board3[row][col] = null; break;
                }
            }
        }
        
        boolean result = ChessLogic.checkForCheckmate(board3, false);
        assertEquals(false, result);
    }


}
