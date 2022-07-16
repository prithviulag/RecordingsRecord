package com.hxmalar.resrec;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import java.net.URL;

import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.StageStyle;

import java.io.IOException;

public class IntroApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Font.loadFont(getClass().getResource("/fonts/WorkSans-Light.ttf").toExternalForm(), 10);

        URL viewUrl = IntroApplication.class.getResource("intro.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(viewUrl);
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("ResRec");
        stage.setScene(scene);

        stage.getIcons().add(new Image(getClass().getResourceAsStream("/assets/resrecIcon.png")));

        //Pane pane = fxmlLoader.load(openStream()); unnecessary
        IntroController introController = fxmlLoader.getController();
        introController.generateAssets();

        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}