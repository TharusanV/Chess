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



/*Order:
* Stage (Window / Similar to JFrame) ->
* Scene (Drawing surface for graphics / Similar to JPanel) ->
* Scene Graph (Hierarchy tree of nodes) ->
 * Nodes (Different components added to a scene e.g. button, images, text boxes)
 */
public class Main extends Application {

    public Piece selectedPiece = null;

    public void start(Stage boardStage) {
        try {
            GridPane grid = new GridPane();
            grid.setPrefSize(800, 800);

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
                        ImageView imageView = new ImageView(pawn.getImage());
                        imageView.setFitWidth(square.getWidth());
                        imageView.setFitHeight(square.getHeight());
                        GridPane.setConstraints(imageView, col, row);
                        grid.getChildren().add(imageView);
                    }

                    // Placing the rest of the pieces
                    if (row == 0 || row == 7){
                        if(col == 0 || col == 7){
                            Rook rook = new Rook(numRooks, col, row, (row == 0) ? Color.BLACK : Color.WHITE, (row == 0) ? new Image("assets/black_rook.png") : new Image("assets/white_rook.png"));
                            numRooks++;
                            ImageView imageView = new ImageView(rook.getImage());
                            imageView.setFitWidth(square.getWidth());
                            imageView.setFitHeight(square.getHeight());
                            GridPane.setConstraints(imageView, col, row);
                            grid.getChildren().add(imageView);
                        }
                        if(col == 1 || col == 6){
                            Knight knight = new Knight(numKnights, col, row, (row == 0) ? Color.BLACK : Color.WHITE, (row == 0) ? new Image("assets/black_knight.png") : new Image("assets/white_knight.png"));
                            numKnights++;
                            ImageView imageView = new ImageView(knight.getImage());
                            imageView.setFitWidth(square.getWidth());
                            imageView.setFitHeight(square.getHeight());
                            GridPane.setConstraints(imageView, col, row);
                            grid.getChildren().add(imageView);
                        }
                        if(col == 2 || col == 5){
                            Bishop bishop = new Bishop(numBishops, col, row, (row == 0) ? Color.BLACK : Color.WHITE, (row == 0) ? new Image("assets/black_bishop.png") : new Image("assets/white_bishop.png"));
                            numBishops++;
                            ImageView imageView = new ImageView(bishop.getImage());
                            imageView.setFitWidth(square.getWidth());
                            imageView.setFitHeight(square.getHeight());
                            GridPane.setConstraints(imageView, col, row);
                            grid.getChildren().add(imageView);
                        }
                        if(col == 3){
                            Queen queen = new Queen(numQueens, col, row, (row == 0) ? Color.BLACK : Color.WHITE, (row == 0) ? new Image("assets/black_queen.png") : new Image("assets/white_queen.png"));
                            numQueens++;
                            ImageView imageView = new ImageView(queen.getImage());
                            imageView.setFitWidth(square.getWidth());
                            imageView.setFitHeight(square.getHeight());
                            GridPane.setConstraints(imageView, col, row);
                            grid.getChildren().add(imageView);
                        }
                        if(col == 4){
                            King king = new King(numKings, col, row, (row == 0) ? Color.BLACK : Color.WHITE, (row == 0) ? new Image("assets/black_King.png") : new Image("assets/white_King.png"));
                            numKings++;
                            ImageView imageView = new ImageView(king.getImage());
                            imageView.setFitWidth(square.getWidth());
                            imageView.setFitHeight(square.getHeight());
                            GridPane.setConstraints(imageView, col, row);
                            grid.getChildren().add(imageView);
                        }
                    }
                }
                isWhite = !isWhite;
            }

            // Set the alignment of the grid to center
            grid.setAlignment(Pos.CENTER);


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

