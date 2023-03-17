package pieces;

import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class King extends Piece{
    // Define the possible offsets for a King's move
    int[][] offsets = {
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1},           {0, 1},
            {1, -1},  {1, 0},  {1, 1}
    };

    private ArrayList<int[]> coordinates;

    public King(int pieceID, int positionX, int positionY, Color color, Image image) {
        super(pieceID, positionX, positionY, color, image);
        this.coordinates = new ArrayList<int[]>();
    }

    public ArrayList<int[]> getCoordinates() {
        return this.coordinates;
    }

    public void possibleMoves(GridPane board) {
        coordinates.clear();
        // Iterate over the possible offsets
        for (int[] offset : offsets) {
            // Calculate the row and column indexes of the potential move
            int row = getPositionY() + offset[0];
            int col = getPositionX() + offset[1];

            // Check if the move is within the bounds of the board
            if (row >= 0 && row < board.getRowCount() && col >= 0 && col < board.getColumnCount()) {
                if(getPieceAt(col, row, board) != null){
                    if(getPieceAt(col, row, board).getColor() != getColor()){
                        coordinates.add(new int[] {col, row});
                    }
                }
                else{
                    coordinates.add(new int[] {col, row});
                }
            }
        }
    }

    public void possibleMovesShown(GridPane board){
        for (int[] coord : coordinates) {
            // Get the row and column indexes of the rectangle
            int row = coord[1];
            int col = coord[0];

            if(getPieceAt(col, row, board) == null){
                // Get the rectangle at the row and column index
                Rectangle rect = (Rectangle) board.getChildren().get(row * board.getRowCount() + col);

                // Modify the color of the rectangle
                rect.setFill(Color.CRIMSON);
            }
            if(getPieceAt(col, row, board) != null) {
                if (getPieceAt(col, row, board).getColor() != getColor()) {
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
