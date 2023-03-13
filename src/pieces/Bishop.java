package pieces;

import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class Bishop extends Piece{

    public Bishop(int pieceID, int positionX, int positionY, Color color, Image image) {
        super(pieceID, positionX, positionY, color, image);
    }

    public boolean canMove(int possibleMoveX, int possibleMoveY, GridPane board) {
        Piece targetPiece = getPieceAt(possibleMoveX, possibleMoveY, board);

        // Check if the bishop is moving diagonally
        int diffX = Math.abs(getPositionX() - possibleMoveX);
        int diffY = Math.abs(getPositionY() - possibleMoveY);
        if (diffX != diffY) {
            return false;
        }

        // Check if there are any pieces between the bishop and the destination
        int minX = Math.min(getPositionX(), possibleMoveX);
        int maxX = Math.max(getPositionX(), possibleMoveX);
        int minY = Math.min(getPositionY(), possibleMoveY);
        int maxY = Math.max(getPositionY(), possibleMoveY);

        //Checking the spaces between the min and max which is why we +1 min and < for max
        int x = minX + 1;
        int y = minY + 1;

        while (x < maxX && y < maxY) {
            Piece piece = getPieceAt(x, y, board);

            if (piece != null) {
                return false;
            }

            x++;
            y++;
        }

        return targetPiece == null || targetPiece.getColor() != getColor();
    }
}
