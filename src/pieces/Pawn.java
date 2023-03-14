package pieces;

import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
public class Pawn extends Piece{

    private boolean isFirstMove;

    public Pawn(int pieceID, int positionX, int positionY, Color color, Image image) {
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
        // Check if the pawn is moving diagonally
        if (possibleMoveX != getPositionX() && possibleMoveY != getPositionY()) {
            // Pawns can only move diagonally when capturing an opponent's piece
            Piece targetPiece = getPieceAt(possibleMoveX, possibleMoveY, board);
            int diangonalDirection = getColor() == Color.WHITE ? -1 : 1;

            if (targetPiece.getColor() == getColor() ||
                    possibleMoveX != getPositionX() + diangonalDirection ||
                    possibleMoveY != getPositionY() + diangonalDirection) {
                return false;
            }

            return true;
        }

        // Check if the pawn is moving forward
        int moveDirection = getColor() == Color.WHITE ? -1 : 1;
        if (possibleMoveY != getPositionY() + moveDirection) {
            return false;
        }

        // Check if the pawn is moving one or two spaces forward
        if (possibleMoveX == getPositionX() && possibleMoveY == getPositionY() + moveDirection) {
            // Moving one space forward
            return getPieceAt(possibleMoveX, possibleMoveY, board) == null;
        }
        else if (possibleMoveX == getPositionX() && possibleMoveY == getPositionY() + moveDirection * 2) {
            // Moving two spaces forward
            if (getIsFirstMove() == false) {
                return false;
            }
            // Check if there is a piece blocking the way


            setIsFirstMove();
            return true;
        }

        // The move is not valid
        return false;
    }


}
