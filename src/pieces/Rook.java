package pieces;

import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Rook extends Piece{

    private ArrayList<int[]> coordinates;

    public Rook(int pieceID, int positionX, int positionY, Color color, Image image) {
        super(pieceID, positionX, positionY, color, image);
        this.coordinates = new ArrayList<int[]>();
    }

    public ArrayList<int[]> getCoordinates() {
        return this.coordinates;
    }

    public void possibleMoves(GridPane board){
        coordinates.clear();

        // Check moves in the same row to the right
        for(int col = getPositionX() + 1; col < 8; col++){
            if (getPieceAt(col, getPositionY(), board) == null){
                coordinates.add(new int[] {col, getPositionY()});
            }
            else if (getPieceAt(col, getPositionY(), board) != null && getPieceAt(col, getPositionY(), board).getColor() != getColor()){
                coordinates.add(new int[] {col, getPositionY()});
                break;
            }
            else{
                break;
            }
        }

        // Check moves in the same row to the left
        for (int col = getPositionX() - 1; col >= 0; col--) {
            if (getPieceAt(col, getPositionY(), board) == null){
                coordinates.add(new int[] {col, getPositionY()});
            }
            else if (getPieceAt(col, getPositionY(), board) != null && getPieceAt(col, getPositionY(), board).getColor() != getColor()){
                coordinates.add(new int[] {col, getPositionY()});
                break;
            }
            else{
                break;
            }
        }

        // Check moves in the same column upwards
        for (int row = getPositionY() - 1; row >= 0; row--) {
            if (getPieceAt(getPositionX(), row, board) == null){
                coordinates.add(new int[] {getPositionX(), row});
            }
            else if (getPieceAt(getPositionX(), row, board) != null && getPieceAt(getPositionX(), row, board).getColor() != getColor()){
                coordinates.add(new int[] {getPositionX(), row});
                break;
            }
            else{
                break;
            }
        }

        for (int row = getPositionY() + 1; row < 8; row++) {
            if (getPieceAt(getPositionX(), row, board) == null){
                coordinates.add(new int[] {getPositionX(), row});
            }
            else if (getPieceAt(getPositionX(), row, board) != null && getPieceAt(getPositionX(), row, board).getColor() != getColor()){
                coordinates.add(new int[] {getPositionX(), row});
                break;
            }
            else{
                break;
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
        // Check if the rook is moving diagonally (Not Allowed)
        if (possibleMoveX != getPositionX() && possibleMoveY != getPositionY()) {
            return false;
        }

        // Check if the rook is moving vertically
        if (possibleMoveX == getPositionX()) {
            //Gets the minY and maxY - difference between the initial and possible destination
            int minY = Math.min(getPositionY(), possibleMoveY);
            int maxY = Math.max(getPositionY(), possibleMoveY);
            Piece targetPiece = getPieceAt(possibleMoveX, possibleMoveY, board);
            //minY gets added to +1 as we don't need to check the current position
            for (int y = minY + 1; y < maxY; y++) {
                if (getPieceAt(possibleMoveX, y, board) != null) {
                    return false;
                }
            }

            return targetPiece == null || targetPiece.getColor() != getColor();
        }

        // Check if the rook is moving horizontally
        if (possibleMoveY == getPositionY()) {
            //Gets the minX and maxX - difference between the initial and possible destination to loop through the squares
            int minX = Math.min(getPositionX(), possibleMoveX);
            int maxX = Math.max(getPositionX(), possibleMoveX);
            Piece targetPiece = getPieceAt(possibleMoveX, possibleMoveY, board);
            //minX gets added to +1 as we don't need to check the current position
            for (int x = minX + 1; x < maxX; x++) {
                if (getPieceAt(x, possibleMoveY, board) != null) {
                    return false;
                }
            }

            return targetPiece == null || targetPiece.getColor() != getColor();
        }

        // The move is not valid
        return false;
    }
}
