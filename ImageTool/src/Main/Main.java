package Main;


/* Program: INFO 5100
 * CreatedBy: Zhan Wang
 * CreatDate: April/2021*/



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view/view.fxml"));
        primaryStage.setTitle("Image Management Tool");
        primaryStage.setScene(new Scene(root, 800, 400));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
