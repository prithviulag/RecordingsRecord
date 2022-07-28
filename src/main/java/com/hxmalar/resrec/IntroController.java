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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class IntroController {
    @FXML
    private VBox container;
    @FXML
    private VBox loginContainer;
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
    @FXML
    private HBox lowerMenu;
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
            throws IllegalArgumentException
    {
        introHeading.setText("Logging in...");
        loginContainer.setVisible(false);
        lowerMenu.setVisible(false);
        String inputPass = passField.getText();
        LoginAuth.RegisterLogin(inputPass);
        //LoginAuth.VerifyLogin(inputPass);
        inputPass = "";
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