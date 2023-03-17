package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.Parent;
import pieces.*;
import javafx.event.EventHandler;
import utility.Board;

import java.util.ArrayList;
import java.util.Optional;


/*Order:
* Stage (Window / Similar to JFrame) ->
* Scene (Drawing surface for graphics / Similar to JPanel) ->
* Scene Graph (Hierarchy tree of nodes) ->
 * Nodes (Different components added to a scene e.g. button, images, text boxes)
 */
public class Main extends Application {

    Piece selectedPiece;
    Piece possiblePiece;
    int numSelectedPieces = 0;

    boolean isWhiteTurn = true;
    boolean isBlackTurn = false;

    ArrayList<Piece> allPieces = new ArrayList<Piece>();

    public void start(Stage boardStage) {
        try {
            GridPane grid = new GridPane();
            grid.setPrefSize(800, 800);

            Board board = new Board(grid);
            board.resetBoard(grid, allPieces);


            // Set the alignment of the grid to center
            grid.setAlignment(Pos.CENTER);

            // Add mouse event listeners to each square
            grid.setOnMouseClicked(event -> {
                Node clickedNode = event.getPickResult().getIntersectedNode();
                board.updateGridPane(grid, allPieces);
                if(numSelectedPieces == 0){
                    if (clickedNode instanceof ImageView) {
                        Integer rowIndex = GridPane.getRowIndex(clickedNode);
                        Integer colIndex = GridPane.getColumnIndex(clickedNode);
                        //System.out.println("Step 1 - Clicked on row " + rowIndex + ", column " + colIndex);
                        selectedPiece = board.getPieceAt(colIndex, rowIndex, grid);
                        if (selectedPiece.getColor() == Color.WHITE && isWhiteTurn) {
                            selectedPiece.possibleMoves(grid);
                            selectedPiece.possibleMovesShown(grid);
                            numSelectedPieces++;
                        } else if (selectedPiece.getColor() == Color.BLACK && !isWhiteTurn) {
                            selectedPiece.possibleMoves(grid);
                            selectedPiece.possibleMovesShown(grid);
                            numSelectedPieces++;
                        }
                    }
                }
                else{
                    if (clickedNode instanceof ImageView ) {
                        Integer rowIndex = GridPane.getRowIndex(clickedNode);
                        Integer colIndex = GridPane.getColumnIndex(clickedNode);
                        //System.out.println("Step 2 - Clicked on row " + rowIndex + ", column " + colIndex);
                        possiblePiece = board.getPieceAt(colIndex, rowIndex, grid);
                        if(selectedPiece.canMove(possiblePiece.getPositionX(), possiblePiece.getPositionY(), grid)){
                            board.updateChessPiece(selectedPiece.getPositionX(), selectedPiece.getPositionY(), colIndex, rowIndex, allPieces, grid);
                            board.updateGridPane(grid, allPieces);
                            numSelectedPieces = 0;
                            if (isWhiteTurn) {
                                isWhiteTurn = false;
                                isBlackTurn = true;
                                board.isCheckMate(grid, allPieces ,isWhiteTurn, boardStage);
                            } else {
                                isWhiteTurn = true;
                                isBlackTurn = false;
                                board.isCheckMate(grid, allPieces ,isWhiteTurn, boardStage);
                            }
                        };
                        numSelectedPieces = 0;
                    }
                    else if (clickedNode instanceof Rectangle) {
                        Integer rowIndex = GridPane.getRowIndex(clickedNode);
                        Integer colIndex = GridPane.getColumnIndex(clickedNode);
                        System.out.println("Step 2 - Clicked on row " + rowIndex + ", column " + colIndex);
                        if(selectedPiece.canMove(colIndex, rowIndex, grid)){
                            board.updateChessPiece(selectedPiece.getPositionX(), selectedPiece.getPositionY(), colIndex, rowIndex, allPieces, grid);
                            board.updateGridPane(grid, allPieces);
                            numSelectedPieces = 0;
                            if (isWhiteTurn) {
                                isWhiteTurn = false;
                                isBlackTurn = true;
                                board.isCheckMate(grid, allPieces ,isWhiteTurn, boardStage);
                            } else {
                                isWhiteTurn = true;
                                isBlackTurn = false;
                                board.isCheckMate(grid, allPieces ,isWhiteTurn, boardStage);
                            }
                        };
                        numSelectedPieces = 0;
                    }
                    else{
                        numSelectedPieces = 0;
                    }
                }

            });


            //Board stuff
            boardStage.setTitle("Chess");
            Image icon = new Image("icon.png");
            boardStage.getIcons().add(icon);
            boardStage.setResizable(false);

            //Setting the grid to the scene
            Scene scene = new Scene(grid);
            //Adding the scene to the stage
            boardStage.setScene(scene);
            //To see the stage
            boardStage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        /*Static Method that belongs to the application class taking
         * and when called we send our string array of arguments to the launch
         * method. Behind the scenes the start method would be called*/
        launch(args);
    }

}

