package gameLogic;

import pieces.Piece;

public class Move {
	
    int originalX, originalY; 
    int nowX, nowY;
    Piece movingPiece; 
    Piece capturedPiece; // The piece that was captured (if any)

    public Move(int p_originalX, int p_originalY, int p_nowX, int p_nowY, Piece p_movingPiece, Piece p_capturedPiece) {
        this.originalX = p_originalX;
        this.originalY = p_originalY;
        this.nowX = p_nowX;
        this.nowY = p_nowY;
        this.movingPiece = p_movingPiece;
        this.capturedPiece = p_capturedPiece; // Can be null if no capture
    }

	public int getOriginalX() {
		return originalX;
	}

	public void setOriginalX(int originalX) {
		this.originalX = originalX;
	}

	public int getOriginalY() {
		return originalY;
	}

	public void setOriginalY(int originalY) {
		this.originalY = originalY;
	}

	public int getNowX() {
		return nowX;
	}

	public void setNowX(int nowX) {
		this.nowX = nowX;
	}

	public int getNowY() {
		return nowY;
	}

	public void setNowY(int nowY) {
		this.nowY = nowY;
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

