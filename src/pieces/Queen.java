package pieces;

import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class Queen extends Piece{
    private boolean isFirstMove;

    public Queen(int pieceID, int positionX, int positionY, Color color, Image image) {
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
        int currentX = getPositionX();
        int currentY = getPositionY();

        return true;
    }
}
