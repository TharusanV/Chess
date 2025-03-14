package gameLogic;

import pieces.Piece;

public class Move {
	
    int originalCol, originalRow; 
    int nowCol, nowRow;
    Piece movingPiece; 
    Piece capturedPiece; // The piece that was captured (if any)

    public Move(int p_originalCol, int p_originalRow, int p_nowCol, int p_nowRow, Piece p_movingPiece, Piece p_capturedPiece) {
        this.originalCol = p_originalCol;
        this.originalRow = p_originalRow;
        this.nowCol = p_nowCol;
        this.nowRow = p_nowRow;
        this.movingPiece = p_movingPiece;
        this.capturedPiece = p_capturedPiece; // Can be null if no capture
    }

	public int getOriginalCol() {
		return originalCol;
	}

	public void setOriginalCol(int originalCol) {
		this.originalCol = originalCol;
	}

	public int getOriginalRow() {
		return originalRow;
	}

	public void setOriginalRow(int originalRow) {
		this.originalRow = originalRow;
	}

	public int getNowCol() {
		return nowCol;
	}

	public void setNowCol(int nowCol) {
		this.nowCol = nowCol;
	}

	public int getNowRow() {
		return nowRow;
	}

	public void setNowRow(int nowRow) {
		this.nowRow = nowRow;
	}

	public Piece getMovingPiece() {
		return movingPiece;
	}

	public void setMovingPiece(Piece movingPiece) {
		this.movingPiece = movingPiece;
	}

	public Piece getCapturedPiece() {
		return capturedPiece;
	}

	public void setCapturedPiece(Piece capturedPiece) {
		this.capturedPiece = capturedPiece;
	}
    
    
}

