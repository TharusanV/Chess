package pieces;

import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Queen extends Piece{
    private ArrayList<int[]> coordinates;

    public Queen(int pieceID, int positionX, int positionY, Color color, Image image) {
        super(pieceID, positionX, positionY, color, image);
        this.coordinates = new ArrayList<int[]>();
    }

    public ArrayList<int[]> getCoordinates() {
        return this.coordinates;
    }

    public void possibleMoves(GridPane board){
        int rowD = getPositionY(); // get the current row of the queen
        int colD = getPositionX(); // get the current column of the queen

        // check all possible moves in the top-left diagonal
        for (int i = 1; rowD-i >= 0 && colD-i >= 0; i++) {
            if (getPieceAt(colD-i, rowD-i, board)  == null) { // empty square
                coordinates.add(new int[]{colD-i, rowD-i});
            } else if (getPieceAt(colD-i, rowD-i, board).getColor() != getColor()) { // capture opponent's piece
                coordinates.add(new int[]{colD-i, rowD-i});
                break; // can't move further in this direction
            } else { // own piece blocking the way
                break; // can't move further in this direction
            }
        }
        // check all possible moves in the top-right diagonal
        for (int i = 1; rowD-i >= 0 && colD+i < 8; i++) {
            if (getPieceAt(colD+i, rowD-i, board) == null) { // empty square
                coordinates.add(new int[]{colD+i, rowD-i});
            } else if (getPieceAt(colD+i, rowD-i, board).getColor() != getColor()) { // capture opponent's piece
                coordinates.add(new int[]{colD+i, rowD-i});
                break; // can't move further in this direction
            } else { // own piece blocking the way
                break; // can't move further in this direction
            }
        }
        // check all possible moves in the bottom-left diagonal
        for (int i = 1; rowD+i < 8 && colD-i >= 0; i++) {
            if (getPieceAt(colD-i, rowD+i, board) == null) { // empty square
                coordinates.add(new int[]{colD-i, rowD+i});
            } else if (getPieceAt(colD-i, rowD+i, board).getColor() != getColor()) { // capture opponent's piece
                coordinates.add(new int[]{colD-i, rowD+i});
                break; // can't move further in this direction
            } else { // own piece blocking the way
                break; // can't move further in this direction
            }
        }
        // check all possible moves in the bottom-right diagonal
        for (int i = 1; rowD+i < 8 && colD+i < 8; i++) {
            if (getPieceAt(colD+i, rowD+i, board) == null) { // empty square
                coordinates.add(new int[]{colD+i, rowD+i});
            } else if (getPieceAt(colD+i, rowD+i, board).getColor() != getColor()) { // capture opponent's piece
                coordinates.add(new int[]{colD+i, rowD+i});
                break; // can't move further in this direction
            } else { // own piece blocking the way
                break; // can't move further in this direction
            }
        }

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
        Piece targetPiece = getPieceAt(possibleMoveX, possibleMoveY, board);

        // Check if the bishop is moving diagonally
        int diffX = Math.abs(getPositionX() - possibleMoveX);
        int diffY = Math.abs(getPositionY() - possibleMoveY);
        if (diffX == diffY) {
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
            if (targetPiece == null || targetPiece.getColor() != getColor()) {
                return true; // the target square is empty or has an opponent's piece that can be captured
            } else {
                return false; // the target square has a piece of the same color
            }
        }

        // Check if the Queen is moving vertically
        if (possibleMoveX == getPositionX()) {
            //Gets the minY and maxY - difference between the initial and possible destination
            int minY = Math.min(getPositionY(), possibleMoveY);
            int maxY = Math.max(getPositionY(), possibleMoveY);
            //minY gets added to +1 as we don't need to check the current position
            for (int y = minY + 1; y < maxY; y++) {
                if (getPieceAt(possibleMoveX, y, board) != null) {
                    return false;
                }
            }

            return targetPiece == null || targetPiece.getColor() != getColor();
        }

        // Check if the Queen is moving horizontally
        if (possibleMoveY == getPositionY()) {
            //Gets the minX and maxX - difference between the initial and possible destination to loop through the squares
            int minX = Math.min(getPositionX(), possibleMoveX);
            int maxX = Math.max(getPositionX(), possibleMoveX);
            //minX gets added to +1 as we don't need to check the current position
            for (int x = minX + 1; x < maxX; x++) {
                if (getPieceAt(x, possibleMoveY, board) != null) {
                    return false;
                }
            }

            return targetPiece == null || targetPiece.getColor() != getColor();
        }

        // If the queen is not moving diagonally or horizontally/vertically, it's an invalid move
        return false;
    }
}
