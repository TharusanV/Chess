package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/*Order:
* Stage (Window / Similar to JFrame) ->
* Scene (Drawing surface for graphics / Similar to JPanel) ->
* Scene Graph (Hierarchy tree of nodes) ->
 * Nodes (Different components added to a scene e.g. button, images, text boxes)
 */
public class Main extends Application {
        public void start(Stage boardStage) {
            try {
                //Creating a root node to arrange all of the different nodes (components) that we have
                Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
                //Adding root node to the scene
                Scene scene = new Scene(root);
                //Gets the style sheet for the scene


                //Stage styling
                boardStage.setTitle("Chess");
                Image icon = new Image("icon.png");
                boardStage.getIcons().add(icon);
                boardStage.setResizable(false);

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

