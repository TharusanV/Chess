package pieces;

import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class Queen extends Piece{

    public Queen(int pieceID, int positionX, int positionY, Color color, Image image) {
        super(pieceID, positionX, positionY, color, image);
    }

    public void possibleMoves(GridPane board){

    }

    public boolean canMove(int possibleMoveX, int possibleMoveY, GridPane board) {
        Piece targetPiece = getPieceAt(possibleMoveX, possibleMoveY, board);

        // Check if the queen is moving diagonally
        if (Math.abs(possibleMoveX - getPositionX()) == Math.abs(possibleMoveY - getPositionY())) {
            int minX = Math.min(getPositionX(), possibleMoveX);
            int minY = Math.min(getPositionY(), possibleMoveY);
            int maxX = Math.max(getPositionX(), possibleMoveX);
            int maxY = Math.max(getPositionY(), possibleMoveY);

            int x = minX + 1;
            int y = minY + 1;
            while (x < maxX && y < maxY) {
                if (getPieceAt(x, y, board) != null) {
                    return false;
                }
                x++;
                y++;
            }

            return targetPiece == null || targetPiece.getColor() != getColor();
        }

        // Check if the Queen is moving vertically
        if (possibleMoveX == getPositionX()) {
            //Gets the minY and maxY - difference between the initial and possible destination
            int minY = Math.min(getPositionY(), possibleMoveY);
            int maxY = Math.max(getPositionY(), possibleMoveY);
            //minY gets added to +1 as we don't need to check the current position
            for (int y = minY + 1; y < maxY; y++) {
                if (getPieceAt(possibleMoveX, y, board) != null) {
                    return false;
                }
            }

            return targetPiece == null || targetPiece.getColor() != getColor();
        }

        // Check if the Queen is moving horizontally
        if (possibleMoveY == getPositionY()) {
            //Gets the minX and maxX - difference between the initial and possible destination to loop through the squares
            int minX = Math.min(getPositionX(), possibleMoveX);
            int maxX = Math.max(getPositionX(), possibleMoveX);
            //minX gets added to +1 as we don't need to check the current position
            for (int x = minX + 1; x < maxX; x++) {
                if (getPieceAt(x, possibleMoveY, board) != null) {
                    return false;
                }
            }

            return targetPiece == null || targetPiece.getColor() != getColor();
        }

        // If the queen is not moving diagonally or horizontally/vertically, it's an invalid move
        return false;
    }
}
