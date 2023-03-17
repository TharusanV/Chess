package utility;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

import pieces.*;

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

    public void resetBoard(GridPane grid, ArrayList<Piece> allPieces){
        grid.getChildren().clear();
        allPieces.clear();

        int numPawns = 0;
        int numKings = 0;
        int numQueens = 0;
        int numBishops = 0;
        int numKnights = 0;
        int numRooks= 0;

        // Create the brown and white checked pattern
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

                // Place pawns on the board
                if (row == 1 || row == 6) {
                    Pawn pawn = new Pawn(numPawns, col, row, (row == 1) ? Color.BLACK : Color.WHITE, (row == 1) ? new Image("assets/black_pawn.png") : new Image("assets/white_pawn.png"));
                    numPawns++;
                    pawn.addImageViewToGridPane(grid, col, row);
                    allPieces.add(pawn);
                }

                // Placing the rest of the pieces
                if (row == 0 || row == 7){
                    if(col == 0 || col == 7){
                        Rook rook = new Rook(numRooks, col, row, (row == 0) ? Color.BLACK : Color.WHITE, (row == 0) ? new Image("assets/black_rook.png") : new Image("assets/white_rook.png"));
                        numRooks++;
                        rook.addImageViewToGridPane(grid, col, row);
                        allPieces.add(rook);
                    }
                    if(col == 1 || col == 6){
                        Knight knight = new Knight(numKnights, col, row, (row == 0) ? Color.BLACK : Color.WHITE, (row == 0) ? new Image("assets/black_knight.png") : new Image("assets/white_knight.png"));
                        numKnights++;
                        knight.addImageViewToGridPane(grid, col, row);
                        allPieces.add(knight);
                    }
                    if(col == 2 || col == 5){
                        Bishop bishop = new Bishop(numBishops, col, row, (row == 0) ? Color.BLACK : Color.WHITE, (row == 0) ? new Image("assets/black_bishop.png") : new Image("assets/white_bishop.png"));
                        numBishops++;
                        bishop.addImageViewToGridPane(grid, col, row);
                        allPieces.add(bishop);
                    }
                    if(col == 3){
                        Queen queen = new Queen(numQueens, col, row, (row == 0) ? Color.BLACK : Color.WHITE, (row == 0) ? new Image("assets/black_queen.png") : new Image("assets/white_queen.png"));
                        numQueens++;
                        queen.addImageViewToGridPane(grid, col, row);
                        allPieces.add(queen);
                    }
                    if(col == 4){
                        King king = new King(numKings, col, row, (row == 0) ? Color.BLACK : Color.WHITE, (row == 0) ? new Image("assets/black_King.png") : new Image("assets/white_King.png"));
                        numKings++;
                        king.addImageViewToGridPane(grid, col, row);
                        allPieces.add(king);
                    }
                }

            }
            isWhite = !isWhite;
        }
    }

    public void isCheckMate(GridPane grid, ArrayList<Piece> chessPieces, boolean isWhiteTurn, Stage boardStage) {
        int[][] offsets = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        Piece king = null;
        ArrayList<int[]> kingMoves = new ArrayList<>();
        ArrayList<Piece> opponentPieces = new ArrayList<>();
        kingMoves.clear();
        opponentPieces.clear();
        int numOfAttackOps = 0;

        for (Piece piece : chessPieces) {
            if (piece.getColor() == (isWhiteTurn ? Color.WHITE : Color.BLACK)) {
                if (piece instanceof King) {
                    king = piece;
                    for (int[] move : offsets) {
                        int row = king.getPositionY() + move[0];
                        int col = king.getPositionX() + move[1];
                        if (row >= 0 && row < board.getRowCount() && col >= 0 && col < board.getColumnCount()) {
                            if (getPieceAt(col, row, board) != null) {
                                if (getPieceAt(col, row, board).getColor() != king.getColor()) {
                                    System.out.println(col + "," + row);
                                    kingMoves.add(new int[]{col, row});
                                }
                            } else {
                                kingMoves.add(new int[]{col, row});
                            }

                        }
                    }
                }
            } else {
                opponentPieces.add(piece);
            }
        }


        for (Piece piece : opponentPieces) {
            for (int[] offset : offsets) {
                int row = king.getPositionY() + offset[0];
                int col = king.getPositionX() + offset[1];
                piece.possibleMoves(grid);
                for (int[] possible : piece.getCoordinates()) {
                    int y = possible[1];
                    int x = possible[0];
                    if (col == x && row == y) {
                        kingMoves.removeIf(move -> (move[0] == col && move[1] == row));
                        numOfAttackOps++;
                    }
                }
            }
        }

        //System.out.println(kingMoves.size());
        Popup popup = new Popup();
        popup.setAutoHide(true);

        if(kingMoves.isEmpty() && numOfAttackOps > 0){
            Label label = new Label((isWhiteTurn ? "Black Win" : "White Win"));
            label.setStyle("-fx-font-weight: bold; -fx-font-size: 64px; -fx-text-fill: red;");

            Button resetButton = new Button("Restart");
            resetButton.setOnAction(event -> {
                resetBoard(grid, chessPieces);
                popup.hide();
            });

            resetButton.setStyle("-fx-border-color: #ffffff; -fx-border-width: 0;");

            VBox popupContent = new VBox(label, resetButton);
            popupContent.setAlignment(Pos.CENTER);
            popupContent.setSpacing(10);

            popup.getContent().add(popupContent);
            popup.setHideOnEscape(false);
            popup.setAnchorLocation(PopupWindow.AnchorLocation.WINDOW_BOTTOM_LEFT);
            popup.show(boardStage);

        } else if(kingMoves.size() < 8 && kingMoves.size() > 0  && numOfAttackOps > 0){

            Label label = new Label((isWhiteTurn ? "White in check" : "Black in check"));
            label.setStyle("-fx-font-weight: bold; -fx-font-size: 64px; -fx-text-fill: blue;");
            popup.getContent().add(label);
            popup.show(boardStage);

            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), ev -> {
                popup.hide();
            }));
            timeline.play();
        }

    }




}
