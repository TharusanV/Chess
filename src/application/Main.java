package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;


/*Order:
* Stage (Window / Similar to JFrame) ->
* Scene (Drawing surface for graphics / Similar to JPanel) ->
* Scene Graph (Hierarchy tree of nodes) ->
 * Nodes (Different components added to a scene e.g. button, images, text boxes)
 */
public class Main extends Application {

    //Piece Images
    String w_pawnImageString = "assets/white_pawn.png";
    String b_pawnImageString = "assets/black_pawn.png";
    String w_kingImageString = "assets/white_king.png";
    String b_kingImageString = "assets/black_king.png";
    String w_rookImageString = "assets/white_rook.png";
    String b_rookImageString = "assets/black_rook.png";
    String w_knightImageString = "assets/white_knight.png";
    String b_knightImageString = "assets/black_knight.png";
    String w_bishopImageString = "assets/white_bishop.png";
    String b_bishopImageString = "assets/black_bishop.png";
    String w_queenImageString = "assets/white_queen.png";
    String b_queenImageString = "assets/black_queen.png";

    public void start(Stage boardStage) {
        try {
            GridPane grid = new GridPane();
            grid.setPrefSize(800, 800);

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

