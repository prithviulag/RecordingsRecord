package com.hxmalar.resrec;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class IntroController {
    @FXML
    private VBox container;
    @FXML
    private Label introHeading;
    @FXML
    private TextField userField;
    @FXML
    private PasswordField passField;
    @FXML
    private Button introEnter;
    @FXML
    private Button introExit;
    @FXML
    private ImageView logo;
    @FXML
    private Button themeToggle;
    private Scene introScene;
    private boolean inDarkTheme;

    @FXML
    protected void generateAssets()
    {
        introScene = introHeading.getScene();
        toggleTheme();

        Image logoImage = new Image(getClass().getResourceAsStream("/assets/resrecLogo.png"));
        //looks in src/main/resources folder, which is bundled into jar at compile time!
        logo.setImage(logoImage);
        logo.setPreserveRatio(true);
        logo.setFitHeight(70);

        Platform.runLater( () -> container.requestFocus() );
        //makes sure userField isn't highlighted on load
    }
    @FXML
    protected void onIntroEnterClick()
    {
        introHeading.setText("Logging in...");
        userField.setVisible(false);
        passField.setVisible(false);
        introEnter.setVisible(false);
        introExit.setVisible(false);
        introExit.setVisible(false);
        themeToggle.setVisible(false);
    }

    @FXML
    protected void onIntroExitClick()
    {
        Platform.exit();
    }

    @FXML
    protected void toggleTheme()
    {
        if (inDarkTheme)
        {
            introScene.getStylesheets().clear();
            introScene.getStylesheets().add(getClass().getResource("/stylesheets/light.css").toExternalForm());
            inDarkTheme = false;
        }
        else
        {
            introScene.getStylesheets().clear();
            introScene.getStylesheets().add(getClass().getResource("/stylesheets/dark.css").toExternalForm());
            inDarkTheme = true;
        }
    }
}