package pieces;

import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class Knight extends Piece{

    public Knight(int pieceID, int positionX, int positionY, Color color, Image image) {
        super(pieceID, positionX, positionY, color, image);
    }

    public boolean canMove(int possibleMoveX, int possibleMoveY, GridPane board) {
        Piece targetPiece = getPieceAt(possibleMoveX, possibleMoveY, board);

        int[][] possibilities = { {1, 2}, {-1, 2}, {1, -2}, {-1,-2} };
        int diffX = getPositionX() - possibleMoveX;
        int diffY = getPositionY() - possibleMoveY;

        for (int[] pair : possibilities) {
            if (pair[0] == diffX && pair[1] == diffY) {
                return targetPiece == null || targetPiece.getColor() != getColor();
            }
        }

        return false;
    }
}
