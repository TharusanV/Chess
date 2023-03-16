package pieces;

import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Bishop extends Piece{

    private ArrayList<int[]> coordinates;

    public Bishop(int pieceID, int positionX, int positionY, Color color, Image image) {
        super(pieceID, positionX, positionY, color, image);
        this.coordinates = new ArrayList<int[]>();
    }

    public ArrayList<int[]> getCoordinates() {
        return this.coordinates;
    }

    public void possibleMoves(GridPane board){
        int row = getPositionY(); // get the current row of the bishop
        int col = getPositionX(); // get the current column of the bishop

        // check all possible moves in the top-left diagonal
        for (int i = 1; row-i >= 0 && col-i >= 0; i++) {
            if (getPieceAt(col-i, row-i, board)  == null) { // empty square
                coordinates.add(new int[]{col-i, row-i});
            } else if (getPieceAt(col-i, row-i, board).getColor() != getColor()) { // capture opponent's piece
                coordinates.add(new int[]{col-i, row-i});
                break; // can't move further in this direction
            } else { // own piece blocking the way
                break; // can't move further in this direction
            }
        }
        // check all possible moves in the top-right diagonal
        for (int i = 1; row-i >= 0 && col+i < 8; i++) {
            if (getPieceAt(col+i, row-i, board) == null) { // empty square
                coordinates.add(new int[]{col+i, row-i});
            } else if (getPieceAt(col+i, row-i, board).getColor() != getColor()) { // capture opponent's piece
                coordinates.add(new int[]{col+i, row-i});
                break; // can't move further in this direction
            } else { // own piece blocking the way
                break; // can't move further in this direction
            }
        }
        // check all possible moves in the bottom-left diagonal
        for (int i = 1; row+i < 8 && col-i >= 0; i++) {
            if (getPieceAt(col-i, row+i, board) == null) { // empty square
                coordinates.add(new int[]{col-i, row+i});
            } else if (getPieceAt(col-i, row+i, board).getColor() != getColor()) { // capture opponent's piece
                coordinates.add(new int[]{col-i, row+i});
                break; // can't move further in this direction
            } else { // own piece blocking the way
                break; // can't move further in this direction
            }
        }
        // check all possible moves in the bottom-right diagonal
        for (int i = 1; row+i < 8 && col+i < 8; i++) {
            if (getPieceAt(col+i, row+i, board) == null) { // empty square
                coordinates.add(new int[]{col+i, row+i});
            } else if (getPieceAt(col+i, row+i, board).getColor() != getColor()) { // capture opponent's piece
                coordinates.add(new int[]{col+i, row+i});
                break; // can't move further in this direction
            } else { // own piece blocking the way
                break; // can't move further in this direction
            }
        }
        // Loop through the coordinates in the ArrayList
        for (int[] coord : coordinates) {
            // Get the row and column indexes of the rectangle
            row = coord[1];
            col = coord[0];

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
        // Check if the bishop is moving diagonally
        int diffX = Math.abs(getPositionX() - possibleMoveX);
        int diffY = Math.abs(getPositionY() - possibleMoveY);
        if (diffX != diffY) {
            return false;
        }

        // Check if there are any pieces blocking the bishop's path
        int moveDirectionX = 0;
        int moveDirectionY = 0;
        if (diffX != 0) {
            moveDirectionX = (possibleMoveX - getPositionX()) / diffX;
        }
        if (diffY != 0) {
            moveDirectionY = (possibleMoveY - getPositionY()) / diffY;
        }
        for (int i = 1; i < diffX; i++) {
            int checkX = getPositionX() + i * moveDirectionX;
            int checkY = getPositionY() + i * moveDirectionY;
            if (getPieceAt(checkX, checkY, board) != null) {
                return false; // there is a piece blocking the bishop's path
            }
        }

        // Check if the target square is empty or has an opponent's piece that can be captured
        Piece targetPiece = getPieceAt(possibleMoveX, possibleMoveY, board);
        if (targetPiece == null || targetPiece.getColor() != getColor()) {
            return true; // the target square is empty or has an opponent's piece that can be captured
        } else {
            return false; // the target square has a piece of the same color
        }
    }


}
