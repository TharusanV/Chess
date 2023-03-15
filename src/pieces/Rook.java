package pieces;

import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class Rook extends Piece{

    public Rook(int pieceID, int positionX, int positionY, Color color, Image image) {
        super(pieceID, positionX, positionY, color, image);
    }
    public void possibleMoves(GridPane board){

    }

    public boolean canMove(int possibleMoveX, int possibleMoveY, GridPane board) {
        // Check if the rook is moving diagonally (Not Allowed)
        if (possibleMoveX != getPositionX() && possibleMoveY != getPositionY()) {
            return false;
        }

        // Check if the rook is moving vertically
        if (possibleMoveX == getPositionX()) {
            //Gets the minY and maxY - difference between the initial and possible destination
            int minY = Math.min(getPositionY(), possibleMoveY);
            int maxY = Math.max(getPositionY(), possibleMoveY);
            Piece targetPiece = getPieceAt(possibleMoveX, possibleMoveY, board);
            //minY gets added to +1 as we don't need to check the current position
            for (int y = minY + 1; y < maxY; y++) {
                if (getPieceAt(possibleMoveX, y, board) != null) {
                    return false;
                }
            }

            return targetPiece == null || targetPiece.getColor() != getColor();
        }

        // Check if the rook is moving horizontally
        if (possibleMoveY == getPositionY()) {
            //Gets the minX and maxX - difference between the initial and possible destination to loop through the squares
            int minX = Math.min(getPositionX(), possibleMoveX);
            int maxX = Math.max(getPositionX(), possibleMoveX);
            Piece targetPiece = getPieceAt(possibleMoveX, possibleMoveY, board);
            //minX gets added to +1 as we don't need to check the current position
            for (int x = minX + 1; x < maxX; x++) {
                if (getPieceAt(x, possibleMoveY, board) != null) {
                    return false;
                }
            }

            return targetPiece == null || targetPiece.getColor() != getColor();
        }

        // The move is not valid
        return false;
    }
}
