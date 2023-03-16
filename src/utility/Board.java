package utility;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import pieces.Pawn;
import pieces.Piece;
import pieces.Queen;

import java.util.ArrayList;

public class Board {

    private GridPane board;

    public Board(GridPane board){
        this.board = board;
    }

    public GridPane getBoard() {
        return this.board;
    }
    public void setBoard(GridPane board) {
        this.board = board;
    }

    public Piece getPieceAt(int x, int y, GridPane board) {
        for (Node node : board.getChildren()) {
            if (board.getColumnIndex(node) == x && board.getRowIndex(node) == y) {
                if (node instanceof ImageView) {
                    Piece piece = (Piece) node.getUserData();
                    return piece;
                }
            }
        }
        return null;
    }

    public void updateChessPiece(int startX, int startY, int endX, int endY, ArrayList<Piece> pieces, GridPane grid) {
        // Find the piece to move
        Piece movingPiece = null;
        for (Piece piece : pieces) {
            if (piece.getPositionX() == startX && piece.getPositionY() == startY) {
                movingPiece = piece;
                break;
            }
        }
        if (movingPiece == null) {
            // The piece to move was not found
            return;
        }

        // Check if there is a piece at the destination coordinate
        Piece targetPiece = null;
        for (Piece piece : pieces) {
            if (piece.getPositionX() == endX && piece.getPositionY() == endY) {
                targetPiece = piece;
                break;
            }
        }

        if (targetPiece != null) {
            // Remove the target piece from the array list
            pieces.remove(targetPiece);
        }

        if(movingPiece instanceof Pawn){
            if(movingPiece.getColor() == Color.WHITE && startY == 1 && endY == 0){
                pieces.remove(movingPiece);
                Queen queen = new Queen(pieces.size(), endX, endY, Color.WHITE, new Image("assets/white_queen.png"));
                queen.addImageViewToGridPane(grid, endX, endY);
                pieces.add(queen);
            }
            if(movingPiece.getColor() == Color.BLACK && startY == 6 && endY == 7){
                pieces.remove(movingPiece);
                Queen queen = new Queen(pieces.size(), endX, endY, Color.BLACK, new Image("assets/black_queen.png"));
                queen.addImageViewToGridPane(grid, endX, endY);
                pieces.add(queen);
            }
        }

        // Move the piece
        movingPiece.setPositionX(endX);
        movingPiece.setPositionY(endY);

        // Update the position of the piece's image view on the board
        movingPiece.setImageViewPosition(endX, endY, grid);
    }

    public void updateGridPane(GridPane grid, ArrayList<Piece> chessPieces) {
        grid.getChildren().clear(); // clear all existing nodes from the GridPane

        boolean isWhite = true;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Rectangle square = new Rectangle(100, 100);
                if (isWhite) {
                    square.setFill(Color.WHITE);
                } else {
                    square.setFill(Color.BROWN);
                }
                grid.add(square, col, row);
                isWhite = !isWhite;
            }
            isWhite = !isWhite;
        }

        // Loop through the ArrayList of Piece objects and add their ImageViews to the GridPane
        for (Piece piece : chessPieces) {
            grid.add(piece.getImageView(), piece.getPositionX(), piece.getPositionY());
        }
    }


}
