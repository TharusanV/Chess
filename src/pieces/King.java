package pieces;

import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class King extends Piece{

    public King(int pieceID, int positionX, int positionY, Color color, Image image) {
        super(pieceID, positionX, positionY, color, image);
    }

    public boolean canMove(int possibleMoveX, int possibleMoveY, GridPane board) {
        Piece targetPiece = getPieceAt(possibleMoveX, possibleMoveY, board);

        int diffX = Math.abs(getPositionX() - possibleMoveX);
        int diffY = Math.abs(getPositionY() - possibleMoveY);

        if (diffX <= 1 && diffY <= 1) {
            return targetPiece == null || targetPiece.getColor() != getColor();
        }

        return false;
    }


}
