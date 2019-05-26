package hellofx;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;


public class HelloFX extends Application {

    @Override
    public void start(Stage stage) {
        //Parent root = FXMLLoader.load(getClass().getResource("scene.fxml"));

        // Create and set the Scene.
        Scene scene = new Scene(new Group(), 640, 360);
        stage.setScene(scene);

        File file = new File("src/main/resources/imgs/maxresdefault.jpg");
        final URL resource = getClass().getResource("imgs/Blend_W-gladRLvno7U.mp4");
        String loc = this.getClass().getResource("Blend_W-gladRLvno7U.mp4").toString();


        String test = Paths.get("src/main/resources/hellofxBlend_W-gladRLvno7U.mp4").toUri().toString();
        //Set up Media
        String source  = "src/main/resources/imgs/Blend_W-gladRLvno7U.mp4";
        Media media = new Media(loc);
        // Create the player and set to play automatically.
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);

        // Create the view and add it to the Scene.
        MediaView mediaView = new MediaView(mediaPlayer);
        ((Group) scene.getRoot()).getChildren().add(mediaView);

//
//        HBox hbox = new HBox(imageView);
//
//        //Scene scene = new Scene(root);
//        Scene scene = new Scene(hbox, 1280, 720);
        //scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        stage.setTitle("JavaFX and Gradle");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}