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

import java.io.FileNotFoundException;

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
    private String themeSetting;

    @FXML
    protected void generateAssets(String themeArg)
    {
        introScene = introHeading.getScene();
        changeTheme(themeArg);

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

    private void changeTheme(String themeArg) throws IllegalArgumentException
    {
        try {
            introScene.getStylesheets().clear();
            introScene.getStylesheets().add(getClass().getResource("/stylesheets/" + themeArg + ".css").toExternalForm());
            themeSetting = themeArg;

            if (themeSetting.equals("dark"))
            {
                themeToggle.setText("Light theme");
            }
            else
            {
                themeToggle.setText("Dark theme");
            }
        }
        catch (NullPointerException x) {
            throw new IllegalArgumentException(themeSetting);
        }
    }
    @FXML
    protected void toggleTheme()
    {
        //will need to be changed if more than dark/light are supported
        if (themeSetting.equals("dark"))
        {
            changeTheme("light");
        }
        else if (themeSetting.equals("light"))
        {
            changeTheme("dark");
        }
        else
        {
            System.out.println("Current theme does not support toggling.");
        }
    }
}