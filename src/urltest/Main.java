package urltest;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("weatherAPP.fxml"));
        primaryStage.setTitle("每日天气");
        primaryStage.getIcons().add(new Image("icon.png"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("./application.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
