package pieces;

import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class King extends Piece{
    // Define the possible offsets for a King's move
    int[][] offsets = {
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1},           {0, 1},
            {1, -1},  {1, 0},  {1, 1}
    };

    public King(int pieceID, int positionX, int positionY, Color color, Image image) {
        super(pieceID, positionX, positionY, color, image);
    }
    public void possibleMoves(GridPane board) {
        // Iterate over the possible offsets
        for (int[] offset : offsets) {
            // Calculate the row and column indexes of the potential move
            int row = getPositionY() + offset[0];
            int col = getPositionX() + offset[1];

            // Check if the move is within the bounds of the board
            if (row >= 0 && row < board.getRowCount() && col >= 0 && col < board.getColumnCount()) {
                if(getPieceAt(col, row, board) != null){
                    if(getPieceAt(col, row, board).getColor() != getColor()){
                        // Get the rectangle at the row and column index
                        Rectangle rect = (Rectangle) board.getChildren().get(row * board.getRowCount() + col);
                        // Modify the color of the rectangle
                        rect.setFill(Color.CRIMSON);
                    }
                }
                else{
                    Rectangle rect = (Rectangle) board.getChildren().get(row * board.getRowCount() + col);
                    // Modify the color of the rectangle
                    rect.setFill(Color.CRIMSON);
                }
            }
        }
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
