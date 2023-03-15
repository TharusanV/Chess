package pieces;

import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Pawn extends Piece{

    private boolean isFirstMove;

    public Pawn(int pieceID, int positionX, int positionY, Color color, Image image) {
        super(pieceID, positionX, positionY, color, image);
        this.isFirstMove = true;
    }

    public boolean getIsFirstMove() {
        return isFirstMove;
    }

    public void setIsFirstMove() {
        this.isFirstMove = !isFirstMove;
    }

    public void possibleMoves(GridPane board){
        ArrayList<int[]> coordinates = new ArrayList<int[]>();
        int moveDirection = getColor() == Color.WHITE ? -1 : 1;

        if(getPieceAt(getPositionX(), getPositionY() + moveDirection, board) == null){
            coordinates.add(new int[] {getPositionX(), getPositionY() + moveDirection});
            if(getIsFirstMove() == true && getPieceAt(getPositionX(), getPositionY() + moveDirection * 2, board) == null){
                coordinates.add(new int[] {getPositionX(), getPositionY() + moveDirection * 2});
            }
        }

        if(getPieceAt(getPositionX()+moveDirection, getPositionY() + moveDirection, board) != null){
            if (getPieceAt(getPositionX()+moveDirection, getPositionY() + moveDirection, board).getColor() != getColor()){
                coordinates.add(new int[] {getPositionX() + moveDirection, getPositionY() + moveDirection});
            }
        }

        if(getPieceAt(getPositionX() - moveDirection, getPositionY() - moveDirection, board) != null){
            if (getPieceAt(getPositionX() - moveDirection, getPositionY() - moveDirection, board).getColor() != getColor()){
                coordinates.add(new int[] {getPositionX() - moveDirection, getPositionY() - moveDirection});
            }
        }




        // Loop through the coordinates in the ArrayList
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
        // Check if the pawn is moving diagonally
        if (possibleMoveX != getPositionX() && possibleMoveY != getPositionY()) {
            // Pawns can only move diagonally when capturing an opponent's piece
            Piece targetPiece = getPieceAt(possibleMoveX, possibleMoveY, board);
            if(targetPiece != null){
                int diagonalDirection = getColor() == Color.WHITE ? -1 : 1;
                if (targetPiece.getColor() != getColor() &&
                        (possibleMoveX == getPositionX() + diagonalDirection ||
                                possibleMoveX == getPositionX() - diagonalDirection) &&
                        possibleMoveY == getPositionY() + diagonalDirection) {
                    return true;
                }
            }
            return false;
        }

        // Check if the pawn is moving forward
        int moveDirection = getColor() == Color.WHITE ? -1 : 1;

        if (possibleMoveX == getPositionX() && possibleMoveY == getPositionY() + moveDirection * 2) {
            // Moving two spaces forward
            if (getIsFirstMove() == false) {
                return false;
            }
            // Check if there is a piece in the way
            if (getPieceAt(possibleMoveX, getPositionY() + moveDirection, board) != null) {
                return false;
            }
            setIsFirstMove();
            return getPieceAt(possibleMoveX, possibleMoveY, board) == null;
        }

        if (possibleMoveX == getPositionX() && possibleMoveY == getPositionY() + moveDirection) {
            // Moving one space forward
            return getPieceAt(possibleMoveX, possibleMoveY, board) == null;
        }

        // The move is not valid
        return false;
    }


}
