package pieces;

import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class Rook extends Piece{
    private boolean isFirstMove;

    public Rook(int pieceID, int positionX, int positionY, Color color, Image image) {
        super(pieceID, positionX, positionY, color, image);
        this.isFirstMove = true;
    }

    public boolean getIsFirstMove() {
        return isFirstMove;
    }

    public void setIsFirstMove() {
        this.isFirstMove = !isFirstMove;
    }

    public boolean canMove(int possibleMoveX, int possibleMoveY, GridPane board) {
        // Check if the rook is moving diagonally
        if (possibleMoveX != getPositionX() && possibleMoveY != getPositionY()) {
            return false;
        }

        // Check if the rook is moving vertically
        if (possibleMoveX == getPositionX()) {
            int minY = Math.min(getPositionY(), possibleMoveY);
            int maxY = Math.max(getPositionY(), possibleMoveY);
            Piece targetPiece = getPieceAt(possibleMoveX, possibleMoveY, board);
            for (int y = minY + 1; y < maxY; y++) {
                if (targetPiece != null && targetPiece.getColor() == getColor()) {
                    return false;
                }
            }

            return targetPiece == null || targetPiece.getColor() != getColor();
        }

        // Check if the rook is moving horizontally
        if (possibleMoveY == getPositionY()) {
            int minX = Math.min(getPositionX(), possibleMoveX);
            int maxX = Math.max(getPositionX(), possibleMoveX);
            Piece targetPiece = getPieceAt(possibleMoveX, possibleMoveY, board);
            for (int x = minX + 1; x < maxX; x++) {
                if (targetPiece != null && targetPiece.getColor() == getColor()) {
                    return false;
                }
            }

            return targetPiece == null || targetPiece.getColor() != getColor();
        }

        // The move is not valid
        return false;
    }
}
