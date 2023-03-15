package pieces;

import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Knight extends Piece{
    int[][] possibilities = { {1, 2}, {-1, 2}, {1, -2}, {-1,-2}, {2, 1}, {-2, 1}, {2, -1}, {-2,-1} };

    public Knight(int pieceID, int positionX, int positionY, Color color, Image image) {
        super(pieceID, positionX, positionY, color, image);
    }

    public void possibleMoves(GridPane board){
        for (int[] pair : possibilities) {
            // Get the row and column indexes of the rectangle
            int row = getPositionY() + pair[1];
            int col = getPositionX() + pair[0];

            // Check if the row and column indexes are valid
            if (row >= 0 && row < board.getRowCount() && col >= 0 && col < board.getColumnCount()) {
                if(getPieceAt(col, row, board) == null){
                    Rectangle rect = (Rectangle) board.getChildren().get(row * board.getRowCount() + col);
                    // Modify the color of the rectangle
                    rect.setFill(Color.CRIMSON);
                }
                if(getPieceAt(col, row, board) != null){
                    if(getPieceAt(col, row, board).getColor() != getColor()){
                        Rectangle rect = (Rectangle) board.getChildren().get(row * board.getRowCount() + col);
                        // Modify the color of the rectangle
                        rect.setFill(Color.CRIMSON);
                    }
                }
            }
        }
    }


    public boolean canMove(int possibleMoveX, int possibleMoveY, GridPane board) {
        Piece targetPiece = getPieceAt(possibleMoveX, possibleMoveY, board);

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
